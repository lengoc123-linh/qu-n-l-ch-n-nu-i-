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
import dao.OrganizationDAO;
import java.util.ArrayList;
import javax.swing.JComboBox;
import model.Commune;
import model.District;
import model.Farm;
import model.Organization;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;

/**
 *
 * @author Admin
 */
public class UpdateFarm extends javax.swing.JDialog {
    private FarmForm homeFarm; // Form chính để cập nhật lại bảng dữ liệu

    public UpdateFarm(javax.swing.JInternalFrame parent, javax.swing.JFrame owner, boolean modal) {
        super(owner, modal);
        initComponents();
        setLocationRelativeTo(null);

        homeFarm = (FarmForm) parent;

        // Lấy thông tin farm được chọn
        Farm farm = homeFarm.getFarmSelect();

        // Điền thông tin vào các trường nhập liệu
        txtFarmName.setText(farm.getFarmName());
        txtAddress.setText(farm.getAddress());
        txtOwner.setText(farm.getOwner());
        txtLatitude.setText(String.valueOf(farm.getLatitude()));
        txtLongitude.setText(String.valueOf(farm.getLongitude()));
        txtFarmId.setText(String.valueOf(farm.getFarmId()));

        // Nạp giá trị cho các ComboBox
        loadDistricts();
        loadOrganizations();
        loadCommuneByDistrict(farm.getDistrictId());

        // Thiết lập giá trị ComboBox theo dữ liệu farm
        cbxDistrict.setSelectedItem(getDistrictName(farm.getDistrictId()));
        cbxCommune.setSelectedItem(getCommuneName(farm.getCommuneId()));
        cbxOrganization.setSelectedItem(getOrganizationName(farm.getOrganizationId()));
    }

    private void loadDistricts() {
        DistrictDAO districtDAO = DistrictDAO.getInstance();
        ArrayList<District> districts = (ArrayList<District>) districtDAO.selectAll();
        cbxDistrict.removeAllItems();
        cbxDistrict.addItem("Chọn huyện");
        for (District district : districts) {
            cbxDistrict.addItem(district.getDistrictId() + " - " + district.getDistrictName());
        }

        // Lắng nghe sự kiện thay đổi huyện để cập nhật xã
        cbxDistrict.addActionListener(e -> {
            if (cbxDistrict.getSelectedIndex() > 0) {
                String selectedDistrict = (String) cbxDistrict.getSelectedItem();
                int districtId = Integer.parseInt(selectedDistrict.split(" - ")[0]);
                loadCommuneByDistrict(districtId);
            } else {
                loadCommuneByDistrict(0);
            }
        });
    }

    private void loadCommuneByDistrict(int districtId) {
        CommuneDAO communeDAO = CommuneDAO.getInstance();
        ArrayList<Commune> communes = communeDAO.selectByDistrictId(districtId);
        cbxCommune.removeAllItems();
        cbxCommune.addItem("Chọn xã");
        for (Commune commune : communes) {
            cbxCommune.addItem(commune.getCommuneId() + " - " + commune.getCommuneName());
        }
    }

    private void loadOrganizations() {
        OrganizationDAO organizationDAO = OrganizationDAO.getInstance();
        ArrayList<Organization> organizations = (ArrayList<Organization>) organizationDAO.selectAll();
        cbxOrganization.removeAllItems();
        cbxOrganization.addItem("Chọn tổ chức");
        for (Organization org : organizations) {
            cbxOrganization.addItem(org.getOrganizationId() + " - " + org.getName());
        }
    }

    private String getDistrictName(int districtId) {
        DistrictDAO districtDAO = DistrictDAO.getInstance();
        District district = districtDAO.selectById(districtId);
        return district != null ? district.getDistrictName() : "";
    }

    private String getCommuneName(int communeId) {
        CommuneDAO communeDAO = CommuneDAO.getInstance();
        Commune commune = communeDAO.selectById(communeId);
        return commune != null ? commune.getCommuneName() : "";
    }

    private String getOrganizationName(int organizationId) {
        OrganizationDAO organizationDAO = OrganizationDAO.getInstance();
        Organization organization = organizationDAO.selectById(organizationId);
        return organization != null ? organization.getName() : "";
    }

