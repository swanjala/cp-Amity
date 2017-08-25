package com.app;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all the room functional operations.
 *
 */


public class RoomOps extends Person {

    private List<Room> roomList = new ArrayList<Room>();
    private int capacity = 0;
    private String message;

	/* Method that adds a new room to the application*/

    public String addRoom(String roomName, String roomCategory){

        String message = "New room has been added";

        Room room = new Room();

        room.setRoomName(roomName);
        room.setCategory(roomCategory);

        if (roomCategory =="OFFICE") {
            capacity = 6;

            room.setCapacity(capacity);

        }else if(roomCategory=="LIVING"){
            capacity = 4;

            room.setCapacity(capacity);

        } else {
            message = "This room category does not exist";
            return message;
        }

        room.setRoomName(roomName);
        room.setCategory(roomCategory);
        room.setCapacity(capacity);
        roomList.add(room);

        return message;
    }


}