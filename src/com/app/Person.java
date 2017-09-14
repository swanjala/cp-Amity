package com.app;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing accessors and mutators for people in Amity.
 */

public class Person extends Room {

    private String name,category,allocatedRoom;
    private String wants_accomodation;
    List <String> personList = new ArrayList<String>();


    public void setName(String name){

        this.name = name ;
    }

    public void setCategory(String category){

        this.category = category;
    }

    public void setAccomodationRequest(String wants_accomodation){

        this.wants_accomodation = wants_accomodation;
    }
    public void setAccomodationRoom(String roomName){

        this.allocatedRoom = roomName;
    }
    public String getAccomodationRoom(){
        return allocatedRoom;
    }


    public String getName (){

        return name;
    }


    public String getCategory(){

        return category;

    }

    public String getAccomodationRequest(){

        return wants_accomodation;
    }

}