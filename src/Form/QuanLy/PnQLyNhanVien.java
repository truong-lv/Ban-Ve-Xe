/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

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
        jTable1.setDefaultEditor(Object.class, null);
        jButton_Sua.setVisible(false);
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

    // Hàm kiểm tra tài khoản
    public boolean ktTaiKhoan(String taiKhoan) {
        boolean kt = true;
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "select * from TAI_KHOAN";

        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(taiKhoan)) {
                    kt = false;
                    JOptionPane.showMessageDialog(this, "Tài Khoản Đã Được Sử Dụng. Vui Lòng Chọn Tên Tài Khoản Khác");
                    break;
                }
            }
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(ThemNV.class.getName()).log(Level.SEVERE, null, e);
        }
        return kt;
    }

    // Hàm Xóa Nhân Viên
    public void xoaNhanVien(String maNV) {
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "Delete from NHAN_VIEN where MaNV = ?";

        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            if (JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Xóa Nhân Viên ?", "Confirm", 0) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Xóa Nhân Viên Thành Công");
            }
            ps.close();
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(PnQLyNhanVien.class.getName()).log(Level.SEVERE, null, e);
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

    // Hàm Sửa Thông Tin Nhân Viên
    public void suaNhanVien(String maNV, String hoTen, String CMND, String gioiTinh, String sdt, String chucVu) {
        Connection connect = Code.KetNoi.layKetNoi();
        String sql = "UPDATE NHAN_VIEN SET HoTen=?, CMND=?, GioiTinh=?, DienThoai=?, MaLoaiNV=? where MaNV=?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, hoTen);
            ps.setString(2, CMND);
            ps.setString(3, gioiTinh);
            ps.setString(4, sdt);
            ps.setString(5, chucVu);
            ps.setString(6, maNV);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Chỉnh Sửa Nhân Viên Thành Công");
        } catch (SQLException e) {
            Logger.getLogger(PnQLyNhanVien.class.getName()).log(Level.SEVERE, null, e);
        }
        loadNhanVien();
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
        } catch (SQLException e) {
            Logger.getLogger(PnQLyNhanVien.class.getName()).log(Level.SEVERE, null, e);
        }
        loadNhanVien();
    }

    //Hàm Làm Mới Các TextField
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_TaiKhoan = new javax.swing.JLabel();
        jLabel_ChucVu = new javax.swing.JLabel();
        jLabel_TenNV = new javax.swing.JLabel();
        jLabel_MaNV = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_ThemNV = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton_ThemNV1 = new javax.swing.JButton();
        jButton_SuaNV = new javax.swing.JButton();
        jTextField_MaNV = new javax.swing.JTextField();
        jTextField_TenNV = new javax.swing.JTextField();
        jTextField_TaiKhoan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_CMND = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_SDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton_Nam = new javax.swing.JRadioButton();
        jRadioButton_Nu = new javax.swing.JRadioButton();
        jButton_Sua = new javax.swing.JButton();
        jComboBox_ChucVu = new javax.swing.JComboBox<>();

        jLabel_TaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel_ChucVu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel_TenNV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel_MaNV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel_MaNV.setForeground(new java.awt.Color(255, 0, 0));

        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(23);
        jTable1.setRowMargin(3);
        jTable1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jTable1HierarchyChanged(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jTable1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jTable1VetoableChange(evt);
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Mã NV :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Tài Khoản :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Chức Vụ :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Tên NV :");

        jButton_ThemNV1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton_ThemNV1.setText("Xóa Nhân viên");
        jButton_ThemNV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemNV1ActionPerformed(evt);
            }
        });

        jButton_SuaNV.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton_SuaNV.setText("Sửa Nhân viên");
        jButton_SuaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SuaNVActionPerformed(evt);
            }
        });

        jTextField_MaNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_TenNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField_TaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("CMND :");

        jTextField_CMND.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Điện Thoại :");

        jTextField_SDT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Giới Tính :");

        buttonGroup1.add(jRadioButton_Nam);
        jRadioButton_Nam.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jRadioButton_Nam.setText("Nam");

        buttonGroup1.add(jRadioButton_Nu);
        jRadioButton_Nu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jRadioButton_Nu.setText("Nữ");

        jButton_Sua.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton_Sua.setText("Sửa");
        jButton_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SuaActionPerformed(evt);
            }
        });

        jComboBox_ChucVu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jComboBox_ChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AD - Quản lý", "NV - Nhân viên" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_CMND, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton_Nam)
                                        .addGap(28, 28, 28)
                                        .addComponent(jRadioButton_Nu)))))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_TaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(jTextField_SDT)
                            .addComponent(jComboBox_ChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_ThemNV, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton_ThemNV1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton_SuaNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(144, 144, 144))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_ThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_ThemNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_SuaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jTextField_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jComboBox_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextField_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(75, 75, 75))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_CMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jRadioButton_Nam)
                            .addComponent(jRadioButton_Nu))))
                .addGap(8, 8, 8)
                .addComponent(jButton_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        jRadioButton_Nam.disable();
        jRadioButton_Nu.disable();
        jTextField_MaNV.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        if (jTable1.getValueAt(jTable1.getSelectedRow(), 5).equals("AD")) {
            jComboBox_ChucVu.setSelectedIndex(0);
        } else {
            jComboBox_ChucVu.setSelectedIndex(1);
        }
        jTextField_TenNV.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
        if ((String) jTable1.getValueAt(jTable1.getSelectedRow(), 6) != null) {
            jTextField_TaiKhoan.setForeground(Color.BLACK);
            jTextField_TaiKhoan.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 6));
        } else {
            jTextField_TaiKhoan.setForeground(Color.RED);
            jTextField_TaiKhoan.setText("Chưa Có Tài Khoản");
        }
        jTextField_CMND.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 2));
        jTextField_SDT.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 4));
        if (jTable1.getValueAt(jTable1.getSelectedRow(), 3).equals("Nam")) {
            jRadioButton_Nam.setSelected(true);
        } else {
            jRadioButton_Nu.setSelected(true);
        }
        jTextField_MaNV.disable();
        jComboBox_ChucVu.disable();
        jTextField_CMND.disable();
        jTextField_SDT.disable();
        jTextField_TaiKhoan.disable();
        jTextField_TenNV.disable();
        jRadioButton_Nam.disable();
        jRadioButton_Nu.disable();
        jButton_Sua.setVisible(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
        loadNhanVien();
    }//GEN-LAST:event_jTable1KeyTyped

    private void jButton_ThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemNVActionPerformed
        // TODO add your handling code here:
        new ThemNV().setVisible(true);
    }//GEN-LAST:event_jButton_ThemNVActionPerformed

    private void jButton_ThemNV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemNV1ActionPerformed
        // TODO add your handling code here:
        xoaNhanVien(jTextField_MaNV.getText());
    }//GEN-LAST:event_jButton_ThemNV1ActionPerformed

    private void jButton_SuaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SuaNVActionPerformed
        // TODO add your handling code here:
        jComboBox_ChucVu.enable();
        jTextField_CMND.enable();
        jTextField_SDT.enable();
        jTextField_TenNV.enable();
        jRadioButton_Nam.enable();
        jRadioButton_Nu.enable();
        jButton_Sua.setVisible(true);
    }//GEN-LAST:event_jButton_SuaNVActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
        loadNhanVien();
        jTable1.setDefaultEditor(Object.class, null);
    }//GEN-LAST:event_formHierarchyChanged

    private void jTable1HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jTable1HierarchyChanged
        // TODO add your handling code here:
        loadNhanVien();
        jButton_Sua.setVisible(false);
    }//GEN-LAST:event_jTable1HierarchyChanged

    private void jTable1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jTable1VetoableChange
        // TODO add your handling code here:
        loadNhanVien();
        jButton_Sua.setVisible(false);
    }//GEN-LAST:event_jTable1VetoableChange

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        // TODO add your handling code here:
        loadNhanVien();
        jButton_Sua.setVisible(false);
    }//GEN-LAST:event_jTable1PropertyChange

    private void jButton_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SuaActionPerformed
        // TODO add your handling code here:
        jButton_Sua.setVisible(false);
        jTextField_MaNV.disable();
        jComboBox_ChucVu.disable();
        jTextField_CMND.disable();
        jTextField_SDT.disable();
        jTextField_TaiKhoan.disable();
        jTextField_TenNV.disable();
        jRadioButton_Nam.disable();
        jRadioButton_Nu.disable();
        String maNV = jTextField_MaNV.getText();
        String hoTen, CMND, gioiTinh, dienThoai, chucVu, taiKhoan, loaiTaiKhoan;
        if (jTextField_TenNV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Điền Tên Nhân Viên");
        } else if (jTextField_CMND.getText().isEmpty() || !jTextField_CMND.getText().matches("[0-9]{9}")) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Xem Lại CMND");
        } else if (!jRadioButton_Nu.isSelected() && !jRadioButton_Nam.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Giới Tính");
        } else if (!jTextField_SDT.getText().matches("0\\d{9,10}") || jTextField_SDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Xem Lại SDT");
        } else {
            hoTen = jTextField_TenNV.getText();
            CMND = jTextField_CMND.getText();
            if (jRadioButton_Nam.isSelected()) {
                gioiTinh = jRadioButton_Nam.getText();
            } else {
                gioiTinh = jRadioButton_Nu.getText();
            }
            dienThoai = jTextField_SDT.getText();
            chucVu = jComboBox_ChucVu.getSelectedItem().toString().substring(0, 2);
            suaNhanVien(maNV, hoTen, CMND, gioiTinh, dienThoai, chucVu);
        }
    }//GEN-LAST:event_jButton_SuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_Sua;
    private javax.swing.JButton jButton_SuaNV;
    private javax.swing.JButton jButton_ThemNV;
    private javax.swing.JButton jButton_ThemNV1;
    private javax.swing.JComboBox<String> jComboBox_ChucVu;
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
    private javax.swing.JRadioButton jRadioButton_Nam;
    private javax.swing.JRadioButton jRadioButton_Nu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_CMND;
    private javax.swing.JTextField jTextField_MaNV;
    private javax.swing.JTextField jTextField_SDT;
    private javax.swing.JTextField jTextField_TaiKhoan;
    private javax.swing.JTextField jTextField_TenNV;
    // End of variables declaration//GEN-END:variables
}
