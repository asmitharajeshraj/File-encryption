import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class FileEncryptor {

    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "MySuperSecretKey".getBytes(); // 16 bytes = 128-bit key

    public static void encrypt(String inputFilePath, String outputFilePath) {
        try {
            SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFilePath);
            byte[] inputBytes = new byte[(int) new File(inputFilePath).length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFilePath);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

            System.out.println("✅ File encrypted successfully: " + outputFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decrypt(String inputFilePath, String outputFilePath) {
        try {
            SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFilePath);
            byte[] inputBytes = new byte[(int) new File(inputFilePath).length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFilePath);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

            System.out.println("✅ File decrypted successfully: " + outputFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java FileEncryptor <encrypt/decrypt> <inputFile> <outputFile>");
            return;
        }

        String mode = args[0];
        String inputFile = args[1];
        String outputFile = args[2];

        if (mode.equalsIgnoreCase("encrypt")) {
            encrypt(inputFile, outputFile);
        } else if (mode.equalsIgnoreCase("decrypt")) {
            decrypt(inputFile, outputFile);
        } else {
            System.out.println("❌ Invalid mode! Use 'encrypt' or 'decrypt'");
        }
    }
}