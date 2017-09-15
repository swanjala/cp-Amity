package com.app;

import java.sql.*;

/*
* Helper class for setting up database */
public class dbHelper {

    private static Connection connection = null;
    private static Statement statement = null;
    private static String sqlStatement = "";
    private String message = "";

    public String createDB(String dbName) {

        String url = "jdbc:sqlite:" + dbName + ".db";
        message = "Connection Success!";

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            return message;

        } catch (Exception exception) {

            System.out.println(exception.getMessage());
        }

        return message;

    }

    public String createPeopleTable(String dbName) {

        String url = "jdbc:sqlite:" + dbName + ".db";

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            sqlStatement = "CREATE TABLE PEOPLE(NAME TEXT NOT NULL, CATEGORY TEXT NOT NULL,ACCOMODATION TEXT NOT NULL)";
            statement.execute(sqlStatement);
            message = "People table created successfully";

            statement.close();
            connection.close();

            return message;
        } catch (Exception e) {

            message = "An error occured on creation";
            return message;
        }

    }

    public String createRoomTable(String dbName) {


        String url = "jdbc:sqlite:" + dbName + ".db";

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            sqlStatement = "CREATE TABLE ROOMS(NAME TEXT NOT NULL, CATEGORY TEXT NOT NULL)";
            statement.execute(sqlStatement);
            message = "Room table created successfully";

            statement.close();
            connection.close();

            return message;
        } catch (Exception e) {

            message = "An error occured on creation";
            return message;
        }
    }

    public String dropDataBase(String dbName) {

        // deleting database
        message = "Database was not deleted";
        String url = "jdbc:sqlite:" + dbName + ".db";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            sqlStatement = "DROP DATABASE " + dbName;
            statement.execute(sqlStatement);
            message = "DB dropped";
            statement.close();
            connection.close();
            return message;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return message;

    }

}
