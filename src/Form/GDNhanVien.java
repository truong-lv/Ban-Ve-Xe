/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author n18dc
 */
public class GDNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form GDNhanVien
     */
    public GDNhanVien() {
        initComponents();
        LbUserName.setText(BanVeXe.hoTen);
        lbLoaiTK.setText(BanVeXe.quyenToString());
    }

    //=================================HÀM XEM THÔNG TIN NHÂN VIÊN====================
    private void loadThongTinNV(){
        Connection connect=KetNoiSQL.KetNoi.layKetNoi();
        String sql="SELECT CMND, GioiTinh, DienThoai FROM NHAN_VIEN WHERE MaNV='"+BanVeXe.primaryKey+"'";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                lbMaNV.setText(BanVeXe.primaryKey);
                lbHoTen.setText(BanVeXe.hoTen);
                lbCMT.setText(rs.getString(1));
                lbGioiTinh.setText(rs.getString(2));
                lbSDT.setText(rs.getString(3));
                lbChucVu.setText(BanVeXe.quyenToString());
                lbTaiKhoan.setText(BanVeXe.Account);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    //=================================LOAD DỮ LIỆU VÀO BẢNG=========================
    private void loadDuLieuVaoBang(JTable tb, String sql){
    DefaultTableModel dtm=(DefaultTableModel)tb.getModel();
        dtm.setNumRows(0);
        //tb.setModel(dtm);
        Connection ketnoi=KetNoiSQL.KetNoi.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps=ketnoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                vt=new Vector();
                for(int i=1;i<=tb.getColumnCount();i++){
                    vt.add(rs.getString(i));
                }
                dtm.addRow(vt);
            }
            tb.setModel(dtm);
            rs.close();
            ps.close();
            ketnoi.close();
        } catch (SQLException e) {
            Logger.getLogger(GDNhanVien.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    private void duyetVe(){
        String trangThai=(String) tbVeXe.getValueAt(tbVeXe.getSelectedRow(), 5);
        if(trangThai.equals("0")){
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbPnMenu = new javax.swing.JTabbedPane();
        pnQlyVe = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSearchVe = new javax.swing.JTextField();
        btnSearchVe = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbVeXe = new javax.swing.JTable();
        btnDuyetVe = new javax.swing.JButton();
        btnHuyVe = new javax.swing.JButton();
        btnSuaVe = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbbSort1 = new javax.swing.JComboBox<>();
        btnTypeSort1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        cbbSort2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        pnQlyKhach = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhachHang = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbbSort = new javax.swing.JComboBox<>();
        btnTypeSort = new javax.swing.JButton();
        pnThongTinTK = new javax.swing.JPanel();
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
        jLabel5 = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        lbHoTen = new javax.swing.JLabel();
        lbCMT = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        lbChucVu = new javax.swing.JLabel();
        lbTaiKhoan = new javax.swing.JLabel();
        lbMatKhau = new javax.swing.JLabel();
        btnSeePass = new javax.swing.JButton();
        lbGioiTinh = new javax.swing.JLabel();
        pnInfor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LbUserName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbLoaiTK = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBtnCancelGDNhanVien = new javax.swing.JButton();
        lbTopBanner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHẦN MỀM BÁN VÉ XE");
        setLocation(new java.awt.Point(600, 200));
        setResizable(false);

        tbPnMenu.setBackground(new java.awt.Color(102, 102, 255));
        tbPnMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbPnMenu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbPnMenu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbPnMenuStateChanged(evt);
            }
        });

        pnQlyVe.setBackground(new java.awt.Color(0, 150, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Quản Lý Vé Xe");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Chọn:");

        btnSearchVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search-icon.png"))); // NOI18N
        btnSearchVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchVeActionPerformed(evt);
            }
        });

        tbVeXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã vé", "Điên thoại ", "Ghế ngồi", "Giá vé", "Thời gian", "Trạng thái", "Mã nhân viên"
            }
        ));
        tbVeXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVeXeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbVeXe);
        if (tbVeXe.getColumnModel().getColumnCount() > 0) {
            tbVeXe.getColumnModel().getColumn(2).setMinWidth(70);
            tbVeXe.getColumnModel().getColumn(2).setMaxWidth(70);
            tbVeXe.getColumnModel().getColumn(4).setMinWidth(200);
            tbVeXe.getColumnModel().getColumn(4).setMaxWidth(200);
        }

        btnDuyetVe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDuyetVe.setText("Duyệt vé");
        btnDuyetVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuyetVeActionPerformed(evt);
            }
        });

        btnHuyVe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuyVe.setText("Hủy vé");

        btnSuaVe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaVe.setText("Sửa Vé");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Sắp xếp theo:");

        cbbSort1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thời gian", "Mã vé", "Điện thoại", " " }));

        btnTypeSort1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Up-icon.png"))); // NOI18N
        btnTypeSort1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeSort1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Trạng Thái Vé:");

        cbbSort2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã đặt", "Đã duyệt", "Tất cả", " " }));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Có thể click chuột chọn");

        javax.swing.GroupLayout pnQlyVeLayout = new javax.swing.GroupLayout(pnQlyVe);
        pnQlyVe.setLayout(pnQlyVeLayout);
        pnQlyVeLayout.setHorizontalGroup(
            pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(pnQlyVeLayout.createSequentialGroup()
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnDuyetVe, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(btnSuaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btnHuyVe, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbSort1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTypeSort1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbSort2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addGroup(pnQlyVeLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchVe, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearchVe, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        pnQlyVeLayout.setVerticalGroup(
            pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQlyVeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(20, 20, 20)
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchVe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtSearchVe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(cbbSort1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnTypeSort1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cbbSort2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDuyetVe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHuyVe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        tbPnMenu.addTab("QUẢN LÝ VÉ XE", pnQlyVe);

        pnQlyKhach.setBackground(new java.awt.Color(0, 150, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Quản Lý Khách Hàng");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Chọn:");

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search-icon.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SĐT", "Họ Tên", "Giới Tính", "Tài Khoản"
            }
        ));
        tbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhachHang);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Thêm");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Xóa");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Sửa");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sắp xếp theo:");

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Điện Thoại", "Họ Tên", " " }));

        btnTypeSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Up-icon.png"))); // NOI18N
        btnTypeSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeSortActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnQlyKhachLayout = new javax.swing.GroupLayout(pnQlyKhach);
        pnQlyKhach.setLayout(pnQlyKhachLayout);
        pnQlyKhachLayout.setHorizontalGroup(
            pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                        .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                        .addGap(155, 155, 155)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                        .addGap(307, 307, 307)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(78, 78, 78)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                .addGap(291, 291, 291)
                                .addComponent(jLabel6))
                            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                .addGap(206, 206, 206)
                                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnTypeSort, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 238, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnQlyKhachLayout.setVerticalGroup(
            pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(cbbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTypeSort, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tbPnMenu.addTab("QUẢN LÝ KHÁCH HÀNG", pnQlyKhach);

        pnThongTinTK.setBackground(new java.awt.Color(0, 150, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Thông tin cá nhân:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Thông tin Tài Khoản:");

        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Họ Tên:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("SĐT:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Giới Tính:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Tên Tài Khoản:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Mật khẩu:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Mã NV:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("CMND/CCCD:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Chức vụ:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 153));
        jLabel5.setText("(Nhân viên không có quyền hiệu chỉnh)");

        lbMaNV.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbMaNV.setForeground(new java.awt.Color(255, 255, 255));

        lbHoTen.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbHoTen.setForeground(new java.awt.Color(255, 255, 255));

        lbCMT.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbCMT.setForeground(new java.awt.Color(255, 255, 255));

        lbSDT.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbSDT.setForeground(new java.awt.Color(255, 255, 255));

        lbChucVu.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbChucVu.setForeground(new java.awt.Color(255, 255, 255));

        lbTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));

        lbMatKhau.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lbMatKhau.setText("*******");

        btnSeePass.setForeground(new java.awt.Color(255, 255, 255));
        btnSeePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Eye-2-icon.png"))); // NOI18N
        btnSeePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeePassActionPerformed(evt);
            }
        });

        lbGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbGioiTinh.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnThongTinTKLayout = new javax.swing.GroupLayout(pnThongTinTK);
        pnThongTinTK.setLayout(pnThongTinTKLayout);
        pnThongTinTKLayout.setHorizontalGroup(
            pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCMT)
                            .addComponent(lbSDT)))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel23)
                        .addGap(14, 14, 14)
                        .addComponent(lbMaNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17))
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbHoTen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbGioiTinh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel16)
                        .addGap(121, 121, 121)))
                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbChucVu))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTaiKhoan))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbMatKhau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeePass, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(144, 144, 144))
            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(98, 98, 98))
        );
        pnThongTinTKLayout.setVerticalGroup(
            pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(63, 63, 63)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(lbMaNV))
                        .addGap(33, 33, 33)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(lbCMT))
                        .addGap(27, 27, 27)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lbSDT)))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(lbTaiKhoan))
                        .addGap(38, 38, 38)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSeePass, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbMatKhau)
                                .addComponent(jLabel21)))
                        .addGap(62, 62, 62)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(lbChucVu))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(96, 96, 96))
        );

        tbPnMenu.addTab("XEM THÔNG TIN CÁ NHÂN", pnThongTinTK);

        pnInfor.setBackground(new java.awt.Color(102, 153, 0));
        pnInfor.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Người dùng:");
        pnInfor.add(jLabel1);
        jLabel1.setBounds(50, 40, 100, 20);

        LbUserName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        pnInfor.add(LbUserName);
        LbUserName.setBounds(160, 30, 180, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Loại tài khoản:");
        pnInfor.add(jLabel4);
        jLabel4.setBounds(40, 70, 122, 20);

        lbLoaiTK.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        pnInfor.add(lbLoaiTK);
        lbLoaiTK.setBounds(170, 60, 150, 40);
        pnInfor.add(jLabel3);
        jLabel3.setBounds(574, 31, 0, 0);

        jBtnCancelGDNhanVien.setBackground(new java.awt.Color(0, 100, 255));
        jBtnCancelGDNhanVien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBtnCancelGDNhanVien.setText("ĐĂNG XUẤT");
        jBtnCancelGDNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelGDNhanVienActionPerformed(evt);
            }
        });
        pnInfor.add(jBtnCancelGDNhanVien);
        jBtnCancelGDNhanVien.setBounds(20, 110, 120, 40);

        lbTopBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageBackground/banner - Copy.png"))); // NOI18N
        pnInfor.add(lbTopBanner);
        lbTopBanner.setBounds(0, 0, 840, 160);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbPnMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbPnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCancelGDNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelGDNhanVienActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jBtnCancelGDNhanVienActionPerformed

    private void tbPnMenuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbPnMenuStateChanged
        //BẮT SỰ KIỆN THAY ĐỔI MENU
        if(tbPnMenu.getSelectedIndex()==0){
            String sql="SELECT MaVe, DienThoai, ViTriGhe, GiaVe, ThoiGian=NgayDi+'  '+CONVERT(varchar, CX.GioDi,120), VX.TrangThai, MaNV "
                    +"FROM VE_XE VX, CHUYEN_XE CX WHERE VX.MaChuyenXe=CX.MaChuyenXe";
            loadDuLieuVaoBang(tbVeXe,sql);
        }
        else if(tbPnMenu.getSelectedIndex()==1){
            loadDuLieuVaoBang(tbKhachHang,"SELECT * FROM HANH_KHACH");
        }
        else if(tbPnMenu.getSelectedIndex()==2){
            loadThongTinNV();
        }
    }//GEN-LAST:event_tbPnMenuStateChanged

    private void btnSeePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeePassActionPerformed
        // TODO add your handling code here:
        if(lbMatKhau.getText().equals("*******")){
            lbMatKhau.setText(BanVeXe.pass);
        }else lbMatKhau.setText("*******");

    }//GEN-LAST:event_btnSeePassActionPerformed

    private void btnTypeSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeSortActionPerformed
        String namePicture=btnTypeSort.getIcon().toString().substring(btnTypeSort.getIcon().toString().lastIndexOf("/")+1);
        if(namePicture.equals("Arrows-Up-icon.png")){
            btnTypeSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Down-icon.png")));
        }else {
            btnTypeSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Up-icon.png")));
        }
    }//GEN-LAST:event_btnTypeSortActionPerformed

    private void tbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseClicked
        // TODO add your handling code here:
        String dataSelect=(String) tbKhachHang.getValueAt(tbKhachHang.getSelectedRow(), 0);
        txtSearch.setText(dataSelect);
    }//GEN-LAST:event_tbKhachHangMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnTypeSort1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeSort1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTypeSort1ActionPerformed

    private void tbVeXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVeXeMouseClicked
        // TODO add your handling code here:
        String tbSelect=(String) tbVeXe.getValueAt(tbVeXe.getSelectedRow(), 0);
        txtSearchVe.setText(tbSelect);
    }//GEN-LAST:event_tbVeXeMouseClicked

    private void btnSearchVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchVeActionPerformed

    private void btnDuyetVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuyetVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDuyetVeActionPerformed

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
            java.util.logging.Logger.getLogger(GDNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GDNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GDNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GDNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GDNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbUserName;
    private javax.swing.JButton btnDuyetVe;
    private javax.swing.JButton btnHuyVe;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchVe;
    private javax.swing.JButton btnSeePass;
    private javax.swing.JButton btnSuaVe;
    private javax.swing.JButton btnTypeSort;
    private javax.swing.JButton btnTypeSort1;
    private javax.swing.JComboBox<String> cbbSort;
    private javax.swing.JComboBox<String> cbbSort1;
    private javax.swing.JComboBox<String> cbbSort2;
    private javax.swing.JButton jBtnCancelGDNhanVien;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbCMT;
    private javax.swing.JLabel lbChucVu;
    private javax.swing.JLabel lbGioiTinh;
    private javax.swing.JLabel lbHoTen;
    private javax.swing.JLabel lbLoaiTK;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMatKhau;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbTaiKhoan;
    private javax.swing.JLabel lbTopBanner;
    private javax.swing.JPanel pnInfor;
    private javax.swing.JPanel pnQlyKhach;
    private javax.swing.JPanel pnQlyVe;
    private javax.swing.JPanel pnThongTinTK;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTabbedPane tbPnMenu;
    private javax.swing.JTable tbVeXe;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchVe;
    // End of variables declaration//GEN-END:variables
}
