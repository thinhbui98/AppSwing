/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import controller.DAOBook;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh Bui
 */
public class Book extends javax.swing.JFrame {

    /**
     * Creates new form Book
     */
    DefaultTableModel model;
    private ArrayList<model.Book> list;
    private int userId;
    private int typeUser;
    public Book() {
        initComponents();
        list = new DAOBook().getListBookSearched("SELECT books.*,categories.categoryname FROM books INNER JOIN categories ON categories.id = books.category_id WHERE books.deleted = 1 ");
        model = (DefaultTableModel) tblBook.getModel();
        showTable();
    }
    
    public Book(int param, int type) {
        initComponents();
        userId = param;
        typeUser = type;
        list = new DAOBook().getListBookSearched("SELECT books.*,categories.categoryname FROM books INNER JOIN categories ON categories.id = books.category_id WHERE books.deleted = 1 ");
        model = (DefaultTableModel) tblBook.getModel();
        showTable();
    }

    public void showTable() {
        tblBook.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblBook.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblBook.getColumnModel().getColumn(2).setPreferredWidth(70);     
        tblBook.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblBook.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        list.forEach((s) -> {                                                    
            model.addRow(new Object[] {
                s.getId(), s.getBookname(), s.getCategoryname(), s.getQuantity()
            });
        });
    }
    
    private void loadData() {
        list = new DAOBook().getListBookSearched("SELECT books.*,categories.categoryname FROM books INNER JOIN categories ON categories.id = books.category_id WHERE books.deleted = 1");
                                                               
        for (int i = model.getRowCount()-1; i >= 0; i--) {
            model.removeRow(i);                                
        }                                                      

        tblBook.repaint();
        showTable();
    }
    
    private void addBook() {
        model.Book b = new model.Book();
        
        b.setBookname(txtBookname.getText());
        b.setCategory_id(Integer.parseInt(txtCategory.getText()));
        b.setDescription(txtDescription.getText());
        b.setQuantity(Integer.parseInt(txtQuantity.getText()));
        list.add(b);
        
        if(new DAOBook().addBook(b)) {
            JOptionPane.showMessageDialog(null, "ĐÃ THÊM");
            loadData();
        }
        else {
            JOptionPane.showMessageDialog(null, "KHÔNG THỂ THÊM");
        }
    }
    
    private void editBook() {
        int r = tblBook.getSelectedRow();
        model.Book b = new model.Book();
        b.setId(Integer.parseInt(txtId.getText()));
        b.setBookname(txtBookname.getText());
        b.setCategory_id(Integer.parseInt(txtCategory.getText()));
        b.setDescription(txtDescription.getText());
        b.setQuantity(Integer.parseInt(txtQuantity.getText()));
                                                            
        list.set(r, b);                                     
                                                            
        if(new DAOBook().updateBook(b)) {
            model.Book bk = list.get(r);
            JOptionPane.showMessageDialog(null, "ĐÃ SỬA");
            txtBookname.setText(bk.getBookname());
            txtCategory.setText(String.valueOf(bk.getCategory_id()));
            txtDescription.setText(bk.getDescription());
            txtQuantity.setText(String.valueOf(bk.getQuantity()));
            loadData();
        }
        else {
            JOptionPane.showMessageDialog(null, "KHÔNG THỂ SỬA");
        }
        
    }
    
    private void deleteBook() {
        int r = tblBook.getSelectedRow();                  
        model.Book b = new model.Book();
        
        b.setId(Integer.parseInt(txtId.getText()));            

        list.remove(r);                                             
        
        if(new DAOBook().deleteBook(b)) {                
            JOptionPane.showMessageDialog(null, "ĐÃ XÓA");     
            model.removeRow(r);                           
        }                                                       
        else {
            JOptionPane.showMessageDialog(null, "KHÔNG THỂ XÓA");
        }
    }
    
    private void searchBook(){
        
        list = new DAOBook().getListBookSearched("SELECT books.*,categories.categoryname FROM books INNER JOIN categories ON categories.id = books.category_id WHERE books.deleted = 1 AND books.bookname LIKE '%" + txtSearch.getText() + "%'");
                                                               
        for (int i = model.getRowCount()-1; i >= 0; i--) {
            model.removeRow(i);                                
        }                                                      

        tblBook.repaint();
        showTable();
    }
    
