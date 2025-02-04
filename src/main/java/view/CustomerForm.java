/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import controller.SearchCustomer;
import controller.SearchProduct;
import dao.CustomerDAO;
import dao.SanPhamDAO;
import dao.SanPhamDAO;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.Customer;
import model.SanPham;
import model.SanPham;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class CustomerForm extends javax.swing.JInternalFrame {

    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public CustomerForm() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);  // Ẩn thanh tiêu đề
        tblSanPham.setDefaultEditor(Object.class, null);  // Không cho phép chỉnh sửa trực tiếp trên bảng
        initTable();
        loadDataToTable();
        changeTextFind();
    }

    public void checkRole(Account t) {
        if (t.getRole().equals("Nhân viên nhập") || t.getRole().equals("Nhân viên xuất")) {
            add_ProductFormBtn.setEnabled(false);
            delete_ProductFormBtn.setEnabled(false);
            edit_ProductFormBtn.setEnabled(false);
            exportexcel_ProductFormBtn.setEnabled(false);
        } else {
            System.out.println("abcdjad");
        }
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        // Thay đổi các cột bảng cho phù hợp với dữ liệu khách hàng
        String[] headerTbl = new String[]{"ID khách hàng", "Tên khách hàng", "Email", "Số điện thoại", "Địa chỉ"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblSanPham.setModel(tblModel);  // Giữ nguyên tên bảng tblSanPham
        tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblSanPham.getColumnModel().getColumn(4).setPreferredWidth(200);
    }

    // Tải dữ liệu khách hàng từ database và hiển thị lên bảng
    public void loadDataToTable() {
        try {
            CustomerDAO customerDAO = new CustomerDAO();  // Sử dụng CustomerDAO để lấy dữ liệu khách hàng
            ArrayList<Customer> customers = customerDAO.selectAll();  // Lấy danh sách khách hàng từ database
            tblModel.setRowCount(0);  // Xóa dữ liệu cũ trong bảng
            for (Customer customer : customers) {
                tblModel.addRow(new Object[]{
                    customer.getId(), 
                    customer.getFullName(), 
                    customer.getEmail(), 
                    customer.getPhone(), 
                    customer.getAddress()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        ToolBar = new javax.swing.JToolBar();
        add_ProductFormBtn = new javax.swing.JButton();
        delete_ProductFormBtn = new javax.swing.JButton();
        edit_ProductFormBtn = new javax.swing.JButton();
        detail_ProductFormBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        exportexcel_ProductFormBtn = new javax.swing.JButton();
        searchbar = new javax.swing.JPanel();
        search_ProductFormComboBox = new javax.swing.JComboBox<>();
        search_ProductFormText = new javax.swing.JTextField();
        refresh_ProductFromBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        ToolBar.setBackground(new java.awt.Color(255, 255, 255));
        ToolBar.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        ToolBar.setRollover(true);

        add_ProductFormBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_add_40px.png"))); // NOI18N
        add_ProductFormBtn.setText("Thêm");
        add_ProductFormBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add_ProductFormBtn.setFocusable(false);
        add_ProductFormBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add_ProductFormBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add_ProductFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_ProductFormBtnActionPerformed(evt);
            }
        });
        ToolBar.add(add_ProductFormBtn);

        delete_ProductFormBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_40px.png"))); // NOI18N
        delete_ProductFormBtn.setText("Xoá");
        delete_ProductFormBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        delete_ProductFormBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        delete_ProductFormBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        delete_ProductFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_ProductFormBtnActionPerformed(evt);
            }
        });
        ToolBar.add(delete_ProductFormBtn);

        edit_ProductFormBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_edit_40px.png"))); // NOI18N
        edit_ProductFormBtn.setText("Sửa");
        edit_ProductFormBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        edit_ProductFormBtn.setFocusable(false);
        edit_ProductFormBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        edit_ProductFormBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        edit_ProductFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_ProductFormBtnActionPerformed(evt);
            }
        });
        ToolBar.add(edit_ProductFormBtn);

        detail_ProductFormBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_eye_40px.png"))); // NOI18N
        detail_ProductFormBtn.setText("Xem chi tiết");
        detail_ProductFormBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        detail_ProductFormBtn.setFocusable(false);
        detail_ProductFormBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        detail_ProductFormBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        detail_ProductFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detail_ProductFormBtnActionPerformed(evt);
            }
        });
        ToolBar.add(detail_ProductFormBtn);
        ToolBar.add(jSeparator1);

        exportexcel_ProductFormBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_spreadsheet_file_40px.png"))); // NOI18N
        exportexcel_ProductFormBtn.setText("Xuất Excel");
        exportexcel_ProductFormBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportexcel_ProductFormBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportexcel_ProductFormBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exportexcel_ProductFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportexcel_ProductFormBtnActionPerformed(evt);
            }
        });
        ToolBar.add(exportexcel_ProductFormBtn);

        searchbar.setBackground(new java.awt.Color(255, 255, 255));
        searchbar.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        searchbar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_ProductFormComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã máy", "Tên máy", "Số lượng", "Đơn giá", "RAM", "CPU", "Dung lượng", "Card màn hình", "Xuất xứ", "Đã xóa" }));
        search_ProductFormComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_ProductFormComboBoxActionPerformed(evt);
            }
        });
        search_ProductFormComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                search_ProductFormComboBoxPropertyChange(evt);
            }
        });
        searchbar.add(search_ProductFormComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 40));

        search_ProductFormText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_ProductFormTextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_ProductFormTextKeyReleased(evt);
            }
        });
        searchbar.add(search_ProductFormText, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 360, 40));

        refresh_ProductFromBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        refresh_ProductFromBtn.setText("Làm mới");
        refresh_ProductFromBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh_ProductFromBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_ProductFromBtnActionPerformed(evt);
            }
        });
        searchbar.add(refresh_ProductFromBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 140, 40));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchbar, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(ToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_ProductFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_ProductFormBtnActionPerformed
        AddCustomer a = new AddCustomer(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
        a.setVisible(true);

    }//GEN-LAST:event_add_ProductFormBtnActionPerformed

    private void delete_ProductFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_ProductFormBtnActionPerformed
        if (tblSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xoá");
        } else {
            xoaKhachHangSelect();
        }
    }//GEN-LAST:event_delete_ProductFormBtnActionPerformed

    private void edit_ProductFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_ProductFormBtnActionPerformed
            if (tblSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa");
        } else {
            UpdateCustomer a = new UpdateCustomer(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            a.setVisible(true);
        }
    }//GEN-LAST:event_edit_ProductFormBtnActionPerformed

    private void exportexcel_ProductFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportexcel_ProductFormBtnActionPerformed
        // TODO add your handling code here:
        try {
         JFileChooser jFileChooser = new JFileChooser();
         jFileChooser.showSaveDialog(this);
         File saveFile = jFileChooser.getSelectedFile();
         if (saveFile != null) {
             saveFile = new File(saveFile.toString() + ".xlsx");
             Workbook wb = new XSSFWorkbook();
             Sheet sheet = wb.createSheet("Customer");

             Row rowCol = sheet.createRow(0);
             for (int i = 0; i < tblSanPham.getColumnCount(); i++) {
                 Cell cell = rowCol.createCell(i);
                 cell.setCellValue(tblSanPham.getColumnName(i));
             }

             for (int j = 0; j < tblSanPham.getRowCount(); j++) {
                 Row row = sheet.createRow(j + 1);
                 for (int k = 0; k < tblSanPham.getColumnCount(); k++) {
                     Cell cell = row.createCell(k);
                     if (tblSanPham.getValueAt(j, k) != null) {
                         cell.setCellValue(tblSanPham.getValueAt(j, k).toString());
                     }
                 }
             }
             FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
             wb.write(out);
             wb.close();
             out.close();
             openFile(saveFile.toString());
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
    }//GEN-LAST:event_exportexcel_ProductFormBtnActionPerformed

    private void refresh_ProductFromBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_ProductFromBtnActionPerformed
        // TODO add your handling code here:
         search_ProductFormComboBox.setSelectedIndex(0);
        loadDataToTable();
    }//GEN-LAST:event_refresh_ProductFromBtnActionPerformed

    private void detail_ProductFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detail_ProductFormBtnActionPerformed
        if (tblSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng !");
        } else {
            DetailCustomer a = new DetailCustomer(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            a.setVisible(true);
        }
    }//GEN-LAST:event_detail_ProductFormBtnActionPerformed

    private void search_ProductFormTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_ProductFormTextKeyReleased
        String luaChon = search_ProductFormComboBox.getSelectedItem().toString();
        String content = search_ProductFormText.getText();
        ArrayList<Customer> result = searchFn(luaChon, content);
        loadDataToTableSearch(result);
    }//GEN-LAST:event_search_ProductFormTextKeyReleased

    private void search_ProductFormComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_ProductFormComboBoxActionPerformed
        String luaChon = search_ProductFormComboBox.getSelectedItem().toString();
        String content = search_ProductFormText.getText();
        ArrayList<Customer> result = searchFn(luaChon, content);
        loadDataToTableSearch(result);
    }//GEN-LAST:event_search_ProductFormComboBoxActionPerformed

    private void search_ProductFormTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_ProductFormTextKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_search_ProductFormTextKeyPressed

    private void search_ProductFormComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_search_ProductFormComboBoxPropertyChange
        // TODO add your handling code here:
         String luaChon = search_ProductFormComboBox.getSelectedItem().toString();
        String content = search_ProductFormText.getText();
        ArrayList<Customer> result = searchFn(luaChon, content);
        loadDataToTableSearch(result);
    }//GEN-LAST:event_search_ProductFormComboBoxPropertyChange

    // Chức năng tìm kiếm khách hàng
