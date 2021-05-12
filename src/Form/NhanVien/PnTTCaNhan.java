/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.NhanVien;

import Code.BanVeXe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author n18dc
 */
public class PnTTCaNhan extends javax.swing.JPanel {

    /**
     * Creates new form PnTTCaNhan
     */
    public PnTTCaNhan() {
        initComponents();
        loadThongTinNV();
        
        //quyenChinhSua();
    }

//    private void quyenChinhSua(){// chỉ có admin đc hiệu chỉnh thông tin cá nhân
//        if(BanVeXe.quyen.equals("Quản lý")){
//            
//            btnChinhSua.setVisible(true);
//        }else{
//            lbThongBao.setVisible(true);
//        }
//    }
    private void setEdit(boolean trangThai){
        txtMaNV.setEditable(trangThai);
        txtTen.setEditable(trangThai);
        txtCMND.setEditable(trangThai);
        txtTK.setEditable(trangThai);
        txtMK.setEditable(trangThai);
        cbbChucVu.enable(trangThai);
        txtSDT.setEditable(trangThai);
        rBtnNam1.setEnabled(trangThai);
        rBtnNu1.setEnabled(trangThai);
    }
    
    private void loadCbbChucVu(){
        cbbChucVu.removeAllItems();
        Connection connect=Code.KetNoi.layKetNoi();
        String sql="SELECT MaLoaiNV, ChucVu  FROM LOAI_NHAN_VIEN";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                cbbChucVu.addItem(rs.getString(1)+"- "+rs.getString(2));
            }
        } catch (Exception e) {
        }
    }
    
    private void loadThongTinNV(){
        Connection connect=Code.KetNoi.layKetNoi();
        String sql="SELECT CMND, GioiTinh, DienThoai, NV.MaLoaiNV, LNV.ChucVu FROM NHAN_VIEN NV,LOAI_NHAN_VIEN LNV WHERE MaNV='"+BanVeXe.primaryKey+"' AND NV.MaLoaiNV=LNV.MaLoaiNV";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                txtMaNV.setText(BanVeXe.primaryKey);
                txtTen.setText(BanVeXe.hoTen);
                txtCMND.setText(rs.getString(1));
                if(rs.getString(2).equalsIgnoreCase("Nam")){rBtnNam1.setSelected(true);}
                txtSDT.setText(rs.getString(3));
                cbbChucVu.addItem(rs.getString(4)+"- "+rs.getString(5));
                txtTK.setText(BanVeXe.Account);
                
            }
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(PnTTCaNhan.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
   
    private void loadHieuChinhTT(String maNV, String ten, String CMND, String gioiTinh, String chucVu, String sdt, String taiKhoan, String matKhau){
        Connection connect=Code.KetNoi.layKetNoi();
        if(!taiKhoan.equals(BanVeXe.Account)||!matKhau.equals(BanVeXe.pass)){// kiểm tra thông tin tài khoản có khác hiện tại ko
            String sql="UPDATE TAI_KHOAN SET TaiKhoan=?, MatKhau=? WHERE TaiKhoan='"+taiKhoan+"'";
            try {
                PreparedStatement ps=connect.prepareStatement(sql);
                ps.setString(1, taiKhoan);
                ps.setString(2, matKhau);
                ps.executeUpdate();
                // dong ket noi
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(PnTTCaNhan.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        String sql="UPDATE NHAN_VIEN SET HoTen=?, CMND=?, GioiTinh=?, DienThoai=?,MaLoaiNV=? WHERE MaNV='"+maNV+"'";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, CMND);
            ps.setString(3, gioiTinh);
            ps.setString(4, sdt);
            ps.setString(5, chucVu);
            ps.executeUpdate();
            // dong ket noi
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(PnTTCaNhan.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbThongBao = new javax.swing.JLabel();
        btnSeePass = new javax.swing.JButton();
        txtMaNV = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtTK = new javax.swing.JTextField();
        rBtnNam1 = new javax.swing.JRadioButton();
        rBtnNu1 = new javax.swing.JRadioButton();
        btnChinhSua = new javax.swing.JButton();
        txtMK = new javax.swing.JTextField();
        cbbChucVu = new javax.swing.JComboBox<>();
        btnHuy = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("Thông tin cá nhân:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setText("Thông tin Tài Khoản:");

        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel17.setText("Họ Tên:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel18.setText("SĐT:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel19.setText("Giới Tính:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel20.setText("Tên Tài Khoản:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel21.setText("Mật khẩu:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel23.setText("Mã NV:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel22.setText("CMND/CCCD:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel24.setText("Chức vụ:");

        lbThongBao.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbThongBao.setForeground(new java.awt.Color(255, 51, 51));
        lbThongBao.setText("Nhân viên chỉ có quyền đổi mật khẩu");
        lbThongBao.setVisible(false);

        btnSeePass.setForeground(new java.awt.Color(255, 255, 255));
        btnSeePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Eye-2-icon.png"))); // NOI18N
        btnSeePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeePassActionPerformed(evt);
            }
        });

        txtMaNV.setEditable(false);
        txtMaNV.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        txtTen.setEditable(false);
        txtTen.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        txtCMND.setEditable(false);
        txtCMND.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        txtSDT.setEditable(false);
        txtSDT.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        txtTK.setEditable(false);
        txtTK.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        buttonGroup1.add(rBtnNam1);
        rBtnNam1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rBtnNam1.setText("Nam");
        rBtnNam1.setEnabled(false);

        buttonGroup1.add(rBtnNu1);
        rBtnNu1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rBtnNu1.setSelected(true);
        rBtnNu1.setText("Nữ");
        rBtnNu1.setEnabled(false);

        btnChinhSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnChinhSua.setText("Chỉnh sửa");
        btnChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaActionPerformed(evt);
            }
        });

        txtMK.setEditable(false);
        txtMK.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txtMK.setText("**********");

        cbbChucVu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setVisible(false);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(466, 466, 466)
                .addComponent(btnChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(546, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rBtnNam1)
                                    .addGap(29, 29, 29)
                                    .addComponent(rBtnNu1))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(13, 13, 13)
                                            .addComponent(jLabel23))
                                        .addComponent(jLabel17))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(32, 32, 32))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel22))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel14))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(166, 166, 166))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbThongBao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSeePass, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(130, 130, 130))))
            .addGroup(layout.createSequentialGroup()
                .addGap(463, 463, 463)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rBtnNam1)
                                        .addComponent(rBtnNu1)))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21)
                                .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSeePass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(27, 27, 27)
                        .addComponent(lbThongBao)))
                .addGap(43, 43, 43)
                .addComponent(btnChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeePassActionPerformed
        // TODO add your handling code here:
        if(txtMK.getText().equals("**********")){
            txtMK.setText(BanVeXe.pass);
        }else txtMK.setText("**********");
    }//GEN-LAST:event_btnSeePassActionPerformed

    private void btnChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaActionPerformed
        // TODO add your handling code here:
        if(btnChinhSua.getText().equalsIgnoreCase("Chỉnh sửa")){//chuyển qua chức năng chỉnh sửa
            btnChinhSua.setText("Lưu");
            btnHuy.setVisible(true);
            txtMK.setText(BanVeXe.pass);
            
            if(BanVeXe.quyen.equals("Nhân viên")){
                txtMK.setEditable(true);
                lbThongBao.setVisible(true);
            }else
            {
                setEdit(true);
                loadCbbChucVu();
            }
            
        }else{// chuyển qua chức năng xem
            
            String maNV=txtMaNV.getText();
            String ten=txtTen.getText();
            String gt=(rBtnNam1.isSelected())?"Nam":"Nữ";
            String cmnd=txtCMND.getText();
            String sdt=txtSDT.getText();
            String tk=txtTK.getText();
            String mk=txtMK.getText();
            String chucVu=cbbChucVu.getSelectedItem().toString();
            chucVu=chucVu.substring(0, chucVu.indexOf("-"));
            loadHieuChinhTT(maNV, ten, cmnd, gt, chucVu, sdt, tk, mk);
            JOptionPane.showMessageDialog(this, "Hiệu chỉnh thông tin thành công");
            
            btnChinhSua.setText("Chỉnh sửa");
            btnHuy.setVisible(false);
            setEdit(false);
            lbThongBao.setVisible(false);
            txtMK.setText("**********");
        }
    }//GEN-LAST:event_btnChinhSuaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        btnChinhSua.setText("Chỉnh sửa");
        btnHuy.setVisible(false);
        setEdit(false);
        loadThongTinNV();
    }//GEN-LAST:event_btnHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChinhSua;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSeePass;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChucVu;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel lbThongBao;
    private javax.swing.JRadioButton rBtnNam1;
    private javax.swing.JRadioButton rBtnNu1;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
