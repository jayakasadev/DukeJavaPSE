package hw5.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kasa2 on 10/4/2016.
 */
public class MarkovModel {

    private String myText;
    private Random myRandom;
    private int order;

    public MarkovModel(int order) {
        myRandom = new Random();
        this.order = order;
    }

    public List<String> getFollows(String key){
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < myText.length() - order; i++) {
            if (key.equals(myText.substring(i, i + order))){
                list.add(myText.substring(i + order, i + order + 1));
            }
        }

        return list;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length()-order);
        //int index = 0;
        String key = myText.substring(index, index+order);
        sb.append(key);

        for(int k=0; k < numChars-order; k++){
            List<String> list = getFollows(key);
            if(list.size() == 0){
                break;
            }
            index = myRandom.nextInt(list.size());
            String temp = "" + list.get(index);
            sb.append(temp);
            key = key.substring(1) + temp;
        }

        return sb.toString();
    }
}