public ArrayList<Customer> searchFn(String luaChon, String content) {
    ArrayList<Customer> result = new ArrayList<>();
    SearchCustomer searchCus = new SearchCustomer();
    switch (luaChon) {
        case "Tất cả":
            result = searchCus.searchTatCa(content);
            break;
        case "Mã khách hàng":
            result = searchCus.searchMaKH(content);
            break;
        case "Tên khách hàng":
            result = searchCus.searchTenKH(content);
            break;
        case "Số điện thoại":
            result = searchCus.searchPhone(content);
            break;
        case "Email":
            result = searchCus.searchEmail(content);
            break;
        case "Địa chỉ":
            result = searchCus.searchDiaChi(content);
            break;
    }
    return result;
}

// Lấy chi tiết khách hàng
public Customer getDetailCustomer() {
    Customer a = CustomerDAO.getInstance().selectById(getCustomerSelect().getId());
    return a;
}

// Xóa khách hàng được chọn
public void xoaKhachHangSelect() {
    DefaultTableModel table_acc = (DefaultTableModel) tblSanPham.getModel();
    int i_row = tblSanPham.getSelectedRow();
    int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá khách hàng này?", "Xoá khách hàng", JOptionPane.YES_NO_OPTION);
    if (luaChon == JOptionPane.YES_OPTION) {
        Customer remove = getCustomerSelect();
        CustomerDAO.getInstance().delete(remove);
    }
    loadDataToTable();
}

