package de.kyleonaut.ticketsystem.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import java.sql.SQLException;
import java.util.ArrayList;


public class InventoryHolder {


    public static void openKategorienGui(Player p) {
        Builder builder = new Builder();
        Inventory ticketinv = Bukkit.createInventory(null, InventoryType.HOPPER, "Kategorien");
        ticketinv.addItem(builder.createGlassPane(Builder.Color.RED, "§ePlot verschieben", 1, "§7➥ Beantrage eine Plotverschiebung"));
        ticketinv.addItem(builder.createGlassPane(Builder.Color.WHITE, "§ePlot melden", 1, "§7➥ Melde ein Plot"));
        ticketinv.addItem(builder.createGlassPane(Builder.Color.YELLOW, "§ePlot mergen", 1, "§7➥ Beantrage ein Plotmerge"));
        ticketinv.addItem(builder.createGlassPane(Builder.Color.BLUE, "§ePlot beantragen", 1, "§7➥ Beantrage ein Plot"));
        ticketinv.addItem(builder.createGlassPane(Builder.Color.LIME_GREEN, "§eEigenes Ticket", 1, "§7➥ Erstelle dein eigenes Ticket"));
        p.openInventory(ticketinv);
    }

    public static void openMainModerationGui(Player p) throws SQLException {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "Ticket Moderation");
        Builder builder = new Builder();
        for (Object id : TicketSqlAPI.getAllOpenTicketIds()) {
            try {
                String ticket_type = TicketSqlAPI.getTicketTypeById((Integer) id);
                String ticket_args = TicketSqlAPI.getArgsById((Integer) id);
                String eingangs_Datum = TicketSqlAPI.getEingangDatumById((Integer) id);
                String ticket_status = TicketSqlAPI.getTicketStatusById((Integer) id);
                ticket_type = ticketConverter(ticket_type);
                ticket_status = "§eOffen";
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7➥§e Ticket Type: §7" + ticket_type);
                lore.add("§7➥§e Ticket Message: §7" + ticket_args);
                lore.add("§7➥§e Eingangsdatum: §7" + eingangs_Datum);
                lore.add("§7➥§e Ticket Status: §7" + ticket_status);
                lore.add("§7➥§e Ticket Nummer: §7" + id);
                inventory.addItem(builder.createHead(TicketSqlAPI.getPlayerNameById((Integer) id), "§e" + TicketSqlAPI.getPlayerNameById((Integer) id), lore));
            } catch (Exception e) {

            }
        }
        inventory.setItem(53, builder.createItem(Material.BOOK, "§eZum Archiv"));
        p.openInventory(inventory);
    }

    public static void openSecondModerationGui(Player p, String targetPlayerName, InventoryClickEvent event) throws SQLException {
        Builder builder = new Builder();
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "Ticket Verwaltung");
        ArrayList<String> lore = new ArrayList<>();
        int id = Integer.parseInt(event.getCurrentItem().getItemMeta().getLore().get(4).replace("§7➥§e Ticket Nummer: §7", ""));
        lore.add("§7➥ §eTicket Nummer: " + event.getCurrentItem().getItemMeta().getLore().get(4).replace("§7➥§e Ticket Nummer: §7", ""));
        inventory.addItem(builder.createHead(targetPlayerName, "§e" + TicketSqlAPI.getPlayerNameById(id), lore));
        inventory.addItem(builder.createGlassPane(Builder.Color.RED, "§aErledigt & §cAbgelehnt", 1, "§7➥ Klicke um das Ticket zu schließen"));
        inventory.addItem(builder.createGlassPane(Builder.Color.LIME_GREEN, "§aErledigt & Angenommen", 1, "§7➥ Klicke um das Ticket zu schließen"));
        inventory.addItem(builder.createGlassPane(Builder.Color.YELLOW, "§eAlle Tickets", 1, "§7➥§e Zeige alle offnen Tickets von: " + TicketSqlAPI.getPlayerNameById(id)));
        inventory.addItem(builder.createItem(Material.BARRIER, "§eZurück zum Hauptmenu"));
        p.openInventory(inventory);
    }

    public static void openFilteredModerationGui(Player player, String filterPlayerName) throws SQLException {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "Ticket Moderation");
        Builder builder = new Builder();
        for (Object id : TicketSqlAPI.getAllOpenTicketIdsByPlayerName(filterPlayerName)) {
            try {
                String ticket_type = TicketSqlAPI.getTicketTypeById((Integer) id);
                String ticket_args = TicketSqlAPI.getArgsById((Integer) id);
                String eingangs_Datum = TicketSqlAPI.getEingangDatumById((Integer) id);
                String ticket_status = TicketSqlAPI.getTicketStatusById((Integer) id);
                ticket_type = ticketConverter(ticket_type);
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7➥§e Ticket Type: §7" + ticket_type);
                lore.add("§7➥§e Ticket Message: §7" + ticket_args);
                lore.add("§7➥§e Eingangsdatum: §7" + eingangs_Datum);
                lore.add("§7➥§e Ticket Status: §7" + ticket_status);
                lore.add("§7➥§e Ticket Nummer: §7" + id);
                inventory.addItem(builder.createHead(TicketSqlAPI.getPlayerNameById((Integer) id), "§e" + TicketSqlAPI.getPlayerNameById((Integer) id), lore));
            } catch (Exception e) {

            }

        }
        inventory.setItem(53, builder.createItem(Material.BOOK, "§eZum Archiv"));
        player.openInventory(inventory);
    }

    public static void openTicketHistory(Player p) throws SQLException {
        Builder builder = new Builder();
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "Ticket History");
        for (Object id : TicketSqlAPI.getAllTicketIds()) {
            try {
                try {
                    String ticket_type = TicketSqlAPI.getTicketTypeById((Integer) id);
                    String ticket_args = TicketSqlAPI.getArgsById((Integer) id);
                    String eingangs_Datum = TicketSqlAPI.getEingangDatumById((Integer) id);
                    String ticket_status = TicketSqlAPI.getTicketStatusById((Integer) id);
                    String zuständiger_Mod = TicketSqlAPI.getModeratorById((Integer) id).getDisplayName();
                    String abgabe_Datum = TicketSqlAPI.getDatumAbgabeById((Integer) id);
                    if (ticket_status.equalsIgnoreCase("ERLEDIGT_ANGENOMMEN")) {
                        ticket_status = "§aangenommen";
                    } else if (ticket_status.equalsIgnoreCase("ERLEDIGT_ABGELEHNT")) {
                        ticket_status = "§cabgelehnt";
                    }
                    ticket_type = ticketConverter(ticket_type);
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("§7➥§e Ticket Type: §7" + ticket_type);
                    lore.add("§7➥§e Ticket Message: §7" + ticket_args);
                    lore.add("§7➥§e Eingangsdatum: §7" + eingangs_Datum);
                    lore.add("§7➥§e Ticket Status: §7" + ticket_status);
                    lore.add("§7➥§e Ticket Nummer: §7" + id);
                    lore.add("§7➥§e Abgabedatum: §7" + abgabe_Datum);
                    lore.add("§7➥§a Zuständiger Moderator: §7" + zuständiger_Mod);
                    inventory.addItem(builder.createHead(TicketSqlAPI.getPlayerNameById((Integer) id), "§e" + TicketSqlAPI.getPlayerNameById((Integer) id), lore));
                } catch (Exception e) {

                }
            } catch (IllegalArgumentException e) {

            }
        }
        inventory.setItem(53, builder.createItem(Material.BARRIER, "§eZurück zum Hauptmenu"));
        p.openInventory(inventory);
    }

    private static String ticketConverter(String ticketType) {
        if (ticketType.equals("PLOT_VERSCHIEBEN")) {
            ticketType = "§ePlot verschieben";
        } else if (ticketType.equals("PLOT_MELDEN")) {
            ticketType = "§ePlot melden";
        } else if (ticketType.equals("PLOT_BEANTRAGEN")) {
            ticketType = "§ePlot beantragen";
        } else if (ticketType.equals("PLOT_MERGEN")) {
            ticketType = "§ePlot mergen";
        } else if (ticketType.equals("EIGENES_TICKET")) {
            ticketType = "§eEigenes Ticket";
        }
        return ticketType;
    }

    public static void openTicketOverview(Player p) throws SQLException {
        Builder builder = new Builder();
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "Ticket Overview");
        for (Object id : TicketSqlAPI.getAllOpenTicketIdsByPlayerName(p.getName())) {
            try {
                String ticket_type = TicketSqlAPI.getTicketTypeById((Integer) id);
                String ticket_args = TicketSqlAPI.getArgsById((Integer) id);
                String eingangs_Datum = TicketSqlAPI.getEingangDatumById((Integer) id);
                String ticket_status = TicketSqlAPI.getTicketStatusById((Integer) id);
                ticket_type = ticketConverter(ticket_type);
                ticket_status = "§eDein Ticket wurde noch nicht bearbeitet";
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7➥§e Ticket Type: §7" + ticket_type);
                lore.add("§7➥§e Ticket Message: §7" + ticket_args);
                lore.add("§7➥§e Eingangsdatum: §7" + eingangs_Datum);
                lore.add("§7➥§e Ticket Status: §7" + ticket_status);
                lore.add("§7➥§e Ticket Nummer: §7" + id);
                inventory.addItem(builder.createHead(TicketSqlAPI.getPlayerNameById((Integer) id), "§e" + TicketSqlAPI.getPlayerNameById((Integer) id), lore));
            } catch (Exception e) {

            }
        }
        p.openInventory(inventory);
    }


}
