package jframe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Karina Ganich
 * 
 * Class for issue book page
 */
public class IssueBook extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(IssueBook.class.getName());

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }
    
    /**
     * Method for fetching book details from db
     */
    public void getBookDetails(){
        int bookID = Integer.parseInt(txt_bookID.getText());
        try{
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
            
            PreparedStatement ps = conn.prepareStatement("select * from book_details where book_id = ?");
            ps.setInt(1, bookID);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                lbl_bookID.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
                txt_bookError.setText("");
                
            }else{
                txt_bookError.setText("Invalid book ID");
                lbl_bookID.setText("");
                lbl_bookName.setText("");
                lbl_author.setText("");
                lbl_quantity.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method for fetching student details from db
     */
    public void getStudentDetails(){
        int studentID = Integer.parseInt(txt_studentID.getText());
        try{
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            
            // 1. Create student table if not exists
            String createTableSQL = "CREATE TABLE IF NOT EXISTS student_details ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "student_id INT,"
                    + "student_name VARCHAR(255),"
                    + "course VARCHAR(255),"
                    + "branch VARCHAR(255))";
            st.execute(createTableSQL);

            // 2. Check if the table is empty
            ResultSet set = st.executeQuery("SELECT COUNT(*) FROM student_details");
            set.next();
            int count = set.getInt(1);

            // 3. Insert default students if empty
            if (count == 0) {
                String insertSQL = "INSERT INTO student_details (student_id, student_name, course, branch) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(insertSQL);

                ps.setInt(1, 1);
                ps.setString(2, "Petr Popelka");
                ps.setString(3, "BSC");
                ps.setString(4, "IT");
                ps.executeUpdate();


                System.out.println("Inserted default students.");
            } else {
                System.out.println("Students already exist. Skipping insert.");
            }
            
            PreparedStatement ps = conn.prepareStatement("select * from student_details where student_id = ?");
            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                lbl_studentID.setText(rs.getString("student_id"));
                lbl_studentName.setText(rs.getString("student_name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
                txt_studentError.setText("");
                
            }else {
                txt_studentError.setText("Invalid student ID");
                lbl_studentID.setText("");
                lbl_studentName.setText("");
                lbl_course.setText("");
                lbl_branch.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Method for inserting issue book to db
     */
    public boolean issueBook(){
        boolean isIssued = false;
        int bookID = Integer.parseInt(txt_bookID.getText());
        int studentID = Integer.parseInt(txt_studentID.getText());
        String bookName = lbl_bookName.getText();
        String studentName = lbl_studentName.getText();
        
        java.util.Date issue = date_issue.getDatoFecha();
        java.util.Date due = date_due.getDatoFecha();
        
        Date issueDate = new Date(issue.getTime());
        Date dueDate = new Date(due.getTime());
        
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
        
            // Create issue book table if not exists
            String createTableSQL = "CREATE TABLE IF NOT EXISTS issue_book_details ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "book_id INT,"
                    + "book_name VARCHAR(255),"
                    + "student_id INT,"
                    + "student_name VARCHAR(255),"
                    + "issue_date DATE,"
                    + "due_date DATE,"
                    + "status VARCHAR(255))";
            st.execute(createTableSQL);
            
            String sql = "insert into issue_book_details(book_id, book_name, student_id, student_name, issue_date, due_date, status) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookID);
            ps.setString(2, bookName);
            ps.setInt(3, studentID);
            ps.setString(4, studentName);
            ps.setDate(5, issueDate);
            ps.setDate(6, dueDate);
            ps.setString(7, "pending");
            
            int rowCount = ps.executeUpdate();
            if (rowCount>0){
                isIssued = true;
            } else {
                isIssued = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isIssued;
    }
    
    /**
     * Method for updating book count
     */
    public void updateBookCount(){
        int bookID = Integer.parseInt(txt_bookID.getText());
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookID);
            int rowCount = ps.executeUpdate();
            if (rowCount>0){
                JOptionPane.showMessageDialog(this, "book count updated");
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount -1));
            } else {
                JOptionPane.showMessageDialog(this, "failed update book count");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Method for checking duplicated book
     */
    public boolean isIssuedAlready(){
        boolean isIssued = false;
        int bookID = Integer.parseInt(txt_bookID.getText());
        int studentID = Integer.parseInt(txt_studentID.getText());
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookID);
            ps.setInt(2, studentID);
            ps.setString(3, "pending");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                isIssued = true;
            } else {
                isIssued = false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return isIssued;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_bookID = new app.bolivia.swing.JCTextField();
        txt_studentID = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        date_issue = new rojeru_san.componentes.RSDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        date_due = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        jLabel1 = new javax.swing.JLabel();
        txt_studentError = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_bookID = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        lbl_studentID = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_bookError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 102, 255));

        jLabel2.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Katari", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Issue book");

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

        txt_studentID.setBackground(new java.awt.Color(153, 102, 255));
        txt_studentID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_studentID.setPlaceholder("Enter");
        txt_studentID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIDFocusLost(evt);
            }
        });
        txt_studentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIDActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Enter Book ID");

        jLabel12.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Select Issue date");

        date_issue.setBackground(new java.awt.Color(153, 102, 255));
        date_issue.setForeground(new java.awt.Color(153, 102, 255));
        date_issue.setAutoscrolls(true);
        date_issue.setColorBackground(new java.awt.Color(153, 102, 255));
        date_issue.setColorButtonHover(new java.awt.Color(153, 102, 255));
        date_issue.setColorForeground(new java.awt.Color(153, 102, 255));
        date_issue.setColorSelForeground(new java.awt.Color(153, 102, 255));
        date_issue.setColorTextDiaActual(new java.awt.Color(153, 102, 255));
        date_issue.setFont(new java.awt.Font("Katari", 0, 13)); // NOI18N
        date_issue.setFuente(new java.awt.Font("Katari", 1, 14)); // NOI18N
        date_issue.setPlaceholder("Select issue date");

        jLabel13.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Enter Student ID");

        jLabel14.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Due date");

        date_due.setBackground(new java.awt.Color(153, 102, 255));
        date_due.setForeground(new java.awt.Color(153, 102, 255));
        date_due.setAutoscrolls(true);
        date_due.setColorBackground(new java.awt.Color(153, 102, 255));
        date_due.setColorButtonHover(new java.awt.Color(153, 102, 255));
        date_due.setColorForeground(new java.awt.Color(153, 102, 255));
        date_due.setColorSelForeground(new java.awt.Color(153, 102, 255));
        date_due.setColorTextDiaActual(new java.awt.Color(153, 102, 255));
        date_due.setFont(new java.awt.Font("Katari", 0, 13)); // NOI18N
        date_due.setFuente(new java.awt.Font("Katari", 1, 14)); // NOI18N
        date_due.setPlaceholder("Select due date");

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(204, 153, 255));
        rSMaterialButtonCircle2.setActionCommand("ISSUE BOOK");
        rSMaterialButtonCircle2.setFont(new java.awt.Font("Katari", 0, 17)); // NOI18N
        rSMaterialButtonCircle2.setLabel("ISSUE BOOK");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_studentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_bookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date_issue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date_due, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonCircle2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(36, 36, 36)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_bookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_studentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_issue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(date_due, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(rSMaterialButtonCircle2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 850));

        jLabel1.setFont(new java.awt.Font("Katari", 1, 24)); // NOI18N
        jLabel1.setText("Book details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, -1));

        txt_studentError.setFont(new java.awt.Font("Katari", 0, 24)); // NOI18N
        txt_studentError.setForeground(new java.awt.Color(204, 102, 255));
        jPanel1.add(txt_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 480, 240, 110));

        lbl_quantity.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jPanel1.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, 160, 30));

        jLabel5.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel5.setText("Book name:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel6.setText("Author:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, -1, -1));

        jLabel7.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel7.setText("Book ID:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, -1, -1));

        lbl_bookID.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jPanel1.add(lbl_bookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, 160, 30));

        lbl_bookName.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jPanel1.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, 160, 30));

        lbl_author.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jPanel1.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 160, 30));

        lbl_studentName.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jPanel1.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 510, 160, 30));

        lbl_course.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jPanel1.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 560, 160, 30));

        lbl_branch.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jPanel1.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 600, 160, 30));

        lbl_studentID.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jPanel1.add(lbl_studentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 160, 30));

        jLabel15.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel15.setText("Branch:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 600, -1, -1));

        jLabel16.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel16.setText("Course:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 560, -1, -1));

        jLabel17.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel17.setText("Student name:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 510, -1, -1));

        jLabel18.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel18.setText("Student ID:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, -1, -1));

        jLabel19.setFont(new java.awt.Font("Katari", 1, 24)); // NOI18N
        jLabel19.setText("Student details");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, -1, -1));

        jLabel8.setFont(new java.awt.Font("Katari", 0, 18)); // NOI18N
        jLabel8.setText("Quantity:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, -1, -1));

        txt_bookError.setFont(new java.awt.Font("Katari", 0, 24)); // NOI18N
        txt_bookError.setForeground(new java.awt.Color(204, 102, 255));
        jPanel1.add(txt_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 160, 240, 110));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        HomePage page = new HomePage();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txt_bookIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIDFocusLost
        if (!txt_bookID.getText().equals("")){
            getBookDetails();
        }
    }//GEN-LAST:event_txt_bookIDFocusLost

    private void txt_bookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIDActionPerformed
        
    }//GEN-LAST:event_txt_bookIDActionPerformed

    private void txt_studentIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIDFocusLost
        if (!txt_studentID.getText().equals("")){
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_studentIDFocusLost

    private void txt_studentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIDActionPerformed
        
    }//GEN-LAST:event_txt_studentIDActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (lbl_quantity.getText().equals(0)) {
            JOptionPane.showMessageDialog(this, "book is not available");
            return;
        }
        if (!isIssuedAlready()){
            if(issueBook()){
                JOptionPane.showMessageDialog(this, "book issued successfully");
                updateBookCount();
            } else {
                JOptionPane.showMessageDialog(this, "book issue failed");
            }
        } else {
            JOptionPane.showMessageDialog(this, "this student already has this book");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new IssueBook().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_due;
    private rojeru_san.componentes.RSDateChooser date_issue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookID;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentID;
    private javax.swing.JLabel lbl_studentName;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private javax.swing.JLabel txt_bookError;
    private app.bolivia.swing.JCTextField txt_bookID;
    private javax.swing.JLabel txt_studentError;
    private app.bolivia.swing.JCTextField txt_studentID;
    // End of variables declaration//GEN-END:variables
}
