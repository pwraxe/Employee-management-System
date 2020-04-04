package employeemanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Welcome extends JFrame
{
    JLabel loginname,username,password;
    JTextField logintext,passwdtext;
    JButton login,register;
    JPanel p;
    JFrame j;
    public Welcome() throws Exception
    {
        UIManager.setLookAndFeel(new NimbusLookAndFeel()); 
        j = new JFrame("Welcome, Guest");
        j.setLayout(null);
        j.setBounds(0, 0, 1840, 990);               // Window Size
        j.setResizable(false);
        
        Font f = new Font("Times new Roman", Font.ITALIC, 50);
        p = new JPanel(null);
        p.setBounds(400, 100, 1000, 500);               // Jpanel Size
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        JLabel text = new JLabel("Admin Panel");
        text.setBounds(300, 10, 600, 80);
        text.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        text.setForeground(Color.BLACK);
        p.add(text);
        
        username = new JLabel("Username : ");
        username.setBounds(150, 130 , 270, 50);
        username.setFont(new Font("Times new Roman", Font.PLAIN, 50));
        p.add(username);
        
        logintext = new JTextField();
        logintext.setBounds(400, 130, 400, 60);
        logintext.setFont(new Font("Times new Roman", Font.PLAIN, 50));
        p.add(logintext);
        
        //_______________________________________________________________________________
        
        password = new JLabel("Password : ");
        password.setBounds(150, 220, 270, 50);
        password.setFont(new Font("Times new Roman", Font.PLAIN, 50));
        p.add(password);
        
        passwdtext = new JPasswordField();
        passwdtext.setBounds(400, 220, 400, 60);
        passwdtext.setFont(f);
        p.add(passwdtext);
        
        //_______________________________________________________________________________
        
        login = new JButton("Login");
        login.setBounds(400, 320, 250, 80);
        login.setFont(new Font("Times new Roman", Font.PLAIN, 50));
        login.setBackground(Color.WHITE);
        p.add(login);
        
        login.addActionListener((ActionEvent e) ->
        {
          try{
                run(p,j);
          }catch(Exception ex){ex.printStackTrace();}
        });

        
        j.add(p);
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);
        j.setVisible(true);
        
    }
    
    
    public static void main(String[] args) throws Exception
    {
        Welcome w = new Welcome();
    }

    
     public void run(JPanel p,JFrame j) throws SQLException
    {
        String url = "jdbc:mysql://localhost/industry";
        String user = "root";
        String pass = "Akshay";
        Connection c = DriverManager.getConnection(url,user,pass);
        Statement s = c.createStatement();
        String usrnm = logintext.getText();
        String passwd = passwdtext.getText();

        String logPass = "select username from login where username = ? and password = ?";
        PreparedStatement ps1 = (PreparedStatement) c.prepareStatement(logPass);
        ps1.setString(1, usrnm);
        ps1.setString(2, passwd);


        ResultSet set =  ps1.executeQuery();
        if(set.next())   // if(set.next())
        {
            JOptionPane.showMessageDialog(null, "Login Successful");
            getData();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username and password");
        }
    }

     public void getData() {
     
          try{ 
                EmployeeData ed = new EmployeeData(j);
                p.removeAll();
                //p.remove(loginname);
                p.remove(username);
                p.remove(logintext);
                p.remove(password);
                p.remove(passwdtext);
                p.remove(login);
               // p.remove(register);
                j.remove(p);
                
            }catch(Exception x){ x.printStackTrace(); }
         
     }
     
     public void getNewWindow() {
     
         j.dispose();
          try{ 
                
                p.removeAll();
                j.remove(p);
               new EmployeeData(j).refreshData();
                
                
            }catch(Exception x){ x.printStackTrace(); }
         
     }
     
     
}

