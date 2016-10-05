package hw5.WordNGrams.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kasa2 on 10/4/2016.
 */
public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];

        sb.append(key1);
        sb.append(" ");

        sb.append(key2);
        sb.append(" ");

        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);

            //System.out.println(follows);

            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        int index = 0;
        //System.out.println("getFollows " + key);
        int count = 0;
        ArrayList<String> follows = new ArrayList<>();
        while(index < myText.length){
            //System.out.println(index);
            index = indexOf(myText, key1 , key2, index);
            if(index == -1){
                break;
            }
            ++index;
            if(++index < myText.length)
                follows.add(myText[index]);
        }

        return follows;
    }

    private int indexOf(String[] words, String target1, String target2, int start){
        while(start < words.length){
            if(words[start].equals(target1)){
                if(words[start+1].equals(target2)){
                    return start;
                }
            }
            start++;
        }
        return -1;
    }

    public void testIndexOf(){
        String[] words = "this is just a test yes this is a simple test".split(" ");
        String target1 = "this";
        String target2 = "is";

        myText = words;
        List<String> list = getFollows(target1, target2);
        System.out.println(list);

        target1 = "just";
        target2 = "a";

        myText = words;
        list = getFollows(target1, target2);
        System.out.println(list);

    }

    public static void main(String[] args){
        MarkovWordTwo m = new MarkovWordTwo();
        m.testIndexOf();
    }
}
