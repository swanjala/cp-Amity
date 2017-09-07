package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Main class where the entire application is run from.
 * the class generates a Command Line user interface.
 */
public class Home {

    private static String startText;
    private static String nav = "";
    private static String navContent ="";
    private static Scanner input;
    private static PersonOps person = new PersonOps();


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

    /* String tokenizer with a space delimeter : Takes in add person user input
     * and parses user defined variables into an array of  variables
     * of type Person
     */

    private static List<Person> addPersonTokenizer(String addPersonInput) {

        List<Person> addPersonVarObject = new ArrayList<Person>();
        Person personVars = new Person();

        StringTokenizer addPersonST = new StringTokenizer(addPersonInput.substring(10)," ");
        StringBuilder nameSb = new StringBuilder();

        while (addPersonST.hasMoreTokens()){

            for (int i = 0; i < 2 ; i++) {

                String nameVal = addPersonST.nextToken();
                nameSb.append(" " +nameVal);
            }

            String Category = addPersonST.nextToken();

            personVars.setName(nameSb.toString());
            personVars.setCategory(Category.toString());

            addPersonVarObject.add(personVars);

        }

        return addPersonVarObject;
    }

    private static List<Room> addRoomTokenizer(String addRoomInput) {

        List<Room> addRoomVarObject = new ArrayList<Room>();
        Room roomVars = new Room();

        StringTokenizer addRoomST = new StringTokenizer(addRoomInput.substring(10)," ");
        StringBuilder roomNameSb = new StringBuilder();

        while (addRoomST.hasMoreTokens()){

            for (int i = 0; i < 2 ; i++) {

                String RoomNameVal = addRoomST.nextToken();
                roomNameSb.append(" " +RoomNameVal);
            }

            String RoomCategory = addRoomST.nextToken();

            roomVars.setRoomName(roomNameSb.toString());
            roomVars.setRoomCategory(RoomCategory.toString());


            addRoomVarObject.add(roomVars);

        }

        return addRoomVarObject;
    }

    public static void main(String[] arg) {

        input = new Scanner(System.in);
        System.out.println(" " + start());


        do {
            nav = input.nextLine();
            navContent = nav.substring(0,12);

            if (navContent.contains("Add Person")){

                List<Person> varList = addPersonTokenizer(nav);


            }
            if (navContent.contains("Add Room")){

                List<Room> roomVarList = addRoomTokenizer(nav);
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
