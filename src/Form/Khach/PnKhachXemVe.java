/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Khach;

import Code.KetNoi;
import Code.BanVeXe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author n18dc
 */
public class PnKhachXemVe extends javax.swing.JPanel {

    /**
     * Creates new form PnKhachXemVe
     */
    ArrayList<String> gio = new ArrayList<String>();
    Boolean z = true;

    public PnKhachXemVe() {
        initComponents();

        loadVe();
    }

    public String ktGioVe(String maChuyen) {
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "SELECT * FROM CHUYEN_XE";
        String temp = "";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).equals(maChuyen)) {
                    temp = rs.getString(2);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PnKhachXemVe.class.getName()).log(Level.SEVERE, null, e);
        }
        return temp;
    }

    public boolean ktVe(String ngayVe, String thangVe, String namVe, String maChuyen) {
        String namht = String.valueOf(java.time.LocalDate.now()).substring(0, 4);
        String thanght = String.valueOf(java.time.LocalDate.now()).substring(5, 7);
        String ngayht = String.valueOf(java.time.LocalDate.now()).substring(8, 10);
        String gioht = String.valueOf(java.time.LocalTime.now()).substring(0, 2);
        if (Integer.parseInt(namht) > Integer.parseInt(namVe)) {
            return false;
        } else if (Integer.parseInt(namht) == Integer.parseInt(namVe)) {
            if (Integer.parseInt(thanght) > Integer.parseInt(thangVe)) {
                return false;
            } else if (Integer.parseInt(thanght) == Integer.parseInt(thangVe)) {
                if (Integer.parseInt(ngayht) > Integer.parseInt(ngayVe)) {
                    return false;
                } else if (Integer.parseInt(ngayht) == Integer.parseInt(ngayVe)) {
                    if (Integer.parseInt(gioht) - Integer.parseInt(ktGioVe(maChuyen).substring(0, 2)) < 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void loadVe() {
        Connection ketNoi = KetNoi.layKetNoi();

        if (z == true) {
            String sql1 = "SELECT * FROM CHUYEN_XE";
            try {
                PreparedStatement ps = ketNoi.prepareStatement(sql1);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    gio.add(rs.getString(2).substring(0, 8));
                }
                System.out.println(gio.get(80));
            } catch (SQLException e) {
                Logger.getLogger(PnKhachXemVe.class.getName()).log(Level.SEVERE, null, e);
            }
            z = false;
        }

        String sql = "SELECT * FROM VE_XE ORDER BY TRY_CONVERT(date, NgayDi, 105) ASC";
        DefaultTableModel dtm = (DefaultTableModel) tbVe.getModel();
        dtm.setNumRows(0);
        Vector value;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(2).equals(BanVeXe.primaryKey)) {
                    value = new Vector();
                    value.add(rs.getString(1)); // lấy dữ liệu ở cột 1
                    value.add(rs.getString(3)); // lấy dữ liệu ở cột 3
                    value.add(rs.getString(4)); // lấy dữ liệu ở cột 4
                    value.add(rs.getString(5)); // lấy dữ liệu ở cột 5
                    value.add(gio.get(Integer.parseInt(rs.getString(6)))); // lấy dữ liệu ở cột 6
                    String trangThai = rs.getString(7).equals("1") ? "Đã thanh toán" : "Chưa thanh toán";
                    value.add(trangThai); // lấy dữ liệu ở cột 7
                    dtm.addRow(value);
                }
            }
            tbVe.setModel(dtm);
        } catch (SQLException e) {
            Logger.getLogger(PnKhachXemVe.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void xoaVe(String maVe) {
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "Delete from VE_XE where MaVe = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maVe);
            if (JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Muốn Hủy Vé ?", "Confirm", 0) == JOptionPane.YES_OPTION) {
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Xoa Ve Thanh Cong");
            }
            
        } catch (Exception e) {
            System.out.println("Xoa Ve That Bai");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbVe = new javax.swing.JTable();
        jButton_XoaVe = new javax.swing.JButton();
        lbMave = new javax.swing.JLabel();
        txtMaVe = new javax.swing.JTextField();
        lb = new javax.swing.JLabel();
        txtViTriGhe = new javax.swing.JTextField();
        txtNgayDi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaChuyenXe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTrangThaiVe = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtGiaVe = new javax.swing.JTextField();

        tbVe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Vé", "Vị Trí Ghế", "Giá Vé", "Ngày Đi", "Giờ Đi", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbVe.setRowHeight(23);
        tbVe.setRowMargin(3);
        tbVe.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                tbVeHierarchyChanged(evt);
            }
        });
        tbVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbVeMouseEntered(evt);
            }
        });
        tbVe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbVePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tbVe);

        jButton_XoaVe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_XoaVe.setText("Hủy Vé");
        jButton_XoaVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaVeActionPerformed(evt);
            }
        });

        lbMave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbMave.setText("Mã vé:");

        txtMaVe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaVeActionPerformed(evt);
            }
        });

        lb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lb.setText("Vị trí ghế:");

        txtViTriGhe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtViTriGhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtViTriGheActionPerformed(evt);
            }
        });

        txtNgayDi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNgayDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayDiActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Ngày đi:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Giờ Đi :");

        txtMaChuyenXe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaChuyenXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaChuyenXeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Trạng thái vé:");

        txtTrangThaiVe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTrangThaiVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiVeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Giá vé:");

        txtGiaVe.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtGiaVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbMave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMaChuyenXe, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTrangThaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtViTriGhe, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 84, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton_XoaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(488, 488, 488))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMave)
                    .addComponent(txtMaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb)
                    .addComponent(txtViTriGhe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTrangThaiVe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaChuyenXe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jButton_XoaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_XoaVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaVeActionPerformed
        String maVe = (String) tbVe.getValueAt(tbVe.getSelectedRow(), 0);
        String trangThai = (String) tbVe.getValueAt(tbVe.getSelectedRow(), 5);
        if (trangThai.equals("Chưa thanh toán")) {
            trangThai = "0";
        } else {
            trangThai = "1";
        }

        // 01/34/6789
        String ngayVe = txtNgayDi.getText().substring(0, 2);
        String thangVe = txtNgayDi.getText().substring(3, 5);
        String namVe = txtNgayDi.getText().substring(6, 10);

        if (!trangThai.equals("0")) {
            JOptionPane.showMessageDialog(this, "Vé Này Đã Được Thanh Toán. Không Thể Xóa !!!");
            loadVe();
        } else if (ktVe(ngayVe, thangVe, namVe, txtMaChuyenXe.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Vé Này Đã Hết Hạn Sử Dụng. Không Thể Xóa !!!");
        } else if (trangThai.equals("0") && ktVe(ngayVe, thangVe, namVe, txtMaChuyenXe.getText()) == true) {
            xoaVe(maVe);
            loadVe();
        }
    }//GEN-LAST:event_jButton_XoaVeActionPerformed

    private void txtMaVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaVeActionPerformed

    private void txtViTriGheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtViTriGheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtViTriGheActionPerformed

    private void txtNgayDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayDiActionPerformed

    private void txtMaChuyenXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaChuyenXeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaChuyenXeActionPerformed

    private void txtTrangThaiVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangThaiVeActionPerformed

    private void txtGiaVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaVeActionPerformed

    private void tbVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVeMouseClicked
        // TODO add your handling code here:
        txtMaVe.setText(tbVe.getValueAt(tbVe.getSelectedRow(), 0).toString());
        txtViTriGhe.setText(tbVe.getValueAt(tbVe.getSelectedRow(), 1).toString());
        txtGiaVe.setText(tbVe.getValueAt(tbVe.getSelectedRow(), 2).toString());
        txtNgayDi.setText(tbVe.getValueAt(tbVe.getSelectedRow(), 3).toString());
        txtMaChuyenXe.setText(tbVe.getValueAt(tbVe.getSelectedRow(), 4).toString());
        txtTrangThaiVe.setText(tbVe.getValueAt(tbVe.getSelectedRow(), 5).toString());
    }//GEN-LAST:event_tbVeMouseClicked

    private void tbVeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbVeMouseEntered

    private void tbVePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbVePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbVePropertyChange

    private void tbVeHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_tbVeHierarchyChanged
        // TODO add your handling code here:
        loadVe();
    }//GEN-LAST:event_tbVeHierarchyChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_XoaVe;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbMave;
    private javax.swing.JTable tbVe;
    private javax.swing.JTextField txtGiaVe;
    private javax.swing.JTextField txtMaChuyenXe;
    private javax.swing.JTextField txtMaVe;
    private javax.swing.JTextField txtNgayDi;
    private javax.swing.JTextField txtTrangThaiVe;
    private javax.swing.JTextField txtViTriGhe;
    // End of variables declaration//GEN-END:variables
}
