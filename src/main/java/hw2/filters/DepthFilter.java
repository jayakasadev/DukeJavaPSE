package hw2.filters;

import hw1.doa.QuakeEntry;

/**
 * Created by kasa2 on 9/15/2016.
 */
public class DepthFilter implements Filter{

    private double min;
    private double max;
    private String name;

    public DepthFilter(double min, double max){
        this.max = max;
        this.min = min;
        name = "DepthFilter";
    }

    public DepthFilter(double min, double max, String name){
        this.max = max;
        this.min = min;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        double depth = qe.getDepth();
        if(depth >= min && depth <= max){
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
