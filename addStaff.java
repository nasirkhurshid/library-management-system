package LMS;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Date;
//import java.text.SimpleDateFormat;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants; 
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class addStaff extends JFrame implements ActionListener{
    JTextField name, pass,desig,join, usert, shiftt;
    JLabel labelc,date_label;
    JPanel pc,date_p;
    String n;
    addStaff(String n){
    	this.n = n;
        String blue = "#3566b5";
        
        JPanel back = new JPanel();
        back.setLayout(new GridLayout());
        back.setBounds(5,18,60,30);
        JButton bbtn = new JButton("Back");
        bbtn.setBackground(Color.decode(blue));
        bbtn.setForeground(Color.white);
        bbtn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        bbtn.addActionListener(this);
        back.add(bbtn);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout());
        p1.setBounds(70,10,650,50);
        JLabel label = new JLabel("DRSM LIBRARY MANAGEMENT SYSTEM");
        label.setForeground(Color.decode(blue));
        label.setFont(new Font("Times New Roman",Font.BOLD,32));
        p1.add(label);

        JPanel logged = new JPanel();
        logged .setLayout(new GridLayout());
        logged .setBounds(650,12,220,50);
        JLabel admin = new JLabel(n);
        admin.setForeground(Color.BLACK);
        admin.setHorizontalAlignment(SwingConstants.RIGHT);
        admin.setFont(new Font("Calibri",Font.BOLD,20));
        logged.add(admin);

        JButton logout = new JButton("Logout");
        logout.setBackground(Color.decode(blue));
        logout.setForeground(Color.white);
        logout.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        logout.addActionListener(this);

        JPanel p2 = new JPanel();
        p2.setBounds(880,18,80,30);
        p2.setLayout(new GridLayout());
        p2.add(logout);

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout());
        p3.setBounds(0,100,1000,50);
        JLabel tile = new JLabel("Add Staff");
        tile.setForeground(Color.BLACK);
        tile.setHorizontalAlignment(SwingConstants.CENTER);
        tile.setFont(new Font("Times New Roman",Font.BOLD,25));
        p3.add(tile);

        JLabel namet = new JLabel("Name");
        namet.setFont(new Font("Times New Roman",Font.PLAIN,20));
        namet.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel user = new JLabel("Username");
        user.setFont(new Font("Times New Roman",Font.PLAIN,20));
        user.setHorizontalAlignment(SwingConstants.RIGHT);
        usert = new JTextField();
        usert.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        
        JLabel passt = new JLabel("Password");
        passt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        passt.setHorizontalAlignment(SwingConstants.RIGHT);
        name = new JTextField();
        name.setFont(new Font("Times New Roman",Font.PLAIN,20));
        pass = new JTextField();
        pass.setFont(new Font("Times New Roman",Font.PLAIN,20));

        JLabel desg = new JLabel("Designation");
        desg.setFont(new Font("Times New Roman",Font.PLAIN,20));
        desg.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel joind = new JLabel("Joining Date");
        joind.setFont(new Font("Times New Roman",Font.PLAIN,20));
        joind.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel shift = new JLabel("Shift");
        shift.setFont(new Font("Times New Roman",Font.PLAIN,20));
        shift.setHorizontalAlignment(SwingConstants.RIGHT);
        
        desig = new JTextField();
        desig.setFont(new Font("Times New Roman",Font.PLAIN,20));
        join = new JTextField();
        join.setFont(new Font("Times New Roman",Font.PLAIN,20));
        shiftt = new JTextField();
        shiftt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(7,2,10,10));
        p4.setBounds(85,170,550,280);
        p4.add(namet);
        p4.add(name);
        p4.add(user);
        p4.add(usert);
        p4.add(passt);
        p4.add(pass);
        p4.add(desg);
        p4.add(desig);
        p4.add(joind);
        p4.add(join);
        p4.add(shift);
        p4.add(shiftt);

        pc = new JPanel();
        pc.setLayout(new GridLayout());
        pc.setBounds(645,170,250,30);

        labelc = new JLabel("Enter valid name");
        labelc.setForeground(Color.decode(blue));
        labelc.setFont(new Font("Times New Roman",Font.PLAIN,15));
        pc.add(labelc);

        date_p = new JPanel();
        date_p.setLayout(new GridLayout());
        date_p.setBounds(645,310,250,30);
        
        date_label = new JLabel("Enter Date in DD/MM/YYYY Format");
        date_label.setForeground(Color.decode(blue));
        date_label.setFont(new Font("Times New Roman",Font.PLAIN,15));
        date_p.add(date_label);
        
        JButton addstf = new JButton("Add");
        addstf.setBackground(Color.decode(blue));
        addstf.setForeground(Color.white);
        addstf.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addstf.addActionListener(this);

        JPanel p5 = new JPanel();
        p5.setBounds(465,450,80,40);
        p5.setLayout(new GridLayout());
        p5.add(addstf);

        setTitle("DRSM LMS");
        setSize(1000,600);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(back);
        add(p1);
        add(logged);
        add(p2);
        add(pc);
        add(date_p);
        add(p3);
        add(p4);
        add(p5);
        labelc.setVisible(false);
        date_label.setVisible(false);
    }
      
    public static boolean isValidDate(String name)
    {
        String regex="^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }  
        Matcher m = p.matcher(name);
        if(!m.matches())
        {
        	return false;
        }
        else
        {
        	return true;
        }
    }
    
    public static boolean isValidname(String name)
    {
        String regex = "^[a-zA-Z\s]+$";;
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        if(!m.matches())
        {
        	return false;
        }
        else
        {
        	return true;
        }
    }
    
    @Override
	public void actionPerformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        String btn = b.getText();
        if(btn=="Back") {
        	dispose();
        	new adminUI(n);
        }
         if(btn=="Add"){
        	String _name = name.getText();
     	    String user = usert.getText();
     	    String password = pass.getText();
     	    String designation = desig.getText();
     	    String shift = shiftt.getText();
     	    String joining = join.getText();
     	    int count=0;
     	  	if(!isValidname(_name)) {
     	  		labelc.setVisible(true);
     	  		count++;
     	  	}        	  	
     	  	if(!isValidDate(joining)) {
     	  		date_label.setVisible(true);
     	  		count++;
     	  	}
            try{
        	  	if(count==0){
        	  		String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
                    Connection con  = DriverManager.getConnection(url);
                    String query = "SELECT * from Staff";
    	            Statement stmt = con.createStatement();
    	            ResultSet res = stmt.executeQuery(query);
    	            int check=0;
    	            while(res.next()) {
    	            	if(res.getString(3).equals(user)) {
    	            		check++;
    	            	}
    	            }
    	            if(check==0){
    	                PreparedStatement preparedStatement=con.prepareStatement("insert into Staff(Name,Username,Password,Status,Shift,Designation,Joining_Date) values(?,?,?,?,?,?,?)");
    	                preparedStatement.setString(1,_name);
    	                preparedStatement.setString(2,user);
    	                preparedStatement.setString(3,password);
    	                preparedStatement.setString(4,"Yes");
    	                preparedStatement.setString(5,shift);
    	                preparedStatement.setString(6,designation);
    	                preparedStatement.setString(7,joining);
    	                preparedStatement.executeUpdate();
    	                JOptionPane.showMessageDialog(this,"Staff Added");
    	                dispose();
    	                new adminUI(n);
    	                System.out.println("Staff Data Inserted");
    	            }
    	            else {
    	            	JOptionPane.showMessageDialog(this,"Username already exists!","Invalid username",JOptionPane.WARNING_MESSAGE);
    	            }
        	  	}
            }catch(Exception t){
                System.out.println("Connection Error");
            }
        }
        if(btn=="Logout"){
            dispose();
            new home();
        }
    }
    
	public static void main(String[] args)throws SQLException{
//        addStaff obj = new addStaff();
    }
}
