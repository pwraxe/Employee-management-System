package employeemanagement;

import datechooser.beans.DateChooserDialog;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ACER
 */
public class EmployeeData 
{
    String id,fname,lname,mobile,email,dob,doj,salary,dept,city,pin;
    String colm[] = {"id","fname","lname","mobile","email","Birth Date","Joining Date","Salary","Department","City","Pincode"};
    JTable table;
    Font font = new Font("Times new Roman", Font.PLAIN, 15);
    
    JComboBox B_DAY,B_MONTH,B_YEAR, J_DAY,J_MONTH,J_YEAR;
    
    int rowSize = 0;
    
    String day[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String month[] ={"January","February","March","April","May","June","July","August","September","October","November","December"};
    String year[] ={"1950","1951","1952","1953","1954","1955","1956","1957","1958","1959","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    
    JButton Submit;
    JLabel IDText,FnameText,LnameText,MobileText,EmailText,BirthDayText,JoiningText,SalaryText,DepartmentText,CityText,PincodeText;
    JTextField IDBox,FnameBox,LnameBox,MobileBox,EmailBox,SalaryBox,DepartmentBox,CityBox,PincodeBox;
    JPanel pan;
    DateChooserDialog DOB;
    JFrame originalFrame ;
    JButton refesh;
    DefaultTableModel model;
    ResultSet set;
    
    Connection c;
    Statement s;
    public EmployeeData(JFrame f) throws Exception {
        originalFrame = f;
        refreshData();
    }
   
    
    public void updateEmployee()        // call this function when you want to submit updated data
    {    
        int Id,Salary,Pin;
        Long Mobile;
        String FirstName, LastName,Email,BirthDate,JoiningDate,Department,City; 
        
        Id =  Integer.parseInt(IDBox.getText());
        FirstName = FnameBox.getText();
        LastName = LnameBox.getText();
        Mobile = Long.parseLong(MobileBox.getText());
        Email = EmailBox.getText();
        BirthDate = getBirthDate();
        JoiningDate = getJoiningDate();
        Salary = Integer.parseInt(SalaryBox.getText());
        Department = DepartmentBox.getText();
        City = CityBox.getText();
        Pin = Integer.parseInt(PincodeBox.getText());
        
        
        String url = "jdbc:mysql://localhost/industry";
        String user = "root";
        String pass = "Akshay";

        //String input = "insert into employee values(?,?,?,?,?,?,?,?,?,?,?)";

        Connection c;
        Statement s;
        try
        {
            c = DriverManager.getConnection(url,user,pass);
            s = c.createStatement();
            
            
            if(!Mobile.toString().matches("\\d{10}")) { 
                JOptionPane.showMessageDialog(originalFrame,"Mobile No Should be 10 digit");
                //System.exit(0);
            }
            String input = "update employee set fname = ?,lname = ?,mobile = ?,email = ?,dob = ?,doj = ?,salary = ?,department = ?,city = ?,pin = ? where id ="+Id;
            PreparedStatement ps1 = c.prepareStatement(input);
            
            ps1.setString(1,FirstName);
            ps1.setString(2,LastName);
            ps1.setLong(3,Mobile);
            ps1.setString(4,Email);
            ps1.setString(5,BirthDate);
            ps1.setString(6,JoiningDate);
            ps1.setInt(7,Salary);
            ps1.setString(8,Department);
            ps1.setString(9,City);
            ps1.setInt(10,Pin);
            int rs = ps1.executeUpdate();
            if(rs == -1)
                JOptionPane.showMessageDialog(originalFrame , "Error to modify Data ");
            else
            {
                JOptionPane.showMessageDialog(originalFrame , "Successfully modify Data ");
                displayTableData();
            }
                
        }catch(Exception ex){ex.printStackTrace();}
    }
    
    public void refreshData() throws SQLException, Exception{
        
        pan = new JPanel();
        pan.setLayout(null);
        pan.setBounds(0,0, originalFrame.getWidth(), 900);            // JPane Position
        //p.setBackground(Color.red);
        originalFrame.add(pan);       
        
        String url = "jdbc:mysql://localhost/industry";
        String user = "root";
        String pass = "Akshay";
        c = DriverManager.getConnection(url,user,pass);
        s = c.createStatement();

        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colm);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        
      
        TableColumnModel colModel = table.getColumnModel();
        
        colModel.getColumn(0).setWidth(50); // id
        colModel.getColumn(1).setWidth(110); // fname
        colModel.getColumn(2).setWidth(120); // lname
        colModel.getColumn(3).setWidth(130); // mobile
        colModel.getColumn(4).setWidth(280); // email
        colModel.getColumn(5).setWidth(140); // dob
        colModel.getColumn(6).setWidth(130); // doj
        colModel.getColumn(7).setWidth(80); //sal
        colModel.getColumn(8).setWidth(110); // dept
        colModel.getColumn(9).setWidth(80); // city
        colModel.getColumn(10).setWidth(80); // pin
       
        JScrollPane sp = new JScrollPane();
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pan.add(sp);
        
        table.setBounds(0, 0, 1800, 1000);
        table.setFont(font);
        table.setRowHeight(35);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        
        //table.setSize(pan.getWidth(), pan.getHeight());
        
        displayTableData();
        if(rowSize < 1)
            JOptionPane.showMessageDialog(null, "Sorry...No Record Found","Error",JOptionPane.ERROR_MESSAGE);
        else
            System.out.println(rowSize+" Records Found");
        
        table.add(sp);
        pan.add(table);
        
    
     // ------------------------------------------ Button
        
        JButton addEmployee = new JButton("Add Employee Data");
        addEmployee.setBounds(100,600,200,50);
        addEmployee.setLayout(null);
        addEmployee.setFont(font);
        table.add(addEmployee);
        
        JButton deleteEmployee = new JButton("Delete Employee Data");
        deleteEmployee.setBounds(350,600,250,50);
        deleteEmployee.setLayout(null);
        deleteEmployee.setFont(font);
        table.add(deleteEmployee);
        
        JButton modifyEmployee = new JButton("Modify Employee Data");
        modifyEmployee.setBounds(650, 600, 250, 50);
        modifyEmployee.setLayout(null);
        modifyEmployee.setFont(font);
        table.add(modifyEmployee);
        
        refesh = new JButton("Refresh");
        refesh.setLayout(null);
        refesh.setBounds(920, 600, 200, 50);
        refesh.setFont(font);
        table.add(refesh);
        
        refesh.addActionListener((ActionEvent e) -> {
            refesh.repaint();
            table.repaint();
            model.fireTableDataChanged();
        });
        
        
        addEmployee.addActionListener((ActionEvent e) -> {
           addOneMoreEmployee(model);
            
        });
        
        deleteEmployee.addActionListener((ActionEvent e) -> {
            int delete_id = Integer.parseInt(JOptionPane.showInputDialog(originalFrame, "Enter Employee ID to Delete "));
            
             try {
                s.executeUpdate("delete from employee where id = "+delete_id+";");
                JOptionPane.showMessageDialog(pan,"Data Deleted Succeessfully");
                
                
                //--------------------------------delete with in these line-----------------------
                
                pan.repaint();              // alreay there
                originalFrame.repaint();    // alreay there
                
                 displayTableData();

                //--------------------------------delete with in these line-----------------------
                
            } catch (Exception ex) {  ex.printStackTrace();   }
             
        });
        
        modifyEmployee.addActionListener((ActionEvent e) -> {
            try{
                int modify_id = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID to Modify : "));
                //takeDataFromUser(2,modify_id);
                collectAndUpdateData(originalFrame, model, modify_id);
            }catch(Exception ex){ex.printStackTrace();}
            //modifyExsistingEmployee(modify_id);

        });
    
    }
    
    public void displayTableData() throws Exception{
    
        model.setRowCount(0);
        pan.repaint();
        originalFrame.repaint();
        set = s.executeQuery("select * from employee");
        model.addRow(new Object[]{"<html><b>ID</b></html>",
                                  "<html><b>First Name</b></html>",
                                  "<html><b>Last Name</b></html>",
                                  "<html><b>Mobile No</b></html>",
                                  "<html><b>Email ID</b></html>",
                                  "<html><b>Birth Date</b></html>",
                                  "<html><b>Joining Date</b></html>",
                                  "<html><b>Salary (INR)</b></html>",
                                  "<html><b>Department</b></html>",
                                  "<html><b>City</b></html>",
                                  "<html><b>Pincode</b></html>"});
        while(set.next())
        {
            pan.repaint();
            originalFrame.repaint();
            
            id = set.getString("id");
            fname = set.getString("fname");
            lname = set.getString("lname");
            mobile = set.getString("mobile");
            email = set.getString("email");
            dob = set.getString("dob");
            doj = set.getString("doj");
            salary = set.getString("salary");
            dept = set.getString("department");
            city = set.getString("city");
            pin = set.getString("pin");
            model.addRow(new Object[]{id,fname,lname,mobile,email,dob,doj,salary,dept,city,pin});
            rowSize++;
        }
        
    }

    //----------------------------------------------------------------------------------------
    
    

    public void takeDataFromUser(int flag,int emp_id){
        JFrame addFrame = new JFrame("Add One More Employee");
        addFrame.setLayout(null);
        addFrame.setBounds(300, 0, 800, 1030);
        addFrame.setBackground(Color.GRAY);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(1000, 1000));
        addFrame.add(panel);
        
        
        JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(0,0,800,670);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        addFrame.add(scroll);
        // ---------------------------------ID Text / Label
        IDText = new JLabel("ID : ");
        IDText.setLayout(null);
        IDText.setBounds(50,30,100,50);
        IDText.setFont(font);
        panel.add(IDText);
                
        IDBox = new JTextField();
        IDBox.setLayout(null);
        IDBox.setBounds(250, 30, 300, 50);
        IDBox.setFont(font);
        panel.add(IDBox);
        
        // --------------------------------- First Name Text / Label
        FnameText = new JLabel("First Name : ");
        FnameText.setLayout(null);
        FnameText.setBounds(50, 110, 150, 50);
        FnameText.setFont(font);
        panel.add(FnameText);
        
        FnameBox = new JTextField();
        FnameBox.setLayout(null);
        FnameBox.setBounds(250,110,300,50);
        FnameBox.setFont(font);
        panel.add(FnameBox);
        
        // --------------------------------- Last name Text / input Box 
        
        LnameText = new JLabel("Last Name : ");
        LnameText.setBounds(50, 190, 150, 50);
        LnameText.setLayout(null);
        LnameText.setFont(font);
        panel.add(LnameText);
        
        LnameBox = new JTextField();
        LnameBox.setLayout(null);
        LnameBox.setBounds(250, 190, 300, 50);
        LnameBox.setFont(font);
        panel.add(LnameBox);
        
        // ---------------------------------- mobile Text / input box
        MobileText = new JLabel("Mobile No : ");
        MobileText.setLayout(null);
        MobileText.setBounds(50, 270, 150, 50);
        MobileText.setFont(font);
        panel.add(MobileText);
        
        MobileBox = new JTextField();
        MobileBox.setLayout(null);
        MobileBox.setBounds(250, 270, 300, 50);
        MobileBox.setFont(font);
        panel.add(MobileBox);
        
        // -----------------------------------  email text / input box
        
        EmailText = new JLabel("Email ID : ");
        EmailText.setLayout(null);
        EmailText.setBounds(50, 350, 150, 50);
        EmailText.setFont(font);
        panel.add(EmailText);
        
        EmailBox = new JTextField();
        EmailBox.setLayout(null);
        EmailBox.setBounds(250, 350, 300, 50);
        EmailBox.setFont(font);
        panel.add(EmailBox);
        
        // ------------------------------------- Birth Date 
        
        BirthDayText = new JLabel("Date of Birth : ");
        BirthDayText.setLayout(null);
        BirthDayText.setBounds(50,430,150,50);
        BirthDayText.setFont(font);
        panel.add(BirthDayText);
        
        B_DAY = new JComboBox(day);
        B_DAY.setBounds(250, 430, 100, 50);
        B_DAY.setLayout(null);
        B_DAY.setFont(font);
        panel.add(B_DAY);
        
        B_MONTH = new JComboBox(month);
        B_MONTH.setLayout(null);
        B_MONTH.setBounds(370,430, 150, 50);
        B_MONTH.setFont(font);
        panel.add(B_MONTH);
        
        B_YEAR = new JComboBox(year);
        B_YEAR.setLayout(null);
        B_YEAR.setBounds(540, 430, 100, 50);
        B_YEAR.setFont(font);
        panel.add(B_YEAR);
        
       // ---------------------------------------- Joining Date
        
        JoiningText = new JLabel("Date of Joining : ");
        JoiningText.setBounds(50, 510, 150, 50);
        JoiningText.setLayout(null);
        JoiningText.setFont(font);
        panel.add(JoiningText);
        
        J_DAY = new JComboBox(day);
        J_DAY.setBounds(250, 510, 100, 50);
        J_DAY.setLayout(null);
        J_DAY.setFont(font);
        panel.add(J_DAY);
        
        J_MONTH = new JComboBox(month);
        J_MONTH.setLayout(null);
        J_MONTH.setBounds(370,510, 150, 50);
        J_MONTH.setFont(font);
        panel.add(J_MONTH);
        
        J_YEAR = new JComboBox(year);
        J_YEAR.setLayout(null);
        J_YEAR.setBounds(540, 510, 100, 50);
        J_YEAR.setFont(font);
        panel.add(J_YEAR);
     
        // --------------------------------------Salary text / input box
        
        SalaryText = new JLabel("Salary : ");
        SalaryText.setLayout(null);
        SalaryText.setBounds(50, 590, 100, 50);
        SalaryText.setFont(font);
        panel.add(SalaryText);
        
        SalaryBox = new JTextField();
        SalaryBox.setLayout(null);
        SalaryBox.setBounds(250, 590, 300,50);
        SalaryBox.setFont(font);
        panel.add(SalaryBox);
       
        // ------------------------------------  Department text / input Box
        
        DepartmentText = new JLabel("Department : ");
        DepartmentText.setLayout(null);
        DepartmentText.setBounds(50, 670, 130, 50);
        DepartmentText.setFont(font);
        panel.add(DepartmentText);
        
        DepartmentBox = new JTextField();
        DepartmentBox.setBounds(250, 670, 300, 50);
        DepartmentBox.setLayout(null);
        DepartmentBox.setFont(font);
        panel.add(DepartmentBox);
        
        // ----------------------------------------  City text / pin
        
        CityText = new JLabel("City : ");
        CityText.setLayout(null);
        CityText.setBounds(50, 750, 100, 50);
        CityText.setFont(font);
        panel.add(CityText);
        
        CityBox = new JTextField();
        CityBox.setLayout(null);
        CityBox.setBounds(250, 750, 300, 50);
        CityBox.setFont(font);
        panel.add(CityBox);
        
        // -------------------------------------------  pin text / input box
        
        PincodeText = new JLabel("Pincode : ");
        PincodeText.setLayout(null);
        PincodeText.setBounds(50, 830, 100, 50);
        PincodeText.setFont(font);
        panel.add(PincodeText);
        
        PincodeBox = new  JTextField();
        PincodeBox.setLayout(null);
        PincodeBox.setBounds(250, 830, 250, 50);
        PincodeBox.setFont(font);
        panel.add(PincodeBox);
        
        
        Submit = new JButton("Submit");
        Submit.setLayout(null);
        Submit.setBounds(250, 900, 200, 50);
        Submit.setFont(font);
        panel.add(Submit);
        
        addFrame.setVisible(true);
        
        Submit.addActionListener((ActionEvent e) -> {
            try{
            
                if(flag == 1){
                    collectAndStorData(addFrame,model);
                    //refreshData();
                    refesh.repaint();
                    table.repaint();
                    model.fireTableDataChanged();
                    addFrame.dispose();
                    displayTableData();
                }else{}
                
                
            }catch(Exception ex){ex.printStackTrace();}
        });
        panel.setVisible(true);
    }
    
