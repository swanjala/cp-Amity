package com.app;

import com.sun.deploy.security.MozillaMyKeyStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Amity {

   private List<Person>personList =new ArrayList<>();
   private List<Room>roomList = new ArrayList<>();
   private Connection connection = null;
   private Statement statement = null;
   private String sqlStatement ="";

   public boolean saveState(List<Person> personList,List<Room> roomList, String dbName){

       /* Saving people data in the app */

       if (personList.size() == 0 && roomList.size() == 0) {

           System.out.println("Can not save empty data sets");
           return false;

       } else {

           /* Save People data in the application*/

           String url = "jdbc:sqlite:" + dbName + ".db";

           try {
               Class.forName("org.sqlite.JDBC");
               connection = DriverManager.getConnection(url);

               for (int i = 0; i <personList.size() ; i++) {

                   String name = personList.get(i).toString();
                   String category = personList.get(i).toString();
                   String wants_accomodation = personList.get(i).toString();

                   statement = connection.createStatement();
                   sqlStatement = "INSERT INTO FELLOWS(NAME TEXT NOT NULL, CATEGORY TEXT NOT NULL,WANTS_ACCOMODATION TEXT NOT NULL)VALUES("+name+","+category+","+wants_accomodation+")";
                   statement.execute(sqlStatement);

               }


           }catch (Exception e){
               System.out.println(e.getMessage());
               return false;
           }

           /* Save Room data in the application */

           try {
               Class.forName("org.sqlite.JDBC");
               connection = DriverManager.getConnection(url);

               for (int i = 0; i <roomList.size() ; i++) {

                   String roomName = personList.get(i).toString();
                   String roomType = personList.get(i).toString();
                   String occupants = personList.get(i).toString();

                   statement = connection.createStatement();
                   sqlStatement = "INSERT INTO ROOMS(NAME TEXT NOT NULL, CATEGORY TEXT NOT NULL,OCCUPANTS TEXT NOT NULL)VALUES("+roomName+","+roomType+","+occupants+")";
                   statement.execute(sqlStatement);

               }

           }catch (Exception e){
               System.out.println(e.getMessage());
               return false;
           }
           return true;
       }

   }

   public boolean loadState(){

       return false;
   }


}
