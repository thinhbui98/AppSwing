/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import controller.DAORequest;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Thinh Bui
 */
public class Request extends javax.swing.JFrame {

    /**
     * Creates new form Request
     */
    DefaultTableModel model;
    private ArrayList<model.Request> list;
    private ArrayList<model.RequestDetail> listrd;
    model.Account account;
    private int bookId;
    private int userId;
    private int typeUser;
    public Request() {
        initComponents();
        list = new DAORequest().getListRequestSearched("SELECT requests.*,users.username,books.bookname FROM requests "
                + "INNER JOIN users ON users.id = requests.user_id "
                + "INNER JOIN request_details ON requests.id = request_details.request_id "
                + "INNER JOIN books ON books.id = request_details.book_id "
        );
        model = (DefaultTableModel) tblRequest.getModel();
        showTable();
    }
    

    
    public Request(int book_id, int user_id, int type){
        initComponents();
        bookId = book_id;
        userId = user_id;
        typeUser = type;
        boolean addRequestDetail = new DAORequest().addRequestDetail(bookId, userId, typeUser); 
        if (addRequestDetail) {
            JOptionPane.showMessageDialog(null, "THÊM REQUEST MỚI THÀNH CÔNG");
        } else {
            JOptionPane.showMessageDialog(null, "KHÔNG THỂ THÊM REQUEST MỚI");
        }
        list = new DAORequest().getListRequestSearched("SELECT requests.*,users.username,books.bookname FROM requests "
                + "INNER JOIN users ON users.id = requests.user_id "
                + "INNER JOIN request_details ON requests.id = request_details.request_id "
                + "INNER JOIN books ON books.id = request_details.book_id "
        );
        model = (DefaultTableModel) tblRequest.getModel();
        showTable();
    }
    
    public void showTable() {
        tblRequest.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblRequest.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblRequest.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblRequest.getColumnModel().getColumn(3).setPreferredWidth(10); 
        tblRequest.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        list.forEach((s) -> {
            String status = "";
            switch(s.getStatus()) {
                case 1:
                    status = "Đồng Ý";
                    break;
                case 2:
                    status = "Từ Chối";
                    break;
                case 3:
                    status = "Đang Xử Lý";
                    break;
                case 4:
                    status = "Đang Chờ";
                    break;
                default:
                    status = "Đã Trả Sách";
            }
            model.addRow(new Object[] {
                s.getId(), s.getUsername(), s.getBookname(), status
            });
        });
    }
    
//    private void addRequest() {
//        model.Request r = new model.Request();
//        
//        r.getUser_id(txt.getText());
//        b.setCategory_id(Integer.parseInt(txtCategory.getText()));
//        b.setDescription(txtDescription.getText());
//        b.setQuantity(Integer.parseInt(txtQuantity.getText()));
//        list.add(b);
//        
//        if(new DAOBook().addBook(b)) {
//            JOptionPane.showMessageDialog(null, "ĐÃ THÊM");
//            loadData();
//        }
//        else {
//            JOptionPane.showMessageDialog(null, "KHÔNG THỂ THÊM");
//        }
//    }
    private void acceptRequest() {
        int r = tblRequest.getSelectedRow();                   
        model.Request rq = new model.Request();
        
        rq.setId(Integer.parseInt(txtId.getText()));              

        list.remove(rq);                                                   
        
        if(new DAORequest().acceptRequest(rq)) {                 
            JOptionPane.showMessageDialog(null, "ĐÃ XÓA");      
            model.removeRow(r);                                 
        }                                                       
        else {
            JOptionPane.showMessageDialog(null, "KHÔNG THỂ XÓA");
        }
    }
    
    
    private void deleteRequest() {
        int r = tblRequest.getSelectedRow();                 
        model.Request rq = new model.Request();
        
        rq.setId(Integer.parseInt(txtId.getText())); 

        list.remove(rq);                                                
        
        if(new DAORequest().deleteRequest(rq)) {                
            JOptionPane.showMessageDialog(null, "ĐÃ XÓA");    
            model.removeRow(r);                               
        }                                                       
        else {
            JOptionPane.showMessageDialog(null, "KHÔNG THỂ XÓA");
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

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRequest = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnAccept = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnDecline = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtBookRequest = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jButton2.setText("Duyệt");

        jButton3.setText("Xóa");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên người dùng", "Tên sách", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRequestMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRequest);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông tin yêu cầu");

        jLabel2.setText("Tên người dùng:");

        jLabel3.setText("Sách yêu cầu:");

        btnAccept.setBackground(new java.awt.Color(0, 255, 0));
        btnAccept.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAcceptMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Duyệt");

        javax.swing.GroupLayout btnAcceptLayout = new javax.swing.GroupLayout(btnAccept);
        btnAccept.setLayout(btnAcceptLayout);
        btnAcceptLayout.setHorizontalGroup(
            btnAcceptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAcceptLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addGap(40, 40, 40))
        );
        btnAcceptLayout.setVerticalGroup(
            btnAcceptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnDecline.setBackground(new java.awt.Color(255, 0, 0));
        btnDecline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeclineMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Từ chối");

        javax.swing.GroupLayout btnDeclineLayout = new javax.swing.GroupLayout(btnDecline);
        btnDecline.setLayout(btnDeclineLayout);
        btnDeclineLayout.setHorizontalGroup(
            btnDeclineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDeclineLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addGap(40, 40, 40))
        );
        btnDeclineLayout.setVerticalGroup(
            btnDeclineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );

        jLabel6.setText("Trạng thái:");

        jLabel7.setText("Id:");

        txtId.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAccept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDecline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(48, 48, 48)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtId)
                                .addComponent(txtUsername)
                                .addComponent(txtStatus)
                                .addComponent(txtBookRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtBookRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnDecline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAccept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        jTextField1.setText("Tìm kiếm...");

        jButton1.setText("Tìm kiếm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRequestMouseClicked
        // TODO add your handling code here:
        int r = tblRequest.getSelectedRow();
        
        if(r != -1) {
            model.Request rq = list.get(r);
            txtId.setText(String.valueOf(rq.getId()));
            txtUsername.setText(rq.getUsername());
            String status = "";
            switch(rq.getStatus()) {
                case 1:
                    status = "Đồng Ý";
                    break;
                case 2:
                    status = "Từ Chối";
                    break;
                case 3:
                    status = "Đang Xử Lý";
                    break;
                case 4:
                    status = "Đang Chờ";
                    break;
                default:
                    status = "Đã Trả Sách";
            }
            txtStatus.setText(status);
            txtBookRequest.setText(rq.getBookname());
            if (rq.getStatus() < 3 || rq.getStatus() > 4) {
                btnAccept.setVisible(false);
                btnDecline.setVisible(false);
            } else {
                btnAccept.setVisible(true);
                btnDecline.setVisible(true);
            }
        }
    }//GEN-LAST:event_tblRequestMouseClicked

    private void btnDeclineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeclineMouseClicked
        // TODO add your handling code here:
        deleteRequest();
    }//GEN-LAST:event_btnDeclineMouseClicked

    private void btnAcceptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAcceptMouseClicked
        // TODO add your handling code here:
        acceptRequest();
    }//GEN-LAST:event_btnAcceptMouseClicked

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
            java.util.logging.Logger.getLogger(Request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Request().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAccept;
    private javax.swing.JPanel btnDecline;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblRequest;
    private javax.swing.JTextField txtBookRequest;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
