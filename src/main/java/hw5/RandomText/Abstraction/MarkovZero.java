package hw5.RandomText.Abstraction;
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel{

	public MarkovZero() {
		super();
        order = 0;
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
		for(int k=0; k < numChars; k++){
			int index = myRandom.nextInt(myText.length());
			sb.append(myText.charAt(index));
		}
		
		return sb.toString();
	}

    @Override
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
}
