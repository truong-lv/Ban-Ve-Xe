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
        selectPanel(pnMainMenu);
        LbUserName.setText(BanVeXe.hoTen);
        lbLoaiTK.setText(BanVeXe.quyenToString());
        
    }
    private void selectPanel(JPanel pn){
        pnThongTinTK.setVisible(false);
        pnQlyKhach.setVisible(false);
        pnQlyVe.setVisible(false);
        pnMainMenu.setVisible(false);
        pn.setVisible(true);
        
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
    
    
    //=================================LOAD DỮ LIỆU KHÁCH HÀNG VÀO BẢNG=========================
    private void loadKhachVaoBang(JTable tb, String tableQuery){
    DefaultTableModel dtm=(DefaultTableModel)tb.getModel();
        dtm.setNumRows(0);
        Connection ketnoi=KetNoiSQL.KetNoi.layKetNoi();
        String sql="SELECT * FROM "+tableQuery;
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
            tbKhachHang.setModel(dtm);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(GDNhanVien.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        pnInfor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LbUserName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbLoaiTK = new javax.swing.JLabel();
        pnMainMenu = new javax.swing.JPanel();
        jBtnCancelGDNhanVien = new javax.swing.JButton();
        btnQlyKhach = new javax.swing.JButton();
        btnQlyVeXe = new javax.swing.JButton();
        btnXemThongTin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        pnThongTinTK = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        lbHoTen = new javax.swing.JLabel();
        lbCMT = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        lbChucVu = new javax.swing.JLabel();
        lbTaiKhoan = new javax.swing.JLabel();
        lbMatKhau = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lbGioiTinh = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
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
        btnCancel2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbbSort = new javax.swing.JComboBox<>();
        btnTypeSort = new javax.swing.JButton();
        pnQlyVe = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSearch1 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbKhachHang1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnCancel3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbbSort1 = new javax.swing.JComboBox<>();
        btnTypeSort1 = new javax.swing.JButton();

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHẦN MỀM BÁN VÉ XE");
        setLocation(new java.awt.Point(600, 200));

        pnInfor.setBackground(new java.awt.Color(102, 102, 255));
        pnInfor.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Người dùng:");

        LbUserName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Loại tài khoản:");

        lbLoaiTK.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        javax.swing.GroupLayout pnInforLayout = new javax.swing.GroupLayout(pnInfor);
        pnInfor.setLayout(pnInforLayout);
        pnInforLayout.setHorizontalGroup(
            pnInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInforLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnInforLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LbUserName))
                    .addGroup(pnInforLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLoaiTK)))
                .addGap(0, 634, Short.MAX_VALUE))
        );
        pnInforLayout.setVerticalGroup(
            pnInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInforLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(LbUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pnInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(lbLoaiTK, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pnMainMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnCancelGDNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        jBtnCancelGDNhanVien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBtnCancelGDNhanVien.setText("Đăng xuất");
        jBtnCancelGDNhanVien.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnCancelGDNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelGDNhanVienActionPerformed(evt);
            }
        });

        btnQlyKhach.setBackground(new java.awt.Color(204, 204, 255));
        btnQlyKhach.setText("Quản lý khách hàng");
        btnQlyKhach.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnQlyKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQlyKhachActionPerformed(evt);
            }
        });

        btnQlyVeXe.setBackground(new java.awt.Color(204, 204, 255));
        btnQlyVeXe.setText("Quản lý vé xe");
        btnQlyVeXe.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnQlyVeXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQlyVeXeActionPerformed(evt);
            }
        });

        btnXemThongTin.setBackground(new java.awt.Color(204, 204, 255));
        btnXemThongTin.setText("Xem thông tin tài khoản");
        btnXemThongTin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXemThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemThongTinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMainMenuLayout = new javax.swing.GroupLayout(pnMainMenu);
        pnMainMenu.setLayout(pnMainMenuLayout);
        pnMainMenuLayout.setHorizontalGroup(
            pnMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainMenuLayout.createSequentialGroup()
                .addGroup(pnMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMainMenuLayout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addGroup(pnMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnQlyVeXe, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(btnQlyKhach, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(btnXemThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnMainMenuLayout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(jBtnCancelGDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMainMenuLayout.setVerticalGroup(
            pnMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMainMenuLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(btnXemThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnQlyKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnQlyVeXe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addComponent(jBtnCancelGDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Home");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Thông tin cá nhân:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Thông tin Tài Khoản:");

        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setText("Họ Tên:");

        jLabel18.setText("SĐT:");

        jLabel19.setText("Giới Tính:");

        jLabel20.setText("Tên Tài Khoản:");

        jLabel21.setText("Mật khẩu:");

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Go-back-icon.png"))); // NOI18N
        btnCancel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMainMenuActionPerformed(evt);
            }
        });

        jLabel23.setText("Mã NV:");

        jLabel22.setText("CMND/CCCD:");

        jLabel24.setText("Chức vụ:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Thông tin người dùng");

        jLabel5.setForeground(new java.awt.Color(255, 102, 102));
        jLabel5.setText("Nhân viên không có quyền hiệu chỉnh");

        lbMaNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lbHoTen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lbCMT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lbSDT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lbChucVu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lbTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lbMatKhau.setText("*******");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Eye-2-icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout pnThongTinTKLayout = new javax.swing.GroupLayout(pnThongTinTK);
        pnThongTinTK.setLayout(pnThongTinTKLayout);
        pnThongTinTKLayout.setHorizontalGroup(
            pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbHoTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbMaNV))
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
                        .addGap(264, 264, 264)
                        .addComponent(jLabel2))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(jLabel5))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinTKLayout.createSequentialGroup()
                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 247, Short.MAX_VALUE))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbGioiTinh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addGap(32, 32, 32)
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
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(48, 48, 48)))))
                .addGap(146, 146, 146))
        );
        pnThongTinTKLayout.setVerticalGroup(
            pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(lbMaNV))
                        .addGap(31, 31, 31)
                        .addComponent(lbHoTen)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel17)
                        .addGap(37, 37, 37)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lbGioiTinh))
                        .addGap(47, 47, 47)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(lbCMT))
                        .addGap(27, 27, 27)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lbSDT)))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinTKLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14)))
                                .addGap(33, 33, 33)
                                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(lbTaiKhoan))
                                .addGap(38, 38, 38)
                                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbMatKhau)
                                        .addComponent(jLabel21)))
                                .addGap(62, 62, 62)
                                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(lbChucVu))))))
                .addGap(44, 44, 44)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Quản Lý Khách Hàng");

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

        btnCancel2.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Go-back-icon.png"))); // NOI18N
        btnCancel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMainMenuActionPerformed(evt);
            }
        });

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
                        .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                .addGap(244, 244, 244)
                                .addComponent(jLabel6))
                            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTypeSort, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnQlyKhachLayout.setVerticalGroup(
            pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(33, 33, 33)
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("Quản Lý Vé Xe");

        jLabel11.setText("Chọn:");

        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search-icon.png"))); // NOI18N
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        tbKhachHang1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã vé", "Điên thoại ", "Ghế ngồi", "Giá vé", "Thời gian", "null", "null", "Title 8"
            }
        ));
        tbKhachHang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHang1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbKhachHang1);

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Thêm");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Xóa");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Sửa");

        btnCancel3.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Go-back-icon.png"))); // NOI18N
        btnCancel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCancel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackToMainMenuActionPerformed(evt);
            }
        });

        jLabel12.setText("Sắp xếp theo:");

        cbbSort1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Điện Thoại", "Họ Tên", " " }));

        btnTypeSort1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Up-icon.png"))); // NOI18N
        btnTypeSort1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeSort1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnQlyVeLayout = new javax.swing.GroupLayout(pnQlyVe);
        pnQlyVe.setLayout(pnQlyVeLayout);
        pnQlyVeLayout.setHorizontalGroup(
            pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQlyVeLayout.createSequentialGroup()
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnQlyVeLayout.createSequentialGroup()
                                .addGap(244, 244, 244)
                                .addComponent(jLabel10))
                            .addGroup(pnQlyVeLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
            .addGroup(pnQlyVeLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(btnCancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(pnQlyVeLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSort1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTypeSort1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnQlyVeLayout.setVerticalGroup(
            pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQlyVeLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(33, 33, 33)
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(cbbSort1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTypeSort1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pnMainMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnThongTinTK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnQlyKhach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnQlyVe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(pnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 167, Short.MAX_VALUE)
                    .addComponent(pnThongTinTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 169, Short.MAX_VALUE)
                    .addComponent(pnQlyKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 168, Short.MAX_VALUE)
                    .addComponent(pnQlyVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCancelGDNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelGDNhanVienActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jBtnCancelGDNhanVienActionPerformed

    private void btnXemThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemThongTinActionPerformed
        // TODO add your handling code here:
        selectPanel(pnThongTinTK);
        loadThongTinNV();
    }//GEN-LAST:event_btnXemThongTinActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnTypeSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeSortActionPerformed
        String namePicture=btnTypeSort.getIcon().toString().substring(btnTypeSort.getIcon().toString().lastIndexOf("/")+1);
        if(namePicture.equals("Arrows-Up-icon.png")){
            btnTypeSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Down-icon.png")));
        }else {
            btnTypeSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Up-icon.png")));
        }
    }//GEN-LAST:event_btnTypeSortActionPerformed

    private void btnQlyKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQlyKhachActionPerformed
        // TODO add your handling code here:
        selectPanel(pnQlyKhach);
        loadKhachVaoBang(tbKhachHang,"HANH_KHACH");
    }//GEN-LAST:event_btnQlyKhachActionPerformed

    private void btnBackToMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackToMainMenuActionPerformed
        // TODO add your handling code here:
        selectPanel(pnMainMenu);
    }//GEN-LAST:event_btnBackToMainMenuActionPerformed

    private void tbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseClicked
        // TODO add your handling code here:
        String dataSelect=(String) tbKhachHang.getValueAt(tbKhachHang.getSelectedRow(), 0);
        txtSearch.setText(dataSelect);
    }//GEN-LAST:event_tbKhachHangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(lbMatKhau.getText().equals("*******")){
            lbMatKhau.setText(BanVeXe.pass);
        }else lbMatKhau.setText("*******");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void tbKhachHang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHang1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbKhachHang1MouseClicked

    private void btnTypeSort1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeSort1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTypeSort1ActionPerformed

    private void btnQlyVeXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQlyVeXeActionPerformed
        // TODO add your handling code here:
        selectPanel(pnQlyVe);
    }//GEN-LAST:event_btnQlyVeXeActionPerformed

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
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnCancel3;
    private javax.swing.JButton btnQlyKhach;
    private javax.swing.JButton btnQlyVeXe;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnTypeSort;
    private javax.swing.JButton btnTypeSort1;
    private javax.swing.JButton btnXemThongTin;
    private javax.swing.JComboBox<String> cbbSort;
    private javax.swing.JComboBox<String> cbbSort1;
    private javax.swing.JButton jBtnCancelGDNhanVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCMT;
    private javax.swing.JLabel lbChucVu;
    private javax.swing.JLabel lbGioiTinh;
    private javax.swing.JLabel lbHoTen;
    private javax.swing.JLabel lbLoaiTK;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMatKhau;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbTaiKhoan;
    private javax.swing.JPanel pnInfor;
    private javax.swing.JPanel pnMainMenu;
    private javax.swing.JPanel pnQlyKhach;
    private javax.swing.JPanel pnQlyVe;
    private javax.swing.JPanel pnThongTinTK;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTable tbKhachHang1;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    // End of variables declaration//GEN-END:variables
}
