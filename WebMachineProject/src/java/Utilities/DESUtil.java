package Utilities;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DESUtil {
    static byte[] desKey = new byte[]{
        21, 1, -110, 82, -32, -85, -128, -65
    };

    public byte[] getDesKey() {
        return desKey;
    }

    public void setDesKey(byte[] desKey) {
        DESUtil.desKey = desKey;
    }
    
    
    public static String encrypt(String data)
    {
        String encryptedData = null;
        try
          {
            
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(desKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);

            encryptedData = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
          }
        catch (Exception e)
          {
            throw new RuntimeException(e);
          }
        return encryptedData;
    }


    
    public static String decrypt(String cryptData)
    {
        String decryptedData = null;
        try
          {

            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(desKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);

            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            decryptedData = new String(cipher.doFinal(Base64.getDecoder().decode(cryptData)));
          }
        catch (Exception e)
          {

            throw new RuntimeException(e);
          }
        return decryptedData;
    }
}
