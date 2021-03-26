public class CaesarCipherTwo {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2){
        mainKey1 = key1;
        mainKey2 = key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, mainKey1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, mainKey2);
    }


    public char encryptedCharacter(String shiftedAlphabet, char ch){
        int index = alphabet.indexOf(Character.toUpperCase(ch));
        if(index != -1){
            if(Character.isLowerCase(ch)){
                return Character.toLowerCase(shiftedAlphabet.charAt(index));
            }
            else{
                return shiftedAlphabet.charAt(index);
            }
        }
        return ch;
    }

    /*This method encrypts the given input
    * using both shiftedAlphabets */
    public String encrypt(String input){
        StringBuilder phrase = new StringBuilder(input);
        for(int i=0; i<phrase.length(); i++){
            if(i%2==0){
                char result = encryptedCharacter(shiftedAlphabet1, phrase.charAt(i));
                phrase.setCharAt(i, result);
            }
            else{
                char result = encryptedCharacter(shiftedAlphabet2, phrase.charAt(i));
                phrase.setCharAt(i, result);
            }
        }
        return phrase.toString();
    }

    /*This method decrypts the given message
    * using two keys*/
    public String decrypt(String input){
        CaesarCipherTwo cipher = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cipher.encrypt(input);
    }

}