    private int getIdFromComboBox(JComboBox<String> comboBox) {
        String selectedItem = (String) comboBox.getSelectedItem();
        if (selectedItem != null && selectedItem.contains(" - ")) {
            return Integer.parseInt(selectedItem.split(" - ")[0]);
        }
        return 0; // Nếu không chọn hoặc dữ liệu không hợp lệ
    }

    private Farm getFarmFromInput() {
        // Lấy dữ liệu từ các trường nhập liệu
        String farmName = txtFarmName.getText().trim();
        String address = txtAddress.getText().trim();
        String owner = txtOwner.getText().trim();
        int districtId = getIdFromComboBox(cbxDistrict);
        int communeId = getIdFromComboBox(cbxCommune);
        int organizationId = getIdFromComboBox(cbxOrganization);

        String latitudeStr = txtLatitude.getText().trim();
        String longitudeStr = txtLongitude.getText().trim();

        if (farmName.isEmpty() || address.isEmpty() || owner.isEmpty() || districtId == 0 || communeId == 0 || organizationId == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        double latitude = 0;
        double longitude = 0;
        try {
            latitude = Double.parseDouble(latitudeStr);
            longitude = Double.parseDouble(longitudeStr);
            if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
                JOptionPane.showMessageDialog(this, "Vĩ độ phải trong khoảng [-90, 90] và kinh độ trong khoảng [-180, 180]!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vĩ độ và kinh độ phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        int farmId;
        try {
            farmId = Integer.parseInt(txtFarmId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã farm (ID) phải là số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Farm(farmId, farmName, address, districtId, communeId, owner, latitude, longitude, organizationId);
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Farm farm = getFarmFromInput();
            if (farm != null) {
                int result = FarmDAO.getInstance().update(farm);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    homeFarm.loadDataToTable((ArrayList<Farm>) FarmDAO.getInstance().selectAll());
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        jLabel2 = new javax.swing.JLabel();
        txtFarmName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtOwner = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbxOrganization = new javax.swing.JComboBox<>();
        btnupdate = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxDistrict = new javax.swing.JComboBox<>();
        txtAddress = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFarmId = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtLongitude = new javax.swing.JTextField();
        txtLatitude = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbxCommune = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sửa tài khoản");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel2.setText("Farm name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 110, -1));

        txtFarmName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFarmNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtFarmName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 298, 38));

        jLabel3.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel3.setText("Địa chỉ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 50, 24));
        jPanel1.add(txtOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 298, 38));

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel5.setText("Xã");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 50, -1));

        cbxOrganization.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        cbxOrganization.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxOrganization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrganizationActionPerformed(evt);
            }
        });
        jPanel1.add(cbxOrganization, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 298, 38));

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
        jLabel6.setText("Tổ chức");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 90, -1));

        jLabel4.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel4.setText("Huyện");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        cbxDistrict.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbxDistrict, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 300, 40));
        jPanel1.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 298, 38));

        jLabel7.setText("ID");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        txtFarmId.setText("txtFarmId");
        jPanel1.add(txtFarmId, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Lat");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Long");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, -1, -1));

        txtLongitude.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtLongitude, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 120, -1));

        txtLatitude.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txtLatitude, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 120, -1));

        jLabel10.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel10.setText("Chủ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 90, -1));

        cbxCommune.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        cbxCommune.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxCommune.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCommuneActionPerformed(evt);
            }
        });
        jPanel1.add(cbxCommune, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 298, 38));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
             try {
        // Lấy dữ liệu từ form
        Farm farm = getFarmFromInput();

        if (farm != null) {
            // Cập nhật vào cơ sở dữ liệu
            int result = FarmDAO.getInstance().update(farm);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                homeFarm.loadDataToTable((ArrayList<Farm>) FarmDAO.getInstance().selectAll());
                this.dispose(); // Đóng form sau khi cập nhật thành công
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdateMouseClicked
            
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
    private void txtFarmNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFarmNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFarmNameActionPerformed

    private void cbxOrganizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrganizationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxOrganizationActionPerformed

    private void cbxCommuneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCommuneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCommuneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cbxCommune;
    private javax.swing.JComboBox<String> cbxDistrict;
    private javax.swing.JComboBox<String> cbxOrganization;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JLabel txtFarmId;
    private javax.swing.JTextField txtFarmName;
    private javax.swing.JTextField txtLatitude;
    private javax.swing.JTextField txtLongitude;
    private javax.swing.JTextField txtOwner;
    // End of variables declaration//GEN-END:variables
}
