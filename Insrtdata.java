
package insrtdata;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insrtdata {

    public static void main(String[] args) {
        
        
        
        Statement statement = null;
        ResultSet resultset = null;

        String url = "jdbc:mysql://localhost: / ";
        String user = "root";
        String password = "";
        
        String uservar = "Martin";

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Insert for Math: ");
        int mt = scanner.nextInt();
        
        System.out.println("Enter Science: ");
        int sc = scanner.nextInt();
        
        System.out.println("Enter PE: ");
        int pe = scanner.nextInt();
        
        System.out.println("Enter Information Management: ");
        int im = scanner.nextInt();
        
        System.out.println("Enter Human Computing: ");
        int ih = scanner.nextInt();
        
        System.out.println("Enter Computer Programming: ");
        int cp = scanner.nextInt();
        
        System.out.println("Enter STS: ");
        int st = scanner.nextInt();
        
        System.out.println("Enter Visual Arts: ");
        int rv = scanner.nextInt();
        
        System.out.println("Enter NSTP: ");
        int ns = scanner.nextInt();
        
        System.out.println("Enter Information Management (LEC): ");
        int im2 = scanner.nextInt();
        
        System.out.println("Enter Human Computing (LEC): ");
        int ih2 = scanner.nextInt();
        
        System.out.println("Enter Computer Programming (LEC): ");
        int cp2 = scanner.nextInt();
        
        //scanner.close();
        
        
        
        try(Connection connection = DriverManager.getConnection(url, user, password);
                ){
            
            statement = connection.createStatement();
            
            String sql1 = "SELECT Ctrl_no FROM User WHERE Usernm = '" +uservar+"';";
            resultset = statement.executeQuery(sql1);
            
            if (resultset.next()) {
                
                String resultValue = resultset.getString("Ctrl_no");
                //String value = resultValue;
                System.out.println("Result: " + resultValue);
                
                String sql2 = "UPDATE Contents SET Math = ?, Science = ?, PE = ?, Info_mgmt = ?, Intro_hci = ?, Com_prog = ?, Sts = ?, Rva = ?, Nstp = ?, `Info_mgmt(lec)` = ?, `Intro_hci(lec)` = ?, `Com_prog(lec)` = ? WHERE Ctrl_no = "+resultValue+"; ";
                PreparedStatement preparedStatement = connection.prepareStatement(sql2);
                
            preparedStatement.setString(1, "" +mt);
            preparedStatement.setString(2, "" +sc);
            preparedStatement.setString(3, "" +pe); 
            preparedStatement.setString(4, "" +im);
            preparedStatement.setString(5, "" +ih);
            preparedStatement.setString(6, "" +cp);
            preparedStatement.setString(7, "" +st);
            preparedStatement.setString(8, "" +rv);
            preparedStatement.setString(9, "" +ns);
            preparedStatement.setString(10, "" +im2);
            preparedStatement.setString(11, "" +ih2);
            preparedStatement.setString(12, "" +cp2);
            
            int rowsAffected = preparedStatement.executeUpdate();
            
                 if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
                } else {
                System.out.println("Failed to insert data.");
                }
                
                
            } else {
                System.out.println("No data found!");
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
             }
    }
    
}
