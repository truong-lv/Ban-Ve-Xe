/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Code.KetNoi;
import Code.BanVeXe;
import Code.HamXuLyBang;
import Form.Khach.PnKhachXemVe;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author n18dc
 */
public class PnDatVe extends javax.swing.JPanel {

    HamXuLyBang xLBang = new HamXuLyBang();
    JTable tbKhachHang;

    ArrayList<JTextField> selected = new ArrayList<JTextField>();
    ArrayList<JTextField> chair = new ArrayList<JTextField>();

    public PnDatVe() {
        initComponents();
        AddTime();
        phanQuyenDatVe();
        taiLoaiXe();
        taiNoiXuatPhat();
        addChair();
        taiGheDaDat();
    }

    private void phanQuyenDatVe() {
        if (BanVeXe.quyen.equalsIgnoreCase("Khách hàng"))//nếu là khách đặt thì chỉ cần load thông tin vào txt
        {
            txtSDT.setText(BanVeXe.primaryKey);
            txtHoTen_Khach.setText(BanVeXe.hoTen);
            if (BanVeXe.gioiTinh.equalsIgnoreCase("Nam")) {
                rBtnNam1.setSelected(true);
            } else {
                rBtnNu1.setSelected(true);
            }
        } else {// nếu không phải khách thì mở edit txt cho nv hoặc admin nhập thông tin khách
            txtSDT.setEditable(true);
            txtHoTen_Khach.setEditable(true);
            rBtnNam1.setEnabled(true);
            rBtnNu1.setEnabled(true);
        }
    }

    //chức năng được gọi khi nhân viên, admin đặt vé - khi nhập sđt khách thì sẽ kt từ bảng KH và load khách hàng có sẵn vào các txt
    public void loadBangKhachHang(JTable tb) {
        tbKhachHang = tb;
    }

    private void timKiemKhach() {
        xLBang.locTatCa(tbKhachHang, txtSDT.getText(), -1);//lấy sđt tìm kiếm trên bảng khách hàng

        //----kiểm tra có tìm đc ko. Nếu tìm đc thì load thông tin khách
        if (tbKhachHang.getRowCount() > 0 && txtSDT.getText().equals(tbKhachHang.getValueAt(0, 0).toString())) {

            txtHoTen_Khach.setText(tbKhachHang.getValueAt(0, 1).toString());
            txtHoTen_Khach.setEditable(false);

            String gt = tbKhachHang.getValueAt(0, 2).toString();
            //if(gt.equalsIgnoreCase("Nam")){ rBtnNam1.setSelected(true);}
            if (gt.equalsIgnoreCase("Nam")) {
                rBtnNam1.setSelected(true);
            } else {
                rBtnNu1.setSelected(true);
            }
            rBtnNam1.setEnabled(false);
            rBtnNu1.setEnabled(false);
        } else {// nếu ko thì reset lại textField
            txtHoTen_Khach.setText("");
            txtHoTen_Khach.setEditable(true);
            rBtnNam1.setEnabled(true);
            rBtnNu1.setEnabled(true);
        }
        //xLBang.locTatCa(tbKhachHang, "",-1);
    }

    private boolean ktNhap() {
        boolean kt = true;
        if (!txtSDT.getText().matches("0\\d{9,10}")) {
            lbLoiSDT.setVisible(true);
            kt = false;
        } else {
            lbLoiSDT.setVisible(false);
        }

        if (txtHoTen_Khach.getText().isEmpty()) {
            lbLoiTen.setVisible(true);
            kt = false;
        } else {
            lbLoiTen.setVisible(false);
        }
        if ((!rBtnNam1.isSelected() && !rBtnNu1.isSelected())) {
            lbLoiGioiTinh.setVisible(true);
            kt = false;
        } else {
            lbLoiGioiTinh.setVisible(false);
        }

        return kt;
    }

    public void resetTxt() {
        txtSDT.setText("");
        txtHoTen_Khach.setText("");
        xLBang.locTatCa(tbKhachHang, "", -1);
    }
    //================================================

