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
        
    }
//==============CÁC HÀM XỬ LÝ BẢNG LOẠI XE=========================
    private void setField(String maTX, String ten, String hsg){
        txtMaLX.setText(maTX);
        txtTenLoai.setText(ten);
        txtHeSoGia.setText(hsg);
    }
    
    private void setEnableBtnLoaiXe(boolean them, boolean sua, boolean xoa, boolean huy){
        btnThemLX.setEnabled(them);
        btnSuaLX.setEnabled(sua);
        btnXoaLX.setEnabled(xoa);
        btnHuyLX.setEnabled(huy);
        if(huy==true){// nếu đang trong trạng thái thêm/xóa/sửa/thêm TK thì ko cho tìm kiếm
            tbLoaiXe.setEnabled(false);
            
        }
        else{
            tbLoaiXe.setEnabled(true);
        }
    }
    
    private boolean ktTrungLoaiXe(String ma){
        java.sql.Connection connect=Code.KetNoi.layKetNoi();
        try {
            PreparedStatement ps=connect.prepareStatement("SELECT MaLoaiXe FROM LOAI_XE WHERE MaLoaiXe='"+ma+"'");
            ResultSet rs=ps.executeQuery();
            rs=ps.executeQuery();
            // duyet ket qua
            while (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    
    private void setEditableLoaiXe(boolean bool, Color col){
        lbMaLX.setForeground(col);
        lbTenLoai.setForeground(col);
        txtTenLoai.setEditable(bool);
        lbHSG.setForeground(col);
        txtHeSoGia.setEditable(bool);
    }
   
    private void themSuaLoaiXe(String sql, String ma, String ten, String hsg){
        Connection con =Code.KetNoi.layKetNoi();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, ma);
            ps.setString(2, ten);
            ps.setString(3, hsg);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private boolean ktXoaLoaiXe(String ma){
        Connection con =Code.KetNoi.layKetNoi();
        String sql="SELECT MaLoaiXe FROM XE WHERE MaLoaiXe=?";
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
            java.util.logging.Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }
    
    private void xoaLoaiXe(String ma){
        Connection con =Code.KetNoi.layKetNoi();
        String sql="DELETE FROM LOAI_XE WHERE MaLoaiXe=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, ma);
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
        
    }
    
    private void setEnableBtnXe(boolean them, boolean sua, boolean xoa, boolean huy){
        btnThemXe.setEnabled(them);
        btnSuaXe.setEnabled(sua);
        btnXoaXe.setEnabled(xoa);
        btnHuy.setEnabled(huy);
        if(huy==true){// nếu đang trong trạng thái thêm/xóa/sửa/thêm TK thì ko cho tìm kiếm
            tbXe.setEnabled(false);
            txtTimKiem.setEditable(false);
        }
        else{
            tbXe.setEnabled(true);
            txtTimKiem.setEditable(true);
        }
    }
    
    private void loadCbbXe(){
        cbbLoaiXe.removeAllItems();  
        cbbChoNgoi.removeAllItems();
        Connection connect =Code.KetNoi.layKetNoi();
        String sql="SELECT MaLoaiXe, TenLoaiXe FROM LOAI_XE";
        String sql2="SELECT DISTINCT SoGhe FROM XE";
        try {
            PreparedStatement ps =connect.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            // load combobox LOẠI XE và HỆ SỐ GIÁ
            while(rs.next()){
                cbbLoaiXe.addItem(rs.getString(1)/*+"- "+rs.getString(2)*/);
//                cbbHeSoGia.addItem(rs.getString(3));
            }
            rs.close();
            ps.close();
            //load combobox SO CHO NGOI
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
    
    private boolean ktXoaXe(String soXe){
        Connection con =Code.KetNoi.layKetNoi();
        String sql="SELECT SoXe FROM CHUYEN_XE WHERE SoXe=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, soXe);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return false;
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyXe.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
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

        btnThemXe = new javax.swing.JButton();
        btnXoaXe = new javax.swing.JButton();
        btnSuaXe = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lbSoXe = new javax.swing.JLabel();
        txtSoXe = new javax.swing.JTextField();
        lbLoaiXe = new javax.swing.JLabel();
        cbbLoaiXe = new javax.swing.JComboBox<>();
        cbbChoNgoi = new javax.swing.JComboBox<>();
        lbChoNgoi = new javax.swing.JLabel();
        lbLoiSoXe = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbXe = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        lbMaLX = new javax.swing.JLabel();
        lbTenLoai = new javax.swing.JLabel();
        txtMaLX = new javax.swing.JTextField();
        lbLoiLoaiXe = new javax.swing.JLabel();
        txtTenLoai = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbLoaiXe = new javax.swing.JTable();
        btnThemLX = new javax.swing.JButton();
        btnHuyLX = new javax.swing.JButton();
        btnXoaLX = new javax.swing.JButton();
        btnSuaLX = new javax.swing.JButton();
        lbHSG = new javax.swing.JLabel();
        txtHeSoGia = new javax.swing.JTextField();
        lbLoiHSG = new javax.swing.JLabel();
        jLabel_Sdt1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });

        btnThemXe.setBackground(new java.awt.Color(131, 199, 233));
        btnThemXe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnThemXe.setText("Thêm");
        btnThemXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemXeActionPerformed(evt);
            }
        });

        btnXoaXe.setBackground(new java.awt.Color(131, 199, 233));
        btnXoaXe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnXoaXe.setText("Xóa");
        btnXoaXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaXeActionPerformed(evt);
            }
        });

        btnSuaXe.setBackground(new java.awt.Color(131, 199, 233));
        btnSuaXe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSuaXe.setText("Sửa");
        btnSuaXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaXeActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(131, 199, 233));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setEnabled(false);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lbSoXe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbSoXe.setText("Số xe:");

        txtSoXe.setEditable(false);

        lbLoaiXe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbLoaiXe.setText("Loại xe:");

        cbbLoaiXe.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        cbbLoaiXe.setMaximumRowCount(6);
        cbbLoaiXe.setEnabled(false);

        cbbChoNgoi.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        cbbChoNgoi.setMaximumRowCount(6);
        cbbChoNgoi.setEnabled(false);

        lbChoNgoi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbChoNgoi.setText("Số Chỗ:");

        lbLoiSoXe.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiSoXe.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiSoXe.setText("Số xe không hợp lệ !!");
        lbLoiSoXe.setVisible(false);

        tbXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số xe", "Số chỗ", "Loại xe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại xe", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 16))); // NOI18N

        lbMaLX.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbMaLX.setText("Mã loại:");

        lbTenLoai.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbTenLoai.setText("Tên Loại:");

        txtMaLX.setEditable(false);

        lbLoiLoaiXe.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiLoaiXe.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiLoaiXe.setText("Mã đã tồn tại !!");
        lbLoiLoaiXe.setVisible(false);

        txtTenLoai.setEditable(false);

        tbLoaiXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Loại Xe", "Tên Loại", "Hệ số giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLoaiXe.setRowHeight(25);
        tbLoaiXe.setRowMargin(3);
        tbLoaiXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLoaiXeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbLoaiXe);

        btnThemLX.setBackground(new java.awt.Color(131, 199, 233));
        btnThemLX.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemLX.setText("Thêm");
        btnThemLX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLXActionPerformed(evt);
            }
        });

        btnHuyLX.setBackground(new java.awt.Color(131, 199, 233));
        btnHuyLX.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuyLX.setText("Hủy");
        btnHuyLX.setEnabled(false);
        btnHuyLX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnXoaLX.setBackground(new java.awt.Color(131, 199, 233));
        btnXoaLX.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoaLX.setText("Xóa");
        btnXoaLX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLXActionPerformed(evt);
            }
        });

        btnSuaLX.setBackground(new java.awt.Color(131, 199, 233));
        btnSuaLX.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaLX.setText("Sửa");
        btnSuaLX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLXActionPerformed(evt);
            }
        });

        lbHSG.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbHSG.setText("Hệ số giá:");

        txtHeSoGia.setEditable(false);

        lbLoiHSG.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiHSG.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiHSG.setText("Hãy Nhập số");
        lbLoiHSG.setVisible(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThemLX, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(btnSuaLX, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaLX, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btnHuyLX, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbMaLX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbLoiLoaiXe)
                                    .addComponent(txtMaLX, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTenLoai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbHSG)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbLoiHSG)
                                    .addComponent(txtHeSoGia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbMaLX)
                                    .addComponent(txtMaLX, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbLoiLoaiXe))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbTenLoai)
                                .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbHSG)
                            .addComponent(txtHeSoGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLoiHSG))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemLX, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaLX, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaLX, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyLX, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jLabel_Sdt1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Sdt1.setText("Tìm kiếm:");

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lbChoNgoi)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cbbChoNgoi, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(32, 32, 32)
                                                        .addComponent(btnThemXe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnSuaXe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btnXoaXe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel_Sdt1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(175, 175, 175)))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Sdt1))
                        .addGap(37, 37, 37)
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
                            .addComponent(cbbChoNgoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaXe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaXe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemXe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbXeMouseClicked
        // TODO add your handling code here:
        if(tbXe.getSelectionModel().isSelectionEmpty() || !(tbXe.isEnabled())){return;}
        int rowSelect=tbXe.getSelectedRow();
        txtSoXe.setText(tbXe.getValueAt(rowSelect, 0).toString());
        cbbChoNgoi.setSelectedItem(tbXe.getValueAt(rowSelect, 1).toString());
        cbbLoaiXe.setSelectedItem(tbXe.getValueAt(rowSelect, 2).toString());
        tbLoaiXe.clearSelection();
        

    }//GEN-LAST:event_tbXeMouseClicked

    private void btnThemXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemXeActionPerformed
        // TODO add your handling code here:
        if(btnThemXe.getText().equals("Thêm")){
            txtSoXe.setText("");
            txtHeSoGia.setText("");
            setEditableXe(true,Color.GREEN);
            setEnableBtnXe(true, false, false, true);
            setEnableBtnLoaiXe(false, false, false, false);
            txtSoXe.setEditable(true);
            btnThemXe.setText("Lưu");
            btnHuy.setEnabled(true);
            
        }else {
            String soXe=txtSoXe.getText();
            
            if(!soXe.trim().isEmpty()){lbLoiSoXe.setVisible(false);}
            else{
                lbLoiSoXe.setText("Số xe không hợp lệ !!");
                lbLoiSoXe.setVisible(true);
                return;
            }
            if(ktTrungXe(soXe)){
                lbLoiSoXe.setText("Số xe đã tồn tại !!");
                lbLoiSoXe.setVisible(true);
                return;
            }else lbLoiSoXe.setVisible(false);
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận thêm xe: "+soXe, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                String loaiXe=cbbLoaiXe.getSelectedItem().toString();
                loaiXe=loaiXe.substring(0,loaiXe.indexOf("-"));
                String soGhe=cbbChoNgoi.getSelectedItem().toString();
                themSuaXe("INSERT INTO XE VALUES (?,?,?)",soXe, soGhe, loaiXe);
                    
                JOptionPane.showMessageDialog(this, "Đã thêm xe thành công");
                xuLyBang.loadDuLieuVaoBang(tbXe, "SELECT * FROM XE");
                btnThemXe.setText("Thêm");
                setEnableBtnXe(true, true, true, false);
                setEnableBtnLoaiXe(true, true, true, false);
                txtSoXe.setEditable(false);
                setEditableXe(false, null);
            }  
        }
        
    }//GEN-LAST:event_btnThemXeActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        //reset button về ban đầu
        btnThemXe.setText("Thêm");
        btnSuaXe.setText("Sửa");
        btnXoaXe.setText("Xóa");
        btnThemLX.setText("Thêm");
        btnSuaLX.setText("Sửa");
        
        //tắt các thông báo lỗi
        lbLoiSoXe.setVisible(false);
        lbLoiLoaiXe.setVisible(false);
        lbLoiHSG.setVisible(false);
        
        //resset textField về trạng thái ban đầu
        tbLoaiXe.clearSelection();
        tbXe.clearSelection();
        setField("", "", "");
        txtSoXe.setText("");
        setEditableXe(false, null);
        setEditableLoaiXe(false, null);
        setEnableBtnXe(true, true, true, false);
        setEnableBtnLoaiXe(true, true, true, false);
    }//GEN-LAST:event_btnHuyActionPerformed


    private void btnSuaXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaXeActionPerformed
        // TODO add your handling code here:
        
        if(tbXe.getSelectionModel().isSelectionEmpty()){// kiểm tra người dùng đã click chọn dữ liệu trong bảng chưa
         JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin cần sửa trong bảng");
         return;
        }
        
        if(btnSuaXe.getText().equals("Sửa")){// kt tra nếu Button đang ở trạng thái Sửa thì
            setEditableXe(true,Color.blue); // mở khóa các combobox cho người dùng chọn
            txtSoXe.setEditable(false);
            setEnableBtnXe(false, true, false, true); // mở khóa các button cần phục vụ cho chức năng
            setEnableBtnLoaiXe(false, false, false, false);
            
            btnSuaXe.setText("Lưu");// đổi text của button từ Sửa-> Lưu
            btnHuy.setEnabled(true);
            
        }else {//kt tra nếu Button đang ở trạng thái LƯU thì
            String soXe=txtSoXe.getText();
            // xác nhận lại
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Sửa thông tin xe: "+soXe, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
            String loaiXe=cbbLoaiXe.getSelectedItem().toString();
            String soGhe=cbbChoNgoi.getSelectedItem().toString();
            // sửa thông tin xe
            themSuaXe("UPDATE XE SET SoGhe=?, MaLoaiXe=? WHERE SoXe=?",soGhe, loaiXe, soXe);

            JOptionPane.showMessageDialog(this, "Đã Sửa thông tin xe thành công");
            xuLyBang.loadDuLieuVaoBang(tbXe, "SELECT * FROM XE");// cập nhập lại bảng

            //cập nhập lại các cbb và button
            btnSuaXe.setText("Sửa");
            setEnableBtnXe(true, true, true, false);
            setEnableBtnLoaiXe(true, true, true, false);
            setEditableXe(false,null);
            }
        }
       
        
            
    }//GEN-LAST:event_btnSuaXeActionPerformed

    private void btnXoaXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaXeActionPerformed
        // TODO add your handling code here:
        if(!tbXe.getSelectionModel().isSelectionEmpty()){
            if(btnXoaXe.getText().equals("Xóa")){// kt tra nếu Button đang ở trạng thái Sửa thì
                if(!ktXoaXe(txtSoXe.getText())){
                    JOptionPane.showMessageDialog(this, "Xe đã từng hoạt động không thể xóa");
                    return;
                }
                
                setEnableBtnXe(false, false, true, true); // mở khóa các button cần phục vụ cho chức năng
                setEnableBtnLoaiXe(false, false, false, false);
                setEditableXe(false, Color.red);
                
                btnXoaXe.setText("Lưu");// đổi text của button từ Xóa-> Lưu
                btnHuy.setEnabled(true);
            
            }else {//kt tra nếu Button đang ở trạng thái LƯU thì
                String soXe=tbXe.getValueAt(tbXe.getSelectedRow(), 0).toString();// lưu số xe để xóa
                 // xác nhận lại
                int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Xóa thông tin về xe: "+soXe, "Thông Báo",0);
                if(chon==JOptionPane.OK_OPTION){
                // Xóa xe
                xoaXe(soXe);

                JOptionPane.showMessageDialog(this, "Đã Xóa thông tin xe thành công");
                xuLyBang.loadDuLieuVaoBang(tbXe, "SELECT * FROM XE");// cập nhập lại bảng

                //cập nhập lại các cbb và button
                btnXoaXe.setText("Xóa");
                setEnableBtnXe(true, true, true, false);
                setEnableBtnLoaiXe(true, true, true, false);
                setEditableXe(false, null);
                }
            }
       
        }else JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin cần xóa trong bảng");
            
    }//GEN-LAST:event_btnXoaXeActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
        xuLyBang.loadDuLieuVaoBang(tbLoaiXe, "SELECT * FROM LOAI_XE");
        xuLyBang.loadDuLieuVaoBang(tbXe, "SELECT * FROM XE");
        loadCbbXe();
    }//GEN-LAST:event_formHierarchyChanged

    private void btnSuaLXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLXActionPerformed
        // TODO add your handling code here:
        if(tbLoaiXe.getSelectionModel().isSelectionEmpty()){// kiểm tra người dùng đã click chọn dữ liệu trong bảng chưa
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin cần sửa trong bảng");
            return;
        }
        if(btnSuaLX.getText().equals("Sửa")){
            setEditableLoaiXe(true,Color.GREEN);
            lbMaLX.setForeground(Color.red);
            setEnableBtnLoaiXe(false, true, false, true);
            setEnableBtnXe(false, false, false, false);
            btnSuaLX.setText("Lưu");
            btnHuyLX.setEnabled(true);

        }else {
            String maTx=txtMaLX.getText();
            String hoTen=txtTenLoai.getText();
            String hsg=txtHeSoGia.getText();
            if(maTx.isEmpty() || hoTen.isEmpty() || hsg.isEmpty()){
                JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
                return;
            }
            if(!hsg.matches("[0-9|.]+")){
                lbLoiHSG.setVisible(true);
                return;
            }else lbLoiHSG.setVisible(false);
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Sửa loại xe: "+maTx, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                themSuaLoaiXe("UPDATE LOAI_XE SET MaLoaiXe=?, TenLoaiXe=?, HeSoGia=? WHERE MaLoaiXe='"+maTx+"'",maTx,hoTen,hsg);

                JOptionPane.showMessageDialog(this, "Đã Sửa Loại xe thành công");
                xuLyBang.loadDuLieuVaoBang(tbLoaiXe, "SELECT * FROM LOAI_XE");
                xuLyBang.loadDuLieuVaoBang(tbXe, "SELECT * FROM XE");// cập nhập lại bảng
                loadCbbXe();
                btnSuaLX.setText("Sửa");
                setEnableBtnXe(true, true, true, false);
                setEnableBtnLoaiXe(true, true, true, false);
                setEditableLoaiXe(false, null);
            }
        }
    }//GEN-LAST:event_btnSuaLXActionPerformed

    private void btnXoaLXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLXActionPerformed
        // TODO add your handling code here:
        if(!tbLoaiXe.getSelectionModel().isSelectionEmpty()){
            if(!ktXoaLoaiXe(txtMaLX.getText())){
                    JOptionPane.showMessageDialog(this, "Loại xe không thể xóa");
                    return;
                }
            if(btnXoaLX.getText().equals("Xóa")){// kt tra nếu Button đang ở trạng thái Sửa thì
                setEnableBtnLoaiXe(false, false, true, true); // mở khóa các button cần phục vụ cho chức năng
                setEnableBtnXe(false, false, false, false);
                setEditableLoaiXe(false, Color.red);

                btnXoaLX.setText("Lưu");// đổi text của button từ Xóa-> Lưu
                btnHuyLX.setEnabled(true);

            }else {//kt tra nếu Button đang ở trạng thái LƯU thì
                String maTX=txtMaLX.getText();// lưu số xe để xóa
                // xác nhận lại
                int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Xóa thông tin về loại xe: "+maTX, "Thông Báo",0);
                if(chon==JOptionPane.OK_OPTION){
                    // Xóa xe
                    xoaLoaiXe(maTX);

                    JOptionPane.showMessageDialog(this, "Đã xóa Loại xe thành công");
                    xuLyBang.loadDuLieuVaoBang(tbLoaiXe, "SELECT * FROM LOAI_XE");
                    xuLyBang.loadDuLieuVaoBang(tbXe, "SELECT * FROM XE");// cập nhập lại bảng
                    loadCbbXe();
                    btnXoaLX.setText("Xóa");
                    setEnableBtnXe(true, true, true, false);
                    setEnableBtnLoaiXe(true, true, true, false);
                    setEditableLoaiXe(false, null);
                }
            }

        }else JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin cần Xóa trong bảng");
    }//GEN-LAST:event_btnXoaLXActionPerformed

    private void btnThemLXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLXActionPerformed
        // TODO add your handling code here:

        if(btnThemLX.getText().equals("Thêm")){
            setField("", "", "");
            txtMaLX.setEditable(true);
            tbLoaiXe.clearSelection();
            setEditableLoaiXe(true,Color.GREEN);
            setEnableBtnLoaiXe(true, false, false, true);
            setEnableBtnXe(false, false, false, false);
            btnThemLX.setText("Lưu");
            btnHuyLX.setEnabled(true);

        }else {
            String maTx=txtMaLX.getText();
            String hoTen=txtTenLoai.getText();
            String hsg=txtHeSoGia.getText();
            if(maTx.isEmpty() || hoTen.isEmpty() || hsg.isEmpty()){
                JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
                return;
            }
            
            if(ktTrungLoaiXe(maTx)){
                lbLoiLoaiXe.setText("Mã đã tồn tại !!");
                lbLoiLoaiXe.setVisible(true);
                return;
            }else lbLoiLoaiXe.setVisible(false);
            
            if(!hsg.matches("[0-9]+")){
                lbLoiHSG.setVisible(true);
                return;
            }else lbLoiHSG.setVisible(false);
            
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận thêm Loại xe: "+maTx, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                themSuaLoaiXe("INSERT INTO LOAI_XE VALUES (?,?,?)",maTx, hoTen, hsg);

                JOptionPane.showMessageDialog(this, "Đã thêm Loại xe thành công");
                xuLyBang.loadDuLieuVaoBang(tbLoaiXe, "SELECT * FROM LOAI_XE");
                xuLyBang.loadDuLieuVaoBang(tbXe, "SELECT * FROM XE");// cập nhập lại bảng
                loadCbbXe();
                btnThemLX.setText("Thêm");
                setEnableBtnXe(true, true, true, false);
                setEnableBtnLoaiXe(true, true, true, false);
                setEditableLoaiXe(false, null);
            }
        }
    }//GEN-LAST:event_btnThemLXActionPerformed

    private void tbLoaiXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLoaiXeMouseClicked
        // TODO add your handling code here:
        if(tbLoaiXe.getSelectionModel().isSelectionEmpty() || !(tbLoaiXe.isEnabled())){return;}
        int rowSelect=tbLoaiXe.getSelectedRow();
        txtMaLX.setText(tbLoaiXe.getValueAt(rowSelect, 0).toString());
        txtTenLoai.setText(tbLoaiXe.getValueAt(rowSelect, 1).toString());
        txtHeSoGia.setText(tbLoaiXe.getValueAt(rowSelect, 2).toString());
        tbXe.clearSelection();
    }//GEN-LAST:event_tbLoaiXeMouseClicked

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        tbXe.clearSelection();
        if(!txtTimKiem.getText().isEmpty()){
            new HamXuLyBang().locTatCa(tbXe,txtTimKiem.getText(),0);
        }
        else new HamXuLyBang().locTatCa(tbXe,"",-1);
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuyLX;
    private javax.swing.JButton btnSuaLX;
    private javax.swing.JButton btnSuaXe;
    private javax.swing.JButton btnThemLX;
    private javax.swing.JButton btnThemXe;
    private javax.swing.JButton btnXoaLX;
    private javax.swing.JButton btnXoaXe;
    private javax.swing.JComboBox<String> cbbChoNgoi;
    private javax.swing.JComboBox<String> cbbLoaiXe;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel_Sdt1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbChoNgoi;
    private javax.swing.JLabel lbHSG;
    private javax.swing.JLabel lbLoaiXe;
    private javax.swing.JLabel lbLoiHSG;
    private javax.swing.JLabel lbLoiLoaiXe;
    private javax.swing.JLabel lbLoiSoXe;
    private javax.swing.JLabel lbMaLX;
    private javax.swing.JLabel lbSoXe;
    private javax.swing.JLabel lbTenLoai;
    private javax.swing.JTable tbLoaiXe;
    private javax.swing.JTable tbXe;
    private javax.swing.JTextField txtHeSoGia;
    private javax.swing.JTextField txtMaLX;
    private javax.swing.JTextField txtSoXe;
    private javax.swing.JTextField txtTenLoai;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
