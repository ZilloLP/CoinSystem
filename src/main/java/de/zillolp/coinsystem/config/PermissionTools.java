package de.zillolp.coinsystem.config;

import de.zillolp.coinsystem.utils.ConfigUtil;

public class PermissionTools {
    private static String ADMIN_PERMISSION;

    public static void load() {
        ConfigUtil configUtil = new ConfigUtil("permissions.yml");
        ADMIN_PERMISSION = configUtil.getString("ADMIN_PERMISSION");
    }

    public static String getADMIN_PERMISSION() {
        return ADMIN_PERMISSION;
    }
}