    //hàm Tính Giá Vé
    public String giaVe(String loaiXe) {
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "SELECT * FROM LOAI_XE";

        String giaVe = "0";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(2).equals(loaiXe)) {
                    giaVe = String.valueOf(150000 * (Double.valueOf(rs.getString(3))));
                }
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(PnDatVe.class.getName()).log(Level.SEVERE, null, e);
        }
        return giaVe;
    }

    //Hàm Lấy Mã Những Chuyến Xe Có Gio Di Và TramXuatPhat cho khách chọn
    public String getMaCX(String time, String start) {
        String maCX = "";
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "SELECT * FROM CHUYEN_XE WHERE TrangThai=1 AND GioDi=? AND TramXuatPhat=?";

        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            //System.out.println(time+ ".0000000");
            ps.setString(1, time);
            ps.setString(2, start);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                maCX = rs.getString(1);
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(PnDatVe.class.getName()).log(Level.SEVERE, null, e);
        }

        return maCX;
    }

    //Hàm setcolor ghế lại màu trắng
    public void setChair() {
        for (int i = 0; i < chair.size(); i++) {
            chair.get(i).setBackground(Color.WHITE);
        }
    }

    //Hàm Tải Nơi Xuất Phát
    public void taiNoiXuatPhat() {
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "select DISTINCT TramXuatPhat from CHUYEN_XE WHERE TrangThai=1";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jComboBox_chuyenDi.addItem(rs.getString(1));
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(PnDatVe.class.getName()).log(Level.SEVERE, null, e);
        }
        jComboBox_chuyenDi.setSelectedIndex(1);
        jComboBox_chuyenDi.removeItemAt(0);
    }

    // Hàm Tải Loại Xe
    public void taiLoaiXe() {
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "select * from LOAI_XE";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jComboBox_loaiXe.addItem(rs.getString(2));
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(PnDatVe.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // hàm load nơi đến
    public void taiNoiDen(String noiXuatPhat) {
        jComboBox_diemDen.setSelectedIndex(0);
        for (int i = 1; i < jComboBox_diemDen.getItemCount(); i++) {
            jComboBox_diemDen.removeItemAt(i);
        }
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "SELECT DISTINCT TramDen FROM CHUYEN_XE WHERE TrangThai=1 AND TramXuatPhat = N'" + noiXuatPhat + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jComboBox_diemDen.addItem(rs.getString(1));
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(PnDatVe.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Hàm load vé đã được đặt
    public void taiGheDaDat() {
        setChair();
        String price = giaVe(jComboBox_loaiXe.getSelectedItem().toString());
        if (price.length() > 1) {
            price = price.substring(0, price.indexOf("."));
        }
        String loaiXe = jComboBox_loaiXe.getSelectedItem().toString();

        String day = jComboBox_Day.getSelectedItem().toString() /*+ jLabel_month.getText()*/;

        String maCX = "";
        if (jComboBox_Time.getSelectedItem() == null) {
            maCX = getMaCX("03:00:00" + ".0000000", jComboBox_chuyenDi.getSelectedItem().toString());
        } else {
            maCX = getMaCX(jComboBox_Time.getSelectedItem().toString(), jComboBox_chuyenDi.getSelectedItem().toString());
        }

        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "select * from VE_XE";
        //"select * from VE_XE where GiaVe=? AND NgayDi=? AND MaChuyenXe=?"
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                for (int i = 0; i < chair.size(); i++) {
                    if (chair.get(i).getText().equals(rs.getString(3)) && price.equals(rs.getString(4)) && day.equals(rs.getString(5)) && maCX.equals(rs.getString(6))) {
                        chair.get(Integer.parseInt(rs.getString(3)) - 2).setBackground(Color.RED);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PnDatVe.class.getName()).log(Level.SEVERE, null, e);
        }

//        PnKhachXemVe loadVe = new PnKhachXemVe();
//        loadVe.loadVe();
    }

    // Hàm load thời gian để khách hàng đặt vé
    public void AddTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");// /MM/uuuu
        LocalDate localDate = LocalDate.now();
        jLabel_month.setText(dtf.format(localDate));

        //Load thẳng ngày/tháng/năm vào cbb. Load từ ngày hiện tại trở đi 7 ngày
        for (int i = 0; i < 7; i++) {
            jComboBox_Day.addItem(dtf.format(localDate.plusDays(i)));
        }
        jComboBox_Day.setSelectedIndex(1);
        jComboBox_Day.removeItemAt(0);
        if (jComboBox_diemDen.getSelectedItem().toString().equals("Điểm Đến")) {
            jComboBox_Time.setEnabled(false);
            jComboBox_Day.setEnabled(false);
        } else {
            jComboBox_Time.setEnabled(true);
            jComboBox_Day.setEnabled(true);
        }
    }

    public boolean Add(JTextField txt, int z) {
        // kiểm tra xem có bấm vào ghế lần 2 ko
        boolean temp = false;
        for (int i = 0; i < selected.size(); i++) {
            if (selected.get(i).getText().equals(txt.getText())) {
                selected.remove(i);
                temp = true;
            }
        }
        if (temp == false && z != 5) {
            selected.add(txt);
        }
        return temp;
    }

    //HÀM THAY ĐỔI MÀU GHẾ KHI NHẤN VÀO BUTTON======
    public void changeColor(JTextField txt) {
        int soLuong = Integer.parseInt(lbSoLuongVe.getText());

        if (jComboBox_loaiXe.getSelectedItem().equals("Chọn Loại Xe")) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Loại Xe");
        } else if (jComboBox_chuyenDi.getSelectedItem().equals("Chọn Nơi Xuất Phát")) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Nơi Xuất Phát");
        } else {
            if (txt.getBackground() != Color.RED) {
                if (soLuong < 5) {
                    if (txt.getBackground() == Color.WHITE) {
                        txt.setBackground(Color.GREEN);
                        soLuong++;
                    } else {
                        txt.setBackground(Color.white);
                        soLuong--;
                    }
                    lbSoLuongVe.setText(String.valueOf(soLuong));
                    Add(txt, soLuong - 1);
                } else if (Integer.parseInt(lbSoLuongVe.getText()) == 5) {
                    if (Add(txt, 5)) {
                        txt.setBackground(Color.WHITE);
                        lbSoLuongVe.setText("4");
                    } else {
                        JOptionPane.showConfirmDialog(null, "Không được đặt quá 5 ghế", "Thông Báo", JOptionPane.DEFAULT_OPTION);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ghế Này Đã Được Đặt");
            }
        }

        // Tính Tổng Tiền
        if (soLuong != 0) {
            Locale localeVN = new Locale("vi", "VN");
            double price = Double.valueOf(giaVe(jComboBox_loaiXe.getSelectedItem().toString())) * Integer.parseInt(lbSoLuongVe.getText());
            jLabel_priceAll.setText(String.valueOf(NumberFormat.getCurrencyInstance(localeVN).format(price)).substring(0, String.valueOf(NumberFormat.getCurrencyInstance(localeVN).format(price)).length() - 1) + " VND");
        }

    }

    //Hàm Add Ghế vào ArrayList để đổi màu ghế đã đặt
    public void addChair() {
        chair.add(txtGhe2);
        chair.add(txtGhe3);
        chair.add(txtGhe4);
        chair.add(txtGhe5);
        chair.add(txtGhe6);
        chair.add(txtGhe7);
        chair.add(txtGhe8);
        chair.add(txtGhe9);
        chair.add(txtGhe10);
        chair.add(txtGhe11);
        chair.add(txtGhe12);
        chair.add(txtGhe13);
        chair.add(txtGhe14);
        chair.add(txtGhe15);
        chair.add(txtGhe16);
        chair.add(txtGhe17);
    }

    // Hàm Đặt Vé Xe
    public void datVe(String maVe, String sdt, String viTriGhe, String giaVe, String ngayDi, String maChuyen, String trangThai, String maNV) {
        Connection connect = Code.KetNoi.layKetNoi();
        String sql = "insert into VE_XE values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, maVe);
            ps.setString(2, sdt);
            ps.setString(3, viTriGhe);
            ps.setString(4, giaVe);
            ps.setString(5, ngayDi);
            ps.setString(6, maChuyen);
            ps.setString(7, trangThai);
            ps.setString(8, maNV);
            ps.executeUpdate();

            ps.close();
            connect.close();
        } catch (SQLException e) {
            Logger.getLogger(PnDatVe.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel_month = new javax.swing.JLabel();
        pnDatVe = new javax.swing.JPanel();
        lblNoiDi = new javax.swing.JLabel();
        lblNoiDen = new javax.swing.JLabel();
        lblGiaVe = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtGhe1 = new javax.swing.JTextField();
        txtGhe2 = new javax.swing.JTextField();
        txtGhe8 = new javax.swing.JTextField();
        txtGhe3 = new javax.swing.JTextField();
        txtGhe4 = new javax.swing.JTextField();
        txtGhe5 = new javax.swing.JTextField();
        txtGhe6 = new javax.swing.JTextField();
        txtGhe10 = new javax.swing.JTextField();
        txtGhe7 = new javax.swing.JTextField();
        txtGhe11 = new javax.swing.JTextField();
        txtGhe9 = new javax.swing.JTextField();
        txtGhe14 = new javax.swing.JTextField();
        txtGhe15 = new javax.swing.JTextField();
        txtGhe16 = new javax.swing.JTextField();
        txtGhe12 = new javax.swing.JTextField();
        txtGhe13 = new javax.swing.JTextField();
        txtGhe17 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbSoLuongVe = new javax.swing.JLabel();
        jComboBox_Day = new javax.swing.JComboBox<>();
        jComboBox_Time = new javax.swing.JComboBox<>();
        lblNoiDi1 = new javax.swing.JLabel();
        jComboBox_loaiXe = new javax.swing.JComboBox<>();
        lblNoiDi2 = new javax.swing.JLabel();
        jComboBox_chuyenDi = new javax.swing.JComboBox<>();
        jLabel_price = new javax.swing.JLabel();
        lblGiaVe1 = new javax.swing.JLabel();
        lblGiaVe2 = new javax.swing.JLabel();
        jLabel_priceAll = new javax.swing.JLabel();
        lblNoiDi3 = new javax.swing.JLabel();
        jComboBox_diemDen = new javax.swing.JComboBox<>();
        btnXacNhan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtHoTen_Khach = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        rBtnNam1 = new javax.swing.JRadioButton();
        rBtnNu1 = new javax.swing.JRadioButton();
        lbLoiGioiTinh = new javax.swing.JLabel();
        lbLoiTen = new javax.swing.JLabel();
        lbLoiSDT = new javax.swing.JLabel();

        jLabel_month.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        pnDatVe.setBackground(new java.awt.Color(255, 255, 255));
        pnDatVe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đặt", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 16))); // NOI18N

        lblNoiDi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNoiDi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNoiDi.setText("Ngày Khởi Hành:");
        lblNoiDi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblNoiDen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNoiDen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNoiDen.setText("Giờ :");
        lblNoiDen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblGiaVe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGiaVe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGiaVe.setText("Giá vé :");
        lblGiaVe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0)));

        txtGhe1.setEditable(false);
        txtGhe1.setBackground(Color.CYAN
        );
        txtGhe1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe1.setText("Tài xế");
        txtGhe1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe1.setFocusable(false);

        txtGhe2.setEditable(false);
        txtGhe2.setBackground(Color.white);
        txtGhe2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe2.setText("2");
        txtGhe2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe2.setFocusable(false);
        txtGhe2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe2MouseClicked(evt);
            }
        });
        txtGhe2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGhe2ActionPerformed(evt);
            }
        });

        txtGhe8.setEditable(false);
        txtGhe8.setBackground(Color.white);
        txtGhe8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe8.setText("8");
        txtGhe8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe8.setFocusable(false);
        txtGhe8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe8MouseClicked(evt);
            }
        });

        txtGhe3.setEditable(false);
        txtGhe3.setBackground(Color.white);
        txtGhe3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe3.setText("3");
        txtGhe3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe3.setFocusable(false);
        txtGhe3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe3MouseClicked(evt);
            }
        });

        txtGhe4.setEditable(false);
        txtGhe4.setBackground(Color.white);
        txtGhe4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe4.setText("4");
        txtGhe4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe4.setFocusable(false);
        txtGhe4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe4MouseClicked(evt);
            }
        });

        txtGhe5.setEditable(false);
        txtGhe5.setBackground(Color.white);
        txtGhe5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe5.setText("5");
        txtGhe5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe5.setFocusable(false);
        txtGhe5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe5MouseClicked1(evt);
            }
        });

        txtGhe6.setEditable(false);
        txtGhe6.setBackground(Color.white);
        txtGhe6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe6.setText("6");
        txtGhe6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe6.setFocusable(false);
        txtGhe6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe6MouseClicked(evt);
            }
        });

        txtGhe10.setEditable(false);
        txtGhe10.setBackground(Color.white);
        txtGhe10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe10.setText("10");
        txtGhe10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe10.setFocusable(false);
        txtGhe10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe10MouseClicked(evt);
            }
        });

        txtGhe7.setEditable(false);
        txtGhe7.setBackground(Color.white);
        txtGhe7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe7.setText("7");
        txtGhe7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe7.setFocusable(false);
        txtGhe7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe7MouseClicked(evt);
            }
        });

        txtGhe11.setEditable(false);
        txtGhe11.setBackground(Color.white);
        txtGhe11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe11.setText("11");
        txtGhe11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe11.setFocusable(false);
        txtGhe11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe11MouseClicked(evt);
            }
        });

        txtGhe9.setEditable(false);
        txtGhe9.setBackground(Color.white);
        txtGhe9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe9.setText("9");
        txtGhe9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe9.setFocusable(false);
        txtGhe9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe9MouseClicked(evt);
            }
        });

        txtGhe14.setEditable(false);
        txtGhe14.setBackground(Color.white);
        txtGhe14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe14.setText("14");
        txtGhe14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe14.setFocusable(false);
        txtGhe14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe14MouseClicked(evt);
            }
        });

        txtGhe15.setEditable(false);
        txtGhe15.setBackground(Color.white);
        txtGhe15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe15.setText("15");
        txtGhe15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe15.setFocusable(false);
        txtGhe15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe15MouseClicked(evt);
            }
        });

        txtGhe16.setEditable(false);
        txtGhe16.setBackground(Color.white);
        txtGhe16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe16.setText("16");
        txtGhe16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe16.setFocusable(false);
        txtGhe16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe16MouseClicked(evt);
            }
        });

        txtGhe12.setEditable(false);
        txtGhe12.setBackground(Color.white);
        txtGhe12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe12.setText("12");
        txtGhe12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe12.setFocusable(false);
        txtGhe12.setBackground(Color.WHITE);
        txtGhe12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe12MouseClicked(evt);
            }
        });

        txtGhe13.setEditable(false);
        txtGhe13.setBackground(Color.white);
        txtGhe13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe13.setText("13");
        txtGhe13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe13.setFocusable(false);
        txtGhe13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe13MouseClicked(evt);
            }
        });

        txtGhe17.setEditable(false);
        txtGhe17.setBackground(Color.white);
        txtGhe17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGhe17.setText("17");
        txtGhe17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtGhe17.setFocusable(false);
        txtGhe17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhe17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtGhe6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGhe5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGhe4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGhe3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGhe2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtGhe1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGhe8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhe9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhe10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhe11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhe7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGhe14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhe15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhe16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhe17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhe12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhe13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(64, 64, 64)
                            .addComponent(txtGhe8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(txtGhe9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(txtGhe10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)
                            .addComponent(txtGhe11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtGhe7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtGhe1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGhe2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtGhe3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtGhe4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtGhe5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(txtGhe6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtGhe12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(txtGhe14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(txtGhe15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(txtGhe16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(txtGhe17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtGhe13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Chọn ghế:");

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Ghi Chú");

        jTextField1.setEditable(false);
        jTextField1.setBackground(Color.white);
        jTextField1.setFocusable(false);

        jTextField2.setEditable(false);
        jTextField2.setBackground(Color.green);
        jTextField2.setFocusable(false);

        jTextField3.setEditable(false);
        jTextField3.setBackground(Color.red);
        jTextField3.setFocusable(false);

        jLabel7.setText(": Còn Trống");

        jLabel8.setText(": Đang Chọn");

        jLabel9.setText(": Đã được đặt");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addGap(0, 30, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbSoLuongVe.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbSoLuongVe.setText("0");

        jComboBox_Day.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jComboBox_Day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày Khởi Hành" }));
        jComboBox_Day.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_DayItemStateChanged(evt);
            }
        });
        jComboBox_Day.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_DayMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBox_DayMouseEntered(evt);
            }
        });

        jComboBox_Time.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jComboBox_Time.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_TimeItemStateChanged(evt);
            }
        });
        jComboBox_Time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_TimeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBox_TimeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jComboBox_TimeMouseExited(evt);
            }
        });

        lblNoiDi1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNoiDi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNoiDi1.setText("Loại xe :");
        lblNoiDi1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox_loaiXe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Loại Xe" }));
        jComboBox_loaiXe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_loaiXeItemStateChanged(evt);
            }
        });

        lblNoiDi2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNoiDi2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNoiDi2.setText("Xuất Phát : ");
        lblNoiDi2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox_chuyenDi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Nơi Xuất Phát" }));
        jComboBox_chuyenDi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_chuyenDiItemStateChanged(evt);
            }
        });
        jComboBox_chuyenDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_chuyenDiActionPerformed(evt);
            }
        });

        jLabel_price.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel_price.setText("0 VND");

        lblGiaVe1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGiaVe1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGiaVe1.setText("Số Lượng :");
        lblGiaVe1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblGiaVe2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGiaVe2.setForeground(new java.awt.Color(255, 51, 0));
        lblGiaVe2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGiaVe2.setText("Tổng Tiền : ");
        lblGiaVe2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel_priceAll.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel_priceAll.setForeground(new java.awt.Color(255, 51, 0));
        jLabel_priceAll.setText("0 VND");

        lblNoiDi3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNoiDi3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNoiDi3.setText("Điểm Đến  :");
        lblNoiDi3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox_diemDen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Điểm Đến" }));
        jComboBox_diemDen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_diemDenItemStateChanged(evt);
            }
        });
        jComboBox_diemDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_diemDenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDatVeLayout = new javax.swing.GroupLayout(pnDatVe);
        pnDatVe.setLayout(pnDatVeLayout);
        pnDatVeLayout.setHorizontalGroup(
            pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDatVeLayout.createSequentialGroup()
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDatVeLayout.createSequentialGroup()
                                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDatVeLayout.createSequentialGroup()
                                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblGiaVe1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblGiaVe2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDatVeLayout.createSequentialGroup()
                                        .addComponent(lblGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)))
                                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSoLuongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_price, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_priceAll, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnDatVeLayout.createSequentialGroup()
                                .addComponent(lblNoiDi2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_chuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnDatVeLayout.createSequentialGroup()
                                .addComponent(lblNoiDi1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_loaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnDatVeLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(lblNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jComboBox_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnDatVeLayout.createSequentialGroup()
                                .addComponent(lblNoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_Day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addComponent(lblNoiDi3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jComboBox_diemDen, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnDatVeLayout.setVerticalGroup(
            pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoiDi2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_chuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoiDi3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_diemDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoiDi1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_loaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Day, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_price, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGiaVe1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSoLuongVe))
                        .addGap(18, 18, 18)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGiaVe2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_priceAll, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        btnXacNhan.setText("Đặt Vé");
        btnXacNhan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 16))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel25.setText("SĐT:");

        txtSDT.setEditable(false);
        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSDTFocusLost(evt);
            }
        });
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSDTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel27.setText("Họ Tên:");

        txtHoTen_Khach.setEditable(false);
        txtHoTen_Khach.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel28.setText("Giới Tính");

        buttonGroup1.add(rBtnNam1);
        rBtnNam1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rBtnNam1.setText("Nam");
        rBtnNam1.setEnabled(false);
        rBtnNam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnNam1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rBtnNu1);
        rBtnNu1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        rBtnNu1.setText("Nữ");
        rBtnNu1.setEnabled(false);

        lbLoiGioiTinh.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiGioiTinh.setText("Vui lòng chọn giới tính");
        lbLoiGioiTinh.setVisible(false);

        lbLoiTen.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiTen.setText("Họ Tên không hợp lệ.");
        lbLoiTen.setVisible(false);

        lbLoiSDT.setForeground(new java.awt.Color(255, 51, 51));
        lbLoiSDT.setText("Vui lòng nhập đúng SĐT");
        lbLoiSDT.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbLoiSDT)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbLoiTen)
                            .addComponent(txtHoTen_Khach, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbLoiGioiTinh)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(rBtnNam1)
                            .addGap(9, 9, 9)
                            .addComponent(rBtnNu1)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbLoiSDT)
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen_Khach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbLoiTen)
                .addGap(26, 26, 26)
                .addComponent(jLabel28)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rBtnNam1)
                    .addComponent(rBtnNu1))
                .addGap(18, 18, 18)
                .addComponent(lbLoiGioiTinh)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnDatVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(478, 478, 478))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnDatVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtGhe2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe2MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe2);
    }//GEN-LAST:event_txtGhe2MouseClicked

    private void txtGhe2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGhe2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhe2ActionPerformed

    private void txtGhe8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe8MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe8);
    }//GEN-LAST:event_txtGhe8MouseClicked

    private void txtGhe3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe3MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe3);
    }//GEN-LAST:event_txtGhe3MouseClicked

    private void txtGhe4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe4MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe4);
    }//GEN-LAST:event_txtGhe4MouseClicked

    private void txtGhe5MouseClicked1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe5MouseClicked1
        // TODO add your handling code here:
        changeColor(txtGhe5);
    }//GEN-LAST:event_txtGhe5MouseClicked1

    private void txtGhe6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe6MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe6);
    }//GEN-LAST:event_txtGhe6MouseClicked

    private void txtGhe10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe10MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe10);
    }//GEN-LAST:event_txtGhe10MouseClicked

    private void txtGhe7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe7MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe7);
    }//GEN-LAST:event_txtGhe7MouseClicked

    private void txtGhe11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe11MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe11);
    }//GEN-LAST:event_txtGhe11MouseClicked

    private void txtGhe9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe9MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe9);
    }//GEN-LAST:event_txtGhe9MouseClicked

    private void txtGhe14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe14MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe14);
    }//GEN-LAST:event_txtGhe14MouseClicked

    private void txtGhe15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe15MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe15);
    }//GEN-LAST:event_txtGhe15MouseClicked

    private void txtGhe16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe16MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe16);
    }//GEN-LAST:event_txtGhe16MouseClicked

    private void txtGhe12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe12MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe12);
    }//GEN-LAST:event_txtGhe12MouseClicked

    private void txtGhe13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe13MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe13);
    }//GEN-LAST:event_txtGhe13MouseClicked

    private void txtGhe17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe17MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe17);
    }//GEN-LAST:event_txtGhe17MouseClicked

    public void thoigian() {
        int day = Integer.parseInt(jComboBox_Day.getSelectedItem().toString().substring(0, 2));
        String tramXuatPhat = jComboBox_chuyenDi.getSelectedItem().toString();

        //String tramDen = jComboBox_diemDen.getSelectedIndex()!=-1? jComboBox_diemDen.getSelectedItem().toString():"";
        String tramDen = jComboBox_diemDen.getSelectedItem().toString();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");// /MM/uuuu
        LocalDate localDate = LocalDate.now();
        int ngay = Integer.parseInt(dtf.format(localDate));
        int gio = Integer.parseInt(java.time.LocalTime.now().toString().substring(0, 2)) + 2;
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "SELECT DISTINCT GioDi FROM CHUYEN_XE where TrangThai=1 AND TramXuatPhat=N'" + tramXuatPhat + "' AND TramDen=N'" + tramDen + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            jComboBox_Time.removeAllItems();
            if (day == ngay) {
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString(1).toString().substring(0, 2)) >= gio) {
                        jComboBox_Time.addItem(rs.getString(1).substring(0, 8));
                    }
                }
            } else {
                while (rs.next()) {
                    jComboBox_Time.addItem(rs.getString(1).substring(0, 8));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PnDatVe.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void jComboBox_DayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_DayItemStateChanged
        //reset select
        taiGheDaDat();
        lbSoLuongVe.setText("0");
        jLabel_priceAll.setText("0 VND");
        // Cập Nhập lại Giờ
        thoigian();
    }//GEN-LAST:event_jComboBox_DayItemStateChanged

    private void jComboBox_TimeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_TimeItemStateChanged
        // TODO add your handling code here:
        //reset select
        taiGheDaDat();
        lbSoLuongVe.setText("0");
        jLabel_priceAll.setText("0 VND");

    }//GEN-LAST:event_jComboBox_TimeItemStateChanged

    private void jComboBox_TimeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_TimeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_TimeMouseExited

    private void jComboBox_loaiXeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_loaiXeItemStateChanged
        // TODO add your handling code here:
        //reset select
        lbSoLuongVe.setText("0");
        jLabel_priceAll.setText("0 VND");

        String loaiXe = jComboBox_loaiXe.getSelectedItem().toString();
        Locale localeVN = new Locale("vi", "VN");
        double price = Double.valueOf(giaVe(jComboBox_loaiXe.getSelectedItem().toString()));

        jLabel_price.setText(String.valueOf(NumberFormat.getCurrencyInstance(localeVN).format(price)).substring(0, String.valueOf(NumberFormat.getCurrencyInstance(localeVN).format(price)).length() - 1) + " VND");
        taiGheDaDat();
    }//GEN-LAST:event_jComboBox_loaiXeItemStateChanged

    private void jComboBox_chuyenDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_chuyenDiItemStateChanged
        //reset select
        taiGheDaDat();
        lbSoLuongVe.setText("0");
        jLabel_priceAll.setText("0 VND");

        String chuyenDi = jComboBox_chuyenDi.getSelectedItem().toString();
        taiNoiDen(chuyenDi);

        thoigian();
    }//GEN-LAST:event_jComboBox_chuyenDiItemStateChanged

    private void jComboBox_chuyenDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_chuyenDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_chuyenDiActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        if (ktNhap() && selected.size() > 0) {
            //nếu nhập khách hàng mới thì thêm khách hàng vào database trc
            if (txtHoTen_Khach.isEditable()) {
                DangKyKhach dky = new DangKyKhach();
                String gt = (rBtnNam1.isSelected()) ? "Nam" : "Nữ";
                dky.themKhachHang(txtSDT.getText(), txtHoTen_Khach.getText(), gt, null);
            }
            String trangThai = "0";
            if (JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Đặt Vé? \nHãy Kiểm Tra Kỹ Thông Tin !!!", "Xác Nhận", JOptionPane.YES_NO_OPTION) == 0) {
                if (JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thanh Toán Online ?\nLưu Ý: Thanh Toán Online Sẽ Không Được Hủy Vé!!!", "Thanh Toán Online", JOptionPane.YES_NO_OPTION) == 0) {
                    trangThai = "1";
                }
                for (int i = 0; i < selected.size(); i++) {
                    String viTriGhe = selected.get(i).getText();
                    String maCX = getMaCX(jComboBox_Time.getSelectedItem().toString(), jComboBox_chuyenDi.getSelectedItem().toString());
                    String maVe = jComboBox_Day.getSelectedItem().toString() /*+ jLabel_month.getText()*/ + maCX + viTriGhe;
                    maVe = maVe.replace("/", "");
                    String ngayDi = jComboBox_Day.getSelectedItem().toString() /*+ jLabel_month.getText()*/;

                    //mặc định để "0": chưa được thanh toán
                    // String trangThai ="0";
                    //mặc định để null chưa có nhân viên duyệt
                    String maNV = null;

//=======
//                    //nếu là khách hàng đặt thì maNV null
//                    String maNV = (BanVeXe.quyen.equalsIgnoreCase("Khách hàng")) ? null : BanVeXe.primaryKey;
//                    System.out.println("ma: " + maNV);
//>>>>>>> fdb8fe87cd2cf4e04defd45e4ad5fcb4db52d119
                    String giaVe = giaVe(jComboBox_loaiXe.getSelectedItem().toString());
                    giaVe = giaVe.substring(0, giaVe.indexOf("."));
                    System.out.println(giaVe);
                    datVe(maVe, txtSDT.getText(), viTriGhe, giaVe, ngayDi, maCX, trangThai, maNV);
                }
                taiGheDaDat();
                if (trangThai.equals("0")) {
                    JOptionPane.showMessageDialog(this, "Bạn Đã Đặt Vé Thanh Công. Vé Chưa Được Thanh Toán.\nVui Lòng Đến Quầy Thanh Toán.");
                } else {
                    JOptionPane.showMessageDialog(this, "Bạn Đã Đặt Vé Thanh Công. Vé Đã Được Thanh Toán");
                }

                lbSoLuongVe.setText("0");
                jLabel_priceAll.setText("0 VND");
                selected.clear();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng Kiểm Tra lại thông tin", "Chưa Thể Đặt Vé", 0);
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void txtSDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTFocusLost


    }//GEN-LAST:event_txtSDTFocusLost

    private void txtSDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTKeyPressed

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        // TODO add your handling code here:
        timKiemKhach();
    }//GEN-LAST:event_txtSDTKeyReleased

    private void rBtnNam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnNam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnNam1ActionPerformed

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_formFocusLost

    private void jComboBox_diemDenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_diemDenItemStateChanged
        jComboBox_Day.setSelectedIndex(0);
        if (jComboBox_diemDen.getSelectedItem().toString().equals("Điểm Đến")) {
            jComboBox_Time.setEnabled(false);
            jComboBox_Day.setEnabled(false);
        } else {
            jComboBox_Time.setEnabled(true);
            jComboBox_Day.setEnabled(true);
        }
        thoigian();
    }//GEN-LAST:event_jComboBox_diemDenItemStateChanged

    private void jComboBox_diemDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_diemDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_diemDenActionPerformed

    private void jComboBox_DayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_DayMouseClicked
        // TODO add your handling code here:
        if (jComboBox_diemDen.getSelectedItem().toString().equals("Điểm Đến")) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Điểm Đến");
        }
    }//GEN-LAST:event_jComboBox_DayMouseClicked

    private void jComboBox_DayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_DayMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox_DayMouseEntered

    private void jComboBox_TimeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_TimeMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox_TimeMouseEntered

    private void jComboBox_TimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_TimeMouseClicked
        // TODO add your handling code here:
        if (jComboBox_diemDen.getSelectedItem().toString().equals("Điểm Đến")) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Điểm Đến");
        }
    }//GEN-LAST:event_jComboBox_TimeMouseClicked

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
        phanQuyenDatVe();
    }//GEN-LAST:event_formHierarchyChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox_Day;
    private javax.swing.JComboBox<String> jComboBox_Time;
    private javax.swing.JComboBox<String> jComboBox_chuyenDi;
    private javax.swing.JComboBox<String> jComboBox_diemDen;
    private javax.swing.JComboBox<String> jComboBox_loaiXe;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_month;
    private javax.swing.JLabel jLabel_price;
    private javax.swing.JLabel jLabel_priceAll;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lbLoiGioiTinh;
    private javax.swing.JLabel lbLoiSDT;
    private javax.swing.JLabel lbLoiTen;
    private javax.swing.JLabel lbSoLuongVe;
    private javax.swing.JLabel lblGiaVe;
    private javax.swing.JLabel lblGiaVe1;
    private javax.swing.JLabel lblGiaVe2;
    private javax.swing.JLabel lblNoiDen;
    private javax.swing.JLabel lblNoiDi;
    private javax.swing.JLabel lblNoiDi1;
    private javax.swing.JLabel lblNoiDi2;
    private javax.swing.JLabel lblNoiDi3;
    private javax.swing.JPanel pnDatVe;
    private javax.swing.JRadioButton rBtnNam1;
    private javax.swing.JRadioButton rBtnNu1;
    private javax.swing.JTextField txtGhe1;
    private javax.swing.JTextField txtGhe10;
    private javax.swing.JTextField txtGhe11;
    private javax.swing.JTextField txtGhe12;
    private javax.swing.JTextField txtGhe13;
    private javax.swing.JTextField txtGhe14;
    private javax.swing.JTextField txtGhe15;
    private javax.swing.JTextField txtGhe16;
    private javax.swing.JTextField txtGhe17;
    private javax.swing.JTextField txtGhe2;
    private javax.swing.JTextField txtGhe3;
    private javax.swing.JTextField txtGhe4;
    private javax.swing.JTextField txtGhe5;
    private javax.swing.JTextField txtGhe6;
    private javax.swing.JTextField txtGhe7;
    private javax.swing.JTextField txtGhe8;
    private javax.swing.JTextField txtGhe9;
    private javax.swing.JTextField txtHoTen_Khach;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
