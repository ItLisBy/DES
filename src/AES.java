import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class AES {
    public static byte[] key_hash;
    public static String message;
    public static String ciphered_message;
    public static IvParameterSpec iv;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "-k" -> {
                    if (!args[i+1].isBlank())
                        key_hash = hashKey(args[++i]);
                    else {
                        System.out.println("Error: Invalid key");
                        return;
                    }
                }
                case "-m" -> message = args[++i];
                default -> {
                    System.out.println("Error: Invalid parameter key");
                    return;
                }
            }
        }
        if (message.isBlank()) {
            System.out.println("Error: Invalid message");
            return;
        }
        iv = generateIv();

        System.out.printf("Message: %s\n", message);
        ciphered_message = encrypt();
        System.out.printf("Cipher: %s\n", ciphered_message);
        System.out.printf("Decrypted: %s\n", decrypt());
    }

    public static byte[] hashKey(String key) {
        byte[] result;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(key.getBytes());
            result = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            result = "Error".getBytes(StandardCharsets.UTF_8);
        }
        return result;
    }

    public static String encrypt() {
        String result;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key_hash, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(message.getBytes());
            result = Base64.getEncoder().encodeToString(encrypted);
        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            result = "Error";
        }
        return result;
    }

    public static String decrypt() {
        String result;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key_hash, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(ciphered_message));
            result = new String(decrypted);
        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            result = "Error";
        }
        return result;
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
}
