package it.unisa.utils;
import java.math.BigInteger;
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
    public static boolean isValidPassword(String password) {
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

    /**
     * Metodo che controlla se in una stringa sono contenuti caratteri speciali
     * @param password
     * @return True se sono contenuti caratteri speciali, False altrimenti
     */
    private static boolean containsSpecial(String password) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        return m.find();
    }
    /**
     * Metodo che controlla se in una stringa sono contenuti dei numeri
     * @param password
     * @return True se sono contenuti numeri, False altrimenti
     */
    private static boolean containsNumber(String password){
        return password.matches(".*\\d.*");
    }
    /**
     * Metodo che controlla se in una stringa sono contenute delle maiuscole
     * @param password
     * @return True se sono contenute maiuscole, False altrimenti
     */
    private static boolean containsCapital(String password){
        return password.matches(".*[A-Z].*");
    }
    /**
     * Metodo per ottenere il byte array contenente la stringa in hash
     * @param password
     * @return La stringa corrispondente allo sha256 della password in input
     */
    public static String cipherPassword(String password){
        byte[] encodedHash;
        StringBuilder hexString = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return toHexString(encodedHash);
    }

    /**
     * Metodo per convertire un Hex array in stringa
     * @param hash
     * @return Stringa ottenuta da Hex array
     */
    public static String toHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64){
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
