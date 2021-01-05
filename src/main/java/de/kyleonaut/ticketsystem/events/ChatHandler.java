package de.kyleonaut.ticketsystem.events;

import de.kyleonaut.ticketsystem.TicketSystem;
import de.kyleonaut.ticketsystem.util.Config;
import de.kyleonaut.ticketsystem.util.TicketSqlAPI;
import de.kyleonaut.ticketsystem.util.TicketTypes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ChatHandler implements Listener {

    public static ArrayList<String> hasCooldown = new ArrayList<>();

    @EventHandler
    public void onChatMessage(PlayerChatEvent event) throws SQLException {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (InventoryClickHandler.plotVerschiebenArrayList.contains(player)) {
            chatManager(player, event, message, TicketTypes.PLOT_VERSCHIEBEN);
        } else if (InventoryClickHandler.plotMeldenArrayList.contains(player)) {
            chatManager(player, event, message, TicketTypes.PLOT_MELDEN);
        } else if (InventoryClickHandler.plotMergenArrayList.contains(player)) {
            chatManager(player, event, message, TicketTypes.PLOT_MERGEN);
        } else if (InventoryClickHandler.plotBeantragenArrayList.contains(player)) {
            chatManager(player, event, message, TicketTypes.PLOT_BEANTRAGEN);
        } else if (InventoryClickHandler.eigenesTicketArrayList.contains(player)) {
            chatManager(player, event, message, TicketTypes.EIGENES_TICKET);
        }
    }

    private void chatManager(Player player, PlayerChatEvent event, String message, TicketTypes type) throws SQLException {
        event.setCancelled(true);
        if (message.length() > 200) {
            Config.sendMessage(player, "Messages.MessageToLong");
        } else {
            TicketSqlAPI.createTicket(player, type, message, new Date());
            InventoryClickHandler.plotVerschiebenArrayList.remove(player);
            InventoryClickHandler.eigenesTicketArrayList.remove(player);
            InventoryClickHandler.plotBeantragenArrayList.remove(player);
            InventoryClickHandler.plotMeldenArrayList.remove(player);
            InventoryClickHandler.plotMergenArrayList.remove(player);
            Config.sendMessage(player, "Messages.TicketEingegangen");
            hasCooldown.add(player.getName());
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (p.hasPermission("ticketsystem.notify")) {
                    Config.sendMessage(p, "Messages.NotifyModerators");
                }
            }
            if (player.getName().equalsIgnoreCase("kyleonaut") || player.getName().equalsIgnoreCase("sheldor003")) {
                hasCooldown.remove(player.getName());
                Config.sendMessage(player, "Messages.PlayerOutOfTicketCooldown");
            } else {
                Bukkit.getScheduler().scheduleSyncDelayedTask(TicketSystem.getPlugin(), new BukkitRunnable() {
                    @Override
                    public void run() {
                        try {
                            Config.sendMessage(player, "Messages.PlayerOutOfTicketCooldown");
                            hasCooldown.remove(player.getName());
                        } catch (Exception exception) {
                            /*
                             * Sollte der Spieler Offline sein, bekommt er beim rejoinen einen Nachricht, dass er ein Ticket erstellen kann.
                             * */
                        }
                    }
                }, 20 * 60 * 15);
            }
        }
    }
}
