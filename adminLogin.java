package LMS;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class adminLogin extends JFrame implements ActionListener{
    JTextField name, pass;
    adminLogin(){
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

        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout());
        p2.setBounds(70,10,650,50);
        JLabel label = new JLabel("DRSM LIBRARY MANAGEMENT SYSTEM");
        label.setForeground(Color.decode(blue));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Times New Roman",Font.BOLD,32));
        p2.add(label);


        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout());
        p3.setBounds(0,100,1000,50);
        JLabel tile = new JLabel("Admin Login");
        tile.setForeground(Color.BLACK);
        tile.setHorizontalAlignment(SwingConstants.CENTER);
        tile.setFont(new Font("Times New Roman",Font.BOLD,25));
        p3.add(tile);

        JLabel namet = new JLabel("Username");
        namet.setFont(new Font("Times New Roman",Font.PLAIN,20));
        JLabel passt = new JLabel("Password");
        passt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        name = new JTextField();
        name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pass = new JPasswordField();
        pass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(4,1,0,5));
        p4.setBounds(340,170,320,150);
        p4.add(namet);
        p4.add(name);
        p4.add(passt);
        p4.add(pass);


        JButton login = new JButton("Login");
        login.setBackground(Color.decode(blue));
        login.setForeground(Color.white);
        login.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        login.addActionListener(this);
        
        JButton forgot = new JButton("Forgot");
        forgot.setBackground(Color.decode(blue));
        forgot.setForeground(Color.white);
        forgot.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        forgot.addActionListener(this);
        
        JPanel p5 = new JPanel();
        p5.setBounds(390,350,240,40);
        p5.setLayout(new GridLayout(1,2,50,0));
        p5.add(forgot);
        p5.add(login);
        

        setTitle("DRSM LMS");
        setSize(1000,600);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(back);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
    }
    
    @Override
	public void actionPerformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        String btn = b.getText();
        if(btn=="Login"){
            try{
            	String name_ = name.getText();
        	    String password = pass.getText();
	           	 String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
	             Connection con  = DriverManager.getConnection(url);
	             String query = "SELECT * from Admin";
	             Statement stmt = con.createStatement();
	             ResultSet res = stmt.executeQuery(query);
	             int errorCount = 1;
	             while(res.next()){
	            	 if(res.getString(3).equals(name_) && res.getString(4).equals(password)) {
	            		 errorCount=0;
	            		 dispose();
	                     new adminUI(res.getString(3));
	                     break;
	            	 }
	             }
	             if(errorCount!=0) {
	            	 JOptionPane.showMessageDialog(this,"Username or password are invalid!","Invalid Login",JOptionPane.WARNING_MESSAGE);
	             }
            }catch(Exception t){
                System.out.println("Connection Error");
            }
        }
        if(btn=="Forgot") {
        	dispose();
        	new forgotAdmin();
        }
        if(btn=="Back") {
        	dispose();
        	new home();
        }
    }
    public static void main(String[] args) {
        adminLogin obj = new adminLogin();
    }
}
