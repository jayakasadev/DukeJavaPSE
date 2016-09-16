package hw2.filters;

import hw1.doa.QuakeEntry;

/**
 * Created by kasa2 on 9/15/2016.
 */
public class MagnitudeFilter implements Filter{

    private double min;
    private double max;
    private String name;

    public MagnitudeFilter(double min, double max){
        this.max = max;
        this.min = min;
        name = "MagnitudeFilter";
    }

    public MagnitudeFilter(double min, double max, String name){
        this.max = max;
        this.min = min;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        double mag = qe.getMagnitude();
        if(mag >= min && mag <= max){
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
