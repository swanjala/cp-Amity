package com.app;

import java.util.Scanner;


public class Home {

    private static String startText;
    private static String nav = "";
    private static String navContent ="";
    private static Scanner input;


    private static String start() {

        startText = "Welcome to Amity \n" +
                "++++++++++++++++++++++++++ \n" +
                "Add Person <Name> <Category> <Wants Accomodation> \n" +
                "Add Room <Room Name> ... \n" +
                "Reallocate <Name> <New Room Name> \n" +
                "Save State <dbName> \n" +
                "Load State <dbName>\n" +
                "Enter 'quit' to exit";

        return startText;

    }

    public static void main(String[] arg) {

        input = new Scanner(System.in);
        System.out.println(" " + start());


        do {
            nav = input.nextLine();
            navContent = nav.substring(0,12);

            if (navContent.contains("Add Person")){
                /* Add Person Logic*/
            }
            if (navContent.contains("Add Room")){
                /* Add room Logic*/
            }
            if (navContent.contains("Reallocate")){
                /* Reallocate Logic */
            }
            if (navContent.contains("Save State")){
                /* Save State Logic */
            }
            if (navContent.contains("Load state")){
                /* Load State Logic*/
            }
        }while (!nav.equals("quit"));

        System.exit(0);


    }
}
