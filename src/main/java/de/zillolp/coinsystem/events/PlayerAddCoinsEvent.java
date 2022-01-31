package de.zillolp.coinsystem.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAddCoinsEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Player target;
    private int coins;

    public PlayerAddCoinsEvent(Player player, Player target, int coins) {
        this.player = player;
        this.target = target;
        this.coins = coins;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getTarget() {
        return target;
    }

    public int getCoins() {
        return coins;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
