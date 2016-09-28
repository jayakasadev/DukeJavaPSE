package hw4;

import java.util.Comparator;

/**
 * Created by kasa2 on 9/28/2016.
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    @Override
    public int compare(QuakeEntry o1, QuakeEntry o2) {
        int index1 = o1.getInfo().lastIndexOf(" ");
        int index2 = o2.getInfo().lastIndexOf(" ");

        String sb1 = o1.getInfo().substring(index1);
        String sb2 = o2.getInfo().substring(index2);

        System.out.println(sb1);
        System.out.println(sb2);

        int compare = sb1.compareTo(sb2);

        if(compare == 0){
            if(o1.getMagnitude() < o2.getMagnitude()){
                return -1;
            }
            if(o1.getMagnitude() > o2.getMagnitude()){
                return 1;
            }
        }

        return compare;
    }
}
