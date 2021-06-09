/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.NhanVien;

import Form.DangKyKhach;
import Form.Khach.PnTTKhach;
import Code.HamXuLyBang;
import Code.XuLyNhap;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author n18dc
 */
public class PnQlyKhach extends javax.swing.JPanel {

    /**
     * Creates new form PnQlyKhach
     */
    HamXuLyBang xLBang=new HamXuLyBang();
    public PnQlyKhach() {
        initComponents();
        xLBang.loadDuLieuVaoBang(tbKhachHang,"SELECT * FROM HANH_KHACH");
    }
    
    public JTable getTable(){
        return tbKhachHang;
    }
    private void setEditableTxt(boolean bool, Color col){
        txtSDT.setEditable(bool);
        jLabel_Sdt.setForeground(Color.RED);
        txtHoTen.setEditable(bool);
        jLabel_HoTen.setForeground(col);
        jRadioButton_Nam.setEnabled(bool);
        jRadioButton_Nu.setEnabled(bool);
        jLabel1_GioiTinh.setForeground(col);
        txtTK.setEditable(bool);
        jLabel_TK.setForeground(col);
        jLabel1_MK.setForeground(col);
        
    }
    private void setEnableBtn(boolean them, boolean sua, boolean xoa, boolean huy){
        btnThem.setEnabled(them);
        btnSua.setEnabled(sua);
        btnXoa.setEnabled(xoa);
        btnHuy.setEnabled(huy);
        if(huy==true){// nếu đang trong trạng thái thêm/xóa/sửa/thêm TK thì ko cho tìm kiếm
            txtTimKiem.setEditable(false);
            btnClearText.setEnabled(false);
            tbKhachHang.setEnabled(false);
            cbbSortKhach.setEnabled(false);
            btnTypeSortKhach.setEnabled(false);
        }
        else{
            txtTimKiem.setEditable(true);
            btnClearText.setEnabled(true);
            tbKhachHang.setEnabled(true);
            cbbSortKhach.setEnabled(true);
            btnTypeSortKhach.setEnabled(true);
        }
        if(them || sua || xoa){// nếu đang trong thêm/xóa/sửa thì tắt button thêm/xóa tài khoản
            btnThemTKKhach.setVisible(false);
        }
    }
    
    private void setField(String sdt, String ten, String tk, boolean namIsSelect, boolean nuIsSelect, boolean passEnable){
        txtSDT.setText(sdt);
        txtHoTen.setText(ten);
        txtTK.setText(tk);
        jRadioButton_Nam.setSelected(namIsSelect);
        jRadioButton_Nu.setSelected(nuIsSelect);
        psMK.setVisible(passEnable);
        jLabel1_MK.setVisible(passEnable);
        
    }
   
