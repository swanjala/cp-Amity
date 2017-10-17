package com.app.Ops;

import com.app.Fields.Room;

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


    public List<Room> addRoom(){

        roomList.add(room);
        return roomList;
    }

}
