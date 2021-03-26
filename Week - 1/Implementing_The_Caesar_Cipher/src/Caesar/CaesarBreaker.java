package Caesar;

import Words.WordLength;

public class CaesarBreaker {

    /*This method iterates through given string
    * and counts the occurrences of each character and
    * stores them in counts array*/
    public int[] countLetters(String sentence){
        int[] counts = new int[27];
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0; i<sentence.length(); i++){
            int index = letters.indexOf(Character.toLowerCase(sentence.charAt(i)));
            if(index !=-1){
                counts[index+1] += 1;
            }
        }
        return counts;
    }

    /*This methods returns the index of
      maximum count value */
    public int maxIndexOfCounts(int[] counts){
        WordLength wordLength = new WordLength();
        return wordLength.indexOfMax(counts);
    }

    /*This method decrypt the given encrypted message
    * based on most common character in counts array*/
    public String decrypt(String encrypted){
        CaesarCipher cipher = new CaesarCipher();
        int[] frequencyCounts = countLetters(encrypted);
        int maxIndex = maxIndexOfCounts(frequencyCounts);
        int decryptKey = maxIndex -5;
        if(maxIndex < 5){
            decryptKey = 26 - (5-maxIndex);
        }
        return cipher.encrypt(encrypted, 26- decryptKey);
    }

    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        String encrypted = cipher.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 5);
        System.out.println(encrypted);

        CaesarBreaker breaker = new CaesarBreaker();
        String decrypted = breaker.decrypt(encrypted);
        System.out.println(decrypted);
    }
}
