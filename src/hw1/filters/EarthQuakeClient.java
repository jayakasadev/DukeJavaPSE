package hw1.filters;

import hw1.EarthQuakeParser;
import hw1.doa.Location;
import hw1.doa.QuakeEntry;

import java.util.*;
//import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        //code from lecture
        for(QuakeEntry qe: quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        //hw
        for(QuakeEntry qe : quakeData){
            Location location = qe.getLocation();
            //assuming that distanceMax was in kilometers
            if(location.distanceTo(from) < distMax*1000){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> out = new ArrayList<>();
        for(QuakeEntry qe : quakeData){
            double depth = qe.getDepth();
            if(depth > minDepth && depth < maxDepth){
                out.add(qe);
            }
        }
        return out;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> out = new ArrayList<>();

        for(QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            if(where.equals("start") && title.startsWith(phrase)){
                //System.out.println("start " + title);
                out.add(qe);
            }
            else if(where.equals("end") && title.endsWith(phrase)){
                //System.out.println("end " + title);
                out.add(qe);
            }
            else if(where.equals("any") && title.contains(phrase)){
                //System.out.println("any " + title);
                out.add(qe);
            }
        }
        return out;
    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "src/hw1/data/nov20quakedata.atom";
        String source = "src/hw1/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        //Added from watching lecture
        /*
        for(QuakeEntry qe : list){
            if(qe.getMagnitude() > 5.0){
                System.out.println(qe);
            }
        }
        */

        list = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        System.out.println("Found " + list.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "src/hw1/data/nov20quakedata.atom";
        String source = "src/hw1/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        /*
        //hw
        int count = 0;
        for(QuakeEntry qe : list){
            //System.out.println(qe.getInfo());
            Location location = qe.getLocation();
            float distance = location.distanceTo(city);
            //System.out.println(distance);
            //distanceTo measures the distance in meters
            //convert 1000km to meters
            if(distance <= 1000000){
                System.out.println((distance/1000) + " " + qe.getInfo());
                count++;
            }
        }
        System.out.println("Found " + count + " quakes that match that criteria");
        */

        list = filterByDistanceFrom(list, 1000, city);
        for(QuakeEntry qe : list){
            float distance = qe.getLocation().distanceTo(city)/1000;
            System.out.println(distance + " " + qe.getInfo());
        }
        System.out.println("Found " + list.size() + " quakes that match that criteria");
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/hw1/data/nov20quakedata.atom";
        //String source = "src/hw1/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");
        System.out.println("Find quakes with depth between -10000.0 and -5000.0");

        //list = filterByDepth(list, -10000.0, -5000.0);
        list = filterByDepth(list, -8000.0, -5000.0);

        list.stream().forEach(qe -> System.out.println(qe));

        System.out.println("Found " + list.size() + " quakes that match the criteria");
    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "src/hw1/data/nov20quakedatasmall.atom";
        String source = "src/hw1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");

        //list = filterByPhrase(list, "end", "California");
        //list = filterByPhrase(list, "any", "Can");
        list = filterByPhrase(list, "start", "Explosion");
        //list = filterByPhrase(list, "any", "Creek");

        list.stream().forEach(qe -> System.out.println(qe));

        //System.out.println("Found " + list.size() + " quakes that match California at end");
        //System.out.println("Found " + list.size() + " quakes that match Can at any");
        System.out.println("Found " + list.size() + " quakes that match Explosion at start");
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/hw1/data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
