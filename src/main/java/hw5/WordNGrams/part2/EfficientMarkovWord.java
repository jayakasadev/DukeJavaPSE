package hw5.WordNGrams.part2;

import edu.duke.FileResource;

import java.util.*;

/**
 * Created by kasa2 on 10/5/2016.
 */
public class EfficientMarkovWord implements  IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private Map<WordGram, List<String>> map;

    public EfficientMarkovWord(int order){
        myOrder = order;
        myRandom = new Random();
        map = new HashMap<>();
    }

    @Override
    public void setTraining(String text) {
        myText = text.split("\\s+");
        //System.out.println("Building the Map");
        buildMap();
        printHashMapInfo();
    }

    @Override
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    @Override
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();

        List<WordGram> keys = new ArrayList<>(map.keySet());
        int index = myRandom.nextInt(map.size());  // random word to start with

        WordGram w = keys.get(index);
        sb.append(w);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            List<String> follows = getFollows(w);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            w = w.shiftAdd(next);
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
        return map.get(kGram);
    }

    private void buildMap(){
        //System.out.println("buildMap");

        int index = 0;  // random word to start with
        String[] key = new String[myOrder];
        System.arraycopy(myText, index, key, 0, myOrder);
        WordGram w = new WordGram(key, 0, myOrder);

        while(index < myText.length-myOrder){
            //System.out.println(w + " index " + index + " myText.length-myOrder " + (myText.length-myOrder));
            String text = myText[index+myOrder];
            List<String> list = map.get(w);

            if(list == null){
                list = new ArrayList<>();
                map.put(w, list);
            }

            list.add(text);

            w = w.shiftAdd(text);
            index++;
            //System.out.println("\t" + w + " index " + index + " myText.length-myOrder " + (myText.length-myOrder));
        }
        map.put(w, new ArrayList<>());
    }

    public void printHashMapInfo(){
        System.out.println("\nprintHashMapInfo");
        //System.out.println("Theoretical " + (myText.length()-3));
        //System.out.println("Hashmap has " + map.size() + " keys.");

        Set<WordGram> keys = map.keySet();
        System.out.println("Hashmap has " + keys.size() + " keys.");
        int largest = 0;

        for(WordGram key : keys){
            int size = map.get(key).size();
            if(size > largest){
                largest = size;
            }
        }

        for(WordGram key : keys){
            //System.out.println(key);
            int size = map.get(key).size();
            if(size == largest){
                System.out.println(key + " HAS SIZE " + largest);
            }
        }

        System.out.println("printHashMapInfo\n");
    }

    @Override
    public String toString() {
        return "EfficientMarkovWord " + myOrder;
    }
}
