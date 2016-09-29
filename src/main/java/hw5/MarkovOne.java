package hw5;

import java.util.List;
import java.util.Random;

/**
 * Created by kasa2 on 9/28/2016.
 */
public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public List<Character> getFollows(String key){
        return null;
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
        for(int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }
}
