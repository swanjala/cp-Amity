package com.app;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages the data and functionality
 * of rooms
 */
public class Room extends Person {

    private List<String> roomNameList;
    private String [] roomName= new String[100];
    private String category;
    private int capacity = 0;
    private List<String> room_occupants = new ArrayList<String>();


    public void Room (){

    }

    /* Set room name */
    public void setRoomName(List<String> roomNameList) {

        for (int i = 0; i <roomNameList.size() ; i++) {
            this.roomName[i] = roomNameList.get(i);
        }

    }

    /* Set the room capacity */
    public void setCapacity(int capacity) {

        this.capacity = capacity;
    }
    /* Set room category*/

    public void setRoomCategory(String roomCategory) {

        this.category= roomCategory;
    }

    /* Get room name*/
    public String getRoomName() {

        return roomName;
    }
	/* Get room category*/

    public String getRoomCategory() {
        return category;
    }

    /* Get room capacity*/
    public int roomCapacity(){

        return capacity;
    }

}