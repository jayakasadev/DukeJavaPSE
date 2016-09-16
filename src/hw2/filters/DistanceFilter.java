package hw2.filters;

import hw1.doa.Location;
import hw1.doa.QuakeEntry;

/**
 * Created by kasa2 on 9/15/2016.
 */
public class DistanceFilter implements Filter{

    private Location location;
    private double maxDistance;
    private String name;

    public DistanceFilter(Location location, double maxDistance){
        this.location = location;
        this.maxDistance = maxDistance;
        name = "DistanceFilter";
    }

    public DistanceFilter(Location location, double maxDistance, String name){
        this.location = location;
        this.maxDistance = maxDistance;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        double distance = qe.getLocation().distanceTo(location);
        //System.out.println(distance);
        if(distance < maxDistance*1000){
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
