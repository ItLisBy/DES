import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

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
        String key_path = "";
        String message_path = "";
        String key = new byte[8];
        String message = "";
        for (int i = 0; i < args.length; ++i) {
            if (args[i].isBlank()) {
                continue;
            }
            switch (args[i]) {
                case "-k_p" -> key_path = args[++i];
                case "-m_p" -> message_path = args[++i];
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
            if (key_path.isBlank() || message_path.isBlank()) {
                System.out.println("Error: Invalid parameter");
                return;
            }
            try {
                //key = (long) (new String(Files.readAllBytes(Paths.get(key_path))));
                message = new String(Files.readAllBytes(Paths.get(message_path)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        Cipher(key, message);

    }

    private static void Cipher(byte[] key, String message) {

    }

    private static String[][] Keys(String key) {
        StringBuilder new_key = new StringBuilder();
        for (var i : PC_1) {
            new_key.append(key.charAt(i));
        }
        var o = Long.parseLong(new_key.toString(), 2) << 1;
    }
}
