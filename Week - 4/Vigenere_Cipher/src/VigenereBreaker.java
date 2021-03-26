import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slice = "";
        for(int i=whichSlice; i<message.length(); i+=totalSlices){
            slice = slice + message.charAt(i);
        }
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i=0; i<klength; i++){
            String slice = sliceString(encrypted, i, klength);
            CaesarCracker cracker = new CaesarCracker(mostCommon);
            int keyOfSlice = cracker.getKey(slice);
            key[i] = keyOfSlice;
        }
        return key;
    }

    //this method reads the language file and
    //makes hashset of all words
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for(String line: fr.lines()){
            words.add(line.toLowerCase(Locale.ROOT));
        }
        return words;
    }

    //this method returns the number of valid words
    //a word is valid if it is present in dictionary hashset
    public int countWords(String message, HashSet<String> dictionary){
        int numOfValidWords = 0;
        for(String word: message.split("\\W+")){
            if(dictionary.contains(word.toLowerCase(Locale.ROOT))){
                numOfValidWords+=1;
            }
        }
        return numOfValidWords;
    }


    //this method decrypts the given encrypted message
    //when key length is not known
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String correctDecryptedMessage = null;
        int largestCount = 0;
        int[] correctKeys = null;
        for(int i=1; i<=6; i++){
            //calculating keys for each length
            char mostCommonChar = mostCommonCharIn(dictionary);
            int[] keysArray = tryKeyLength(encrypted, i, mostCommonChar);

            //decrypting using Viginere Cipher using keys of ith length
            VigenereCipher cipher = new VigenereCipher(keysArray);
            String decrypted = cipher.decrypt(encrypted);

            /*finding decrypted message by
            calculating valid words*/
            int validWords = countWords(decrypted, dictionary);
            if(validWords > largestCount){
                largestCount = validWords;
                correctDecryptedMessage = decrypted;
                correctKeys = keysArray;
            }
        }

        System.out.println("Correct keys are: ");
        for (int correctKey : correctKeys) {
            System.out.print(correctKey + " ");
        }
        System.out.println("Key length is: " + correctKeys.length);
        System.out.println("Number pf valid words are: " + largestCount);
        return correctDecryptedMessage;
    }




    //this method returns the most
    //common character from given dictionary
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        CaesarCracker cracker = new CaesarCracker();
        String message = "";
        for(String word: dictionary){
            message+= word.toLowerCase(Locale.ROOT);
        }
        int[] charCounts = cracker.countLetters(message);
        int maxIndex = cracker.maxIndex(charCounts);
        return alphabets.charAt(maxIndex);
    }



    /* this method decrypts given encrypted message by :
    * 1.iterating through every language in hashmap
    * 2. decrypting the message using breakForLanguage
    *    where key length is not known
    * 3.checking common words from message with dictionary
    *    to check whether the message belongs to that language*/
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int largestCount = 0;
        String correctDecryptedMessage = null;
        String correctLanguage = null;
        for(String language: languages.keySet()){
            HashSet<String> dictionary = languages.get(language);
            String decryptOfLanguage = breakForLanguage(encrypted, dictionary);
            int wordCount = countWords(decryptOfLanguage, dictionary);
            if(wordCount > largestCount){
                largestCount = wordCount;
                correctDecryptedMessage = decryptOfLanguage;
                correctLanguage = language;
            }
        }
        System.out.println("Language is: " + correctLanguage);
        System.out.println("Decrypted message is: " + correctDecryptedMessage);
    }


    /*this method creates HashMap for every language where
   language is mapped to its words and breaks the encrypted message*/
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        System.out.println("Select file to decrypt");
        FileResource fr = new FileResource();
        String message = fr.asString();


        HashMap<String, HashSet<String>> languagesDictionary =
                new HashMap<String, HashSet<String>>();
        System.out.println("Select languages for dictionary: ");
        DirectoryResource resource = new DirectoryResource();

        //for every file, we are creating a dictionary
        for(File f: resource.selectedFiles()){
            FileResource file = new FileResource(f);
            HashSet<String> dictionary = readDictionary(file);
            languagesDictionary.put(f.getName(), dictionary);
            System.out.println("Read "+ f.getName() + " language and made a dictionary");
        }

        /*passing all the languages and their
       dictionaries to decrypt given message*/
        breakForAllLangs(message, languagesDictionary);

    }



    public static void main(String[] args) {
        VigenereBreaker breaker = new VigenereBreaker();
        breaker.breakVigenere();
    }
}
