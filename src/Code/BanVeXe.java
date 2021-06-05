
package Code;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;


public class BanVeXe {
    public static String Account="";
    public static String pass="";
    public static String primaryKey="";
    public static String hoTen="";
    public static String gioiTinh="";
    public static String quyen="";
    
    public BanVeXe() {
    }

    public static String getAccount() {
        return Account;
    }

    public static String getQuyen() {
        return quyen;
    }

    public static void setAccount(String Account) {
        BanVeXe.Account = Account;
    }

    public static void setQuyen(String quyen) {
        BanVeXe.quyen = quyen;
    }
    
   public static void setBanVeXe(String Account, String pass, String hoTen, String gioiTinh, String quyen) {
       BanVeXe.Account = Account; 
       BanVeXe.pass = pass;
       BanVeXe.hoTen = hoTen;
       BanVeXe.gioiTinh = gioiTinh;
       BanVeXe.quyen = quyen;
    }
}
