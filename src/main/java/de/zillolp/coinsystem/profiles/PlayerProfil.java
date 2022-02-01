package de.zillolp.coinsystem.profiles;

import de.zillolp.coinsystem.CoinSystem;
import de.zillolp.coinsystem.utils.ConfigUtil;
import org.bukkit.entity.Player;

public class PlayerProfil {
    private Player player;
    private int coins;

    public PlayerProfil(Player player) {
        ConfigUtil configUtil = new ConfigUtil("coins.yml");
        this.player = player;
        this.coins = configUtil.getInt("Players." + player.getUniqueId() + ".Coins");
        createProfile();
    }

    private void createProfile() {
        ConfigUtil configUtil = new ConfigUtil("coins.yml");
        configUtil.set("Players." + player.getUniqueId() + ".Name", player.getName());
        if (!(configUtil.contains("Players." + player.getUniqueId() + ".Coins"))) {
            configUtil.set("Players." + player.getUniqueId() + ".Coins", 0);
        }
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int coins) {
        setCoins(this.coins + coins);
    }

    public void removeCoins(int coins) {
        setCoins(this.coins - coins);
    }

    public void saveProfil() {
        ConfigUtil configUtil = new ConfigUtil("coins.yml");
        configUtil.set("Players." + player.getUniqueId() + ".Coins", coins);
        CoinSystem.playerProfiles.remove(player.getUniqueId());
    }
}
