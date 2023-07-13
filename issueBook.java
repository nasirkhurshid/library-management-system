package LMS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class issueBook extends JFrame implements ActionListener{
    JTextField bid_t, mid_t, sid_t, issue;
    JLabel date_label;
    JPanel date_p;
    String staffId, n;
    issueBook(String id, String n){
    	staffId = id;
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
        JLabel tile = new JLabel("Issue Book");
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
        bid_t.setFont(new Font("Times New Roman",Font.PLAIN,20));
        mid_t = new JTextField();
        mid_t.setFont(new Font("Times New Roman",Font.PLAIN,20));
          
        JLabel issDate = new JLabel("Issue Date");
        issDate.setFont(new Font("Times New Roman",Font.PLAIN,20));
        issDate.setHorizontalAlignment(SwingConstants.RIGHT);        
        
        issue = new JTextField();
        issue.setFont(new Font("Times New Roman",Font.PLAIN,20));
        String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        issue.setText(currentDate);

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(4,2,10,10));
        p4.setBounds(85,170,550,180);
        p4.add(b_id);
        p4.add(bid_t);
        p4.add(m_id);
        p4.add(mid_t);
        p4.add(issDate);
        p4.add(issue);
        
        date_p = new JPanel();
        date_p.setLayout(new GridLayout());
        date_p.setBounds(645,315,250,30);
        
        date_label = new JLabel("Enter Date in DD/MM/YYYY Format");
        date_label.setForeground(Color.decode(blue));
        date_label.setFont(new Font("Times New Roman",Font.PLAIN,15));
        date_p.add(date_label);

        JButton issue = new JButton("Issue");
        issue.setBackground(Color.decode(blue));
        issue.setForeground(Color.white);
        issue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        issue.addActionListener(this);

        JPanel p5 = new JPanel();
        p5.setBounds(450,380,80,40);
        p5.setLayout(new GridLayout());
        p5.add(issue);

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
        if(btn=="Issue"){
        	String getid = bid_t.getText();
		  	String book = getid.substring(0,1);
        	String bookid = getid.substring(1);
        	String getid1 = mid_t.getText();
		  	String memb = getid1.substring(0,1);
        	String memid = getid1.substring(1);
    	    String iss = issue.getText();
    	    int count=0;
    	  	if(!isValidDate(iss)) {
    	  		date_label.setVisible(true);
    	  		count++;
    	  	}
            try{
            	if(count==0) {
            		if(book.equals("B") && memb.equals("M")) {
            			System.out.println("");
		        	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
		                Connection con  = DriverManager.getConnection(url);
		                String query = "SELECT * from Book WHERE BookId="+Integer.parseInt(bookid);
			            Statement stmt = con.createStatement();
			            ResultSet res = stmt.executeQuery(query);
			            while(res.next()){
			            	 if(res.getString(1).equals(bookid)) {
			            		 String query2 = "SELECT * from Member WHERE MemberId="+Integer.parseInt(memid);
			    	             Statement stmt2 = con.createStatement();
			    	             ResultSet res2 = stmt2.executeQuery(query2);
			    	             int check = 1;
			    	             while(res2.next()) {
			    	            	 if((res2.getString(1).equals(memid)) && (res.getString(8).equals("Yes"))) {
			    	            		 check = 0;
			    	            		 PreparedStatement preparedStatement=con.prepareStatement("update Book SET Status=? WHERE BookId="+Integer.parseInt(bookid));
			    	            		 preparedStatement.setString(1,"No");
			    	            		 preparedStatement.executeUpdate();
			    	            		 
			    	            		 PreparedStatement Statement=con.prepareStatement("insert into Issuance(IssueDate,ReturnDate,BookId,MemberId,StaffId)values(?,?,?,?,?)");
			    	                     Statement.setString(1,iss);
			    	                     Statement.setString(2,"Null");
			    	                     Statement.setString(3,bookid);
			    	                     Statement.setString(4,memid);
			    	                     Statement.setString(5,staffId);
			    	                     Statement.executeUpdate();
			    	                     
			    	                     String query3 = "SELECT * from Issuance WHERE BookId="+Integer.parseInt(bookid)+" AND MemberId="+Integer.parseInt(memid);
					    	             Statement stmt3 = con.createStatement();
					    	             ResultSet res3 = stmt3.executeQuery(query3);
			    	                     while(res3.next()) {
			    	                    	 if((res3.getString(4).equals(bookid)) && (res3.getString(5).equals(memid)) && (res3.getString(3).equals("Null"))){
			    	                    		 PreparedStatement settingFine = con.prepareStatement("insert into Fine(Amount,BookId,MemberId,StaffId,IssuanceId)values(?,?,?,?,?)");
					    	     		         settingFine.setString(1, "0");
					    	     		         settingFine.setString(2, bookid);
					    	     		         settingFine.setString(3, memid);
					    	     		         settingFine.setString(4, staffId);
					    	     		         settingFine.setString(5, res3.getString(1));
					    	     		         settingFine.executeUpdate();
					    	     		         JOptionPane.showMessageDialog(this,"Book Issued");
						    	            	 dispose();
							                     new staffUI(staffId,n);
			    	                    	 }
			    	                    	 
			    	                     }
			    	     		        
				    	             }
			    	            	 else {
				    	     		     JOptionPane.showMessageDialog(this,"Book is not Available","Not found",JOptionPane.WARNING_MESSAGE);
					            		 break;
					            	 }
			    	             } 
			            	 }
			            	 else {
		    	     		     JOptionPane.showMessageDialog(this,"No Book Found","Not found",JOptionPane.WARNING_MESSAGE);
			            		 System.out.println("Book Not Found!");
			            	 }
			             }
		                System.out.println("Book Issued!");
	            	}
                	else {
    		  			JOptionPane.showMessageDialog(this,"Invalid Book ID or Member ID!","Invalid",JOptionPane.WARNING_MESSAGE);
    		  		}
	            	if(count==1) {
	            		JOptionPane.showMessageDialog(this,"Book is not available at this time","Not found",JOptionPane.WARNING_MESSAGE);
	            	}
            	}
            }catch(Exception t){
                System.out.println("Connection Error");
            }
        }
        if(btn=="Back") {
        	dispose();
        	new staffUI(staffId,n);
        }
        if(btn =="Logout"){
            dispose();
			new home();
        }
    }
    public static void main(String[] args) {
//        issueBook obj = new issueBook("","");
    }
}
