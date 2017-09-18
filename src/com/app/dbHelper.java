package com.app;

import java.sql.*;

/*
* Helper class for setting up database */
public class dbHelper {

    private static Connection connection = null;
    private static Statement statement = null;
    private static String sqlStatement = "";

    public Boolean createDB(String dbName) {

        String url = "jdbc:sqlite:" + dbName + ".db";

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);


        } catch (Exception exception) {

            System.out.println(exception.getMessage());
            return false;
        }

        return true;

    }

    public boolean createPeopleTable(String dbName) {

        String url = "jdbc:sqlite:" + dbName + ".db";

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            sqlStatement = "CREATE TABLE PEOPLE(NAME TEXT NOT NULL, CATEGORY TEXT NOT NULL, ACCOMODATION TEXT NOT NULL)";
            statement.execute(sqlStatement);

            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    public boolean createRoomTable(String dbName) {


        String url = "jdbc:sqlite:" + dbName + ".db";

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            sqlStatement = "CREATE TABLE ROOMS(NAME TEXT NOT NULL, CATEGORY TEXT NOT NULL)";
            statement.execute(sqlStatement);
            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
