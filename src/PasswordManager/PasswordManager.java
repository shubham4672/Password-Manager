package PasswordManager;

import javax.crypto.Cipher; // for actual encryption/decryption operations
import javax.crypto.spec.SecretKeySpec; // to convert your raw key (byte array) into a key object the Cipher understands
import java.util.Base64; // to encode encrypted bytes into a readable string (and decode when decrypting)
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PasswordManager {
    private static final String ALGORITHM = "AES"; // tells JAVA to use the AES encryption engine
    private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't',
            'K', 'e', 'y' }; // secret key for aes
    private Map<String, String> passwordStore = new HashMap<>();

    public static void main(String[] args) {
        PasswordManager manager = new PasswordManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Password");
            System.out.println("2. Retrieve Password");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter site: ");
                    String site = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    manager.addPassword(site, password);
                    break;
                case 2:
                    System.out.print("Enter site: ");
                    site = scanner.nextLine();
                    String retrievedPassword = manager.getPassword(site);
                    System.out.println("Password: " + retrievedPassword);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void addPassword(String site, String password) {
        try {
            String encryptedPassword = encrypt(password);
            passwordStore.put(site, encryptedPassword);
            System.out.println("Password added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPassword(String site) {
        try {
            String encryptedPassword = passwordStore.get(site);
            return encryptedPassword != null ? decrypt(encryptedPassword) : "No password found for this site.";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // input -> AES encrytion -> Encrypted bytes -> base64 string
    private String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    // Base64 string → Decoded bytes → AES decryption → Original string
    private String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue);
    }
}
