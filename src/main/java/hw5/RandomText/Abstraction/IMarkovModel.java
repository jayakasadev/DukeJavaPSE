package hw5.RandomText.Abstraction;

/**
 * Created by kasa2 on 10/4/2016.
 */
public interface IMarkovModel {
    public void setTraining(String s);

    public String getRandomText(int numChars);

    public void setRandom(int seed);
}
