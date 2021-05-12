/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.QuanLy;

import Form.DangKyKhach;
import Code.HamXuLyBang;
import java.awt.Color;
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
public class PnQlyXe extends javax.swing.JPanel {

    HamXuLyBang xuLyBang=new HamXuLyBang();
    
    public PnQlyXe() {
        initComponents();
        xuLyBang.loadDuLieuVaoBang(tbTaiXe, "SELECT * FROM TAI_XE");
        xuLyBang.loadDuLieuVaoBang(tbXe, "{call SP_LOAD_XE ()}");
        loadCbbXe();
        
        // Set dòng đầu tiên là vị trí được chọn ban đầu
        tbXe.setRowSelectionInterval(0, 0);
        txtSoXe.setText(tbXe.getValueAt(0, 0).toString());
        cbbLoaiXe.setSelectedItem(tbXe.getValueAt(0, 1).toString());
        cbbChoNgoi.setSelectedItem(tbXe.getValueAt(0, 2).toString());
        cbbHeSoGia.setSelectedItem(tbXe.getValueAt(0, 3).toString());
    }
    //==============CÁC HÀM XỬ LÝ BẢNG TÀI XẾ=========================
    private void setField(String maTX, String ten, String cmnd, boolean namIsSelect, boolean nuIsSelect, String sdt){
        txtMaTX.setText(maTX);
        txtHoTen.setText(ten);
        txtCMND.setText(cmnd);
        rBtnNam1.setSelected(namIsSelect);
        rBtnNu1.setSelected(nuIsSelect);
        txtSDT.setText(sdt);
    }
    private void setEnableBtnTaiXe(boolean them, boolean sua, boolean xoa, boolean huy){
        btnThemTX.setEnabled(them);
        btnSuaTX.setEnabled(sua);
        btnXoaTX.setEnabled(xoa);
        btnHuyTX.setEnabled(huy);
    }
    private String layMaTX(){
        java.sql.Connection connect=Code.KetNoi.layKetNoi();
        try {
            PreparedStatement ps=connect.prepareStatement("{call SP_LOAD_MA_TAIXE()}");
            ResultSet rs=ps.executeQuery();
            rs=ps.executeQuery();
            // duyet ket qua
            while (rs.next()) {
                return rs.getString(1);
            }
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
        return "";
    }
    private boolean ktTrungTX(String cmnd, String sdt){
        String sql2="SELECT * FROM TAI_XE WHERE CMND='"+cmnd+"'";
        String sql3="SELECT * FROM TAI_XE WHERE DienThoai='"+sdt +"'";
         java.sql.Connection connect=Code.KetNoi.layKetNoi();
        try {
            PreparedStatement ps=connect.prepareStatement(sql2);
            ResultSet rs=ps.executeQuery();
            rs=ps.executeQuery();
            // duyet ket qua
            while (rs.next()) {
                JOptionPane.showMessageDialog(this, "CMND đã tồn tại");
               return true;
            }
            
            ps=connect.prepareStatement(sql3);
            rs=ps.executeQuery();
            // duyet ket qua
            while (rs.next()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
               return true;
            }
            
            // dong ket noi
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
         return false;
    }
    private void setEditableTaiXe(boolean bool, Color col){
        lbMaTX.setForeground(col);
        lbHoTen.setForeground(col);
        txtHoTen.setEditable(bool);
        lbGioiTinh.setForeground(col);
        rBtnNam1.setEnabled(bool);
        rBtnNu1.setEnabled(bool);
        lbCMND.setForeground(col);
        txtCMND.setEditable(bool);
        lbSDT.setForeground(col);
        txtSDT.setEditable(bool);
    }
    private void themSuaTX(String sql, String maTX, String hoTen, String cmnd, String gt, String sdt){
        Connection con =Code.KetNoi.layKetNoi();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, maTX);
            ps.setString(2, hoTen);
            ps.setString(3, cmnd);
            ps.setString(4, gt);
            ps.setString(5, sdt);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void xoaTX(String maTX){
        Connection con =Code.KetNoi.layKetNoi();
        String sql="DELETE FROM TAI_XE WHERE MaTX=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, maTX);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    
    
    
    //============CÁC HÀM XỬ LÝ VỚI BẢNG QUẢN LÝ XE================
    private void setEditableXe(boolean bool, Color col){
        lbSoXe.setForeground(col);
        cbbLoaiXe.setEnabled(bool);
        lbLoaiXe.setForeground(col);
        cbbChoNgoi.setEnabled(bool);
        lbChoNgoi.setForeground(col);
        cbbHeSoGia.setEnabled(bool);
        lbHSG.setForeground(col);
        txtMaTX2.setEditable(bool);
        lbMaTX2.setForeground(col);
        
    }
    private void setEnableBtnXe(boolean them, boolean sua, boolean xoa, boolean huy){
        btnThem.setEnabled(them);
        btnSua.setEnabled(sua);
        btnXoa.setEnabled(xoa);
        btnHuy.setEnabled(huy);
    }
    
    
    private void loadCbbXe(){
        Connection connect =Code.KetNoi.layKetNoi();
        String sql="SELECT MaLoaiXe, TenLoaiXe, HeSoGia FROM LOAI_XE";
        String sql2="SELECT DISTINCT SoGhe FROM XE";
        try {
            PreparedStatement ps =connect.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                cbbLoaiXe.addItem(rs.getString(1)+"- "+rs.getString(2));
                cbbHeSoGia.addItem(rs.getString(3));
            }
            rs.close();
            ps.close();
            
            PreparedStatement ps1 =connect.prepareStatement(sql2);
            ResultSet rs1= ps1.executeQuery();
            while(rs1.next()){
                cbbChoNgoi.addItem(rs1.getString(1));
            }
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // kiểm tra xe có tồn tại không
    private boolean ktTrungXe(String soXe){
        String sql= "SELECT * FROM XE WHERE SoXe='"+soXe+"'";
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
            java.util.logging.Logger.getLogger(DangKyKhach.class.getName()).log(Level.SEVERE, null, e);
        }
         return false;
    }
    
    private void themSuaXe(String sql, String soXe, String soGhe, String loaiXe){
        Connection con =Code.KetNoi.layKetNoi();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, soXe);
            ps.setString(2, soGhe);
            ps.setString(3, loaiXe);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void xoaXe(String soXe){
        Connection con =Code.KetNoi.layKetNoi();
        String sql="DELETE FROM XE WHERE SoXe=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, soXe);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbMaTX = new javax.swing.JLabel();
        lbHoTen = new javax.swing.JLabel();
        lbGioiTinh = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        txtMaTX = new javax.swing.JTextField();
        lbLoiSoXe1 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTaiXe = new javax.swing.JTable();
        rBtnNam1 = new javax.swing.JRadioButton();
        rBtnNu1 = new javax.swing.JRadioButton();
        btnThemTX = new javax.swing.JButton();
        btnHuyTX = new javax.swing.JButton();
        btnXoaTX = new javax.swing.JButton();
        btnSuaTX = new javax.swing.JButton();
        lbCMND = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        lbSoXe = new javax.swing.JLabel();
        txtSoXe = new javax.swing.JTextField();
        lbLoaiXe = new javax.swing.JLabel();
        cbbLoaiXe = new javax.swing.JComboBox<>();
        cbbHeSoGia = new javax.swing.JComboBox<>();
        lbHSG = new javax.swing.JLabel();
        cbbChoNgoi = new javax.swing.JComboBox<>();
        lbChoNgoi = new javax.swing.JLabel();
        lbLoiSoXe = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbXe = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtMaTX2 = new javax.swing.JTextField();
        lbMaTX2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setBackground(new java.awt.Color(131, 199, 233));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin tài xế", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 16))); // NOI18N

        lbMaTX.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbMaTX.setText("Mã TX:");

        lbHoTen.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbHoTen.setText("Họ tên:");

        lbGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbGioiTinh.setText("Giới tính:");

        lbSDT.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbSDT.setText("SĐT:");

        txtMaTX.setEditable(false);

        lbLoiSoXe1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiSoXe1.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiSoXe1.setText("Vui lòng nhập đúng( 2 kí tự và 5-7 số)");
        lbLoiSoXe1.setVisible(false);

        txtHoTen.setEditable(false);

        txtSDT.setEditable(false);

        tbTaiXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã TX", "Họ tên", "CMND", "Giới tính", "SĐT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTaiXe.setRowHeight(25);
        tbTaiXe.setRowMargin(3);
        tbTaiXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTaiXeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbTaiXe);

        rBtnNam1.setText("Nam");
        rBtnNam1.setEnabled(false);

        rBtnNu1.setText("Nữ");
        rBtnNu1.setEnabled(false);

        btnThemTX.setBackground(new java.awt.Color(131, 199, 233));
        btnThemTX.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThemTX.setText("Thêm");
        btnThemTX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTXActionPerformed(evt);
            }
        });

