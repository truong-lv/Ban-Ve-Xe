/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.awt.Color;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author n18dc
 */
public class ChinhSuaKH extends javax.swing.JFrame {

    private String dienThoai;
    private String taiKhoan;
    public ChinhSuaKH(String phone) {
        this.dienThoai=phone;
        initComponents();
        loadKH();
    }
    //======LẤY DỮ LIỆU KHÁCH HÀNG TỪ DATABASE XUỐNG==========
    public void loadKH(){
        Connection connect=KetNoiSQL.KetNoi.layKetNoi();
        String sql="SELECT HoTen,GioiTinh, TaiKhoan FROM HANH_KHACH KH WHERE KH.DienThoai='"+dienThoai+"'";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            // duyet ket qua
            while (rs.next()) {
               txtSDT1.setText(dienThoai);
               txtHoTen1.setText(rs.getString(1));
               if(rs.getString(2).trim().equalsIgnoreCase(rBtnNam1.getText()) ){
                  rBtnNam1.setSelected(true);
               }else {
                   rBtnNu1.setSelected(true);
               }
               txtTK1.setText(rs.getString(3));
               taiKhoan=txtTK1.getText();
            }
            // dong ket noi
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(ChinhSuaKH.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void chinhSuaTT(String dt, String ten, String gt, String taiKhoan){
        Connection connect=KetNoiSQL.KetNoi.layKetNoi();
        String sql="UPDATE HANH_KHACH SET DienThoai=?, HoTen=?, GioiTinh=?, TaiKhoan=? WHERE DienThoai='"+dienThoai+"'";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ps.setString(1, dt);
            ps.setString(2, ten);
            ps.setString(3, gt);
            ps.setString(4, taiKhoan);
            ps.executeUpdate();
            // dong ket noi
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(ChinhSuaKH.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void chinhSuaTK(String taiKhoan, String matKhau){
        Connection connect=KetNoiSQL.KetNoi.layKetNoi();
        String sql="UPDATE TAI_KHOAN SET TaiKhoan=?, MatKhau=? WHERE TaiKhoan='"+taiKhoan+"'";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ps.setString(1, taiKhoan);
//            BanVeXe.setAccount(taiKhoan);
            ps.setString(2, matKhau);
            ps.executeUpdate();
            // dong ket noi
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(ChinhSuaKH.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private boolean ktLoiNhapLieu(){
        boolean ktTen=false, ktGT=false, ktMKF=false, ktSDT=false, ktMK=false;
        // KT Nhập họ tên
        if(txtHoTen1.getText().isEmpty()){
            ktTen=true;
            lbInvalidTen.setVisible(true);
            txtHoTen1.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(!txtHoTen1.getText().isEmpty()) {
            lbInvalidTen.setVisible(false);
            txtHoTen1.setBorder(BorderFactory.createLineBorder(null));

        }
        
        //KT nhập Gioi Tinh
        if(!rBtnNam1.isSelected() && !rBtnNu1.isSelected()){
            ktGT=true;
            lbInvalidGioiTinh.setVisible(true);
        }
        else {
            lbInvalidGioiTinh.setVisible(false);
        }
        
        // KT nhập sđt
        if( !txtSDT1.getText().matches("\\d{9,10}")){
            ktSDT=true;
            lbInvalidSDT.setVisible(true);
            txtSDT1.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(txtSDT1.getText().matches("\\d{9,10}")) {
            lbInvalidSDT.setVisible(false);
            txtSDT1.setBorder(BorderFactory.createLineBorder(null));
        }

        // KT nhập lại mật khẩu
         if(!txtPassComfrim1.getText().equals(txtPass1.getText())){
            ktMKF=true;
            lbInvalidComfirmMK.setVisible(true);
            txtPassComfrim1.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(txtPassComfrim1.getText().equals(txtPass1.getText())) {
            lbInvalidComfirmMK.setVisible(false);
            txtPassComfrim1.setBorder(BorderFactory.createLineBorder(null));
        }
         
        if( ktSDT || ktMK|| ktTen || ktMKF || ktGT){
            return true;
        }else return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtHoTen1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtSDT1 = new javax.swing.JTextField();
        rBtnNam1 = new javax.swing.JRadioButton();
        rBtnNu1 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        lbInvalidGioiTinh = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTK1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtPass1 = new javax.swing.JPasswordField();
        jLabel22 = new javax.swing.JLabel();
        txtPassComfrim1 = new javax.swing.JPasswordField();
        btnOk1 = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        lbInvalidTen = new javax.swing.JLabel();
        lbInvalidSDT = new javax.swing.JLabel();
        lbInvalidTK = new javax.swing.JLabel();
        lbInvalidMK = new javax.swing.JLabel();
        lbInvalidComfirmMK = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Chỉnh sửa thông tin");
        setLocation(new java.awt.Point(600, 350));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Thông tin cá nhân:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Thông tin Tài Khoản:");

        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setText("Họ Tên:");

        txtHoTen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTen1ActionPerformed(evt);
            }
        });

        jLabel18.setText("SĐT:");

        buttonGroup1.add(rBtnNam1);
        rBtnNam1.setText("Nam");
        rBtnNam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnNam1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rBtnNu1);
        rBtnNu1.setText("Nữ");

        jLabel19.setText("Giới Tính");

        lbInvalidGioiTinh.setForeground(new java.awt.Color(255, 0, 0));
        lbInvalidGioiTinh.setText("Vui lòng chọn giới tính");
        lbInvalidGioiTinh.setVisible(false);

        jLabel20.setText("Tên Tài Khoản:");

        jLabel21.setText("Mật khẩu mới:");

        txtPass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPass1ActionPerformed(evt);
            }
        });

        jLabel22.setText("Nhập lại mật khẩu:");

        txtPassComfrim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassComfrim1ActionPerformed(evt);
            }
        });