    public void addOneMoreEmployee(DefaultTableModel model) {
        takeDataFromUser(1,0);
    }
    
    public String getBirthDate(){
        String d,m,y;
        d = B_DAY.getSelectedItem().toString();
        m = (String) B_MONTH.getSelectedItem();
        y = B_YEAR.getSelectedItem().toString();
        return y+"-"+m+"-"+d;
    }
    public String getJoiningDate(){
        String d,m,y;
        d = J_DAY.getSelectedItem().toString();
        m = (String) J_MONTH.getSelectedItem();
        y = J_YEAR.getSelectedItem().toString();
        return y+"-"+m+"-"+d;
        
    }
        
    public void collectAndUpdateData(JFrame p,DefaultTableModel model,int emp_id) throws Exception {
        
        String url = "jdbc:mysql://localhost/industry";
        String user = "root";
        String pass = "Akshay";
        c = DriverManager.getConnection(url,user,pass);
        s = c.createStatement();
        //----------------------------------------------------------

        JFrame addFrame = new JFrame("Update Employee Data");
        addFrame.setLayout(null);
        addFrame.setBounds(300, 0, 800, 1030);
        addFrame.setBackground(Color.GRAY);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(1000, 1000));
        addFrame.add(panel);
        
        
        JScrollPane scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(0,0,800,670);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        addFrame.add(scroll);

