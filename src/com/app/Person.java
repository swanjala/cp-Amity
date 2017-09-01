package com.app;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing accessors and mutators for people in Amity.
 */

public class Person {


    private String name,category,roomName;
    private String wants_accomodation;
    List <String> personList = new ArrayList<String>();

    public void  Person(String name, String category, String wants_accomodation, String roomName){

        setName(name);
        setCategory(category);
        setAccomodation(wants_accomodation);
        setRoomAllocation(roomName);

    }

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

    public String getRoomName(){
        return roomName;
    }

    public String getCategory(){

        return category;

    }

    public String getAccomodation(){

        return wants_accomodation;
    }


}