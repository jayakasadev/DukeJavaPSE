package hw5.WordNGrams.part2;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";

        for(String s : myWords){
            ret += s + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;

        if(other  == null){
            return false;
        }
        if(!other.toString().equals(toString())){
            return false;
        }
        return true;

    }

    private String[] getMyWords(){
        return myWords;
    }

    public WordGram shiftAdd(String word) {	
        //WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method

        String[] arr = new String[myWords.length];

        int index = 0;

        while(index < myWords.length-1){
            //System.out.println(this);
            arr[index] = myWords[index+1];
            index++;
        }
        arr[index] = word;
        //System.out.println(this);

        WordGram out = new WordGram(arr, 0, arr.length);

        return out;
    }

    public static void main(String[] args){
        String[] source = "this is a test".split(" ");
        WordGram w = new WordGram(source, 0, source.length);
        System.out.println(w);
        w = w.shiftAdd("yes");
        System.out.println(w);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}