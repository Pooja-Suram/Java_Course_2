import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> map;

    WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }


    /*this method adds all the words to the map
    where a corresponding arraylist of
    file names in which a word appears will be mapped to
    the word*/
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word: fr.words()){
            ArrayList<String> array = new ArrayList<String>();
            if(map.containsKey(word)){
                array = map.get(word);
                if(!array.contains(f.getName())){
                    array.add(f.getName());
                }
            }
            else {
                array = new ArrayList<String>();
                array.add(f.getName());
            }
            map.put(word, array);
        }
    }

    /*selects multiple files using DirectoryResource
    and puts all the words from a file into the map
    by calling addWordsFromFile method*/
    public void buildWordFileMap(){
        map = new HashMap<String, ArrayList<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    /*this method returns maximum number of files
     any word appears in.*/
    public int maxNumber(){
        int largestCount = 0;
        for(String word: map.keySet()){
            ArrayList<String> array = map.get(word);
            int count = array.size();
            if(largestCount < count){
                largestCount = count;
            }
        }
        return largestCount;
    }

    /*this method returns an ArrayList of words that appear
    in exactly 'number' number of files*/
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> commonWords = new ArrayList<String>();
        for(String word: map.keySet()){
            int length = map.get(word).size();
            if(length == number){
                commonWords.add(word);
            }
        }
        System.out.println("total number of words that appear " + number +" of times are:" + commonWords.size());
        return commonWords;
    }

    /*this method prints the names of files
    this word appears in*/
    public void printFilesIn(String word){
        System.out.println("The files in which " + word + " appears are:");
        ArrayList<String> array = map.get(word);
        for(int i=0; i<array.size(); i++){
            System.out.println(array.get(i));
        }
    }


    public void tester(){
        buildWordFileMap();
        int maxCount = maxNumber();
        System.out.println("Max number of files any word is in is: "+ maxCount);
        ArrayList<String> commonWords = wordsInNumFiles(maxCount);
        System.out.println("Most common words are: ");
        for(int i=0; i<commonWords.size(); i++){
            printFilesIn(commonWords.get(i));
        }

    }


    public static void main(String[] args) {
        WordsInFiles words = new WordsInFiles();
        words.tester();
    }
}
