package com.app;
import com.app.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages the data and functionality
 * of rooms
 */
public class Room extends Person {

    private String roomName, category;
    private int capacity = 0;
    private List<String> room_occupants = new ArrayList<String>();

    // setting up a default constuctor

    public void Room (){

    }

    public void Room(String roomName, String category) {

        this.roomName = roomName;
        this.category = category;
    }

    /* Set room name */
    public void setRoomName(String roomName) {

        this.roomName = roomName;
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