        btnOk1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Ok-icon.png"))); // NOI18N
        btnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk1ActionPerformed(evt);
            }
        });

        btnCancel1.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Windows-Close-Program-icon.png"))); // NOI18N
        btnCancel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        lbInvalidTen.setForeground(new java.awt.Color(255, 0, 0));
        lbInvalidTen.setText("Vui lòng nhập tên");
        lbInvalidTen.setVisible(false);

        lbInvalidSDT.setForeground(new java.awt.Color(255, 0, 0));
        lbInvalidSDT.setText("Vui lòng nhập đúng SĐT");
        lbInvalidSDT.setVisible(false);

        lbInvalidTK.setForeground(new java.awt.Color(255, 0, 0));
        lbInvalidTK.setText("Vui lòng nhập tài khoản");
        lbInvalidTK.setVisible(false);

        lbInvalidMK.setForeground(new java.awt.Color(255, 0, 0));
        lbInvalidMK.setText("Vui lòng nhập mật khẩu");
        lbInvalidMK.setVisible(false);

        lbInvalidComfirmMK.setForeground(new java.awt.Color(255, 0, 0));
        lbInvalidComfirmMK.setText("Nhập lại mật khẩu ko đúng");
        lbInvalidComfirmMK.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel17))
                                .addGap(0, 113, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSDT1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHoTen1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbInvalidGioiTinh)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rBtnNam1)
                                        .addGap(18, 18, 18)
                                        .addComponent(rBtnNu1))
                                    .addComponent(lbInvalidTen)
                                    .addComponent(lbInvalidSDT))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel15))
                            .addComponent(jLabel20)
                            .addComponent(txtTK1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(txtPass1)
                            .addComponent(jLabel22)
                            .addComponent(txtPassComfrim1)
                            .addComponent(lbInvalidTK)
                            .addComponent(lbInvalidMK)
                            .addComponent(lbInvalidComfirmMK)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTK1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbInvalidTK)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(lbInvalidMK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassComfrim1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbInvalidComfirmMK))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lbInvalidTen)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lbInvalidSDT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rBtnNam1)
                                    .addComponent(rBtnNu1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbInvalidGioiTinh))))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOk1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rBtnNam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnNam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnNam1ActionPerformed

    private void txtPass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPass1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPass1ActionPerformed

    private void txtPassComfrim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassComfrim1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassComfrim1ActionPerformed

    private void btnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk1ActionPerformed
        // TODO add your handling code here:
        if(!ktLoiNhapLieu()){
            int confirm=JOptionPane.showConfirmDialog(this, "Xác nhận cập nhập thông tin");
            if(confirm==JOptionPane.OK_OPTION){
                if(!txtTK1.getText().equals(taiKhoan) || !txtPass1.getText().isEmpty()){
                    chinhSuaTK(txtTK1.getText(), txtPass1.getText());
                }
                String gt=(rBtnNam1.isSelected())?"Nam":"Nữ";
                chinhSuaTT(txtSDT1.getText(), txtHoTen1.getText(), gt, txtTK1.getText());
                JOptionPane.showMessageDialog(this, "Cập nhập thành công");
                this.dispose();
            }
        }
        
    }//GEN-LAST:event_btnOk1ActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void txtHoTen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTen1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTen1ActionPerformed

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
            java.util.logging.Logger.getLogger(ChinhSuaKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChinhSuaKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChinhSuaKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChinhSuaKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnOk1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel lbInvalidComfirmMK;
    private javax.swing.JLabel lbInvalidGioiTinh;
    private javax.swing.JLabel lbInvalidMK;
    private javax.swing.JLabel lbInvalidSDT;
    private javax.swing.JLabel lbInvalidTK;
    private javax.swing.JLabel lbInvalidTen;
    private javax.swing.JRadioButton rBtnNam1;
    private javax.swing.JRadioButton rBtnNu1;
    private javax.swing.JTextField txtHoTen1;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JPasswordField txtPassComfrim1;
    private javax.swing.JTextField txtSDT1;
    private javax.swing.JTextField txtTK1;
    // End of variables declaration//GEN-END:variables
}
