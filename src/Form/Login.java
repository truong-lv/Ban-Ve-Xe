/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.font.TextAttribute;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author n18dc
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        //this.setContentPane(new JLabel(new ImageIcon("/image/BackgrounLogin.png")));
        //this.getContentPane().setBackground(new Color(4,25,45));
        //pnLogin.add(new JLabel(new ImageIcon("D:\\MyCode\\Java\\BanVeXe\\src\\image\\NhaXe1.png")));
        initComponents();
        pnDKY.setVisible(false);
        //pnLogin.setVisible(false);
        txtTK.setText(BanVeXe.Account);
    }
    public  void loadAccount(){
        boolean checkLogin=false;
        Connection connect=KetNoiSQL.KetNoi.layKetNoi();
        String tk=txtTK.getText();
        String mk=psMatkhau.getText();
        //=========================LOAD TÀI KHOẢN TỪ DATABASE============================
        try {
            CallableStatement command = connect.prepareCall("{call SP_LOAD_LOGIN (?,?)}");
             //cung cap gia tri cho bien
            command.setString(1, tk);
            command.setString(2, mk);
            ResultSet rs = command.executeQuery();
            
            // duyet ket qua
            while (rs.next()) {
                BanVeXe.Account=tk;
                BanVeXe.pass=mk;
                BanVeXe.primaryKey=rs.getString(1);
                BanVeXe.hoTen=rs.getString(2);
                BanVeXe.quyen=rs.getInt(3);
                System.out.println("ssssssssss");
                checkLogin= true;
            }
            
            // dong ket noi
            rs.close();
            command.close();
            connect.close();
        } catch (Exception e) {
        }
        
        
        //====================KT NẾU LOAD ĐƯỢC TÀI KHOẢN THÌ CHUYỂN GIAO DIỆN=========================
        if(checkLogin){
            //lbErorrLogin.setVisible(false);
            
            if(BanVeXe.quyen==0){
                new GDAdmin().setVisible(true);
                this.dispose();
            }
            else if(BanVeXe.quyen==1){
                new GDNhanVien().setVisible(true);
                this.dispose();
            }
            if(BanVeXe.quyen==2){
                new GDKhach().setVisible(true);
                this.dispose();
            }
        }else {
            lbErorrLogin.setVisible(true);
        }
    }
    
    public void LoginAccount(){
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnDKY = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtCMT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbHo = new javax.swing.JLabel();
        lbTen = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbMatKhau = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbDangKy = new javax.swing.JLabel();
        psMatkhau1 = new javax.swing.JPasswordField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        rBtnNam = new javax.swing.JRadioButton();
        rBtnNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        lbGioiTinh = new javax.swing.JLabel();
        btnDangKy = new javax.swing.JButton();
        pnLogin = new javax.swing.JPanel();
        lbErorrLogin = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        fLbDangKy = new javax.swing.JLabel();
        psMatkhau = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        BackgroundLogin = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel4.setText("Đăng Ký Thành Viên!");

        jLabel5.setText("Đồng hành cùng bạn trên mọi hành trình");

        jLabel7.setText("Họ Tên");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        txtCMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCMTActionPerformed(evt);
            }
        });

        jLabel8.setText("CMT/CCCD");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel9.setText("SĐT");

        jLabel10.setText("Mật khẩu");

        lbHo.setForeground(new java.awt.Color(255, 0, 0));
        lbHo.setText("Vui lòng nhập CMT.");
        lbHo.setVisible(false);

        lbTen.setForeground(new java.awt.Color(255, 0, 0));
        lbTen.setText("Vui lòng nhập Tên.");
        lbTen.setVisible(false);

        lbEmail.setForeground(new java.awt.Color(255, 0, 0));
        lbEmail.setText("Vui lòng nhập SĐT");
        lbEmail.setVisible(false);

        lbMatKhau.setForeground(new java.awt.Color(255, 0, 0));
        lbMatKhau.setText("Vui lòng nhập Mật khẩu.");
        lbMatKhau.setVisible(false);

        jLabel12.setText("Từ 6 đến 50 ký tự");

        jLabel14.setText("Bạn có muốn");

        lbDangKy.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbDangKy.setForeground(new java.awt.Color(51, 51, 255));
        lbDangKy.setText("Đăng nhập");
        lbDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDangKyMouseClicked(evt);
            }
        });

        rBtnNam.setText("Nam");
        rBtnNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rBtnNamActionPerformed(evt);
            }
        });

        rBtnNu.setText("Nữ");

        jLabel11.setText("Giới Tính");

        lbGioiTinh.setForeground(new java.awt.Color(255, 0, 0));
        lbGioiTinh.setText("Vui lòng chọn giới tính");
        lbGioiTinh.setVisible(false);

        btnDangKy.setBackground(new java.awt.Color(102, 102, 255));
        btnDangKy.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnDangKy.setForeground(new java.awt.Color(255, 255, 255));
        btnDangKy.setText("Đăng Ký");
        btnDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDKYLayout = new javax.swing.GroupLayout(pnDKY);
        pnDKY.setLayout(pnDKYLayout);
        pnDKYLayout.setHorizontalGroup(
            pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDKYLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDKYLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDKYLayout.createSequentialGroup()
                                .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnDKYLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel4))
                                .addGap(195, 195, 195))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDKYLayout.createSequentialGroup()
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnDKYLayout.createSequentialGroup()
                                        .addComponent(lbDangKy)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel14))
                                .addGap(197, 197, 197))))
                    .addGroup(pnDKYLayout.createSequentialGroup()
                        .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDKYLayout.createSequentialGroup()
                                .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnDKYLayout.createSequentialGroup()
                                        .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(lbTen)
                                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(94, 94, 94)
                                        .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbGioiTinh)
                                            .addComponent(jLabel11)
                                            .addGroup(pnDKYLayout.createSequentialGroup()
                                                .addComponent(rBtnNam)
                                                .addGap(18, 18, 18)
                                                .addComponent(rBtnNu))))
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(lbEmail))
                                .addGap(0, 139, Short.MAX_VALUE))
                            .addComponent(btnDangKy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(pnDKYLayout.createSequentialGroup()
                        .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(psMatkhau1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbHo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMatKhau, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCMT, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 371, Short.MAX_VALUE))))
        );
        pnDKYLayout.setVerticalGroup(
            pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDKYLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rBtnNam)
                    .addComponent(rBtnNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTen)
                    .addComponent(lbGioiTinh))
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCMT, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHo)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lbEmail)
                .addGap(35, 35, 35)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(psMatkhau1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMatKhau)
                .addGap(3, 3, 3)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(btnDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDKYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDKYLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbDangKy))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHẦN MỀM BÁN VÉ XE");
        setBackground(new java.awt.Color(4, 25, 45));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);
        setLocation(new java.awt.Point(600, 200));
        setResizable(false);

        pnLogin.setOpaque(false);
        pnLogin.setLayout(null);

        lbErorrLogin.setForeground(new java.awt.Color(255, 102, 102));
        lbErorrLogin.setText("Tài hoặc mật khẩu không hợp lệ !!Vui lòng nhập lại");
        lbErorrLogin.setVisible(false);
        pnLogin.add(lbErorrLogin);
        lbErorrLogin.setBounds(70, 470, 290, 33);

        jLabel13.setText("Nếu bạn chưa có tài khoản?");
        pnLogin.add(jLabel13);
        jLabel13.setBounds(140, 520, 158, 16);

        btnDangNhap.setBackground(new java.awt.Color(242, 227, 57));
        btnDangNhap.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("Đăng Nhập");
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        btnDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDangNhapKeyPressed(evt);
            }
        });
        pnLogin.add(btnDangNhap);
        btnDangNhap.setBounds(40, 400, 350, 50);

        fLbDangKy.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        fLbDangKy.setForeground(new java.awt.Color(102, 255, 255));
        fLbDangKy.setText("Đăng Ký");
        fLbDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fLbDangKyMouseClicked(evt);
            }
        });
        pnLogin.add(fLbDangKy);
        fLbDangKy.setBounds(180, 550, 67, 20);

        psMatkhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                psMatkhauKeyPressed(evt);
            }
        });
        pnLogin.add(psMatkhau);
        psMatkhau.setBounds(80, 350, 310, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel1.setText("Đăng Nhập Tài Khoản");
        pnLogin.add(jLabel1);
        jLabel1.setBounds(90, 60, 253, 32);
        pnLogin.add(txtTK);
        txtTK.setBounds(80, 290, 310, 42);

        jLabel2.setText("Đồng hành cùng bạn trên mọi hành trình");
        pnLogin.add(jLabel2);
        jLabel2.setBounds(100, 230, 232, 16);
        pnLogin.add(jSeparator1);
        jSeparator1.setBounds(130, 560, 50, 10);
        pnLogin.add(jSeparator2);
        jSeparator2.setBounds(250, 560, 50, 10);

        BackgroundLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImageBackground/Login.png"))); // NOI18N
        pnLogin.add(BackgroundLogin);
        BackgroundLogin.setBounds(0, 0, 420, 670);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txtCMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCMTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCMTActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyActionPerformed
        // TODO add your handling code here:
        boolean ktTen=false,ktGT=false, ktCMT=false, ktSDT=false, ktMK=false;
        // KT Nhập họ tên
        if(txtTen.getText().isEmpty()){
            ktTen=true;
            lbTen.setVisible(true);
            txtTen.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(!txtTen.getText().isEmpty()) {
            lbTen.setVisible(false);
            txtTen.setBorder(BorderFactory.createLineBorder(null));

        }
        
        //KT nhập Gioi Tinh
        if(!rBtnNam.isSelected() && !rBtnNu.isSelected()){
            ktGT=true;
            lbGioiTinh.setVisible(true);
        }
        else {
            lbGioiTinh.setVisible(false);
        }
        
        //KT nhập CMND
        if(txtCMT.getText().isEmpty()||txtCMT.getText().matches("[0-9]{9}")){
            ktCMT=true;
            lbHo.setVisible(true);
            txtCMT.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(!txtCMT.getText().isEmpty()) {
            lbHo.setVisible(false);
            txtCMT.setBorder(BorderFactory.createLineBorder(null));
        }

        // KT nhập sđt
        if( !txtSDT.getText().matches("0\\d{9,10}")){
            ktSDT=true;
            lbEmail.setVisible(true);
            txtSDT.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(txtSDT.getText().matches("0\\d{9,10}")) {
            lbEmail.setVisible(false);
            txtSDT.setBorder(BorderFactory.createLineBorder(null));
        }

        // KT nhập mật khẩu
        if(psMatkhau.getText().isEmpty()){
            ktMK=true;
            lbMatKhau.setVisible(true);
            psMatkhau.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        else if(!psMatkhau.getText().isEmpty()) {
            lbMatKhau.setVisible(false);
            psMatkhau.setBorder(BorderFactory.createLineBorder(null));
        }
        if( ktSDT && ktMK){
            JOptionPane.showConfirmDialog(this, "ko đc");

        }
    }//GEN-LAST:event_btnDangKyActionPerformed

    private void lbDangKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDangKyMouseClicked
        // TODO add your handling code here:
        pnDKY.setVisible(false);
        pnLogin.setVisible(true);
    }//GEN-LAST:event_lbDangKyMouseClicked

    private void rBtnNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rBtnNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rBtnNamActionPerformed

    private void psMatkhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psMatkhauKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loadAccount();
        }
    }//GEN-LAST:event_psMatkhauKeyPressed

    private void fLbDangKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fLbDangKyMouseClicked
        pnLogin.setVisible(false);
        pnDKY.setVisible(true);
    }//GEN-LAST:event_fLbDangKyMouseClicked

    private void btnDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDangNhapKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loadAccount();
        }
    }//GEN-LAST:event_btnDangNhapKeyPressed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        // TODO add your handling code here:
        loadAccount();

    }//GEN-LAST:event_btnDangNhapActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundLogin;
    private javax.swing.JButton btnDangKy;
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JLabel fLbDangKy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbDangKy;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbErorrLogin;
    private javax.swing.JLabel lbGioiTinh;
    private javax.swing.JLabel lbHo;
    private javax.swing.JLabel lbMatKhau;
    private javax.swing.JLabel lbTen;
    private javax.swing.JPanel pnDKY;
    private javax.swing.JPanel pnLogin;
    private javax.swing.JPasswordField psMatkhau;
    private javax.swing.JPasswordField psMatkhau1;
    private javax.swing.JRadioButton rBtnNam;
    private javax.swing.JRadioButton rBtnNu;
    private javax.swing.JTextField txtCMT;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

    private void setLocation(float CENTER_ALIGNMENT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
