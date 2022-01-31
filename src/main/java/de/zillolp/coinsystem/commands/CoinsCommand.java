package de.zillolp.coinsystem.commands;

import de.zillolp.coinsystem.CoinSystem;
import de.zillolp.coinsystem.config.tools.LanguageTools;
import de.zillolp.coinsystem.profiles.PlayerProfil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CoinsCommand implements CommandExecutor {
    private HashMap<UUID, PlayerProfil> playerProfiles = CoinSystem.playerProfiles;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                PlayerProfil playerProfil = playerProfiles.get(player.getUniqueId());
                player.sendMessage(LanguageTools.getOWN_COINS(playerProfil.getCoins()));
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(LanguageTools.getONLY_PLAYER());
        }
        return false;
    }
}
