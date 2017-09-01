package com.app;
import java.util.ArrayList;
import java.util.List;

/**
 * Class constaing data states for people in Amity.
 */

public class Person {


    private String name,category,roomName;
    private String wants_accomodation;
    List <String> personList = new ArrayList<String>();

 /* Contructor function for the initialioskatin of data items */


    public void  Person(String name, String category, String wants_accomodation, String roomName){

        setName(name);
        setCategory(category);
        setAccomodation(wants_accomodation);
        setRoomAllocation(roomName);

    }

 /* Sets up the users name*/

    public void setName(String name){

        this.name = name ;
    }

    public void setCategory(String category){

        this.category = category;
    }

    public void setAccomodation(String wants_accomodation){

        this.wants_accomodation = wants_accomodation;
    }

    public void setRoomAllocation(String roomName){

        this.roomName = roomName;

    }

    public String getName (){

        return name;
    }

 /* Gets the room a fellow is allocated*/

    public String getRoomName(){
//
        return roomName;
    }

    /* Gets the persons category*/
    public String getCategory(){

        return category;

    }
 /* Gets the persons accomdation preference*/

    public String getAccomodation(){

        return wants_accomodation;
    }

 /* Method to add a new person to the application */

}