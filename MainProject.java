
//package main.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class MainProject {

    static JFrame frame2; 
    static JFrame frame3;
    static JFrame frame4;
    public static void main(String[] args) {
        
        JFrame frame1 = new JFrame("Student Create Account");
        frame1.setLayout(null);
        frame1.setSize(400, 600);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel wel = new JLabel("Welcome to the Student Grade Tracker!");
        wel.setBounds(20, 150, 250, 20);

        JButton tocrt = new JButton("Create");
        tocrt.setBounds(150, 375, 95, 30);
        tocrt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (frame1.isVisible()) {
                    frame1.setVisible(false);
                    frame2.setVisible(true);
                } else {
                    frame2.setVisible(false);
                    frame1.setVisible(true);
                }
            }
        });
        JButton tolog = new JButton("Log In");
        tolog.setBounds(250, 375, 95, 30);
        tolog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (frame1.isVisible()) {
                    frame1.setVisible(false);
                    frame3.setVisible(true);
                } else {
                    frame2.setVisible(false);
                    frame3.setVisible(true);
                }
            }
        });
        frame1.add(wel);
        frame1.add(tocrt);
        frame1.add(tolog);


        frame2 = new JFrame("Student Create Account"); 
        frame2.setLayout(null);
        frame2.setSize(400, 600);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextField tf1 = new JTextField();
            tf1.setBounds(50, 150, 150, 20);
        final JTextField tf2 = new JTextField();
            tf2.setBounds(50, 200, 150, 20);
        final JTextField tf3 = new JTextField();
            tf3.setBounds(50, 250, 150, 20);

        JButton createacct = new JButton("Create Account");
        createacct.setBounds(50, 50, 95, 30);
        createacct.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent e ){
            
        Statement statement = null;
        ResultSet resultset = null;
        
        String url = "jdbc:mysql://0.tcp.ap.ngrok.io:16812/Usernm ";
        String user = "root";
        String password = "";

        String insertQuery = "INSERT INTO User (Usernm, Password) VALUES (?, ?)";
        
        String usernm = "" +tf1.getText();
        String pass = "" +tf2.getText();
        String repass = "" +tf3.getText();

        try(Connection connection = DriverManager.getConnection(url, user, password);){
            statement = connection.createStatement();
            if (pass.equals(repass)){
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                preparedStatement.setString(1, "" + usernm);
                preparedStatement.setString(2, "" +pass); 

                int rowsAffected = preparedStatement.executeUpdate();
            
                 if (rowsAffected > 0) {
                     if (frame1.isVisible()) {
                    frame2.setVisible(false);
                    frame3.setVisible(true);
                } else {
                    frame2.setVisible(false);
                    frame3.setVisible(true);
                }
                System.out.println("Data inserted successfully into Users.");
                } else {
                System.out.println("Failed to insert data (Wrong Password).");
                } 
            String sql1 = "SELECT Ctrl_no FROM User WHERE Usernm = '" +usernm+"';";
            resultset = statement.executeQuery(sql1);

            if (resultset.next()) {
                int resultValue = resultset.getInt("Ctrl_no");
                
                System.out.println("Result: " + resultValue);
                
                String sql2 = "INSERT INTO Contents (Ctrl_no, Math, Science, PE, Info_mgmt, Intro_hci, Com_prog, Sts, Rva, Nstp, `Info_mgmt(lec)`, `Intro_hci(lec)`, `Com_prog(lec)`) VALUE ( ?, '0','0','0','0','0','0','0','0','0','0','0','0'); ";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setInt(1, resultValue);
                
                int rowsAffected1 = preparedStatement2.executeUpdate();
                 if (rowsAffected1 > 0) {
                System.out.println("Data inserted successfully to Contents.");
                } else {
                System.out.println("Failed to insert data (Database Error).");
                }
             }
             else {
                 System.out.println("Password did not match");
             }
            }
            else{
                    System.out.println("Error!");
            }

        }catch (SQLException e1){
            e1.printStackTrace();
        }
        }
       });
       frame2.add(tf1);
       frame2.add(tf2);
       frame2.add(tf3);
       frame2.add(createacct);


       frame3 = new JFrame("Log In");
       frame3.setLayout(null);
       frame3.setSize(400, 600);
       frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextField tf11 = new JTextField();
        tf11.setBounds(50, 150, 150, 20);
        final JTextField tf12 = new JTextField();
        tf12.setBounds(50, 200, 150, 20);

        JButton logbot = new JButton("Log In");
        logbot.setBounds(50, 250, 95, 30);
        logbot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
        Statement statement = null;
        ResultSet resultset = null;

        String url = "jdbc:mysql://0.tcp.ap.ngrok.io:16812/Usernm";
        String user = "root";
        String password = "";
 
        String usernml = "" +tf11.getText();
        String passl = "" +tf12.getText();
        
         try (Connection connection = DriverManager.getConnection(url, user, password);){   
            statement = connection.createStatement();
             
            String insertQuery = "SELECT * FROM User WHERE Usernm = '" +usernml+ "' AND Password = '" + passl + "';";
           
            resultset = statement.executeQuery(insertQuery);
            
             if (resultset.next()) {
                     if (frame3.isVisible()) {
                    frame3.setVisible(false);
                    frame4.setVisible(true);
                } else {
                    frame4.setVisible(false);
                    frame3.setVisible(true);
                }
                 System.out.println("Login successful!");
             } else {
                 System.out.println("Incorrect username or password.");
             }
         }catch (SQLException e2) {
           e2.printStackTrace();
             }
            }
        });
        JButton tomainl = new JButton("Return");
        tomainl.setBounds(150, 250, 95, 30);
        tomainl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (frame3.isVisible()) {
                    frame3.setVisible(false);
                    frame1.setVisible(true);
                } else {
                    frame1.setVisible(false);
                    frame3.setVisible(true);
                }
            }
        });
        frame3.add(tf11);
        frame3.add(tf12);
        frame3.add(logbot);
        frame3.add(tomainl);


        frame4 = new JFrame("Insert Data"); 
        frame4.setLayout(null);
        frame4.setSize(400, 600);
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextField tf13 = new JTextField();
            tf13.setBounds(50, 150, 150, 20);
        final JTextField tf23 = new JTextField();
            tf23.setBounds(50, 200, 150, 20);
        final JTextField tf33 = new JTextField();
            tf33.setBounds(50, 250, 150, 20);
        final JTextField tf43 = new JTextField();
            tf43.setBounds(50, 300, 150, 20);
        final JTextField tf53 = new JTextField();
            tf53.setBounds(50, 350, 150, 20);
        final JTextField tf63 = new JTextField();
            tf63.setBounds(50, 400, 150, 20);
        final JTextField tf73 = new JTextField();
            tf73.setBounds(50, 450, 150, 20);
        final JTextField tf83 = new JTextField();
            tf83.setBounds(50, 500, 150, 20);
        final JTextField tf93 = new JTextField();
            tf93.setBounds(50, 550, 150, 20);
        final JTextField tf103 = new JTextField();
            tf103.setBounds(50, 600, 150, 20);
        final JTextField tf113 = new JTextField();
            tf113.setBounds(50, 650, 150, 20);
        final JTextField tf123 = new JTextField();
            tf123.setBounds(50, 700, 150, 20);
        final JTextField tf133 = new JTextField();
            tf133.setBounds(350, 150, 150, 20);
        final JTextField tf143 = new JTextField();
            tf143.setBounds(350, 200, 150, 20);


        JButton toins = new JButton("Input Data");
        toins.setBounds(350, 375, 95, 30);
        toins.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

        Statement statement = null;
        ResultSet resultset = null;

        String url = "jdbc:mysql://0.tcp.ap.ngrok.io:16812/Usernm";
        String user = "root";
        String password = "";
        
        String uservar = "Leo";

        int mt =  +Integer.parseInt(tf13.getText());
        int sc =  +Integer.parseInt(tf23.getText());
        int pe =  +Integer.parseInt(tf33.getText());
        int im =  +Integer.parseInt(tf43.getText());
        int ih =  +Integer.parseInt(tf53.getText());
        int cp =  +Integer.parseInt(tf63.getText());
        int st =  +Integer.parseInt(tf73.getText());
        int rv =  +Integer.parseInt(tf83.getText());
        int ns =  +Integer.parseInt(tf93.getText());
        int im2 =  +Integer.parseInt(tf103.getText());
        int ih2 =  +Integer.parseInt(tf113.getText());
        int cp2 =  +Integer.parseInt(tf123.getText());

        try(Connection connection = DriverManager.getConnection(url, user, password);){
            statement = connection.createStatement();
            
            String sql1 = "SELECT Ctrl_no FROM User WHERE Usernm = '" +uservar+"';";
            resultset = statement.executeQuery(sql1);
            
            if (resultset.next()) {
                String resultValue = resultset.getString("Ctrl_no");
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
        }catch (SQLException e4) {
            e4.printStackTrace();
             }
            }
        });
        JButton showrmk = new JButton("Remarks");
        showrmk.setBounds(450, 375, 95, 30);
        showrmk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
        int mt =  +Integer.parseInt(tf13.getText());
        int sc =  +Integer.parseInt(tf23.getText());
        int pe =  +Integer.parseInt(tf33.getText());
        int im =  +Integer.parseInt(tf43.getText());
        int ih =  +Integer.parseInt(tf53.getText());
        int cp =  +Integer.parseInt(tf63.getText());
        int st =  +Integer.parseInt(tf73.getText());
        int rv =  +Integer.parseInt(tf83.getText());
        int ns =  +Integer.parseInt(tf93.getText());
        int im2 =  +Integer.parseInt(tf103.getText());
        int ih2 =  +Integer.parseInt(tf113.getText());
        int cp2 =  +Integer.parseInt(tf123.getText());
        
            int a,b;
            a = mt + sc + pe + im + ih + cp + st + rv + ns + im2 + ih2 + cp2;
            b = a/12;

            tf133.setText(String.valueOf(b));
                if (b>=75){
                    tf143.setText("Passed");
                } 
                else{
                    tf143.setText("Failed");
                }
            }
        });
        JButton tomain = new JButton("Return");
        tomain.setBounds(650, 375, 95, 30);
        tomain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (frame4.isVisible()) {
                    frame4.setVisible(false);
                    frame1.setVisible(true);
                } else {
                    frame1.setVisible(false);
                    frame4.setVisible(true);
                }
            }
        });
        JButton clr = new JButton("Clear");
        clr.setBounds(550, 375, 95, 30);
        clr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                tf13.setText("");
                tf23.setText("");
                tf33.setText("");
                tf43.setText("");
                tf53.setText("");
                tf63.setText("");
                tf73.setText("");
                tf83.setText("");
                tf93.setText("");
                tf103.setText("");
                tf113.setText("");
                tf123.setText("");
                tf133.setText("");
                tf143.setText("");
                //
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                //
                tf11.setText("");
                tf12.setText("");
            }
        });
        frame4.add(tf13);
        frame4.add(tf23);
        frame4.add(tf33);
        frame4.add(tf43);
        frame4.add(tf53);
        frame4.add(tf63);
        frame4.add(tf73);
        frame4.add(tf83);
        frame4.add(tf93);
        frame4.add(tf103);
        frame4.add(tf113);
        frame4.add(tf123);
        frame4.add(tf133);
        frame4.add(tf143);
        frame4.add(toins);
        frame4.add(showrmk);
        frame4.add(tomain);
        frame4.add(clr);
    }
}