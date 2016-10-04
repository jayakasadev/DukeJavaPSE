package hw5.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kasa2 on 10/4/2016.
 */
abstract class AbstractMarkovModel implements IMarkovModel{

    String myText;
    Random myRandom;
    int order;

    AbstractMarkovModel(){
        myRandom = new Random();
    }

    List<String> getFollows(String key){
        //System.out.println("Parent");
        List<String> list = new ArrayList<>();

        for (int i = 0; i < myText.length() - order; i++) {
            if (key.equals(myText.substring(i, i + order))){
                list.add(myText.substring(i + order, i + order + 1));
            }
        }

        return list;
    }

}
