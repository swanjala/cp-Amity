package com.app;

import java.sql.*;

/*
* Helper class for setting up database*/
public class dbHelper {


    private static Connection connection =null;
    private static Statement statement = null;
    private static String sqlStatement= "";
    private String message ="";


    public String createDB(String dbName){

        String url = "jdbc:sqlite:" + dbName;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);

            if (connection != null){
                sqlStatement ="CREATE DATABASE IF NOT EXISTS " + dbName +".db";
                statement =connection.createStatement();
                statement.execute(sqlStatement);
                statement.close();
                connection.close();
                message = "Database Created Successfully";
                return  message;
            }else {
                message = "Unable to connect to database"
            }

        } catch (Exception e){
            System.out.println("Unable to create Database");
        }

        return message;

    }

    public String createPeopleTable(String dbName){

        String url = "jdbc:sqlite:"+ dbName+".db";

        try {
            Class.forName("org.sqlite.JDBC");
            connection =DriverManager.getConnection(url);
            statement = connection.createStatement();
            sqlStatement = "CREATE TABLE FELLOWS(NAME TEXT NOT NULL, CATEGORY TEXT NOT NULL,WANTS_ACCOMODATION TEXT NOT NULL)";
            statement.execute(sqlStatement);
            message = "Table Created Successfully";

            statement.close();
            connection.close();

            return  message;
        }catch (Exception e){

            message ="An error occured on creation";
            return message;
        }

    }
    public String createRoomTable(String dbName){


        String url = "jdbc:sqlite:"+ dbName+".db";

        try {
            Class.forName("org.sqlite.JDBC");
            connection =DriverManager.getConnection(url);
            statement = connection.createStatement();
            sqlStatement = "CREATE TABLE ROOMS(NAME TEXT NOT NULL, CATEGORY TEXT NOT NULL, OCCUPANTS TEXT)";
            statement.execute(sqlStatement);
            message = "Table Created Successfully";

            statement.close();
            connection.close();

            return  message;
        }catch (Exception e){

            message ="An error occured on creation";
            return message;
        }
    }

}
