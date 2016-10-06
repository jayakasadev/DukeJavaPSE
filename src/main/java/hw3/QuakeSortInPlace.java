package hw3;
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class QuakeSortInPlace {
    //private static int count;
    public QuakeSortInPlace() {
        //count = 0;
    }

    public void sortByMagnitudeWithCheck(List<QuakeEntry> in){
        int passes = 0;
        if(!checkInSortedOrder(in)){
            for(int a = 0; a < in.size(); a++){
                int max = getLargestMagnitude(in, a);
                //System.out.println(max);
                QuakeEntry maxQE = in.get(max);
                QuakeEntry replace = in.get(a);
                in.set(max, replace);
                in.set(a,maxQE);
                passes++;
                if(checkInSortedOrder(in)){
                    break;
                }
            }
        }
        System.out.println("Passes " + passes);
    }

    public int getLargestMagnitude(List<QuakeEntry> quakeData, int from){
        for(int a = from+1; a < quakeData.size(); a++){
            if(quakeData.get(from).getMagnitude() > quakeData.get(a).getMagnitude()){
                from = a;
            }
        }
        return from;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(List<QuakeEntry> in){
        int passes = 0;
        int max = in.size()-1;
        if(!checkInSortedOrder(in)){
            //System.out.println();
            while(passes < max){
                onePassBubbleSortChecked(in, passes);
                passes++;
                if(checkInSortedOrder(in)){
                    //System.out.println("BREAKING");
                    break;
                }
            }
        }
        System.out.println("Passes " + passes);
    }

    public boolean checkInSortedOrder(List<QuakeEntry> quakes){
        /*
        System.out.println("\nChecking");
        for(QuakeEntry qe : quakes){
            System.out.println(qe);
        }
        */
        //System.out.println("STEPS");
        for(int a = 0; a < quakes.size()-1; a++){
            //System.out.println(quakes.get(a));
            if(quakes.get(a).getMagnitude() > quakes.get(a+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    public void onePassBubbleSortChecked(List<QuakeEntry> quakeData, int numSorted){
        int max = quakeData.size()-1-numSorted;
        for(int a = 0; a < max; a++){
            QuakeEntry next = quakeData.get(a+1);
            QuakeEntry curr = quakeData.get(a);
            if(curr.getMagnitude() > next.getMagnitude()){
                quakeData.set(a, next);
                quakeData.set(a+1,curr);
            }
        }
    }

    public void onePassBubbleSort(List<QuakeEntry> quakeData, int numSorted){
        int max = quakeData.size()-1-numSorted;
        for(int a = 0; a < max; a++){
            QuakeEntry next = quakeData.get(a+1);
            QuakeEntry curr = quakeData.get(a);
            if(curr.getMagnitude() < next.getMagnitude()){
                quakeData.set(a, next);
                quakeData.set(a+1,curr);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(List<QuakeEntry> in){
        int passes = 0;
        int max = in.size()-1;
        while(passes < max){
            /*
            for(QuakeEntry qe : in){
                System.out.println(qe);
            }
            System.out.println();
            */
            onePassBubbleSort(in, passes);
            passes++;
        }
    }

    public int getLargestDepth(List<QuakeEntry> quakeData, int from){
        for(int a = from+1; a < quakeData.size(); a++){
            if(quakeData.get(from).getDepth() < quakeData.get(a).getDepth()){
                from = a;
            }
        }
        return from;
    }

    public void sortByLargestDepth(List<QuakeEntry> quakeData){
        //sort in place using selection sort
        //System.out.println(quakeData.size());
        int passes = 0;
        for (int i=0; i< quakeData.size(); i++) {
            int max = getLargestDepth(quakeData,i);
            QuakeEntry qi = quakeData.get(i);
            QuakeEntry qmin = quakeData.get(max);
            quakeData.set(i,qmin);
            quakeData.set(max,qi);
            passes++;
            if(passes == 70){
                break;
            }
        }
        System.out.println("Passes " + passes);
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        //lecture code
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       //lecture code
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "src/hw3/data/nov20quakedatasmall.atom";
        //String source = "src/hw3/data/nov20quakedata.atom";
        //String source = "src/hw3/data/earthquakeDataSampleSix2.atom";
        //String source = "src/hw3/data/earthquakeDataSampleSix1.atom";
        //String source = "src/hw3/data/earthQuakeDataDec6sample1.atom";
        //String source = "src/main/java/hw3/data/earthQuakeDataDec6sample2.atom";
        //String source = "src/main/java/hw3/data/earthQuakeDataWeekDec6sample2.atom";
        String source = "src/main/java/hw3/data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

	public static void main(String[] args){
        QuakeSortInPlace q = new QuakeSortInPlace();
        q.testSort();
    }
}
