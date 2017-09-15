package com.app;

import java.sql.SQLException;
import java.util.*;


/**
 * Main class where the entire application is run from.
 * the class generates a Command Line user interface.
 */
public class Home {

    private static String startText;
    private static String nav = "";
    private static String navContent = "";
    private static Scanner input;
    private static List<Person> personList = new ArrayList<>();
    private static Collection<List<Person>> personInfo = new ArrayList<List<Person>>();
    private static Collection<List<Room>> roomInfo = new ArrayList<List<Room>>();

    private static String start() {

        startText = "Welcome to Amity \n" +
                "++++++++++++++++++++++++++ \n" +
                "Add Person <Name> <Category> <Wants Accomodation> \n" +
                "Add Room <Room Name> ... \n" +
                "Reallocate <Name> <New Room N`ame> \n" +
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
        Random random = new Random();

        StringTokenizer addPersonST = new StringTokenizer(addPersonInput.substring(10), " ");
        StringBuilder nameSb = new StringBuilder();

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

        Iterator<List<Room>> itr = roomInfo.iterator();

        List<Room> list = null;
        List<String> roomNames = new ArrayList<>();
        String randomizedName = null;
        while (itr.hasNext()) {

            list = itr.next();
            roomNames.add(list.get(0).getRoomName());

        }

        randomizedName = shuffleBox(roomNames);

        personVars.setAccomodationRoom(randomizedName);
        addPersonVarObject.add(personVars);
        return addPersonVarObject;
    }

    private static String shuffleBox(List<String> list) {

        Random randValue = new Random();
        int index = randValue.nextInt(list.size());

        return list.get(index);

    }

    private static List<Room> addRoomTokenizer(String addRoomInput) {

        List<Room> addRoomVarObject = new ArrayList<Room>();
        List<String> roomList = new ArrayList<String>();
        Room roomVars = new Room();

        StringTokenizer addRoomST = new StringTokenizer(addRoomInput.substring(9), " ");
        StringBuilder roomSB = new StringBuilder();
        while (addRoomST.hasMoreTokens()) {

            String RoomNameVal = addRoomST.nextToken();
            String RoomCategory = addRoomST.nextToken();

            roomVars.setRoomName(RoomNameVal.toString());
            roomVars.setRoomCategory(RoomCategory.toString());

            addRoomVarObject.add(roomVars);

        }

        return addRoomVarObject;
    }

    /* Method takes in user input and returns a list of Variables
    * and isolates the command
    * */

    private static List<String> reallocateRoomTokenizer(String reallocateRoomInput) {

        List<String> reallocateVarObj = new ArrayList<String>();

        StringTokenizer reallocateST = new StringTokenizer(reallocateRoomInput.substring(10), " ");

        while (reallocateST.hasMoreTokens()) {
            String personName = reallocateST.nextToken();
            String newRoomName = reallocateST.nextToken();

            reallocateVarObj.add(personName);
            reallocateVarObj.add(newRoomName);
        }

        return reallocateVarObj;
    }

    /* Method picks up the database name after the save and load state command and
    * assigns it to a String variable
    * */

    private static List<String> saveLoadStateTokenizer(String saveStateInput) {

        List<String> saveLoadStateParamList = new ArrayList<String>();
        StringTokenizer saveStateST = new StringTokenizer(saveStateInput.substring(10), " ");

        while (saveStateST.hasMoreTokens()) {
            String databaseName = saveStateST.nextToken();
        }
        return saveLoadStateParamList;

    }

    public static void main(String[] arg) throws SQLException, ClassNotFoundException {

        input = new Scanner(System.in);
        System.out.println(" " + start());


        do {
            nav = input.nextLine();
            navContent = nav.substring(0, 12);

            if (navContent.contains("Add Person")) {

                List<Person> varList = addPersonTokenizer(nav);

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

                List<Room> roomVarList = addRoomTokenizer(nav);

                RoomOps room = new RoomOps(roomVarList.get(0).getRoomName(), roomVarList.get(0).getRoomCategory());
                List<Room> newRoom = room.addRoom();
                roomInfo.add(newRoom);
                room.setRoomInfo(roomInfo);

                System.out.println(roomInfo.size());

                Iterator<List<Room>> roomItr = roomInfo.iterator();


                while (roomItr.hasNext()) {
                    List<Room> roomElements = roomItr.next();

                    for (int i = 0; i < roomElements.size(); i++) {
                        System.out.println(roomElements.get(i).getRoomName() + " " +
                                "" + roomElements.get(i).getRoomCategory() + " " +
                                "" + roomElements.get(i).getRoomCapacity());
                    }

                }

            }
            if (navContent.contains("Reallocate")) {

                List<String> roomVars = reallocateRoomTokenizer(nav);
                PersonOps person = new PersonOps(roomVars.get(0).toString(), roomVars.get(1).toString());

            }
            if (navContent.contains("Save State")) {

                List<String> dbName = saveLoadStateTokenizer(nav);

                dbModels models = new dbModels();
                models.saveState(dbName.toString());

            }
            if (navContent.contains("Load state")) {

                List<String> dbName = saveLoadStateTokenizer(nav);

                dbModels models = new dbModels();
                models.loadState(dbName.toString());

            } else {
                System.out.println("Enter Correct Commands, see description for help");
            }
        } while (!nav.equals("quit"));

        System.exit(0);


    }
}