    private void themTaiKhoanKhach(String tk, String mk, String sdt){
        java.sql.Connection conn = Code.KetNoi.layKetNoi();
        // truy vấn 1: kt tài khoản mới thêm vào có trùng ko
        String sql="SELECT * FROM TAI_KHOAN WHERE TaiKhoan = '"+tk+"'";
        //truy vấn 2: thêm 1 tài khoản 
        String sql2= "INSERT INTO TAI_KHOAN values (?,?,?) ";
        //truy vấn 3: thêm tài khoản vào khách hàng
        String sql3="UPDATE HANH_KHACH SET TaiKhoan=? WHERE DienThoai='"+sdt+"'";
        try {
            //thực hiện câu truy vấn 1
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại");
                return;
            }
            //thực hiện câu truy vấn 2
            ps = conn.prepareStatement(sql2);
            ps.setString(1, tk);
            ps.setString(2, mk);
            ps.setString(3, "2");
            ps.executeUpdate();
             
            //thực hiện câu truy vấn 3
            ps = conn.prepareStatement(sql3);
            ps.setString(1, tk);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công");
            btnThemTKKhach.setVisible(false);
            
            rs.close();
            ps.close();
            conn.close();
          } catch (SQLException e) {
          Logger.getLogger(PnQlyKhach.class.getName()).log(Level.SEVERE, null, e);
         }
    }
    private void xoaKhach(String sdt){
        Connection con =Code.KetNoi.layKetNoi();
        String sql="DELETE FROM HANH_KHACH WHERE DienThoai=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, sdt);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyKhach.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void xoaTK(String tk){
        Connection con =Code.KetNoi.layKetNoi();
        String sql="DELETE FROM TAI_KHOAN WHERE TaiKhoan=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, tk);
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyKhach.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel33 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel_Sdt = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhachHang = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbbSortKhach = new javax.swing.JComboBox<>();
        btnTypeSortKhach = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        txtHoTen = new javax.swing.JTextField();
        jLabel_HoTen = new javax.swing.JLabel();
        jLabel1_GioiTinh = new javax.swing.JLabel();
        jLabel_TK = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        jRadioButton_Nam = new javax.swing.JRadioButton();
        jRadioButton_Nu = new javax.swing.JRadioButton();
        jLabel1_MK = new javax.swing.JLabel();
        lbLoiSDT = new javax.swing.JLabel();
        lbLoiTrungTK = new javax.swing.JLabel();
        lbLoiGioiTinh = new javax.swing.JLabel();
        lbLoiHoTen = new javax.swing.JLabel();
        psMK = new javax.swing.JPasswordField();
        lbLoiTrungTK1 = new javax.swing.JLabel();
        jLabel_Sdt1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnThemTKKhach = new javax.swing.JButton();
        btnClearText = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 204, 0));
        jLabel33.setText("( Click chọn KH cần thao tác )");
        jLabel33.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("Quản Lý Khách Hàng");

        jLabel_Sdt.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Sdt.setText("SĐT*:");

        txtSDT.setEditable(false);
        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
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
        tbKhachHang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbKhachHangFocusLost(evt);
            }
        });
        tbKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhachHang);

        btnThem.setBackground(new java.awt.Color(0, 150, 255));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 150, 255));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Sắp xếp theo:");

        cbbSortKhach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbSortKhach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Điện Thoại", "Họ Tên", "Giới Tính", "Tài Khoản" }));
        cbbSortKhach.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSortKhachItemStateChanged(evt);
            }
        });

        btnTypeSortKhach.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnTypeSortKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Up-icon.png"))); // NOI18N
        btnTypeSortKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTypeSortKhachActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 150, 255));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(0, 150, 255));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setEnabled(false);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        txtHoTen.setEditable(false);
        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtHoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoTenKeyReleased(evt);
            }
        });

        jLabel_HoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_HoTen.setText("Họ Tên*:");

        jLabel1_GioiTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1_GioiTinh.setText("Giới tính*:");

        jLabel_TK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_TK.setText("Tài khoản:");

        txtTK.setEditable(false);
        txtTK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTKKeyReleased(evt);
            }
        });

        buttonGroup1.add(jRadioButton_Nam);
        jRadioButton_Nam.setText("Nam");
        jRadioButton_Nam.setEnabled(false);
        jRadioButton_Nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_NamActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_Nu);
        jRadioButton_Nu.setText("Nữ");
        jRadioButton_Nu.setEnabled(false);
        jRadioButton_Nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_NuActionPerformed(evt);
            }
        });

        jLabel1_MK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1_MK.setForeground(Color.BLUE);
        jLabel1_MK.setText("Mật khẩu:");
        jLabel1_MK.setVisible(false);

        lbLoiSDT.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiSDT.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiSDT.setText("SĐT ko hợp lệ, hoặc đã tồn tại");
        lbLoiSDT.setVisible(false);

        lbLoiTrungTK.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiTrungTK.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiTrungTK.setText("Tài khoản ko hợp lệ hoặc đã tồn tại");
        lbLoiTrungTK.setVisible(false);

        lbLoiGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiGioiTinh.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiGioiTinh.setText("Hãy Chọn Giới tính");
        lbLoiGioiTinh.setVisible(false);

        lbLoiHoTen.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiHoTen.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiHoTen.setText("Họ tên không được để trống");
        lbLoiHoTen.setVisible(false);

        psMK.setVisible(false);

        lbLoiTrungTK1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        lbLoiTrungTK1.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiTrungTK1.setText("Mật khẩu không đc để trống");
        lbLoiTrungTK1.setVisible(false);

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

        btnThemTKKhach.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnThemTKKhach.setText("Thêm tài khoản");
        btnThemTKKhach.setVisible(false);
        btnThemTKKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTKKhachActionPerformed(evt);
            }
        });

        btnClearText.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnClearText.setText("X");
        btnClearText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Sdt1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbSortKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTypeSortKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearText)
                        .addContainerGap(464, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(439, 439, 439)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(lbLoiSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel_Sdt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel_TK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbLoiTrungTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTK, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel_HoTen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1_MK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(psMK, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbLoiTrungTK1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbLoiHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1_GioiTinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton_Nam)
                                .addGap(15, 15, 15)
                                .addComponent(jRadioButton_Nu))
                            .addComponent(lbLoiGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btnThemTKKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_Sdt1)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnClearText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbbSortKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnTypeSortKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1_GioiTinh)
                                    .addComponent(jRadioButton_Nam)
                                    .addComponent(jRadioButton_Nu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbLoiGioiTinh))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel_HoTen)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbLoiHoTen)))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1_MK)
                            .addComponent(psMK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLoiTrungTK1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_Sdt)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLoiSDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_TK)
                            .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLoiTrungTK)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemTKKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(53, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        
    }//GEN-LAST:event_txtSDTKeyReleased

    private void tbKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHangMouseClicked
        
        if(tbKhachHang.getSelectionModel().isSelectionEmpty()){return;}
        if(!(tbKhachHang.isEnabled())){return;}
        String gt=xLBang.selectRow(tbKhachHang, 2);
        setField(xLBang.selectRow(tbKhachHang, 0), xLBang.selectRow(tbKhachHang, 1), xLBang.selectRow(tbKhachHang, 3), gt.equals("Nam"), gt.equals("Nữ"), false);
        btnThemTKKhach.setVisible(true);
        if(txtTK.getText().isEmpty()) {
            
            btnThemTKKhach.setText("Thêm tài khoản");
        }
        else {
            btnThemTKKhach.setText("Xóa tài khoản");
        }
    }//GEN-LAST:event_tbKhachHangMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(!tbKhachHang.getSelectionModel().isSelectionEmpty()){// kt đã click chọn dữ liệu chưa 
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần sửa");
            return;
        }
        
        if(btnSua.getText().equals("Sửa")){//kt thời điểm nhấn vào button đang ở trạng thái Sửa chưa
            setEditableTxt(true,Color.blue);//mở khóa tất cả txt cho người dùng sửa
            txtSDT.setEditable(false);
            setEnableBtn(false, true, false, true);
            psMK.setVisible(true);
            jLabel1_MK.setVisible(true);
            
            btnSua.setText("Lưu");// đổi button sang trạng thái Lưu
        }else {// ngược lại là trạng thái lưu
            // Tiến hành kt vs lưu dữ liệu đã được sửa
            String sdt=txtSDT.getText();
            String hoTen=txtHoTen.getText();
            XuLyNhap chuanHoa=new XuLyNhap();
            hoTen=chuanHoa.chuanHoa(hoTen);
            String gt=jRadioButton_Nam.isSelected()?"Nam":"Nữ";
            String tk=txtTK.getText();
            String mk=psMK.getText();

            PnTTKhach ttKhach=new PnTTKhach(sdt, null,null);
          
            // kt trùng tài khoản
            if(ttKhach.ktTrungTaiKhoan(tk)){return;}
            
            //Xác nhận thêm
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận Sửa TT khách hàng: "+hoTen, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                String tkTruoc=xLBang.selectRow(tbKhachHang, 3);
                ttKhach.chinhSuaTK(tkTruoc, txtTK.getText(), mk);
                ttKhach.chinhSuaTT(sdt, hoTen, gt, tk);
                xLBang.loadDuLieuVaoBang(tbKhachHang, "SELECT * FROM HANH_KHACH");
                btnSua.setText("Sửa");
                
                setField("", "", "", false, false, false); 
                setEnableBtn(true, true, true, false);
                setEditableTxt(false, null);
            }
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTypeSortKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTypeSortKhachActionPerformed
        String namePicture=btnTypeSortKhach.getIcon().toString().substring(btnTypeSortKhach.getIcon().toString().lastIndexOf("/")+1);
        if(namePicture.equals("Arrows-Up-icon.png")){
            btnTypeSortKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Down-icon.png")));
            xLBang.sapXepBang(tbKhachHang, cbbSortKhach.getSelectedIndex(), 1);
        }else {
            btnTypeSortKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Arrows-Up-icon.png")));
            xLBang.sapXepBang(tbKhachHang, cbbSortKhach.getSelectedIndex(), 0);
        }
    }//GEN-LAST:event_btnTypeSortKhachActionPerformed

    private void tbKhachHangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbKhachHangFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_tbKhachHangFocusLost

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(btnThem.getText().equals("Thêm")){
            setEditableTxt(true,Color.GREEN);//mở khóa các txt cho phép nhập
            setEnableBtn(true, false, false, true);//mở khóa 2 button "Thêm" và "Hủy"
            setField("", "", "", false, false, true); //xóa dữ liệu trên các txt để thêm mới 
            psMK.setVisible(true);//hiển thị txt mật khẩu để nhập
            jLabel1_MK.setVisible(true);
            btnThem.setText("Lưu");// đổi Text sang "Lưu" cho lần nhấn thứ hai
        }else {
            String sdt=txtSDT.getText();
            String hoTen=txtHoTen.getText();
            XuLyNhap chuanHoa=new XuLyNhap();
            hoTen=chuanHoa.chuanHoa(hoTen);
            String gt=jRadioButton_Nam.isSelected()?"Nam":"Nữ";
            String tk=txtTK.getText();
            String mk=psMK.getText();

            DangKyKhach themKhach=new DangKyKhach();
            if(!themKhach.ktLoi(txtHoTen, txtSDT, txtTK, psMK, jRadioButton_Nam, jRadioButton_Nu, lbLoiHoTen, lbLoiGioiTinh, lbLoiSDT, lbLoiTrungTK, lbLoiTrungTK1))
            {return;}
            
            //kt trùng số điện thoại
            if(themKhach.ktTaiKhoan("SELECT * FROM HANH_KHACH WHERE DienThoai = '",sdt))
            {
                jLabel_Sdt.setForeground(Color.red);
                JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại. Vui lòng nhập lại!");
                return;}else {jLabel_Sdt.setForeground(Color.black);}
            // kt trùng tài khoản
            if(themKhach.ktTaiKhoan("SELECT * FROM TAI_KHOAN WHERE TaiKhoan = '",tk)){
                jLabel_TK.setForeground(Color.red);
                JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại. Vui lòng nhập lại!");
                return;}else {jLabel_TK.setForeground(Color.black);}
            
            //Xác nhận thêm
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận thêm khách hàng: "+hoTen, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                themKhach.themTaiKhoan(tk, mk, "2");// thêm tài khoản
                themKhach.themKhachHang(sdt, hoTen, gt, tk);// thêm thông tin khách
                xLBang.loadDuLieuVaoBang(tbKhachHang, "SELECT * FROM HANH_KHACH");
                setField("", "", "", false, false, false); 
                btnThem.setText("Thêm");
                setEnableBtn(true, true, true, false);
                setEditableTxt(false, null);
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if(!tbKhachHang.getSelectionModel().isSelectionEmpty()){
            if(btnXoa.getText().equals("Xóa")){// kt tra nếu Button đang ở trạng thái Sửa thì
                setEnableBtn(false, false, true, true); // mở khóa các button cần phục vụ cho chức năng
                setEditableTxt(false, Color.red);
                
                btnXoa.setText("Lưu");// đổi text của button từ Xóa-> Lưu
            
            }else {//kt tra nếu Button đang ở trạng thái LƯU thì
                String sdt=txtSDT.getText();// lưu số xe để xóa
                 // xác nhận lại

                // Xóa xe
                xoaKhach(sdt);

                JOptionPane.showMessageDialog(this, "Đã Xóa thông tin khách thành công");
                xLBang.loadDuLieuVaoBang(tbKhachHang, "SELECT * FROM HANH_KHACH");// cập nhập lại bảng

                //cập nhập lại các cbb và button
                setField("", "", "", false, false, false); 
                btnXoa.setText("Xóa");
                setEnableBtn(true, true, true, false);
                setEditableTxt(false, null);
                btnThemTKKhach.setVisible(false);
                
            }
       
        }else JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần xóa trong bảng");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        btnThem.setText("Thêm");
        btnSua.setText("Sửa");
        btnXoa.setText("Xóa");
//        btnThemTKKhach.setText("Thêm tài khoản");
//        btnThemTKKhach.setVisible(false);
        tbKhachHang.clearSelection();
        setField("", "", "", false, false, false); 
        setEditableTxt(false, null);
        setEnableBtn(true, true, true, false);
        
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtHoTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoTenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenKeyReleased

    private void txtTKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTKKeyReleased

    private void jRadioButton_NamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_NamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_NamActionPerformed

    private void jRadioButton_NuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_NuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_NuActionPerformed

    private void btnThemTKKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTKKhachActionPerformed
        // TODO add your handling code here:
        if(btnThemTKKhach.getText().equals("Thêm tài khoản")){
            //đổi Text Thành lưu
            btnThemTKKhach.setText("Lưu");
            
            //mở khóa các TextField tài khoản, mật khẩu để thêm  
            txtTK.setEditable(true);
            jLabel_TK.setForeground(Color.GREEN);
            psMK.setVisible(true);
            jLabel1_MK.setVisible(true);
            jLabel1_MK.setForeground(Color.GREEN);
            setEnableBtn(false, false, false, true);
        }
        
        else if(btnThemTKKhach.getText().equals("Lưu"))
        {
            //Kiểm tra tài khoản vừa nhập có trùng không
            if(txtTK.getText().isEmpty()){
                lbLoiTrungTK.setVisible(true);
                return;
            }else{lbLoiTrungTK.setVisible(false);}
            
            //kiểm tra mk có rỗng không
            if(psMK.getText().isEmpty()){
                lbLoiTrungTK1.setVisible(true);
                return;
            }else lbLoiTrungTK1.setVisible(false);
            
            //Thêm tài khoản
            themTaiKhoanKhach(txtTK.getText(), psMK.getText(), txtSDT.getText());
            
            //load lại bảng, set các button,txt về trạng thái ban đầu
            xLBang.loadDuLieuVaoBang(tbKhachHang, "SELECT * FROM HANH_KHACH");
            txtTK.setEditable(false);
            jLabel_TK.setForeground(null);
            psMK.setVisible(false);
            jLabel1_MK.setVisible(false);
            jLabel1_MK.setForeground(null);
            btnThemTKKhach.setText("Xóa tài khoản");
            setEnableBtn(true, true, true, false);
        }
        else {
            String tk=txtTK.getText();
            int chon=JOptionPane.showConfirmDialog(this, "Xác nhận xóa tài khoản: "+tk, "Thông Báo",0);
            if(chon==JOptionPane.OK_OPTION){
                xoaTK(tk);
                //load lại bảng, set các button,txt về trạng thái ban đầu
                xLBang.loadDuLieuVaoBang(tbKhachHang, "SELECT * FROM HANH_KHACH");
                txtTK.setEditable(false);
                jLabel_TK.setForeground(null);
                psMK.setVisible(false);
                jLabel1_MK.setVisible(false);
                jLabel1_MK.setForeground(null);
                btnThemTKKhach.setText("Thêm tài khoản");
                setEnableBtn(true, true, true, false);
            }
        }
        
    }//GEN-LAST:event_btnThemTKKhachActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        setField("", "", "", false, false, false);
        btnThemTKKhach.setEnabled(false);
        tbKhachHang.clearSelection();
        if(!txtTimKiem.getText().isEmpty()){
            xLBang.locTatCa(tbKhachHang,txtTimKiem.getText(),-1);
        }
        else xLBang.locTatCa(tbKhachHang,"",-1);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
        xLBang.loadDuLieuVaoBang(tbKhachHang,"SELECT * FROM HANH_KHACH");
        xLBang.locTatCa(tbKhachHang,"",-1);
    }//GEN-LAST:event_formHierarchyChanged

    private void btnClearTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTextActionPerformed
        // TODO add your handling code here:
        setField("", "", "", false, false, false);
        btnThemTKKhach.setEnabled(false);
        tbKhachHang.clearSelection();
    }//GEN-LAST:event_btnClearTextActionPerformed

    private void cbbSortKhachItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSortKhachItemStateChanged
        // TODO add your handling code here:
        String namePicture=btnTypeSortKhach.getIcon().toString().substring(btnTypeSortKhach.getIcon().toString().lastIndexOf("/")+1);
        if(namePicture.equals("Arrows-Up-icon.png")){    
            xLBang.sapXepBang(tbKhachHang, cbbSortKhach.getSelectedIndex(), 0);
        }else {
            xLBang.sapXepBang(tbKhachHang, cbbSortKhach.getSelectedIndex(), 1);
        }
    }//GEN-LAST:event_cbbSortKhachItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearText;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemTKKhach;
    private javax.swing.JButton btnTypeSortKhach;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbSortKhach;
    private javax.swing.JLabel jLabel1_GioiTinh;
    private javax.swing.JLabel jLabel1_MK;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_HoTen;
    private javax.swing.JLabel jLabel_Sdt;
    private javax.swing.JLabel jLabel_Sdt1;
    private javax.swing.JLabel jLabel_TK;
    private javax.swing.JRadioButton jRadioButton_Nam;
    private javax.swing.JRadioButton jRadioButton_Nu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbLoiGioiTinh;
    private javax.swing.JLabel lbLoiHoTen;
    private javax.swing.JLabel lbLoiSDT;
    private javax.swing.JLabel lbLoiTrungTK;
    private javax.swing.JLabel lbLoiTrungTK1;
    private javax.swing.JPasswordField psMK;
    private javax.swing.JTable tbKhachHang;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
