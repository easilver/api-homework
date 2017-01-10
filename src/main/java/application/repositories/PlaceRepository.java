package application.repositories;


import application.resources.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository used to abstractly query the h2 database in memory for place objects
 * Created by erica on 1/9/17.
 */

@RepositoryRestResource(path = "place")
public interface PlaceRepository extends CrudRepository<Place, Long> {

    Place getOne(@Param("id") long id);

}