package LMS;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class staffLogin extends JFrame implements ActionListener{
    JTextField name, pass;
    staffLogin(){
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
        JLabel tile = new JLabel("Staff Login");
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
        
        JButton login = new JButton("Login");
        login.setBackground(Color.decode(blue));
        login.setForeground(Color.white);
        login.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        login.addActionListener(this);

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(4,1,0,5));
        p4.setBounds(340,170,320,150);
        p4.add(namet);
        p4.add(name);
        p4.add(passt);
        p4.add(pass);

        JPanel p5 = new JPanel();
        p5.setBounds(450,350,80,40);
        p5.setLayout(new GridLayout());
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
	           	 String url ="jdbc:ucanaccess://C://Users//LMS_DB.accdb";
	             Connection con  = DriverManager.getConnection(url);
	             String query = "SELECT * from Staff";
	             Statement stmt = con.createStatement();
	             ResultSet res = stmt.executeQuery(query);
	             int errorCount=1;
	             while(res.next()){
	            	 if(res.getString(3).equals(name_) && res.getString(4).equals(password)) {
	            		 errorCount=0;
	            		 String query2 = "SELECT * from Member";
	    	             Statement stmt2 = con.createStatement();
	    	             ResultSet res2 = stmt2.executeQuery(query2);
	    	             while(res2.next()){
	 	            		 String query3 = "SELECT * from Issuance";
	 	    	             Statement stmt3 = con.createStatement();
	 	    	             ResultSet res3 = stmt3.executeQuery(query3);
	 	    	             while(res3.next()) {
	 	    	            	 if((res2.getString(1).equals(res3.getString(5)))&&(res3.getString(3).equals("Null"))){
	 	    	            		 String issueDate = res3.getString(2);
	 	    	            		 String returnDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	 	    	            		 
	 	    	            		 Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(issueDate);
	 	    	         			 Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(returnDate);
	 	    	         			 long diff = date1.getTime() - date2.getTime();
	 	    	     
	 	    	         		     TimeUnit time = TimeUnit.DAYS; 
	 	    	     		         long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
	 	    	     		         int days = (int)diffrence;
	 	    	     		         int fine;
	 	    	     		         if(days>14 || days<-14) {
	 	    	     		        	fine = days*15;
	 	    	     		         }
	 	    	     		         else {
	 	    	     		        	 fine = 0;
	 	    	     		         }
	 	    	     		         fine = Math.abs(fine);
	 	    	     		         System.out.println(fine);
	 	    	     		         String amount = String.valueOf(fine);
	 	    	                     System.out.println(res3.getString(1));
	 	    	     		         PreparedStatement returning = con.prepareStatement("update Fine SET Amount=? WHERE BookId="+Integer.parseInt(res3.getString(4))+" AND IssuanceId="+Integer.parseInt(res3.getString(1)));
	 		   	            		 returning.setString(1,amount);
	 		   	            		 returning.executeUpdate();  
	 	    	            	 }
	 	    	             }
	    	             }
	            		 String stfid = res.getString(1);
	            		 dispose();
	                     new staffUI(stfid,res.getString(3));
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
        if(btn=="Back") {
        	dispose();
        	new home();
        }
    }
    
    public static void main(String[] args) {
        staffLogin obj = new staffLogin();
    }
}