        // ---------------------------------ID Text / Label
        IDText = new JLabel("ID : ");
        IDText.setLayout(null);
        IDText.setBounds(50,30,100,50);
        IDText.setFont(font);
        panel.add(IDText);
                
        IDBox = new JTextField();
        IDBox.setLayout(null);
        IDBox.setBounds(250, 30, 300, 50);
        IDBox.setFont(font);
        panel.add(IDBox);
        
        // --------------------------------- First Name Text / Label
        FnameText = new JLabel("First Name : ");
        FnameText.setLayout(null);
        FnameText.setBounds(50, 110, 150, 50);
        FnameText.setFont(font);
        panel.add(FnameText);
        
        FnameBox = new JTextField();
        FnameBox.setLayout(null);
        FnameBox.setBounds(250,110,300,50);
        FnameBox.setFont(font);
        panel.add(FnameBox);
        
        // --------------------------------- Last name Text / input Box 
        
        LnameText = new JLabel("Last Name : ");
        LnameText.setBounds(50, 190, 150, 50);
        LnameText.setLayout(null);
        LnameText.setFont(font);
        panel.add(LnameText);
        
        LnameBox = new JTextField();
        LnameBox.setLayout(null);
        LnameBox.setBounds(250, 190, 300, 50);
        LnameBox.setFont(font);
        panel.add(LnameBox);
        
