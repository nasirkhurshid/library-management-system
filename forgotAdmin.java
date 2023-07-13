package LMS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

public class forgotAdmin extends JFrame implements ActionListener{
    JTextField usert, sect, passt;
    forgotAdmin(){
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

        JPanel p2 = new JPanel();
        p2.setBounds(880,18,80,30);
        p2.setLayout(new GridLayout());

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout());
        p3.setBounds(0,100,1000,50);
        JLabel tile = new JLabel("Forgot Password");
        tile.setForeground(Color.BLACK);
        tile.setHorizontalAlignment(SwingConstants.CENTER);
        tile.setFont(new Font("Times New Roman",Font.BOLD,25));
        p3.add(tile);

        JLabel user = new JLabel("Username");
        user.setFont(new Font("Times New Roman",Font.PLAIN,20));
        user.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel sec = new JLabel("Security Key");
        sec.setFont(new Font("Times New Roman",Font.PLAIN,20));
        sec.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel pass = new JLabel("New Password");
        pass.setFont(new Font("Times New Roman",Font.PLAIN,20));
        pass.setHorizontalAlignment(SwingConstants.RIGHT);
        
        usert = new JTextField();
        usert.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        sect = new JTextField();
        sect.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passt = new JTextField();
        passt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        
        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(3,2,10,10));
        p4.setBounds(85,170,550,130);
        p4.add(user);
        p4.add(usert);
        p4.add(sec);
        p4.add(sect);
        p4.add(pass);
        p4.add(passt);
        
        JButton update = new JButton("Update");
        update.setBackground(Color.decode(blue));
        update.setForeground(Color.white);
        update.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        update.addActionListener(this);

        JPanel p5 = new JPanel();
        p5.setBounds(450,330,90,40);
        p5.setLayout(new GridLayout());
        p5.add(update);

        setTitle("DRSM LMS");
        setSize(1000,600);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(back);
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
    }
    
    @Override
	public void actionPerformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        String btn = b.getText();
        if(btn=="Back") {
        	dispose();
        	new adminLogin();
        }
        if(btn=="Update"){
        	String username = usert.getText();
    	    String security = sect.getText();
    	    String password = passt.getText();
            try{
            	String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
                Connection con  = DriverManager.getConnection(url);
                String query = "SELECT * from Admin";
	            Statement stmt = con.createStatement();
	            ResultSet res = stmt.executeQuery(query);
	            int check=1;
	            while(res.next()) {
	            	if((username.equals(res.getString(3)))&&(security.equals(res.getString(5)))) {
	            		check=0;
	            		PreparedStatement preparedStatement=con.prepareStatement("update Admin SET Password=? WHERE Id="+Integer.parseInt(res.getString(1)));		            	 
		        		preparedStatement.setString(1,password);
		                preparedStatement.executeUpdate();
		                JOptionPane.showMessageDialog(this,"Password Updated");
		                dispose();
		            	new adminLogin(); 
	            	}
	            }
	            if(check==1) {
	            	JOptionPane.showMessageDialog(this,"Username or Security Key is invalid!","Invalid",JOptionPane.WARNING_MESSAGE);
	            }
            }catch(Exception t){
                System.out.println("Connection Error");
            }
        }
        if(btn =="Logout"){
            dispose();
			new home();
        }
    }
    public static void main(String[] args) {
        forgotAdmin obj = new forgotAdmin();
    }
}
