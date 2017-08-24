package com.app;

/**
 * Class containing all the room functional operations.
 *
 */


public class RoomOps extends Person {
    private List<Room> roomList = new ArrayList<Room>();
    private String message;

	/* Method that adds a new room to the application*/

    public String addRoom(){

        String message = "New room has been added";

        Room room = new Room();

        room.setRoomName(roomName);
        room.setCategory(roomCategory);

        if (roomCategory =="OFFICE") {
            int capacity = 6;

            room.setCapacity(capacity);

        }else if(roomCategory=="LIVING"){
            int capacity = 4;

            room.setCapacity(capacity);

        } else {
            message = "This room category does not exist";
            return message;
        }

        room.setRoomName(roomName);
        room.setCategory(roomCategory);
        room.setCapacity(roomCapacity);

        roomList.add(room);

        return message;
    }


}