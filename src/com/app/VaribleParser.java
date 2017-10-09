package com.app;

import java.util.*;

public class VaribleParser {


    /**
     * addPersonTokenizer: This method uses a space delimiter to break
     * down an input String entered in Commandline User Interface and parses
     * it into an array of usablevariables that adds a person to the application.
     */

    private static List<Person> addPersonTokenizer(StringTokenizer addPersonST, Collection<List<Room>> roomInfo){

        Room room = new Room();
        Person personVars = new Person();
        StringBuilder nameSb = new StringBuilder();
        Iterator<List<Room>> itr = roomInfo.iterator();
        List<Room> list;
        List<Room> roomNames = new ArrayList<>();
        List<Person> addPersonVarObject = new ArrayList<Person>();
        HashMap<String, String> randomizedName = new HashMap<>();

        while (addPersonST.hasMoreTokens()) {

            for (int i = 0; i < 2; i++) {
                String nameVal = addPersonST.nextToken();
                nameSb.append(nameVal + " ");

            }

            String Category = addPersonST.nextToken();
            String accomodation = addPersonST.nextToken().toUpperCase();
            personVars.setName(nameSb.toString().trim());
            personVars.setCategory(Category);
            personVars.setAccomodationRequest(accomodation);

        }

        randomizedName = shuffleBox(roomInfo);

        if (personVars.getAccomodationRequest().equals("Y") &&
                !personVars.getCategory().equals("STAFF")) {

            randomizedName = shuffleBox(roomInfo);
            personVars.setAccomodationRoom(randomizedName.get("Living"));
            personVars.setOfficeRoom(randomizedName.get("Office"));

        } else if (personVars.getAccomodationRequest().toUpperCase().trim().equals("N") &&
                personVars.getCategory().trim().equals("FELLOW")) {

            personVars.setAccomodationRoom("None");
            personVars.setOfficeRoom(randomizedName.get("Office"));

        } else if (personVars.getCategory().equals("STAFF")) {

            personVars.setAccomodationRoom("None");
            personVars.setOfficeRoom(randomizedName.get("Office"));
        }


        addPersonVarObject.add(personVars);


        return addPersonVarObject;
    }

    /**
     * addRoomTokenizer: This method uses a space delimiter to break
     * down an input String entered in a Commandline User Interface
     * and parses it into an array of usable variables that adds a
     * new room to the application.
     */

    public static List<Room> addRoomTokenizer(String addRoomInput) {

        List<Room> addRoomVarObject = new ArrayList<Room>();
        Room roomVars = new Room();

        StringTokenizer addRoomST = new StringTokenizer(addRoomInput.substring(9), " ");

        while (addRoomST.hasMoreTokens()) {

            String RoomNameVal = addRoomST.nextToken();
            String RoomCategory = addRoomST.nextToken();

            roomVars.setRoomName(RoomNameVal);
            roomVars.setRoomCategory(RoomCategory);

            addRoomVarObject.add(roomVars);

        }

        return addRoomVarObject;
    }

    /**
     * reallocationTokenizer: This method uses a space delimiter to break
     * down an input String entered in a Commandline User Interface
     * and parses it into an array of usable string variables that moves
     * a user from their initially assigned room to a new
     * room in the application.
     */

    public static String[] reallocateRoomTokenizer(String reallocateRoomInput) {

        String[] reallocateVarArray = new String[2];


        StringTokenizer reallocateST = new StringTokenizer(reallocateRoomInput.substring(10), " ");
        StringBuilder nameString = new StringBuilder();

        while (reallocateST.hasMoreTokens()) {
            for (int i = 0; i < 2; i++) {
                String nameVal = reallocateST.nextToken();
                nameString.append(nameVal + " ");

            }
            String personName = nameString.toString().trim();
            String newRoomName = reallocateST.nextToken();


            reallocateVarArray[0] = personName;
            reallocateVarArray[1] = newRoomName;
        }

        return reallocateVarArray;
    }



    /**
     * printRoomTokenizer: This method uses a space delimiter to break
     * down an input String entered in a Commandline User Interface
     * and parses it into an array of usable string variables that prints
     * users allocated to a specified room.
     */
    public static  String printRoomTokenizer(String printRoomString){
        String  printRoomVar = "";

        StringTokenizer printRoomST = new StringTokenizer(printRoomString.substring(10));

        while (printRoomST.hasMoreTokens()){
            printRoomVar = printRoomST.nextToken();
        }


        return printRoomVar;
    }


}
