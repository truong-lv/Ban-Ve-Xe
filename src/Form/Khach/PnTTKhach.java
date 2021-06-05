/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Khach;

import Code.BanVeXe;
import Form.Login;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author n18dc
 */
public class PnTTKhach extends javax.swing.JPanel {

    private String dienThoai;
    private JLabel lbAc;
    public PnTTKhach(String phone, JLabel lb) {
        this.dienThoai=phone;
        this.lbAc=lb;
        initComponents();
        loadKH();
    }
    
    private void setEditableTxt(boolean bool, Color col){
        txtHoTen1.setEditable(bool);
        lbHoTen.setForeground(col);
        txtTK1.setEditable(bool);
        lbTK.setForeground(col);
        lbGioiTinh.setForeground(col);
        rBtnNam1.setEnabled(bool);
        rBtnNu1.setEnabled(bool);
    }
    public boolean ktTrungTaiKhoan(String tk){
        java.sql.Connection conn = Code.KetNoi.layKetNoi();
        String sql="SELECT * FROM TAI_KHOAN WHERE TaiKhoan = '"+tk+"' AND TaiKhoan !='"+BanVeXe.Account+"'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại");
                return true;
            }
            rs.close();
            ps.close();
            conn.close();
          } catch (SQLException e) {
          Logger.getLogger(PnTTKhach.class.getName()).log(Level.SEVERE, null, e);
         }
        return false;
    }
    //======LẤY DỮ LIỆU KHÁCH HÀNG TỪ DATABASE XUỐNG==========
    public void loadKH(){
        Connection connect=Code.KetNoi.layKetNoi();
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
            }
            // dong ket noi
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(PnTTKhach.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void chinhSuaTT(String dt, String ten, String gt, String tk){
        Connection connect=Code.KetNoi.layKetNoi();
        String sql="UPDATE HANH_KHACH SET DienThoai=?, HoTen=?, GioiTinh=?, TaiKhoan=? WHERE DienThoai='"+dienThoai+"'";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ps.setString(1, dt);
            ps.setString(2, ten);
            ps.setString(3, gt);
            ps.setString(4, tk);
            ps.executeUpdate();
            // dong ket noi
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(PnTTKhach.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void chinhSuaTK(String tk, String matKhau){
        Connection connect=Code.KetNoi.layKetNoi();
        String sql="UPDATE TAI_KHOAN SET TaiKhoan=?, MatKhau=? WHERE TaiKhoan='"+BanVeXe.Account+"'";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ps.setString(1, tk);
            ps.setString(2, matKhau);
            ps.executeUpdate();
            // dong ket noi
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(PnTTKhach.class.getName()).log(Level.SEVERE, null, e);
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

        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbHoTen = new javax.swing.JLabel();
        txtHoTen1 = new javax.swing.JTextField();
        lbSDT = new javax.swing.JLabel();
        txtSDT1 = new javax.swing.JTextField();
        rBtnNam1 = new javax.swing.JRadioButton();
        rBtnNu1 = new javax.swing.JRadioButton();
        lbGioiTinh = new javax.swing.JLabel();
        lbInvalidGioiTinh = new javax.swing.JLabel();
        lbTK = new javax.swing.JLabel();
        txtTK1 = new javax.swing.JTextField();
        lbMkMoi = new javax.swing.JLabel();
        txtPass1 = new javax.swing.JPasswordField();
        lbNhapLaiMK = new javax.swing.JLabel();
        txtPassComfrim1 = new javax.swing.JPasswordField();
        btnChinhSua = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        lbInvalidTen = new javax.swing.JLabel();
        lbInvalidSDT = new javax.swing.JLabel();
        lbInvalidTK = new javax.swing.JLabel();
        lbInvalidMK = new javax.swing.JLabel();
        lbInvalidComfirmMK = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel14.setText("Thông tin cá nhân:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel15.setText("Thông tin Tài Khoản:");

        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbHoTen.setText("Họ Tên:");

        txtHoTen1.setEditable(false);
        txtHoTen1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        lbSDT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbSDT.setText("SĐT:");

        txtSDT1.setEditable(false);
        txtSDT1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        rBtnNam1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rBtnNam1.setText("Nam");
        rBtnNam1.setEnabled(false);
        rBtnNam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnNam1ActionPerformed(evt);
            }
        });

        rBtnNu1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rBtnNu1.setText("Nữ");
        rBtnNu1.setEnabled(false);

        lbGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbGioiTinh.setText("Giới Tính:");

        lbInvalidGioiTinh.setForeground(new java.awt.Color(255, 0, 0));
        lbInvalidGioiTinh.setText("Vui lòng chọn giới tính");
        lbInvalidGioiTinh.setVisible(false);

        lbTK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTK.setText("Tên Tài Khoản:");

        txtTK1.setEditable(false);
        txtTK1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        lbMkMoi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbMkMoi.setForeground(Color.GREEN);
        lbMkMoi.setText("Mật khẩu mới (nếu muốn thay đổi):");
        lbMkMoi.setVisible(false);

        txtPass1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPass1.setVisible(false);
        txtPass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPass1ActionPerformed(evt);
            }
        });

        lbNhapLaiMK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbNhapLaiMK.setForeground(Color.GREEN);
        lbNhapLaiMK.setText("Nhập lại mật khẩu:");
        lbNhapLaiMK.setVisible(false);

        txtPassComfrim1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtPassComfrim1.setVisible(false);
        txtPassComfrim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassComfrim1ActionPerformed(evt);
            }
        });

        btnChinhSua.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnChinhSua.setText("Chỉnh sửa");
        btnChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaActionPerformed(evt);
            }
        });

        btnCancel1.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCancel1.setText("Hủy");
        btnCancel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCancel1.setEnabled(false);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(418, 418, 418)
                .addComponent(btnChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbGioiTinh)
                            .addComponent(lbInvalidGioiTinh)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rBtnNam1)
                                .addGap(18, 18, 18)
                                .addComponent(rBtnNu1))
                            .addComponent(lbHoTen)
                            .addComponent(lbInvalidTen)
                            .addComponent(txtHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbSDT)
                                        .addComponent(lbInvalidSDT))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtSDT1)))
                        .addGap(209, 209, 209)
                        .addComponent(jLabel16)
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMkMoi)
                            .addComponent(lbTK)
                            .addComponent(lbNhapLaiMK)
                            .addComponent(lbInvalidTK)
                            .addComponent(lbInvalidMK)
                            .addComponent(lbInvalidComfirmMK)
                            .addComponent(txtPassComfrim1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTK1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)))
                .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lbSDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lbInvalidSDT)
                        .addGap(27, 27, 27)
                        .addComponent(lbHoTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbInvalidTen)
                        .addGap(18, 18, 18)
                        .addComponent(lbGioiTinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rBtnNam1)
                            .addComponent(rBtnNu1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbInvalidGioiTinh))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lbTK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTK1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbInvalidTK)
                        .addGap(26, 26, 26)
                        .addComponent(lbMkMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lbInvalidMK)
                        .addGap(14, 14, 14)
                        .addComponent(lbNhapLaiMK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassComfrim1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbInvalidComfirmMK)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );
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

    private void btnChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaActionPerformed

        // Nếu btn đang ở trạng thái "Chỉnh sửa" thì mở khóa các txt và chuyển qua trạng thái "Lưu"
        if(btnChinhSua.getText().equals("Chỉnh sửa")){
            setEditableTxt(true, Color.GREEN);
            lbMkMoi.setVisible(true);
            lbNhapLaiMK.setVisible(true);
            txtPass1.setVisible(true);
            txtPassComfrim1.setVisible(true);
            btnChinhSua.setText("Lưu");
            btnCancel1.setEnabled(true);
        }else{// Nếu btn đang ở trạng thái "Lưu" thì tiến hành lưu thông tin đã chỉnh sửa và khóa các txt lại
            if(!ktLoiNhapLieu()){// kiểm tra các lỗi nhập 
                
                if(ktTrungTaiKhoan(txtTK1.getText())){return;}// kt tài khoản mà khách chỉnh sửa cá trùng với tk khác ko
                // hiện dialog cho người dùng xác nhận lại
                int confirm=JOptionPane.showConfirmDialog(this, "Xác nhận cập nhập thông tin");
                if(confirm==JOptionPane.OK_OPTION)// xác nhận == OK
                {
                    // Nếu tại khoản mật khẩu bị gì thay đổi thì cập nhập lại tài khoản mật khẩu trước
                    if(!txtTK1.getText().equals(BanVeXe.Account)){
                        chinhSuaTK(txtTK1.getText(), (!txtPass1.getText().isEmpty())?txtPass1.getText():BanVeXe.pass);
                        
                    }
                    String gt=(rBtnNam1.isSelected())?"Nam":"Nữ";// chuyển giới tính sang String
                    
                    //Thực hiện câu truy vấn chỉnh sửa thông tin cá nhân khách
                    chinhSuaTT(txtSDT1.getText(), txtHoTen1.getText(), gt, txtTK1.getText());

                    JOptionPane.showMessageDialog(this, "Cập nhập thành công");
                    BanVeXe.setBanVeXe(txtTK1.getText(), txtPass1.getText(), txtHoTen1.getText(), gt, BanVeXe.quyen);
                    lbAc.setText(BanVeXe.Account);
                    //Khóa các txt lại
                    setEditableTxt(false, Color.BLACK);
                    lbMkMoi.setVisible(false);
                    lbNhapLaiMK.setVisible(false);
                    txtPass1.setVisible(false);
                    txtPassComfrim1.setVisible(false);
                    btnChinhSua.setText("Chỉnh sửa");
                    btnCancel1.setEnabled(false);
                }
            }
        }

    }//GEN-LAST:event_btnChinhSuaActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
        loadKH();
        setEditableTxt(false, Color.BLACK);
        lbMkMoi.setVisible(false);
        lbNhapLaiMK.setVisible(false);
        txtPass1.setVisible(false);
        txtPassComfrim1.setVisible(false);
        btnChinhSua.setText("Chỉnh sửa");
        btnCancel1.setEnabled(false);
    }//GEN-LAST:event_btnCancel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnChinhSua;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel lbGioiTinh;
    private javax.swing.JLabel lbHoTen;
    private javax.swing.JLabel lbInvalidComfirmMK;
    private javax.swing.JLabel lbInvalidGioiTinh;
    private javax.swing.JLabel lbInvalidMK;
    private javax.swing.JLabel lbInvalidSDT;
    private javax.swing.JLabel lbInvalidTK;
    private javax.swing.JLabel lbInvalidTen;
    private javax.swing.JLabel lbMkMoi;
    private javax.swing.JLabel lbNhapLaiMK;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbTK;
    private javax.swing.JRadioButton rBtnNam1;
    private javax.swing.JRadioButton rBtnNu1;
    private javax.swing.JTextField txtHoTen1;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JPasswordField txtPassComfrim1;
    private javax.swing.JTextField txtSDT1;
    private javax.swing.JTextField txtTK1;
    // End of variables declaration//GEN-END:variables
}
