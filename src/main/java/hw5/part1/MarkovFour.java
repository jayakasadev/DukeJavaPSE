package hw5.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kasa2 on 10/4/2016.
 */
public class MarkovFour {

    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
    }

    public List<String> getFollows(String key){
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < myText.length() - 4; i++) {
            if (key.equals(myText.substring(i, i + 4))){
                list.add(myText.substring(i + 4, i + 5));
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

}
