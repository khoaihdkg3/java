/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 *
 * @author Administrator
 */
public class CipherDES {
    private Cipher enCipher = null;
    private Cipher deCipher = null;
    private byte[] salt = hexStringToByteArray("0da5367e75cfd3ab");
    private int interationCount = 1000;
    private AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, interationCount);
    public CipherDES(String Password){
        try {
            KeySpec keyspec = new PBEKeySpec(Password.toCharArray(), salt, interationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keyspec);
            deCipher = Cipher.getInstance(key.getAlgorithm());
            enCipher = Cipher.getInstance(key.getAlgorithm());
            
            deCipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
            enCipher.init(Cipher.ENCRYPT_MODE, key,paramSpec);
        } catch (NoSuchAlgorithmException | 
                NoSuchPaddingException | 
                InvalidKeyException | 
                InvalidAlgorithmParameterException | 
                InvalidKeySpecException ex) {
            Logger.getLogger(CipherDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String encrypt(String str){
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enstr = enCipher.doFinal(utf8);
            return Base64.getEncoder().encodeToString(enstr);
        } catch (UnsupportedEncodingException | 
                IllegalBlockSizeException | 
                BadPaddingException ex) {
            Logger.getLogger(CipherDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String decrypt(String str){
        try {
            byte[] destr = Base64.getDecoder().decode(str);
            byte[] utf8 = deCipher.doFinal(destr);
            return new String(utf8,"UTF8");
        } catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(CipherDES.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
    }
    return data;
    }
}
