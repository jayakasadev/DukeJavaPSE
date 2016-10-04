package hw5.part2;

import java.util.List;
import java.util.Random;

/**
 * Created by kasa2 on 9/28/2016.
 */
public class MarkovOne extends AbstractMarkovModel{

    public MarkovOne() {
        super();
        order = 1;
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

        int index = myRandom.nextInt(myText.length()-1);
        //int index = 0;
        String key = myText.substring(index, index+1);
        sb.append(key);

        for(int k=0; k < numChars-1; k++){
            List<String> list = getFollows(key);
            if(list.size() == 0){
                break;
            }
            index = myRandom.nextInt(list.size());
            key = "" + list.get(index);
            sb.append(key);
        }

        return sb.toString();
    }

    @Override
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
}
