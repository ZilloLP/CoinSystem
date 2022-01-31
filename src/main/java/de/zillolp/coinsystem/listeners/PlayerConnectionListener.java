package de.zillolp.coinsystem.listeners;

import de.zillolp.coinsystem.CoinSystem;
import de.zillolp.coinsystem.profiles.PlayerProfil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerConnectionListener implements Listener {
    private HashMap<UUID, PlayerProfil> playerProfiles = CoinSystem.playerProfiles;

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (playerProfiles.containsKey(player.getUniqueId())) {
            playerProfiles.replace(player.getUniqueId(), new PlayerProfil(player));
        } else {
            playerProfiles.put(player.getUniqueId(), new PlayerProfil(player));
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (playerProfiles.containsKey(player.getUniqueId())) {
            playerProfiles.remove(player.getUniqueId());
        }
    }
}
