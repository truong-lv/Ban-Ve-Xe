/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

import Form.ThemNV;
import Code.KetNoi;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author n18dc
 */
public class PnQLyNhanVien extends javax.swing.JPanel {

    /**
     * Creates new form PnQLyNhanVien
     */
    public PnQLyNhanVien() {
        initComponents();
        loadNhanVien();
        jButton_ThemTK.setVisible(false);
        jPanel_TaoTK.setVisible(false);
        lamMoiTextfield();
    }

    // Hàm load nhân viên vào bảng
    public void loadNhanVien() {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "select * from NHAN_VIEN";
        Vector vt;

        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString(1)); // lấy dữ liệu ở cột 1
                vt.add(rs.getString(2)); // lấy dữ liệu ở cột 2
                vt.add(rs.getString(3)); // lấy dữ liệu ở cột 3
                vt.add(rs.getString(4)); // lấy dữ liệu ở cột 4
                vt.add(rs.getString(5)); // lấy dữ liệu ở cột 5
                vt.add(rs.getString(6)); // lấy dữ liệu ở cột 6
                vt.add(rs.getString(7)); // lấy dữ liệu ở cột 7
                dtm.addRow(vt);
            }
            jTable1.setModel(dtm);
        } catch (Exception e) {
        }
    }

    // Hàm Thêm Tạo Khoản Mới
    public void taoTaiKhoan(String tk, String mk, String quyen) {
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "insert into TAI_KHOAN values (?,?,?)";

        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, tk);
            ps.setString(2, mk);
            ps.setString(3, quyen);

            ps.close();
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(PnQLyNhanVien.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Hàm Thêm Tài Khoản Cho Nhân Viên
    public void themTaiKhoan(String maNV, String tk) {
        Connection connect = Code.KetNoi.layKetNoi();
        String sql = "UPDATE NHAN_VIEN SET TaiKhoan=? where MaNV=?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, tk);
            ps.setString(2, maNV);

            ps.executeUpdate();
            ps.close();
            connect.close();
            JOptionPane.showMessageDialog(this, "Thêm Tài Khoản Thành Công");
            lamMoiTextfield();
        } catch (SQLException e) {
            Logger.getLogger(PnQLyNhanVien.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Hàm Làm Mới Các TextField
    public void lamMoiTextfield() {
        jTextField_TaoMoi.setText("");
        jPasswordField_MK.setText("");
        jPasswordField_MK2.setText("");
        jLabel_ktMK.setVisible(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_TaiKhoan = new javax.swing.JLabel();
        jLabel_ChucVu = new javax.swing.JLabel();
        jLabel_TenNV = new javax.swing.JLabel();
        jLabel_MaNV = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_ThemNV = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton_ThemTK = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton_ThemNV1 = new javax.swing.JButton();
        jButton_ThemNV2 = new javax.swing.JButton();
        jTextField_MaNV = new javax.swing.JTextField();
        jTextField_TenNV = new javax.swing.JTextField();
        jTextField_ChucVu = new javax.swing.JTextField();
        jTextField_TaiKhoan = new javax.swing.JTextField();
        jPanel_TaoTK = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton_DangKy = new javax.swing.JButton();
        jTextField_TaoMoi = new javax.swing.JTextField();
        jPasswordField_MK2 = new javax.swing.JPasswordField();
        jPasswordField_MK = new javax.swing.JPasswordField();
        jLabel_ktMK = new javax.swing.JLabel();

        jLabel_TaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel_ChucVu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel_TenNV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel_MaNV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel_MaNV.setForeground(new java.awt.Color(255, 0, 0));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ Tên", "CMND", "Giới Tính", "Điện Thoại", "Chức Vụ", "Tài Khoản"
            }
        ));
        jTable1.setRowHeight(23);
        jTable1.setRowMargin(3);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton_ThemNV.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton_ThemNV.setText("Thêm Nhân Viên");
        jButton_ThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemNVActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Mã NV :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Tài Khoản :");

        jButton_ThemTK.setText("Thêm Tài Khoản");
        jButton_ThemTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ThemTKMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Chức Vụ :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Tên NV :");

        jButton_ThemNV1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton_ThemNV1.setText("Xóa Nhân viên");
        jButton_ThemNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemNV1ActionPerformed(evt);
            }
        });

        jButton_ThemNV2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton_ThemNV2.setText("Sửa Nhân viên");
        jButton_ThemNV2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemNV2ActionPerformed(evt);
            }
        });

        jTextField_MaNV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jTextField_TenNV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jTextField_ChucVu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jTextField_TaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Tài Khoản :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Mật Khẩu :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Nhập Lại Mật Khẩu :");

        jButton_DangKy.setText("Đăng Ký Tài Khoản");
        jButton_DangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DangKyActionPerformed(evt);
            }
        });

        jTextField_TaoMoi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jPasswordField_MK2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jPasswordField_MK.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel_ktMK.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel_ktMK.setForeground(new java.awt.Color(255, 51, 51));
        jLabel_ktMK.setText("MK không khớp");

        javax.swing.GroupLayout jPanel_TaoTKLayout = new javax.swing.GroupLayout(jPanel_TaoTK);
        jPanel_TaoTK.setLayout(jPanel_TaoTKLayout);
        jPanel_TaoTKLayout.setHorizontalGroup(
            jPanel_TaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TaoTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_TaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel_TaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_TaoTKLayout.createSequentialGroup()
                        .addGroup(jPanel_TaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_TaoMoi)
                            .addComponent(jPasswordField_MK2)
                            .addComponent(jPasswordField_MK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_ktMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel_TaoTKLayout.createSequentialGroup()
                        .addComponent(jButton_DangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 251, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_TaoTKLayout.setVerticalGroup(
            jPanel_TaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_TaoTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_TaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_TaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_TaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordField_MK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel_TaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_TaoTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jPasswordField_MK2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_ktMK))
                    .addComponent(jLabel7))
                .addGap(29, 29, 29)
                .addComponent(jButton_DangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(7, 7, 7)
                                    .addComponent(jTextField_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField_TenNV)
                                        .addComponent(jTextField_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_ThemTK, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jPanel_TaoTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_ThemNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_ThemNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_ThemNV2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton_ThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_ThemNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jButton_ThemNV2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_TaoTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextField_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel5)
                                        .addGap(21, 21, 21))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextField_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_ThemTK, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(63, 63, 63))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jTextField_MaNV.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        jTextField_ChucVu.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 5));
        jTextField_TenNV.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        jPanel_TaoTK.setVisible(false);
        if ((String) jTable1.getValueAt(jTable1.getSelectedRow(), 6) != null) {
            jTextField_TaiKhoan.setForeground(Color.BLACK);
            jTextField_TaiKhoan.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 6));
            jButton_ThemTK.setVisible(false);
        } else {
            jTextField_TaiKhoan.setForeground(Color.RED);
            jTextField_TaiKhoan.setText("Chưa Có Tài Khoản");
            jButton_ThemTK.setVisible(true);
        }
        lamMoiTextfield();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
        loadNhanVien();
    }//GEN-LAST:event_jTable1KeyTyped

    private void jButton_ThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemNVActionPerformed
        // TODO add your handling code here:
        new ThemNV().setVisible(true);
    }//GEN-LAST:event_jButton_ThemNVActionPerformed

    private void jButton_ThemTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ThemTKMouseClicked
        // TODO add your handling code here:
        jPanel_TaoTK.setVisible(true);
    }//GEN-LAST:event_jButton_ThemTKMouseClicked

    private void jButton_DangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DangKyActionPerformed
        // TODO add your handling code here:
        if (!jTextField_TaoMoi.getText().equals("") && !String.valueOf(jPasswordField_MK.getPassword()).equals("") && !String.valueOf(jPasswordField_MK2.getPassword()).equals("")) {
            if (String.valueOf(jPasswordField_MK.getPassword()).equals(String.valueOf(jPasswordField_MK2.getPassword()))) {
                String tk = jTextField_TaoMoi.getText();
                String mk = String.valueOf(jPasswordField_MK.getPassword());
                String maNV = jTextField_MaNV.getText();
                String quyen = "";
                if (jTextField_ChucVu.getText().equals("Quản Lý")) {
                    quyen = "0";
                } else if (jTextField_ChucVu.getText().equals("NV bán vé")) {
                    quyen = "1";
                }
                if (quyen != "") {
                    taoTaiKhoan(tk, mk, quyen);
                    themTaiKhoan(maNV, tk);
                }
            } else {
                jLabel_ktMK.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Điền Đầy Đủ Thông Tin");
        }
    }//GEN-LAST:event_jButton_DangKyActionPerformed

    private void jButton_ThemNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemNV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ThemNV1ActionPerformed

    private void jButton_ThemNV2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemNV2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ThemNV2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_DangKy;
    private javax.swing.JButton jButton_ThemNV;
    private javax.swing.JButton jButton_ThemNV1;
    private javax.swing.JButton jButton_ThemNV2;
    private javax.swing.JButton jButton_ThemTK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_ChucVu;
    private javax.swing.JLabel jLabel_MaNV;
    private javax.swing.JLabel jLabel_TaiKhoan;
    private javax.swing.JLabel jLabel_TenNV;
    private javax.swing.JLabel jLabel_ktMK;
    private javax.swing.JPanel jPanel_TaoTK;
    private javax.swing.JPasswordField jPasswordField_MK;
    private javax.swing.JPasswordField jPasswordField_MK2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_ChucVu;
    private javax.swing.JTextField jTextField_MaNV;
    private javax.swing.JTextField jTextField_TaiKhoan;
    private javax.swing.JTextField jTextField_TaoMoi;
    private javax.swing.JTextField jTextField_TenNV;
    // End of variables declaration//GEN-END:variables
}
