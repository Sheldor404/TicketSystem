package de.kyleonaut.ticketsystem.events;

import de.kyleonaut.ticketsystem.util.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class InventoryClickHandler implements Listener {


    public static ArrayList<Player> plotVerschiebenArrayList = new ArrayList<>();
    public static ArrayList<Player> plotMeldenArrayList = new ArrayList<>();
    public static ArrayList<Player> plotMergenArrayList = new ArrayList<>();
    public static ArrayList<Player> plotBeantragenArrayList = new ArrayList<>();
    public static ArrayList<Player> eigenesTicketArrayList = new ArrayList<>();

    public static ArrayList<Player> offlineAbgelehnt = new ArrayList<>();
    public static ArrayList<Player> offlineAngenommen = new ArrayList<>();

    public static HashMap<Player, Integer> waitingForNotiz = new HashMap<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) throws SQLException {
        if (event.getInventory().getTitle().equals("Kategorien")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            Player player = (Player) event.getWhoClicked();
            switch (slot) {
                //Plot verschieben
                case 0:
                    switchManager(player, plotVerschiebenArrayList, "Messages.PlotVerschieben");
                    break;
                //Plot melden
                case 1:
                    switchManager(player, plotMeldenArrayList, "Messages.PlotMelden");
                    break;
                //Plot mergen
                case 2:
                    switchManager(player, plotMergenArrayList, "Messages.PlotMergen");
                    break;
                //Plot beantragen
                case 3:
                    switchManager(player, plotBeantragenArrayList, "Messages.PlotBeantragen");
                    break;
                //Eigenes Ticket
                case 4:
                    switchManager(player, eigenesTicketArrayList, "Messages.EigenesTicket");
                    break;
            }
        } else if (event.getInventory().getTitle().equalsIgnoreCase("Ticket Moderation")) {
            event.setCancelled(true);
            if (event.getSlot() == 53) {
                InventoryHolder.openTicketHistory((Player) event.getWhoClicked());
            } else {
                try {
                    String target = TicketSqlAPI.getPlayerNameById(Integer.parseInt(event.getCurrentItem().getItemMeta().getLore().get(4).replace("§7➥§e Ticket Nummer: §7", "")));
                    InventoryHolder.openSecondModerationGui((Player) event.getWhoClicked(), target, event);
                } catch (NullPointerException exception) {

                }
            }

        } else if (event.getInventory().getTitle().equalsIgnoreCase("Ticket Verwaltung")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            Player player = (Player) event.getWhoClicked();
            switch (slot) {
                case 1:
                    TicketSqlAPI.changeStatus(Status.ERLEDIGT_ABGELEHNT, Integer.parseInt((event.getInventory().getItem(0).getItemMeta().getLore().get(0).replace("§7➥ §eTicket Nummer: ", ""))), (Player) event.getWhoClicked());
                    InventoryHolder.openMainModerationGui(player);
                    notifyPlayer(player, TicketSqlAPI.getPlayerNameById(Integer.parseInt(event.getInventory().getItem(0).getItemMeta().getLore().get(0).replace("§7➥ §eTicket Nummer: ", ""))), Status.ERLEDIGT_ABGELEHNT);
                    break;
                case 2:
                    TicketSqlAPI.changeStatus(Status.ERLEDIGT_ANGENOMMEN, Integer.parseInt((event.getInventory().getItem(0).getItemMeta().getLore().get(0).replace("§7➥ §eTicket Nummer: ", ""))), (Player) event.getWhoClicked());
                    InventoryHolder.openMainModerationGui(player);
                    notifyPlayer(player, TicketSqlAPI.getPlayerNameById(Integer.parseInt(event.getInventory().getItem(0).getItemMeta().getLore().get(0).replace("§7➥ §eTicket Nummer: ", ""))), Status.ERLEDIGT_ANGENOMMEN);
                    break;
                case 3:
                    InventoryHolder.openFilteredModerationGui(player, TicketSqlAPI.getPlayerNameById(Integer.parseInt(event.getInventory().getItem(0).getItemMeta().getLore().get(0).replace("§7➥ §eTicket Nummer: ", ""))));
                    break;
                case 4:
                    InventoryHolder.openAllNotesByTicketId(Integer.parseInt(event.getInventory().getItem(0).getItemMeta().getLore().get(0).replace("§7➥ §eTicket Nummer: ", "")), player);
                    break;
                case 8:
                    InventoryHolder.openMainModerationGui(player);
                    break;
            }
        } else if (event.getInventory().getTitle().equalsIgnoreCase("Ticket History")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 53) {
                InventoryHolder.openMainModerationGui((Player) event.getWhoClicked());
            } else {
                try {
                    InventoryHolder.openAllNotesByTicketIdByHistory(Integer.parseInt(event.getCurrentItem().getItemMeta().getLore().get(4).replace("§7➥§e Ticket Nummer: §7", "")), player);
                } catch (NullPointerException e) {

                }
            }

        } else if (event.getInventory().getTitle().equalsIgnoreCase("Ticket Overview")) {
            event.setCancelled(true);
        } else if (event.getInventory().getTitle().equalsIgnoreCase("Notizen")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 8) {
                waitingForNotiz.put(player, Integer.valueOf(event.getInventory().getItem(0).getItemMeta().getLore().get(0).replace("§7➥ Ticket Nummer: ", "")));
                player.closeInventory();
                Config.sendMessage(player, "Messages.Notiz");
            } else if (event.getSlot() == 53) {
                InventoryHolder.openMainModerationGui(player);
            } else {
                return;
            }
        } else if (event.getInventory().getTitle().equalsIgnoreCase("Notizen [Geschlossen]")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getSlot() == 53) {
                InventoryHolder.openTicketHistory(player);
            } else {
                return;
            }
        } else if (event.getInventory().getTitle().equalsIgnoreCase("Ticket Moderation [Gefiltert]")) {
            if (event.getSlot() == 53) {
                InventoryHolder.openMainModerationGui((Player) event.getWhoClicked());
            } else {
                return;
            }
        }
    }

    private void switchManager(Player player, ArrayList arrayList, String messageKey) {
        if (!arrayList.contains(player)) {
            arrayList.add(player);
            Config.sendMessage(player, messageKey);
        } else {
            Config.sendMessage(player, "Messages.PlayerHasOpenTicket");
        }
        player.closeInventory();
    }

    private void notifyPlayer(Player sender, String playerName, Status status) {
        if (status == Status.ERLEDIGT_ABGELEHNT) {
            String value = Config.getCfg().getString("Messages.NotifyPlayer").replace("{ticket_status}", "§cabgelehnt");
            sender.performCommand("mail send " + playerName + " " + value);

        } else if (status == Status.ERLEDIGT_ANGENOMMEN) {
            String value = Config.getCfg().getString("Messages.NotifyPlayer").replace("{ticket_status}", "§aangenommen");
            sender.performCommand("mail send " + playerName + " " + value);


        }
    }
}
