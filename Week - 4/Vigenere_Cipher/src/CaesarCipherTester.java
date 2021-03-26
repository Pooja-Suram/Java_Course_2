import edu.duke.FileResource;

public class CaesarCipherTester {
    public static void main(String[] args) {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cipher = new CaesarCipher(5);
        String encryptedMessage = cipher.encrypt(message);
        System.out.println(encryptedMessage);
        String decryptedMessage = cipher.decrypt(encryptedMessage);
        System.out.println("Decrypted message is : "+ decryptedMessage);

    }
}
