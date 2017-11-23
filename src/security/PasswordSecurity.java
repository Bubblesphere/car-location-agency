package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordSecurity {
  private static String Salt = "AFF1C913F74C77D9475EEA75494C8";

  /**
   * Méthode pour hash un mot de passe
   * 
   * @param password
   *          le string à hasher.
   * @return the le mot de passe hashé.
   */
  public static String hashPassword(String password) {
    String generatedPassword = null;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(Salt.getBytes("UTF-8"));
      byte[] bytes = md.digest(password.getBytes("UTF-8"));
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }

  public static boolean checkPassword(String password, String hash) {
    String hashedPassword = hashPassword(password);
    return hash.equals(hashedPassword);
  }
}