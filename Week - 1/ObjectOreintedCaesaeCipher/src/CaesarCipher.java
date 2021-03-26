public class CaesarCipher {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key){
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(mainKey) + alphabet.substring(0, mainKey);
    }

    /*This method returns encrypted letter
    * of given character */
    public char encryptedCharacter(char ch){
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

    /*This method returns a string that
    * is the input encrypted using shifted alphabet */
    public String encrypt(String input){
        StringBuilder inputMessage = new StringBuilder(input);
        for(int i=0; i<input.length(); i++){
            char result = encryptedCharacter(input.charAt(i));
            inputMessage.setCharAt(i, result);
        }
        return inputMessage.toString();
    }

    /*This method returns decrypted string of
    * given input */
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
