/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import dao.CommuneDAO;
import dao.DistrictDAO;
import dao.FarmDAO;
import dao.ProductionFacilityDAO;
import dao.SanPhamDAO;
import dao.TestingFacilityDAO;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Commune;
import model.District;
import model.Farm;
import model.ProductionFacility;
import model.SanPham;
import model.TestingFacility;


/**
 *
 * @author Admin
 */
public class AddTestingFacility extends javax.swing.JDialog {
    private TestingFacilityForm owner;  // Thay đổi từ ProductionFacilityForm sang TestingFacilityForm

    public AddTestingFacility(javax.swing.JInternalFrame parent, javax.swing.JFrame owner, boolean modal) {
        super(owner, modal);
        this.owner = (TestingFacilityForm) parent;  // Thay đổi từ ProductionFacilityForm sang TestingFacilityForm
        initComponents();
        setLocationRelativeTo(null);
        loadDistricts();  // Tải danh sách huyện
        loadCommuneByDistrict(0);  // Mặc định chọn huyện có id = 0 (hoặc bạn có thể để trống)
    }

    private AddTestingFacility(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Load danh sách huyện vào ComboBox
    private void loadDistricts() {
        DistrictDAO districtDAO = new DistrictDAO();
        ArrayList<District> districts = (ArrayList<District>) districtDAO.selectAll();
        cbxDistrict.removeAllItems();  // Xóa hết các mục cũ trong ComboBox
        cbxDistrict.addItem("Chọn huyện");  // Item mặc định
        for (District district : districts) {
            cbxDistrict.addItem(district.getDistrictName());
        }
    }

    // Load danh sách xã vào ComboBox khi chọn huyện
    private void loadCommuneByDistrict(int districtId) {
        CommuneDAO communeDAO = new CommuneDAO();
        ArrayList<Commune> communes = communeDAO.selectByDistrictId(districtId);
        cbxCommune.removeAllItems();  // Xóa hết các mục cũ trong ComboBox
        cbxCommune.addItem("Chọn xã");  // Item mặc định
        for (Commune commune : communes) {
            cbxCommune.addItem(commune.getCommuneName());
        }
    }

    // Phương thức lấy ID của huyện từ tên huyện (đã có sẵn trong DistrictDAO)
    private int getDistrictIdByName(String districtName) {
        DistrictDAO districtDAO = new DistrictDAO();
        District district = districtDAO.selectByName(districtName);
        return district != null ? district.getDistrictId() : 0;
    }

    // Phương thức lấy ID của xã từ tên xã
    private int getCommuneIdByName(String communeName) {
        CommuneDAO communeDAO = new CommuneDAO();
        Commune commune = communeDAO.selectByName(communeName);
        return commune != null ? commune.getCommuneId() : 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAddProduct = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtContactPerson = new javax.swing.JTextField();
        cbxDistrict = new javax.swing.JComboBox<>();
        cbxCommune = new javax.swing.JComboBox<>();
        txtFacilityName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtContactPhone = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thêm sản phẩm mới");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Địa Chỉ");

        txtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddressActionPerformed(evt);
            }
        });

        jLabel4.setText("Huyện");

        btnAddProduct.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnAddProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProduct.setText("Thêm sản phẩm");
        btnAddProduct.setBorder(null);
        btnAddProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(255, 0, 0));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Huỷ bỏ");
        btnCancel.setBorder(null);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel15.setText("Xã");

        jLabel16.setText("Người liên hệ");

        txtContactPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactPersonActionPerformed(evt);
            }
        });

        cbxDistrict.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxDistrict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDistrictActionPerformed(evt);
            }
        });

        cbxCommune.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Name");

        jLabel17.setText("Số điện thoại");

        txtContactPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactPhoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContactPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cbxDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFacilityName, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxCommune, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFacilityName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxCommune, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(txtContactPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 380, 480));

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("THÊM ĐƠN VỊ MỚI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(80, 80, 80))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        // Lấy thông tin từ các trường đầu vào
        String facilityName = txtFacilityName.getText().trim();
        String address = txtAddress.getText().trim();
        String contactPerson = txtContactPerson.getText().trim();
        String contactPhone = txtContactPhone.getText().trim();

        // Kiểm tra các trường thông tin
        if (facilityName.isEmpty() || address.isEmpty() || contactPerson.isEmpty() || contactPhone.isEmpty()
                || cbxDistrict.getSelectedItem() == null || cbxCommune.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        // Lấy ID huyện và xã từ ComboBox (dùng phương thức để lấy ID từ tên)
        String districtName = cbxDistrict.getSelectedItem().toString();
        String communeName = cbxCommune.getSelectedItem().toString();

        int districtId = getDistrictIdByName(districtName);
        int communeId = getCommuneIdByName(communeName);

        // Kiểm tra nếu ID huyện hoặc xã không hợp lệ
        if (districtId == -1 || communeId == -1) {
            JOptionPane.showMessageDialog(this, "Huyện hoặc xã không hợp lệ!");
            return;
        }

        // Tạo đối tượng TestingFacility với thông tin đã nhập
        TestingFacility facility = new TestingFacility(0, facilityName, address, districtId, communeId, contactPerson, contactPhone);

        try {
            // Thêm cơ sở khảo nghiệm vào cơ sở dữ liệu
            int result = TestingFacilityDAO.getInstance().insert(facility);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Thêm cơ sở khảo nghiệm thành công!");
                this.dispose();
                // Tải lại bảng sau khi thêm
                ArrayList<TestingFacility> testingFacilities = (ArrayList<TestingFacility>) TestingFacilityDAO.getInstance().selectAll();
                owner.loadDataToTable(testingFacilities);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm cơ sở khảo nghiệm thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm cơ sở khảo nghiệm!");
        }
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void txtContactPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactPersonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactPersonActionPerformed

    private void cbxDistrictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDistrictActionPerformed
         // Lấy tên huyện được chọn và tìm id của huyện         
        String selectedDistrict = (String) cbxDistrict.getSelectedItem();
        
        // Kiểm tra xem người dùng đã chọn huyện chưa
        if (selectedDistrict != null && !selectedDistrict.equals("Chọn huyện")) {
            // Tìm districtId từ tên huyện
            DistrictDAO districtDAO = new DistrictDAO();
            District district = districtDAO.selectByName(selectedDistrict);
            int districtId = district.getDistrictId();  // Lấy ID của huyện
            
            // Load danh sách xã dựa trên districtId
            loadCommuneByDistrict(districtId);
        } else {
            // Nếu không chọn huyện, xóa danh sách xã
            loadCommuneByDistrict(0);  // Có thể thay 0 bằng giá trị mặc định
        }
    }//GEN-LAST:event_cbxDistrictActionPerformed

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressActionPerformed

    private void txtContactPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactPhoneActionPerformed

    /**
     * @param args the command line arguments
     */
//    public String createIdPC() {
//        ArrayList<SanPham> mtAll = SanPhamDAO.getInstance().selectAll();
//        ArrayList<SanPham> pcAll = new ArrayList<SanPham>();
//        for (SanPham sanPham : mtAll) {
//            if (sanPham.getMaSP().contains("PC")) {
//                pcAll.add(sanPham);
//            }
//        }
//        int i = pcAll.size();
//        String check ="check";
//        while(check.length()!=0){
//            i++;
//            for (SanPham sanPham : pcAll) {
//                if(sanPham.getMaSP().equals("PC"+i)){
//                    check="";
//                }
//            }
//            if(check.length()==0){
//                check ="check";
//            } else {
//                check = "";
//            }
//        }
//        return "PC" + i;
//    }

    public String createIdLT() {
        ArrayList<SanPham> spAll = SanPhamDAO.getInstance().selectAll();
        ArrayList<SanPham> sp = new ArrayList<SanPham>();
        for (SanPham sanPham : spAll) {
            if (sanPham.getMaSP().contains("SP")) {
               sp.add(sanPham);
            }
        }
        int i = sp.size();
        String check ="check";
        while(check.length()!=0){
            i++;
            for (SanPham sanPham : sp) {
                if(sanPham.getMaSP().equals("SP"+i)){
                    check="";
                }
            }
            if(check.length()==0){
                check ="check";
            } else {
                check = "";
            }
        }
        return "SP" + i;
    }

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
            java.util.logging.Logger.getLogger(AddCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddTestingFacility dialog = new AddTestingFacility(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox<String> cbxCommune;
    private javax.swing.JComboBox<String> cbxDistrict;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContactPerson;
    private javax.swing.JTextField txtContactPhone;
    private javax.swing.JTextField txtFacilityName;
    // End of variables declaration//GEN-END:variables
}
