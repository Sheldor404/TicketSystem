package de.kyleonaut.ticketsystem.events;

import de.kyleonaut.ticketsystem.util.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HandlePlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (InventoryClickHandler.offlineAngenommen.contains(player)) {
            InventoryClickHandler.offlineAngenommen.remove(player);
            String msg = Config.getCfg().getString("Messages.NotifyPlayer").replace("{ticket_status}", "§aangenommen");
            player.sendMessage(Config.getCfg().getString("Settings.Prefix") + msg);
        }
        if (InventoryClickHandler.offlineAbgelehnt.contains(player)) {
            InventoryClickHandler.offlineAbgelehnt.remove(player);
            String msg = Config.getCfg().getString("Messages.NotifyPlayer").replace("{ticket_status}", "§cabgelehnt");
            player.sendMessage(Config.getCfg().getString("Settings.Prefix") + msg);
        }

    }
}
