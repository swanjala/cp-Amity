package com.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that conatains all the functionalities arounf people in Amity
 */

public class PersonOps extends RoomOps {

    public  List<Person> peopleList =  new ArrayList<Person>();
    private Person person = new Person();
    private StringBuilder sb = new StringBuilder();

    public PersonOps(String name,String category, String wantsAccomodation, String roomAllocatedName){

        person.setName(name);
        person.setCategory(category);
        person.setAccomodationRequest("Y");
        person.setAccomodationRoom(roomAllocatedName);


    }
    /* Overload constractor with reallocation variables */

    public PersonOps() {

    }

    public PersonOps(String personName,String newRoom){

        person.setName(personName);
        person.setAccomodationRoom(newRoom);

    }

    public List<Person> addPerson() {

        peopleList.add(person);
        return peopleList;
    }


   /* Method returns the list of pe ople who are saved in the application*/

    public String printPeople(List<Person> peopleList) {

        for (Person peopleData:peopleList
             ) {
            sb.append(" "+peopleData.getName()+" "+ peopleData.getCategory()+
                    peopleData.getAccomodationRequest()+" " +peopleData.getAccomodationRoom());
        }

        return sb.toString();

    }

    public void printUnallocated() {

        for (int i = 0; i < peopleList.size() ; i++) {

            if (peopleList.get(i).getAccomodationRoom()==""){
                System.out.println(peopleList.get(i).getName() + "\n");
            }
        }

    }

}