package de.kyleonaut.ticketsystem.util;

import de.kyleonaut.ticketsystem.TicketSystem;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Date;

public class TicketSqlAPI {

    public static void createTicket(Player player, TicketTypes ticketType, String args, Date eingangsDatum) throws SQLException {
        TicketSystem.getCon().execute("INSERT INTO ticketsystem_tickets VALUES('" + player.getUniqueId() + "','" + ticketType + "','" + args + "','" + eingangsDatum + "','" + Status.OFFEN + "','','')");
    }

    public static void changeStatus(Status status, int index, Player player) throws SQLException {
        TicketSystem.getCon().execute("UPDATE ticketsystem_tickets SET ticket_status = '" + status + "',  ");
    }
}
