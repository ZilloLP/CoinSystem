package de.zillolp.coinsystem.utils;

import org.bukkit.entity.Player;

public class StringUtil {

    public static boolean isNumber(String value) {
        boolean ret = false;
        if(value.matches("[0-9]+")){
            ret = true;
        }
        return ret;
    }

    public static String replaceDefaults(String message) {
        message = message.replace("%Ae%", "Ä");
        message = message.replace("%ae%", "ä");
        message = message.replace("%Oe%", "Ö");
        message = message.replace("%oe%", "ö");
        message = message.replace("%Ue%", "Ü");
        message = message.replace("%ue%", "ü");
        message = message.replace("%sz%", "ß");
        message = message.replace("%>%", "»");
        message = message.replace("%<%", "«");
        message = message.replace("%*%", "×");
        message = message.replace("%|%", "┃");
        message = message.replace("%->%", "➜");
        message = message.replace("&", "§");
        return message;
    }

    public static String replacePlayer(Player player, String message) {
        return message.replace("%player%", player.getName());
    }

    public static String replaceTarget(Player target, String message) {
        return message.replace("%target%", target.getName());
    }

    public static String replaceCoins(int coins, String message) {
        return message.replace("%coins%", String.valueOf(coins));
    }
}
