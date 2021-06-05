/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.NhanVien;

import Code.BanVeXe;
import Code.GhiFileExcel;
import Code.HamXuLyBang;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author n18dc
 */
public class PnQlyVe extends javax.swing.JPanel {

    HamXuLyBang xLBang=new HamXuLyBang();
    public PnQlyVe() {
        initComponents();
        //LOAD DỮ LIỆU VÉ VÀO BẢNG TẠM
        xLBang.loadDuLieuVaoBang(tbTam, "{call SP_LOAD_VE_TO_JTABLE ()}");
        
        loadComboNgayDi();
        loadComboGio();
        loadComboChuyenXe();
        locDuLieuVeXe(true);
    }
    
    //lấy dữ liệu từ combobox 
    private String stringCbb(JComboBox cbb){
        return cbb.getSelectedIndex()!=-1?cbb.getSelectedItem().toString():"";
    }
    private int numCbb(JComboBox cbb){
        return cbb.getSelectedIndex()!=-1?Integer.parseInt(cbb.getSelectedItem().toString()):-1;
    }
    private Date StringToDate(String str){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {

            Date date = formatter.parse(str);
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    //LOAD CÁC COMBOBOX TỪ BẢNG TẠM
    public void loadComboNgayDi(){
        cbbNgayDi.removeAllItems();
        String tram=cbbTramDi.getSelectedItem().toString();
        String cheDo=cbbCheDoXem.getSelectedItem().toString();
        
        // load vé đặt trước vào combobox
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");// /MM/uuuu
        LocalDate localDate = LocalDate.now();
        Date htai=StringToDate(dtf.format(localDate));
        Date ngayTam=new Date();
        for(int i=0;i<tbTam.getRowCount() ;i++)//Đk = với trạm đang được chọn
        {
            ngayTam=StringToDate(tbTam.getValueAt(i, 6).toString());
            if((tram.equals(tbTam.getValueAt(i, 8).toString())&&(cheDo.equals("Vé đặt trước") && htai.compareTo(ngayTam)<=0) ) /*|| cheDo.equals("Tất cả vé")*/)//nếu chế độ là vé đặt trc thì load vé >tg hiện tại, ngược lại load tất cả
            {
                if(cbbNgayDi.getItemCount()>0){//kt cbb đã có item nào chưa
                    boolean ktTrung=false;
                    for(int j=0;ktTrung==false && j<cbbNgayDi.getItemCount();j++){
                        if(tbTam.getValueAt(i, 6).toString().equals(cbbNgayDi.getItemAt(j)))// kt ngày đã tồn tại trong combobox chưa
                        {
                            ktTrung=true;
                        }
                    }
                    if(!ktTrung)cbbNgayDi.addItem(tbTam.getValueAt(i, 6).toString());//Ngày nằm ở cột thứ 5
                }
                
                else cbbNgayDi.addItem(tbTam.getValueAt(i, 6).toString());
            }else if(tram.equals(tbTam.getValueAt(i, 8).toString())&&cheDo.equals("Tất cả vé")){
                if(cbbNgayDi.getItemCount()>0){//kt cbb đã có item nào chưa
                    boolean ktTrung=false;
                    for(int j=0;ktTrung==false && j<cbbNgayDi.getItemCount();j++){
                        if(tbTam.getValueAt(i, 6).toString().equals(cbbNgayDi.getItemAt(j)))// kt ngày đã tồn tại trong combobox chưa
                        {
                            ktTrung=true;
                        }
                    }
                    if(!ktTrung)cbbNgayDi.addItem(tbTam.getValueAt(i, 6).toString());//Ngày nằm ở cột thứ 5
                }
                
                else cbbNgayDi.addItem(tbTam.getValueAt(i, 6).toString());
            }
        }

    }
    
    public void loadComboGio(){
        cbbGioDi.removeAllItems();
        String tram=cbbTramDi.getSelectedItem().toString();
        //String ngay=cbbNgayDi.getSelectedItem().toString();
        String ngay=cbbNgayDi.getSelectedIndex()!=-1?cbbNgayDi.getSelectedItem().toString():"";
        for(int i=0;i<tbTam.getRowCount();i++)
        {
            if(tram.equals(tbTam.getValueAt(i, 8).toString()) && ngay.equals(tbTam.getValueAt(i, 6).toString()) )//Trạm nằm ở cột thứ 7
            {
                if(cbbGioDi.getItemCount()>0){//kt cbb đã có item nào chưa
                    boolean ktTrung=false;
                    for(int j=0; ktTrung==false && j<cbbGioDi.getItemCount(); j++){
                        if(tbTam.getValueAt(i, 7).toString().equals(cbbGioDi.getItemAt(j)))// kt ngày đã tồn tại trong combobox chưa
                        {
                            ktTrung=true;
                        }
                    }
                    if(!ktTrung){cbbGioDi.addItem(tbTam.getValueAt(i, 7).toString());}//Ngày nằm ở cột thứ 5
                }
                else cbbGioDi.addItem(tbTam.getValueAt(i, 7).toString());
            }
        }

    }
    
    public void loadComboChuyenXe(){
        cbbChuyenXe.removeAllItems();
        String tram=cbbTramDi.getSelectedItem().toString();
        String ngay=cbbNgayDi.getSelectedIndex()!=-1?cbbNgayDi.getSelectedItem().toString():"";
        String gio=cbbGioDi.getSelectedIndex()!=-1?cbbGioDi.getSelectedItem().toString():"";
        for(int i=0;i<tbTam.getRowCount();i++)
        {
            if(tram.equals(tbTam.getValueAt(i, 8).toString()) 
                    && ngay.equals(tbTam.getValueAt(i, 6).toString())
                    && gio.equals(tbTam.getValueAt(i, 7).toString()) )//Trạm nằm ở cột thứ 7
            {
                if(cbbChuyenXe.getItemCount()>0){//kt cbb đã có item nào chưa
                    boolean ktTrung=false;
                    for(int j=0; ktTrung==false && j<cbbGioDi.getItemCount(); j++){
                        //System.out.println(i+" "+cbbGioDi.getItemAt(j)+" "+j);
                        
                        if(tbTam.getValueAt(i, 2).toString().equals(cbbChuyenXe.getItemAt(j)))// kt ngày đã tồn tại trong combobox chưa
                        {
                            ktTrung=true;
                        }
                    }
                    if(!ktTrung){cbbChuyenXe.addItem(tbTam.getValueAt(i, 2).toString());}//Ngày nằm ở cột thứ 5
                }
                else cbbChuyenXe.addItem(tbTam.getValueAt(i, 2).toString());
            }
        }

    }
    
    
    //Hàm lọc dữ liệu từ Bảng Tạm(-bảng lưu trữ) vào Bảng VéXe(-bảng hiển thị)
    public void locDuLieuVeXe(boolean dk){// biến bool = true: lọc theo đk, bool=false: lọc tất cả
        DefaultTableModel dtm=(DefaultTableModel)tbVeXe.getModel();
        dtm.setNumRows(0);
        Vector vt;
        for(int i=0;i<tbTam.getRowCount();i++)//Duyệt từng cột của bảng tạm
        {
            // lấy những cột thỏa trạm, ngày, giờ, chuyến đi, đưa vào bảng hiện thị
            if (dk==true){
                if(tbTam.getValueAt(i, 8).toString().equals(stringCbb(cbbTramDi)) && tbTam.getValueAt(i, 6).toString().equals(stringCbb(cbbNgayDi)) 
                    && tbTam.getValueAt(i, 7).toString().equals(stringCbb(cbbGioDi)) && Integer.parseInt(tbTam.getValueAt(i, 2).toString())==numCbb(cbbChuyenXe))
                {
                    vt=new Vector();
                    vt.add(Integer.parseInt(tbTam.getValueAt(i, 0).toString()));
                    for(int j=1;j<tbTam.getColumnCount();j++){
                        vt.add(tbTam.getValueAt(i, j));
                    }
                dtm.addRow(vt);
                
                }
            }
            else{
                vt=new Vector();
                    vt.add(Integer.parseInt(tbTam.getValueAt(i, 0).toString()));
                    for(int j=1;j<tbTam.getColumnCount();j++){
                        vt.add(tbTam.getValueAt(i, j));
                    }
                dtm.addRow(vt);
            }
            
        }
        
        tbVeXe.setModel(dtm);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tbVeXe.setDefaultRenderer(Integer.class, centerRenderer);
        // sắp xếp bảng tăng dần theo ghế
        xLBang.sapXepBang(tbVeXe ,0,0);
    }
    
    //--------------Duyệt Vé-------------------------
    public void duyetVe(String maVe, String maNV){
        String trangThai=(String) tbVeXe.getValueAt(tbVeXe.getSelectedRow(), tbVeXe.getColumnCount()-1);
        Connection connect=Code.KetNoi.layKetNoi();
        String sql="UPDATE VE_XE SET TrangThai=1, MaNV=? WHERE MaVe=?";
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, maVe);
            ps.executeUpdate();
            
            ps.close();
            connect.close();
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyVe.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    //----------------Hủy Vé------------------------
    public void huyVe(String maVe){
        Connection connect=Code.KetNoi.layKetNoi();
        String sql="DELETE FROM VE_XE WHERE MaVe=?";
        
        try {
            PreparedStatement ps=connect.prepareStatement(sql);
            ps.setString(1, maVe);
            ps.executeUpdate();
            ps.close();
            connect.close();
        } catch (SQLException e) {
            java.util.logging.Logger.getLogger(PnQlyVe.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tbTam = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtSearchVe = new javax.swing.JTextField();
        btnClearText = new javax.swing.JButton();
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
        jLabel14 = new javax.swing.JLabel();
        cbbChuyenXe = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbbCheDoXem = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        tbTam.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbTam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ghế ngồi", "Mã vé", "Mã chuyến xe", "Điên thoại ", "Họ tên", "Giá vé", "Ngày Đi", "Giờ", "Trạm", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTam.setRowHeight(23);
        tbTam.setRowMargin(3);
        jScrollPane3.setViewportView(tbTam);
        if (tbTam.getColumnModel().getColumnCount() > 0) {
            tbTam.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbTam.getColumnModel().getColumn(4).setPreferredWidth(130);
            tbTam.getColumnModel().getColumn(9).setPreferredWidth(100);
        }

        setBackground(new java.awt.Color(255, 255, 255));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel11.setText("Tìm kiếm:");

        txtSearchVe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSearchVe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchVeKeyReleased(evt);
            }
        });

        btnClearText.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnClearText.setText("X");
        btnClearText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTextActionPerformed(evt);
            }
        });

        tbVeXe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbVeXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ghế ngồi", "Mã vé", "Mã chuyến", "Điên thoại ", "Họ tên", "Giá vé", "Ngày Đi", "Giờ", "Trạm", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbVeXe.setRowHeight(23);
        tbVeXe.setRowMargin(3);
        tbVeXe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbVeXeFocusLost(evt);
            }
        });
        tbVeXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVeXeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbVeXe);
        if (tbVeXe.getColumnModel().getColumnCount() > 0) {
            tbVeXe.getColumnModel().getColumn(0).setPreferredWidth(40);
            tbVeXe.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbVeXe.getColumnModel().getColumn(2).setPreferredWidth(40);
            tbVeXe.getColumnModel().getColumn(4).setPreferredWidth(130);
            tbVeXe.getColumnModel().getColumn(5).setPreferredWidth(60);
            tbVeXe.getColumnModel().getColumn(9).setPreferredWidth(100);
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
        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa thanh toán", "Đã thanh toán" }));
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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel12.setText("Trạm khởi hành:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel31.setText("Ngày:");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel32.setText("Giờ:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel14.setText("Chuyến xe:");

        cbbChuyenXe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbChuyenXe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbChuyenXeItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Chế độ xem:");

        cbbCheDoXem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vé đặt trước", "Tất cả vé" }));
        cbbCheDoXem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCheDoXemItemStateChanged(evt);
            }
        });
        cbbCheDoXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCheDoXemActionPerformed(evt);
            }
        });

        jButton1.setText("Xuất file excel ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbbTramDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbNgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbGioDi, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbChuyenXe, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSearchVe, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnClearText)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(441, 441, 441)
                                .addComponent(btnDuyetVe, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(btnHuyVe, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(420, 420, 420))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbCheDoXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtSearchVe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jButton1))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbChuyenXe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTramDi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(cbbNgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31)
                        .addComponent(jLabel32)
                        .addComponent(cbbGioDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(cbbCheDoXem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDuyetVe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyVe, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTextActionPerformed
        // TODO add your handling code here:
        btnDuyetVe.setEnabled(false);
        btnHuyVe.setEnabled(false);
        txtSearchVe.setText("");
        locDuLieuVeXe(true);
    }//GEN-LAST:event_btnClearTextActionPerformed

    private void tbVeXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVeXeMouseClicked

        // đưa dữ liệu đc chọn lên txt search
        String tbSelect=(String) tbVeXe.getValueAt(tbVeXe.getSelectedRow(), 1);
        txtSearchVe.setText(tbSelect);
        
        //lấy trạng thái của dữ liệu được chọn
        String trangThai=(String) tbVeXe.getValueAt(tbVeXe.getSelectedRow(),tbVeXe.getColumnCount()-1);
        
        if(trangThai.equals("Chưa thanh toán"))// vé nào chưa thanh toán thì cho phép duyệt, hủy
        {
            btnDuyetVe.setEnabled(true);
            btnHuyVe.setEnabled(true);
        }
        else{
            btnHuyVe.setEnabled(true);
            btnDuyetVe.setEnabled(false);
        }

    }//GEN-LAST:event_tbVeXeMouseClicked

    private void btnDuyetVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuyetVeActionPerformed
        // TODO add your handling code here:
        if(!tbVeXe.getSelectionModel().isSelectionEmpty()){
            // Lưu dữ liệu đc chọn vào biến
            String maVe=xLBang.selectRow(tbVeXe, 1);
            int choose = JOptionPane.showConfirmDialog(this, "Xác nhận duyệt vé: "+maVe, "Duyệt vé", 0);
            if(choose==JOptionPane.OK_OPTION){
                duyetVe(maVe,BanVeXe.primaryKey);
                JOptionPane.showMessageDialog(this, "Duyệt vé: "+maVe+" thành công");
                xLBang.loadDuLieuVaoBang(tbTam, "{call SP_LOAD_VE_TO_JTABLE ()}");
                locDuLieuVeXe(true);
                btnDuyetVe.setEnabled(false);
                btnHuyVe.setEnabled(false);
            }
            
        }
    }//GEN-LAST:event_btnDuyetVeActionPerformed

    private void btnHuyVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyVeActionPerformed
        // TODO add your handling code here:
        if(!tbVeXe.getSelectionModel().isSelectionEmpty()){
            String maVe=xLBang.selectRow(tbVeXe, 1);// đưa dữ liệu đc chọn vào biến
            int choose = JOptionPane.showConfirmDialog(this, "Xác nhận hủy vé: "+maVe, "Hủy vé", 0);
            if(choose==JOptionPane.OK_OPTION){
                huyVe(maVe);
                JOptionPane.showMessageDialog(this, "Hủy vé: "+maVe+" thành công");
                xLBang.loadDuLieuVaoBang(tbTam, "{call SP_LOAD_VE_TO_JTABLE ()}");
                locDuLieuVeXe(true);
                btnDuyetVe.setEnabled(false);
                btnHuyVe.setEnabled(false);
            }
        }else JOptionPane.showMessageDialog(this, "Hãy chọn vé cần hủy");
        
        
    }//GEN-LAST:event_btnHuyVeActionPerformed

    private void cbbTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTrangThaiItemStateChanged
        // TODO add your handling code here:
        if(cbbTrangThai.getSelectedIndex()>0){
            xLBang.locTatCa(tbVeXe,(String)cbbTrangThai.getSelectedItem(),9);
        }
        else {
            xLBang.locTatCa(tbVeXe,"",9);
        } 
    }//GEN-LAST:event_cbbTrangThaiItemStateChanged

    private void cbbTramDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTramDiItemStateChanged
        // TODO add your handling code here:
        loadComboNgayDi();
        locDuLieuVeXe(true);
    }//GEN-LAST:event_cbbTramDiItemStateChanged

    private void cbbNgayDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNgayDiItemStateChanged
        // TODO add your handling code here
        loadComboGio();
        locDuLieuVeXe(true);
    }//GEN-LAST:event_cbbNgayDiItemStateChanged

    private void cbbGioDiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbGioDiItemStateChanged
        loadComboChuyenXe();
        locDuLieuVeXe(true);
    }//GEN-LAST:event_cbbGioDiItemStateChanged

    private void cbbChuyenXeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbChuyenXeItemStateChanged
        // TODO add your handling code here:
        locDuLieuVeXe(true);
    }//GEN-LAST:event_cbbChuyenXeItemStateChanged

    private void cbbCheDoXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCheDoXemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCheDoXemActionPerformed

    private void cbbCheDoXemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCheDoXemItemStateChanged
        // TODO add your handling code here:
        loadComboNgayDi();
        loadComboGio();
        loadComboChuyenXe();
        locDuLieuVeXe(true);
    }//GEN-LAST:event_cbbCheDoXemItemStateChanged

    private void txtSearchVeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchVeKeyReleased
        // TODO add your handling code here:
        if(!txtSearchVe.getText().isEmpty()){
            locDuLieuVeXe(false);
            xLBang.locTatCa(tbVeXe,txtSearchVe.getText(),-1);
        }
        else {
            locDuLieuVeXe(true);
            xLBang.locTatCa(tbVeXe,"",-1);
            
        }
        
    }//GEN-LAST:event_txtSearchVeKeyReleased

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        // TODO add your handling code here:
        xLBang.loadDuLieuVaoBang(tbTam, "{call SP_LOAD_VE_TO_JTABLE ()}");
        loadComboNgayDi();
        loadComboGio();
        loadComboChuyenXe();
        locDuLieuVeXe(true);
    }//GEN-LAST:event_formHierarchyChanged

    private void tbVeXeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbVeXeFocusLost
        // TODO add your handling code here:
        if(btnDuyetVe.isEnabled() || btnHuyVe.isEnabled()) {return;}
        btnDuyetVe.setEnabled(false);
        btnHuyVe.setEnabled(false);
        tbVeXe.clearSelection();
    }//GEN-LAST:event_tbVeXeFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");   

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
        {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            GhiFileExcel ghiFile= new GhiFileExcel(tbVeXe,"THÔNG TIN VÉ CỦA CHUYẾN XE");
            ghiFile.setFileName(fileToSave.getAbsolutePath());
            String tram=cbbTramDi.getSelectedItem().toString();
            String ngay=cbbNgayDi.getSelectedIndex()!=-1?cbbNgayDi.getSelectedItem().toString():"";
            String gio=cbbGioDi.getSelectedIndex()!=-1?cbbGioDi.getSelectedItem().toString():"";
            String chuyen=cbbChuyenXe.getSelectedIndex()!=-1?cbbChuyenXe.getSelectedItem().toString():"";
            Vector vt1=new Vector();
            vt1.add("Trạm Đi: "+tram);
            vt1.add("Ngày: "+ngay);
            vt1.add("Giờ: "+gio);
            vt1.add("Chuyến xe số: "+chuyen);
            ghiFile.setChiTiet1(vt1);
            Vector vt2=new Vector();
            vt2.add("Nhân viên: "+BanVeXe.hoTen);
            vt2.add("Mã NV: "+BanVeXe.primaryKey);
            ghiFile.setChiTiet2(vt2);
            ghiFile.writeFileExcel();
        }
}
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearText;
    private javax.swing.JButton btnDuyetVe;
    private javax.swing.JButton btnHuyVe;
    private javax.swing.JComboBox<String> cbbCheDoXem;
    private javax.swing.JComboBox<String> cbbChuyenXe;
    private javax.swing.JComboBox<String> cbbGioDi;
    private javax.swing.JComboBox<String> cbbNgayDi;
    private javax.swing.JComboBox<String> cbbTramDi;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbTam;
    private javax.swing.JTable tbVeXe;
    private javax.swing.JTextField txtSearchVe;
    // End of variables declaration//GEN-END:variables
}
