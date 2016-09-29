package hw1.filters;

import hw1.EarthQuakeParser;
import hw1.doa.QuakeEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by kasa2 on 9/15/2016.
 */
public class LargestQuakes {
    public LargestQuakes(){
    }

    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "src/hw1/data/nov20quakedatasmall.atom";
        String source = "src/hw1/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        System.out.println("read data for " + list.size() + " quakes");

        System.out.println("The largest earthquake was at " + list.get(indexOfLargest(list)));

        list = getLargest(list, 5);

        System.out.println("\nThe 5 largest quakes");
        list.stream().forEach(qe -> System.out.println(qe));
    }

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int index = 0;
        for(int a = 1; a < quakeData.size(); a++){
            if(quakeData.get(index).getMagnitude() < quakeData.get(a).getMagnitude()){
                index = a;
            }
        }
        return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){

        Collections.sort(quakeData, new Comparator<QuakeEntry>() {
            @Override
            public int compare(QuakeEntry o1, QuakeEntry o2) {
                if(o1.getMagnitude() > o2.getMagnitude()){
                    return -1;
                }
                else if(o1.getMagnitude() < o2.getMagnitude()){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });

        /*
        for(QuakeEntry qe : quakeData){
            System.out.println(qe.getMagnitude());
        }
        */

        ArrayList<QuakeEntry> out = new ArrayList<>();

        for(int a = 0; a < howMany; a++){
            out.add(quakeData.get(a));
        }

        return out;
    }
}
