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
import dao.FarmCertificateDAO;
import dao.FarmDAO;
import dao.ProductionFacilityDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Commune;
import model.District;
import model.Farm;
import model.FarmCertificate;
import model.ProductionFacility;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;

/**
 *
 * @author Admin
 */
public class UpdateCertification extends javax.swing.JDialog {
    private CertificateForm owner; // Form cha để tải lại dữ liệu sau khi cập nhật
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format ngày

    public UpdateCertification(javax.swing.JInternalFrame parent, javax.swing.JFrame owner, boolean modal) {
        super(owner, modal);
        initComponents();
        setLocationRelativeTo(null);

        // Truyền đối tượng CertificateForm từ parent
        this.owner = (CertificateForm) parent;

        // Lấy thông tin chứng chỉ đã được chọn
        FarmCertificate certificate = this.owner.getCertificateSelected();

        // Điền thông tin chứng chỉ vào các trường nhập liệu
        txtFarmId.setText(String.valueOf(certificate.getCertificateId())); // ID chứng chỉ
        txtCertificateType.setText(certificate.getCertificateType()); // Loại chứng chỉ
        txtIssueDate.setDate(parseDate(certificate.getIssueDate())); // Ngày cấp
        txtExpiryDate.setDate(parseDate(certificate.getExpiryDate())); // Ngày hết hạn
        txtIssuer.setText(certificate.getIssuer()); // Cơ quan cấp

        // Load danh sách cơ sở vào ComboBox và set cơ sở đã chọn
        loadFarms();
        cbxFarm.setSelectedItem(getFarmNameById(certificate.getFarmId()));

        // Thiết lập trạng thái cho RadioButton
        if (certificate.getStatus() == 1) {
            rbtnActive.setSelected(true);
        } else {
            rbtnInactive.setSelected(true);
        }
    }

    // Load danh sách cơ sở vào ComboBox
    private void loadFarms() {
        ArrayList<Farm> farms = (ArrayList<Farm>) FarmDAO.getInstance().selectAll();
        cbxFarm.removeAllItems();
        for (Farm farm : farms) {
            cbxFarm.addItem(farm.getFarmId() + " - " + farm.getFarmName());
        }
    }

    // Hàm chuyển String sang Date
    private Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    // Lấy tên cơ sở từ ID
    private String getFarmNameById(int farmId) {
        Farm farm = FarmDAO.getInstance().selectById(farmId);
        return farm != null ? farm.getFarmId() + " - " + farm.getFarmName() : "";
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCertificateType = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxFarm = new javax.swing.JComboBox<>();
        btnupdate = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFarmId = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIssuer = new javax.swing.JTextField();
        txtIssueDate = new com.toedter.calendar.JDateChooser();
        txtExpiryDate = new com.toedter.calendar.JDateChooser();
        rbtnActive = new javax.swing.JRadioButton();
        rbtnInactive = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sửa tài khoản");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel2.setText("Loại chứng chỉ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 110, -1));

        txtCertificateType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCertificateTypeActionPerformed(evt);
            }
        });
        jPanel1.add(txtCertificateType, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 298, 38));

        jLabel3.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel3.setText("Ngày cấp");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, 24));

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel5.setText("Cơ sở");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 50, -1));

        cbxFarm.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        cbxFarm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxFarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFarmActionPerformed(evt);
            }
        });
        jPanel1.add(cbxFarm, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 298, 38));

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
        jLabel6.setText("Trạng thái");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 100, -1));

        jLabel4.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel4.setText("Ngày hết hạn");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jLabel7.setText("ID");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        txtFarmId.setText("txtFarmId");
        jPanel1.add(txtFarmId, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel8.setText("Cơ quan cấp");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 100, -1));
        jPanel1.add(txtIssuer, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 298, 38));
        jPanel1.add(txtIssueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 300, 40));
        jPanel1.add(txtExpiryDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 300, 40));

        buttonGroup1.add(rbtnActive);
        rbtnActive.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbtnActive.setText("Hiệu lực");
        jPanel1.add(rbtnActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, -1, -1));

        buttonGroup1.add(rbtnInactive);
        rbtnInactive.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbtnInactive.setText("Hết hạn");
        jPanel1.add(rbtnInactive, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, -1, -1));

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
     try {
            // Lấy thông tin từ các trường
            int certificateId = Integer.parseInt(txtFarmId.getText());
            String certificateType = txtCertificateType.getText().trim();
            String issueDate = dateFormat.format(txtIssueDate.getDate());
            String expiryDate = dateFormat.format(txtExpiryDate.getDate());
            String issuer = txtIssuer.getText().trim();
            int status = rbtnActive.isSelected() ? 1 : 0;

            // Kiểm tra các trường dữ liệu
            if (certificateType.isEmpty() || issueDate.isEmpty() || expiryDate.isEmpty() || issuer.isEmpty() || cbxFarm.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // Lấy farmId từ ComboBox
            String selectedFarm = cbxFarm.getSelectedItem().toString();
            int farmId = Integer.parseInt(selectedFarm.split(" - ")[0]);

            // Tạo đối tượng FarmCertificate
            FarmCertificate certificate = new FarmCertificate(
                certificateId, farmId, certificateType, issueDate, expiryDate, issuer, status
            );

            // Cập nhật vào database
            int result = FarmCertificateDAO.getInstance().update(certificate);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật chứng chỉ thành công!");
                this.dispose(); // Đóng form sau khi cập nhật
                owner.loadDataToTable((ArrayList<FarmCertificate>) FarmCertificateDAO.getInstance().selectAll());
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật chứng chỉ thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật chứng chỉ!");
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdateMouseClicked
        
    }//GEN-LAST:event_btnupdateMouseClicked

    private void cbxFarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFarmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFarmActionPerformed

    private void txtCertificateTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCertificateTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCertificateTypeActionPerformed
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxFarm;
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
    private javax.swing.JRadioButton rbtnActive;
    private javax.swing.JRadioButton rbtnInactive;
    private javax.swing.JTextField txtCertificateType;
    private com.toedter.calendar.JDateChooser txtExpiryDate;
    private javax.swing.JLabel txtFarmId;
    private com.toedter.calendar.JDateChooser txtIssueDate;
    private javax.swing.JTextField txtIssuer;
    // End of variables declaration//GEN-END:variables
}