        // ---------------------------------- mobile Text / input box
        MobileText = new JLabel("Mobile No : ");
        MobileText.setLayout(null);
        MobileText.setBounds(50, 270, 150, 50);
        MobileText.setFont(font);
        panel.add(MobileText);
        
        MobileBox = new JTextField();
        MobileBox.setLayout(null);
        MobileBox.setBounds(250, 270, 300, 50);
        MobileBox.setFont(font);
        panel.add(MobileBox);
        
        // -----------------------------------  email text / input box
        
        EmailText = new JLabel("Email ID : ");
        EmailText.setLayout(null);
        EmailText.setBounds(50, 350, 150, 50);
        EmailText.setFont(font);
        panel.add(EmailText);
        
        EmailBox = new JTextField();
        EmailBox.setLayout(null);
        EmailBox.setBounds(250, 350, 300, 50);
        EmailBox.setFont(font);
        panel.add(EmailBox);
        
        // ------------------------------------- Birth Date 
        
        BirthDayText = new JLabel("Date of Birth : ");
        BirthDayText.setLayout(null);
        BirthDayText.setBounds(50,430,150,50);
        BirthDayText.setFont(font);
        panel.add(BirthDayText);
        
        B_DAY = new JComboBox(day);
        B_DAY.setBounds(250, 430, 100, 50);
        B_DAY.setLayout(null);
        B_DAY.setFont(font);
        panel.add(B_DAY);
        
