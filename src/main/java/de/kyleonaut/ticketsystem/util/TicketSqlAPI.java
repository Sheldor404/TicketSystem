package de.kyleonaut.ticketsystem.util;

import de.kyleonaut.ticketsystem.TicketSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class TicketSqlAPI {

    public static void createTicket(Player player, TicketTypes ticketType, String args, Date eingangsDatum) throws SQLException {
        TicketSystem.getCon().execute("INSERT INTO ticketsystem_tickets VALUES(" + 0 + ",'" + player.getUniqueId() + "','" + ticketType + "','" + args + "','" + eingangsDatum + "','" + Status.OFFEN + "','NULL','NULL')");
    }

    public static void changeStatus(Status status, int Id, Player player) throws SQLException {
        Date date = new Date();
        TicketSystem.getCon().execute("UPDATE ticketsystem_tickets SET ticket_status = '" + status + "', moderator_uuid = '" + player.getUniqueId() + "',datum_abgabe = '" + date + "' WHERE id = " + Id + "");
    }

    public static Player getPlayerById(int Id) throws SQLException {
        UUID player_uuid = UUID.fromString((String) TicketSystem.getCon().get("SELECT uuid_player FROM ticketsystem_tickets WHERE id = " + Id + "", "uuid_player").get(0));
        return (Player) Bukkit.getOfflinePlayer(player_uuid);
    }

    public static String getTicketTypeById(int Id) throws SQLException {
        return (String) TicketSystem.getCon().get("SELECT ticket_type FROM ticketsystem_tickets WHERE id = " + Id + "", "ticket_type").get(0);
    }

    public static String getArgsById(int Id) throws SQLException {
        return (String) TicketSystem.getCon().get("SELECT ticket_args FROM ticketsystem_tickets WHERE id = " + Id + "", "ticket_args").get(0);
    }

    public static String getEingangDatumById(int Id) throws SQLException {
        return (String) TicketSystem.getCon().get("SElECT eingangs_datum FROM ticketsystem_tickets WHERE id = " + Id, "eingangs_datum").get(0);
    }

    public static String getTicketStatusById(int Id) throws SQLException {
        return (String) TicketSystem.getCon().get("SELECT ticket_status FROM ticketsystem_tickets WHERE id = " + Id + "", "ticket_status").get(0);
    }

    public static Player getModeratorById(int Id) throws SQLException {
        UUID player_uuid = UUID.fromString((String) TicketSystem.getCon().get("SELECT moderator_uuid FROM ticketsystem_tickets WHERE id = " + Id + "", "moderator_uuid").get(0));
        return (Player) Bukkit.getOfflinePlayer(player_uuid);
    }

    public static String getDatumAbgabeById(int Id) throws SQLException {
        return (String) TicketSystem.getCon().get("SElECT datum_abgabe FROM ticketsystem_tickets WHERE id = " + Id, "datum_abgabe").get(0);
    }

    public static Player getScoreByModerator(Player moderator) throws SQLException {
        return Bukkit.getPlayer(UUID.fromString((String) TicketSystem.getCon().get("SELECT score FROM ticketsystem_stats WHERE moderator_uuid = " + moderator.getUniqueId() + "", "score").get(0)));
    }

    public static ArrayList<Object> getAllOpenTicketIds() throws SQLException {
        return TicketSystem.getCon().get("SELECT id FROM ticketsystem_tickets WHERE ticket_status = 'OFFEN'", "id");
    }

    public static ArrayList<Object> getAllOpenTicketIdsByPlayer(Player player) throws SQLException {
        return TicketSystem.getCon().get("SELECT id FROM ticketsystem_tickets WHERE uuid_player = '" + player.getUniqueId() + "' AND ticket_status = 'OFFEN' ", "id");
    }

    public static ArrayList<Object> getAllTicketIds() throws SQLException {
        return TicketSystem.getCon().get("SELECT * FROM ticketsystem_tickets ORDER BY id DESC", "id");
    }


}
