package hw5.part2;

import edu.duke.FileResource;
import hw5.part2.threads.MarkovThread;

/**
 * Created by kasa2 on 10/4/2016.
 */
public class MarkovRunnerWithInterface {
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        IMarkovModel markov = new MarkovZero();
        //markov.setRandom(101);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovOne() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        IMarkovModel markov = new MarkovOne();
        //markov.setRandom(101);
        markov.setRandom(42);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovFour() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        IMarkovModel markov = new MarkovFour();
        //markov.setRandom(101);
        markov.setRandom(25);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovModel() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        IMarkovModel markov = new MarkovModel(3);
        //markov.setRandom(101);
        markov.setRandom(38);
        markov.setTraining(st);
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runModel(IMarkovModel model, String text,  int size, int seed){
        model.setRandom(seed);
        model.setTraining(text);
        for(int k=0; k < 3; k++){
            String temp = model.getRandomText(size);
            printOut(temp);
        }
    }

    public void runMarkov(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');


        System.out.println("\t\tZERO");
        IMarkovModel model = new MarkovZero();
        runModel(model, st, 500, 101);
        System.out.println("\t\tONE");
        model = new MarkovOne();
        runModel(model, st, 500,  42);
        System.out.println("\t\tTHREE");
        model = new MarkovModel(3);
        runModel(model, st, 500, 38);
        System.out.println("\t\tFOUR");
        model = new MarkovFour();
        runModel(model, st, 500, 25);
    }

    public void testHashMap(){
        int seed = 42;
        String text = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        EfficientMarkovModel model = new EfficientMarkovModel(2);

        //model.setTraining(text);
        //model.printHashMapInfo();
        //model.setRandom(seed);
        //model.getRandomText(size);

        runModel(model, text, size, seed);
    }

    public void compareMethods(){
        int order = 2;
        int seed = 42;
        int size = 1000;


        FileResource fr = new FileResource();
        String text = fr.asString();
        text = text.replace('\n', ' ');

        Thread model = new Thread(new MarkovThread(text, size, seed, new MarkovModel(order)));
        Thread efficient = new Thread(new MarkovThread(text, size, seed, new EfficientMarkovModel(order)));


        try {
            model.start();
            efficient.start();
            model.join();
            efficient.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public static void main(String[] args){
        MarkovRunnerWithInterface m = new MarkovRunnerWithInterface();
        //m.runMarkov();
        //m.testHashMap();
        m.compareMethods();
    }
}
