package de.zillolp.coinsystem;

import de.zillolp.coinsystem.commands.AddCoinsCommand;
import de.zillolp.coinsystem.commands.CoinsCommand;
import de.zillolp.coinsystem.commands.RemoveCoinsCommand;
import de.zillolp.coinsystem.commands.SetCoinsCommand;
import de.zillolp.coinsystem.config.ConfigCreation;
import de.zillolp.coinsystem.config.tools.LanguageTools;
import de.zillolp.coinsystem.config.tools.PermissionTools;
import de.zillolp.coinsystem.listeners.PlayerConnectionListener;
import de.zillolp.coinsystem.profiles.PlayerProfil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class CoinSystem extends JavaPlugin {
    public static CoinSystem coinSystem;
    public static HashMap<UUID, PlayerProfil> playerProfiles;

    @Override
    public void onEnable() {
        coinSystem = this;
        init(Bukkit.getPluginManager());
        Bukkit.getConsoleSender().sendMessage(LanguageTools.getPREFIX() + "§aDas Plugin wurde gestartet.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(LanguageTools.getPREFIX() + "§cDas Plugin wurde gestoppt.");
    }

    private void init(PluginManager pluginManager) {
        ConfigCreation.createConfigs();
        LanguageTools.load();
        PermissionTools.load();
        playerProfiles = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerProfiles.put(player.getUniqueId(), new PlayerProfil(player));
        }
        pluginManager.registerEvents(new PlayerConnectionListener(), this);
        getCommand("coins").setExecutor(new CoinsCommand());
        getCommand("setcoins").setExecutor(new SetCoinsCommand());
        getCommand("addcoins").setExecutor(new AddCoinsCommand());
        getCommand("removecoins").setExecutor(new RemoveCoinsCommand());
    }
}
