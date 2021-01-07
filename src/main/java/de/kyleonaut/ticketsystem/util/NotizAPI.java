package de.kyleonaut.ticketsystem.util;

import de.kyleonaut.ticketsystem.TicketSystem;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class NotizAPI {

    public static int getTicketIdByNotizId(int notizId) throws SQLException {
        return (int) TicketSystem.getCon().get("SELECT ticket_id FROM ticketsystem_ticket_notiz WHERE notiz_id = " + notizId + "", "ticket_id").get(0);
    }

    public static String getNotizMessageByNotizId(int notizId) throws SQLException {
        return (String) TicketSystem.getCon().get("SElECT notiz_message FROM ticketsystem_ticket_notiz WHERE notiz_id = " + notizId + "", "notiz_message").get(0);
    }

    public static String getAuthorByNotizId(int notizid) throws SQLException {
        return (String) TicketSystem.getCon().get("SELECT author_name FROM ticketsystem_ticket_notiz WHERE notiz_id = " + notizid + "", "author_name").get(0);
    }

    public static ArrayList<Object> getAllNotizIdsByTicketId(int ticket_id) throws SQLException {
        return TicketSystem.getCon().get("SELECT * FROM `ticketsystem_ticket_notiz` WHERE ticket_id = " + ticket_id + " ORDER BY notiz_id DESC", "notiz_id");
    }

    public static void createNewNotiz(Player author, String message, int ticket_id) throws SQLException {
        LocalDateTime ldt = LocalDateTime.now();
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss", Locale.GERMANY).format(ldt);
        TicketSystem.getCon().execute("INSERT INTO ticketsystem_ticket_notiz VALUES(" + ticket_id + "," + 0 + ",'" + author.getUniqueId() + "','" + author.getName() + "','" + message + "','" + date + "') ");
        TicketSqlAPI.changeStatus(Status.IN_BEARBEITUNG, ticket_id, author);
    }

    public static String getDateByNotizId(int notizid) throws SQLException {
        return String.valueOf(TicketSystem.getCon().get("SELECT datum_uhrzeit FROM ticketsystem_ticket_notiz WHERE notiz_id = " + notizid + "", "datum_uhrzeit"));
    }
}
