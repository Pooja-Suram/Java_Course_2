import edu.duke.FileResource;

public class CaesarCrackerTester {
    public static void main(String[] args) {
        CaesarCracker cracker = new CaesarCracker();
        FileResource fr = new FileResource();
        String message = fr.asString();
        String decryptedMessage = cracker.decrypt(message);
        System.out.println("Decrypted message is: " + decryptedMessage);

    }
}
