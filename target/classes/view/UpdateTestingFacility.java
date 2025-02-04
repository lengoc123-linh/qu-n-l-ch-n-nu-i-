/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import view.AccountForm;
import model.Account;
import dao.AccountDAO;
import dao.CommuneDAO;
import dao.DistrictDAO;
import dao.FarmDAO;
import dao.ProductionFacilityDAO;
import dao.TestingFacilityDAO;
import java.util.ArrayList;
import javax.swing.JFrame;
import model.Commune;
import model.District;
import model.Farm;
import model.ProductionFacility;
import model.TestingFacility;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;

/**
 *
 * @author Admin
 */
public class UpdateTestingFacility extends javax.swing.JDialog {
    private TestingFacilityForm owner;  // Form cha để tải lại dữ liệu sau khi cập nhật

    public UpdateTestingFacility(javax.swing.JInternalFrame parent, javax.swing.JFrame owner, boolean modal) {
        super(owner, modal);
        initComponents();
        setLocationRelativeTo(null);

        // Truyền đối tượng TestingFacilityForm từ parent
        this.owner = (TestingFacilityForm) parent;

        // Lấy thông tin TestingFacility đã được chọn
        TestingFacility facility = this.owner.getFacilitySelect();

        // Điền thông tin TestingFacility vào các trường nhập liệu
        txtFacilityId.setText(String.valueOf(facility.getFacilityId()));  // Mã cơ sở
        txtFacilityName.setText(facility.getFacilityName());  // Tên cơ sở
        txtAddress.setText(facility.getAddress());  // Địa chỉ
        txtContactPerson.setText(facility.getContactPerson());  // Người liên hệ
        txtContactPhone.setText(facility.getContactPhone());  // Số điện thoại

        // Thiết lập các giá trị cho ComboBox huyện và xã
        loadDistricts();  // Nạp các huyện vào combobox huyện
        loadCommuneByDistrict(facility.getDistrictId());  // Nạp các xã theo huyện đã chọn

        // Set huyện và xã đã được chọn
        cbxDistrict.setSelectedItem(getDistrictName(facility.getDistrictId()));  // Set huyện theo ID
        cbxCommune.setSelectedItem(getCommuneName(facility.getCommuneId()));  // Set xã theo ID
    }

    public UpdateTestingFacility(TestingFacilityForm parent, TestingFacility facilityToEdit, JFrame owner, boolean modal) {
        super(owner, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.owner = parent;

        // Điền thông tin TestingFacility vào các trường nhập liệu
        txtFacilityId.setText(String.valueOf(facilityToEdit.getFacilityId()));  // Mã cơ sở
        txtFacilityName.setText(facilityToEdit.getFacilityName());  // Tên cơ sở
        txtAddress.setText(facilityToEdit.getAddress());  // Địa chỉ
        txtContactPerson.setText(facilityToEdit.getContactPerson());  // Người liên hệ
        txtContactPhone.setText(facilityToEdit.getContactPhone());  // Số điện thoại

        // Thiết lập các giá trị cho ComboBox huyện và xã
        loadDistricts();
        loadCommuneByDistrict(facilityToEdit.getDistrictId());

        // Set huyện và xã đã được chọn
        cbxDistrict.setSelectedItem(getDistrictName(facilityToEdit.getDistrictId()));
        cbxCommune.setSelectedItem(getCommuneName(facilityToEdit.getCommuneId()));
    }

        private void loadDistricts() {
            DistrictDAO districtDAO = new DistrictDAO();
            ArrayList<District> districts = (ArrayList<District>) districtDAO.selectAll();
            cbxDistrict.removeAllItems();  // Xóa hết các mục cũ trong ComboBox
            cbxDistrict.addItem("Chọn huyện");  // Item mặc định

            // Nạp các huyện vào ComboBox
            for (District district : districts) {
                cbxDistrict.addItem(district.getDistrictName());
            }
        }
        private void loadCommuneByDistrict(int districtId) {
            CommuneDAO communeDAO = new CommuneDAO();
            ArrayList<Commune> communes = communeDAO.selectByDistrictId(districtId);
            cbxCommune.removeAllItems();  // Xóa hết các mục cũ trong ComboBox
            cbxCommune.addItem("Chọn xã");  // Item mặc định

            // Nạp các xã vào ComboBox
            for (Commune commune : communes) {
                cbxCommune.addItem(commune.getCommuneName());
            }
        }
        private String getDistrictName(int districtId) {
            DistrictDAO districtDAO = new DistrictDAO();
            District district = districtDAO.selectById(districtId);
            return district != null ? district.getDistrictName() : "";
        }

        private String getCommuneName(int communeId) {
            CommuneDAO communeDAO = new CommuneDAO();
            Commune commune = communeDAO.selectById(communeId);
            return commune != null ? commune.getCommuneName() : "";
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
        jLabel2 = new javax.swing.JLabel();
        txtFacilityName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtContactPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbxCommune = new javax.swing.JComboBox<>();
        btnupdate = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxDistrict = new javax.swing.JComboBox<>();
        txtAddress = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFacilityId = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtContactPerson = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sửa tài khoản");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel2.setText("Name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 110, -1));

