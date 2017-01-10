package application.repositories;

import application.resources.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
/**
 * Repository used to abstractly query the h2 database in memory for person objects
 * Created by erica on 1/8/17.
 */

@RepositoryRestResource(path = "person")
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person getOne(@Param("id") long id);

}