
package acctdb;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Acctdb {

    public static void main(String[] args) {
        
        Statement statement = null;
        ResultSet resultset = null;
        
        
        String url = "jdbc:mysql://localhost: / ";
        String user = "root";
        String password = "";

        String insertQuery = "INSERT INTO User (Usernm, Password) VALUES (?, ?)";

        Scanner myscanner = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String usernm = myscanner.nextLine();

        System.out.println("Enter Password: ");
        String pass = myscanner.nextLine();
        
        System.out.println("Re-Enter Password: ");
        String repass = myscanner.nextLine();

        try(Connection connection = DriverManager.getConnection(url, user, password);){

            statement = connection.createStatement();

            if (pass.equals(repass)){
                
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                preparedStatement.setString(1, "" + usernm);
                preparedStatement.setString(2, "" +pass); 

                int rowsAffected = preparedStatement.executeUpdate();
            
                 if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
                } else {
                System.out.println("Failed to insert data.");
                } 
                 
                 
            String sql1 = "SELECT Ctrl_no FROM User WHERE Usernm = '" +usernm+"';";
            resultset = statement.executeQuery(sql1);

            if (resultset.next()) {
                
                int resultValue = resultset.getInt("Ctrl_no");
                //String value = resultValue;
                System.out.println("Result: " + resultValue);
                
                String sql2 = "INSERT INTO Contents (Ctrl_no, Math, Science, PE, Info_mgmt, Intro_hci, Com_prog, Sts, Rva, Nstp, `Info_mgmt(lec)`, `Intro_hci(lec)`, `Com_prog(lec)`) VALUE ( ?, '0','0','0','0','0','0','0','0','0','0','0','0'); ";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                
                preparedStatement2.setInt(1, resultValue);
                
                int rowsAffected1 = preparedStatement2.executeUpdate();
            
                 if (rowsAffected1 > 0) {
                System.out.println("Data inserted successfully.");
                } else {
                System.out.println("Failed to insert data.");
                }
                 
             }
             else {
                 System.out.println("Password did not match");
             }
            
            

            }
            else{
                    System.out.println("Error!");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
