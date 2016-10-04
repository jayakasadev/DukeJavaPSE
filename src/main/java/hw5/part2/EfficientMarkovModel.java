package hw5.part2;

import java.util.*;

/**
 * Created by kasa2 on 10/4/2016.
 */
public class EfficientMarkovModel extends AbstractMarkovModel{

    private Map<String, List<String>> map;

    public EfficientMarkovModel(int order) {
        super();
        this.order = order;
        map = new HashMap<>();
    }

    @Override
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }

    @Override
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int index = myRandom.nextInt(myText.length()-order);
        //int index = 0;
        String key = myText.substring(index, index+order);
        sb.append(key);

        for(int k=0; k < numChars-order; k++){
            //System.out.println("key *" + key + "* k " + k);
            List<String> list = getFollows(key);
            if(list == null){
                break;
            }
            index = myRandom.nextInt(list.size());
            String temp = "" + list.get(index);
            sb.append(temp);
            key = key.substring(1) + temp;
        }

        return sb.toString();
    }

    @Override
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel Order: " + order;
    }

    public void buildMap(){
        String key = myText.substring(0, order);
        List<String> list;
        int count = 0;

        for(int a = 0; a < myText.length()-order + 1; a++){
            //System.out.println("key " + key + " a " + a);
            if(map.containsKey(key)){
                list = map.get(key);
                //System.out.println(list);
            }
            else{
                //System.out.println("\tcount " + ++count);
                list = new ArrayList<>();
                map.put(key, list);
            }
            String temp = "";

            if(a+order < myText.length())
                temp += myText.charAt(a+order);
            list.add(temp);
            key = key.substring(1) + temp;
        }
    }

    @Override
    public List<String> getFollows(String key){
        return map.get(key);
    }

    public void printHashMapInfo(){
        System.out.println("\nprintHashMapInfo");
        //System.out.println("Theoretical " + (myText.length()-3));
        //System.out.println("Hashmap has " + map.size() + " keys.");

        Set<String> keys = map.keySet();
        System.out.println("Hashmap has " + keys.size() + " keys.");
        int largest = 0;

        for(String key : keys){
            int size = map.get(key).size();
            if(size > largest){
                largest = size;
            }
        }

        for(String key : keys){
            int size = map.get(key).size();
            if(size == largest){
                System.out.println(key + " HAS SIZE " + largest);
            }
        }

        System.out.println("printHashMapInfo\n");
    }
}
