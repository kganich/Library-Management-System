package jframe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Karina Ganich
 * 
 * Class for manage books page
 */
public class ManageBooks extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageBooks.class.getName());
    
    String bookName, author;
    int bookID, quantity;
    DefaultTableModel model;

    /** Creates new form ManageBooks */
    public ManageBooks() {
        initComponents();
        setBookDetailsToTable();
    }
    
    /**
     * Method for setting book details into table
     */
    public void setBookDetailsToTable(){
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            
            // 1. Create books table if not exists
            String createTableSQL = "CREATE TABLE IF NOT EXISTS book_details ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "book_id INT,"
                    + "book_name VARCHAR(255),"
                    + "author VARCHAR(255),"
                    + "quantity INT)";
            st.execute(createTableSQL);

            // 2. Check if the table is empty
            ResultSet set = st.executeQuery("SELECT COUNT(*) FROM book_details");
            set.next();
            int count = set.getInt(1);

            // 3. Insert default books if empty
            if (count == 0) {
                String insertSQL = "INSERT INTO book_details (book_id, book_name, author, quantity) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(insertSQL);

                ps.setInt(1, 1);
                ps.setString(2, "The Hobbit");
                ps.setString(3, "J.R.R. Tolkien");
                ps.setInt(4, 10);
                ps.executeUpdate();

                ps.setInt(1, 2);
                ps.setString(2, "1984");
                ps.setString(3, "George Orwell");
                ps.setInt(4, 15);
                ps.executeUpdate();

                System.out.println("Inserted default books.");
            } else {
                System.out.println("Books already exist. Skipping insert.");
            }
            
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from book_details");
            
            while(rs.next()){
                String bookID = rs.getString("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                
                Object[] obj = {bookID, bookName, author, quantity};
                model = (DefaultTableModel) tbl_bookDetails.getModel();
                model.addRow(obj);
             }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Method for adding book to db
     */
    public boolean addBook(){
        boolean isAdded = false;
        bookID = Integer.parseInt(txt_bookID.getText());
        bookName = txt_bookName.getText();
        author = txt_author.getText();
        quantity = Integer.parseInt(txt_quantity.getText());
        try {
            Connection conn = DBConnection.getConnection();
            String query = "insert into book_details (book_id, book_name, author, quantity) values(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, bookID);
            st.setString(2, bookName);
            st.setString(3, author);
            st.setInt(4, quantity);
            
            int rowCount = st.executeUpdate();
            if (rowCount > 0){
                isAdded = true;
            }else {
                isAdded = false;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return isAdded;
        
    }
    
    /**
     * Method for clearing the table
     */
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);
        
    }
    
    /**
     * Method for updating book details
     */
    public boolean updateBook(){
        boolean isUpdated = false;
        bookID = Integer.parseInt(txt_bookID.getText());
        bookName = txt_bookName.getText();
        author = txt_author.getText();
        quantity = Integer.parseInt(txt_quantity.getText());
        
        try {
            Connection conn = DBConnection.getConnection();
            String query = "update book_details set book_name = ?, author = ?, quantity = ? where book_id = ?"; 
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, bookName);
            st.setString(2, author);
            st.setInt(3, quantity);
            st.setInt(4, bookID);
            
            int rowCount = st.executeUpdate();
            if (rowCount > 0){
                isUpdated = true;
            }else {
                isUpdated = false;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return isUpdated;
    }
    
    /**
     * Method for deleting book from db
     */
    public boolean deleteBook(){
        boolean isDeleted = false;
        bookID = Integer.parseInt(txt_bookID.getText());
        
        try {
            Connection conn = DBConnection.getConnection();
            String query = "delete from book_details where book_id = ?"; 
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, bookID);
            
            int rowCount = st.executeUpdate();
            if (rowCount > 0){
                isDeleted = true;
            }else {
                isDeleted = false;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return isDeleted;
    }
        

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txt_bookID = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_bookName = new app.bolivia.swing.JCTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_username2 = new app.bolivia.swing.JCTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_username3 = new app.bolivia.swing.JCTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_author = new app.bolivia.swing.JCTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_quantity = new app.bolivia.swing.JCTextField();
        jLabel16 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new necesario.RSMaterialButtonCircle();
        txt_password3 = new app.bolivia.swing.JCTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Enter Book ID");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 230, 30));

        txt_bookID.setBackground(new java.awt.Color(153, 102, 255));
        txt_bookID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookID.setPlaceholder("Enter ");
        txt_bookID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIDFocusLost(evt);
            }
        });
        txt_bookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIDActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        jLabel13.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Enter book name");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 230, 30));

        txt_bookName.setBackground(new java.awt.Color(153, 102, 255));
        txt_bookName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_bookName.setPlaceholder("Enter");
        txt_bookName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookNameFocusLost(evt);
            }
        });
        txt_bookName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

        jPanel3.setBackground(new java.awt.Color(204, 102, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel12.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Enter Book ID");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 230, 30));

        jLabel15.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Contact_26px.png"))); // NOI18N
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 50, 50));

        txt_username2.setBackground(new java.awt.Color(153, 102, 255));
        txt_username2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_username2.setPlaceholder("Enter name");
        txt_username2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_username2FocusLost(evt);
            }
        });
        txt_username2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_username2ActionPerformed(evt);
            }
        });
        jPanel3.add(txt_username2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        jLabel19.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Enter book name");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 230, 30));

        jLabel20.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 50, 50));

        txt_username3.setBackground(new java.awt.Color(153, 102, 255));
        txt_username3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_username3.setPlaceholder("Enter name");
        txt_username3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_username3FocusLost(evt);
            }
        });
        txt_username3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_username3ActionPerformed(evt);
            }
        });
        jPanel3.add(txt_username3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

        jLabel21.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 50, 50));

        jLabel22.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Enter Author name");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 230, 30));

        txt_author.setBackground(new java.awt.Color(153, 102, 255));
        txt_author.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_author.setPlaceholder("Enter");
        txt_author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_authorActionPerformed(evt);
            }
        });
        jPanel3.add(txt_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, -1));

        jLabel18.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Enter Quantity");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 230, 30));

        txt_quantity.setBackground(new java.awt.Color(153, 102, 255));
        txt_quantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_quantity.setPlaceholder("Enter");
        txt_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantityActionPerformed(evt);
            }
        });
        jPanel3.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, -1, -1));

        jLabel16.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Unit_26px.png"))); // NOI18N
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 50, 50));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(153, 102, 255));
        rSMaterialButtonCircle1.setText("DELETE");
        rSMaterialButtonCircle1.setFont(new java.awt.Font("Katari", 0, 17)); // NOI18N
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel3.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 690, 210, 60));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(204, 153, 255));
        rSMaterialButtonCircle2.setText("ADD");
        rSMaterialButtonCircle2.setFont(new java.awt.Font("Katari", 0, 17)); // NOI18N
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel3.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, 210, 60));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(153, 153, 255));
        rSMaterialButtonCircle3.setText("UPDATE");
        rSMaterialButtonCircle3.setFont(new java.awt.Font("Katari", 0, 17)); // NOI18N
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel3.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 620, 210, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 860));

        txt_password3.setBackground(new java.awt.Color(153, 102, 255));
        txt_password3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_password3.setPlaceholder("Enter password");
        txt_password3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_password3ActionPerformed(evt);
            }
        });
        jPanel1.add(txt_password3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, -1));

        jLabel23.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 50, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 860));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book id", "Book name", "Author", "Quantity"
            }
        ));
        tbl_bookDetails.setColorBackgoundHead(new java.awt.Color(204, 102, 255));
        tbl_bookDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookDetails.setColorFilasForeground1(new java.awt.Color(204, 102, 255));
        tbl_bookDetails.setColorFilasForeground2(new java.awt.Color(204, 102, 255));
        tbl_bookDetails.setColorSelBackgound(new java.awt.Color(204, 102, 255));
        tbl_bookDetails.setFont(new java.awt.Font("Katari", 0, 13)); // NOI18N
        tbl_bookDetails.setFuenteFilas(new java.awt.Font("Katari", 1, 14)); // NOI18N
        tbl_bookDetails.setFuenteFilasSelect(new java.awt.Font("Katari", 1, 14)); // NOI18N
        tbl_bookDetails.setFuenteHead(new java.awt.Font("Katari", 1, 18)); // NOI18N
        tbl_bookDetails.setIntercellSpacing(new java.awt.Dimension(1, 1));
        tbl_bookDetails.setRowHeight(40);
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bookDetails);

        jLabel1.setFont(new java.awt.Font("Katari", 1, 24)); // NOI18N
        jLabel1.setText("Manage books");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(287, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 920, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bookIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIDFocusLost

    }//GEN-LAST:event_txt_bookIDFocusLost

    private void txt_bookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIDActionPerformed
        
    }//GEN-LAST:event_txt_bookIDActionPerformed

    private void txt_bookNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookNameFocusLost

    }//GEN-LAST:event_txt_bookNameFocusLost

    private void txt_bookNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookNameActionPerformed
        
    }//GEN-LAST:event_txt_bookNameActionPerformed

    private void txt_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantityActionPerformed
        
    }//GEN-LAST:event_txt_quantityActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        HomePage page = new HomePage();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txt_authorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_authorActionPerformed
        
    }//GEN-LAST:event_txt_authorActionPerformed

    private void txt_password3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_password3ActionPerformed
   
    }//GEN-LAST:event_txt_password3ActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        if (deleteBook()){
            JOptionPane.showMessageDialog(this, "Book deleted");
            clearTable();
            setBookDetailsToTable();
        } else{
            JOptionPane.showMessageDialog(this, "Delete book failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (addBook()){
            JOptionPane.showMessageDialog(this, "Book added");
            clearTable();
            setBookDetailsToTable();
        } else{
            JOptionPane.showMessageDialog(this, "Add book failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (updateBook()){
            JOptionPane.showMessageDialog(this, "Book updated");
            clearTable();
            setBookDetailsToTable();
        } else{
            JOptionPane.showMessageDialog(this, "Update book failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
        int rowNumber = tbl_bookDetails.getSelectedRow();
        TableModel model = tbl_bookDetails.getModel();
        txt_bookID.setText(model.getValueAt(rowNumber, 0).toString());
        txt_bookName.setText(model.getValueAt(rowNumber, 1).toString());
        txt_author.setText(model.getValueAt(rowNumber, 2).toString());
        txt_quantity.setText(model.getValueAt(rowNumber, 3).toString());
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

    private void txt_username2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_username2ActionPerformed
        
    }//GEN-LAST:event_txt_username2ActionPerformed

    private void txt_username2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_username2FocusLost
        
    }//GEN-LAST:event_txt_username2FocusLost

    private void txt_username3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_username3ActionPerformed
        
    }//GEN-LAST:event_txt_username3ActionPerformed

    private void txt_username3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_username3FocusLost
        
    }//GEN-LAST:event_txt_username3FocusLost

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ManageBooks().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private app.bolivia.swing.JCTextField txt_author;
    private app.bolivia.swing.JCTextField txt_bookID;
    private app.bolivia.swing.JCTextField txt_bookName;
    private app.bolivia.swing.JCTextField txt_password3;
    private app.bolivia.swing.JCTextField txt_quantity;
    private app.bolivia.swing.JCTextField txt_username2;
    private app.bolivia.swing.JCTextField txt_username3;
    // End of variables declaration//GEN-END:variables

}
