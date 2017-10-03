package com.app;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.math.RoundingMode;
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
                "Print Peopele \n" +
                "Enter 'quit' to exit";

        return startText;
    }

    /**
     * addPersonTokenizer: This method uses a space delimiter to break
     * down an input String entered in Commandline User Interface and parses
     * it into an array of usablevariables that adds a person to the application.
     */

    private static List<Person> addPersonTokenizer(StringTokenizer addPersonST) {

        Room room = new Room();
        Person personVars = new Person();
        StringBuilder nameSb = new StringBuilder();
        Iterator<List<Room>> itr = roomInfo.iterator();
        List<Room> list;
        List<Room> roomNames = new ArrayList<>();
        HashMap<String, String> randomizedName = new HashMap<>();
        List<Person> addPersonVarObject = new ArrayList<Person>();

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

        while (itr.hasNext()) {

            list = itr.next();
            room.setRoomName(list.get(0).getRoomName());
            room.setRoomCategory(list.get(0).getRoomCategory());

            roomNames.add(room);

        }

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
     * shuffleBox : Generates a hash set of rooms  at random when a person
     * is added to the application.
     * It takes in a Collection of Lists of rooms, generates a random
     * index , references a random room in the list and returns
     * it
     *
     * @return
     */

    private static HashMap<String, String> shuffleBox(Collection<List<Room>> roomList) {

        List<String> livingRoomNames = new ArrayList<>();
        List<String> officeSpace = new ArrayList<>();
        HashMap<String, String> roomValues = new HashMap<>();
        Room room = new Room();
        Random randValue = new Random();
        List<Room> result = new ArrayList<>();
        String livingRoomToAllocate;
        String officeSpaceToAllocate;
         int livingRoomIndex = 0;
        int officeIndex = 0;


        Iterator<List<Room>> iterator = roomList.iterator();


        while (iterator.hasNext()) {

            List<Room> roomIterator = iterator.next();


            for (int index = 0; index < roomIterator.size(); index++) {

                if (roomIterator.get(0).getRoomCategory().equals("LIVING")) {
                    livingRoomNames.add(roomIterator.get(0).getRoomName());
                } else if (roomIterator.get(0).getRoomCategory().equals("OFFICE")) {
                    officeSpace.add(roomIterator.get(0).getRoomName());
                }
            }
        }

        if (livingRoomNames.size()== 0){

            livingRoomToAllocate ="Sample Room";

        }else {
            livingRoomIndex = randValue.nextInt(livingRoomNames.size());
            livingRoomToAllocate = livingRoomNames.get(livingRoomIndex);
        }

        if (officeSpace.size() ==0){
            officeSpaceToAllocate = "Sample Space";
        } else {
            officeIndex = randValue.nextInt(officeSpace.size());
            officeSpaceToAllocate = officeSpace.get(officeIndex);
        }

        roomValues.put("Office", officeSpaceToAllocate);
        roomValues.put("Living", livingRoomToAllocate);
        roomValues.put("NoRespose", "No Room");

        return roomValues;

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

        return dbnameSB.toString();

    }

    public static void main(String[] arg) throws SQLException, ClassNotFoundException {

        List<Person> varList = new ArrayList<>();
        input = new Scanner(System.in);
        System.out.println(" " + start());

        do {
            navigationString = input.nextLine();

            if (navigationString.length() > 12) {
                navContent = navigationString.substring(0, 12);
            }

            if (navContent.contains("Add Person")) {


                StringTokenizer addPersonST = new StringTokenizer(navigationString.substring(10), " ");

                int tokenCounter = addPersonST.countTokens();


                if (tokenCounter < 3) {

                    System.out.println("Use this format \n" +
                            "Add Person <First Name> <Second Name> <Category> <Wants Accomodation> ");

                } else if (tokenCounter == 3) {

                    String navigationStringDefault = navigationString.substring(10).concat("N");
                    addPersonST = new StringTokenizer(navigationStringDefault);
                    varList = addPersonTokenizer(addPersonST);

                } else {

                    varList = addPersonTokenizer(addPersonST);
                }

                PersonOps personOps = new PersonOps(varList.get(0).getName(), varList.get(0).getCategory().toUpperCase()
                        , varList.get(0).getAccomodationRequest().toUpperCase(), varList.get(0).getAccomodationRoom(),
                        varList.get(0).getAllocatedOffice());

                List<Person> pData = personOps.addPerson();
                personInfo.add(pData);


                Iterator<List<Person>> itr = personInfo.iterator();

                System.out.println("Name \t \t  Category \t Room \t Office");

                while (itr.hasNext()) {
                    List<Person> element = itr.next();
                    System.out.println(element.get(0).getName() + " " + element.get(0).getCategory() +
                            " \t" + element.get(0).getAccomodationRoom() + "\t \t"
                            + element.get(0).getAllocatedOffice());
                }

            }

            if (navContent.contains("Add Room")) {

                List<Room> roomVarList = addRoomTokenizer(navigationString);

                RoomOps roomOps = new RoomOps(roomVarList.get(0).getRoomName(),
                        roomVarList.get(0).getRoomCategory().toUpperCase());
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

                System.out.println(personInfo.size());

                PersonOps personOps = new PersonOps(roomVars[0], roomVars[1], personInfo);
                personOps.reallocatePerson();

            }
            if (navigationString.contains("Save State")) {

                String dbName = saveLoadStateTokenizer(navigationString);

                dbModels models = new dbModels();
                models.saveState(dbName, personInfo, roomInfo);

            }
            if (navigationString.contains("Load State")) {

                String dbName = saveLoadStateTokenizer(navigationString);

                dbModels models = new dbModels();
                HashMap<String, List> data = models.loadState(dbName);
                System.out.println("Getting data information " + data.toString());

                List<Person> peopleList = data.get("People");
                List<Room> roomList = data.get("Rooms");

                for (int index = 0; index < peopleList.size(); index++) {
                    personInfo.add(peopleList);
                }
                for (int index = 0; index < roomList.size(); index++) {
                    roomInfo.add(roomList);
                }

            } if (navigationString.contains("Print People")){

                PersonOps personOps = new PersonOps();

                String peopleValue = personOps.printPeople(personInfo);

                System.out.println(peopleValue);
            }

            else {
                System.out.println("Enter Correct Commands, see description for help");
            }
        } while (!navigationString.equals("quit"));

        System.exit(0);


    }
}
