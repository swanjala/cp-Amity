package com.app.Ops;

import com.app.App.VaribleParser;
import com.app.Fields.Person;
import com.app.Fields.Room;

import java.util.*;

/**
 * Class that conatains all the functionalities arounf people in Amity
 */

public class PersonOps extends VaribleParser{

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