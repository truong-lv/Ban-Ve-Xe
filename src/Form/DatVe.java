/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import KetNoiSQL.KetNoi;
import java.time.LocalDate;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author n18dc
 */
public class DatVe extends javax.swing.JFrame {

    //giữ sdt khách đặt vé
    ArrayList<JTextField> selected = new ArrayList<JTextField>();
    ArrayList<JTextField> chair = new ArrayList<JTextField>();

    public DatVe() {

        initComponents();
        AddTime();
        addChair();
        loadChair();
    }

    //Hàm Lấy Mã Những Chuyến Xe Có Gio Di Và TramXuatPhat cho khách chọn
    public String getMaCX(String time, String start) {
        String maCX = "";
        Connection ketNoi = KetNoi.layKetNoi();
        String sql = "SELECT * FROM CHUYEN_XE WHERE GioDi=? AND TramXuatPhat=?";

        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, (time + ".0000000"));
            ps.setString(2, start);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                maCX = rs.getString(1);
            }
        } catch (Exception e) {
            System.err.println("SAi ROI");
        }

        return maCX;
    }

    //Hàm setcolor ghế lại màu trắng
    public void setChair() {
        for (int i = 0; i < chair.size(); i++) {
            chair.get(i).setBackground(Color.WHITE);
        }
    }

    public void loadChair() {
        setChair();
        String price = "";
        String loaiXe = jComboBox_loaiXe.getSelectedItem().toString();
        if (loaiXe.equals("Giường Nằm")) {
            price = "300000";
        } else {
            price = "150000";
        }
        
        String day = jComboBox_Day.getSelectedItem().toString() + jLabel_month.getText();

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
                    if (chair.get(i).getText().equals(rs.getString("ViTriGhe")) && price.equals(rs.getString(4)) && day.equals(rs.getString(5)) && maCX.equals(rs.getString(6))) {
                        chair.get(Integer.parseInt(rs.getString(3))-2).setBackground(Color.RED);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    // Hàm load thời gian để khách hàng đặt vé
    public void AddTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("/MM/uuuu");
        LocalDate localDate = LocalDate.now();
        jLabel_month.setText(dtf.format(localDate));

        int toDay = Integer.parseInt(java.time.LocalDate.now().toString().substring(8, 10));
        int temp = 0;
        for (int i = toDay; temp < 7; i++) {
            jComboBox_Day.addItem(String.valueOf(i));
            temp++;
            //ngày ko quá 31
            if (i == 31) {
                break;
            }
        }
        //biến temp để xem thử coi có đủ 7 ngày chưa nếu chưa thì add thêm
        if (temp < 7) {
            for (int z = 1; temp < 7; z++) {
                jComboBox_Day.addItem(String.valueOf(z));
                temp++;
            }
        }
        int time = Integer.parseInt(java.time.LocalTime.now().toString().substring(0, 2)) + 3;
        for (int i = time; i < 24; i++) {
            if (i < 10) {
                jComboBox_Time.addItem("0" + String.valueOf(i) + ":00:00");
            } else {
                jComboBox_Time.addItem(String.valueOf(i) + ":00:00");
            }
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
                    JOptionPane.showConfirmDialog(null, "Không được đặt quá 5 ghế", "Be ok!", JOptionPane.DEFAULT_OPTION);
                }
            }
        }

        // Tính Tổng Tiền
        Locale localeVN = new Locale("vi", "VN");
        int price = Integer.parseInt(jLabel_price.getText().substring(0, 3) + jLabel_price.getText().substring(4, 7)) * Integer.parseInt(lbSoLuongVe.getText());
        jLabel_priceAll.setText(String.valueOf(NumberFormat.getCurrencyInstance(localeVN).format(price)));
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
        Connection connect = KetNoiSQL.KetNoi.layKetNoi();
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
        } catch (SQLException e) {
            Logger.getLogger(DatVe.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnDatVe = new javax.swing.JPanel();
        lblNoiDi = new javax.swing.JLabel();
        lblNoiDen = new javax.swing.JLabel();
        lblGiaVe = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
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
        jLabel_month = new javax.swing.JLabel();
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
        jLabel_diemDen = new javax.swing.JLabel();

        jLabel11.setText("jLabel11");

        jLabel10.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(600, 200));
        setResizable(false);

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

        btnQuayLai.setText("Quay lại");
        btnQuayLai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Đặt Vé");

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

        jLabel_month.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        jComboBox_Day.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jComboBox_Day.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_DayItemStateChanged(evt);
            }
        });

        jComboBox_Time.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jComboBox_Time.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_TimeItemStateChanged(evt);
            }
        });
        jComboBox_Time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jComboBox_TimeMouseExited(evt);
            }
        });

        lblNoiDi1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNoiDi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNoiDi1.setText("Loại xe :");
        lblNoiDi1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox_loaiXe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giường Nằm", "Ghế Ngồi" }));
        jComboBox_loaiXe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_loaiXeItemStateChanged(evt);
            }
        });

        lblNoiDi2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNoiDi2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNoiDi2.setText("Xuất Phát : ");
        lblNoiDi2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox_chuyenDi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TP.HCM", "Đồng Nai" }));
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
        jLabel_price.setText("300.000 đ");

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
        lblNoiDi3.setText("Điểm Đến :");
        lblNoiDi3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel_diemDen.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        javax.swing.GroupLayout pnDatVeLayout = new javax.swing.GroupLayout(pnDatVe);
        pnDatVe.setLayout(pnDatVeLayout);
        pnDatVeLayout.setHorizontalGroup(
            pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDatVeLayout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnDatVeLayout.createSequentialGroup()
                                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGiaVe1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblGiaVe2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNoiDi1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNoiDi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblNoiDi3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSoLuongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_loaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_price, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_priceAll, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnDatVeLayout.createSequentialGroup()
                                        .addComponent(jComboBox_Day, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel_month, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox_chuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_diemDen, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(35, 35, 35)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDatVeLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)))
                    .addComponent(lblNoiDi2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDatVeLayout.createSequentialGroup()
                .addGap(0, 330, Short.MAX_VALUE)
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDatVeLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(306, 306, 306))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDatVeLayout.createSequentialGroup()
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182))))
        );
        pnDatVeLayout.setVerticalGroup(
            pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDatVeLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoiDi1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_loaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoiDi2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_chuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNoiDi3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_diemDen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_Day, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_month, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGiaVe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_price, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGiaVe1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSoLuongVe))
                        .addGap(18, 18, 18)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGiaVe2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_priceAll, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnDatVeLayout.createSequentialGroup()
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82)
                        .addGroup(pnDatVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnDatVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnDatVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        int choose=JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát?", "Xác Nhận", 0);
        if(choose==JOptionPane.OK_OPTION){
            this.dispose();
            new GDKhach().setVisible(true);
        }
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void txtGhe2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe2MouseClicked
        // TODO add your handling code here:

        changeColor(txtGhe2);
    }//GEN-LAST:event_txtGhe2MouseClicked

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

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        if (selected.size() > 0) {
            for (int i = 0; i < selected.size(); i++) {
                String viTriGhe = selected.get(i).getText();
                String maCX = getMaCX(jComboBox_Time.getSelectedItem().toString(), jComboBox_chuyenDi.getSelectedItem().toString());
                String maVe = jComboBox_Day.getSelectedItem().toString() + jLabel_month.getText() + maCX + viTriGhe;
                maVe = maVe.replace("/", "");
                String ngayDi = jComboBox_Day.getSelectedItem().toString() + jLabel_month.getText();
                String trangThai = "0";
                String maNV = null;
                String giaVe;
                if (jComboBox_loaiXe.getSelectedItem().toString().equals("Giường Nằm")) {
                    giaVe = "300000";
                } else {
                    giaVe = "150000";
                }

                datVe(maVe, BanVeXe.primaryKey, viTriGhe, giaVe, ngayDi, maCX, trangThai, maNV);
                JOptionPane.showMessageDialog(this, "Bạn Đã Đặt Vé Thanh Công");
                this.dispose();
                new GDKhach().setVisible(true);
            }
        }
        else JOptionPane.showMessageDialog(this, "Vui lòng Kiểm Tra lại","Chưa Thể Đặt Vé",0);
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void txtGhe17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhe17MouseClicked
        // TODO add your handling code here:
        changeColor(txtGhe17);
    }//GEN-LAST:event_txtGhe17MouseClicked

    private void jComboBox_loaiXeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_loaiXeItemStateChanged
        // TODO add your handling code here:
        String loaiXe = jComboBox_loaiXe.getSelectedItem().toString();
        if (loaiXe.equals("Giường Nằm")) {
            jLabel_price.setText("300.000 đ");
        } else {
            jLabel_price.setText("150.000 đ");
        }
        //setChair();
        lbSoLuongVe.setText("0");
        loadChair();
    }//GEN-LAST:event_jComboBox_loaiXeItemStateChanged

    private void jComboBox_chuyenDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_chuyenDiItemStateChanged
        //selected.clear();
        //jLabel_diemDen.setText("TP.HCM");
        lbSoLuongVe.setText("0");
        jLabel_priceAll.setText("0 VND");
        String chuyenDi = jComboBox_chuyenDi.getSelectedItem().toString();
        if (chuyenDi.equals("TP.HCM")) {
            jLabel_diemDen.setText("Đồng Nai");
            
        } else {
            jLabel_diemDen.setText("TP.HCM");
        }
        
        loadChair();
    }//GEN-LAST:event_jComboBox_chuyenDiItemStateChanged

    private void jComboBox_DayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_DayItemStateChanged

        // Bắt Điều Kiện Khi Ngày Đặt Tràn Qua Tháng Mới
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("/MM/uuuu");
        LocalDate localDate = LocalDate.now();
        int day = Integer.parseInt(jComboBox_Day.getSelectedItem().toString());
        if (day < 7 && Integer.parseInt(String.valueOf(localDate.getDayOfMonth())) <= 31) {
            jLabel_month.setText(dtf.format(localDate.plusMonths(1)));
        }

        if (day > localDate.getDayOfMonth()) {
            jComboBox_Time.removeAllItems();
            for (int i = 2; i < 24; i++) {
                if (i < 10) {
                    jComboBox_Time.addItem("0" + String.valueOf(i) + ":00:00");
                } else {
                    jComboBox_Time.addItem(String.valueOf(i) + ":00:00");
                }
            }
        }
    }//GEN-LAST:event_jComboBox_DayItemStateChanged

    private void jComboBox_TimeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_TimeItemStateChanged
        // TODO add your handling code here:
        loadChair();
    }//GEN-LAST:event_jComboBox_TimeItemStateChanged

//    int q = 1;
    private void jComboBox_TimeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_TimeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_TimeMouseExited

    private void jComboBox_chuyenDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_chuyenDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_chuyenDiActionPerformed

    private void txtGhe2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGhe2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhe2ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatVe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> jComboBox_Day;
    private javax.swing.JComboBox<String> jComboBox_Time;
    private javax.swing.JComboBox<String> jComboBox_chuyenDi;
    private javax.swing.JComboBox<String> jComboBox_loaiXe;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_diemDen;
    private javax.swing.JLabel jLabel_month;
    private javax.swing.JLabel jLabel_price;
    private javax.swing.JLabel jLabel_priceAll;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
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
    // End of variables declaration//GEN-END:variables
}
