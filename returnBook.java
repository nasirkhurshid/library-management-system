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

public class returnBook extends JFrame implements ActionListener{
    JTextField bid_t, mid_t,returnt;
    JLabel date_label;
    JPanel date_p;
    String staffId, n;
    returnBook(String id, String n){
    	staffId = id;
    	this.n=n;
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
        //label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(new Font("Times New Roman",Font.BOLD,32));
        p1.add(label);

        JPanel logged = new JPanel();
        logged .setLayout(new GridLayout());
        logged .setBounds(650,12,220,50);
        JLabel name = new JLabel(n);
        name.setForeground(Color.BLACK);
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        name.setFont(new Font("Calibri",Font.BOLD,20));
        logged.add(name);

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
        JLabel tile = new JLabel("Return Book");
        tile.setForeground(Color.BLACK);
        tile.setHorizontalAlignment(SwingConstants.CENTER);
        tile.setFont(new Font("Times New Roman",Font.BOLD,25));
        p3.add(tile);

        JLabel b_id = new JLabel("Book ID");
        b_id.setFont(new Font("Times New Roman",Font.PLAIN,20));
        b_id.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel m_id = new JLabel("Member ID");
        m_id.setFont(new Font("Times New Roman",Font.PLAIN,20));
        m_id.setHorizontalAlignment(SwingConstants.RIGHT);
        bid_t = new JTextField();
        bid_t.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mid_t = new JTextField();
        mid_t.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        
        JLabel returnDate = new JLabel("Return Date");
        returnDate.setFont(new Font("Times New Roman",Font.PLAIN,20));
        returnDate.setHorizontalAlignment(SwingConstants.RIGHT);
        returnt = new JTextField();
        returnt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        returnt.setText(currentDate);
        
        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(3,2,10,10));
        p4.setBounds(85,170,550,130);
        p4.add(b_id);
        p4.add(bid_t);
        p4.add(m_id);
        p4.add(mid_t);
        p4.add(returnDate);
        p4.add(returnt);

        date_p = new JPanel();
        date_p.setLayout(new GridLayout());
        date_p.setBounds(645,265,250,30);
        
        date_label = new JLabel("Enter Date in DD/MM/YYYY Format");
        date_label.setForeground(Color.decode(blue));
        date_label.setFont(new Font("Times New Roman",Font.PLAIN,15));
        date_p.add(date_label);
        
        JButton ret = new JButton("Return");
        ret.setBackground(Color.decode(blue));
        ret.setForeground(Color.white);
        ret.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        ret.addActionListener(this);

        JPanel p5 = new JPanel();
        p5.setBounds(450,330,90,40);
        p5.setLayout(new GridLayout());
        p5.add(ret);

        setTitle("DRSM LMS");
        setSize(1000,600);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(back);
        add(p1);
        add(logged);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(date_p);
        date_label.setVisible(false);
    }

    public static boolean isValidDate(String name){
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
    
    @Override
	public void actionPerformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        String btn = b.getText();
        if(btn=="Back") {
        	dispose();
        	new staffUI(staffId,n);
        }
        if(btn=="Return"){
        	String getid = bid_t.getText();
		  	String book = getid.substring(0,1);
        	String bookid = getid.substring(1);
        	String getid1 = mid_t.getText();
		  	String memb = getid1.substring(0,1);
        	String memid = getid1.substring(1);
    	    String return_date = returnt.getText();
    	    int count=0;
    	  	if(!isValidDate(return_date)) {
    	  		date_label.setVisible(true);
    	  		count++;
    	  	}
            try{
            	if(count==0) {
            		if(book.equals("B") && memb.equals("M")) {
		        	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
		                Connection con  = DriverManager.getConnection(url);
		                String query = "SELECT * from Issuance WHERE MemberId="+Integer.parseInt(memid);
			            Statement stmt = con.createStatement();
			            ResultSet res = stmt.executeQuery(query);
			            int check=1;
			            while(res.next()){
			            	 if((res.getString(4).equals(bookid)) && (res.getString(5).equals(memid)) &&(res.getString(3).equals("Null"))) {
			            		 check = 0;
			            		 PreparedStatement preparedStatement=con.prepareStatement("update Book SET Status=? WHERE BookId="+Integer.parseInt(bookid));
			            		 preparedStatement.setString(1,"Yes");
			            		 preparedStatement.executeUpdate();
			            		
			     		         PreparedStatement returning = con.prepareStatement("update Issuance SET ReturnDate=? WHERE BookId="+Integer.parseInt(bookid)+" AND MemberId="+Integer.parseInt(memid));
			            		 returning.setString(1,return_date);
			            		 returning.executeUpdate();
			            		 
			    	            	 if((res.getString(4).equals(bookid))&&(res.getString(5).equals(memid))) {
			    	            		 String issueDate = res.getString(2);
			    	            		 Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(issueDate);
			    	         			 Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(return_date);
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
			    	     		         String amount = String.valueOf(fine);
			    	     		         PreparedStatement fineUpdate = con.prepareStatement("update Fine SET Amount=? WHERE BookId="+Integer.parseInt(res.getString(4))+" AND IssuanceId="+Integer.parseInt(res.getString(1)));
				   	            		 fineUpdate.setString(1,amount);
				   	            		 fineUpdate.executeUpdate();  
			    	            	 }
			            	 }
			             }
			            if(check==1) {
			            	JOptionPane.showMessageDialog(this,"No Record Found","Not found",JOptionPane.WARNING_MESSAGE);
			            }
			            else {
			            	JOptionPane.showMessageDialog(this,"Book Returned");
			                System.out.println("Book Returned!");
			                dispose();
			                new staffUI(staffId,n);
			            }
	            	}
            		else {
    	  				JOptionPane.showMessageDialog(this,"Invalid Book ID or Member ID!","Invalid",JOptionPane.WARNING_MESSAGE);
    	  			}
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
//        returnBook obj = new returnBook("1");
    }
}
