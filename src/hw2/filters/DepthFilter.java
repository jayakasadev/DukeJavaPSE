package hw2.filters;

import hw1.doa.QuakeEntry;

/**
 * Created by kasa2 on 9/15/2016.
 */
public class DepthFilter implements Filter{

    private double min;
    private double max;

    public DepthFilter(double min, double max){
        this.max = max;
        this.min = min;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        double depth = qe.getDepth();
        if(depth >= min && depth <= max){
            return true;
        }
        return false;
    }
}
