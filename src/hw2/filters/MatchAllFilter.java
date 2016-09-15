package hw2.filters;

import hw1.doa.QuakeEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasa2 on 9/15/2016.
 */
public class MatchAllFilter implements Filter{

    public List<Filter> filters;

    public MatchAllFilter(){
        filters = new ArrayList<>();
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {

        for(Filter filter : filters){
            if(!filter.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
}
