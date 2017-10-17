package com.app.fields;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that manages the data and functionality
 * of rooms
 */
public class Room {

    private List<String> roomNameList;
    private String roomName;
    private String category;
    private int capacity = 0;
    private List<String> room_occupants = new ArrayList<String>();
    public Collection<List<Room>> roomInfo = new ArrayList<List<Room>>();


    public void Room (){

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

        this.category = roomCategory;

        if (category.equals("OFFICE")){
          setCapacity(4);
        }else if (category.equals("LIVING")){
            setCapacity(6);
        }
    }
    public void setRoomAllocation(String roomName){

        this.roomName = roomName;
    }
    public void setRoomInfo (Collection <List<Room>> roomInfo){
        this.roomInfo.equals(roomInfo);
    }
    public Collection <List<Room>> getRoomInfo(){
        return roomInfo;
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
    public int getRoomCapacity(){

        return capacity;
    }

    public String getRoomAllocation(){

        return roomName;
    }

}