        B_MONTH = new JComboBox(month);
        B_MONTH.setLayout(null);
        B_MONTH.setBounds(370,430, 150, 50);
        B_MONTH.setFont(font);
        panel.add(B_MONTH);
        
        B_YEAR = new JComboBox(year);
        B_YEAR.setLayout(null);
        B_YEAR.setBounds(540, 430, 100, 50);
        B_YEAR.setFont(font);
        panel.add(B_YEAR);
        
       // ---------------------------------------- Joining Date
        
        JoiningText = new JLabel("Date of Joining : ");
        JoiningText.setBounds(50, 510, 150, 50);
        JoiningText.setLayout(null);
        JoiningText.setFont(font);
        panel.add(JoiningText);
        
        J_DAY = new JComboBox(day);
        J_DAY.setBounds(250, 510, 100, 50);
        J_DAY.setLayout(null);
        J_DAY.setFont(font);
        panel.add(J_DAY);
        
        J_MONTH = new JComboBox(month);
        J_MONTH.setLayout(null);
        J_MONTH.setBounds(370,510, 150, 50);
        J_MONTH.setFont(font);
        panel.add(J_MONTH);
        
        J_YEAR = new JComboBox(year);
        J_YEAR.setLayout(null);
        J_YEAR.setBounds(540, 510, 100, 50);
        J_YEAR.setFont(font);
        panel.add(J_YEAR);
     
        // --------------------------------------Salary text / input box
        
        SalaryText = new JLabel("Salary : ");
        SalaryText.setLayout(null);
        SalaryText.setBounds(50, 590, 100, 50);
        SalaryText.setFont(font);
        panel.add(SalaryText);
        
        SalaryBox = new JTextField();
        SalaryBox.setLayout(null);
        SalaryBox.setBounds(250, 590, 300,50);
        SalaryBox.setFont(font);
        panel.add(SalaryBox);
       
        // ------------------------------------  Department text / input Box
        
        DepartmentText = new JLabel("Department : ");
        DepartmentText.setLayout(null);
        DepartmentText.setBounds(50, 670, 130, 50);
        DepartmentText.setFont(font);
        panel.add(DepartmentText);
        