        txtFacilityName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFacilityNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtFacilityName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 298, 38));

        jLabel3.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel3.setText("Địa chỉ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 24));
        jPanel1.add(txtContactPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 298, 38));

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel5.setText("Xã");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 50, -1));

        cbxCommune.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        cbxCommune.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxCommune.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCommuneActionPerformed(evt);
            }
        });
        jPanel1.add(cbxCommune, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 298, 38));

        btnupdate.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnupdate.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(255, 255, 255));
        btnupdate.setText("Cập nhật");
        btnupdate.setBorder(null);
        btnupdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnupdateMouseClicked(evt);
            }
        });
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 140, 38));

        btnClose.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        btnClose.setText("Huỷ");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jPanel1.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 590, 140, 38));

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CẬP NHẬT THÔNG TIN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 70));

        jLabel6.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel6.setText("Số điện thoại");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 100, -1));

        jLabel4.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel4.setText("Huyện");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        cbxDistrict.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbxDistrict, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 300, 40));
        jPanel1.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 298, 38));

        jLabel7.setText("ID");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        txtFacilityId.setText("txtFarmId");
        jPanel1.add(txtFacilityId, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel8.setText("Người liên hệ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 100, -1));
        jPanel1.add(txtContactPerson, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 298, 38));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdateMouseClicked
          // Lấy thông tin từ các trường đầu vào
            String facilityName = txtFacilityName.getText().trim();
            String address = txtAddress.getText().trim();
            String contactPhone = txtContactPhone.getText().trim();
            String contactPerson = txtContactPerson.getText().trim();

            // Kiểm tra các trường thông tin
            if (facilityName.isEmpty() || address.isEmpty() || contactPhone.isEmpty() || contactPerson.isEmpty()
                    || cbxDistrict.getSelectedItem() == null || cbxCommune.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // Lấy ID huyện và xã từ ComboBox
            int districtId = getDistrictId(cbxDistrict.getSelectedItem().toString());
            int communeId = getCommuneId(cbxCommune.getSelectedItem().toString());

            // Kiểm tra nếu ID huyện hoặc xã không hợp lệ
            if (districtId == -1 || communeId == -1) {
                JOptionPane.showMessageDialog(this, "Huyện hoặc xã không hợp lệ!");
                return;
            }

            // Lấy TestingFacility từ form đã chọn
            TestingFacility facility = owner.getFacilitySelect();
            facility.setFacilityName(facilityName);
            facility.setAddress(address);
            facility.setContactPhone(contactPhone);
            facility.setContactPerson(contactPerson);
            facility.setDistrictId(districtId);
            facility.setCommuneId(communeId);

            // Cập nhật TestingFacility vào cơ sở dữ liệu
            try {
                int result = TestingFacilityDAO.getInstance().update(facility);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật cơ sở khảo nghiệm thành công!");
                    this.dispose();  // Đóng form sau khi cập nhật thành công
                    owner.loadDataToTable((ArrayList<TestingFacility>) TestingFacilityDAO.getInstance().selectAll());  // Tải lại dữ liệu vào bảng
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật cơ sở khảo nghiệm thất bại!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật cơ sở khảo nghiệm!");
            }
        
    }//GEN-LAST:event_btnupdateMouseClicked
 // Hàm lấy ID của huyện từ tên huyện
    private int getDistrictId(String districtName) {
        DistrictDAO districtDAO = new DistrictDAO();
        District district = districtDAO.selectByName(districtName);
        return district != null ? district.getDistrictId() : -1;
    }

    // Hàm lấy ID của xã từ tên xã
    private int getCommuneId(String communeName) {
        CommuneDAO communeDAO = new CommuneDAO();
        Commune commune = communeDAO.selectByName(communeName);
        return commune != null ? commune.getCommuneId() : -1;
    }
    private void txtFacilityNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFacilityNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacilityNameActionPerformed

    private void cbxCommuneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCommuneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCommuneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cbxCommune;
    private javax.swing.JComboBox<String> cbxDistrict;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContactPerson;
    private javax.swing.JTextField txtContactPhone;
    private javax.swing.JLabel txtFacilityId;
    private javax.swing.JTextField txtFacilityName;
    // End of variables declaration//GEN-END:variables
}
