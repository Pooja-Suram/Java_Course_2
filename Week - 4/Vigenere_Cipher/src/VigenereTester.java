import edu.duke.FileResource;

public class VigenereTester {
    public static void main(String[] args) {
        int[] key = {17,14,12,4};
        VigenereCipher cipher = new VigenereCipher(key);
        FileResource fr= new FileResource();
        String message = fr.asString();
        String encryptedMessage = cipher.encrypt(message);
        System.out.println(encryptedMessage);
        String decryptedMessage = cipher.decrypt(encryptedMessage);
        System.out.println(decryptedMessage);
    }
}
