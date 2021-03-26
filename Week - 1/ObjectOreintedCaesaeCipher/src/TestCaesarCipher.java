import edu.duke.FileResource;

public class TestCaesarCipher {

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

    /*This method encrypts the given message by
    * creating CaesarCipher object
    * and decrypts it using breakTheCipher method and
    * decrypt method */
    public void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cipher = new CaesarCipher(18);
        String encryptedMessage = cipher.encrypt(input);
        System.out.println("Encrypted message is "+ encryptedMessage);

        String decrypted = breakTheCipher(encryptedMessage);
        System.out.println("Decrypted message is " + decrypted);


        String decryptedMessage = cipher.decrypt(encryptedMessage);
        System.out.println("Decrypted message is "+ decryptedMessage);
    }

    /*This method figures out which key is used to encrypt given message
    * and decrypt the given message */
    public String breakTheCipher(String input){
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
        TestCaesarCipher test = new TestCaesarCipher();
        test.simpleTests();
    }
}