        DepartmentBox = new JTextField();
        DepartmentBox.setBounds(250, 670, 300, 50);
        DepartmentBox.setLayout(null);
        DepartmentBox.setFont(font);
        panel.add(DepartmentBox);
        
        // ----------------------------------------  City text / pin
        
        CityText = new JLabel("City : ");
        CityText.setLayout(null);
        CityText.setBounds(50, 750, 100, 50);
        CityText.setFont(font);
        panel.add(CityText);
        
        CityBox = new JTextField();
        CityBox.setLayout(null);
        CityBox.setBounds(250, 750, 300, 50);
        CityBox.setFont(font);
        panel.add(CityBox);
        
        // -------------------------------------------  pin text / input box
        
        PincodeText = new JLabel("Pincode : ");
        PincodeText.setLayout(null);
        PincodeText.setBounds(50, 830, 100, 50);
        PincodeText.setFont(font);
        panel.add(PincodeText);
        
        PincodeBox = new  JTextField();
        PincodeBox.setLayout(null);
        PincodeBox.setBounds(250, 830, 250, 50);
        PincodeBox.setFont(font);
        panel.add(PincodeBox);
        
        
        Submit = new JButton("Submit");
        Submit.setLayout(null);
        Submit.setBounds(250, 900, 200, 50);
        Submit.setFont(font);
        panel.add(Submit);

        addFrame.setVisible(true);
        
        
        //-------------------------get selected data from data base and set in JTextfield-----------------------
        
            //`write query
            
            try{
            
            ResultSet set = s.executeQuery("select * from employee where id="+emp_id+";");

            while (set.next()) {                
                String id = set.getString("id");
                String fname = set.getString("fname");
                String lname = set.getString("lname");
                String mobile = set.getString("mobile");
                String email = set.getString("email");
                String dob = set.getString("dob");
                String doj = set.getString("doj");
                String salary = set.getString("salary");
                String dept = set.getString("department");
                String city = set.getString("city");
                String pin = set.getString("pin");
            
            //data come
            //System.out.println(id+" : "+emp_id+" : "+fname+" : "+lname+" : "+email+" : "+mobile+" : "+dob+" : "+doj+" : "+salary+" : "+dept+" : "+city+" : "+pin);
            
            
                try{
                    IDBox.setText(id);
                    IDBox.setEditable(false);
                    FnameBox.setText(fname);
                    LnameBox.setText(lname);
                    MobileBox.setText(mobile);
                    EmailBox.setText(email);
                    SalaryBox.setText(salary);
                    DepartmentBox.setText(dept);
                    CityBox.setText(city);
                    PincodeBox.setText(pin);
                    }catch(NullPointerException npe){
                        JOptionPane.showMessageDialog(null, "Error : "+npe);
                    }
                }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        
        
            
            
            

        Submit.addActionListener((ActionEvent e) -> {
            try{
                    
                    //refreshData();
                    refesh.repaint();
                    table.repaint();
                    model.fireTableDataChanged();
                    addFrame.dispose();
                    updateEmployee();
            }catch(Exception ex){ex.printStackTrace();}
        });
        panel.setVisible(true);
    }
    
