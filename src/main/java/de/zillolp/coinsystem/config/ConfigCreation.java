package de.zillolp.coinsystem.config;

import de.zillolp.coinsystem.utils.ConfigManager;
import de.zillolp.coinsystem.utils.ConfigUtil;

import java.util.LinkedHashMap;

public class ConfigCreation {
    public static ConfigManager configManager;
    private static String[] header = {"CoinSystem"};
    private static LinkedHashMap<String, Object> defaults = new LinkedHashMap<>();

    public static void createConfigs() {
        configManager = new ConfigManager();
        createCoinsFile();
        createLanguageFile();
        createPermissionsFile();
    }

    private static void createCoinsFile() {
        ConfigUtil configUtil = configManager.getNewConfig("coins.yml", header);
        configUtil.saveConfig();
    }

    private static void createLanguageFile() {
        ConfigUtil configUtil = configManager.getNewConfig("language.yml", header);
        configUtil.setDefault("PREFIX", "&7[&aCoinSystem&7] ", "Basic messages");
        defaults.put("NO_PERMISSION", "&cDazu hast du keine Rechte!");
        defaults.put("ONLY_PLAYER", "&cDu musst ein Spieler sein!");
        configUtil.setDefault(defaults);
        defaults.clear();
        configUtil.setDefault("OWN_COINS", "&7Du besitzt aktuell &a%coins% Coins&7.", "Command messages");
        defaults.put("UNKNOWN_PLAYER", "&cDieser Spieler konnte nicht gefunden werden.");
        defaults.put("NO_NUMBER", "&cBitte gebe eine richtige Zahl an.");
        defaults.put("SET_COINS_PLAYER", "&7Du hast die Coins von &2%target% &7auf &a%coins% Coins &7gesetzt.");
        defaults.put("SET_COINS_TARGET", "&7Deine Coins wurden von &2%player% &7auf &a%coins% Coins &7gesetzt.");
        defaults.put("SET_COINS_OWN", "&7Du hast deine Coins auf &a%coins% Coins &7gesetzt.");
        defaults.put("ADD_COINS_PLAYER", "&7Du hast &2%target% &a%coins% Coins &7gutgeschrieben.");
        defaults.put("ADD_COINS_TARGET", "&7Du hast von &2%player% &a%coins% Coins &7gutgeschrieben bekommen.");
        defaults.put("ADD_COINS_OWN", "&7Du hast dir &a%coins% Coins &7gutgeschrieben.");
        defaults.put("REMOVE_COINS_PLAYER", "&7Du hast &2%target% &a%coins% Coins &7abgezogen.");
        defaults.put("REMOVE_COINS_TARGET", "&7Du hast von &2%player% &a%coins% Coins &7abgezogen bekommen.");
        defaults.put("REMOVE_COINS_OWN", "&7Du hast dir &a%coins% Coins &7abgezogen.");
        configUtil.setDefault(defaults);
        defaults.clear();
        configUtil.saveConfig();
    }

    private static void createPermissionsFile() {
        ConfigUtil configUtil = configManager.getNewConfig("permissions.yml", header);
        configUtil.setDefault("ADMIN_PERMISSION", "coinssystem.admin", "Permissions");
        configUtil.saveConfig();
    }
}
