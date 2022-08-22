/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.ui;

import com.poly.dao.DiemDAO;
import com.poly.dao.KhoaDAO;
import com.poly.dao.LopDAO;
import com.poly.dao.MonHocDAO;
import com.poly.dao.SinhVienDAO;
import com.poly.model.Diem;
import com.poly.model.khoa;
import com.poly.model.monhoc;
import com.poly.model.sinhvien;
import com.poly.utils.DialogHelPer;
import java.awt.peer.DialogPeer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class DiemSinhVien extends javax.swing.JPanel {

    KhoaDAO khoadao = new KhoaDAO();
    LopDAO lopdao = new LopDAO();
    MonHocDAO monhocdao = new MonHocDAO();
    DiemDAO kqdao = new DiemDAO();
    int row = 0;

    /**
     * Creates new form DiemSinhVien
     */
    public DiemSinhVien() {
        initComponents();
        init();
    }

    void init() {
        fillComboBoxKhoa();
        load();
//        fillComboBoxMaLop();
//       fillComboBoxMaMonHoc();
    }

    void fillComboBoxKhoa() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhoa.getModel();
        model.removeAllElements();
        List<khoa> list = khoadao.selectAll();
        for (khoa k : list) {
            model.addElement(k);
        }
        this.fillComboBoxMonHoc();
//        this.fillComboBoxLop();
    }

    void fillComboBoxMonHoc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMonHoc.getModel();
        model.removeAllElements();
        khoa kh = (khoa) cboKhoa.getSelectedItem();
        if (kh != null) {
            List<monhoc> list = monhocdao.selectByKhoaM(kh.getMaKhoa());
            for (monhoc mh : list) {
                model.addElement(mh);
            }
        }
    }
//    void fillComboBoxLop() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLop.getModel();
//        model.removeAllElements();
//        Lop lop = (Lop) cboKhoa.getSelectedItem();
//        if (lop != null) {
//            List<Lop> list = lopdao.selectByKhoaL(lop.getMaKhoa());
//            for (Lop l : list) {
//                model.addElement(l);
//            }
//        }
//    }

    void edit() {
        String model = (String) tblDiemSV.getValueAt(this.row, 0);
        Diem kq = kqdao.selectById(model);
        this.setModel(kq);
        jTabbedPane1.setSelectedIndex(0);
//        this.updateStatus();
    }

    void setModel(Diem kq) {
        cboMonHoc.setSelectedItem(kq.getMaMH());
//        cboLop.setSelectedItem(kq.getMaLop());
        txtLop.setText(kq.getMaLop());
        cboHocKi.setSelectedItem(kq.getHocKi());
        txtMaSV.setText(kq.getMaSV());
        txtHovaTen.setText(kq.getHoTen());
        txtDiemThi.setText(kq.getDiemThi() + "");
        txtDiemTB.setText(kq.getDiemThi() + "");
        txtXepLoai.setText(kq.getXepLoai());
        txtGhiChu.setText(kq.getGhiChu());
    }

    Diem getModel() {
        Diem kq = new Diem();
        kq.setMaMH(cboMonHoc.getSelectedItem().toString());
//        kq.setHocKi(cboHocKi.getSelectedItem().toString());
        kq.setHocKi(Integer.parseInt((String) cboHocKi.getSelectedItem()));
        kq.setMaLop(txtLop.getText());
        kq.setMaSV(txtMaSV.getText());
        kq.setHoTen(txtHovaTen.getText());
        kq.setDiemThi(Float.parseFloat(txtDiemThi.getText()));
        kq.setDiemTB(Float.parseFloat(txtDiemTB.getText()));
        kq.setXepLoai(txtXepLoai.getText());
        kq.setGhiChu(txtGhiChu.getText());
        return kq;
    }

    void clearfrom() {
        cboMonHoc.setSelectedIndex(0);
        txtLop.setText("");
        cboHocKi.setSelectedIndex(0);
        txtHovaTen.setText("");
        txtMaSV.setText("");
        txtDiemThi.setText("");
        txtDiemTB.setText("");
        txtXepLoai.setText("");
        txtGhiChu.setText("");
        row = -1;
//        updateStatus();
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblDiemSV.getModel();
        model.setRowCount(0);
        List<Diem> list = kqdao.selectAll();
        for (Diem kq : list) {
            Object[] row = {
                kq.getMaSV(),
                kq.getHoTen(),
                kq.getMaLop(),
                kq.getMaMH(),
                kq.getHocKi(),
                kq.getDiemThi(),
                kq.getDiemTB(),
                kq.getXepLoai(),
                kq.getGhiChu(),};
            model.addRow(row);
        }
    }

    public void first() {
        row = 0;
        edit();
    }

    public void prev() {
        if (row > 0) {
            row--;
            edit();
        }
    }

    public void next() {
        if (row < tblDiemSV.getRowCount() - 1) {
            row++;
            edit();
        }
    }

    public void last() {
        row = tblDiemSV.getRowCount() - 1;
        edit();
    }

    void insert() {
        Diem kq = getModel();
        try {
            kqdao.insert(kq);
            load();
            clearfrom();
            DialogHelPer.alert(this, "Đã thêm mới thành công");
        } catch (Exception e) {
            DialogHelPer.alert(this, "Thêm mới thất bại");
            e.printStackTrace();
        }
    }

    void delete() {
        if (DialogHelPer.confirm(this, "Bạn thực sự muốn xóa khoa này?")) {
            String model = txtMaSV.getText();
            try {
                kqdao.delete(model);
                this.load();
                this.clearfrom();
                DialogHelPer.alert(this, "Xóa lớp thành công!");
            } catch (Exception e) {
                DialogHelPer.alert(this, "Xóa lớp thất bại!");
                e.printStackTrace();
            }
        }
    }

    void update() {
        Diem model = getModel();
        try {
            kqdao.update(model);
            load();
            DialogHelPer.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            DialogHelPer.alert(this, "Cập nhật thất bại");
            e.printStackTrace();
        }
    }

