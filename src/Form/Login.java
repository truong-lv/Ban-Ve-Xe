/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;


import Code.BanVeXe;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;


/**
 *
 * @author n18dc
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        txtTK.setText(BanVeXe.Account);
    }
    public  void loadAccount(){
        boolean checkLogin=false;
        Connection connect=Code.KetNoi.layKetNoi();
        String tk=txtTK.getText();
        String mk=psMatkhau.getText();
        //=========================LOAD TÀI KHOẢN TỪ DATABASE============================
        try {
            CallableStatement command = connect.prepareCall("{call SP_LOAD_LOGIN (?,?)}");
             //cung cap gia tri cho bien
            command.setString(1, tk);
            command.setString(2, mk);
            ResultSet rs = command.executeQuery();
            
            // duyet ket qua
            while (rs.next()) {
                BanVeXe.Account=tk;
                BanVeXe.pass=mk;
                BanVeXe.primaryKey=rs.getString(1);
                BanVeXe.hoTen=rs.getString(2);
                BanVeXe.gioiTinh=rs.getString(3);
                BanVeXe.quyen=rs.getString(4);
                checkLogin= true;
            }
            // dong ket noi
            rs.close();
            command.close();
            connect.close();
        } catch (Exception e) {
        }
        
        
        //====================KT NẾU LOAD ĐƯỢC TÀI KHOẢN THÌ CHUYỂN GIAO DIỆN=========================
        if(checkLogin){
                new GDChinh().setVisible(true);
                this.dispose();
  
        }else {
            lbErorrLogin.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLogin = new javax.swing.JPanel();
        lbErorrLogin = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        fLbDangKy = new javax.swing.JLabel();
        psMatkhau = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        BackgroundLogin = new javax.swing.JLabel();
        pndky = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHẦN MỀM BÁN VÉ XE");
        setBackground(new java.awt.Color(4, 25, 45));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setLocation(new java.awt.Point(600, 200));

        pnLogin.setOpaque(false);
        pnLogin.setLayout(null);

        lbErorrLogin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbErorrLogin.setForeground(new java.awt.Color(255, 51, 204));
        lbErorrLogin.setText("Tài hoặc mật khẩu không hợp lệ !!Vui lòng nhập lại");
        lbErorrLogin.setVisible(false);
        pnLogin.add(lbErorrLogin);
        lbErorrLogin.setBounds(50, 470, 330, 33);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nếu bạn chưa có tài khoản?");
        pnLogin.add(jLabel13);
        jLabel13.setBounds(140, 520, 158, 16);

        btnDangNhap.setBackground(new java.awt.Color(242, 227, 57));
        btnDangNhap.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("Đăng Nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        btnDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDangNhapKeyPressed(evt);
            }
        });
        pnLogin.add(btnDangNhap);
        btnDangNhap.setBounds(40, 400, 350, 50);

        fLbDangKy.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        fLbDangKy.setForeground(new java.awt.Color(102, 255, 255));
        fLbDangKy.setText("Đăng Ký");
        fLbDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fLbDangKyMouseClicked(evt);
            }
        });
        pnLogin.add(fLbDangKy);
        fLbDangKy.setBounds(180, 550, 67, 20);

        psMatkhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                psMatkhauKeyPressed(evt);
            }
        });
        pnLogin.add(psMatkhau);
        psMatkhau.setBounds(80, 350, 310, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Đăng Nhập Tài Khoản");
        pnLogin.add(jLabel1);
        jLabel1.setBounds(90, 60, 253, 32);
        pnLogin.add(txtTK);
        txtTK.setBounds(80, 290, 310, 42);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Đồng hành cùng bạn trên mọi hành trình");
        pnLogin.add(jLabel2);
        jLabel2.setBounds(100, 230, 232, 16);
        pnLogin.add(jSeparator1);
        jSeparator1.setBounds(130, 560, 50, 10);
        pnLogin.add(jSeparator2);
        jSeparator2.setBounds(250, 560, 50, 10);

        BackgroundLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageBackground/LoginEdit.png"))); // NOI18N
        pnLogin.add(BackgroundLogin);
        BackgroundLogin.setBounds(0, 0, 422, 670);

        javax.swing.GroupLayout pndkyLayout = new javax.swing.GroupLayout(pndky);
        pndky.setLayout(pndkyLayout);
        pndkyLayout.setHorizontalGroup(
            pndkyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        pndkyLayout.setVerticalGroup(
            pndkyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );

        pnLogin.add(pndky);
        pndky.setBounds(0, 0, 420, 670);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void psMatkhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psMatkhauKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loadAccount();
        }
    }//GEN-LAST:event_psMatkhauKeyPressed

    private void fLbDangKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fLbDangKyMouseClicked
        this.dispose();
        new DangKyKhach().setVisible(true);
    }//GEN-LAST:event_fLbDangKyMouseClicked

    private void btnDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDangNhapKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loadAccount();
        }
    }//GEN-LAST:event_btnDangNhapKeyPressed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
        loadAccount();

    }//GEN-LAST:event_btnDangNhapActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundLogin;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JLabel fLbDangKy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbErorrLogin;
    private javax.swing.JPanel pnLogin;
    private javax.swing.JPanel pndky;
    private javax.swing.JPasswordField psMatkhau;
    private javax.swing.JTextField txtTK;
    // End of variables declaration//GEN-END:variables

}
