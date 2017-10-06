package com.app;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class containing accessors and mutators for people in Amity.
 */

public class Person extends Room {

    private String name,category,allocatedRoom,allocatedOffice;
    private String wants_accomodation;
    List <String> personList = new ArrayList<String>();
    Collection <List<Person>> peopleCollection = new ArrayList<>();


    public void setName(String name){

        this.name = name ;
    }

    public void setCategory(String category){
        this.category = category;
    }
    public void setAllPeopleCollection(Collection<List<Person>> peopleInfo){
        this.peopleCollection = peopleInfo;
    }
    public Collection<List<Person>> getAllPeopleCollection(){
        return peopleCollection;
    }

    public void setAccomodationRequest(String wants_accomodation){

        this.wants_accomodation = wants_accomodation;
    }
    public void setAccomodationRoom(String roomName){

        this.allocatedRoom = roomName;
    }
    public void setOfficeRoom(String roomName){

        this.allocatedOffice = roomName;
    }
    public String getAccomodationRoom(){
        return allocatedRoom;
    }
    public String getAllocatedOffice(){
        return allocatedOffice;
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