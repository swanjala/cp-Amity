package com.app;

import java.sql.Array;
import java.sql.SQLException;
import java.util.*;

/**
 * Home class.
 * This class runs the application. It contains methods that invoke the respective
 * features that the application relies on for the management of Amity.
 * The class generates a Commandline User Interface where the user interracts with
 * the applcation as described in the Start Text.
 */

public class Home {

    private static String startText;
    private static String navigationString = "";
    private static String navContent = "";
    private static Scanner input;
    private static List<Person> personList = new ArrayList<>();
    private static Collection<List<Person>> personInfo = new ArrayList<List<Person>>();
    private static Collection<List<Room>> roomInfo = new ArrayList<List<Room>>();

    private static String start() {

        startText = "WELCOME TO AMITY \n" +
                "++++++++++++++++++++++++++ \n" +
                "Add Person <First Name> <Second Name> <Category> <Wants Accomodation> \n" +
                "Add Room <Room Name> ... \n" +
                "Reallocate <First Name> <Second Name> <New Room Name> \n" +
                "Save State <dbName> \n" +
                "Load State <dbName>\n" +
                "Enter 'quit' to exit";

        return startText;
    }

    /**
     * addPersonTokenizer: This method uses a space delimiter to break
     * down an input String entered in Commandline User Interface and parses
     * it into an array of usablevariables that adds a person to the application.
     */

    private static List<Person> addPersonTokenizer(String addPersonInput) {

        List<Person> addPersonVarObject = new ArrayList<Person>();
        Person personVars = new Person();
        StringTokenizer addPersonST = new StringTokenizer(addPersonInput.substring(10), " ");
        StringBuilder nameSb = new StringBuilder();
        Iterator<List<Room>> itr = roomInfo.iterator();

        while (addPersonST.hasMoreTokens()) {

            for (int i = 0; i < 2; i++) {

                String nameVal = addPersonST.nextToken();
                nameSb.append(nameVal + " ");

            }

            String Category = addPersonST.nextToken();
            String accomodation = addPersonST.nextToken();
            personVars.setName(nameSb.toString().trim());
            personVars.setCategory(Category);
            personVars.setAccomodationRequest(accomodation);

        }

        List<Room> list;
        List<String> roomNames = new ArrayList<>();
        String randomizedName;

        while (itr.hasNext()) {

            list = itr.next();
            roomNames.add(list.get(0).getRoomName());

        }

        randomizedName = shuffleBox(roomNames);
        personVars.setAccomodationRoom(randomizedName);
        addPersonVarObject.add(personVars);
        return addPersonVarObject;
    }

    /**
     * shuffleBox : Generates a room at random when a person is added to the
     * application for the firsttime.
     * It takes in a List of rooms, generates a random
     * index , references a random room in the list and returns
     * it
     * application
     *
     * @param list
     * @return
     */

    private static String shuffleBox(List<String> list) {

        Random randValue = new Random();
        int index = randValue.nextInt(list.size());

        return list.get(index);

    }

    /**
     * addRoomTokenizer: This method uses a space delimiter to break
     * down an input String entered in a Commandline User Interface
     * and parses it into an array of usable variables that adds a
     * new room to the application.
     */

    private static List<Room> addRoomTokenizer(String addRoomInput) {

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

    private static String[] reallocateRoomTokenizer(String reallocateRoomInput) {

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
     * addRoomTokenizer: This method uses a space delimiter to break down an
     * input String entered in a Commandline User Interface and parses it
     * to a String value set as a database name.
     */

    private static String saveLoadStateTokenizer(String saveStateInput) {

        List<String> saveLoadStateParamList = new ArrayList<String>();
        StringTokenizer saveStateST = new StringTokenizer(saveStateInput.substring(10), " ");
        StringBuilder dbnameSB = new StringBuilder();

        while (saveStateST.hasMoreTokens()) {
            String databaseName = saveStateST.nextToken();
            dbnameSB.append(databaseName);
        }

        return dbnameSB.toString() ;

    }

    public static void main(String[] arg) throws SQLException, ClassNotFoundException {

        input = new Scanner(System.in);
        System.out.println(" " + start());


        do {
            navigationString = input.nextLine();

            if (navigationString.length() > 12) {
                navContent = navigationString.substring(0, 12);
            }

            if (navContent.contains("Add Person")) {

                List<Person> varList = addPersonTokenizer(navigationString);

                PersonOps personOps = new PersonOps(varList.get(0).getName(), varList.get(0).getCategory()
                        , varList.get(0).getAccomodationRequest(), varList.get(0).getAccomodationRoom());

                List<Person> pData = personOps.addPerson();
                personInfo.add(pData);

                System.out.println(pData.get(0).getName() + pData.get(0).getAccomodationRoom());
                Iterator<List<Person>> itr = personInfo.iterator();

                while (itr.hasNext()) {
                    List<Person> element = itr.next();
                    System.out.println(element.get(0).getName() + " " +
                            "" + element.get(0).getAccomodationRoom());
                }

            }
            if (navContent.contains("Add Room")) {

                List<Room> roomVarList = addRoomTokenizer(navigationString);

                RoomOps roomOps = new RoomOps(roomVarList.get(0).getRoomName(), roomVarList.get(0).getRoomCategory());
                List<Room> newRoom = roomOps.addRoom();
                roomInfo.add(newRoom);

                Iterator<List<Room>> roomItr = roomInfo.iterator();

                while (roomItr.hasNext()) {
                    List<Room> roomElements = roomItr.next();
                    System.out.println(roomElements.get(0).getRoomName() + " " +
                            "" + roomElements.get(0).getRoomCategory() + " " +
                            "" + roomElements.get(0).getRoomCapacity());

                }

            }
            if (navigationString.contains("Reallocate")) {

                String[] roomVars = reallocateRoomTokenizer(navigationString);

                Iterator<List<Person>> itr = personInfo.iterator();

                while (itr.hasNext()) {
                    List<Person> element = itr.next();

                    if (element.get(0).getName().equals(roomVars[0].trim())) {
                        element.get(0).setAccomodationRoom(roomVars[1]);

                    }
                }

            }
            if (navigationString.contains("Save State")) {

                String dbName = saveLoadStateTokenizer(navigationString);

                dbModels models = new dbModels();
                models.saveState(dbName,personInfo,roomInfo);

            }
            if (navigationString.contains("Load State")) {

                String dbName = saveLoadStateTokenizer(navigationString);

                dbModels models = new dbModels();
                HashMap<String,List> data = models.loadState(dbName);
                System.out.println("Getting data information " + data.toString());

                List<Person> peopleList = data.get("People");
                List<Room> roomList = data.get("Rooms");

                for (int index = 0; index < peopleList.size(); index++) {
                    personInfo.add(peopleList);
                }
                for (int index = 0; index < roomList.size(); index++) {
                    roomInfo.add(roomList);
                }

            } else {
                System.out.println("Enter Correct Commands, see description for help");
            }
        } while (!navigationString.equals("quit"));

        System.exit(0);


    }
}
