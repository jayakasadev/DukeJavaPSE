package hw5.WordNGrams.part2;

/**
 * Created by kasa2 on 10/5/2016.
 */
public class MarkovThread implements Runnable{

    private IMarkovModel model;
    private String text;
    private int seed;
    private int size;

    public MarkovThread(IMarkovModel model, String text, int seed, int size){
        this.model = model;
        this.text = text;
        this.seed = seed;
        this.size = size;

    }

    @Override
    public void run() {
        long start = System.nanoTime();
        MarkovRunner runner = new MarkovRunner();
        runner.runModel(model, text, size, seed);
        long finish = System.nanoTime();

        System.out.println(model + "\n\tRuntime: " + ((finish-start)/1000000000.0) + " seconds");
    }
}
