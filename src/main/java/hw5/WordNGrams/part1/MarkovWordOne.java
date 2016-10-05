package hw5.WordNGrams.part1;
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
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
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);

            //System.out.println(follows);

            if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
        int index = 0;
        //System.out.println("getFollows " + key);
        int count = 0;
        ArrayList<String> follows = new ArrayList<>();
        while(index < myText.length){
            //System.out.println(index);
            index = indexOf(myText, key, index);
            if(index == -1){
                break;
            }
            if(++index < myText.length)
                follows.add(myText[index]);
        }

        return follows;
    }

	private int indexOf(String[] words, String target, int start){
		while(start < words.length){
            if(words[start].equals(target)){
                return start;
            }
            start++;
        }
        return -1;
	}

	public void testIndexOf(){
        String[] words = "this is just a test yes this is a simple test".split(" ");
        String target = "this";
        int start = 0;
        System.out.println("Starting at " + start  + " target: " + target + " Result:" + indexOf(words, target, start));
        start = 3;
        System.out.println("Starting at " + start  + " target: " + target + " Result:" + indexOf(words, target, start));
        start = 0;
        target = "frog";
        System.out.println("Starting at " + start  + " target: " + target + " Result:" + indexOf(words, target, start));
        start = 5;
        System.out.println("Starting at " + start  + " target: " + target + " Result:" + indexOf(words, target, start));
        start = 2;
        target = "simple";
        System.out.println("Starting at " + start  + " target: " + target + " Result:" + indexOf(words, target, start));
        start = 5;
        target = "test";
        System.out.println("Starting at " + start  + " target: " + target + " Result:" + indexOf(words, target, start));
    }

    public static void main(String[] args){
        MarkovWordOne m = new MarkovWordOne();
        m.testIndexOf();
    }
}
