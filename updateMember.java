package LMS;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class updateMember extends JFrame implements ActionListener{
    JTextField mIDt, mnamet,mpas_t,statust, joint;
    JLabel labelc;
    JPanel pc;
    String staffId,n;
    updateMember(String id,String n){
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
        JLabel tile = new JLabel("Update Member");
        tile.setForeground(Color.BLACK);
        tile.setHorizontalAlignment(SwingConstants.CENTER);
        tile.setFont(new Font("Times New Roman",Font.BOLD,25));
        p3.add(tile);

        JLabel mID = new JLabel(" ID");
        mID.setFont(new Font("Times New Roman",Font.PLAIN,20));
        mID.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel mname = new JLabel(" Name");
        mname.setFont(new Font("Times New Roman",Font.PLAIN,20));
        mname.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel mpas = new JLabel("Password");
        mpas.setFont(new Font("Times New Roman",Font.PLAIN,20));
        mpas.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel join = new JLabel("Joining Date");
        join.setFont(new Font("Times New Roman",Font.PLAIN,20));
        join.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel status = new JLabel("Status");
        status.setFont(new Font("Times New Roman",Font.PLAIN,20));
        status.setHorizontalAlignment(SwingConstants.RIGHT);
        
        mIDt = new JTextField();
        mIDt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mnamet = new JTextField();
        mnamet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        mpas_t = new JTextField();
        mpas_t.setFont(new Font("Times New Roman",Font.PLAIN,20));
        joint = new JTextField();
        joint.setFont(new Font("Times New Roman",Font.PLAIN,20));
        statust = new JTextField();
        statust.setFont(new Font("Times New Roman",Font.PLAIN,20));
        

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(5,2,10,10));
        p4.setBounds(85,160,550,200);
        p4.add(mID);
        p4.add(mIDt);
        p4.add(mname);
        p4.add(mnamet);
        p4.add(mpas);
        p4.add(mpas_t);
        p4.add(join);
        p4.add(joint);
        p4.add(status);
        p4.add(statust);
       
        pc = new JPanel();
        pc.setLayout(new GridLayout());
        pc.setBounds(645,215,250,30);
        
        labelc = new JLabel("Enter valid name");
        labelc.setForeground(Color.decode(blue));
        labelc.setFont(new Font("Times New Roman",Font.PLAIN,15));
        pc.add(labelc);
       

        JButton addstf = new JButton("Update");
        addstf.setBackground(Color.decode(blue));
        addstf.setForeground(Color.white);
        addstf.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addstf.addActionListener(this);
        JPanel p5 = new JPanel();
        p5.setBounds(450,390,90,40);
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
        add(p4);
        add(pc);
        add(p5);
        labelc.setVisible(false);
        
        mIDt.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent arg0) {
            	String getid = mIDt.getText();
            	String part1,part2;
            	if(!getid.isBlank()) {
            		part1 = getid.substring(0,1);
                	part2 = getid.substring(1);
                	System.out.println(part2);
    	                try {
    	                	if(part1.equals("M")) {
    	              	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
    	                    Connection con  = DriverManager.getConnection(url);
    	                    String query2 = "SELECT * from Member WHERE MemberId="+Integer.parseInt(part2);
    	                    Statement stmt2 = con.createStatement();
    	                    ResultSet res2 = stmt2.executeQuery(query2);
    	                    if(res2.next()) {
    	                  	  mnamet.setText(res2.getString(2));
    	                  	  mpas_t.setText(res2.getString(4));
    	                  	  joint.setText(res2.getString(5));
    	                  	  statust.setText(res2.getString(6));
    	                    }
    	                    else {
    	                    	mnamet.setText("");
    	                    	mpas_t.setText("");
    	                    	joint.setText("");
    	                    	statust.setText("");
    	                    }
    	                  }
    	                }catch(Exception a) {
    	              	  mnamet.setText("");
    	              	  mpas_t.setText("");
    	              	  joint.setText("");
    	              	  statust.setText("");
    	                }
            	}else {
                	mnamet.setText("");
                	mpas_t.setText("");
                	joint.setText("");
                	statust.setText("");
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
        String _name=mnamet.getText();
        int count=0;
        if(btn=="Back") {
        	dispose();
        	new staffUI(staffId,n);
        }
        if(btn=="Update")
        {
		  	if(!isValidname(_name)) {
		  		labelc.setVisible(true);
		  		count++;
		  	}
 		  	if(count==0){
 		  		String getid = mIDt.getText();
 		  		String part1 = getid.substring(0,1);
 		  		String memId = getid.substring(1);
        	    String memName = mnamet.getText();
        	    String memPass = mpas_t.getText();
        	    String memJoin = joint.getText();
        	    String memStatus = statust.getText();
 		  		try{
 		  			if(part1.equals("M")) {
	 	        	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
	 	                Connection con  = DriverManager.getConnection(url);
	 	                String query = "SELECT * from Member WHERE MemberId="+Integer.parseInt(memId);
	 			        Statement stmt = con.createStatement();
	 			        ResultSet res = stmt.executeQuery(query);
	 			        while(res.next()){
	 			        	if(res.getString(1).equals(memId)) {
	 			        		PreparedStatement preparedStatement=con.prepareStatement("update Member SET Name=?,Password=?,joiningDate=?,Status=? WHERE MemberId="+Integer.parseInt(memId));		            	 
	 			        		preparedStatement.setString(1,memName);
	 			                preparedStatement.setString(2,memPass);
	 			                preparedStatement.setString(3,memJoin);
	 			                preparedStatement.setString(4,memStatus);
	 			                preparedStatement.executeUpdate();
	 			        	}
	 			        }
		                JOptionPane.showMessageDialog(this,"Member Updated");
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
//        updateMember obj = new updateMember("","");
    }
}