// Lấy khách hàng được chọn từ bảng
public Customer getCustomerSelect() {
    int i_row = tblSanPham.getSelectedRow();
    if (i_row == -1) {
        return null; // Không có hàng nào được chọn
    }
    
    // Lấy giá trị ID của khách hàng từ cột đầu tiên (cột ID) và chuyển thành kiểu int
    int customerId = Integer.parseInt(tblModel.getValueAt(i_row, 0).toString());
    
    // Gọi phương thức selectById với ID khách hàng kiểu int
    Customer customer = CustomerDAO.getInstance().selectById(customerId);
    return customer;
}

// Tải dữ liệu khách hàng tìm kiếm lên bảng
public void loadDataToTableSearch(ArrayList<Customer> result) {
    try {
        tblModel.setRowCount(0);  // Xóa dữ liệu cũ trong bảng
        for (Customer i : result) {
            tblModel.addRow(new Object[]{
                i.getId(), i.getFullName(), i.getEmail(), i.getPhone(), i.getAddress()
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


// Thay đổi nội dung tìm kiếm
public void changeTextFind() {
    search_ProductFormText.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            // Khi nội dung thay đổi, có thể gọi hàm tìm kiếm để cập nhật dữ liệu
            String searchContent = search_ProductFormText.getText();
            ArrayList<Customer> result = SearchCustomer.getInstance().searchTatCa(searchContent);
            loadDataToTableSearch(result);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            // Khi nội dung xóa, nếu rỗng thì tải lại toàn bộ dữ liệu
            if (search_ProductFormText.getText().length() == 0) {
                loadDataToTable(); // Tải lại toàn bộ dữ liệu khi không có tìm kiếm
            } else {
                String searchContent = search_ProductFormText.getText();
                ArrayList<Customer> result = SearchCustomer.getInstance().searchTatCa(searchContent);
                loadDataToTableSearch(result);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // Không xử lý gì thêm trong trường hợp này
        }
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar ToolBar;
    private javax.swing.JButton add_ProductFormBtn;
    private javax.swing.JButton delete_ProductFormBtn;
    private javax.swing.JButton detail_ProductFormBtn;
    private javax.swing.JButton edit_ProductFormBtn;
    private javax.swing.JButton exportexcel_ProductFormBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JButton refresh_ProductFromBtn;
    private javax.swing.JComboBox<String> search_ProductFormComboBox;
    private javax.swing.JTextField search_ProductFormText;
    private javax.swing.JPanel searchbar;
    private javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables

    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
