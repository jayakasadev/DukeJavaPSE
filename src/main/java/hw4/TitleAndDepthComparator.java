package hw4;

import hw3.QuakeEntry;

import java.util.Comparator;

/**
 * Created by kasa2 on 9/28/2016.
 */
public class TitleAndDepthComparator implements Comparator<hw4.QuakeEntry>{

    @Override
    public int compare(hw4.QuakeEntry o1, hw4.QuakeEntry o2) {
        int compare = o1.getInfo().compareTo(o2.getInfo());

        //System.out.println(o1.getInfo());
        //System.out.println(o2.getInfo());

        if(compare == 0){
            //tiebreak
            if(o1.getDepth() < o2.getDepth()){
                return -1;
            }
            else if(o1.getDepth() > o2.getDepth()){
                return 1;
            }
        }
        return compare;
    }
}