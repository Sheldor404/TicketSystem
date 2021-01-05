package de.kyleonaut.ticketsystem.events;

import de.kyleonaut.ticketsystem.util.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryClickHandler implements Listener {


    public static HashMap<Player, Integer> plotVerschiebenHashMap = new HashMap<>();
    public static HashMap<Player, Integer> plotMeldenHashMap = new HashMap<>();
    public static HashMap<Player, Integer> plotMergenHashMap = new HashMap<>();
    public static HashMap<Player, Integer> plotBeantragenHashMap = new HashMap<>();
    public static ArrayList<Player> eigenesTicketArrayList = new ArrayList<>();


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("Kategorien")) {
            event.setCancelled(true);
            int slot = event.getSlot();
            Player player = (Player) event.getWhoClicked();
            switch (slot) {
                //Plot verschieben
                case 0:
                    switchManager(player, plotVerschiebenHashMap, "Messages.PlotVerschieben");
                    break;
                //Plot melden
                case 1:
                    switchManager(player, plotMeldenHashMap, "Messages.PlotMelden");
                    break;
                //Plot mergen
                case 2:
                    switchManager(player, plotMergenHashMap, "Messages.PlotMergen");
                    break;
                //Plot beantragen
                case 3:
                    switchManager(player, plotBeantragenHashMap, "Messages.PlotBeantragen");
                    break;
                //Eigenes Ticket
                case 4:
                    if (!eigenesTicketArrayList.contains(player)) {
                        eigenesTicketArrayList.add(player);
                        Config.sendMessage(player, "Messages.EigenesTicket");
                    } else {
                        Config.sendMessage(player, "Messages.PlayerHasOpenTicket");
                    }
                    player.closeInventory();
                    break;
            }
        }
    }

    private void switchManager(Player player, HashMap hashMap, String messageKey) {
        if (!hashMap.containsKey(player)) {
            hashMap.put(player, 0);
            Config.sendMessage(player, messageKey);
        } else {
            Config.sendMessage(player, "Messages.PlayerHasOpenTicket");
        }
        player.closeInventory();
    }
}
