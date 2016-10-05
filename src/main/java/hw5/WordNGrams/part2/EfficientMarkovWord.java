package hw5.WordNGrams.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kasa2 on 10/5/2016.
 */
public class EfficientMarkovWord implements  IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public EfficientMarkovWord(int order){
        myOrder = order;
        myRandom = new Random();
    }

    @Override
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    @Override
    public String getRandomText(int numWords){
        //System.out.println("getRandomText " + numWords);
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        String[] key = new String[myOrder];
        System.arraycopy(myText, index, key, 0, myOrder);
        WordGram w = new WordGram(key, 0, myOrder);
        sb.append(w);
        sb.append(" ");
        //int count = 0;
        for(int k=0; k < numWords-myOrder; k++){
            //System.out.println(w);
            List<String> follows = getFollows(w);
            //System.out.println(follows);

            if (follows.size() == 0) {
                break;
            }

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            w = w.shiftAdd(next);

            /*
            count++;
            if(count == 4){
                break;
            }
            */
        }

        return sb.toString().trim();
    }

    private int indexOf(String[] words, WordGram target, int start){
        //System.out.println("indexOf " + start);
        for(int index = start; index <= words.length - myOrder; index++) {
            WordGram wg = new WordGram(words,index,target.length());
            //System.out.println(wg);
            //System.out.println(target + "\n");
            if(wg.equals(target)){
                //System.out.println("FOUND AT " + index);
                return index;
            }
        }
        //System.out.println("NOT FOUND");
        return -1;
    }

    private List<String> getFollows(WordGram kGram) {
        //System.out.println("getFollows " + kGram);
        List<String> follows = new ArrayList<>();

        //String[] string = "* * *".split(" ");
        //kGram = new WordGram(string, 0, myOrder);

        int index = indexOf(myText, kGram, 0);
        //int count = 0;
        while(index != -1){
            String text = myText[index+myOrder];
            follows.add(text);
            //System.out.println("BACK IN GETFOLLOWS " + follows);
            //kGram = kGram.shiftAdd(text);
            //System.out.println(kGram);
            index = indexOf(myText, kGram, index+myOrder);

            /*
            count++;
            if(count == 4){
                break;
            }
            */
        }

        return follows;
    }
}
