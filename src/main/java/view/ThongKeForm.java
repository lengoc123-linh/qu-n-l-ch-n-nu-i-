package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

import controller.ConvertDate;
import controller.SearchAccount;
import dao.AccountDAO;
import dao.FarmDAO;
import dao.NhaCungCapDAO;
import dao.PhieuNhapDAO;
import dao.PhieuXuatDAO;
import dao.SanPhamDAO;
import dao.SystemActionHistoryDAO;
import dao.ThongKeDAO;
import dao.UserAccessHistoryDAO;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.PhieuNhap;
import java.util.Iterator;
import java.util.Vector;
import model.Account;
import model.Phieu;
import model.PhieuXuat;
import model.ThongKeProduct;

/**
 *
 * @author Admin
 */
public class ThongKeForm extends javax.swing.JInternalFrame {
    
    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
//định nghĩa format ngày tháng và đơn giá
    
    public DecimalFormat getFormatter() {
        return formatter;
    }

    public SimpleDateFormat getFormatDate() {
        return formatDate;
    }

    public ThongKeForm() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        datefilter_phieuFrom.setDateFormatString("dd/MM/yyyy");
        datefilter_phieuTo.setDateFormatString("dd/MM/yyyy");
        dateFrom.setDateFormatString("dd/MM/yyyy");
        dateTo.setDateFormatString("dd/MM/yyyy");
        String sl = Integer.toString(SanPhamDAO.getInstance().getSl());
        txtQuantityProduct.setText(sl);
        txtQuantityNcc.setText(Integer.toString(NhaCungCapDAO.getInstance().selectAll().size()));
        txtQuantityUser.setText(Integer.toString(AccountDAO.getInstance().selectAll().size()));
        txtLogin.setText(Integer.toString(UserAccessHistoryDAO.getInstance().countAccessInLastWeek()));        
        txtSystem.setText(Integer.toString(SystemActionHistoryDAO.getInstance().countActionsInLastWeek()));

