package de.kyleonaut.ticketsystem.util;

import de.kyleonaut.ticketsystem.TicketSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class TicketSqlAPI {

    public static void createTicket(Player player, TicketTypes ticketType, String args, Date eingangsDatum) throws SQLException {
        TicketSystem.getCon().execute("INSERT INTO ticketsystem_tickets VALUES('" + player.getUniqueId() + "','" + ticketType + "','" + args + "','" + eingangsDatum + "','" + Status.OFFEN + "','','')");
    }

    public static void changeStatus(Status status, int index, Player player) throws SQLException {
        TicketSystem.getCon().execute("UPDATE ticketsystem_tickets SET ticket_status = '" + status + "', moderator_uuid = '" + player.getUniqueId() + "' WHERE index = " + index + "");
    }

    public static Player getPlayerByIndex(int index) throws SQLException {
        UUID player_uuid = UUID.fromString((String) TicketSystem.getCon().get("SELECT uuid_player FROM ticketsystem_tickets WHERE index = " + index + "", "uuid_player").get(0));
        return Bukkit.getPlayer(player_uuid);
    }

    public static TicketTypes getTicketTypeByIndex(int index) throws SQLException {
        return (TicketTypes) TicketSystem.getCon().get("SELECT ticket_type FROM ticketsystem_ticktes WHERE index = " + index + "", "ticket_type").get(0);
    }

    public static String getArgsByIndex(int index) throws SQLException {
        return (String) TicketSystem.getCon().get("SELECT ticket_args FROM ticketsystem_tickets WHERE index = " + index + "", "ticket_args").get(0);
    }

    public static Date getEingangDatumByIndex(int index) throws SQLException {
        return (Date) TicketSystem.getCon().get("SElECT eingangs_datum FROM ticketsytem_tickets WHERE index = " + index, "eingangs_datum").get(0);
    }

    public static Status getTicketStatusByIndex(int index) throws SQLException {
        return (Status) TicketSystem.getCon().get("SELECT ticket_status FROM ticketsystem_tickets WHERE index = " + index + "", "ticket_status").get(0);
    }

    public static Player getModeratorByIndex(int index) throws SQLException {
        UUID player_uuid = UUID.fromString((String) TicketSystem.getCon().get("SELECT moderator_uuid FROM ticketsystem_tickets WHERE index = " + index + "", "moderator_uuid").get(0));
        return Bukkit.getPlayer(player_uuid);
    }

    public static Date getDatumAbgabeByIndex(int index) throws SQLException {
        return (Date) TicketSystem.getCon().get("SElECT datum_abgabe FROM ticketsytem_tickets WHERE index = " + index, "datum_abgabe").get(0);
    }


}
