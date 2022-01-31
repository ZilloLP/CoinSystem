package de.zillolp.coinsystem.utils;

import org.bukkit.entity.Player;

public class StringUtil {

    public static boolean isNumber(String value) {
        try {
            int number = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String replaceDefaults(String message) {
        if (message.contains("%Ae%")) {
            message = message.replace("%Ae%", "Ä");
        }
        if (message.contains("%ae%")) {
            message = message.replace("%ae%", "ä");
        }
        if (message.contains("%Oe%")) {
            message = message.replace("%Oe%", "Ö");
        }
        if (message.contains("%oe%")) {
            message = message.replace("%oe%", "ö");
        }
        if (message.contains("%Ue%")) {
            message = message.replace("%Ue%", "Ü");
        }
        if (message.contains("%ue%")) {
            message = message.replace("%ue%", "ü");
        }
        if (message.contains("%sz%")) {
            message = message.replace("%sz%", "ß");
        }
        if (message.contains("%>%")) {
            message = message.replace("%>%", "»");
        }
        if (message.contains("%<%")) {
            message = message.replace("%<%", "«");
        }
        if (message.contains("%*%")) {
            message = message.replace("%*%", "×");
        }
        if (message.contains("%|%")) {
            message = message.replace("%|%", "┃");
        }
        if (message.contains("%->%")) {
            message = message.replace("%->%", "➜");
        }
        if (message.contains("&")) {
            message = message.replace("&", "§");
        }
        return message;
    }

    public static String replacePlayer(Player player, String message) {
        if (message.contains("%player%")) {
            message = message.replace("%player%", player.getName());
        }
        return message;
    }

    public static String replaceTarget(Player target, String message) {
        if (message.contains("%target%")) {
            message = message.replace("%target%", target.getName());
        }
        return message;
    }

    public static String replaceCoins(int coins, String message) {
        if (message.contains("%coins%")) {
            message = message.replace("%coins%", String.valueOf(coins));
        }
        return message;
    }
}
