/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.AccountDAO;
import dao.ChiTietPhieuXuatDAO;
import dao.SanPhamDAO;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChiTietPhieu;
import model.PhieuXuat;

/**
 *
 * @author Admin
 */
public class CTPhieuXuat extends javax.swing.JDialog {

    /**
     * Creates new form CTPhieuDialog
     */
    private PhieuXuatForm parent;

    public CTPhieuXuat(javax.swing.JInternalFrame parent, javax.swing.JFrame owner, boolean modal) {
        super(owner, modal);
        this.parent = (PhieuXuatForm) parent;
        initComponents();
        setLocationRelativeTo(null);
        PhieuXuat pn = this.parent.getPhieuXuatSelect();
        labelMaPhieu.setText(pn.getMaPhieu());        
        labelKhach.setText(pn.getCustomer().getFullName());
        labelNguoiTao.setText(AccountDAO.getInstance().selectById(pn.getNguoiTao()).getFullName());
        labelTongTien.setText(this.parent.getFormatter().format(pn.getTongTien()) + "đ");
        labelThoiGianTao.setText(this.parent.getFormatDate().format(pn.getThoiGianTao()));
        loadDataToTableProduct();
        setWidthTable();
    }

    public void setWidthTable() {
        tblChiTietPhieu.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblChiTietPhieu.getColumnModel().getColumn(1).setPreferredWidth(10);
        tblChiTietPhieu.getColumnModel().getColumn(2).setPreferredWidth(250);
    }

    public void loadDataToTableProduct() {
        try {
            ArrayList<ChiTietPhieu> CTPhieu = ChiTietPhieuXuatDAO.getInstance().selectAll(this.parent.getPhieuXuatSelect().getMaPhieu());
            DefaultTableModel tblCTPhieumd = (DefaultTableModel) tblChiTietPhieu.getModel();
            tblCTPhieumd.setRowCount(0);
            for (int i = 0; i < CTPhieu.size(); i++) {
                tblCTPhieumd.addRow(new Object[]{
                    i + 1, 
                    CTPhieu.get(i).getMaSP(),
                    SanPhamDAO.getInstance().selectById(CTPhieu.get(i).getMaSP()).getTenSP(),
                    CTPhieu.get(i).getSoLuong(),
                    parent.getFormatter().format(CTPhieu.get(i).getDonGia()) + "đ",
                    parent.getFormatter().format(CTPhieu.get(i).getDonGia() * CTPhieu.get(i).getSoLuong()) + "đ"
                });
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietPhieu = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelTongTien = new javax.swing.JLabel();
        labelMaPhieu = new javax.swing.JLabel();
        labelNguoiTao = new javax.swing.JLabel();
        labelThoiGianTao = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        labelKhach = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chi tiết phiếu");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CHI TIẾT PHIẾU XUẤT");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel2.setText("Mã phiếu: ");

        tblChiTietPhieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblChiTietPhieu);

        jLabel4.setText("Người tạo:");

        jLabel5.setText("Thời gian tạo:");

        labelTongTien.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        labelTongTien.setText("...đ");

        labelMaPhieu.setText("jLabel7");

        labelNguoiTao.setText("jLabel7");

        labelThoiGianTao.setText("jLabel7");

        jLabel7.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel7.setText("TỔNG TIỀN:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_pdf_25px.png"))); // NOI18N
        jButton1.setText("Xuất PDF");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Khách hàng");

        labelKhach.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(labelThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(32, 32, 32)
                                .addComponent(labelKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(61, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelMaPhieu)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(labelKhach)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelThoiGianTao)
                    .addComponent(jLabel4)
                    .addComponent(labelNguoiTao))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelTongTien)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// tạo một document
        Document document = new Document();
       try {
           // Khởi tạo PdfWriter
           PdfWriter.getInstance(document, new FileOutputStream("ChiTietPhieuXuat_"+labelMaPhieu.getText()+".pdf"));

           // Mở tài liệu để bắt đầu ghi
           document.open();

           // Thiết lập font và định dạng
           Font fontTitle = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
           Font fontLabel = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
           Font fontNormal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

           // Thêm tiêu đề
           Paragraph title = new Paragraph("CHI TIẾT PHIẾU XUẤT", fontTitle);
           title.setAlignment(Element.ALIGN_CENTER);
           document.add(title);
           document.add(Chunk.NEWLINE);

           // Thêm thông tin phiếu xuất
           PdfPTable infoTable = new PdfPTable(2);
           infoTable.setWidthPercentage(100);
           infoTable.setSpacingBefore(10f);
           infoTable.setSpacingAfter(10f);
           infoTable.setWidths(new float[]{1, 2});

           // Dòng Mã phiếu
           infoTable.addCell(new PdfPCell(new Phrase("Mã phiếu:", fontLabel)));
           infoTable.addCell(new PdfPCell(new Phrase(labelMaPhieu.getText(), fontNormal)));

           // Dòng Người tạo
           infoTable.addCell(new PdfPCell(new Phrase("Người tạo:", fontLabel)));
           infoTable.addCell(new PdfPCell(new Phrase(labelNguoiTao.getText(), fontNormal)));
           
           // Dòng Khách hàng
           infoTable.addCell(new PdfPCell(new Phrase("Khách hàng:", fontLabel)));
           infoTable.addCell(new PdfPCell(new Phrase(labelKhach.getText(), fontNormal)));

           // Dòng Thời gian tạo
           infoTable.addCell(new PdfPCell(new Phrase("Thời gian tạo:", fontLabel)));
           infoTable.addCell(new PdfPCell(new Phrase(labelThoiGianTao.getText(), fontNormal)));

           // Thêm bảng thông tin phiếu vào tài liệu
           document.add(infoTable);

           // Thêm bảng chi tiết sản phẩm
           PdfPTable table = new PdfPTable(6);
           table.setWidthPercentage(100);
           table.setSpacingBefore(10f);
           table.setWidths(new float[]{1, 2, 3, 2, 2, 2});

           // Tiêu đề các cột
           String[] headers = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
           for (String header : headers) {
               PdfPCell headerCell = new PdfPCell(new Phrase(header, fontLabel));
               headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(headerCell);
           }

           // Lấy dữ liệu từ bảng và thêm vào PDF
           DefaultTableModel model = (DefaultTableModel) tblChiTietPhieu.getModel();
           for (int i = 0; i < model.getRowCount(); i++) {
               for (int j = 0; j < model.getColumnCount(); j++) {
                   PdfPCell cell = new PdfPCell(new Phrase(model.getValueAt(i, j).toString(), fontNormal));
                   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                   table.addCell(cell);
               }
           }

           // Thêm bảng vào tài liệu
           document.add(table);

           // Thêm tổng tiền
           Paragraph total = new Paragraph("TỔNG TIỀN: " + labelTongTien.getText(), fontTitle);
           total.setAlignment(Element.ALIGN_RIGHT);
           document.add(total);

           // Đóng tài liệu
           document.close();

           // Hiển thị thông báo thành công
           JOptionPane.showMessageDialog(this, "Xuất PDF thành công!");

       } catch (DocumentException | FileNotFoundException e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(this, "Lỗi khi xuất PDF!", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(CTPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CTPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CTPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CTPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelKhach;
    private javax.swing.JLabel labelMaPhieu;
    private javax.swing.JLabel labelNguoiTao;
    private javax.swing.JLabel labelThoiGianTao;
    private javax.swing.JLabel labelTongTien;
    private javax.swing.JTable tblChiTietPhieu;
    // End of variables declaration//GEN-END:variables
}
