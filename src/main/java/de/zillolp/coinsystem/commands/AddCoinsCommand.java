package de.zillolp.coinsystem.commands;

import de.zillolp.coinsystem.CoinSystem;
import de.zillolp.coinsystem.config.LanguageTools;
import de.zillolp.coinsystem.config.PermissionTools;
import de.zillolp.coinsystem.events.PlayerAddCoinsEvent;
import de.zillolp.coinsystem.profiles.PlayerProfil;
import de.zillolp.coinsystem.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;

public class AddCoinsCommand implements TabExecutor {
    private HashMap<UUID, PlayerProfil> playerProfiles = CoinSystem.playerProfiles;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(LanguageTools.getONLY_PLAYER());
            return false;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission(PermissionTools.getADMIN_PERMISSION()))) {
            player.sendMessage(LanguageTools.getNO_PERMISSION());
            return false;
        }
        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(LanguageTools.getUNKNOWN_PLAYER());
                return false;
            }
            if (!(StringUtil.isNumber(args[1]))) {
                player.sendMessage(LanguageTools.getNO_NUMBER());
                return false;
            }
            int coins = Integer.valueOf(args[1]);
            PlayerProfil playerProfil = playerProfiles.get(target.getUniqueId());
            playerProfil.addCoins(coins);
            if (player != target) {
                player.sendMessage(LanguageTools.getADD_COINS_PLAYER(target, coins));
                target.sendMessage(LanguageTools.getADD_COINS_TARGET(player, coins));
            } else {
                player.sendMessage(LanguageTools.getADD_COINS_OWN(coins));
            }
            Bukkit.getServer().getPluginManager().callEvent(new PlayerAddCoinsEvent(player, target, coins));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            LinkedList<String> playerNames = new LinkedList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        }
        return null;
    }
}