        btnHuyTX.setBackground(new java.awt.Color(131, 199, 233));
        btnHuyTX.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHuyTX.setText("Hủy");
        btnHuyTX.setEnabled(false);
        btnHuyTX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnXoaTX.setBackground(new java.awt.Color(131, 199, 233));
        btnXoaTX.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoaTX.setText("Xóa");
        btnXoaTX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTXActionPerformed(evt);
            }
        });

        btnSuaTX.setBackground(new java.awt.Color(131, 199, 233));
        btnSuaTX.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSuaTX.setText("Sửa");
        btnSuaTX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTXActionPerformed(evt);
            }
        });

        lbCMND.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbCMND.setText("CMND:");

        txtCMND.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbGioiTinh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rBtnNam1)
                                .addGap(18, 18, 18)
                                .addComponent(rBtnNu1)
                                .addGap(49, 49, 49))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbMaTX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbLoiSoXe1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaTX, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbHoTen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbCMND)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbSDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemTX, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnSuaTX, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaTX, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(btnHuyTX, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbMaTX)
                                    .addComponent(txtMaTX, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbLoiSoXe1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbHoTen)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbGioiTinh)
                                    .addComponent(rBtnNam1)
                                    .addComponent(rBtnNu1)
                                    .addComponent(lbCMND)
                                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSDT)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemTX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaTX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyTX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        lbSoXe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbSoXe.setText("Số xe:");

        txtSoXe.setEditable(false);

        lbLoaiXe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbLoaiXe.setText("Loại xe:");

        cbbLoaiXe.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        cbbLoaiXe.setMaximumRowCount(6);
        cbbLoaiXe.setEnabled(false);

        cbbHeSoGia.setEditable(true);
        cbbHeSoGia.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        cbbHeSoGia.setMaximumRowCount(6);
        cbbHeSoGia.setEnabled(false);

        lbHSG.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbHSG.setText("Hệ số giá:");

        cbbChoNgoi.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        cbbChoNgoi.setMaximumRowCount(6);
        cbbChoNgoi.setEnabled(false);

        lbChoNgoi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbChoNgoi.setText("Số Chỗ:");

        lbLoiSoXe.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiSoXe.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiSoXe.setText("Vui lòng nhập đúng( 2 kí tự và 5-7 số)");
        lbLoiSoXe.setVisible(false);

        tbXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số xe", "Loại xe", "Số chỗ", "Hệ số giá", "Mã TX"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbXe.setRowHeight(25);
        tbXe.setRowMargin(3);
        tbXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbXeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbXe);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Quản Lý Xe");

        txtMaTX2.setEditable(false);

        lbMaTX2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbMaTX2.setText("Mã TX:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbChoNgoi)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbChoNgoi, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbHSG)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbHeSoGia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbSoXe)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbLoiSoXe)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lbLoaiXe)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbLoaiXe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbMaTX2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaTX2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel12)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSoXe)
                            .addComponent(txtSoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbLoaiXe)
                            .addComponent(cbbLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLoiSoXe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbChoNgoi)
                            .addComponent(cbbChoNgoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbHSG)
                            .addComponent(cbbHeSoGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbMaTX2)
                            .addComponent(txtMaTX2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbXeMouseClicked
        // TODO add your handling code here:
        int rowSelect=tbXe.getSelectedRow();
        txtSoXe.setText(tbXe.getValueAt(rowSelect, 0).toString());
        cbbLoaiXe.setSelectedItem(tbXe.getValueAt(rowSelect, 1).toString());
        cbbChoNgoi.setSelectedItem(tbXe.getValueAt(rowSelect, 2).toString());
        cbbHeSoGia.setSelectedItem(tbXe.getValueAt(rowSelect, 3).toString());
        

    }//GEN-LAST:event_tbXeMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(btnThem.getText().equals("Thêm")){
            txtSoXe.setText("");
            setEditableXe(true,Color.GREEN);
            setEnableBtnXe(true, false, false, true);
            setEnableBtnTaiXe(false, false, false, false);
            txtSoXe.setEditable(true);
            btnThem.setText("Lưu");
            btnHuy.setEnabled(true);
            
        }else {
            String soXe=txtSoXe.getText();
            
            if(soXe.matches("\\w{2}\\d{5,7}")){lbLoiSoXe.setVisible(false);}
            else{lbLoiSoXe.setVisible(true);
                return;
            }
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận thêm xe: "+soXe, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                String loaiXe=cbbLoaiXe.getSelectedItem().toString();
                loaiXe=loaiXe.substring(0,loaiXe.indexOf("-"));
                String soGhe=cbbChoNgoi.getSelectedItem().toString();
                String heSoGia=cbbHeSoGia.getSelectedItem().toString();
                themSuaXe("INSERT INTO XE VALUES (?,?,?)",soXe, soGhe, loaiXe);
                    
                JOptionPane.showMessageDialog(this, "Đã thêm xe thành công");
                xuLyBang.loadDuLieuVaoBang(tbXe, "{call SP_LOAD_XE ()}");
                btnThem.setText("Thêm");
                setEnableBtnXe(true, true, true, false);
                setEnableBtnTaiXe(true, true, true, false);
                txtSoXe.setEditable(false);
                setEditableXe(false, null);
            }  
        }
        
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        //reset về ban đầu
        btnThem.setText("Thêm");
        btnSua.setText("Sửa");
        btnXoa.setText("Xóa");
        btnThemTX.setText("Thêm");
        btnSuaTX.setText("Sửa");
        btnXoaTX.setText("Xóa");
        txtSoXe.setText(tbXe.getValueAt(tbXe.getSelectedRow(), 0).toString());
        lbLoiSoXe.setVisible(false);
        
        setEditableXe(false, null);
        setEnableBtnXe(true, true, true, false);
        setEnableBtnTaiXe(true, true, true, false);
    }//GEN-LAST:event_btnHuyActionPerformed

