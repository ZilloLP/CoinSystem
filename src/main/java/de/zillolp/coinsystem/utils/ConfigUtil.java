package de.zillolp.coinsystem.utils;

import de.zillolp.coinsystem.CoinSystem;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {
    private File file;
    private YamlConfiguration yamlConfiguration;

    public ConfigUtil(String path) {
        this.file = new File(CoinSystem.getCoinSystem().getDataFolder() + "/" + path);
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public boolean exist() {
        return file.exists();
    }

    public void set(String path, Object value) {
        yamlConfiguration.set(path, value);
        save();
    }

    public String getString(String path) {
        return yamlConfiguration.getString(path);
    }

    public int getInt(String path) {
        return yamlConfiguration.getInt(path);
    }

    public boolean contains(String path) {
        return yamlConfiguration.contains(path);
    }

    private void save() {
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
