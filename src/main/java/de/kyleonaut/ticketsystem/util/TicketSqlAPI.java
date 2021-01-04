package de.kyleonaut.ticketsystem.util;

import de.kyleonaut.ticketsystem.TicketSystem;
import org.bukkit.entity.Player;

public class TicketSqlAPI {


    public static void createTicket(Player player) {
        TicketSystem.getCon().execute("INSERT INTO ticketsystem_tickets VALUES()");
    }
}
