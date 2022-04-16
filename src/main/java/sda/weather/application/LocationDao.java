package sda.weather.application;

public class LocationDao extends AbstractDao<Location>{

    //pozwoli korzystać z metod CRUD zaimplementowanych w AbstractDao
    public LocationDao(){
        super(Location.class);
    }

}
