package LMS;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addMember extends JFrame implements ActionListener{
    JTextField b_namet, passt, mdate_t, usert;
    JLabel labelc,date_label;
    JPanel pc,date_p;
    String staffId,n;
    addMember(String id, String n){
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
        JLabel tile = new JLabel("Add Member");
        tile.setForeground(Color.BLACK);
        tile.setHorizontalAlignment(SwingConstants.CENTER);
        tile.setFont(new Font("Times New Roman",Font.BOLD,25));
        p3.add(tile);

        JLabel bname = new JLabel("Name");
        bname.setFont(new Font("Times New Roman",Font.PLAIN,20));
        bname.setHorizontalAlignment(SwingConstants.RIGHT); 
        JLabel user = new JLabel("Username");
        user.setFont(new Font("Times New Roman",Font.PLAIN,20));
        user.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel pass = new JLabel("Password");
        pass.setFont(new Font("Times New Roman",Font.PLAIN,20));
        pass.setHorizontalAlignment(SwingConstants.RIGHT);
        b_namet = new JTextField();
        b_namet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        usert = new JTextField();
        usert.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passt = new JTextField();
        passt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        JLabel mdate = new JLabel("Membership Date");
        mdate.setFont(new Font("Times New Roman",Font.PLAIN,20));
        mdate.setHorizontalAlignment(SwingConstants.RIGHT);
        mdate_t = new JTextField();
        mdate_t.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(4,2,10,10));
        p4.setBounds(85,170,550,170);
        p4.add(bname);
        p4.add(b_namet);
        p4.add(user);
        p4.add(usert);
        p4.add(pass);
        p4.add(passt);
        p4.add(mdate);
        p4.add(mdate_t);

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

        JButton addmem = new JButton("Add");
        addmem.setBackground(Color.decode(blue));
        addmem.setForeground(Color.white);
        addmem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        addmem.addActionListener(this);

        JPanel p5 = new JPanel();
        p5.setBounds(465,380,80,40);
        p5.setLayout(new GridLayout());
        p5.add(addmem);

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
        add(date_p);
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
        	new staffUI(staffId,n);
        }                 
        if(btn=="Add"){
        	String name = b_namet.getText();
    	    String user = usert.getText();
    	    String password = passt.getText();
    	    String date = mdate_t.getText();
    	    int count=0;
    	  	if(!isValidname(name)) {
    	  		labelc.setVisible(true);
    	  		count++;
    	  	}
    	  	if(!isValidDate(date)) {
    	  		date_label.setVisible(true);
    	  		count++;
    	  	}
            try{
        	  	if(count==0) {
        	  		String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
                    Connection con  = DriverManager.getConnection(url);
                    String query = "SELECT * from Member";
    	            Statement stmt = con.createStatement();
    	            ResultSet res = stmt.executeQuery(query);
    	            int check=0;
    	            while(res.next()) {
    	            	if(res.getString(3).equals(user)) {
    	            		check++;
    	            	}
    	            }
    	            if(check==0){
    	            	PreparedStatement preparedStatement=con.prepareStatement("insert into Member(Name,Username,Password,joiningDate,Status)values(?,?,?,?,?)");
    	                preparedStatement.setString(1,name);
    	                preparedStatement.setString(2,user);
    	                preparedStatement.setString(3,password);
    	                preparedStatement.setString(4,date);
    	                preparedStatement.setString(5,"Yes");
    	                JOptionPane.showMessageDialog(this,"Member Added");
    		            preparedStatement.executeUpdate();
    	                dispose();
    	                new staffUI(staffId,n);
    	                System.out.println("Member Added!");
    	            }
    	            else {
                		JOptionPane.showMessageDialog(this,"Username already exists!","Invalid username",JOptionPane.WARNING_MESSAGE);
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
        addMember obj = new addMember("","");
    }
}
