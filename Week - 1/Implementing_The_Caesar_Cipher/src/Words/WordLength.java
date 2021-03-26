package Words;

import edu.duke.FileResource;

public class WordLength {

    public int calculateLength(String word){
        int length = 0;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            if((i==0 || i==word.length()-1) && !Character.isLetter(ch)){
                continue;
            }
            length+=1;
        }
        return length;
    }

    /*This method iterates through given file
    * and counts the number of words of each length
    * and stores these counts in array counts and returns counts */
    public int[] countWordLengths(FileResource resource, int[] counts){
        for(String word: resource.words()){
            int wordLength = calculateLength(word);
            if(wordLength >= counts.length){
                counts[counts.length -1]+=1;
            }
            else{
                counts[wordLength]+=1;
            }
        }
        for(int i=1; i<counts.length; i++){
            System.out.println("words of length" + i + " are "+ counts[i]);
        }
        return counts;
    }

    /*This method returns index of largest elements in values */
    public int indexOfMax(int[] values){
        int largest = 0;
        int index = 0;
        for(int i=0; i<values.length; i++){
            if(values[i] > largest){
                largest = values[i];
                index = i;
            }
        }
        return index;
    }

    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        counts = countWordLengths(fr, counts);
        int index = indexOfMax(counts);
        System.out.println("highest count is: "+ counts[index]);
    }

    public static void main(String[] args) {
        WordLength length = new WordLength();
        length.testCountWordLengths();
    }
}
