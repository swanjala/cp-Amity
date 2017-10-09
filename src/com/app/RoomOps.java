package com.app;

import java.util.*;

/**
 * Class containing all the room functional operations.
 *
 */

public class RoomOps extends Room {

    ArrayList<List<Room>> roomListData = new ArrayList<List<Room>>();
    public List<Room> roomList = new ArrayList<Room>();
    private int capacity = 0;
    private String message;
    private Room room = new Room();

	public RoomOps(){

    }

    public RoomOps(String roomName, String roomCategory) {

            room.setRoomName(roomName);
            room.setRoomCategory(roomCategory);


            if (roomCategory.trim().equals("OFFICE")) {

                room.setCapacity(4);

            } else if(roomCategory.trim().equals("LIVING")) {

                room.setCapacity(6);

            }else {
                System.out.println("Enter the correct category");

            }
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

    public List<Room> addRoom(){

        roomList.add(room);
        return roomList;
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

    public static HashMap<String, String> shuffleBox(Collection<List<Room>> roomList) {

        List<String> livingRoomNames = new ArrayList<>();
        List<String> officeSpace = new ArrayList<>();
        List<Room> result = new ArrayList<>();

        HashMap<String, String> roomValues = new HashMap<>();

        Room room = new Room();
        Random randValue = new Random();

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

}