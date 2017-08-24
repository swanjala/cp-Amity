package com.app;


/**
 * Class that conatains all the functionalities arounf people in Amity
 */

public class PersonOps {

    private List<Person> peopleList = new ArrayList<Person>();

    /* Method that add the person object to list */
    public String addPerson() {
        String message = " Person added successfully";

        Person person = new Person();
        person.setName(name);
        person.setCategory(category);
        person.setAccomodation(wants_accomodation));
        peopleList.add(person);

        return message;
    }
   /* Method returns the list of pe ople who are saved in the application*/

    public String printPeople() {

        return peopleList;

    }

    public String printUnallocated() {
       /* Prints out people who have not been allocated accomodation*/
    }

    public void reallocatePersonRoom(String personName, String newRoom) {

	/* Reallocate person to new space */


        if (roomList.length == 0) {
            String message = " No rooms to reallocate";
            return message;
        }

        for (i = 0; i < roomList.length; i_++) {

            if (roomList.name == personName) {

                if (roomName = newRoom) {
                    message = "Cannot reallocate to the the same room"
                    return message;
                }

                roomName = newRoom;

            }
        }
    }
s
}