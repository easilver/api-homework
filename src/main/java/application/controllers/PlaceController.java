package application.controllers;

import application.repositories.PlaceRepository;
import application.resources.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Controller for requests to urls that start with '/place'
 * By convention this endpoint should be 'places' but I'm not going to
 * be pedantic about it
 * Created by erica on 1/8/17.
 */
@RestController
public class PlaceController {

    //counter for new place ids
    private final AtomicLong counter = new AtomicLong();

    //connect to place repository
    @Autowired
    private PlaceRepository placeRepository;

    //Method getPlace takes a string id, given in the url, and returns a place object
    //that is associated with that id.
    @RequestMapping(method=GET, value="/place/{id}")
    @ResponseBody
    public Place getPlace(@PathVariable(value="id") long id){
        //find the place with the given id
        Place place = placeRepository.getOne(Long.valueOf(id));
        return place;
    }

    //Method makePlace takes a string name for the place, and values for the place's
    //coordinates (latitude and longitude), given as parameters, and returns a new
    //place object that has the name and coordinates given.
    @RequestMapping(method=POST, value="/place")
    @ResponseBody
    public Place makePlace(@RequestParam(value="name") String name,
                           @RequestParam(value="latitude") double latitude,
                           @RequestParam(value="longitude") double longitude){
        //create new place and populate with given information
        Place place = new Place();
        place.setId(counter.incrementAndGet());
        place.setName(name);
        place.setLatitude(latitude);
        place.setLongitude(longitude);
        //save the new stuff
        placeRepository.save(place);
        return place;
    }

}
