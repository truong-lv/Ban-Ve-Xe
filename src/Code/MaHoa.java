/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author n18dc
 */
public class MaHoa {

    private String chuoiKyTu;
    public void setChuoiKyTu(String chuoiKyTu) {
        this.chuoiKyTu = chuoiKyTu;
    }

    public MaHoa(String chuoiKyTu) {
        this.chuoiKyTu = chuoiKyTu;
    }
    
    public String maHoa(){
//        String original = "BanVeXe";
        String SECRET_KEY = "stackjava.com.if";
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MaHoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(MaHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(MaHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        byte[] byteEncrypted = null;
        try {
            byteEncrypted = cipher.doFinal(chuoiKyTu.getBytes());
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(MaHoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(MaHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String encrypted =  Base64.getEncoder().encodeToString(byteEncrypted);
        return encrypted;
    }
}