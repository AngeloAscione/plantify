package it.unisa.utils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordTool {

    /**
     * Metodo per validare una password che deve avere i seguenti criteri: Lungezza tra i gli 8 e 16 caratteri, almeno un carattere speciale, almeno un numero, almeno una lettera maiuscola
     * @param password
     * @return True se la password rispetta tutti i requisiti, false altrimenti
     */
    public static boolean idValidPassword(String password) {
        if (password.length() < 8 || password.length() > 16){
            return false;
        }
        if (!containsSpecial(password)){
            return false;
        }
        if (!containsNumber(password)){
            return false;
        }
        if (!containsCapital(password)){
            return false;
        }
        return true;
    }

    private static boolean containsSpecial(String password) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        return m.find();
    }

    private static boolean containsNumber(String password){
        return password.matches(".*\\d.*");
    }

    private static boolean containsCapital(String password){
        return password.matches(".*[A-Z].*");
    }

    public static String cipherPassword(String password){
        byte[] encodedHash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return encodedHash != null ? new String(encodedHash) : null;
    }


    public static void main(String ... args){

        System.out.println(PasswordTool.cipherPassword("Test"));

    }

}
