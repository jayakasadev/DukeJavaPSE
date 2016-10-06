package hw5.WordNGrams.part2;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        //System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        //System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size);
            //printOut(st);
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        //System.out.println("runMarkov");
        //MarkovWord markovWord = new MarkovWord(3);
        EfficientMarkovWord markovWord = new EfficientMarkovWord(3);
        runModel(markovWord, st, 200, 643);
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

    public void testHashMap(){

        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);

        //FileResource fr = new FileResource();
        //String st = fr.asString();
        //st = st.replace('\n', ' ');

        //String st = "this is a test yes this is really a test";
        String st = "this is a test yes this is really a test yes a test this is wow";

        System.out.println(st);

        runModel(efficientMarkovWord, st, 50, 42);
    }


    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        int seed = 42;
        int size = 100;

        IMarkovModel word = new MarkovWord(2);
        IMarkovModel efficient = new EfficientMarkovWord(2);

        Thread t1 = new Thread(new MarkovThread(word, st, seed, size));
        Thread t2 = new Thread(new MarkovThread(efficient, st, seed, size));


        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        MarkovRunner m = new MarkovRunner();
        //m.runMarkov();
        //m.testHashMap();
        m.compareMethods();
    }

}
