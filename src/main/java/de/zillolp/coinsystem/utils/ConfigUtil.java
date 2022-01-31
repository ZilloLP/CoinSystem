package de.zillolp.coinsystem.utils;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtil {
    private ConfigManager configManager;
    private File file;
    private int comments;
    private FileConfiguration config;

    public ConfigUtil(InputStream configStream, File file, int comments) {
        this.configManager = new ConfigManager();
        this.file = file;
        this.comments = comments;
        this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(configStream));
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public Object get(String path) {
        return config.get(path);
    }

    public Object get(String path, Object def) {
        return config.get(path, def);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public String getString(String path, String def) {
        return config.getString(path, def);
    }

    public int getInteger(String path) {
        return config.getInt(path);
    }

    public int getInteger(String path, int def) {
        return config.getInt(path, def);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public boolean getBoolean(String path, boolean def) {
        return config.getBoolean(path, def);
    }

    public void createSection(String path) {
        config.createSection(path);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return config.getConfigurationSection(path);
    }

    public double getDouble(String path) {
        return config.getDouble(path);
    }

    public double getDouble(String path, double def) {
        return config.getDouble(path, def);
    }

    public List<?> getList(String path) {
        return config.getList(path);
    }

    public List<?> getList(String path, List<?> def) {
        return config.getList(path, def);
    }

    public boolean contains(String path) {
        return config.contains(path);
    }

    public void removeKey(String path) {
        config.set(path, null);
    }

    public void set(String path, Object value) {
        config.set(path, value);
        saveConfig();
    }

    public void setDefault(String path, Object value) {
        if (!(config.contains(path))) {
            config.set(path, value);
        }
    }

    public void set(LinkedHashMap<String, Object> defaults) {
        for (Entry<String, Object> section : defaults.entrySet()) {
            config.set(section.getKey(), section.getValue());
        }
        saveConfig();
    }

    public void setDefault(LinkedHashMap<String, Object> defaults) {
        for (Entry<String, Object> section : defaults.entrySet()) {
            if (!(config.contains(section.getKey()))) {
                config.set(section.getKey(), section.getValue());
            }
        }
    }

    public void set(String path, Object value, String comment) {
        if (!config.contains(path)) {
            config.set(configManager.getPluginName() + "_COMMENT_" + comments, " " + comment);
            comments++;
        }
        config.set(path, value);
        saveConfig();
    }

    public void setDefault(String path, Object value, String comment) {
        if (!config.contains(path)) {
            config.set(configManager.getPluginName() + "_COMMENT_" + comments, " " + comment);
            comments++;
        }
        if (!(config.contains(path))) {
            config.set(path, value);
        }
    }

    public void set(String path, Object value, String[] comment) {
        for (String comm : comment) {
            if (!config.contains(path)) {
                config.set(configManager.getPluginName() + "_COMMENT_" + comments, " " + comm);
                comments++;
            }
        }
        config.set(path, value);
        saveConfig();
    }

    public void setDefault(String path, Object value, String[] comment) {
        for (String comm : comment) {
            if (!config.contains(path)) {
                config.set(configManager.getPluginName() + "_COMMENT_" + comments, " " + comm);
                comments++;
            }
        }
        if (!(config.contains(path))) {
            config.set(path, value);
        }
    }

    public void setHeader(String[] header) {
        configManager.setHeader(file, header);
        comments = header.length + 2;
        reloadConfig();
    }

    public void reloadConfig() {
        this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(configManager.getConfigContent(file)));
    }

    public void saveConfig() {
        String config = this.config.saveToString();
        configManager.saveConfig(config, file);

    }

    public Set<String> getKeys() {
        return config.getKeys(false);
    }

}
