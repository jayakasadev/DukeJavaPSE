package hw5.RandomText.Generating;

import java.util.ArrayList;
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

    public List<String> getFollows(String key){
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < myText.length() - 1; i++) {
            if (key.equals(myText.substring(i, i + 1))){
                list.add(myText.substring(i + 1, i + 2));
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
}
