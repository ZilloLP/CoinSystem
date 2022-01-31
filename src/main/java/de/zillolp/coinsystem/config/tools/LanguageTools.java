package de.zillolp.coinsystem.config.tools;

import de.zillolp.coinsystem.config.ConfigCreation;
import de.zillolp.coinsystem.utils.ConfigUtil;
import de.zillolp.coinsystem.utils.StringUtil;
import org.bukkit.entity.Player;

public class LanguageTools extends StringUtil {
    private static ConfigUtil configUtil;
    private static String PREFIX;
    private static String NO_PERMISSION;
    private static String ONLY_PLAYER;
    private static String OWN_COINS;
    private static String UNKNOWN_PLAYER;
    private static String NO_NUMBER;
    private static String SET_COINS_PLAYER;
    private static String SET_COINS_TARGET;
    private static String SET_COINS_OWN;
    private static String ADD_COINS_PLAYER;
    private static String ADD_COINS_TARGET;
    private static String ADD_COINS_OWN;
    private static String REMOVE_COINS_PLAYER;
    private static String REMOVE_COINS_TARGET;
    private static String REMOVE_COINS_OWN;

    public static void load() {
        configUtil = ConfigCreation.configManager.getNewConfig("language.yml");
        PREFIX = replaceDefaults(configUtil.getString("PREFIX"));
        NO_PERMISSION = replaceDefaults(configUtil.getString("NO_PERMISSION"));
        ONLY_PLAYER = replaceDefaults(configUtil.getString("ONLY_PLAYER"));
        OWN_COINS = replaceDefaults(configUtil.getString("OWN_COINS"));
        UNKNOWN_PLAYER = replaceDefaults(configUtil.getString("UNKNOWN_PLAYER"));
        NO_NUMBER = replaceDefaults(configUtil.getString("NO_NUMBER"));
        SET_COINS_PLAYER = replaceDefaults(configUtil.getString("SET_COINS_PLAYER"));
        SET_COINS_TARGET = replaceDefaults(configUtil.getString("SET_COINS_TARGET"));
        SET_COINS_OWN = replaceDefaults(configUtil.getString("SET_COINS_OWN"));
        ADD_COINS_PLAYER = replaceDefaults(configUtil.getString("ADD_COINS_PLAYER"));
        ADD_COINS_TARGET = replaceDefaults(configUtil.getString("ADD_COINS_TARGET"));
        ADD_COINS_OWN = replaceDefaults(configUtil.getString("ADD_COINS_OWN"));
        REMOVE_COINS_PLAYER = replaceDefaults(configUtil.getString("REMOVE_COINS_PLAYER"));
        REMOVE_COINS_TARGET = replaceDefaults(configUtil.getString("REMOVE_COINS_TARGET"));
        REMOVE_COINS_OWN = replaceDefaults(configUtil.getString("REMOVE_COINS_OWN"));
    }

    public static String getPREFIX() {
        return PREFIX;
    }

    public static String getNO_PERMISSION() {
        return PREFIX + NO_PERMISSION;
    }

    public static String getONLY_PLAYER() {
        return PREFIX + ONLY_PLAYER;
    }

    public static String getOWN_COINS(int coins) {
        return PREFIX + replaceCoins(coins, OWN_COINS);
    }

    public static String getUNKNOWN_PLAYER() {
        return PREFIX + UNKNOWN_PLAYER;
    }

    public static String getNO_NUMBER() {
        return PREFIX + NO_NUMBER;
    }

    public static String getSET_COINS_PLAYER(Player target, int coins) {
        return PREFIX + replaceTarget(target, replaceCoins(coins, SET_COINS_PLAYER));
    }

    public static String getSET_COINS_TARGET(Player player, int coins) {
        return PREFIX + replacePlayer(player, replaceCoins(coins, SET_COINS_TARGET));
    }

    public static String getSET_COINS_OWN(int coins) {
        return PREFIX + replaceCoins(coins, SET_COINS_OWN);
    }

    public static String getADD_COINS_PLAYER(Player target, int coins) {
        return PREFIX + replaceTarget(target, replaceCoins(coins, ADD_COINS_PLAYER));
    }

    public static String getADD_COINS_TARGET(Player player, int coins) {
        return PREFIX + replacePlayer(player, replaceCoins(coins, ADD_COINS_TARGET));
    }

    public static String getADD_COINS_OWN(int coins) {
        return PREFIX + replaceCoins(coins, ADD_COINS_OWN);
    }

    public static String getREMOVE_COINS_PLAYER(Player target, int coins) {
        return PREFIX + replaceTarget(target, replaceCoins(coins, REMOVE_COINS_PLAYER));
    }

    public static String getREMOVE_COINS_TARGET(Player player, int coins) {
        return PREFIX + replacePlayer(player, replaceCoins(coins, REMOVE_COINS_TARGET));
    }

    public static String getREMOVE_COINS_OWN(int coins) {
        return PREFIX + replaceCoins(coins, REMOVE_COINS_OWN);
    }
}
