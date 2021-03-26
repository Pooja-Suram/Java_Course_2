import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> map;
    private ArrayList<String> wordUsedList;
    private ArrayList<String> wordCategory;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        map = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordCategory = new ArrayList<String>();
    }

    public GladLibMap(String source){
        map = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    //this method gives a source file for every label
    //to get words related to that label
    //and ArrayList of words related to
    //particular label is mapped to that label in hashmap
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color",
                "country", "name", "animal",
                "timeframe", "verb", "fruit"};
        for (String category : categories) {
            ArrayList<String> array = readIt(source + "/" + category + ".txt");
            map.put(category, array);
        }
        wordUsedList = readIt(source+"/wordUsedList.txt");
    }

    //generates a random number and returns a value
    //which is at given random index
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    /*Creates substitute of each label */
    private String getSubstitute(String label) {
        if(label.equals("number")){
            return ""+myRandom.nextInt(500);
        }
        if(map.containsKey(label)) {
            return randomFrom(map.get(label));
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first + 1, last);
        String sub = getSubstitute(label);
        int index = wordUsedList.indexOf(sub);
        while(index != -1) {
            sub = getSubstitute(w.substring(first + 1, last));
            index = wordUsedList.indexOf(sub);
        }
        wordUsedList.add(sub);
        wordCategory.add(label);
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    //this method returns total number of
    //words in all the ArrayLists in the
    //HashMap
    public int totalWOrdsInMap(){
        int totalCount = 0;
        for(String word: map.keySet()){
            int length = map.get(word).size();
            totalCount+= length;
        }
        return totalCount;
    }

    //this method returns total number of
    //words from the ArrayLists which are
    //used in GladLib
    public int totalWordsConsidered(){
        int count = 0;
        for(String label: wordCategory){
            if(!label.equals("number")) {
                count += map.get(label).size();
            }
        }
        return count;
    }

    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("Total number of words that were possible to pick from are:" + totalWOrdsInMap());
        System.out.println("Total number of words replaced are :" + wordUsedList.size());
        System.out.println("Total count of words in categories used in GladLib are: " + totalWordsConsidered());
    }

    public static void main(String[] args) {
        GladLibMap gladLib = new GladLibMap();
        gladLib.makeStory();
    }

}
