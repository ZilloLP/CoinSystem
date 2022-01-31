package de.zillolp.coinsystem.commands;

import de.zillolp.coinsystem.CoinSystem;
import de.zillolp.coinsystem.config.tools.LanguageTools;
import de.zillolp.coinsystem.config.tools.PermissionTools;
import de.zillolp.coinsystem.profiles.PlayerProfil;
import de.zillolp.coinsystem.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SetCoinsCommand implements TabExecutor {
    private HashMap<UUID, PlayerProfil> playerProfiles = CoinSystem.playerProfiles;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(PermissionTools.getADMIN_PERMISSION())) {
                if (args.length == 2) {
                    String name = args[0];
                    Player target = Bukkit.getPlayer(name);
                    if (Bukkit.getOnlinePlayers().contains(target)) {
                        if (StringUtil.isNumber(args[1])) {
                            int coins = Integer.valueOf(args[1]);
                            PlayerProfil playerProfil = playerProfiles.get(target.getUniqueId());
                            playerProfil.setCoins(coins);
                            if (player != target) {
                                player.sendMessage(LanguageTools.getSET_COINS_PLAYER(target, coins));
                                target.sendMessage(LanguageTools.getSET_COINS_TARGET(player, coins));
                            } else {
                                player.sendMessage(LanguageTools.getSET_COINS_OWN(coins));
                            }
                        } else {
                            player.sendMessage(LanguageTools.getNO_NUMBER());
                        }
                    } else {
                        player.sendMessage(LanguageTools.getUNKNOWN_PLAYER());
                    }
                }
            } else {
                player.sendMessage(LanguageTools.getNO_PERMISSION());
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(LanguageTools.getONLY_PLAYER());
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (int i = 0; i < players.length; i++) {
                playerNames.add(players[i].getName());
            }
            return playerNames;
        }
        return null;
    }
}
