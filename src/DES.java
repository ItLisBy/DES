import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class DES {
    private static int[] PC_1 = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };

    public static void main(String[] args) {
        String key = "";
        String message = "";
        for (int i = 0; i < args.length; ++i) {
            if (args[i].isBlank()) {
                continue;
            }
            switch (args[i]) {
                //TODO: Key can be both decimal and hex
                case "-k_h" -> {
                    //key = BigInteger.valueOf(Long.parseLong(args[++i], 16)).toByteArray();
                }
                case "-k" -> key = args[++i];
                case "-m" -> message = args[++i];
                default -> {
                    System.out.println("Error: Invalid parameter key");
                    return;
                }
            }
        }
        if (key.isBlank() || message.isBlank()) {
            System.out.println("Error: Invalid parameter");
            return;
        }
    }

    /*private static void Cipher(String key, String message) {

    }*/

    /*private static String[][] Keys(String key) {
        StringBuilder new_key = new StringBuilder();
        for (var i : PC_1) {
            new_key.append(key.charAt(i));
        }
        var o = Long.parseLong(new_key.toString(), 2) << 1;
    }*/
}
