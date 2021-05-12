/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Form.Login;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author DucAnh00
 */
public class DangKyKhach extends javax.swing.JFrame {

    /**
     * Creates new form FrameDKY
     */
    public DangKyKhach() {
        initComponents();
    }
    //kiểm tra các lỗi nhập
    public boolean ktLoi(JTextField txtTen, JTextField txtSDT,JTextField txtTK,JPasswordField psMK, JRadioButton rBtnNam,JRadioButton rBtnNu,
                            JLabel lbTen, JLabel lbGioiTinh, JLabel lbSDT, JLabel lbTK, JLabel lbMK){
        boolean ktTen=false,ktGT=false, ktTK=false, ktSDT=false, ktMK=false;
        // KT Nhập họ tên
        if(txtTen.getText().isEmpty()){
            ktTen=true;
            lbTen.setVisible(true);
            txtTen.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(!txtTen.getText().isEmpty()) {
            lbTen.setVisible(false);
            txtTen.setBorder(BorderFactory.createLineBorder(null));

        }

        //KT nhập Gioi Tinh
        if(!rBtnNam.isSelected() && !rBtnNu.isSelected()){
            ktGT=true;
            lbGioiTinh.setVisible(true);
        }
        else {
            lbGioiTinh.setVisible(false);
        }

        // KT nhập sđt
        if( !txtSDT.getText().matches("0\\d{9,10}")){
            ktSDT=true;
            lbSDT.setVisible(true);
            txtSDT.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(txtSDT.getText().matches("0\\d{9,10}")) {
            lbSDT.setVisible(false);
            txtSDT.setBorder(BorderFactory.createLineBorder(null));
        }

        //KT nhập Tai khoản
        if(txtTK.getText().isEmpty()||txtTK.getText().matches("[0-9]{9}")){
            ktTK=true;
            lbTK.setVisible(true);
            txtTK.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(!txtTK.getText().isEmpty()) {
            lbTK.setVisible(false);
            txtTK.setBorder(BorderFactory.createLineBorder(null));
        }

        
        // KT nhập mật khẩu
        if(psMK.getText().isEmpty()){
            ktMK=true;
            lbMK.setVisible(true);
            psMK.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(!psMK.getText().isEmpty()) {
            lbMK.setVisible(false);
            psMK.setBorder(BorderFactory.createLineBorder(null));
        }
        if( ktSDT || ktTen || ktGT ||ktTK || ktMK){
            return false;

        }else return true;
    }
    
    // kiểm tra tài khoản có tồn tại không
    public boolean ktTaiKhoan(String sql0, String khoa){
        String sql= sql0+khoa+"'";
         java.sql.Connection connect=Code.KetNoi.layKetNoi();
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            // duyet ket qua
            while (rs.next()) {
               return true;
            }
            // dong ket noi
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(DangKyKhach.class.getName()).log(Level.SEVERE, null, e);
        }
         return false;
    }
    
    public void themKhachHang(String sdt, String hoten, String gt, String tk){
        java.sql.Connection conn2 = Code.KetNoi.layKetNoi();
                String sql1 ="INSERT INTO HANH_KHACH values (?,?,?,?) ";

                try {
                    PreparedStatement ps1=conn2.prepareStatement(sql1);
                    ps1.setString(1, sdt);
                    ps1.setString(2, hoten);
                    ps1.setString(3, gt);
                    ps1.setString(4, tk);
                    ps1.executeUpdate();
                    ps1.close();
                    conn2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DangKyKhach.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void themTaiKhoan(String tk, String mk, String maQuyen){
        java.sql.Connection conn = Code.KetNoi.layKetNoi();
                String sql= "INSERT INTO TAI_KHOAN values (?,?,?) ";

                 try {
                     PreparedStatement ps = conn.prepareStatement(sql);
                     ps.setString(1, tk);
                     ps.setString(2, mk);
                     ps.setString(3, "2");
                     ps.executeUpdate();
                     ps.close();
                     conn.close();
                 } catch (SQLException e) {
                     Logger.getLogger(DangKyKhach.class.getName()).log(Level.SEVERE, null, e);
                 }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnDKY = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtTK = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbTK = new javax.swing.JLabel();
        lbTen = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        lbMK = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbDangKy = new javax.swing.JLabel();
        psMK = new javax.swing.JPasswordField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        rBtnNam = new javax.swing.JRadioButton();
        rBtnNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        lbGioiTinh = new javax.swing.JLabel();
        btnDangKy = new javax.swing.JButton();
        lbBackgroundDky = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(600, 200));

        pnDKY.setOpaque(false);
        pnDKY.setLayout(null);

        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        pnDKY.add(jLabel1);
        jLabel1.setBounds(208, 120, 2, 340);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Đăng Ký Khách Hàng");
        pnDKY.add(jLabel4);
        jLabel4.setBounds(130, 30, 210, 32);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Họ Tên:");
        pnDKY.add(jLabel7);
        jLabel7.setBounds(10, 230, 60, 16);
        pnDKY.add(txtSDT);
        txtSDT.setBounds(10, 140, 190, 42);
        pnDKY.add(txtTen);
        txtTen.setBounds(10, 260, 190, 42);
        pnDKY.add(txtTK);
        txtTK.setBounds(10, 370, 190, 42);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tài Khoản:");
        pnDKY.add(jLabel8);
        jLabel8.setBounds(10, 350, 62, 16);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("SĐT:");
        pnDKY.add(jLabel9);
        jLabel9.setBounds(10, 120, 30, 16);

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mật khẩu:");
        pnDKY.add(jLabel10);
        jLabel10.setBounds(220, 340, 70, 16);

        lbTK.setForeground(new java.awt.Color(255, 153, 204));
        lbTK.setText("Vui lòng nhập Tài Khoản");
        lbTK.setVisible(false);
        pnDKY.add(lbTK);
        lbTK.setBounds(20, 420, 150, 16);

        lbTen.setForeground(new java.awt.Color(255, 153, 204));
        lbTen.setText("Vui lòng nhập Tên.");
        lbTen.setVisible(false);
        pnDKY.add(lbTen);
        lbTen.setBounds(10, 310, 108, 16);

        lbSDT.setForeground(new java.awt.Color(255, 153, 204));
        lbSDT.setText("Vui lòng nhập SĐT");
        lbSDT.setVisible(false);
        pnDKY.add(lbSDT);
        lbSDT.setBounds(10, 190, 107, 16);

        lbMK.setForeground(new java.awt.Color(255, 153, 204));
        lbMK.setText("Vui lòng nhập Mật khẩu.");
        lbMK.setVisible(false);
        pnDKY.add(lbMK);
        lbMK.setBounds(240, 440, 138, 16);

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Từ 6 đến 50 ký tự");
        pnDKY.add(jLabel12);
        jLabel12.setBounds(230, 420, 102, 16);

        lbDangKy.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbDangKy.setForeground(new java.awt.Color(102, 255, 255));
        lbDangKy.setText("Đăng nhập");
        lbDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDangKyMouseClicked(evt);
            }
        });
        pnDKY.add(lbDangKy);
        lbDangKy.setBounds(160, 600, 87, 20);
        pnDKY.add(psMK);
        psMK.setBounds(220, 370, 190, 42);
        pnDKY.add(jSeparator3);
        jSeparator3.setBounds(110, 610, 50, 10);
        pnDKY.add(jSeparator4);
        jSeparator4.setBounds(250, 610, 50, 10);

        buttonGroup1.add(rBtnNam);
        rBtnNam.setText("Nam");
        pnDKY.add(rBtnNam);
        rBtnNam.setBounds(240, 270, 55, 25);

        buttonGroup1.add(rBtnNu);
        rBtnNu.setText("Nữ");
        pnDKY.add(rBtnNu);
        rBtnNu.setBounds(320, 270, 45, 25);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Giới Tính:");
        pnDKY.add(jLabel11);
        jLabel11.setBounds(220, 230, 70, 16);

        lbGioiTinh.setForeground(new java.awt.Color(255, 153, 204));
        lbGioiTinh.setText("Vui lòng chọn giới tính");
        lbGioiTinh.setVisible(false);
        pnDKY.add(lbGioiTinh);
        lbGioiTinh.setBounds(250, 310, 126, 16);

        btnDangKy.setBackground(new java.awt.Color(242, 227, 57));
        btnDangKy.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnDangKy.setForeground(new java.awt.Color(255, 255, 255));
        btnDangKy.setText("Đăng Ký");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });
        pnDKY.add(btnDangKy);
        btnDangKy.setBounds(100, 520, 220, 50);

