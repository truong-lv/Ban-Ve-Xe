/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

import Code.BanVeXe;
import Form.DangKyKhach;
import Code.HamXuLyBang;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author n18dc
 */
public class PnQlyChuyenXe extends javax.swing.JPanel {

    HamXuLyBang xuLyBang=new HamXuLyBang();
    String maChuyenBanDau="";
    public PnQlyChuyenXe() {
        initComponents();
    }
    private void phanQuyen(){
        if(BanVeXe.quyen.equals("Nhân viên")){
            btnThem.setVisible(false);
            btnSua.setVisible(false);
            btnXoa.setVisible(false);
            btnHuy.setVisible(false);
        }else{
            setEnableBtn(true, true, true, false);
        }
    }
    private void setEnableCbb(boolean bool, Color col){
        //txtMaCx.setEditable(bool);
        jLabel_MaChuyen.setForeground(col);
        //txtSoXe.setEditable(bool);
        cbbSoXe.setEnabled(bool);
        jLabel_SoXe.setForeground(col);
        cbbGio.setEnabled(bool);
        jLabel_GioDi.setForeground(col);
        cbbPhut.setEnabled(bool);
        cbbTram.setEnabled(bool);
        jLabel_Tram.setForeground(col);
        cbbTrangThai.setEnabled(bool);
        jLabel_TrangThai.setForeground(col);
        
    }
    
    private void setEnableBtn(boolean them, boolean sua, boolean xoa, boolean huy){
        btnThem.setEnabled(them);
        btnSua.setEnabled(sua);
        btnXoa.setEnabled(xoa);
        btnHuy.setEnabled(huy);
        if(huy){
            tbChuyenXe.setEnabled(false);
            txtTimKiem.setText("");
            txtTimKiem.setEditable(false);
            cbbMucTimKiem.setEnabled(false);
        }
        else {
            tbChuyenXe.setEnabled(true);
            txtTimKiem.setEditable(true);
            cbbMucTimKiem.setEnabled(true);
        }
    }
    
    private void loadSoXe(){
        cbbSoXe.removeAllItems();
        String sql= "SELECT SoXe FROM XE";
         java.sql.Connection connect=Code.KetNoi.layKetNoi();
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            // duyet ket qua
            while (rs.next()) {
              cbbSoXe.addItem(rs.getString(1));
            }
            // dong ket noi
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(DangKyKhach.class.getName()).log(Level.SEVERE, null, e);
        }
    }
