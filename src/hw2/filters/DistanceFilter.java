package hw2.filters;

import hw1.doa.Location;
import hw1.doa.QuakeEntry;

/**
 * Created by kasa2 on 9/15/2016.
 */
public class DistanceFilter implements Filter{

    private Location location;
    private float maxDistance;

    public DistanceFilter(Location location, float maxDistance){
        this.location = location;
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        float distance = qe.getLocation().distanceTo(location);
        if(distance < maxDistance){
            return true;
        }
        return false;
    }
}
