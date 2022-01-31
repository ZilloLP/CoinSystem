package de.zillolp.coinsystem.config.tools;

import de.zillolp.coinsystem.config.ConfigCreation;
import de.zillolp.coinsystem.utils.ConfigUtil;

public class PermissionTools {
    private static ConfigUtil configUtil;
    private static String ADMIN_PERMISSION;

    public static void load() {
        configUtil = ConfigCreation.configManager.getNewConfig("permissions.yml");
        ADMIN_PERMISSION = configUtil.getString("ADMIN_PERMISSION");
    }

    public static String getADMIN_PERMISSION() {
        return ADMIN_PERMISSION;
    }
}