        //
        initTable();
        loadDataToTable();
        changeTextFind();
        //
        loadDataToTableAcc(AccountDAO.getInstance().selectAll());
        tab_taikhoan.setDefaultEditor(Object.class, null);
        tblThongKeProduct.setDefaultEditor(Object.class, null);
        tab_phieu.setDefaultEditor(Object.class, null);
        //
        loadDataToTableThongKeProduct(ThongKeDAO.getInstance().getThongKe());
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"Mã phiếu", "Người tạo", "Thời gian tạo", "Tổng tiền"};
        tblModel.setColumnIdentifiers(headerTbl);
        tab_phieu.setModel(tblModel);
        tab_phieu.getColumnModel().getColumn(0).setPreferredWidth(5);
    }

    private void loadDataToTable() {
        try {
            ArrayList<PhieuNhap> allPhieu = PhieuNhapDAO.getInstance().selectAll();
            tblModel.setRowCount(0);
            for (int i = 0; i < allPhieu.size(); i++) {
                tblModel.addRow(new Object[]{
                    allPhieu.get(i).getMaPhieu(), allPhieu.get(i).getNguoiTao(), formatDate.format(allPhieu.get(i).getThoiGianTao()), formatter.format(allPhieu.get(i).getTongTien()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    private void loadDataToTableSearch(ArrayList<Phieu> result) {
        try {
            tblModel.setRowCount(0);
            for (int i = 0; i < result.size(); i++) {
                tblModel.addRow(new Object[]{
                    result.get(i).getMaPhieu(), result.get(i).getNguoiTao(), formatDate.format(result.get(i).getThoiGianTao()), formatter.format(result.get(i).getTongTien()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<Phieu> searchTatCa(String text) {
        ArrayList<Phieu> result = new ArrayList<>();
        ArrayList<Phieu> armt = PhieuNhapDAO.getInstance().selectAllP();
        for (var phieu : armt) {
            if (phieu.getMaPhieu().toLowerCase().contains(text.toLowerCase())
                    || phieu.getNguoiTao().toLowerCase().contains(text.toLowerCase())) {
                result.add(phieu);
            }
        }
        return result;
    }

    public ArrayList<Phieu> searchPhieuNhap(String text) {
        ArrayList<Phieu> result = new ArrayList<>();
        ArrayList<PhieuNhap> armt = PhieuNhapDAO.getInstance().selectAll();
        for (var phieu : armt) {
            if (phieu.getMaPhieu().toLowerCase().contains(text.toLowerCase())
                    || phieu.getNguoiTao().toLowerCase().contains(text.toLowerCase())) {
                result.add(phieu);
            }
        }
        return result;
    }

    public ArrayList<Phieu> searchPhieuXuat(String text) {
        ArrayList<Phieu> result = new ArrayList<>();
        ArrayList<PhieuXuat> armt = PhieuXuatDAO.getInstance().selectAll();
        for (var phieu : armt) {
            if (phieu.getMaPhieu().toLowerCase().contains(text.toLowerCase())
                    || phieu.getNguoiTao().toLowerCase().contains(text.toLowerCase())) {
                result.add(phieu);
            }
        }
        return result;
    }

    public void changeTextFind() {
        search_phieuText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                /* do nothing */
                if (search_phieuText.getText().length() == 0) {
                    loadDataToTable();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabForm = new javax.swing.JTabbedPane();
        spForm = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        findBox = new javax.swing.JPanel();
        searchText = new javax.swing.JTextField();
        spTab = new javax.swing.JScrollPane();
        tblThongKeProduct = new javax.swing.JTable();
        dateFilter = new javax.swing.JPanel();
        dateFrom = new com.toedter.calendar.JDateChooser();
        dateTo = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnResetThongKePr = new javax.swing.JButton();
        phieuForm = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnDetail = new javax.swing.JButton();
        search_phieu = new javax.swing.JPanel();
        search_phieuComboBox = new javax.swing.JComboBox<>();
        search_phieuText = new javax.swing.JTextField();
        refresh_phieuButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_phieu = new javax.swing.JTable();
        date_phieu = new javax.swing.JPanel();
        datefilter_phieuFrom = new com.toedter.calendar.JDateChooser();
        datefilter_phieuTo = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        price_phieu = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pricefilter_phieuFrom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pricefilter_phieuTo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        soLuong = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tongTien = new javax.swing.JLabel();
        taikhoanForm = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        search_taikhoanComboBox = new javax.swing.JComboBox<>();
        search_taikhoanText = new javax.swing.JTextField();
        refresh_taikhoanButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_taikhoan = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        txtQuantityProduct = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtQuantityUser = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtQuantityNcc = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtSystem = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1180, 770));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabForm.setPreferredSize(new java.awt.Dimension(1180, 770));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        findBox.setBackground(new java.awt.Color(255, 255, 255));
        findBox.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        findBox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextActionPerformed(evt);
            }
        });
        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextKeyReleased(evt);
            }
        });
        findBox.add(searchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, 40));

        tblThongKeProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng nhập", "Số lượng xuất"
            }
        ));
        tblThongKeProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongKeProductMouseClicked(evt);
            }
        });
        spTab.setViewportView(tblThongKeProduct);

        dateFilter.setBackground(new java.awt.Color(255, 255, 255));
        dateFilter.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc theo ngày"));
        dateFilter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dateFrom.setBackground(new java.awt.Color(255, 255, 255));
        dateFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateFromPropertyChange(evt);
            }
        });
        dateFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateFromKeyReleased(evt);
            }
        });
        dateFilter.add(dateFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 170, -1));

        dateTo.setBackground(new java.awt.Color(255, 255, 255));
        dateTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateToPropertyChange(evt);
            }
        });
        dateTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateToKeyReleased(evt);
            }
        });
        dateFilter.add(dateTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 170, -1));

        jLabel6.setText("Đến");
        dateFilter.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 40, 20));

        jLabel8.setText("Từ");
        dateFilter.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 20, 20));

        btnResetThongKePr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        btnResetThongKePr.setText("Làm mới");
        btnResetThongKePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetThongKePrActionPerformed(evt);
            }
        });
        dateFilter.add(btnResetThongKePr, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 140, 40));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTab, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(findBox, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(findBox, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(spTab, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout spFormLayout = new javax.swing.GroupLayout(spForm);
        spForm.setLayout(spFormLayout);
        spFormLayout.setHorizontalGroup(
            spFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spFormLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        spFormLayout.setVerticalGroup(
            spFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, spFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(510, 510, 510))
        );

        tabForm.addTab("Sản phẩm", spForm);

        phieuForm.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        jToolBar1.setRollover(true);

        btnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_eye_40px.png"))); // NOI18N
        btnDetail.setText("Xem chi tiết");
        btnDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetail.setFocusable(false);
        btnDetail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDetail.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDetail);

        search_phieu.setBackground(new java.awt.Color(255, 255, 255));
        search_phieu.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        search_phieu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_phieuComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Phiếu Nhập", "Phiếu Xuất" }));
        search_phieuComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_phieuComboBoxActionPerformed(evt);
            }
        });
        search_phieuComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                search_phieuComboBoxPropertyChange(evt);
            }
        });
        search_phieu.add(search_phieuComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 210, 40));

        search_phieuText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_phieuTextKeyReleased(evt);
            }
        });
        search_phieu.add(search_phieuText, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 310, 40));

        refresh_phieuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        refresh_phieuButton.setText("Làm mới");
        refresh_phieuButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh_phieuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_phieuButtonActionPerformed(evt);
            }
        });
        search_phieu.add(refresh_phieuButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 140, 40));

        tab_phieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tab_phieu);

        date_phieu.setBackground(new java.awt.Color(255, 255, 255));
        date_phieu.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc theo ngày"));
        date_phieu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        datefilter_phieuFrom.setBackground(new java.awt.Color(255, 255, 255));
        datefilter_phieuFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datefilter_phieuFromPropertyChange(evt);
            }
        });
        datefilter_phieuFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                datefilter_phieuFromKeyReleased(evt);
            }
        });
        date_phieu.add(datefilter_phieuFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 170, -1));

        datefilter_phieuTo.setBackground(new java.awt.Color(255, 255, 255));
        datefilter_phieuTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datefilter_phieuToPropertyChange(evt);
            }
        });
        datefilter_phieuTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                datefilter_phieuToKeyReleased(evt);
            }
        });
        date_phieu.add(datefilter_phieuTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 170, -1));

        jLabel1.setText("Đến");
        date_phieu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 40, 20));

        jLabel5.setText("Từ");
        date_phieu.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 20, 20));

        price_phieu.setBackground(new java.awt.Color(255, 255, 255));
        price_phieu.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc theo giá"));

        jLabel3.setText("Từ");

        pricefilter_phieuFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricefilter_phieuFromActionPerformed(evt);
            }
        });
        pricefilter_phieuFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pricefilter_phieuFromKeyReleased(evt);
            }
        });

        jLabel4.setText("Đến");

        pricefilter_phieuTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricefilter_phieuToActionPerformed(evt);
            }
        });
        pricefilter_phieuTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pricefilter_phieuToKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout price_phieuLayout = new javax.swing.GroupLayout(price_phieu);
        price_phieu.setLayout(price_phieuLayout);
        price_phieuLayout.setHorizontalGroup(
            price_phieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(price_phieuLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pricefilter_phieuFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addComponent(pricefilter_phieuTo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        price_phieuLayout.setVerticalGroup(
            price_phieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(price_phieuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(price_phieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pricefilter_phieuFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(pricefilter_phieuTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel2.setText("TỔNG TIỀN");

        soLuong.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        soLuong.setText("0");

        jLabel7.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel7.setText("TỔNG PHIẾU ");

        tongTien.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        tongTien.setForeground(new java.awt.Color(255, 0, 51));
        tongTien.setText("0");

        javax.swing.GroupLayout phieuFormLayout = new javax.swing.GroupLayout(phieuForm);
        phieuForm.setLayout(phieuFormLayout);
        phieuFormLayout.setHorizontalGroup(
            phieuFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phieuFormLayout.createSequentialGroup()
                .addGroup(phieuFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(phieuFormLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(phieuFormLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(phieuFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(phieuFormLayout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(search_phieu, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
                            .addGroup(phieuFormLayout.createSequentialGroup()
                                .addComponent(date_phieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(price_phieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(phieuFormLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        phieuFormLayout.setVerticalGroup(
            phieuFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phieuFormLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(phieuFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(search_phieu, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(phieuFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date_phieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(price_phieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(phieuFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(203, 203, 203))
        );

        tabForm.addTab("Phiếu", phieuForm);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_taikhoanComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "FullName", "UserName", "Role" }));
        search_taikhoanComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_taikhoanComboBoxActionPerformed(evt);
            }
        });
        search_taikhoanComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                search_taikhoanComboBoxPropertyChange(evt);
            }
        });
        jPanel7.add(search_taikhoanComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 210, 40));

        search_taikhoanText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_taikhoanTextKeyReleased(evt);
            }
        });
        jPanel7.add(search_taikhoanText, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 320, 40));

        refresh_taikhoanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        refresh_taikhoanButton.setText("Làm mới");
        refresh_taikhoanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_taikhoanButtonActionPerformed(evt);
            }
        });
        jPanel7.add(refresh_taikhoanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 160, 40));

        tab_taikhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Họ và tên", "Email", "Tên người dùng", "Vai trò", "Tình trạng"
            }
        ));
        tab_taikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_taikhoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_taikhoan);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1163, Short.MAX_VALUE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout taikhoanFormLayout = new javax.swing.GroupLayout(taikhoanForm);
        taikhoanForm.setLayout(taikhoanFormLayout);
        taikhoanFormLayout.setHorizontalGroup(
            taikhoanFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taikhoanFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        taikhoanFormLayout.setVerticalGroup(
            taikhoanFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taikhoanFormLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(510, 510, 510))
        );

        tabForm.addTab("Tài khoản", taikhoanForm);

        getContentPane().add(tabForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, 620));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 204, 0));

        txtQuantityProduct.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtQuantityProduct.setForeground(new java.awt.Color(255, 255, 255));
        txtQuantityProduct.setText("100");

        jLabel10.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Thức ăn trong kho");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-monitor-80.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantityProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(txtQuantityProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap())))
        );

        jPanel11.setBackground(new java.awt.Color(0, 204, 204));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-account-80.png"))); // NOI18N

        txtQuantityUser.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtQuantityUser.setForeground(new java.awt.Color(255, 255, 255));
        txtQuantityUser.setText("100");

        jLabel17.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tài khoản người dùng");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantityUser, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtQuantityUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 102, 0));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-supplier-80.png"))); // NOI18N

        txtQuantityNcc.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtQuantityNcc.setForeground(new java.awt.Color(255, 255, 255));
        txtQuantityNcc.setText("100");

        jLabel14.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nhà cung cấp ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantityNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtQuantityNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14)))
                .addGap(10, 10, 10))
        );

        jPanel12.setBackground(new java.awt.Color(0, 204, 204));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-account-80.png"))); // NOI18N

        txtLogin.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(255, 255, 255));
        txtLogin.setText("100");

        jLabel18.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Truy cập 1 tuần qua");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(0, 204, 204));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-account-80.png"))); // NOI18N

        txtSystem.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtSystem.setForeground(new java.awt.Color(255, 255, 255));
        txtSystem.setText("100");

        jLabel20.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Tác vụ ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20))
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void refresh_phieuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_phieuButtonActionPerformed
        // TODO add your handling code here:
        loadDataToTable();
        search_phieuComboBox.setSelectedIndex(0);
        search_phieuText.setText("");
        datefilter_phieuFrom.setCalendar(null);
        datefilter_phieuTo.setCalendar(null);
        pricefilter_phieuTo.setText("");
        pricefilter_phieuFrom.setText("");
    }//GEN-LAST:event_refresh_phieuButtonActionPerformed

    private void pricefilter_phieuToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricefilter_phieuToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pricefilter_phieuToActionPerformed

    private void search_phieuComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_phieuComboBoxActionPerformed
        try {
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_search_phieuComboBoxActionPerformed

    private void search_phieuTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_phieuTextKeyReleased
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_search_phieuTextKeyReleased

    private void pricefilter_phieuFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricefilter_phieuFromActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_pricefilter_phieuFromActionPerformed

    private void pricefilter_phieuFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pricefilter_phieuFromKeyReleased
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pricefilter_phieuFromKeyReleased

    private void datefilter_phieuFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datefilter_phieuFromKeyReleased
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_datefilter_phieuFromKeyReleased

    private void datefilter_phieuToKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datefilter_phieuToKeyReleased
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_datefilter_phieuToKeyReleased

    private void pricefilter_phieuToKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pricefilter_phieuToKeyReleased
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pricefilter_phieuToKeyReleased

    private void datefilter_phieuFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datefilter_phieuFromPropertyChange
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_datefilter_phieuFromPropertyChange

    private void datefilter_phieuToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datefilter_phieuToPropertyChange
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_datefilter_phieuToPropertyChange

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:
        if (tab_phieu.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu !");
        } else {
            CTThongKe a = new CTThongKe(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            a.setVisible(true);
        }
    }//GEN-LAST:event_btnDetailActionPerformed

    private void search_phieuComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_search_phieuComboBoxPropertyChange


    }//GEN-LAST:event_search_phieuComboBoxPropertyChange

    private void search_taikhoanComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_taikhoanComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_taikhoanComboBoxActionPerformed

    private void search_taikhoanComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_search_taikhoanComboBoxPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_search_taikhoanComboBoxPropertyChange

    private void search_taikhoanTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_taikhoanTextKeyReleased
        // TODO add your handling code here:
        String luachon = (String) search_taikhoanComboBox.getSelectedItem();
        String searchContent = search_taikhoanText.getText();
        ArrayList<Account> result = new ArrayList<>();
        switch (luachon) {
            case "Tất cả":
                result = SearchAccount.getInstance().searchTatCaAcc(searchContent);
                break;
            case "Tên tài khoản":
                result = SearchAccount.getInstance().searchFullName(searchContent);
                break;
            case "Tên đăng nhập":
                result = SearchAccount.getInstance().searchUserName(searchContent);
                break;
            case "Vai trò":
                result = SearchAccount.getInstance().searchRole(searchContent);
                break;
        }
        loadDataToTableAcc(result);
    }//GEN-LAST:event_search_taikhoanTextKeyReleased

    private void tab_taikhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_taikhoanMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            DefaultTableModel tblAcc = (DefaultTableModel) tab_taikhoan.getModel();
            if (tab_taikhoan.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản");
            } else {
                CTThongKeAcc tk;
                tk = new CTThongKeAcc(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
                tk.setVisible(true);
            }
        }
    }//GEN-LAST:event_tab_taikhoanMouseClicked

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyReleased
        try {
            // TODO add your handling code here:
            filterThongKeSanPham();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchTextKeyReleased

    private void tblThongKeProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongKeProductMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblThongKeProductMouseClicked

    private void dateFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateFromPropertyChange
        try {
            // TODO add your handling code here:
            filterThongKeSanPham();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dateFromPropertyChange

    private void dateFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateFromKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFromKeyReleased

    private void dateToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateToPropertyChange
        try {
            // TODO add your handling code here:
            filterThongKeSanPham();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dateToPropertyChange

    private void dateToKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateToKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dateToKeyReleased

    private void btnResetThongKePrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetThongKePrActionPerformed
        // TODO add your handling code here:
        searchText.setText("");
        dateFrom.setCalendar(null);
        dateTo.setCalendar(null);
        loadDataToTableThongKeProduct(ThongKeDAO.getInstance().getThongKe());
    }//GEN-LAST:event_btnResetThongKePrActionPerformed

    private void refresh_taikhoanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_taikhoanButtonActionPerformed
        // TODO add your handling code here:
        search_taikhoanComboBox.setSelectedIndex(0);
        search_taikhoanText.setText("");
        loadDataToTableAcc(AccountDAO.getInstance().selectAll());
    }//GEN-LAST:event_refresh_taikhoanButtonActionPerformed

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextActionPerformed

    public Phieu getPhieuNhapSelect() {
        int i_row = tab_phieu.getSelectedRow();
        Phieu pn = PhieuNhapDAO.getInstance().selectById(tblModel.getValueAt(i_row, 1).toString());
        return pn;
    }

    public Phieu findPhieu() {
        int i_row = tab_phieu.getSelectedRow();
        String text = tblModel.getValueAt(i_row, 0).toString();
        ArrayList<Phieu> phieuAll = PhieuNhapDAO.getInstance().selectAllP();
        Phieu phieuk = null;
        for (Phieu phieu : phieuAll) {
            if (phieu.getMaPhieu().equals(text)) {
                return phieu;
            }
        }
        return phieuk;
    }

    public boolean checkDate(Date dateTest, Date star, Date end) {
        return dateTest.getTime() >= star.getTime() && dateTest.getTime() <= end.getTime();
    }

    public ArrayList<PhieuNhap> searchDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ArrayList<PhieuNhap> result = new ArrayList<PhieuNhap>();
        Date from = datefilter_phieuFrom.getDate();
        Date to = datefilter_phieuTo.getDate();
        ArrayList<PhieuNhap> armt = PhieuNhapDAO.getInstance().selectAll();
        for (var phieu : armt) {
            if (checkDate(phieu.getThoiGianTao(), from, to)) {
                result.add(phieu);
            }

        }
        return result;
    }

    public void loadDataToTableAcc(ArrayList<Account> acc) {
        try {
            DefaultTableModel tblModelAcc = (DefaultTableModel) tab_taikhoan.getModel();
            tblModelAcc.setRowCount(0);
            for (Account i : acc) {
                tblModelAcc.addRow(new Object[]{
                    i.getFullName(), i.getEmail(), i.getUser(), i.getRole(), i.getStatus() == 1 ? "Hoạt động" : "Đã khóa"
                });
            }
        } catch (Exception e) {
        }
    }

    public String getMaAcc() {
        return tab_taikhoan.getValueAt(tab_taikhoan.getSelectedRow(), 2).toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnResetThongKePr;
    private javax.swing.JPanel dateFilter;
    private com.toedter.calendar.JDateChooser dateFrom;
    private com.toedter.calendar.JDateChooser dateTo;
    private javax.swing.JPanel date_phieu;
    private com.toedter.calendar.JDateChooser datefilter_phieuFrom;
    private com.toedter.calendar.JDateChooser datefilter_phieuTo;
    private javax.swing.JPanel findBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel phieuForm;
    private javax.swing.JPanel price_phieu;
    private javax.swing.JTextField pricefilter_phieuFrom;
    private javax.swing.JTextField pricefilter_phieuTo;
    private javax.swing.JButton refresh_phieuButton;
    private javax.swing.JButton refresh_taikhoanButton;
    private javax.swing.JTextField searchText;
    private javax.swing.JPanel search_phieu;
    private javax.swing.JComboBox<String> search_phieuComboBox;
    private javax.swing.JTextField search_phieuText;
    private javax.swing.JComboBox<String> search_taikhoanComboBox;
    private javax.swing.JTextField search_taikhoanText;
    private javax.swing.JLabel soLuong;
    private javax.swing.JPanel spForm;
    private javax.swing.JScrollPane spTab;
    private javax.swing.JTabbedPane tabForm;
    private javax.swing.JTable tab_phieu;
    private javax.swing.JTable tab_taikhoan;
    private javax.swing.JPanel taikhoanForm;
    private javax.swing.JTable tblThongKeProduct;
    private javax.swing.JLabel tongTien;
    private javax.swing.JLabel txtLogin;
    private javax.swing.JLabel txtQuantityNcc;
    private javax.swing.JLabel txtQuantityProduct;
    private javax.swing.JLabel txtQuantityUser;
    private javax.swing.JLabel txtSystem;
    // End of variables declaration//GEN-END:variables

    public void searchAllRepect() throws ParseException {
        String luaChon = search_phieuComboBox.getSelectedItem().toString();
        String content = search_phieuText.getText();
        ArrayList<Phieu> result = null;

        switch (luaChon) {
            case "Tất cả":
                result = searchTatCa(content);
                break;
            case "Phiếu Nhập":
                result = searchPhieuNhap(content);
                break;
            case "Phiếu Xuất":
                result = searchPhieuXuat(content);
                break;
        }

        Iterator<Phieu> itr = result.iterator();
        if (datefilter_phieuFrom.getDate() != null || datefilter_phieuTo.getDate() != null) {
            Date from;
            Date to;
            if (datefilter_phieuFrom.getDate() != null && datefilter_phieuTo.getDate() == null) {
                from = ConvertDate.getInstance().ChangeFrom(datefilter_phieuFrom.getDate());
                to = ConvertDate.getInstance().ChangeTo(new Date());
                while (itr.hasNext()) {
                    Phieu phieu = itr.next();
                    if (!checkDate(phieu.getThoiGianTao(), from, to)) {
                        itr.remove();
                    }
                }
            } else if (datefilter_phieuTo.getDate() != null && datefilter_phieuFrom.getDate() == null) {
                String sDate1 = "01/01/2022";
                from = ConvertDate.getInstance().ChangeFrom(new SimpleDateFormat("dd/MM/yyyy").parse(sDate1));
                to = ConvertDate.getInstance().ChangeTo(datefilter_phieuTo.getDate());
                while (itr.hasNext()) {
                    Phieu phieu = itr.next();
                    if (!checkDate(phieu.getThoiGianTao(), from, to)) {
                        itr.remove();
                    }
                }
            } else {
                from = ConvertDate.getInstance().ChangeFrom(datefilter_phieuFrom.getDate());
                to = ConvertDate.getInstance().ChangeTo(datefilter_phieuTo.getDate());
                while (itr.hasNext()) {
                    Phieu phieu = itr.next();
                    if (!checkDate(phieu.getThoiGianTao(), from, to)) {
                        itr.remove();
                    }
                }
            }
        }

        ArrayList<Phieu> result1 = new ArrayList<>();
        if (pricefilter_phieuFrom.getText().length() > 0 || pricefilter_phieuTo.getText().length() > 0) {
            double a;
            double b;

            if (pricefilter_phieuFrom.getText().length() > 0 && pricefilter_phieuTo.getText().length() == 0) {
                a = Double.parseDouble(pricefilter_phieuFrom.getText());
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getTongTien() >= a) {
                        result1.add(result.get(i));
                    }
                }
            } else if (pricefilter_phieuFrom.getText().length() == 0 && pricefilter_phieuTo.getText().length() > 0) {;
                b = Double.parseDouble(pricefilter_phieuTo.getText());
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getTongTien() <= b) {
                        result1.add(result.get(i));
                    }
                }
            } else if (pricefilter_phieuFrom.getText().length() > 0 && pricefilter_phieuTo.getText().length() > 0) {
                a = Double.parseDouble(pricefilter_phieuFrom.getText());
                b = Double.parseDouble(pricefilter_phieuTo.getText());
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getTongTien() >= a && result.get(i).getTongTien() <= b) {
                        result1.add(result.get(i));
                    }
                }
            }
        }
        if (pricefilter_phieuFrom.getText().length() > 0 || pricefilter_phieuTo.getText().length() > 0) {
            loadDataToTableSearch(result1);
            double sum = 0;
            for (Phieu phieu : result1) {
                sum += phieu.getTongTien();
            }
            soLuong.setText(result1.size() + "");
            tongTien.setText(formatter.format(sum) + "đ");
        } else {
            loadDataToTableSearch(result);
            double sum = 0;
            for (Phieu phieu : result) {
                sum += phieu.getTongTien();
            }
            soLuong.setText(result.size() + "");
            tongTien.setText(formatter.format(sum) + "đ");
        }
    }

    public void filterThongKeSanPham() throws ParseException {
        ArrayList<ThongKeProduct> thongKe = new ArrayList<>();
        if (dateFrom.getDate() != null || dateTo.getDate() != null) {
            Date from = new Date();
            Date to = new Date();
            if (dateFrom.getDate() != null && dateTo.getDate() == null) {
                from = dateFrom.getDate();
                to = new Date();
            } else if (dateTo.getDate() != null && dateFrom.getDate() == null) {
                String sDate1 = "01/01/2022";
                from = ConvertDate.getInstance().ChangeFrom(new SimpleDateFormat("dd/MM/yyyy").parse(sDate1));
                to = dateTo.getDate();
            } else {
                from = dateFrom.getDate();
                to = dateTo.getDate();
                if (from.getTime() > to.getTime()) {
                    JOptionPane.showMessageDialog(this, "Thời gian không hợp lệ !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    datefilter_phieuFrom.setCalendar(null);
                    datefilter_phieuTo.setCalendar(null);
                }
            }
            thongKe = ThongKeDAO.getInstance().getThongKe(from, to);

        } else {
            thongKe = ThongKeDAO.getInstance().getThongKe();
        }
        if (!searchText.getText().equals("")) {
            thongKe = searchTenSanPhamThongKe(thongKe, searchText.getText());
        }
        loadDataToTableThongKeProduct(thongKe);
    }

    private void loadDataToTableThongKeProduct(ArrayList<ThongKeProduct> thongKe) {
        try {
            DefaultTableModel tblModelAcc = (DefaultTableModel) tblThongKeProduct.getModel();
            tblModelAcc.setRowCount(0);
            for (int i = 0; i < thongKe.size(); i++) {
                tblModelAcc.addRow(new Object[]{
                    (i + 1), thongKe.get(i).getMaSP(), thongKe.get(i).getTenSP(), thongKe.get(i).getSlNhap(), thongKe.get(i).getSlXuat()
                });
            }
            tblThongKeProduct.getColumnModel().getColumn(2).setPreferredWidth(400);
        } catch (Exception e) {
        }
    }

    private ArrayList<ThongKeProduct> searchTenSanPhamThongKe(ArrayList<ThongKeProduct> arr, String name) {
        ArrayList<ThongKeProduct> result = new ArrayList<>();
        for (ThongKeProduct i : arr) {
            if (i.getMaSP().toLowerCase().contains(name.toLowerCase()) || i.getTenSP().toLowerCase().contains(name.toLowerCase())) {
                result.add(i);
            }
        }
        return result;
    }
}
