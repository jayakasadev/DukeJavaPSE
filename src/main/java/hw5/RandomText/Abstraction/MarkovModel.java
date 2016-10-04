package hw5.RandomText.Abstraction;

import java.util.List;
import java.util.Random;

/**
 * Created by kasa2 on 10/4/2016.
 */
public class MarkovModel extends AbstractMarkovModel{

    public MarkovModel(int order) {
        super();
        this.order = order;
    }

    @Override
    public void setTraining(String s){
        myText = s.trim();
    }

    @Override
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

    @Override
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    @Override
    public String toString() {
        return "MarkovModel Order " + order;
    }
}
