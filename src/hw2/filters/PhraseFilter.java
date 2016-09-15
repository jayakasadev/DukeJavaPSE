package hw2.filters;

import hw1.doa.QuakeEntry;

/**
 * Created by kasa2 on 9/15/2016.
 */
public class PhraseFilter implements Filter{

    private String type;
    private String phrase;

    public PhraseFilter(String type, String phrase){
        this.type = type;
        this.phrase = phrase;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if(type.equals("start") && title.startsWith(phrase)){
            return true;
        }
        else if(type.equals("end") && title.endsWith(phrase)){
            return true;
        }
        else if(type.equals("any") && title.contains(phrase)){
            return true;
        }
        return false;
    }
}
