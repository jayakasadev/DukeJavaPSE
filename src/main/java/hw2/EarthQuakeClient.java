package hw2;

import java.util.*;
import hw1.EarthQuakeParser;
import hw1.doa.Location;
import hw1.doa.QuakeEntry;
import hw2.filters.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        Filter filter = new DistanceFilter(from, distMax);

        for(QuakeEntry qe : quakeData){
            //System.out.println(qe);
            if(filter.satisfies(qe)){
               answer.add(qe);
            }
        }

        return answer;
    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");



    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "src/hw1/data/nov20quakedatasmall.atom";
        String source = "src/hw1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODO

        list = filterByDistanceFrom(list, 1000, city);
        list.stream().forEach(qe -> System.out.println(qe));
        System.out.println("Found " + list.size() + " quakes that match that criteria");
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
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public void quakesWithFilter(){
        MatchAllFilter filter = new MatchAllFilter();

        //filter.addFilter(new MagnitudeFilter(4.0, 5.0));
        //filter.addFilter(new DepthFilter(-35000.0, -12000.0));

        //filter.addFilter(new DistanceFilter(new Location(35.42, 139.43), 10000));
        //filter.addFilter(new PhraseFilter("end", "Japan"));

        //filter.addFilter(new MagnitudeFilter(0.0, 2.0));
        //filter.addFilter(new DepthFilter(-100000.0, -10000.0));
        //filter.addFilter(new PhraseFilter("any", "a"));

        /*
        filter.addFilter(new MagnitudeFilter(0.0, 3.0));
        filter.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000));
        filter.addFilter(new PhraseFilter("any", "Ca"));
        */

        //filter.addFilter(new DistanceFilter(new Location(35.42, 139.43), 10000));
        //filter.addFilter(new PhraseFilter("end", "Japan"));

        //filter.addFilter(new MagnitudeFilter(4.0, 5.0));
        //filter.addFilter(new DepthFilter(-35000.0, -12000.0));

        /*
        filter.addFilter(new MagnitudeFilter(0.0, 2.0));
        filter.addFilter(new DepthFilter(-100000.0, -10000.0));
        filter.addFilter(new PhraseFilter("any", "a"));
        */

        filter.addFilter(new MagnitudeFilter(0.0, 3.0));
        filter.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000));
        filter.addFilter(new PhraseFilter("any", "Ca"));

        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "src/hw1/data/nov20quakedatasmall.atom";
        String source = "src/hw1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("Found " + list.size() + " quakes\n");

        //ArrayList<QuakeEntry> out = new ArrayList<>();

        int count = 0;
        for(QuakeEntry qe : list){
            if(filter.satisfies(qe)) {
                //out.add(qe);
                System.out.println(qe);
                count++;
            }
        }

        System.out.println("\nFound " + count + " quakes that match the criteria\n");

        System.out.println(filter.getName());

    }

    public static void main(String[] args){
        EarthQuakeClient client = new EarthQuakeClient();
        //client.closeToMe();
        client.quakesWithFilter();
    }
    
}
