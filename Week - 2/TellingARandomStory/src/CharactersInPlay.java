import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class CharactersInPlay {
    private ArrayList<String> myNames;
    private ArrayList<Integer> myFreqs;

    CharactersInPlay(){
        myNames = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    /*This method updates the two arraylists, adding the
     character's name if it is not already there,
       and counting this line as one speaking part for this person*/
    public void update(String person){
        int index = myNames.indexOf(person);
        if(index == -1){
            myNames.add(person);
            myFreqs.add(1);
        }
        else{
            int count = myFreqs.get(index);
            myFreqs.set(index, count+1);
        }
    }

    /*This method extracts the name of speaking person
    * In given data, Speaking person's name always ends with '.'
    * and calls update method to count its occurances */
    public void findAllCharacters(){
        //FileResource fr = new FileResource();
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//java/likeit.txt");
        for(String line: ur.lines()){
            int index = line.indexOf(".");
            if(index !=-1){
                String name = line.substring(0, index);
                update(name);
            }
        }
    }

    /*This method prints out the names of all those characters
    * that have exactly number speaking parts where number lies in range between
    * num1 and num2 */
    public void charactersWithNumParts(int num1, int num2){
        System.out.println("Characters with count in given range are :");
        for(int i=0; i<myFreqs.size(); i++){
            int count = myFreqs.get(i);
            if(count >= num1 && count <= num2){
                System.out.println(myNames.get(i));
            }
        }
    }

    public void tester(){
        findAllCharacters();
        for(int i=0; i<myNames.size(); i++){
            if(myFreqs.get(i) > 1){
                System.out.println(myNames.get(i) + " " + myFreqs.get(i));
            }
        }
        charactersWithNumParts(10,15);
    }

    public static void main(String[] args) {
        CharactersInPlay play = new CharactersInPlay();
        play.tester();
    }
}
