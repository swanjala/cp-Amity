package com.app;


import java.util.ArrayList;
import java.util.List;

/**
 * Class that conatains all the functionalities arounf people in Amity
 */

public class PersonOps {

    private List<Person> peopleList = new ArrayList<Person>();
    private Person person = new Person();
    private StringBuilder sb = new StringBuilder();

    /* Method that add the person object to list */

    public List<Person> addPerson(String name, String category, String wants_accomodation, String roomName) {

        String message = "Person has been added successfully";

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

        for (int i = 0; i < peopleList.size(); i++) {
            sb.append(" "+ peopleList.get(i));

        }
        return sb.toString();

    }

    public String printUnallocated() {
       /* Prints out people who have not been allocated accomodation*/

        return "";
    }

    public void reallocatePersonRoom(List<Room> roomList, String personName, Room newRoom) {

	/* Reallocate person to new space */
        int personIndex = 0; // person field index;
        int roomIndex = 1;

        if (roomList.size() == 0) {
            String message = " No rooms to reallocate";
            System.out.print(message);
            return;
        }

        for (Room roomData: roomList
             ) {

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