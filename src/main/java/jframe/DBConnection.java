package jframe;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Karina Ganich
 * 
 * Class for database connection 
 */
public class DBConnection {
    static Connection connection = null;
    
    /**
     * Method for connecting to db
     */
    public static Connection getConnection(){
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/librarydb", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return connection;
    }
}
