package de.zillolp.coinsystem.profiles;

import de.zillolp.coinsystem.config.ConfigCreation;
import de.zillolp.coinsystem.utils.ConfigUtil;
import org.bukkit.entity.Player;

public class PlayerProfil {
    private ConfigUtil configUtil = ConfigCreation.configManager.getNewConfig("coins.yml");
    private Player player;
    private String uuid;
    private String name;
    private String section;

    public PlayerProfil(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId().toString();
        this.name = player.getName();
        this.section = "Players." + uuid;
        createProfile();
    }

    private void createProfile() {
        configUtil.set(section + ".Name", name);
        if (!(configUtil.contains(section + ".Coins"))) {
            configUtil.set(section + ".Coins", 0);
        }
    }

    public Integer getCoins() {
        return configUtil.getInteger(section + ".Coins");
    }

    public void setCoins(int coins) {
        configUtil.set(section + ".Coins", coins);
    }

    public void addCoins(int coins) {
        setCoins(getCoins() + coins);
    }

    public void removeCoins(int coins) {
        setCoins(getCoins() - coins);
    }
}