    public void collectAndStorData(JFrame p,DefaultTableModel model) throws Exception
    {
        Integer Id,Salary,Pin;
        Long Mobile;
        String FirstName, LastName,Email,BirthDate,JoiningDate,Department,City;    
        try{
            Id =  Integer.parseInt(IDBox.getText());
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(p, "ID cannot be null or empty");
            return;
        }
        FirstName = FnameBox.getText();
        LastName = LnameBox.getText();
        if(FirstName.isEmpty() || LastName.isEmpty()){
            JOptionPane.showMessageDialog(p, "Name field sounds Empty");
            return;
        }        
        try{
            Mobile = Long.parseLong(MobileBox.getText());
            if(Mobile.toString().length() != 10){
                JOptionPane.showMessageDialog(p, "Mobile No Should be 10 Digit");
                return;
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(p, "your 10 digit Mobile No. Require");
            return;
        }
        Email = EmailBox.getText();
        if(!Email.endsWith("@gmail.com")){
            JOptionPane.showMessageDialog(p, "Invalid Email ID Specify.");
            return;
        }
        BirthDate = getBirthDate();
        JoiningDate = getJoiningDate();
        try{
        Salary = Integer.parseInt(SalaryBox.getText());
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(p, "Salary field sounds Empty");
            return;
        }
        Department = DepartmentBox.getText();
        if(Department.isEmpty() || Department == ""){
            JOptionPane.showMessageDialog(p, "You didn't Specify Department");
            return;
        }
        City = CityBox.getText();
        if(City.isEmpty() || City == ""){
            JOptionPane.showMessageDialog(p, "Please Enter City");
            return;
        }
        try{
            Pin = Integer.parseInt(PincodeBox.getText().toString());
            if(Pin.toString().length() != 6){
                JOptionPane.showMessageDialog(p, "Pincode should be 6 digit");
                return;
            }
            
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(p, "Please Enter 6 Digit pincode");
            return;
        }
        String url = "jdbc:mysql://localhost/industry";
        String user = "root";
        String pass = "Akshay";
        String input = "insert into employee values(?,?,?,?,?,?,?,?,?,?,?)";
        Connection c;
        Statement s;
        try
        {
            c = DriverManager.getConnection(url,user,pass);
            s = c.createStatement();
            ResultSet rs = s.executeQuery("select id from employee");
            while(rs.next())
            {
                try{
                    if(rs.getString("id").equals(Id)){
                        JOptionPane.showMessageDialog(p, "ID :  '"+Id+"' already Exist, Please try another ID");
                        return;
                    }
                }catch(Exception ex) {
                   ex.getMessage();
                }
            }
            PreparedStatement ps1 = c.prepareStatement(input);
            ps1.setInt(1, Id);
            ps1.setString(2, FirstName);
            ps1.setString(3, LastName);
            ps1.setLong(4, Mobile);
            ps1.setString(5, Email);
            ps1.setString(6, BirthDate);
            ps1.setString(7, JoiningDate);
            ps1.setInt(8, Salary);
            ps1.setString(9, Department);
            ps1.setString(10, City);
            ps1.setInt(11, Pin);
            int r1 = ps1.executeUpdate();
            if(r1 == -1) {
                JOptionPane.showMessageDialog(p, "Error While inserting");
            }
            else {
                JOptionPane.showMessageDialog(p, "Successfully inserted");
                p.dispose();
                table.repaint();
                model.fireTableDataChanged();
                //new Welcome().getData();
            }
        }catch(Exception ex){ex.printStackTrace();}
        refreshData();
    }
}



//-------------------------------------------------------------------------------------------------------------------------------

/*
    public void modifyExsistingEmployee(int emp_id) 
    {
        JFrame addFrame = new JFrame("Add One More Employee");
        addFrame.setLayout(null);
        addFrame.setBounds(500, 0, 900, 1030);
        addFrame.setBackground(Color.GRAY);
        
        
        // ---------------------------------ID Text / Label
        IDText = new JLabel("ID : ");
        IDText.setLayout(null);
        IDText.setBounds(50,30,100,50);
        IDText.setFont(font);
        addFrame.add(IDText);
                
        IDBox = new JTextField(emp_id);
        IDBox.setText(String.valueOf(emp_id));
        IDBox.setLayout(null);
        IDBox.setBounds(250, 30, 300, 50);
        IDBox.setFont(font);
        IDBox.setEditable(false);
        addFrame.add(IDBox);
        
        // --------------------------------- First Name Text / Label
        FnameText = new JLabel("First Name : ");
        FnameText.setLayout(null);
        FnameText.setBounds(50, 110, 150, 50);
        FnameText.setFont(font);
        addFrame.add(FnameText);
        
        FnameBox = new JTextField();
        FnameBox.setLayout(null);
        FnameBox.setBounds(250,110,300,50);
        FnameBox.setFont(font);
        addFrame.add(FnameBox);
        
        // --------------------------------- Last name Text / input Box 
        
        LnameText = new JLabel("Last Name : ");
        LnameText.setBounds(50, 190, 150, 50);
        LnameText.setLayout(null);
        LnameText.setFont(font);
        addFrame.add(LnameText);
        
        LnameBox = new JTextField();
        LnameBox.setLayout(null);
        LnameBox.setBounds(250, 190, 300, 50);
        LnameBox.setFont(font);
        addFrame.add(LnameBox);
        
        // ---------------------------------- mobile Text / input box
        MobileText = new JLabel("Mobile No : ");
        MobileText.setLayout(null);
        MobileText.setBounds(50, 270, 150, 50);
        MobileText.setFont(font);
        addFrame.add(MobileText);
        
        MobileBox = new JTextField();
        MobileBox.setLayout(null);
        MobileBox.setBounds(250, 270, 300, 50);
        MobileBox.setFont(font);
        addFrame.add(MobileBox);
        
        // -----------------------------------  email text / input box
        
        EmailText = new JLabel("Email ID : ");
        EmailText.setLayout(null);
        EmailText.setBounds(50, 350, 150, 50);
        EmailText.setFont(font);
        addFrame.add(EmailText);
        
        EmailBox = new JTextField();
        EmailBox.setLayout(null);
        EmailBox.setBounds(250, 350, 300, 50);
        EmailBox.setFont(font);
        addFrame.add(EmailBox);
        
        // ------------------------------------- Birth Date 
        
        BirthDayText = new JLabel("Date of Birth : ");
        BirthDayText.setLayout(null);
        BirthDayText.setBounds(50,430,150,50);
        BirthDayText.setFont(font);
        addFrame.add(BirthDayText);
        
        B_DAY = new JComboBox(day);
        B_DAY.setBounds(250, 430, 100, 50);
        B_DAY.setLayout(null);
        B_DAY.setFont(font);
        addFrame.add(B_DAY);
        
        B_MONTH = new JComboBox(month);
        B_MONTH.setLayout(null);
        B_MONTH.setBounds(370,430, 150, 50);
        B_MONTH.setFont(font);
        addFrame.add(B_MONTH);
        
        B_YEAR = new JComboBox(year);
        B_YEAR.setLayout(null);
        B_YEAR.setBounds(540, 430, 100, 50);
        B_YEAR.setFont(font);
        addFrame.add(B_YEAR);
        
       // ---------------------------------------- Joining Date
        
        JoiningText = new JLabel("Date of Joining : ");
        JoiningText.setBounds(50, 510, 150, 50);
        JoiningText.setLayout(null);
        JoiningText.setFont(font);
        addFrame.add(JoiningText);
        
        J_DAY = new JComboBox(day);
        J_DAY.setBounds(250, 510, 100, 50);
        J_DAY.setLayout(null);
        J_DAY.setFont(font);
        addFrame.add(J_DAY);
        
        J_MONTH = new JComboBox(month);
        J_MONTH.setLayout(null);
        J_MONTH.setBounds(370,510, 150, 50);
        J_MONTH.setFont(font);
        addFrame.add(J_MONTH);
        
        J_YEAR = new JComboBox(year);
        J_YEAR.setLayout(null);
        J_YEAR.setBounds(540, 510, 100, 50);
        J_YEAR.setFont(font);
        addFrame.add(J_YEAR);
     
        // --------------------------------------Salary text / input box
        
        SalaryText = new JLabel("Salary : ");
        SalaryText.setLayout(null);
        SalaryText.setBounds(50, 590, 100, 50);
        SalaryText.setFont(font);
        addFrame.add(SalaryText);
        
        SalaryBox = new JTextField();
        SalaryBox.setLayout(null);
        SalaryBox.setBounds(250, 590, 300,50);
        SalaryBox.setFont(font);
        addFrame.add(SalaryBox);
       
        // ------------------------------------  Department text / input Box
        
        DepartmentText = new JLabel("Department : ");
        DepartmentText.setLayout(null);
        DepartmentText.setBounds(50, 670, 130, 50);
        DepartmentText.setFont(font);
        addFrame.add(DepartmentText);
        
        DepartmentBox = new JTextField();
        DepartmentBox.setBounds(250, 670, 300, 50);
        DepartmentBox.setLayout(null);
        DepartmentBox.setFont(font);
        addFrame.add(DepartmentBox);
        
        // ----------------------------------------  City text / pin
        
        CityText = new JLabel("City : ");
        CityText.setLayout(null);
        CityText.setBounds(50, 750, 100, 50);
        CityText.setFont(font);
        addFrame.add(CityText);
        
        CityBox = new JTextField();
        CityBox.setLayout(null);
        CityBox.setBounds(250, 750, 300, 50);
        CityBox.setFont(font);
        addFrame.add(CityBox);
        
        // -------------------------------------------  pin text / input box
        
        PincodeText = new JLabel("Pincode : ");
        PincodeText.setLayout(null);
        PincodeText.setBounds(50, 830, 100, 50);
        PincodeText.setFont(font);
        addFrame.add(PincodeText);
        
        PincodeBox = new  JTextField();
        PincodeBox.setLayout(null);
        PincodeBox.setBounds(250, 830, 250, 50);
        PincodeBox.setFont(font);
        addFrame.add(PincodeBox);
        
        
        Submit = new JButton("Submit");
        Submit.setLayout(null);
        Submit.setBounds(250, 900, 200, 50);
        Submit.setFont(font);
        addFrame.add(Submit);
        
        
        
        Submit.addActionListener((ActionEvent e) -> {
            updateEmployee();
            
        });
        addFrame.setVisible(true);
    }
    */
