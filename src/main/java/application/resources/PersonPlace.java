package application.resources;

/**
 * Dummy object for means of presentation.
 * Created by erica on 1/9/17.
 */
public class PersonPlace {

    private long personId;

    private long placeId;

    public PersonPlace(long personId, long placeId){
        this.personId = personId;
        this.placeId = placeId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }
}
