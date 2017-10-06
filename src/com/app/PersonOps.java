package com.app;

import com.oracle.tools.packager.Log;

import java.util.*;

/**
 * Class that conatains all the functionalities arounf people in Amity
 */

public class PersonOps extends RoomOps {

    public  List<Person> peopleList =  new ArrayList<Person>();
    private Person person = new Person();
    private StringBuilder sb = new StringBuilder();

    public PersonOps(String name,String category, String wantsAccomodation, String roomAllocatedName,String officeSpace){

        person.setName(name);
        person.setCategory(category);
        person.setAccomodationRequest("Y");
        person.setAccomodationRoom(roomAllocatedName);
        person.setOfficeRoom(officeSpace);


    }

    public PersonOps() {

    }

    public PersonOps(String personName, String newRoom, Collection<List<Person>> personInfo){

        person.setName(personName);
        person.setAccomodationRoom(newRoom);
        person.setAllPeopleCollection(personInfo);

    }

    public PersonOps(String userInput, Collection<List<Person>> personInfo, Collection<List<Room>> roomInfo){

        StringTokenizer addPersonST = new StringTokenizer(userInput.substring(10), " ");

        List<Person> varList  = new ArrayList<>();

        int tokenCounter = addPersonST.countTokens();

        if (tokenCounter < 3) {

            System.out.printf( "Use this format \n" +
                    "Add Person <First Name> <Second Name> <Category> <Wants Accomodation> ");

        } else if (tokenCounter == 3) {

            String navigationStringDefault =userInput.substring(10).concat("N");
            addPersonST = new StringTokenizer(navigationStringDefault);

            varList = addPersonTokenizer(addPersonST,roomInfo);


        } else {

            varList = addPersonTokenizer(addPersonST, roomInfo);
        }

        person.setName(varList.get(0).getName());
        person.setCategory(varList.get(0).getCategory());
        person.setAccomodationRequest(varList.get(0).getAccomodationRequest());
        person.setAccomodationRoom(varList.get(0).getAccomodationRoom());
        person.setOfficeRoom(varList.get(0).getAllocatedOffice());

    }

    /**
     * addPersonTokenizer: This method uses a space delimiter to break
     * down an input String entered in Commandline User Interface and parses
     * it into an array of usablevariables that adds a person to the application.
     */

    private static List<Person> addPersonTokenizer(StringTokenizer addPersonST, Collection<List<Room>> roomInfo){

        Room room = new Room();
        Person personVars = new Person();
        StringBuilder nameSb = new StringBuilder();
        Iterator<List<Room>> itr = roomInfo.iterator();
        List<Room> list;
        List<Room> roomNames = new ArrayList<>();
        List<Person> addPersonVarObject = new ArrayList<Person>();
        HashMap<String, String> randomizedName = new HashMap<>();

        while (addPersonST.hasMoreTokens()) {

            for (int i = 0; i < 2; i++) {
                String nameVal = addPersonST.nextToken();
                nameSb.append(nameVal + " ");

            }

            String Category = addPersonST.nextToken();
            String accomodation = addPersonST.nextToken().toUpperCase();
            personVars.setName(nameSb.toString().trim());
            personVars.setCategory(Category);
            personVars.setAccomodationRequest(accomodation);

        }

        randomizedName = shuffleBox(roomInfo);

        if (personVars.getAccomodationRequest().equals("Y") &&
                !personVars.getCategory().equals("STAFF")) {

            randomizedName = shuffleBox(roomInfo);
            personVars.setAccomodationRoom(randomizedName.get("Living"));
            personVars.setOfficeRoom(randomizedName.get("Office"));

        } else if (personVars.getAccomodationRequest().toUpperCase().trim().equals("N") &&
                personVars.getCategory().trim().equals("FELLOW")) {

            personVars.setAccomodationRoom("None");
            personVars.setOfficeRoom(randomizedName.get("Office"));

        } else if (personVars.getCategory().equals("STAFF")) {

            personVars.setAccomodationRoom("None");
            personVars.setOfficeRoom(randomizedName.get("Office"));
        }


        addPersonVarObject.add(personVars);


        return addPersonVarObject;
    }

    public List<Person> addPerson() {

        peopleList.add(person);
        return peopleList;
    }

    /**
     * Reallocates a person's accomodation space
     *
     */

    public String reallocatePerson(){

        Collection<List<Person>> allPeopleList = person.getAllPeopleCollection();
        Iterator<List<Person>> iterator = allPeopleList.iterator();
        while (iterator.hasNext()){
            List<Person> allPeopleData = iterator.next();
            if (allPeopleData.get(0).getName().equals(person.getName().trim())){
                allPeopleData.get(0).setAccomodationRoom(person.getAccomodationRoom());
                return "Reallocation Success";
            }
        }
        return "Reallocation Unsuccessful";
    }


    /**
     * Prints the list of people in the application
     * @param allPeopleList
     * @return
     */


    public String printPeople(Collection <List<Person>> allPeopleList) {

        Iterator<List<Person>> iterator = allPeopleList.iterator();
        while (iterator.hasNext()){
            List<Person> allPeopleData = iterator.next();
            sb.append(allPeopleData.get(0).getName()+" "+allPeopleData.get(0).getCategory()+" "+
            allPeopleData.get(0).getAccomodationRoom());
            }

        return sb.toString();

    }

    public String printUnallocated(Collection<List<Person>> allPeopleList) {

        Iterator<List<Person>> iterator = allPeopleList.iterator();
        while (iterator.hasNext()){
            List<Person> allPeopleData = iterator.next();
            if (allPeopleData.get(0).getAccomodationRoom().equals("None")) {
                sb.append(allPeopleData.get(0).getName() + " " + allPeopleData.get(0).getCategory() + " " +
                        allPeopleData.get(0).getAccomodationRoom());
            }
        }

        return sb.toString();
    }

}