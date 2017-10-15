package com.app;

import java.sql.SQLException;
import java.util.*;

import static com.app.VaribleParser.*;

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
                "Print People \n" +
                "Print Room \n" +
                "Enter 'quit' to exit";

        return startText;
    }


    public static void printer(Collection<List<Person>> personInfo){

        Iterator<List<Person>> itr = personInfo.iterator();

        while (itr.hasNext()) {
            List<Person> element =itr.next();

            System.out.println(element.get(0).getName() + " " + element.get(0).getCategory() +
                    " \t" + element.get(0).getAccomodationRoom() + "\t \t"
                    + element.get(0).getAllocatedOffice());
        }

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

                PersonOps personOps = new PersonOps(navigationString,personInfo,roomInfo);

                List<Person> pData = personOps.addPerson();
                personInfo.add(pData);

                System.out.println("Name \t \t  Category \t Room \t Office");

                printer(personInfo);

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

            }
            if (navigationString.contains("Print People")){

                PersonOps personOps = new PersonOps();

                String peopleValue = personOps.printPeople(personInfo);

                System.out.println(peopleValue);
            }
            if (navigationString.contains("Print Room")){

                String printRoomVar = printRoomTokenizer(navigationString);

                Iterator<List<Person>> iterator = personInfo.iterator();

                System.out.println(printRoomVar + "\n" +"+++++++++++++++++++"+"\n");

                while (iterator.hasNext()){
                    List<Person> personElementList = iterator.next();

                   if (personElementList.get(0).getAllocatedOffice().equals(printRoomVar)){
                       System.out.println(personElementList.get(0).getName());
                   }
                }
            }

            else {
                System.out.println("Enter Correct Commands, see description for help");
            }
        } while (!navigationString.equals("quit"));

        System.exit(0);


    }
}
