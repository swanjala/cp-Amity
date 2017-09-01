package com.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that conatains all the functionalities arounf people in Amity
 */

public class PersonOps {

    private List<Person> peopleList = new ArrayList<Person>();
    private List<String> Unallocated = new ArrayList<String>();
    private Person person = new Person();
    private StringBuilder sb = new StringBuilder();


    /* Method that add the person object to list */

    public List<Person> addPerson(String name, String category, String wants_accomodation, String roomName) {

        if (name == "" || category == "") {

            return null;
        }
        if (wants_accomodation == "") {

            person.setDefaultAccomodation("N");

        } else {

            person.setAccomodation(wants_accomodation);

        }
        person.setName(name);
        person.setCategory(category);
        person.setRoomAllocation(roomName);
        peopleList.add(person);


        return peopleList;
    }
   /* Method returns the list of pe ople who are saved in the application*/

    public String printPeople(List<Person> peopleList) {

        for (Person peopleData:peopleList
             ) {
            sb.append(" "+peopleData.getName()+" "+ peopleData.getCategory()+
                    peopleData.getCategory()+"\n");
        }

        return sb.toString();

    }

    public void printUnallocated() {

        for (int i = 0; i < peopleList.size() ; i++) {

            if (peopleList.get(i).getAccomodation()==""){
                System.out.println(peopleList.get(i).getName());
            }
        }


    }

    public String reallocatePersonRoom(List<Room> roomList, String personName, Room newRoom) {

	/* Reallocate person to new space */
        int personIndex = 0; // person field index;
        int roomIndex = 1;
        String message ="Reallocation successful";

        if (roomList.size() == 0) {
            message = " No rooms to reallocate";
            return message;
        }

        for (Room roomData: roomList
             ) {

            if (roomList.get(personIndex).toString() == personName) {


                if (roomList.get(roomIndex) == newRoom) {
                    message = "Cannot reallocate to the the same room";
                    return message;
                }
                // set the room allocation to a new value

                peopleList.get(personIndex).setRoomAllocation(newRoom.toString());

                return message;

            }


        }

    }
}