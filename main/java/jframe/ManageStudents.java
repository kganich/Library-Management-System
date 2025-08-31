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
 * Class for manage students page
 */
public class ManageStudents extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ManageStudents.class.getName());
    
    String studentName, course, branch;
    int studentID;
    DefaultTableModel model;

    /** Creates new form ManageStudents */
    public ManageStudents() {
        initComponents();
        setStudentDetailsToTable();
    }
    
    /**
     * Method for setting student details into table
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
     * Method for adding book to db
     */
    public boolean addStudent(){
        boolean isAdded = false;
        studentID = Integer.parseInt(txt_studentID.getText());
        studentName = txt_studentName.getText();
        course = combo_course.getSelectedItem().toString();
        branch = combo_branch.getSelectedItem().toString();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "insert into student_details (student_id, student_name, course, branch) values(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, studentID);
            st.setString(2, studentName);
            st.setString(3, course);
            st.setString(4, branch);
            
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
        DefaultTableModel model = (DefaultTableModel) tbl_studentDetails.getModel();
        model.setRowCount(0);
        
    }
    
    /**
     * Method for updating students
     */
    public boolean updateStudent(){
        boolean isUpdated = false;
        studentID = Integer.parseInt(txt_studentID.getText());
        studentName = txt_studentName.getText();
        course = combo_course.getSelectedItem().toString();
        branch = combo_branch.getSelectedItem().toString();
        
        try {
            Connection conn = DBConnection.getConnection();
            String query = "update student_details set student_name = ?, course = ?, branch = ? where student_id = ?"; 
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, studentName);
            st.setString(2, course);
            st.setString(3, branch);
            st.setInt(4, studentID);
            
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
     * Method for deleting student from db
     */
    public boolean deleteStudent(){
        boolean isDeleted = false;
        studentID = Integer.parseInt(txt_studentID.getText());
        
        try {
            Connection conn = DBConnection.getConnection();
            String query = "delete from student_details where student_id = ?"; 
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, studentID);
            
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
        txt_studentID = new app.bolivia.swing.JCTextField();
        txt_studentName = new app.bolivia.swing.JCTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_username2 = new app.bolivia.swing.JCTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_username3 = new app.bolivia.swing.JCTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new necesario.RSMaterialButtonCircle();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        combo_branch = new javax.swing.JComboBox<>();
        combo_course = new javax.swing.JComboBox<>();
        txt_password3 = new app.bolivia.swing.JCTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_studentDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_studentID.setBackground(new java.awt.Color(153, 102, 255));
        txt_studentID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_studentID.setPlaceholder("Enter ");
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
        jPanel1.add(txt_studentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        txt_studentName.setBackground(new java.awt.Color(153, 102, 255));
        txt_studentName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_studentName.setPlaceholder("Enter");
        txt_studentName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentNameFocusLost(evt);
            }
        });
        txt_studentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentNameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

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

        jLabel15.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Contact_26px.png"))); // NOI18N
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 50, 50));

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
        jLabel22.setText("Enter Course");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 230, 30));

        jLabel18.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Enter Branch");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 230, 30));

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

        jLabel13.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Enter name");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 230, 30));

        jLabel11.setFont(new java.awt.Font("Katari", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Enter Student ID");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 230, 30));

        combo_branch.setBackground(new java.awt.Color(153, 102, 255));
        combo_branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT", "CS", "PLAIN", "ELECTRONICS" }));
        jPanel3.add(combo_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 200, 30));

        combo_course.setBackground(new java.awt.Color(153, 102, 255));
        combo_course.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSC", "MSC", "PHD" }));
        jPanel3.add(combo_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 200, 30));

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

        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student id", "Name", "Course", "Branch"
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
        tbl_studentDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_studentDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_studentDetails);

        jLabel1.setFont(new java.awt.Font("Katari", 1, 24)); // NOI18N
        jLabel1.setText("Manage students");

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

    private void txt_studentIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIDFocusLost

    }//GEN-LAST:event_txt_studentIDFocusLost

    private void txt_studentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIDActionPerformed
        
    }//GEN-LAST:event_txt_studentIDActionPerformed

    private void txt_studentNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentNameFocusLost

    }//GEN-LAST:event_txt_studentNameFocusLost

    private void txt_studentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentNameActionPerformed
        
    }//GEN-LAST:event_txt_studentNameActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        HomePage page = new HomePage();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txt_username2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_username2FocusLost
        
    }//GEN-LAST:event_txt_username2FocusLost

    private void txt_username2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_username2ActionPerformed
        
    }//GEN-LAST:event_txt_username2ActionPerformed

    private void txt_username3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_username3FocusLost
        
    }//GEN-LAST:event_txt_username3FocusLost

    private void txt_username3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_username3ActionPerformed
        
    }//GEN-LAST:event_txt_username3ActionPerformed

    private void txt_password3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_password3ActionPerformed
        
    }//GEN-LAST:event_txt_password3ActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        if (deleteStudent()){
            JOptionPane.showMessageDialog(this, "Student deleted");
            clearTable();
            setStudentDetailsToTable();
        } else{
            JOptionPane.showMessageDialog(this, "Delete student failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (addStudent()){
            JOptionPane.showMessageDialog(this, "Student added");
            clearTable();
            setStudentDetailsToTable();
        } else{
            JOptionPane.showMessageDialog(this, "Add student failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (updateStudent()){
            JOptionPane.showMessageDialog(this, "Student updated");
            clearTable();
            setStudentDetailsToTable();
        } else{
            JOptionPane.showMessageDialog(this, "Update student failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void tbl_studentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_studentDetailsMouseClicked
        int rowNumber = tbl_studentDetails.getSelectedRow();
        TableModel model = tbl_studentDetails.getModel();
        txt_studentID.setText(model.getValueAt(rowNumber, 0).toString());
        txt_studentName.setText(model.getValueAt(rowNumber, 1).toString());
        combo_course.setSelectedItem(model.getValueAt(rowNumber, 2).toString());
        combo_branch.setSelectedItem(model.getValueAt(rowNumber, 3).toString());
    }//GEN-LAST:event_tbl_studentDetailsMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new ManageStudents().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_branch;
    private javax.swing.JComboBox<String> combo_course;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
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
    private rojeru_san.complementos.RSTableMetro tbl_studentDetails;
    private app.bolivia.swing.JCTextField txt_password3;
    private app.bolivia.swing.JCTextField txt_studentID;
    private app.bolivia.swing.JCTextField txt_studentName;
    private app.bolivia.swing.JCTextField txt_username2;
    private app.bolivia.swing.JCTextField txt_username3;
    // End of variables declaration//GEN-END:variables

}