//    public void fillComboBoxMaLop() {
//        Connection conn = null;
//        Statement state = null;
//        String url ="jdbc:sqlserver://localhost;databaseName=QLSV;user=sa;password=songlong"; ;
//        
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLop.getModel();
//        model.removeAllElements();
//        try {
//            System.out.println("Connect to database.....");
//            conn = DriverManager.getConnection(url);
//            state = conn.createStatement();
//            String sql = "select Lop.MaLop from Lop";
//            ResultSet rs = state.executeQuery(sql);                
//            while (rs.next()) {
//                cboLop.addItem(rs.getString("MaLop"));
//            }
//            
//            rs.close();
//            state.close();
//            conn.close();
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//    }
    public void fillComboBoxMaMonHoc() {
        Connection conn = null;
        Statement state = null;
        String url = "jdbc:sqlserver://localhost;databaseName=QLSV_PRO1041;user=sa;password=123";;

        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMonHoc.getModel();
        model.removeAllElements();
        try {
            System.out.println("Connect to database.....");
            conn = DriverManager.getConnection(url);
            state = conn.createStatement();
            String sql = "select MonHoc.MaMH from MonHoc";
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                cboMonHoc.addItem(rs.getString("MaMH"));
            }

            rs.close();
            state.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private void HienThi() {
        try {
            SinhVienDAO dao = new SinhVienDAO();
            sinhvien sv = dao.findById(txtMaSV.getText());
            if (sv != null) {
                txtLop.setText(sv.getMaLop());
                txtHovaTen.setText(sv.getHoTen());
            }
        } catch (Exception e) {
        }
    }

    void XepLoai() {
        try {
            String diemThi = txtDiemThi.getText();
            String diemTB = txtDiemTB.getText();

            int dt = Integer.parseInt(diemThi);
            int dtb = Integer.parseInt(diemTB);

            if (dt < 5) {
                txtGhiChu.setText("Thi Lại");
            } else {
                txtGhiChu.setText("");
            }

            if (dtb > 9) {
                txtXepLoai.setText("Xuat Sac");
            } else if (dtb >= 8) {
                txtXepLoai.setText("Gioi");
            } else if (dtb >= 6.5) {
                txtXepLoai.setText("Kha");
            } else if (dtb > 5) {
                txtXepLoai.setText("Trung Binh");
            } else {
                txtXepLoai.setText("Yeu");
            }
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboKhoa = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cboHocKi = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboMonHoc = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtHovaTen = new javax.swing.JTextField();
        txtDiemThi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDiemTB = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtXepLoai = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        txtLop = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnprev = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiemSV = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Khoa: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Lớp:");

        cboKhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhoaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Học Kì:");

        cboHocKi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", " ", " " }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Môn Học:");

        cboMonHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Mã Sinh Viên:");

        txtMaSV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaSVFocusLost(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Họ Và Tên:");

        txtHovaTen.setEditable(false);

        txtDiemThi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiemThiFocusLost(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Điểm Thi:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Điểm TB:");

        txtDiemTB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiemTBFocusLost(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Ghi Chú:");

        txtXepLoai.setEditable(false);

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Xếp Loại:");

        txtLop.setEditable(false);

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icons/plus-thick.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icons/draw-pen.png"))); // NOI18N
        btnSua.setText("Sữa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icons/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icons/autorenew.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnFirst.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnprev.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnprev.setText("<<");
        btnprev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprevActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtXepLoai)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtHovaTen, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMaSV, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboKhoa, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addComponent(jLabel12)
                        .addComponent(txtLop))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnprev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(cboMonHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboHocKi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiemThi, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtDiemTB, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboHocKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiemThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnMoi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHovaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiemTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtXepLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFirst)
                            .addComponent(btnprev)
                            .addComponent(btnNext)
                            .addComponent(btnLast)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cập Nhật", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblDiemSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sinh Viên", "Họ và Tên", "Mã Lớp", "Mã Môn Học", "Học Kì", "Điểm Thi", "Điểm Trung Bình", "Xếp Loại ", "Ghi Chú"
            }
        ));
        tblDiemSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDiemSVMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDiemSV);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Danh Sách", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnprevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprevActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnprevActionPerformed

    private void tblDiemSVMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiemSVMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblDiemSV.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblDiemSVMousePressed

    private void cboKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhoaActionPerformed
        // TODO add your handling code here:
        fillComboBoxMonHoc();
    }//GEN-LAST:event_cboKhoaActionPerformed

    private void txtMaSVFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaSVFocusLost
        // TODO add your handling code here:
        HienThi();
    }//GEN-LAST:event_txtMaSVFocusLost

    private void txtDiemThiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiemThiFocusLost
        // TODO add your handling code here:
        XepLoai();
    }//GEN-LAST:event_txtDiemThiFocusLost

    private void txtDiemTBFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiemTBFocusLost
        // TODO add your handling code here:
        XepLoai();
    }//GEN-LAST:event_txtDiemTBFocusLost

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearfrom();
    }//GEN-LAST:event_btnMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnprev;
    private javax.swing.JComboBox<String> cboHocKi;
    private javax.swing.JComboBox<String> cboKhoa;
    private javax.swing.JComboBox<String> cboMonHoc;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDiemSV;
    private javax.swing.JTextField txtDiemTB;
    private javax.swing.JTextField txtDiemThi;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHovaTen;
    private javax.swing.JTextField txtLop;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtXepLoai;
    // End of variables declaration//GEN-END:variables
}
