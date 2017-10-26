package com.app.app;

import com.app.fields.Person;
import com.app.fields.Room;

import java.util.*;

public class VaribleParser {


    /**
     * addPersonTokenizer: This method uses a space delimiter to break
     * down an input String entered in Commandline User Interface and parses
     * it into an array of usablevariables that adds a person to the application.
     */

    public static List<Person> addPersonTokenizer(StringTokenizer addPersonST, Collection<List<Room>> roomInfo){

        Person personVars = new Person();
        StringBuilder nameSb = new StringBuilder();
        List<Person> addPersonVarObject = new ArrayList<Person>();
        HashMap<String, List<Room>> randomizedName;

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
        List<Room> livingSpace =randomizedName.get("Living") ;
        List<Room> officeSpace = randomizedName.get("Office") ;


        if (personVars.getAccomodationRequest().equals("Y") &&
                !personVars.getCategory().equals("STAFF")) {

            personVars.setAccomodationRoom(livingSpace.get(0).getRoomName());
            livingSpace.get(0).setPersonName(personVars.getName());

            personVars.setOfficeRoom(officeSpace.get(0).getRoomName());
            officeSpace.get(0).setPersonName(personVars.getName());


        } else if (personVars.getAccomodationRequest().toUpperCase().trim().equals("N") &&
                personVars.getCategory().trim().equals("FELLOW")) {

            personVars.setAccomodationRoom("None");
            personVars.setOfficeRoom(officeSpace.get(0).getRoomName());
            officeSpace.get(0).setPersonName(personVars.getName());

        } else if (personVars.getCategory().equals("STAFF")) {

            personVars.setAccomodationRoom("None");
            personVars.setOfficeRoom(officeSpace.get(0).getRoomName());
            officeSpace.get(0).setPersonName(personVars.getName());
        }


        addPersonVarObject.add(personVars);


        return addPersonVarObject;
    }

    /**
     * shuffleBox : Generates a hash set of rooms  at random when a person
     * is added to the application.
     * It takes in a Collection of Lists of rooms, generates a random
     * index , references a random room in the list and returns
     * it
     *
     * @return
     */

    public static HashMap<String, List<Room>> shuffleBox(Collection<List<Room>> roomList) {

        Collection<List<Room>> livingRooms = new ArrayList<>();
        Collection<List<Room>> officeSpaces = new ArrayList<>();

        HashMap<String, List<Room>> roomValues = new HashMap<>();
        Random randValue = new Random();

        List<Room> livingRoomToAllocate = new ArrayList<>();
        List<Room> officeSpaceToAllocate;
        int livingRoomIndex = 0;
        int officeIndex = 0;


        Iterator<List<Room>> iterator = roomList.iterator();

        while (iterator.hasNext()) {

            List<Room> roomIterator = iterator.next();

                if (roomIterator.get(0).getRoomCategory().equals("LIVING")) {

                    livingRooms.add(roomIterator);

                } else if (roomIterator.get(0).getRoomCategory().equals("OFFICE")) {
                    officeSpaces.add(roomIterator);
                }

        }

        if (livingRooms.size()== 0){

            livingRoomToAllocate = null;

        }else {

            livingRoomIndex = randValue.nextInt(livingRooms.size());
            /*TODO
            * Ensure that a random list can be picked from
            * a collection type*/
            Collections.shuffle((List<?>) livingRooms);
            livingRoomToAllocate = (List<Room>) livingRooms.iterator().next().get(livingRoomIndex);

        }

        if (officeSpaces.size() ==0){
            officeSpaceToAllocate = null;
        } else {
            //officeIndex = randValue.nextInt(officeSpaces.size());
            Collections.shuffle((List<?>) officeSpaces);
            officeSpaceToAllocate = (List<Room>) officeSpaces.iterator().next().get(officeIndex);
        }

        roomValues.put("Office", officeSpaceToAllocate);
        roomValues.put("Living", livingRoomToAllocate);
        roomValues.put("NoRespose", null);

        return roomValues;

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



    /**
     * addRoomTokenizer: This method uses a space delimiter to break down an
     * input String entered in a Commandline User Interface and parses it
     * to a String value set as a database name.
     */

    public static String saveLoadStateTokenizer(String saveStateInput) {

        StringTokenizer saveStateST = new StringTokenizer(saveStateInput.substring(10), " ");
        StringBuilder dbnameSB = new StringBuilder();

        while (saveStateST.hasMoreTokens()) {
            String databaseName = saveStateST.nextToken();
            dbnameSB.append(databaseName);
        }

        return dbnameSB.toString();

    }


}
