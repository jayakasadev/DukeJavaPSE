package hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<Character> list = new ArrayList<>();

        String regex = "[a-zA-Z]+[.?!]*";
        String temp = myText;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(temp);

        while(matcher.find()){
            StringBuffer text = new StringBuffer(matcher.group());
            int index = text.indexOf(key);
            //System.out.println(text);
            while(index != -1){
                //System.out.println(index + " " + text);

                char character = ' ';
                if(index + key.length() < text.length()){
                    character = text.charAt(index+key.length());
                }
                list.add(character);
                text.deleteCharAt(index);
                index = text.indexOf(key);
            }
        }

        //list.stream().forEach(c -> System.out.println(c));

        //System.out.println();

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
        for(int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            String key = myText.substring(k, k+numChars);
            List<Character> characters = getFollows(key);
            sb.append(characters.get(index));
        }

        return sb.toString();
    }
}
