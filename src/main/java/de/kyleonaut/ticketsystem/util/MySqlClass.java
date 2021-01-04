package de.kyleonaut.ticketsystem.util;

import java.sql.*;
import java.util.ArrayList;


public class MySqlClass {
    private Connection connection;
    private String host, database, username, password;
    private int port;


    public MySqlClass() {

        host = Config.getCfg().getString("Mysql.host");
        port = Config.getCfg().getInt("Mysql.port");
        database = Config.getCfg().getString("Mysql.database");
        username = Config.getCfg().getString("Mysql.username");
        password = Config.getCfg().getString("Mysql.password");


        try {

            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database +
                        "?autoReconnect=true&useSSL=false", username, password));

                System.out.println("[MySQL] Verbindung aufgebaut");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }


    public ArrayList<Object> get(String statement, String column) throws SQLException {
        PreparedStatement cmd;
        cmd = this.connection.prepareStatement(statement);
        ResultSet res = cmd.executeQuery();
        ArrayList<Object> value = new ArrayList();

        while (res.next()) {
            value.add(res.getObject(column));
        }
        return value;
    }

    public ArrayList<Object> get(String statement, int columnIndex) throws SQLException {
        PreparedStatement cmd;
        cmd = this.connection.prepareStatement(statement);
        ResultSet res = cmd.executeQuery();
        ArrayList<Object> value = new ArrayList();

        while (res.next()) {
            value.add(res.getObject(columnIndex));
        }
        return value;
    }

    public void execute(String statement) throws SQLException {
        PreparedStatement cmd;
        cmd = this.connection.prepareStatement(statement);
        cmd.executeUpdate();
    }


    public void disconnect() {
        if (isConnected()) {
            try {
                this.connection.close();
                System.out.println("[MySQL] Verbindung getrennt!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private boolean isConnected() {
        return (this.connection == null ? false : true);
    }
}




