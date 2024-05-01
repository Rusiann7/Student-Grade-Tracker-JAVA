
package forlogindb;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
//import java.time.LocalTime;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;

public class Forlogindb {
    public static void main(String[] args) {

        //Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
       
        //LocalDateTime td = LocalDateTime.now();
        
        String url = "jdbc:mysql://localhost:8181/Usernm";
        String user = "root";
        String password = "";
 
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter Username: ");
        String usernm = scanner.nextLine();
     
        
        System.out.println("Enter Password: ");
        String pass = scanner.nextLine();
        
        
         try (
               Connection connection = DriverManager.getConnection(url, user, password);
             
                )
         {   
            
             
             statement = connection.createStatement();
             
            String insertQuery = "SELECT * FROM User WHERE Usernm = '" +usernm+ "' AND Password = '" + pass + "';";
            //String insertquery = "INSERT INTO `User` (`Ctrl_no`, `Usernm`, `Password`, `Time_in`, `Time_out`) VALUES (NULL, '', '', '', '');";
            
            resultset = statement.executeQuery(insertQuery);
            
             if (resultset.next()) {
                  //while (resultset.next())
                String usernmsql = resultset.getString("Usernm");
                String passsql = resultset.getString("Password");
                
                System.out.println(usernmsql+ "" +passsql+ "");
               
            
                 System.out.println("Login successful!");
             } else {
                 System.out.println("Incorrect username or password.");
             }
            
           
            
            
         }catch (SQLException e) {
           e.printStackTrace();
             }
         finally{
             //Database.closeResultSet(resultset);
             //Database.closeStatement(statement);
             
         }
    }
    
}
