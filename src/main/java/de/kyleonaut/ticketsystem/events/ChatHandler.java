package de.kyleonaut.ticketsystem.events;

import de.kyleonaut.ticketsystem.TicketSystem;
import de.kyleonaut.ticketsystem.util.Config;
import de.kyleonaut.ticketsystem.util.NotizAPI;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
        if (InventoryClickHandler.waitingForNotiz.containsKey(player)) {
            event.setCancelled(true);
            event.setMessage("");
            NotizAPI.createNewNotiz(player, message, InventoryClickHandler.waitingForNotiz.get(player));
            InventoryClickHandler.waitingForNotiz.remove(player);
            Config.sendMessage(player, "Messages.NotizAdded");
            player.performCommand("ticketmod");
        }
    }

    private void chatManager(Player player, PlayerChatEvent event, String message, TicketTypes type) throws SQLException {
        event.setCancelled(true);
        event.setMessage("");
        if (message.length() > 200) {
            Config.sendMessage(player, "Messages.MessageToLong");
        } else {

            if (hasCooldown.contains(player.getName())) {
                String msg = Config.getCfg().getString("Messages.PlayerInTicketCooldown");
                msg = msg.replace("%TicketCooldownInMinutes%", "§c" + String.valueOf(Config.getCfg().getInt("Settings.TicketCooldownInMinutes")));
                player.sendMessage(Config.getCfg().getString("Settings.Prefix") + msg);
            } else {

                LocalDateTime ldt = LocalDateTime.now();
                String date = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.GERMANY).format(ldt);
                TicketSqlAPI.createTicket(player, type, message, date);
                InventoryClickHandler.plotVerschiebenArrayList.remove(player);
                InventoryClickHandler.eigenesTicketArrayList.remove(player);
                InventoryClickHandler.plotBeantragenArrayList.remove(player);
                InventoryClickHandler.plotMeldenArrayList.remove(player);
                InventoryClickHandler.plotMergenArrayList.remove(player);
                hasCooldown.add(player.getName());
                Config.sendMessage(player, "Messages.TicketEingegangen");

                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (p.hasPermission("ticketsystem.notify")) {
                        Config.sendMessage(p, "Messages.NotifyModerators");
                    }
                }

                if (Config.getCfg().getBoolean("Settings.DebugMode")) {
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
                        }, 20 * 60 * Config.getCfg().getInt("Settings.TicketCooldownInMinutes"));
                    }
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
                    }, 20 * 60 * Config.getCfg().getInt("Settings.TicketCooldownInMinutes"));
                }
            }
        }
    }
}
