import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.Locale;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    /*This method iterates through a file,
    * stores all new words in myWords
    * and their frequencies in myFreqs */
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        //FileResource fr = new FileResource();
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//java/likeit.txt");
        for(String word: ur.words()){
            int index = myWords.indexOf(word.toLowerCase(Locale.ROOT));
            if(index == -1){
                myWords.add(word.toLowerCase(Locale.ROOT));
                myFreqs.add(1);
            }
            else{
                int wordCount = myFreqs.get(index);
                myFreqs.set(index, wordCount+1);
            }
        }
    }

    public void tester(){
        findUnique();
        System.out.println("Number of unique words are: "+ myWords.size());
        /**for(int i=0; i<myWords.size(); i++){
            System.out.println(myWords.get(i) + " : " + myFreqs.get(i));
        }**/

        int largestFreq = findIndexOfMax();
        System.out.println("Word with largest frequency is "+ myWords.get(largestFreq) +
                        " with frequency "+ myFreqs.get(largestFreq));
    }

    /*This method returns an int that is the
    * index location of the largest value in myFreqs*/
    public int findIndexOfMax(){
        int largest = 0;
        for(int i=0; i<myFreqs.size(); i++){
            int value = myFreqs.get(i);
            if(largest < value){
                largest = value;
            }
        }
        return myFreqs.indexOf(largest);
    }

    public static void main(String[] args) {
        WordFrequencies frequencies = new WordFrequencies();
        frequencies.tester();
    }
}