        lbBackgroundDky.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageBackground/backgroundDky.png"))); // NOI18N
        pnDKY.add(lbBackgroundDky);
        lbBackgroundDky.setBounds(0, 0, 430, 690);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnDKY, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnDKY, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbDangKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDangKyMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_lbDangKyMouseClicked

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed

        if( ktLoi(txtTen, txtSDT, txtTK, psMK, rBtnNam, rBtnNu, lbTen, lbGioiTinh, lbSDT, lbTK,lbMK)){
           String sđt= txtSDT.getText();
           String hoten=txtTen.getText();
           String tk= txtTK.getText();
           String mk= psMK.getText();
           String gt=(rBtnNam.isSelected())?"Nam":"Nữ";
           if(!ktTaiKhoan("SELECT * FROM TAI_KHOAN WHERE TaiKhoan = '",tk)){
               themTaiKhoan(tk, mk, hoten);
           }else{ JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại. Vui lòng nhập lại!");return;}
           
            if(!ktTaiKhoan("SELECT * FROM HANH_KHACH WHERE DienThoai = '",sđt)) {
                themKhachHang(sđt, hoten, gt, tk);
                JOptionPane.showMessageDialog(this, "Đăng ký thành công");
            }
            else JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại. Vui lòng nhập lại!");
           
       }
        
    }//GEN-LAST:event_btnDangKyActionPerformed

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
            java.util.logging.Logger.getLogger(DangKyKhach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKyKhach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKyKhach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKyKhach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangKyKhach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangKy;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbBackgroundDky;
    private javax.swing.JLabel lbDangKy;
    private javax.swing.JLabel lbGioiTinh;
    private javax.swing.JLabel lbMK;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbTK;
    private javax.swing.JLabel lbTen;
    private javax.swing.JPanel pnDKY;
    private javax.swing.JPasswordField psMK;
    private javax.swing.JRadioButton rBtnNam;
    private javax.swing.JRadioButton rBtnNu;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
