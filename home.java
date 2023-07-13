package LMS;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class home extends JFrame implements ActionListener{
    JTextField search;
    home(){
        String blue = "#3566b5";
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout());
        p1.setBounds(700,15,180,30);
        search = new JTextField("");
        search.setFont(new Font("Bell MT",Font.PLAIN,15));
        p1.add(search);
        
        JPanel p11 = new JPanel();
        p11.setLayout(new GridLayout());
        p11.setBounds(885,15,80,30);
        JButton sbtn = new JButton("Search");
        sbtn.setBackground(Color.decode(blue));
        sbtn.setForeground(Color.white);
        sbtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        sbtn.addActionListener(this);
        p11.add(sbtn);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout());
        p2.setBounds(0,100,1000,50);
        JLabel label = new JLabel("DRSM LIBRARY MANAGEMENT SYSTEM");
        label.setForeground(Color.decode(blue));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman",Font.BOLD,40));
        p2.add(label);


        //buttons 
        JButton admin = new JButton("Login as Admininstrator");
        admin.setBackground(Color.decode(blue));
        admin.setForeground(Color.white);
        admin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        admin.addActionListener(this);

        JButton staff = new JButton("Login as Staff");
        staff.setBackground(Color.decode(blue));
        staff.setForeground(Color.white);
        staff.addActionListener(this);

        staff.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        JButton member = new JButton("Login as Member");
        member.setBackground(Color.decode(blue));
        member.setForeground(Color.white);
        member.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        member.addActionListener(this);
        //buttons end

        JPanel p3 = new JPanel();
        p3.setBounds(340,180,320,150);
        p3.setLayout(new GridLayout(3,1,0,20));
        p3.add(admin);
        p3.add(staff);
        p3.add(member);
        int memcount=0;
        int bookcount=0;
        try{
    	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
            Connection con  = DriverManager.getConnection(url);
            Statement stmt2 = con.createStatement();
            ResultSet res2 = stmt2.executeQuery("SELECT count(*) from Member");
            res2.next();
            memcount = res2.getInt(1);
            Statement stmt3 = con.createStatement();
            ResultSet res3 = stmt3.executeQuery("SELECT count(*) from Book");
            res3.next();
            bookcount = res3.getInt(1);
        }catch(Exception t){
            System.out.println("Connection Error");
        }
        
		String memb = "Total Members: " + memcount;
		JLabel totalMem = new JLabel(memb);
        totalMem.setForeground(Color.decode(blue));
        totalMem.setFont(new Font("Times New Roman",Font.BOLD,20));
        totalMem.setHorizontalAlignment(SwingConstants.CENTER);
 
		String bk = "Total Books: " + bookcount;
		JLabel totalBook = new JLabel(bk);
        totalBook.setForeground(Color.decode(blue));
        totalBook.setFont(new Font("Times New Roman",Font.BOLD,20));
        totalBook.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JPanel p4 = new JPanel();
        p4.setBounds(350,450,300,50);
        p4.setLayout(new GridLayout(2,1,0,10));
        p4.add(totalMem);
        p4.add(totalBook);
        
        setTitle("DRSM LMS");
        setSize(1000,600);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(p1);
        add(p11);
        add(p2);
        add(p3);
        add(p4);
    }

    @Override
	public void actionPerformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        String btn = b.getText();
        if(btn == "Search") {  
        	String find = search.getText();
        	dispose();
        	new showbook(find);
        }
        
        if(btn =="Login as Admininstrator"){
            dispose();
			new adminLogin();
        }
        else if(btn =="Login as Staff"){
            dispose();
			new staffLogin();
        }
        else if(btn =="Login as Member"){
            dispose();
			new memLogin();
        }
    }
    public static void main(String[] args) {
        home obj = new home();
    }
}
