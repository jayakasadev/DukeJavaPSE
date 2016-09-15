package hw1.filters;

/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import hw1.EarthQuakeParser;
import hw1.doa.Location;
import hw1.doa.QuakeEntry;

import java.util.*;

public class ClosestQuakes {

    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList();

        //code from lecture
        //optimize this for a faster runtime
        //paralellize?
        for(int b = 0; b < howMany; b++){
            int minIndex = 0;
            for(int a = 1; a < copy.size();a++){
                QuakeEntry entry = copy.get(a);
                Location location = entry.getLocation();
                if(location.distanceTo(current) < copy.get(minIndex).getLocation().distanceTo(current)){
                    minIndex = a;
                }
            }
            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "src/hw1/data/nov20quakedata.atom";
        String source = "src/hw1/data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size() + " quakes");

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
