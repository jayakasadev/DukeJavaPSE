package hw5;

import edu.duke.FileResource;

import java.util.List;

/**
 * Created by kasa2 on 10/3/2016.
 */
public class Tester {

    String set = "this is a test yes this is a test.";

    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        set = fr.asString();
        set = set.replace('\n', ' ');

        testGetFollows();
    }

    public void testGetFollows(){
        MarkovOne one = new MarkovOne();
        one.setTraining(set);
        printList(one.getFollows("t"));
        //printList(one.getFollows("e"));
        //printList(one.getFollows("es"));
    }

    void printList(List<String> list){
        list.stream().forEach(c -> System.out.println(c));
        System.out.println(list.size());
    }

    public static void main(String[] args){
        Tester test = new Tester();
        //test.testGetFollowsWithFile();
        test.testGetFollows();
    }
}
