/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.awt.Font;
import java.awt.List;
import java.awt.font.TextAttribute;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
    
    
    
    //=================================HÀM THAO TÁC DỮ LIỆU VỚI BẢNG==================================//
    
    
    //--------------------Hàm load dữ liệu vào bảng--------------------------
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
            
            // Đổi trạng thái từ số về String cho bảng Vé xe
            if(dtm.equals(tbVeXe.getModel()))
            {
                for(int i=0;i<dtm.getRowCount();i++){
                    if(dtm.getValueAt(i, dtm.getColumnCount()-1).equals("0")){
                        dtm.setValueAt("Đã thanh toán", i, dtm.getColumnCount()-1);
                    }else if(dtm.getValueAt(i, dtm.getColumnCount()-1).equals("1")){
                        dtm.setValueAt("Chưa thanh toán", i, dtm.getColumnCount()-1);
                    }
                    
                }
            }
            tb.setModel(dtm);
            rs.close();
            ps.close();
            ketnoi.close();
        } catch (SQLException e) {
            Logger.getLogger(GDNhanVien.class.getName()).log(Level.SEVERE, null, e);
        }
       // sapXepBang(tb ,0,1);
    }
    //------------------Hàm sắp xếp các dòng của bảng------------------------
    private void sapXepBang(JTable tb ,int xepTheoCot,int kieuXep)// xepTheo: cột cần xếp, kieuXep: 0=tăng hoặc 1=giảm
    {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tb.getModel());
        tb.setRowSorter(sorter);

        ArrayList <RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        if(kieuXep==0){
            sortKeys.add(new RowSorter.SortKey(xepTheoCot, SortOrder.ASCENDING));
        }
        else if(kieuXep==1){
            sortKeys.add(new RowSorter.SortKey(xepTheoCot, SortOrder.DESCENDING));
        }
        sorter.setSortKeys(sortKeys); 
    }  
    
    //-----------------Hàm lọc dữ liệu trong bảng----------------------
    private void locDuLieuJTable(JTable tb, String mucLoc){

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tb.getModel())); 
        sorter.setRowFilter(RowFilter.regexFilter(mucLoc));
        tb.setRowSorter(sorter);
    }
    //=================================**********************===========================================

    
    //=================================HÀM LẤY THÔNG TIN NHÂN VIÊN====================
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
            rs.close();
            ps.close();
            connect.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
     
    
    
    //=======================================QUẢN LÝ VÉ XE=========================================================
    private void loadComboNgayDi(){
        cbbNgayDi.removeAllItems();
        Connection connect=KetNoiSQL.KetNoi.layKetNoi();
        // sql load ngày đi theo trạm
        String sqlNgay="SELECT DISTINCT NgayDi FROM VE_XE VX, CHUYEN_XE CX "
           + "WHERE VX.MaChuyenXe=CX.MaChuyenXe AND TramXuatPhat=N'"+cbbTramDi.getSelectedItem().toString()
                +"' and DATEDIFF(DAY,GETDATE(),CONVERT(DATETIME,NgayDi,103))>=0"; 

        try {
            PreparedStatement ps=connect.prepareStatement(sqlNgay);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                cbbNgayDi.addItem(rs.getString(1));
            }
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(GDNhanVien.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void loadComboGio(){
        cbbGioDi.removeAllItems();
        Connection connect=KetNoiSQL.KetNoi.layKetNoi();
        String ngay=cbbNgayDi.getSelectedIndex()!=-1?cbbNgayDi.getSelectedItem().toString():"";
        String sqlGio="SELECT DISTINCT CONVERT(varchar, CX.GioDi,120) FROM VE_XE VX, CHUYEN_XE CX "
                + "WHERE VX.MaChuyenXe=CX.MaChuyenXe AND TramXuatPhat=N'"+cbbTramDi.getSelectedItem().toString()
                +"' and NgayDi='"+ngay+"'";
        

        try {
            PreparedStatement ps=connect.prepareStatement(sqlGio);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                cbbGioDi.addItem(rs.getString(1));
                
            }
            rs.close();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(GDNhanVien.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    //--------------Duyệt Vé-------------------------
    private void duyetVe(String maVe){
        String trangThai=(String) tbVeXe.getValueAt(tbVeXe.getSelectedRow(), tbVeXe.getColumnCount()-1);
//        if(trangThai.equals("Chưa thanh toán")){
            Connection connect=KetNoiSQL.KetNoi.layKetNoi();
            String sql="UPDATE VE_XE SET TrangThai=0, MaNV='"+BanVeXe.primaryKey+"' WHERE MaVe=?";
            try {
                PreparedStatement ps=connect.prepareStatement(sql);
                ps.setString(1, maVe);
                ps.executeUpdate();
                int choose = JOptionPane.showConfirmDialog(this, "Xác nhận duyệt vé: "+maVe, "Duyệt vé", 0);
                if(choose==JOptionPane.OK_OPTION){
                    String sql2="{call SP_LOAD_VE_TO_JTABLE()}";
                    loadDuLieuVaoBang(tbVeXe,sql2);
                    JOptionPane.showMessageDialog(this, "Duyệt vé: "+maVe+" thành công");
                }
                ps.close();
                connect.close();
            } catch (Exception e) {
                System.out.println(e);
            }
//        }else {
//            JOptionPane.showMessageDialog(this, "Không thể duyệt. Vé đã thanh toán");
//        }
        btnDuyetVe.setEnabled(false);
    }
    
    //----------------Hủy Vé------------------------
    private void huyVe(String maVe){
        String trangThai=(String) tbVeXe.getValueAt(tbVeXe.getSelectedRow(),tbVeXe.getColumnCount()-1);
        if(trangThai.equals("Chưa thanh toán")){
            Connection connect=KetNoiSQL.KetNoi.layKetNoi();
            String sql="DELETE FROM VE_XE WHERE MaVe=?";
            try {
                PreparedStatement ps=connect.prepareStatement(sql);
                ps.setString(1, maVe);
                ps.executeUpdate();
                int choose = JOptionPane.showConfirmDialog(this, "Xác nhận hủy vé: "+maVe, "Hủy vé", 0);
                if(choose==JOptionPane.OK_OPTION){
                    String sql2="{call SP_LOAD_VE_TO_JTABLE()}";
                    loadDuLieuVaoBang(tbVeXe,sql2);
                    JOptionPane.showMessageDialog(this, "Hủy vé: "+maVe+" thành công");
                }
                ps.close();
                connect.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Không thể Hủy. Vé đã thanh toán");
        }
        btnHuyVe.setEnabled(false);
    }
    
    //=======================================QUẢN LÝ KHÁCH HÀNG=========================================================

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupGT = new javax.swing.ButtonGroup();
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
        jLabel13 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbbTramDi = new javax.swing.JComboBox<>();
        cbbNgayDi = new javax.swing.JComboBox<>();
        cbbGioDi = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        pnDatVe = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtTK_Khach = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtHoTen_Khach = new javax.swing.JTextField();
        rBtnNu1 = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        rBtnNam1 = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtMK_Khach = new javax.swing.JPasswordField();
        pnQlyKhach = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSearchKhach = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhachHang = new javax.swing.JTable();
        btnThemKhach = new javax.swing.JButton();
        btnSuaKhach = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbbSortKhach = new javax.swing.JComboBox<>();
        btnTypeSortKhach = new javax.swing.JButton();
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
        setLocation(new java.awt.Point(500, 40));

        tbPnMenu.setBackground(new java.awt.Color(102, 102, 255));
        tbPnMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbPnMenu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbPnMenu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbPnMenuStateChanged(evt);
            }
        });

        pnQlyVe.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel10.setText("Quản Lý Vé Xe");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel11.setText("Tìm kiếm:");

        txtSearchVe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSearchVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchVeActionPerformed(evt);
            }
        });
        txtSearchVe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchVeKeyPressed(evt);
            }
        });

        btnSearchVe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSearchVe.setText("X");
        btnSearchVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchVeActionPerformed(evt);
            }
        });

        tbVeXe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbVeXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ghế ngồi", "Mã vé", "Điên thoại ", "Họ tên", "Giá vé", "Ngày Đi", "Giờ", "Trạm", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbVeXe.setRowHeight(20);
        tbVeXe.setRowMargin(3);
        tbVeXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVeXeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbVeXe);
        if (tbVeXe.getColumnModel().getColumnCount() > 0) {
            tbVeXe.getColumnModel().getColumn(0).setMinWidth(90);
            tbVeXe.getColumnModel().getColumn(0).setMaxWidth(90);
            tbVeXe.getColumnModel().getColumn(3).setMinWidth(150);
            tbVeXe.getColumnModel().getColumn(3).setMaxWidth(150);
            tbVeXe.getColumnModel().getColumn(4).setMinWidth(100);
            tbVeXe.getColumnModel().getColumn(4).setMaxWidth(100);
            tbVeXe.getColumnModel().getColumn(5).setMinWidth(120);
            tbVeXe.getColumnModel().getColumn(5).setMaxWidth(120);
        }

        btnDuyetVe.setBackground(new java.awt.Color(0, 150, 255));
        btnDuyetVe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDuyetVe.setText("Duyệt vé");
        btnDuyetVe.setEnabled(false);
        btnDuyetVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuyetVeActionPerformed(evt);
            }
        });

        btnHuyVe.setBackground(new java.awt.Color(0, 150, 255));
        btnHuyVe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuyVe.setText("Hủy vé");
        btnHuyVe.setEnabled(false);
        btnHuyVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyVeActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel13.setText("Trạng thái vé:");

        cbbTrangThai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa thanh toán", "Đã Thanh Toán" }));
        cbbTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTrangThaiItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 0));
        jLabel9.setText("( Click chọn vé cần thao tác )");

        cbbTramDi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTramDi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TP.HCM", "Đồng Nai" }));
        cbbTramDi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTramDiItemStateChanged(evt);
            }
        });

        cbbNgayDi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNgayDi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNgayDiItemStateChanged(evt);
            }
        });

        cbbGioDi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbGioDi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbGioDiItemStateChanged(evt);
            }
        });

        jLabel12.setText("Trạm khởi hành:");

        jLabel31.setText("Ngày:");

        jLabel32.setText("Giờ:");

        javax.swing.GroupLayout pnQlyVeLayout = new javax.swing.GroupLayout(pnQlyVe);
        pnQlyVe.setLayout(pnQlyVeLayout);
        pnQlyVeLayout.setHorizontalGroup(
            pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnQlyVeLayout.createSequentialGroup()
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnQlyVeLayout.createSequentialGroup()
                                .addComponent(txtSearchVe, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearchVe)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbTramDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbNgayDi, 0, 149, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbGioDi, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnQlyVeLayout.createSequentialGroup()
                                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnQlyVeLayout.createSequentialGroup()
                                .addGap(438, 438, 438)
                                .addComponent(jLabel10))
                            .addGroup(pnQlyVeLayout.createSequentialGroup()
                                .addGap(358, 358, 358)
                                .addComponent(btnDuyetVe, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(137, 137, 137)
                                .addComponent(btnHuyVe, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnQlyVeLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnQlyVeLayout.setVerticalGroup(
            pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQlyVeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(26, 26, 26)
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQlyVeLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtSearchVe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchVe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTramDi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbNgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbGioDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel31)
                        .addComponent(jLabel32)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(pnQlyVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDuyetVe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyVe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        tbPnMenu.addTab("QLÝ VÉ XE", new javax.swing.ImageIcon(getClass().getResource("/image/database.png")), pnQlyVe); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel25.setText("SĐT:");

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSDTFocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setText("Đặt vé");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel26.setText("Tài Khoản:");

        txtTK_Khach.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel27.setText("Họ Tên:");

        txtHoTen_Khach.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnGroupGT.add(rBtnNu1);
        rBtnNu1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rBtnNu1.setSelected(true);
        rBtnNu1.setText("Nữ");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel28.setText("Giới Tính");

        btnGroupGT.add(rBtnNam1);
        rBtnNam1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rBtnNam1.setText("Nam");
        rBtnNam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnNam1ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel30.setText("Mật khẩu:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Thông tin hành khách:");

        jButton1.setBackground(new java.awt.Color(0, 150, 255));
        jButton1.setText("Đặt Vé");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtMK_Khach.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        javax.swing.GroupLayout pnDatVeLayout = new javax.swing.GroupLayout(pnDatVe);
        pnDatVe.setLayout(pnDatVeLayout);
        pnDatVeLayout.setHorizontalGroup(
            pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel29))
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel28))
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(rBtnNam1)
                .addGap(9, 9, 9)
                .addComponent(rBtnNu1))
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addGap(445, 445, 445)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addGap(492, 492, 492)
                .addComponent(jLabel2))
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(6, 6, 6)
                        .addComponent(txtHoTen_Khach, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(199, 199, 199)
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(5, 5, 5)
                        .addComponent(txtMK_Khach, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(5, 5, 5)
                        .addComponent(txtTK_Khach, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnDatVeLayout.setVerticalGroup(
            pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(jLabel29)
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel26))
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(txtTK_Khach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51)
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(txtHoTen_Khach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel30))
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtMK_Khach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(jLabel28)
                .addGap(10, 10, 10)
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rBtnNam1)
                    .addComponent(rBtnNu1))
                .addGap(65, 65, 65)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbPnMenu.addTab("ĐẶT VÉ", new javax.swing.ImageIcon(getClass().getResource("/image/payment-icon.png")), pnDatVe); // NOI18N

        pnQlyKhach.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 204, 0));
        jLabel33.setText("( Click chọn KH cần thao tác )");
        jLabel33.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("Quản Lý Khách Hàng");

        jLabel7.setText("Chọn:");

        txtSearchKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKhachKeyPressed(evt);
            }
        });

        tbKhachHang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SĐT", "Họ Tên", "Giới Tính", "Tài Khoản"
            }
        ));
        tbKhachHang.setRowHeight(20);
        tbKhachHang.setRowMargin(3);
        tbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhachHang);

        btnThemKhach.setBackground(new java.awt.Color(0, 150, 255));
        btnThemKhach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemKhach.setText("Thêm");

        btnSuaKhach.setBackground(new java.awt.Color(0, 150, 255));
        btnSuaKhach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaKhach.setText("Sửa");
        btnSuaKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKhachActionPerformed(evt);
            }
        });

        jLabel8.setText("Sắp xếp theo:");

        cbbSortKhach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Điện Thoại", "Họ Tên", " " }));

        btnTypeSortKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Up-icon.png"))); // NOI18N
        btnTypeSortKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeSortKhachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnQlyKhachLayout = new javax.swing.GroupLayout(pnQlyKhach);
        pnQlyKhach.setLayout(pnQlyKhachLayout);
        pnQlyKhachLayout.setHorizontalGroup(
            pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnThemKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbSortKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnTypeSortKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addComponent(btnSuaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                        .addGap(431, 431, 431)
                        .addComponent(jLabel6))
                    .addGroup(pnQlyKhachLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(489, Short.MAX_VALUE))
        );
        pnQlyKhachLayout.setVerticalGroup(
            pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQlyKhachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSearchKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(cbbSortKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTypeSortKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(pnQlyKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tbPnMenu.addTab("QLÝ KHÁCH", new javax.swing.ImageIcon(getClass().getResource("/image/edit-user-icon.png")), pnQlyKhach); // NOI18N

        pnThongTinTK.setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("(Nhân viên không có quyền hiệu chỉnh)");

        lbMaNV.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N

        lbHoTen.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N

        lbCMT.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N

        lbSDT.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N

        lbChucVu.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N

        lbTaiKhoan.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N

        lbMatKhau.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbMatKhau.setText("*******");

        btnSeePass.setForeground(new java.awt.Color(255, 255, 255));
        btnSeePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Eye-2-icon.png"))); // NOI18N
        btnSeePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeePassActionPerformed(evt);
            }
        });

        lbGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N

        javax.swing.GroupLayout pnThongTinTKLayout = new javax.swing.GroupLayout(pnThongTinTK);
        pnThongTinTK.setLayout(pnThongTinTKLayout);
        pnThongTinTKLayout.setHorizontalGroup(
            pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinTKLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(166, 166, 166))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThongTinTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel23)
                        .addGap(14, 14, 14)
                        .addComponent(lbMaNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 711, Short.MAX_VALUE)
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
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                            .addComponent(lbSDT))
                        .addGap(411, 411, 411)
                        .addComponent(jLabel16))
                    .addGroup(pnThongTinTKLayout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(pnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnThongTinTKLayout.createSequentialGroup()
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGap(125, 125, 125)
                .addComponent(jLabel5)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        tbPnMenu.addTab("TT CÁ NHÂN", new javax.swing.ImageIcon(getClass().getResource("/image/edit-configure.png")), pnThongTinTK); // NOI18N

        pnInfor.setBackground(new java.awt.Color(102, 153, 0));
        pnInfor.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user-icon.png"))); // NOI18N
        jLabel1.setText("Người dùng:");
        pnInfor.add(jLabel1);
        jLabel1.setBounds(50, 30, 133, 30);

        LbUserName.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        pnInfor.add(LbUserName);
        LbUserName.setBounds(190, 30, 180, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/phanquyen.png"))); // NOI18N
        jLabel4.setText("Tài khoản:");
        pnInfor.add(jLabel4);
        jLabel4.setBounds(70, 70, 130, 30);

        lbLoaiTK.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        pnInfor.add(lbLoaiTK);
        lbLoaiTK.setBounds(190, 70, 150, 30);
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
        jBtnCancelGDNhanVien.setBounds(30, 110, 120, 40);

        lbTopBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageBackground/1139x160.png"))); // NOI18N
        pnInfor.add(lbTopBanner);
        lbTopBanner.setBounds(0, 0, 1140, 160);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnInfor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tbPnMenu)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbPnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbPnMenu.getAccessibleContext().setAccessibleName("QLÝ VÉ XE");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCancelGDNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelGDNhanVienActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jBtnCancelGDNhanVienActionPerformed

    private void tbPnMenuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbPnMenuStateChanged
        //BẮT SỰ KIỆN THAY ĐỔI MENU CỦA TABBED
        if(tbPnMenu.getSelectedIndex()==0){
            loadDuLieuVaoBang(tbVeXe,"{call SP_LOAD_VE_TO_JTABLE()}");
            loadComboNgayDi();
            loadComboGio();
            locDuLieuJTable(tbVeXe,cbbTramDi.getSelectedItem().toString());
            locDuLieuJTable(tbVeXe,cbbNgayDi.getSelectedIndex()!=-1?cbbNgayDi.getSelectedItem().toString():"");
            
        }
        else if(tbPnMenu.getSelectedIndex()==1){
            loadDuLieuVaoBang(tbKhachHang, "SELECT * FROM HANH_KHACH");
            //reset lại textField
            txtSDT.setText("");
            txtHoTen_Khach.setText("");
            txtHoTen_Khach.setEditable(true);
            txtTK_Khach.setText("");
            rBtnNam1.setEnabled(true);
            rBtnNu1.setEnabled(true);
            txtMK_Khach.setText("");
            txtMK_Khach.setEditable(true);
                
        }
        else if(tbPnMenu.getSelectedIndex()==2){
            loadDuLieuVaoBang(tbKhachHang,"SELECT * FROM HANH_KHACH");
        }
        else if(tbPnMenu.getSelectedIndex()==3){
            loadThongTinNV();
        }
    }//GEN-LAST:event_tbPnMenuStateChanged

    private void btnSeePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeePassActionPerformed
        // TODO add your handling code here:
        if(lbMatKhau.getText().equals("*******")){
            lbMatKhau.setText(BanVeXe.pass);
        }else lbMatKhau.setText("*******");

    }//GEN-LAST:event_btnSeePassActionPerformed

    private void btnTypeSortKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeSortKhachActionPerformed
        String namePicture=btnTypeSortKhach.getIcon().toString().substring(btnTypeSortKhach.getIcon().toString().lastIndexOf("/")+1);
        if(namePicture.equals("Arrows-Up-icon.png")){
            btnTypeSortKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Down-icon.png")));
        }else {
            btnTypeSortKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Up-icon.png")));
        }
    }//GEN-LAST:event_btnTypeSortKhachActionPerformed

    private void tbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseClicked
        // TODO add your handling code here:
        String dataSelect=(String) tbKhachHang.getValueAt(tbKhachHang.getSelectedRow(), 0);
        txtSearchKhach.setText(dataSelect);
    }//GEN-LAST:event_tbKhachHangMouseClicked

    private void tbVeXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVeXeMouseClicked
        // TODO add your handling code here:
        String tbSelect=(String) tbVeXe.getValueAt(tbVeXe.getSelectedRow(), 1);
        txtSearchVe.setText(tbSelect);
        String trangThai=(String) tbVeXe.getValueAt(tbVeXe.getSelectedRow(),tbVeXe.getColumnCount()-1);
        btnHuyVe.setEnabled(true);
        if(trangThai.equals("Chưa thanh toán")) btnDuyetVe.setEnabled(true);
        else btnDuyetVe.setEnabled(false);
        
    }//GEN-LAST:event_tbVeXeMouseClicked

    private void btnDuyetVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuyetVeActionPerformed
        // TODO add your handling code here:
        
        duyetVe(txtSearchVe.getText());
    }//GEN-LAST:event_btnDuyetVeActionPerformed

    private void btnHuyVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyVeActionPerformed
        // TODO add your handling code here:
        huyVe(txtSearchVe.getText());
    }//GEN-LAST:event_btnHuyVeActionPerformed

    private void rBtnNam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnNam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnNam1ActionPerformed

    private void txtSearchVeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchVeKeyPressed
        // TODO add your handling code here:
        btnDuyetVe.setEnabled(false);
        btnHuyVe.setEnabled(false);
        locDuLieuJTable(tbVeXe,cbbTramDi.getSelectedItem().toString());
        locDuLieuJTable(tbVeXe,cbbNgayDi.getSelectedIndex()!=-1?cbbNgayDi.getSelectedItem().toString():"");
        locDuLieuJTable(tbVeXe,cbbGioDi.getSelectedIndex()!=-1?cbbGioDi.getSelectedItem().toString():"");
        if(!txtSearchVe.getText().isEmpty()){
            locDuLieuJTable(tbVeXe,txtSearchVe.getText());
        }
        
    }//GEN-LAST:event_txtSearchVeKeyPressed

    private void cbbTramDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTramDiItemStateChanged
        // TODO add your handling code here:
        locDuLieuJTable(tbVeXe,cbbTramDi.getSelectedItem().toString());
        loadComboNgayDi();
    }//GEN-LAST:event_cbbTramDiItemStateChanged

    private void cbbNgayDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNgayDiItemStateChanged
        // TODO add your handling code here
        loadComboGio();
        locDuLieuJTable(tbVeXe,cbbNgayDi.getSelectedIndex()!=-1?cbbNgayDi.getSelectedItem().toString():"00/00/0000");
        
    }//GEN-LAST:event_cbbNgayDiItemStateChanged

    private void cbbGioDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbGioDiItemStateChanged

        locDuLieuJTable(tbVeXe,cbbGioDi.getSelectedIndex()!=-1?cbbGioDi.getSelectedItem().toString():"00:00:00");
        
    }//GEN-LAST:event_cbbGioDiItemStateChanged

    private void cbbTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTrangThaiItemStateChanged
        // TODO add your handling code here:
        if(cbbTrangThai.getSelectedIndex()>0){
            locDuLieuJTable(tbVeXe,cbbTrangThai.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cbbTrangThaiItemStateChanged

    private void btnSearchVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchVeActionPerformed
        // TODO add your handling code here:
        btnDuyetVe.setEnabled(false);
        btnHuyVe.setEnabled(false);
        txtSearchVe.setText("");

    }//GEN-LAST:event_btnSearchVeActionPerformed

    private void txtSDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTFocusLost
        // TODO add your handling code here:
        locDuLieuJTable(tbKhachHang, txtSDT.getText());//lấy sđt tìm kiếm trên bảng khách hàng
        
        //----kiểm tra có tìm đc ko. Nếu tìm đc thì load thông tin khách
        if(tbKhachHang.getRowCount()>0 && !txtSDT.getText().isEmpty()){
            
            txtHoTen_Khach.setText(tbKhachHang.getValueAt(0, 1).toString());
            txtHoTen_Khach.setEditable(false);
            
            String gt=tbKhachHang.getValueAt(0, 2).toString();
            if(gt.equalsIgnoreCase("Nam")){ rBtnNam1.setSelected(true);}
            rBtnNam1.setEnabled(false);
            rBtnNu1.setEnabled(false);
            
            txtTK_Khach.setText(tbKhachHang.getValueAt(0, 3).toString());
            txtTK_Khach.setEditable(false);
            
            txtMK_Khach.setText("*********");
            txtMK_Khach.setEditable(false);
        }else {// nếu ko thì reset lại textField
            txtHoTen_Khach.setText("");
            txtHoTen_Khach.setEditable(true);
            txtTK_Khach.setText("");
            rBtnNam1.setEnabled(true);
            rBtnNu1.setEnabled(true);
            txtMK_Khach.setText("");
            txtMK_Khach.setEditable(true);
        }
        locDuLieuJTable(tbKhachHang, "");
    }//GEN-LAST:event_txtSDTFocusLost

    private void txtSearchKhachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKhachKeyPressed
        // TODO add your handling code here:
        locDuLieuJTable(tbKhachHang, txtSearchKhach.getText());
    }//GEN-LAST:event_txtSearchKhachKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new DatVe(txtSDT.getText()).setVisible(true);
        
        //đặt xong reset lại textField
        txtSDT.setText("");
        txtHoTen_Khach.setText("");
        txtHoTen_Khach.setEditable(true);
        txtTK_Khach.setText("");
        rBtnNam1.setEnabled(true);
        rBtnNu1.setEnabled(true);
        txtMK_Khach.setText("");
        txtMK_Khach.setEditable(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSuaKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKhachActionPerformed
        // TODO add your handling code here:
        new ChinhSuaKH(txtSearchKhach.getText()).setVisible(true);
        loadDuLieuVaoBang(tbKhachHang, "SELECT * FROM HANH_KHACH");
    }//GEN-LAST:event_btnSuaKhachActionPerformed

    private void txtSearchVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchVeActionPerformed

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
    private javax.swing.ButtonGroup btnGroupGT;
    private javax.swing.JButton btnHuyVe;
    private javax.swing.JButton btnSearchVe;
    private javax.swing.JButton btnSeePass;
    private javax.swing.JButton btnSuaKhach;
    private javax.swing.JButton btnThemKhach;
    private javax.swing.JButton btnTypeSortKhach;
    private javax.swing.JComboBox<String> cbbGioDi;
    private javax.swing.JComboBox<String> cbbNgayDi;
    private javax.swing.JComboBox<String> cbbSortKhach;
    private javax.swing.JComboBox<String> cbbTramDi;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JButton jBtnCancelGDNhanVien;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
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
    private javax.swing.JPanel pnDatVe;
    private javax.swing.JPanel pnInfor;
    private javax.swing.JPanel pnQlyKhach;
    private javax.swing.JPanel pnQlyVe;
    private javax.swing.JPanel pnThongTinTK;
    private javax.swing.JRadioButton rBtnNam1;
    private javax.swing.JRadioButton rBtnNu1;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTabbedPane tbPnMenu;
    private javax.swing.JTable tbVeXe;
    private javax.swing.JTextField txtHoTen_Khach;
    private javax.swing.JPasswordField txtMK_Khach;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearchKhach;
    private javax.swing.JTextField txtSearchVe;
    private javax.swing.JTextField txtTK_Khach;
    // End of variables declaration//GEN-END:variables
}
