package com.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that conatains all the functionalities arounf people in Amity
 */

public class PersonOps {

    public  List<Person> peopleList = new ArrayList<Person>();
    private List<String> Unallocated = new ArrayList<String>();
    private Person person = new Person();
    private StringBuilder sb = new StringBuilder();
    private List<Room> roomList = new ArrayList<Room>();


    public List<Person> addPerson(String name, String category, String wants_accomodation) {

        //
        String roomName = "HOGWARTS";

        if (name == "" || category == "") {

            return null;
        }
        if (wants_accomodation == "") {

            System.out.println("Does not want accomodation");

        } else {

            person.setAccomodation(wants_accomodation);

        }
        person.setName(name);
        person.setCategory(category);
        person.setRoomAllocation(roomName);
        peopleList.add(person);

        for (int i = 0; i < peopleList.size(); i++) {
            System.out.println(peopleList.get(i).getName().toString() +" "+ peopleList.get(i).getCategory().toString());
        }


        return peopleList;
    }
   /* Method returns the list of pe ople who are saved in the application*/

    public String printPeople(List<Person> peopleList) {

        for (Person peopleData:peopleList
             ) {
            sb.append(" "+peopleData.getName()+" "+ peopleData.getCategory()+
                    peopleData.getAccomodation()+"\n");
        }

        return sb.toString();

    }

    public void printUnallocated() {

        for (int i = 0; i < peopleList.size() ; i++) {

            if (peopleList.get(i).getAccomodation()==""){
                System.out.println(peopleList.get(i).getName() + "\n");
            }
        }

    }
    /* Method reallocates person from one room to another */
    public String reallocatePersonRoom(String personName, String newRoom) {

        int personIndex = 0;
        int roomIndex = 1;
        String message ="Reallocation successful";

        if (roomList.size() == 0) {
            message = " No rooms to reallocate";
            return message;
        }

        for (Room roomData: roomList
             ) {

            if (roomList.get(personIndex).toString() == personName) {


                if (roomList.get(roomIndex).toString() == newRoom) {
                    message = "Cannot reallocate to the the same room";
                    return message;
                }

                peopleList.get(personIndex).setRoomAllocation(newRoom);

            }

        }

        return message;

    }
}