package LMS;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class updateStaff extends JFrame implements ActionListener{
    JTextField idt, namet,passt,statust,shiftt;
    JLabel labelc;
    JPanel pc;
    String n;
    updateStaff(String n){
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
        JLabel tile = new JLabel("Update Staff");
        tile.setForeground(Color.BLACK);
        tile.setHorizontalAlignment(SwingConstants.CENTER);
        tile.setFont(new Font("Times New Roman",Font.BOLD,25));
        p3.add(tile);

        JLabel id = new JLabel("ID");
        id.setFont(new Font("Times New Roman",Font.PLAIN,20));
        id.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel name = new JLabel("Name");
        name.setFont(new Font("Times New Roman",Font.PLAIN,20));
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        idt = new JTextField();
        idt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        namet = new JTextField();
        namet.setFont(new Font("Times New Roman",Font.PLAIN,20));

        JLabel pass = new JLabel("Password");
        pass.setFont(new Font("Times New Roman",Font.PLAIN,20));
        pass.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel status = new JLabel("Status");
        status.setFont(new Font("Times New Roman",Font.PLAIN,20));
        status.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel shift = new JLabel("Shift");
        shift.setFont(new Font("Times New Roman",Font.PLAIN,20));
        shift.setHorizontalAlignment(SwingConstants.RIGHT);
        passt = new JTextField();
        passt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        statust = new JTextField();
        statust.setFont(new Font("Times New Roman",Font.PLAIN,20));
        shiftt = new JTextField();
        shiftt.setFont(new Font("Times New Roman",Font.PLAIN,20));

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(5,2,10,10));
        p4.setBounds(85,170,550,200);
        p4.add(id);
        p4.add(idt);
        p4.add(name);
        p4.add(namet);
        p4.add(pass);
        p4.add(passt);
        p4.add(status);
        p4.add(statust);
        p4.add(shift);
        p4.add(shiftt);
        
        pc = new JPanel();
        pc.setLayout(new GridLayout());
        pc.setBounds(645,215,250,30);
        
        labelc = new JLabel("Enter valid name");
        labelc.setForeground(Color.decode(blue));
        labelc.setFont(new Font("Times New Roman",Font.PLAIN,15));
        pc.add(labelc);
        
        
        JButton update = new JButton("Update");
        update.setBackground(Color.decode(blue));
        update.setForeground(Color.white);
        update.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        update.addActionListener(this);
        JPanel p5 = new JPanel();
        p5.setBounds(450,400,90,40);
        p5.setLayout(new GridLayout());
        p5.add(update);

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
        add(pc);
        add(p5);
        labelc.setVisible(false);
               
        idt.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent arg0) {
            	String getid = idt.getText();
            	String part1,part2;
            	if(!getid.isBlank()) {
            		part1 = getid.substring(0,1);
                	part2 = getid.substring(1);
    	                try {
    	                	if(part1.equals("S")) {
    	                		String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
    	                        Connection con  = DriverManager.getConnection(url);
    	                        String query2 = "SELECT * from Staff WHERE StaffId="+Integer.parseInt(part2);
    	                        Statement stmt2 = con.createStatement();
    	                        ResultSet res2 = stmt2.executeQuery(query2);
    	                        if(res2.next()) {
    	                      	  namet.setText(res2.getString(2));
    	                      	  passt.setText(res2.getString(4));
    	                      	  statust.setText(res2.getString(5));
    	                      	  shiftt.setText(res2.getString(6));
    	                        }
    	                        else {
    	                        	namet.setText("");
    	                        	passt.setText("");
    	                        	statust.setText("");
    	                        	shiftt.setText("");
    	                        }
    	                  }
    	                }catch(Exception a) {
    	                	namet.setText("");
                        	passt.setText("");
                        	statust.setText("");
                        	shiftt.setText("");
    	                }
            	}else {
            		namet.setText("");
                	passt.setText("");
                	statust.setText("");
                	shiftt.setText("");
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
    
    public static boolean isValidname(String name)
    {
        String regex = "^[a-zA-Z\s]+$";   
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
        String _name = namet.getText();
	    int count=0;
        if(btn=="Back") {
        	dispose();
        	new adminUI(n);
        }
        if(btn=="Update")
        {
		  	if(!isValidname(_name)) {
		  		labelc.setVisible(true);
		  		count++;
		  	}
        }
        
	  	if(count==0){
	  			String getid = idt.getText();
		  		String part1 = getid.substring(0,1);
		  		String staffId = getid.substring(1);
        	    String staffName = namet.getText();
        	    String staffPass = passt.getText();
        	    String staffStatus = statust.getText();
        	    String staffShift = shiftt.getText();
	  		try{
	  			if(part1.equals("S")) {
	        	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
	                Connection con  = DriverManager.getConnection(url);
	                String query = "SELECT * from Staff WHERE StaffId="+Integer.parseInt(staffId);
			        Statement stmt = con.createStatement();
			        ResultSet res = stmt.executeQuery(query);
			        while(res.next()){
			        	if(res.getString(1).equals(staffId)) {
			            	System.out.println("id "+res.getString(1));
			        		PreparedStatement preparedStatement=con.prepareStatement("update Staff SET Name=?,Password=?,Status=?,Shift=? WHERE StaffId="+Integer.parseInt(staffId));		            	 
			        		preparedStatement.setString(1,staffName);
			                preparedStatement.setString(2,staffPass);
			                preparedStatement.setString(3,staffStatus);
			                preparedStatement.setString(4,staffShift);
			                preparedStatement.executeUpdate();
			        	}
			        }
	                JOptionPane.showMessageDialog(this,"Staff Updated");
			        dispose();
			        new adminUI(n);
	  			}
	  			else {
	  				JOptionPane.showMessageDialog(this,"Invalid ID!","Invalid",JOptionPane.WARNING_MESSAGE);
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
        //updateStaff obj = new updateStaff("");
    }
}