String soXeGoc="";// biến dùng để lưu trữ số xe trước khi sửa - dùng để truy vấn 
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        
        if(tbXe.getSelectionModel().isSelectionEmpty()){// kiểm tra người dùng đã click chọn dữ liệu trong bảng chưa
         JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin cần sửa trong bảng");}
        
        if(btnSua.getText().equals("Sửa")){// kt tra nếu Button đang ở trạng thái Sửa thì
            soXeGoc=txtSoXe.getText();// lưu số xe cần sửa
            setEditableXe(true,Color.blue); // mở khóa các combobox cho người dùng chọn
            setEnableBtnXe(false, true, false, true); // mở khóa các button cần phục vụ cho chức năng
            setEnableBtnTaiXe(false, false, false, false);
            
            btnSua.setText("Lưu");// đổi text của button từ Sửa-> Lưu
            btnHuy.setEnabled(true);
            
        }else {//kt tra nếu Button đang ở trạng thái LƯU thì
            String soXeSau=txtSoXe.getText();// lưu số xe sau khi sửa
            
            if(soXeSau.matches("\\w{2}\\d{5,7}"))// kt đã nhập đúng định dạng chưa
            {
                lbLoiSoXe.setVisible(false);     
            }else{
                lbLoiSoXe.setText("Vui lòng nhập đúng( 2 kí tự và 5-7 số)");
                lbLoiSoXe.setVisible(true);
            }
                
            if(ktTrungXe(soXeSau)){//kt tra số xe có trùng không
                lbLoiSoXe.setText("Số xe đã tồn tại!!");
                lbLoiSoXe.setVisible(true);
                return;
            }
            // xác nhận lại
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Sửa thông tin xe: "+soXeGoc, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
            String loaiXe=cbbLoaiXe.getSelectedItem().toString();
            loaiXe=loaiXe.substring(0,loaiXe.indexOf("-"));
            String soGhe=cbbChoNgoi.getSelectedItem().toString();
            String heSoGia=cbbHeSoGia.getSelectedItem().toString();
            // sửa thông tin xe
            themSuaXe("UPDATE XE SET SoXe=?, SoGhe=?, MaLoaiXe=? WHERE SoXe='"+soXeGoc+"'",soXeSau, soGhe, loaiXe);

            JOptionPane.showMessageDialog(this, "Đã Sửa thông tin xe thành công");
            xuLyBang.loadDuLieuVaoBang(tbXe, "{call SP_LOAD_XE ()}");// cập nhập lại bảng

            //cập nhập lại các cbb và button
            btnSua.setText("Sửa");
            setEnableBtnXe(true, true, true, false);
            setEnableBtnTaiXe(true, true, true, false);
            setEditableXe(false,null);
            }
        }
       
        
            
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if(!tbXe.getSelectionModel().isSelectionEmpty()){
            if(btnXoa.getText().equals("Xóa")){// kt tra nếu Button đang ở trạng thái Sửa thì
                setEnableBtnXe(false, false, true, true); // mở khóa các button cần phục vụ cho chức năng
                setEnableBtnTaiXe(false, false, false, false);
                setEditableXe(false, Color.red);
                
                btnXoa.setText("Lưu");// đổi text của button từ Xóa-> Lưu
                btnHuy.setEnabled(true);
            
            }else {//kt tra nếu Button đang ở trạng thái LƯU thì
                String soXe=tbXe.getValueAt(tbXe.getSelectedRow(), 0).toString();// lưu số xe để xóa
                 // xác nhận lại
                int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Xóa thông tin về xe: "+soXe, "Thông Báo",0);
                if(chon==JOptionPane.OK_OPTION){
                // Xóa xe
                xoaXe(soXe);

                JOptionPane.showMessageDialog(this, "Đã Xóa thông tin xe thành công");
                xuLyBang.loadDuLieuVaoBang(tbXe, "{call SP_LOAD_XE ()}");// cập nhập lại bảng

                //cập nhập lại các cbb và button
                btnXoa.setText("Xóa");
                setEnableBtnXe(true, true, true, false);
                setEnableBtnTaiXe(true, true, true, false);
                setEditableXe(false, null);
                }
            }
       
        }else JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin cần sửa trong bảng");
            
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tbTaiXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTaiXeMouseClicked
        // TODO add your handling code here:
        int rowSelect=tbTaiXe.getSelectedRow();
        txtMaTX.setText(tbTaiXe.getValueAt(rowSelect, 0).toString());
        txtHoTen.setText(tbTaiXe.getValueAt(rowSelect, 1).toString());
        txtCMND.setText(tbTaiXe.getValueAt(rowSelect, 2).toString());
        String gt=tbTaiXe.getValueAt(rowSelect, 3).toString();
        if(gt.equals("Nam")){rBtnNam1.setSelected(true);}
        else {rBtnNu1.setSelected(true);}
        txtSDT.setText(tbTaiXe.getValueAt(rowSelect, 4).toString());
    }//GEN-LAST:event_tbTaiXeMouseClicked

    private void btnThemTXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTXActionPerformed
        // TODO add your handling code here:
        if(btnThemTX.getText().equals("Thêm")){
            setField("", "", "", false, false, "");
            txtMaTX.setText(layMaTX());
            lbMaTX.setForeground(Color.red);
            setEditableTaiXe(true,Color.GREEN);
            setEnableBtnTaiXe(true, false, false, true);
            setEnableBtnXe(false, false, false, false);
            btnThemTX.setText("Lưu");
            btnHuyTX.setEnabled(true);
            
        }else {
            String maTx=txtMaTX.getText();
            String hoTen=txtHoTen.getText();
            String cmnd=txtCMND.getText();
            String gt=rBtnNam1.isSelected()?"Nam":"Nữ";
            String sdt=txtSDT.getText();
            if(maTx.isEmpty() || hoTen.isEmpty() || gt.isEmpty() || sdt.isEmpty() || cmnd.isEmpty()){
                JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
                return;
            }
            if(ktTrungTX(cmnd, sdt)){
                return;
            }
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận thêm tài xế: "+maTx, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                themSuaTX("INSERT INTO TAI_XE VALUES (?,?,?,?,?)",maTx, hoTen, cmnd,gt,sdt);
                    
                JOptionPane.showMessageDialog(this, "Đã thêm Tài Xế thành công");
                xuLyBang.loadDuLieuVaoBang(tbTaiXe, "SELECT * FROM TAI_XE");
                btnThemTX.setText("Thêm");
                setEnableBtnXe(true, true, true, false);
                setEnableBtnTaiXe(true, true, true, false);
                setEditableTaiXe(false, null);
            }  
        }
    }//GEN-LAST:event_btnThemTXActionPerformed

    private void btnSuaTXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTXActionPerformed
        // TODO add your handling code here:
        if(btnSuaTX.getText().equals("Sửa")){
            lbMaTX.setForeground(Color.red);
            setEditableTaiXe(true,Color.GREEN);
            setEnableBtnTaiXe(false, true, false, true);
            setEnableBtnXe(false, false, false, false);
            btnSuaTX.setText("Lưu");
            btnHuyTX.setEnabled(true);
            
        }else {
            String maTx=txtMaTX.getText();
            String hoTen=txtHoTen.getText();
            String cmnd=txtCMND.getText();
            String gt=rBtnNam1.isSelected()?"Nam":"Nữ";
            String sdt=txtSDT.getText();
            if(maTx.isEmpty() || hoTen.isEmpty() || gt.isEmpty() || sdt.isEmpty() || cmnd.isEmpty()){
                JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
                return;
            }
//            if(ktTrungTX(cmnd, sdt)){
//                return;
//            }
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Sửa tài xế: "+maTx, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                themSuaTX("UPDATE TAI_XE SET MaTX=?, HoTen=?, CMND=?, GioiTinh=?, DienThoai=? WHERE MaTX='"+maTx+"'",maTx, hoTen, cmnd,gt,sdt);
                    
                JOptionPane.showMessageDialog(this, "Đã SỬA Tài Xế thành công");
                xuLyBang.loadDuLieuVaoBang(tbTaiXe, "SELECT * FROM TAI_XE");
                btnSuaTX.setText("Sửa");
                setEnableBtnXe(true, true, true, false);
                setEnableBtnTaiXe(true, true, true, false);
                setEditableTaiXe(false, null);
            }  
        }
    }//GEN-LAST:event_btnSuaTXActionPerformed

    private void btnXoaTXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTXActionPerformed
        // TODO add your handling code here:
        if(!tbTaiXe.getSelectionModel().isSelectionEmpty()){
            if(btnXoaTX.getText().equals("Xóa")){// kt tra nếu Button đang ở trạng thái Sửa thì
                setEnableBtnTaiXe(false, false, true, true); // mở khóa các button cần phục vụ cho chức năng
                setEnableBtnXe(false, false, false, false);
                setEditableTaiXe(false, Color.red);
                
                btnXoaTX.setText("Lưu");// đổi text của button từ Xóa-> Lưu
                btnHuyTX.setEnabled(true);
            
            }else {//kt tra nếu Button đang ở trạng thái LƯU thì
                String maTX=txtMaTX.getText();// lưu số xe để xóa
                 // xác nhận lại
                int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Xóa thông tin về tài xế: "+maTX, "Thông Báo",0);
                if(chon==JOptionPane.OK_OPTION){
                // Xóa xe
                    xoaTX(maTX);

                JOptionPane.showMessageDialog(this, "Đã xóa Tài Xế thành công");
                xuLyBang.loadDuLieuVaoBang(tbTaiXe, "SELECT * FROM TAI_XE");
                btnSuaTX.setText("Xóa");
                setEnableBtnXe(true, true, true, false);
                setEnableBtnTaiXe(true, true, true, false);
                setEditableTaiXe(false, null);
                }
            }
       
        }else JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin cần Chỉnh sửa trong bảng");
    }//GEN-LAST:event_btnXoaTXActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuyTX;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaTX;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemTX;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTX;
    private javax.swing.JComboBox<String> cbbChoNgoi;
    private javax.swing.JComboBox<String> cbbHeSoGia;
    private javax.swing.JComboBox<String> cbbLoaiXe;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCMND;
    private javax.swing.JLabel lbChoNgoi;
    private javax.swing.JLabel lbGioiTinh;
    private javax.swing.JLabel lbHSG;
    private javax.swing.JLabel lbHoTen;
    private javax.swing.JLabel lbLoaiXe;
    private javax.swing.JLabel lbLoiSoXe;
    private javax.swing.JLabel lbLoiSoXe1;
    private javax.swing.JLabel lbMaTX;
    private javax.swing.JLabel lbMaTX2;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbSoXe;
    private javax.swing.JRadioButton rBtnNam1;
    private javax.swing.JRadioButton rBtnNu1;
    private javax.swing.JTable tbTaiXe;
    private javax.swing.JTable tbXe;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaTX;
    private javax.swing.JTextField txtMaTX2;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoXe;
    // End of variables declaration//GEN-END:variables
}
