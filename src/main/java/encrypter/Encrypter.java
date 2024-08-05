package encrypter;

public class Encrypter {

    private static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        // Traverse the text
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Encrypt uppercase letters
            if (Character.isUpperCase(c)) {
                char ch = (char) (((c + shift - 'A') % 26 + 26) % 26 + 'A');
                result.append(ch);
            }
            // Encrypt lowercase letters
            else if (Character.isLowerCase(c)) {
                char ch = (char) (((c + shift - 'a') % 26 + 26) % 26 + 'a');
                result.append(ch);
            }
            // For other characters (spaces, punctuation), leave as is
            else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static String encrypt(String input) {
        return encrypt(input, 3);
    }

}