//    private void loadTaiXe(){
//        cbbTaiXe.removeAllItems();
//        String sql= "SELECT MaNV FROM NHAN_VIEN WHERE MaLoaiNV='NV'";
//         java.sql.Connection connect=Code.KetNoi.layKetNoi();
//        try {
//            PreparedStatement ps=connect.prepareStatement(sql);
//            ResultSet rs=ps.executeQuery();
//
//            // duyet ket qua
//            while (rs.next()) {
//              cbbTaiXe.addItem(rs.getString(1));
//            }
//            // dong ket noi
//            rs.close();
//            ps.close();
//            connect.close();
//        } catch (SQLException e) {
//            java.util.logging.Logger.getLogger(DangKyKhach.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
    private void loadCbb(int chon){// load dữ liệu được chọn từ bảng vào combobox, textField
        
        String thoiGian =tbChuyenXe.getValueAt(chon, 1).toString();// lấy thời gian từ dòng được chọn
        String gio=thoiGian.substring(0,thoiGian.indexOf(":"));// cắt lấy phần giờ để đưa vào combobox
        String phut=thoiGian.substring(thoiGian.indexOf(":")+1,5);// cắt lấy phần phút
        txtMaCx.setText(tbChuyenXe.getValueAt(chon, 0).toString());
        cbbGio.setSelectedItem(gio);
        cbbPhut.setSelectedItem(phut);
        cbbSoXe.setSelectedItem(tbChuyenXe.getValueAt(chon, 2).toString());
        cbbTram.setSelectedItem(tbChuyenXe.getValueAt(chon, 3).toString());
        cbbTrangThai.setSelectedItem(tbChuyenXe.getValueAt(chon, 4).toString());
    }
    
    private boolean ktMaChuyen(String maChuyen){
        for(int i=0;i<tbChuyenXe.getRowCount();i++){
            if(maChuyen.equals(tbChuyenXe.getValueAt(i, 0).toString())){
                return true;
            }
        }
        return false;
    }
    
    private void themSuaChuyenXe(String sql, String maChuyen, String gio, String soXe, String tram,String tramDen, int trangThai){
        Connection con =Code.KetNoi.layKetNoi();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, maChuyen);
            ps.setString(2, gio);
            ps.setString(3, soXe);
            ps.setString(4, tram);
            ps.setString(5, tramDen);
            ps.setInt(6, trangThai);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private boolean ktXoaChuyen(String ma){
        Connection con =Code.KetNoi.layKetNoi();
        String sql="SELECT DISTINCT MaChuyenXe FROM VE_XE WHERE MaChuyenXe=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return false;
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyChuyenXe.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }
    
    private void xoaChuyenXe(String maChuyen){
        Connection con =Code.KetNoi.layKetNoi();
        String sql="DELETE FROM CHUYEN_XE WHERE MaChuyenXe=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, maChuyen);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyChuyenXe.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbChuyenXe = new javax.swing.JTable();
        jLabel_MaChuyen = new javax.swing.JLabel();
        txtMaCx = new javax.swing.JTextField();
        jLabel_GioDi = new javax.swing.JLabel();
        cbbGio = new javax.swing.JComboBox<>();
        cbbTram = new javax.swing.JComboBox<>();
        jLabel_Tram = new javax.swing.JLabel();
        jLabel_SoXe = new javax.swing.JLabel();
        jLabel_TrangThai = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbbPhut = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lbLoiMaChuyenXe = new javax.swing.JLabel();
        cbbSoXe = new javax.swing.JComboBox<>();
        txtTimKiem = new javax.swing.JTextField();
        jLabel_Sdt1 = new javax.swing.JLabel();
        cbbMucTimKiem = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("Quản Lý Chuyến Xe");

        tbChuyenXe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbChuyenXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Chuyến", "Giờ đi", "Số xe", "Trạm ", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbChuyenXe.setRowHeight(23);
        tbChuyenXe.setRowMargin(3);
        tbChuyenXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChuyenXeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbChuyenXe);

        jLabel_MaChuyen.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel_MaChuyen.setText("Mã chuyến xe:");

        txtMaCx.setEditable(false);
        txtMaCx.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel_GioDi.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel_GioDi.setText("Giờ Di:");

        cbbGio.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbGio.setMaximumRowCount(6);
        cbbGio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cbbGio.setEnabled(false);
        cbbGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbGioActionPerformed(evt);
            }
        });

        cbbTram.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbTram.setMaximumRowCount(6);
        cbbTram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TP.HCM", "Đồng Nai" }));
        cbbTram.setEnabled(false);

        jLabel_Tram.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel_Tram.setText("Trạm xuất phát:");

        jLabel_SoXe.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel_SoXe.setText("Số xe:");

        jLabel_TrangThai.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel_TrangThai.setText("Trạng thái:");

        cbbTrangThai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbTrangThai.setMaximumRowCount(6);
        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã dừng", "Đang hoạt động" }));
        cbbTrangThai.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setText(":");

        cbbPhut.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbPhut.setMaximumRowCount(6);
        cbbPhut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cbbPhut.setToolTipText("");
        cbbPhut.setEnabled(false);

        btnThem.setBackground(new java.awt.Color(131, 199, 233));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(131, 199, 233));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(131, 199, 233));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setEnabled(false);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(131, 199, 233));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lbLoiMaChuyenXe.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiMaChuyenXe.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiMaChuyenXe.setText("Mã chuyến xe là số");
        lbLoiMaChuyenXe.setVisible(false);

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel_Sdt1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Sdt1.setText("Tìm kiếm:");

        cbbMucTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã Chuyến", "Giờ đi", "Mã tài xế", "Số xe", "Trạm", "Trạng thái" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(410, 410, 410)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(264, 264, 264)
                                .addComponent(jLabel_Sdt1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbMucTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_MaChuyen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtMaCx, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel_SoXe))
                                    .addComponent(lbLoiMaChuyenXe, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Tram)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbTram, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146)))
                        .addComponent(cbbSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(376, 376, 376)
                                .addComponent(jLabel_GioDi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbGio, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(11, 11, 11)
                                .addComponent(cbbPhut, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_TrangThai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Sdt1)
                    .addComponent(cbbMucTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_MaChuyen)
                    .addComponent(txtMaCx, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_SoXe)
                    .addComponent(cbbSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLoiMaChuyenXe)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Tram)
                    .addComponent(cbbTram, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_GioDi)
                    .addComponent(cbbGio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbPhut, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_TrangThai)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbChuyenXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChuyenXeMouseClicked
        // TODO add your handling code here:
        
        //kiểm tra đã có cột nào đc chọn chưa
        if(tbChuyenXe.getSelectionModel().isSelectionEmpty() || !(tbChuyenXe.isEnabled())){return;}
        
        // load dữ liệu đc chọn 
        loadCbb(tbChuyenXe.getSelectedRow());
    }//GEN-LAST:event_tbChuyenXeMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(btnThem.getText().equals("Thêm")){
            txtMaCx.setText("");
            txtMaCx.setEditable(true);
            //txtSoXe.setText("");
            tbChuyenXe.clearSelection();
            cbbTrangThai.setSelectedIndex(1);
            setEnableCbb(true,Color.GREEN);
            setEnableBtn(true, false, false, true);
            btnThem.setText("Lưu");
            btnHuy.setEnabled(true);
            //tbChuyenXe.removeColumn(tbChuyenXe.getColumnModel().getColumn(1));

        }else {
            
            String machuyen=txtMaCx.getText();
            String soXe=cbbSoXe.getSelectedItem().toString();
            //String taiXe=cbbTaiXe.getSelectedItem().toString();
            
            if(machuyen.matches("\\d+")){lbLoiMaChuyenXe.setVisible(false);
            }else{
                lbLoiMaChuyenXe.setText("Mã chuyến xe phải là số");
                lbLoiMaChuyenXe.setVisible(true);
                return;}
            
            
            if(!ktMaChuyen(machuyen)){lbLoiMaChuyenXe.setVisible(false);
            }else{
                lbLoiMaChuyenXe.setText("Mã này đã tồn tại");
                lbLoiMaChuyenXe.setVisible(true);
                return;
            }
                  
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận thêm chuyến xe: "+machuyen, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                String gioDi=cbbGio.getSelectedItem().toString()+":"+cbbPhut.getSelectedItem().toString()+":00";
                String tram=cbbTram.getSelectedItem().toString();
                String tramDen=tram.equals("TP.HCM")?"Đồng Nai":"TP.HCM";

                int trangThai=cbbTrangThai.getSelectedIndex();
                themSuaChuyenXe("INSERT INTO CHUYEN_XE VALUES (?,?,?,?,?,?)", machuyen, gioDi, soXe, tram,tramDen, trangThai);

                JOptionPane.showMessageDialog(this, "Đã thêm chuyến xe thành công");
                xuLyBang.loadDuLieuVaoBang(tbChuyenXe, "{call SP_LOAD_CHUYENXE_TO_JTABLE()}");
                btnThem.setText("Thêm");
                txtMaCx.setEditable(false);
                setEnableBtn(true, true, true, false);
                setEnableCbb(false, null);
            }
            

        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        if(tbChuyenXe.getSelectionModel().isSelectionEmpty()){//kt người dùng có click chọn dữ liệu chưa
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin cần sửa trong bảng");
            return;
        } 
        
        if(btnSua.getText().equals("Sửa")){// kt tra nếu Button đang ở trạng thái Sửa thì
            // mở khóa các combobox cho người dùng chọn
            setEnableCbb(true,Color.blue); 
            
            // mở khóa các button cần phục vụ cho chức năng
            setEnableBtn(false, true, false, true); 
            maChuyenBanDau=txtMaCx.getText();
            
            // đổi text của button từ Sửa-> Lưu
            btnSua.setText("Lưu");
            btnHuy.setEnabled(true);

        }else {//kt tra nếu Button đang ở trạng thái LƯU thì
            
            String maChuyenSau=txtMaCx.getText();// lưu mã chuyến xe sau khi sửa
            String soXe=cbbSoXe.getSelectedItem().toString();
            //String taiXe=cbbTaiXe.getSelectedItem().toString();
            
            
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận sửa chuyến xe: "+maChuyenBanDau, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                //lấy dữ liệu từ các txt và cbb
                String gioDi=cbbGio.getSelectedItem().toString()+":"+cbbPhut.getSelectedItem().toString()+":00";
                String tram=cbbTram.getSelectedItem().toString();
                String tramDen=tram.equals("TP.HCM")?"Đồng Nai":"TP.HCM";
                int trangThai=cbbTrangThai.getSelectedIndex();
                
                //truy vấn Sửa thông tin
                themSuaChuyenXe("UPDATE CHUYEN_XE SET MaChuyenXe=?, GioDi=?, SoXe=?, TramXuatPhat=?, TramDen=?, TrangThai=? WHERE MaChuyenXe='"+maChuyenBanDau+"'",
                maChuyenSau, gioDi, soXe, tram, tramDen, trangThai);
                
                //thông báo và load lại dữ liệu
                JOptionPane.showMessageDialog(this, "Đã SỬA chuyến xe thành công");
                xuLyBang.loadDuLieuVaoBang(tbChuyenXe, "{call SP_LOAD_CHUYENXE_TO_JTABLE()}");
                btnSua.setText("Sửa");
                setEnableBtn(true, true, true, false);
                setEnableCbb(false, null);
            }
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if(tbChuyenXe.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin cần Xóa trong bảng");
            return;
        }
        if(btnXoa.getText().equals("Xóa")){// kt tra nếu Button đang ở trạng thái xóa thì
            //System.out.println(txtMaCx.getText()+" "+ktXoaChuyen(txtMaCx.getText()));
            if(!ktXoaChuyen(txtMaCx.getText())){
                JOptionPane.showMessageDialog(this, "Chuyến xe đã từng hoạt động không thể xóa");
                return;
            }
            setEnableBtn(false, false, true, true); // mở khóa các button cần phục vụ cho chức năng
            setEnableCbb(false, Color.red);
            btnXoa.setText("Lưu");// đổi text của button từ Xóa-> Lưu
            btnHuy.setEnabled(true);

       }else {//kt tra nếu Button đang ở trạng thái LƯU thì
            //lấy mã chuyến để xóa
            maChuyenBanDau=txtMaCx.getText();
            // xác nhận lại
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Xóa thông tin về chuyến xe: "+maChuyenBanDau, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                // Xóa xe
                xoaChuyenXe(maChuyenBanDau);
                JOptionPane.showMessageDialog(this, "Đã Xóa thông tin xe thành công");
                xuLyBang.loadDuLieuVaoBang(tbChuyenXe, "{call SP_LOAD_CHUYENXE_TO_JTABLE()}");// cập nhập lại bảng

                //cập nhập lại các cbb và button
                btnXoa.setText("Xóa");
                setEnableBtn(true, true, true, false);
                setEnableCbb(false, null);
            }
        }

        

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed

        //reset 
        txtMaCx.setText("");
        btnThem.setText("Thêm");
        btnSua.setText("Sửa");
        btnXoa.setText("Xóa");
        tbChuyenXe.clearSelection();
        lbLoiMaChuyenXe.setVisible(false);
        txtMaCx.setEditable(false);
        setEnableCbb(false, null);
        setEnableBtn(true, true, true, false);
        txtTimKiem.setEditable(true);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
        xuLyBang.loadDuLieuVaoBang(tbChuyenXe, "{call SP_LOAD_CHUYENXE_TO_JTABLE()}");
        loadSoXe();
        
        txtMaCx.setText("");
        btnThem.setText("Thêm");
        btnSua.setText("Sửa");
        btnXoa.setText("Xóa");
        tbChuyenXe.clearSelection();
        setEnableCbb(false, null);
        phanQuyen();
        
    }//GEN-LAST:event_formHierarchyChanged

    private void cbbGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbGioActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        tbChuyenXe.clearSelection();
        if(!txtTimKiem.getText().isEmpty()){
            new HamXuLyBang().locTatCa(tbChuyenXe,txtTimKiem.getText(),cbbMucTimKiem.getSelectedIndex()-1);
        }
        else new HamXuLyBang().locTatCa(tbChuyenXe,"",-1);
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbGio;
    private javax.swing.JComboBox<String> cbbMucTimKiem;
    private javax.swing.JComboBox<String> cbbPhut;
    private javax.swing.JComboBox<String> cbbSoXe;
    private javax.swing.JComboBox<String> cbbTram;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_GioDi;
    private javax.swing.JLabel jLabel_MaChuyen;
    private javax.swing.JLabel jLabel_Sdt1;
    private javax.swing.JLabel jLabel_SoXe;
    private javax.swing.JLabel jLabel_Tram;
    private javax.swing.JLabel jLabel_TrangThai;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbLoiMaChuyenXe;
    private javax.swing.JTable tbChuyenXe;
    private javax.swing.JTextField txtMaCx;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
