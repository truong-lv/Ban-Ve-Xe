
package Form;


public class BanVeXe {
    public static String Account="";
    public static String pass="";
    public static String primaryKey="";
    public static String hoTen="";
    public static int quyen=-1;
    
    public BanVeXe() {
    }

    public static String getAccount() {
        return Account;
    }

    public static int getQuyen() {
        return quyen;
    }

    public static void setAccount(String Account) {
        BanVeXe.Account = Account;
    }

    public static void setQuyen(int quyen) {
        BanVeXe.quyen = quyen;
    }
    
    
    public static String quyenToString(){
        String str="";
        switch (quyen) {
            case 0:
                str="Quản Lý";
                break;
            case 1:
                str="Nhân Viên";
                break;
            case 2:
                str="Khách";
                break;
            default:
                break;
        }

        return str;
    }
}
