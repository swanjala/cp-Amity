package com.app;


import java.util.ArrayList;
import java.util.List;

/**
 * Class that conatains all the functionalities arounf people in Amity
 */

public class PersonOps {

    private List<Person> peopleList = new ArrayList<Person>();
    private Person person = new Person();

    /* Method that add the person object to list */
    public String addPerson(String name, String category, String wants_accomodation, String roomName) {

        String message = "Person has been added successfully";

        if (name == "" || category == "") {
            message = "Ensure that you enter both name and category";
            return message;
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


        return message;
    }
   /* Method returns the list of pe ople who are saved in the application*/

    public String printPeople() {

        return "";

    }

    public String printUnallocated() {
       /* Prints out people who have not been allocated accomodation*/

        return "";
    }

    public void reallocatePersonRoom(List<Person> roomList, String personName, Person newRoom) {

	/* Reallocate person to new space */
        int personIndex = 0; // person field index;
        int roomIndex = 1;

        if (roomList.size() == 0) {
            String message = " No rooms to reallocate";
            System.out.print(message);
            return;
        }

        for (int i = 0; i < roomList.size(); i++) {

            if (roomList.get(personIndex).toString() == personName) {


                if (roomList.get(roomIndex) == newRoom) {
                    String message = "Cannot reallocate to the the same room";
                    System.out.println(message);
                }
                // set the room allocation to a new value

                roomList.set(1, newRoom);

            }
        }
    }
}