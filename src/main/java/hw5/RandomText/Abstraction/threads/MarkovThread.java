package hw5.RandomText.Abstraction.threads;

import hw5.RandomText.Abstraction.IMarkovModel;
import hw5.RandomText.Abstraction.MarkovRunnerWithInterface;

/**
 * Created by kasa2 on 10/4/2016.
 */
public class MarkovThread implements Runnable{

    private int order;
    private String text;
    private int size;
    private int seed;
    private IMarkovModel model;
    
    public MarkovThread(String text, int size, int seed, IMarkovModel model){
        this.order = order;
        this.text = text;
        this.size = size;
        this.seed = seed;
        this.model = model;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        new MarkovRunnerWithInterface().runModel(model, text, size, seed);
        long finish = System.currentTimeMillis();

        System.out.println("RUNTIME FOR "+ model +" IS " + ((finish - start)/1000.0) + " SEC");
    }
}
