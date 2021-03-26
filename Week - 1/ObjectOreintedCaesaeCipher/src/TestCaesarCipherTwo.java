import edu.duke.FileResource;


public class TestCaesarCipherTwo {

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


    public int maxIndex(int[] values){
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

    public void simpleTests(){
        FileResource fr = new FileResource();
        String inputMessage = fr.asString();
        CaesarCipherTwo cipherTwo = new CaesarCipherTwo(17, 3);
        String encryptedMessage = cipherTwo.encrypt(inputMessage);
        System.out.println("Encrypted message is " + encryptedMessage);
        String decryptedMessage = cipherTwo.decrypt(encryptedMessage);
        System.out.println("Decrypted message is " + decryptedMessage);

        String messageBroken = breakTheCipher(encryptedMessage);
        System.out.println("Broken message is "+ messageBroken);
    }

    /*This method figures out which keys were used to encrypt
    * the message and creates CaesarCipherTwo object with that
    * key and decrypt the message */
    public String breakTheCipher(String input){
        String oddString = "";
        String evenString = "";
        for(int i=0; i<input.length(); i++){
            if(i%2 == 0){
                oddString = oddString + input.charAt(i);
            }
            else{
                evenString = evenString + input.charAt(i);
            }
        }

        String decryptOddString = breakTheCipherHalf(oddString);
        String decryptEvenString = breakTheCipherHalf(evenString);
        StringBuilder result = new StringBuilder(input);
        for(int i=0; i<decryptOddString.length(); i++){
            result.setCharAt(i*2, decryptOddString.charAt(i));
        }
        for(int i=0; i<decryptEvenString.length(); i++){
            result.setCharAt(i*2 + 1, decryptEvenString.charAt(i));
        }
        return result.toString();
    }



    public String breakTheCipherHalf(String input){
        int[] frequencyCounts = countLetters(input);
        int maxIndex = maxIndex(frequencyCounts);
        int decryptKey = maxIndex -5;
        if(maxIndex < 5){
            decryptKey = 26 - (5-maxIndex);
        }
        CaesarCipher cc = new CaesarCipher(decryptKey);
        return cc.decrypt(input);
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo cipher = new TestCaesarCipherTwo();
        cipher.simpleTests();
    }
}
