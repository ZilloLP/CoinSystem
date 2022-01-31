package de.zillolp.coinsystem.utils;

import de.zillolp.coinsystem.CoinSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class ConfigManager {
    private CoinSystem coinSystem = CoinSystem.coinSystem;

    public ConfigUtil getNewConfig(String filePath, String[] header) {
        File file = getConfigFile(filePath);
        if (!file.exists()) {
            prepareFile(filePath);
            if (header != null && header.length != 0) {
                setHeader(file, header);
            }
        }
        ConfigUtil config = new ConfigUtil(getConfigContent(filePath), file, getCommentsNum(file));
        return config;

    }

    public ConfigUtil getNewConfig(String filePath) {
        return getNewConfig(filePath, null);
    }

    private File getConfigFile(String file) {
        if (file.isEmpty() || file == null) {
            return null;
        }
        File configFile;
        if (file.contains("/")) {
            if (file.startsWith("/")) {
                configFile = new File(coinSystem.getDataFolder() + file.replace("/", File.separator));
            } else {
                configFile = new File(coinSystem.getDataFolder() + File.separator + file.replace("/", File.separator));
            }
        } else {
            configFile = new File(coinSystem.getDataFolder(), file);
        }
        return configFile;
    }

    public void prepareFile(String filePath, String resource) {
        File file = getConfigFile(filePath);
        if (file.exists()) {
            return;
        }
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            if (resource != null && !resource.isEmpty()) {
                this.copyResource(coinSystem.getResource(resource), file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepareFile(String filePath) {
        prepareFile(filePath, null);
    }

    public void setHeader(File file, String[] header) {
        if (!file.exists()) {
            return;
        }
        try {
            String currentLine;
            StringBuilder config = new StringBuilder("");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                config.append(currentLine + "\n");
            }
            reader.close();
            config.append("# +----------------------------------------------------+ #\n");
            for (String line : header) {
                if (line.length() > 50) {
                    continue;
                }
                int lenght = (50 - line.length()) / 2;
                StringBuilder finalLine = new StringBuilder(line);
                for (int i = 0; i < lenght; i++) {
                    finalLine.append(" ");
                    finalLine.reverse();
                    finalLine.append(" ");
                    finalLine.reverse();
                }
                if (line.length() % 2 != 0) {
                    finalLine.append(" ");
                }
                config.append("# < " + finalLine.toString() + " > #\n");
            }
            config.append("# +----------------------------------------------------+ #");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(this.prepareConfigString(config.toString()));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream getConfigContent(File file) {
        if (!file.exists()) {
            return null;
        }
        try {
            int commentNum = 0;
            String addLine;
            String currentLine;
            String pluginName = getPluginName();
            StringBuilder whole = new StringBuilder("");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("#")) {
                    addLine = currentLine.replaceFirst("#", pluginName + "_COMMENT_" + commentNum + ":");
                    whole.append(addLine + "\n");
                    commentNum++;
                } else {
                    whole.append(currentLine + "\n");
                }
            }
            String config = whole.toString();
            InputStream configStream = new ByteArrayInputStream(config.getBytes(Charset.forName("UTF-8")));
            reader.close();
            return configStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getCommentsNum(File file) {
        if (!file.exists()) {
            return 0;
        }
        try {
            int comments = 0;
            String currentLine;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("#")) {
                    comments++;
                }
            }
            reader.close();
            return comments;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public InputStream getConfigContent(String filePath) {
        return getConfigContent(getConfigFile(filePath));
    }

    private String prepareConfigString(String configString) {
        int lastLine = 0;
        int headerLine = 0;
        String[] lines = configString.split("\n");
        StringBuilder config = new StringBuilder("");
        for (String line : lines) {
            if (line.startsWith(getPluginName() + "_COMMENT")) {
                String comment = "#" + line.trim().substring(line.indexOf(":") + 1);
                if (comment.startsWith("# +-")) {
                    if (headerLine == 0) {
                        config.append(comment + "\n");
                        lastLine = 0;
                        headerLine = 1;
                    } else if (headerLine == 1) {
                        config.append(comment + "\n\n");
                        lastLine = 0;
                        headerLine = 0;
                    }
                } else {
                    String normalComment;
                    if (comment.startsWith("# ' ")) {
                        normalComment = comment.substring(0, comment.length() - 1).replaceFirst("# ' ", "# ");
                    } else {
                        normalComment = comment;
                    }
                    if (lastLine == 0) {
                        config.append(normalComment + "\n");
                    } else if (lastLine == 1) {
                        config.append("\n" + normalComment + "\n");
                    }
                    lastLine = 0;
                }
            } else {
                config.append(line + "\n");
                lastLine = 1;
            }
        }
        return config.toString();

    }

    public void saveConfig(String configString, File file) {
        String configuration = prepareConfigString(configString);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(configuration);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPluginName() {
        return coinSystem.getDescription().getName();
    }

    private void copyResource(InputStream resource, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            int lenght;
            byte[] buf = new byte[1024];
            while ((lenght = resource.read(buf)) > 0) {
                out.write(buf, 0, lenght);
            }
            out.close();
            resource.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

