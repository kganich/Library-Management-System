package jframe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Karina Ganich
 * 
 * Class for home page
 */
public class HomePage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomePage.class.getName());

    /**
     * Default table model
     */
    DefaultTableModel model;
    
    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
        setStudentDetailsToTable();
        setBookDetailsToTable();
        setDataToCards();
    }
    
    /**
     * Method for setting the student details into the table
     */
    public void setStudentDetailsToTable(){
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            
            // 1. Create books table if not exists
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

            // 3. Insert default books if empty
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
            
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from student_details");
            
            while(rs.next()){
                String studentID = rs.getString("student_id");
                String studentName = rs.getString("student_name");
                String course = rs.getString("course");
                String branch = rs.getString("branch");
                
                Object[] obj = {studentID, studentName, course, branch};
                model = (DefaultTableModel) tbl_studentDetails.getModel();
                model.addRow(obj);
             }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Method for setting the book details into the table
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
     * Method for setting data to four cards
     */
    public void setDataToCards(){
        Statement st = null;
        ResultSet rs = null;
        
        long date = System.currentTimeMillis();
        Date today = new Date(date);
        
        try {
            Connection conn = DBConnection.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from book_details");
            rs.last();
            lbl_books.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from student_details");
            rs.last();
            lbl_students.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from issue_book_details where status = '"+"pending"+"'");
            rs.last();
            lbl_issue.setText(Integer.toString(rs.getRow()));
            
            rs = st.executeQuery("select * from issue_book_details where due_date < '"+today+"' and status = '"+"pending"+"' ");
            rs.last();
            lbl_defaulter.setText(Integer.toString(rs.getRow()));
            
        } catch (Exception e){
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lbl_books = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbl_students = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbl_issue = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lbl_defaulter = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_studentDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        panelPieChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Katari", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/male_user_50px.png"))); // NOI18N
        jLabel1.setText("Welcome Admin");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1179, 26, -1, -1));

        jLabel3.setFont(new java.awt.Font("Katari", 1, 25)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Library Management System");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1910, 100));

        jPanel2.setBackground(new java.awt.Color(204, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 102, 255));

        jLabel7.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Books_26px.png"))); // NOI18N
        jLabel7.setText("Manage Books");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel9.setText("Manage Students");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 380, -1));

        jPanel4.setBackground(new java.awt.Color(204, 102, 255));

        jLabel10.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Sell_26px.png"))); // NOI18N
        jLabel10.setText("Issue book");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel10)
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 81, Short.MAX_VALUE)
                .addComponent(jLabel10))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 380, -1));

        jPanel5.setBackground(new java.awt.Color(153, 102, 255));

        jLabel6.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home_24px.png"))); // NOI18N
        jLabel6.setText("Home Page");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel6)
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 380, 80));

        jPanel6.setBackground(new java.awt.Color(204, 102, 255));

        jLabel11.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabel11.setText("Return book");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel12.setText("View records");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addContainerGap(195, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 380, -1));

        jLabel13.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Books_26px.png"))); // NOI18N
        jLabel13.setText("View issued books");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, -1, -1));

        jLabel14.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Conference_26px.png"))); // NOI18N
        jLabel14.setText("Defaulter list");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, -1, -1));

        jPanel7.setBackground(new java.awt.Color(153, 51, 255));

        jLabel8.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Exit_26px.png"))); // NOI18N
        jLabel8.setText("Logout");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel8)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 380, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 310, 920));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(153, 102, 255)));
        jPanel9.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_books.setFont(new java.awt.Font("Katari", 0, 30)); // NOI18N
        lbl_books.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_books.setText("10");
        jPanel9.add(lbl_books, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 43, -1, -1));

        jLabel15.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel15.setText("Number of books");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 10, -1, -1));

        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(153, 102, 255)));
        jPanel10.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_students.setFont(new java.awt.Font("Katari", 0, 30)); // NOI18N
        lbl_students.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_People_50px.png"))); // NOI18N
        lbl_students.setText("10");
        jPanel10.add(lbl_students, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jPanel8.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 43, -1, -1));

        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(153, 102, 255)));
        jPanel11.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_issue.setFont(new java.awt.Font("Katari", 0, 30)); // NOI18N
        lbl_issue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Sell_50px.png"))); // NOI18N
        lbl_issue.setText("10");
        jPanel11.add(lbl_issue, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jPanel8.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 43, -1, -1));

        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(153, 102, 255)));
        jPanel12.setPreferredSize(new java.awt.Dimension(260, 100));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_defaulter.setFont(new java.awt.Font("Katari", 0, 30)); // NOI18N
        lbl_defaulter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_defaulter.setText("10");
        jPanel12.add(lbl_defaulter, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jPanel8.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(872, 43, -1, -1));

        jLabel20.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel20.setText("Number of students");
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 6, -1, -1));

        jLabel21.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel21.setText("Issued books");
        jPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 6, -1, -1));

        jLabel22.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel22.setText("Defaulter list");
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 6, -1, -1));

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
        jScrollPane1.setViewportView(tbl_bookDetails);

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 520, 824, 255));

        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student id", "Student name", "course", "branch"
            }
        ));
        tbl_studentDetails.setColorBackgoundHead(new java.awt.Color(204, 102, 255));
        tbl_studentDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_studentDetails.setColorFilasForeground1(new java.awt.Color(204, 102, 255));
        tbl_studentDetails.setColorFilasForeground2(new java.awt.Color(204, 102, 255));
        tbl_studentDetails.setColorSelBackgound(new java.awt.Color(204, 102, 255));
        tbl_studentDetails.setFont(new java.awt.Font("Katari", 0, 13)); // NOI18N
        tbl_studentDetails.setFuenteFilas(new java.awt.Font("Katari", 1, 14)); // NOI18N
        tbl_studentDetails.setFuenteFilasSelect(new java.awt.Font("Katari", 1, 14)); // NOI18N
        tbl_studentDetails.setFuenteHead(new java.awt.Font("Katari", 1, 18)); // NOI18N
        tbl_studentDetails.setIntercellSpacing(new java.awt.Dimension(1, 1));
        tbl_studentDetails.setRowHeight(40);
        jScrollPane2.setViewportView(tbl_studentDetails);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 210, 824, 261));

        jLabel23.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel23.setText("Student details");
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 173, -1, -1));

        jLabel24.setFont(new java.awt.Font("Katari", 1, 18)); // NOI18N
        jLabel24.setText("Book details");
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 483, -1, -1));

        panelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel8.add(panelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1467, 228, -1, -1));

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 1590, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        ManageBooks page = new ManageBooks();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        ManageStudents page = new ManageStudents();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        IssueBook page = new IssueBook();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        ReturnBook page = new ReturnBook();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        ViewRecords page = new ViewRecords();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        IssueBookDetails page = new IssueBookDetails();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        Defaulter page = new Defaulter();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        LoginPage page = new LoginPage();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new HomePage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_books;
    private javax.swing.JLabel lbl_defaulter;
    private javax.swing.JLabel lbl_issue;
    private javax.swing.JLabel lbl_students;
    private javax.swing.JPanel panelPieChart;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private rojeru_san.complementos.RSTableMetro tbl_studentDetails;
    // End of variables declaration//GEN-END:variables
}