    private void addRequest(){
        int r = tblBook.getSelectedRow();
        model.Book bk = list.get(r);
        if (bk.getQuantity() < 1) {
            JOptionPane.showMessageDialog(null, "SÁCH ĐÃ HẾT, VUI LÒNG CHỌN SÁCH KHÁC!");
        } else {
            dispose();
            if (typeUser != 1) {
                new RequestStudent(bk.getId(),userId).setVisible(true);
            } else {
                new Request(bk.getId(),userId,typeUser).setVisible(true);
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblBook = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBookname = new javax.swing.JTextField();
        txtCategory = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnAddBook = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnEditBook = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnDeleteBook = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sách", "Danh mục", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBookMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBook);
        if (tblBook.getColumnModel().getColumnCount() > 0) {
            tblBook.getColumnModel().getColumn(0).setResizable(false);
            tblBook.getColumnModel().getColumn(1).setResizable(false);
            tblBook.getColumnModel().getColumn(2).setResizable(false);
            tblBook.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton1.setText("Thêm vào yêu cầu");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Thêm danh mục");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông tin sách");

        jLabel2.setText("Tên sách: ");

        jLabel3.setText("Danh mục:");

        jLabel4.setText("Số lượng:");

        jLabel5.setText("Mô tả:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        jLabel6.setText("Id:");

        txtId.setEditable(false);

        btnAddBook.setBackground(new java.awt.Color(0, 255, 0));
        btnAddBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddBookMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Thêm");

        javax.swing.GroupLayout btnAddBookLayout = new javax.swing.GroupLayout(btnAddBook);
        btnAddBook.setLayout(btnAddBookLayout);
        btnAddBookLayout.setHorizontalGroup(
            btnAddBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAddBookLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel7)
                .addGap(40, 40, 40))
        );
        btnAddBookLayout.setVerticalGroup(
            btnAddBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnEditBook.setBackground(new java.awt.Color(0, 204, 255));
        btnEditBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditBookMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sửa");

        javax.swing.GroupLayout btnEditBookLayout = new javax.swing.GroupLayout(btnEditBook);
        btnEditBook.setLayout(btnEditBookLayout);
        btnEditBookLayout.setHorizontalGroup(
            btnEditBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEditBookLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel8)
                .addGap(40, 40, 40))
        );
        btnEditBookLayout.setVerticalGroup(
            btnEditBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnDeleteBook.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteBookMouseClicked(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Xóa");

        javax.swing.GroupLayout btnDeleteBookLayout = new javax.swing.GroupLayout(btnDeleteBook);
        btnDeleteBook.setLayout(btnDeleteBookLayout);
        btnDeleteBookLayout.setHorizontalGroup(
            btnDeleteBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDeleteBookLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel9)
                .addGap(40, 40, 40))
        );
        btnDeleteBookLayout.setVerticalGroup(
            btnDeleteBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtCategory))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtQuantity)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBookname)
                                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAddBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(btnEditBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnDeleteBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBookname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteBook, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        btnSearch.setText("Tìm kiếm");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        txtSearch.setText("Tìm kiếm...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(320, 320, 320)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookMouseClicked
        // TODO add your handling code here:
        int r = tblBook.getSelectedRow();
        if(r != -1) {
            model.Book bk = list.get(r);
            txtId.setText(String.valueOf(bk.getId()));
            txtBookname.setText(bk.getBookname());
            txtCategory.setText(bk.getCategoryname());
//            txtCategory.setText(String.valueOf(bk.getCategory_id()));
            txtDescription.setText(bk.getDescription());
            txtQuantity.setText(String.valueOf(bk.getQuantity()));
        }
    }//GEN-LAST:event_tblBookMouseClicked

    private void btnAddBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddBookMouseClicked
        // TODO add your handling code here:
        addBook();
    }//GEN-LAST:event_btnAddBookMouseClicked

    private void btnEditBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditBookMouseClicked
        // TODO add your handling code here:
        editBook();
    }//GEN-LAST:event_btnEditBookMouseClicked

    private void btnDeleteBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteBookMouseClicked
        // TODO add your handling code here:
        deleteBook();
    }//GEN-LAST:event_btnDeleteBookMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        searchBook();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        dispose();
        new Category().setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        addRequest();
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Book().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAddBook;
    private javax.swing.JPanel btnDeleteBook;
    private javax.swing.JPanel btnEditBook;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBook;
    private javax.swing.JTextField txtBookname;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
