package application.controllers;

import application.repositories.PersonRepository;
import application.repositories.PlaceRepository;
import application.resources.Person;
import application.resources.PersonPlace;
import application.resources.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Controller for requests to urls that start with '/person'
 * By convention this endpoint should be 'people' but I'm not going to
 * be pedantic about it.
 * Created by erica on 1/7/17.
 */

@RepositoryRestController
public class PersonController {

    //counter for new person ids
    private final AtomicLong counter = new AtomicLong();

    //connect to person repository
    @Autowired
    private PersonRepository personRepository;

    //connect to place repository
    @Autowired
    private PlaceRepository placeRepository;


    //Method getPerson takes a string id, given in the url, and returns a person object
    //that is associated with that id.
    @RequestMapping(method=GET, value="/person/{id}")
    @ResponseBody
    public Person getPerson(@PathVariable(value="id") long id){
        //find the person with the given id
        Person person =  personRepository.getOne(id);
        return person;
    }

    //Method makePerson takes a string name for the person, given as a parameter, and
    //returns a new person object that has been given that name.
    @RequestMapping(method=POST, value="/person")
    @ResponseBody
    public Person makePerson(@RequestParam(value="name") String name){
        //create new person and populate with the correct name and id
        Person newPerson = new Person();
        newPerson.setId(counter.incrementAndGet());
        newPerson.setName(name);
        //save new stuff
        personRepository.save(newPerson);

        return newPerson;
    }

    //Method connectPersonAndPlace takes the ids of a single person and a single place, given
    //in the url and returns an object that has the id of both the person and the place in it.
    //Also updates list of people for the place, and list of places for the person.
    @RequestMapping(method=POST, value="/person/{personId}/place/{placeId}")
    @ResponseBody
    public PersonPlace connectPersonAndPlace(@PathVariable(value="personId") long personId,
                                             @PathVariable(value="placeId") long placeId){
        //find the person and place that go with the given ids
        Person person = personRepository.getOne(personId);
        Place place = placeRepository.getOne(placeId);
        //add the place to the person's list of places
        List<Place> currentPlaces = person.getPlaces();
        currentPlaces.add(place);
        person.setPlaces(currentPlaces);
        //add the person to the place's list of people
        List<Person> currentPeople = place.getPeople();
        currentPeople.add(person);
        place.setPeople(currentPeople);
        //save the new stuff
        personRepository.save(person);
        placeRepository.save(place);
        //create the required object
        return new PersonPlace(person.getId(), place.getId());
    }

    @RequestMapping(method=GET, value="person/{id}/place")
    @ResponseBody
    public List<Place> getPlacesForPerson(@PathVariable(value="id") long id){
        Person person =  personRepository.getOne(Long.valueOf(id));
        return person.getPlaces();
    }
}
