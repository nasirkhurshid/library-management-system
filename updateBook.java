package LMS;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class updateBook extends JFrame implements ActionListener{
    JTextField bIDt, bnamet, anamet, bpub_t, baqs_t, aqsd_t, statust, rackt;
    JLabel labelc,author_label,date_label;
    JPanel pc,author_p,date_p;
    String staffId,n;
    updateBook(String id,String n){
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
        JLabel tile = new JLabel("Update Book");
        tile.setForeground(Color.BLACK);
        tile.setHorizontalAlignment(SwingConstants.CENTER);
        tile.setFont(new Font("Times New Roman",Font.BOLD,25));
        p3.add(tile);

        JLabel bID = new JLabel("Book ID");
        bID.setFont(new Font("Times New Roman",Font.PLAIN,20));
        bID.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel bname = new JLabel("Book Name");
        bname.setFont(new Font("Times New Roman",Font.PLAIN,20));
        bname.setHorizontalAlignment(SwingConstants.RIGHT);
        bIDt = new JTextField();
        bIDt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        bnamet = new JTextField();
        bnamet.setFont(new Font("Times New Roman",Font.PLAIN,20));

        JLabel aname = new JLabel("Author Name");
        aname.setFont(new Font("Times New Roman",Font.PLAIN,20));
        aname.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel bpub = new JLabel("Publication");
        bpub.setFont(new Font("Times New Roman",Font.PLAIN,20));
        bpub.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel baqs = new JLabel("Aquisition ");
        baqs.setFont(new Font("Times New Roman",Font.PLAIN,20));
        baqs.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel aqsd = new JLabel("Aquisition Date");
        aqsd.setFont(new Font("Times New Roman",Font.PLAIN,20));
        aqsd.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel status = new JLabel("Status");
        status.setFont(new Font("Times New Roman",Font.PLAIN,20));
        status.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel rack = new JLabel("Rack");
        rack.setFont(new Font("Times New Roman",Font.PLAIN,20));
        rack.setHorizontalAlignment(SwingConstants.RIGHT);

        anamet = new JTextField();
        anamet.setFont(new Font("Times New Roman",Font.PLAIN,20));
        bpub_t = new JTextField();
        bpub_t.setFont(new Font("Times New Roman",Font.PLAIN,20));
        baqs_t = new JTextField();
        baqs_t.setFont(new Font("Times New Roman",Font.PLAIN,20));
        aqsd_t = new JTextField();
        aqsd_t.setFont(new Font("Times New Roman",Font.PLAIN,20));
        statust = new JTextField();
        statust.setFont(new Font("Times New Roman",Font.PLAIN,20));
        rackt = new JTextField();
        rackt.setFont(new Font("Times New Roman",Font.PLAIN,20));

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(8,2,10,10));
        p4.setBounds(85,170,550,320);
        p4.add(bID);
        p4.add(bIDt);
        p4.add(bname);
        p4.add(bnamet);
        p4.add(aname);
        p4.add(anamet);
        p4.add(bpub);
        p4.add(bpub_t);
        p4.add(baqs);
        p4.add(baqs_t);
        p4.add(aqsd);
        p4.add(aqsd_t);
        p4.add(status);
        p4.add(statust);
        p4.add(rack);
        p4.add(rackt);

        pc = new JPanel();
        pc.setLayout(new GridLayout());
        pc.setBounds(645,170,250,30);
        
        labelc = new JLabel("Enter valid name");
        labelc.setForeground(Color.decode(blue));
        labelc.setFont(new Font("Times New Roman",Font.PLAIN,15));
        pc.add(labelc);
        
        author_p = new JPanel();
        author_p.setLayout(new GridLayout());
        author_p.setBounds(645,252,250,30);
        
        author_label = new JLabel("Enter valid name");
        author_label.setForeground(Color.decode(blue));
        author_label.setFont(new Font("Times New Roman",Font.PLAIN,15));
        author_p.add(author_label);
        
        date_p = new JPanel();
        date_p.setLayout(new GridLayout());
        date_p.setBounds(645,375,250,30);
        
        date_label = new JLabel("Enter Date in DD/MM/YYYY Format");
        date_label.setForeground(Color.decode(blue));
        date_label.setFont(new Font("Times New Roman",Font.PLAIN,15));
        date_p.add(date_label);
        
        JButton addstf = new JButton("Update");
        addstf.setBackground(Color.decode(blue));
        addstf.setForeground(Color.white);
        addstf.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addstf.addActionListener(this);
        JPanel p5 = new JPanel();
        p5.setBounds(450,500,90,40);
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
        add(p3);
        add(pc);
        add(author_p);
        add(date_p);
        add(p4);
        add(p5);
        labelc.setVisible(false);
        author_label.setVisible(false);
        date_label.setVisible(false);
        
        bIDt.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent arg0) {
            	String getid = bIDt.getText();
            	String part1,part2;
            	if(!getid.isBlank()) {
            		part1 = getid.substring(0,1);
                	part2 = getid.substring(1);
    	                try {
    	                	if(part1.equals("B")) {
    	              	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
    	                    Connection con  = DriverManager.getConnection(url);
    	                    String query2 = "SELECT * from Book WHERE BookId="+Integer.parseInt(part2);
    	                    Statement stmt2 = con.createStatement();
    	                    ResultSet res2 = stmt2.executeQuery(query2);
    	                    if(res2.next()) {
    	                    	bnamet.setText(res2.getString(2));
    	                  	    anamet.setText(res2.getString(3));
    	                  	    bpub_t.setText(res2.getString(4));
    	                  	    baqs_t.setText(res2.getString(5));
    	                  	    aqsd_t.setText(res2.getString(6));
    	                  	    statust.setText(res2.getString(8));
    	                  	    rackt.setText(res2.getString(7));
    	                    }
    	                    else {
    	                    	bnamet.setText("");
    	                  	    anamet.setText("");
    	                  	    bpub_t.setText("");
    	                  	    baqs_t.setText("");
    	                  	    aqsd_t.setText("");
    	                  	    statust.setText("");
    	                  	    rackt.setText("");
    	                    }
    	                  }
    	                }catch(Exception a) {
    	                	bnamet.setText("");
	                  	    anamet.setText("");
	                  	    bpub_t.setText("");
	                  	    baqs_t.setText("");
	                  	    aqsd_t.setText("");
	                  	    statust.setText("");
	                  	    rackt.setText("");
    	                }
            	}else {
            		bnamet.setText("");
              	    anamet.setText("");
              	    bpub_t.setText("");
              	    baqs_t.setText("");
              	    aqsd_t.setText("");
              	    statust.setText("");
              	    rackt.setText("");
                }
            }
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			} 
        });
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
        String book_name = bnamet.getText();
        String author=anamet.getText();
        String date = aqsd_t.getText();
        if(btn=="Back") {
        	dispose();
        	new staffUI(staffId,n);
        }
        if(btn=="Update")
        {
	       	int count=0;
	      	if(!isValidname(book_name)) {
	   	  		labelc.setVisible(true);
	   	  		count++;
	   	  	}
	      	if(!isValidname(author)) {
    	  		author_label.setVisible(true);
    	  		count++;
    	  	}
	   	  	if(!isValidDate(date)) {
	  	  		date_label.setVisible(true);
	   	  		count++;
	    	 }
	    	
		  	if(count==0){
		  		String getid = bIDt.getText();
 		  		String part1 = getid.substring(0,1);
 		  		String bookid = getid.substring(1);
        	    String bookname = bnamet.getText();
        	    String authorname = anamet.getText();
        	    String publish = bpub_t.getText();
        	    String acquisition = baqs_t.getText();
        	    String acquisitionDate =  aqsd_t.getText();
        	    String status = statust.getText();
        	    String rack = rackt.getText();
		  		try{
		  			if(part1.equals("B")) {
		        	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
		                Connection con  = DriverManager.getConnection(url);
		                String query = "SELECT * from Book WHERE BookId="+Integer.parseInt(bookid);
				        Statement stmt = con.createStatement();
				        ResultSet res = stmt.executeQuery(query);
				        while(res.next()){
				        	if(res.getString(1).equals(bookid)) {
				        		PreparedStatement preparedStatement=con.prepareStatement("update Book SET Name=?,Author=?,Publisher=?,Acquisition=?,AcqDate=?,Rack=?,Status=? WHERE BookId="+Integer.parseInt(bookid));		            	 
				        		preparedStatement.setString(1,bookname);
				                preparedStatement.setString(2,authorname);
				                preparedStatement.setString(3,publish);
				                preparedStatement.setString(4,acquisition);
				                preparedStatement.setString(5,acquisitionDate);
				                preparedStatement.setString(6,rack);
				                preparedStatement.setString(7,status);
				                preparedStatement.executeUpdate();
				        	}
				        }
		                JOptionPane.showMessageDialog(this,"Book Updated");
				        dispose();
				        new staffUI(staffId,n);
		  			}
		  			else {
		  				JOptionPane.showMessageDialog(this,"Invalid ID!","Invalid",JOptionPane.WARNING_MESSAGE);
		  			}
	            }catch(Exception t){
	                System.out.println("Connection Error");
	            }
		  	}
        }
        if(btn =="Logout"){
            dispose();
			new home();
        }
    }
    public static void main(String[] args) {
//        updateBook obj = new updateBook("","");
    }
}
