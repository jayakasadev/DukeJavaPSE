package hw5.RandomText.Abstraction;

import java.util.List;
import java.util.Random;

/**
 * Created by kasa2 on 10/4/2016.
 */
public class MarkovFour extends AbstractMarkovModel{

    public MarkovFour() {
        super();
        order = 4;
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

        int index = myRandom.nextInt(myText.length()-4);
        //int index = 0;
        String key = myText.substring(index, index+4);
        sb.append(key);

        for(int k=0; k < numChars-4; k++){
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

}
