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
        if (ChatHandler.hasCooldown.contains(player.getName())) {
            ChatHandler.hasCooldown.remove(player.getName());
            Config.sendMessage(player, "Messages.PlayerOutOfTicketCooldown");
        }
    }
}
