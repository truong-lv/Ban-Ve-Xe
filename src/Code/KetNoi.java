/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class KetNoi {
    public static Connection layKetNoi() {
        Connection ketNoi=null;
        String uRL="jdbc:sqlserver://;databaseName=QLVE_XE2";
        String userName="sa";
        String password="123";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi=DriverManager.getConnection(uRL, userName, password);
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Khong ket noi duoc voi CSDL");
        }
        return ketNoi;
    }
}
