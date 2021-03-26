package Caesar;

import edu.duke.FileResource;

import java.net.PortUnreachableException;

public class CaesarCipher {

    public String getAlphabets(){
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    public String getReplacedAlphabets(String alphabets, int key){
        return alphabets.substring(key) + alphabets.substring(0, key);
    }

    public char encryptedCharacter(String alphabets, String replacedAlphabets, char ch){
        int index = alphabets.indexOf(Character.toUpperCase(ch));
        if(index != -1){
            if(Character.isLowerCase(ch)){
                return Character.toLowerCase(replacedAlphabets.charAt(index));
            }
            else{
                return replacedAlphabets.charAt(index);
            }
        }
     return ch;
    }

    /*This methods returns a encrypted string of given message
    * for both uppercase and lowercase letters */
    public String encrypt(String inputMessage, int key){
        StringBuilder input = new StringBuilder(inputMessage);
        String alphabets = getAlphabets();
        String replacedAlphabets = getReplacedAlphabets(alphabets, key);
        for(int i=0; i<input.length(); i++){
            char result = encryptedCharacter(alphabets, replacedAlphabets, input.charAt(i));
            input.setCharAt(i, result);
        }
        return input.toString();
    }

    public void testEncrypt(){
        String phrase = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        System.out.println(phrase);
    }

    public void testCaesar(){
        int key = 5;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is "+ key + "\n" + encrypted);
    }

    /*This method returns a string where even position character are
    * replaced with key1 and odd position character is replaced with key2 */
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder phrase = new StringBuilder(input);
        String alphabets = getAlphabets();
        String newAlphabetsKey1 = getReplacedAlphabets(alphabets, key1);
        String newAlphabetsKey2 = getReplacedAlphabets(alphabets, key2);
        for(int i=0; i<phrase.length(); i++){
            if(i%2==0){
                char result = encryptedCharacter(alphabets, newAlphabetsKey1, phrase.charAt(i));
                phrase.setCharAt(i, result);
            }
            else{
                char result = encryptedCharacter(alphabets, newAlphabetsKey2, phrase.charAt(i));
                phrase.setCharAt(i, result);
            }
        }
        return phrase.toString();
    }

    public void testEncryptTwoKeys(){
        String encrypted = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(encrypted);
    }

    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        //cipher.testEncrypt();
        //cipher.testCaesar();
        cipher.testEncryptTwoKeys();
    }
}
