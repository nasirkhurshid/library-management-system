package LMS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class addBook extends JFrame implements ActionListener{
    JTextField b_namet, a_namet, pubt, aqst, aqsd_t, rackt;
	JLabel author_label,date_label;
    JPanel author_p,date_p;
    String staffId,n;
    addBook(String id, String n){
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
        JLabel tile = new JLabel("Add Book");
        tile.setForeground(Color.BLACK);
        tile.setHorizontalAlignment(SwingConstants.CENTER);
        tile.setFont(new Font("Times New Roman",Font.BOLD,25));
        p3.add(tile);

        JLabel bname = new JLabel("Book Name");
        bname.setFont(new Font("Times New Roman",Font.PLAIN,20));
        bname.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel aname = new JLabel("Author Name");
        aname.setFont(new Font("Times New Roman",Font.PLAIN,20));
        aname.setHorizontalAlignment(SwingConstants.RIGHT);
        b_namet = new JTextField();
        b_namet.setFont(new Font("Times New Roman",Font.PLAIN,20));
        a_namet = new JTextField();
        a_namet.setFont(new Font("Times New Roman",Font.PLAIN,20));

        JLabel publ = new JLabel("Publication");
        publ.setFont(new Font("Times New Roman",Font.PLAIN,20));
        publ.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel aqs = new JLabel("Acquisition");
        aqs.setFont(new Font("Times New Roman",Font.PLAIN,20));
        aqs.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel aqsd = new JLabel("Acquisition Date");
        aqsd.setFont(new Font("Times New Roman",Font.PLAIN,20));
        aqsd.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel rack = new JLabel("Rack");
        rack.setFont(new Font("Times New Roman",Font.PLAIN,20));
        rack.setHorizontalAlignment(SwingConstants.RIGHT);

        pubt = new JTextField();
        pubt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        aqst = new JTextField();
        aqst.setFont(new Font("Times New Roman",Font.PLAIN,20));
        aqsd_t = new JTextField();
        aqsd_t.setFont(new Font("Times New Roman",Font.PLAIN,20));
        rackt = new JTextField();
        rackt.setFont(new Font("Times New Roman",Font.PLAIN,20));

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(6,2,10,10));
        p4.setBounds(85,170,550,250);
        p4.add(bname);
        p4.add(b_namet);
        p4.add(aname);
        p4.add(a_namet);
        p4.add(publ);
        p4.add(pubt);
        p4.add(aqs);
        p4.add(aqst);
        p4.add(aqsd);
        p4.add(aqsd_t);
        p4.add(rack);
        p4.add(rackt);
        
        author_p = new JPanel();
        author_p.setLayout(new GridLayout());
        author_p.setBounds(645,215,250,30);
        
        author_label = new JLabel("Enter valid name");
        author_label.setForeground(Color.decode(blue));
        author_label.setFont(new Font("Times New Roman",Font.PLAIN,15));
        author_p.add(author_label);
    
        date_p = new JPanel();
        date_p.setLayout(new GridLayout());
        date_p.setBounds(645,342,250,30);
        
        date_label = new JLabel("Enter Date in DD/MM/YYYY Format");
        date_label.setForeground(Color.decode(blue));
        date_label.setFont(new Font("Times New Roman",Font.PLAIN,15));
        date_p.add(date_label);
        
        
        JButton add = new JButton("Add");
        add.setBackground(Color.decode(blue));
        add.setForeground(Color.white);
        add.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add.addActionListener(this);

        JPanel p5 = new JPanel();
        p5.setBounds(465,440,80,40);
        p5.setLayout(new GridLayout());
        p5.add(add);

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
        add(author_p);
        add(date_p);
        add(p5);
        author_label.setVisible(false);
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
        	String book_name = b_namet.getText();
    	    String author = a_namet.getText();
    	    String publisher = pubt.getText();
    	    String acq = aqst.getText();
    	    String acqDate = aqsd_t.getText();
    	    String rack = rackt.getText();
    	    int count=0;
    	  	if(!isValidname(author)) {
    	  		author_label.setVisible(true);
    	  		count++;
    	  	}
    	  	if(!isValidDate(acqDate)) {
    	  		date_label.setVisible(true);
    	  		count++;
    	  	}
            try{
        	  	if(count==0){
	        	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
	                Connection con  = DriverManager.getConnection(url);
	                PreparedStatement preparedStatement=con.prepareStatement("insert into Book(Name,Author,Publisher,Acquisition,AcqDate,Rack,Status)values(?,?,?,?,?,?,?)");
	                preparedStatement.setString(1,book_name);
	                preparedStatement.setString(2,author);
	                preparedStatement.setString(3,publisher);
	                preparedStatement.setString(4,acq);
	                preparedStatement.setString(5,acqDate);
	                preparedStatement.setString(6,rack);
	                preparedStatement.setString(7,"Yes");
	                preparedStatement.executeUpdate();
	                JOptionPane.showMessageDialog(this,"Book Added");
		            dispose();
	                new staffUI(staffId,n);
	                System.out.println("Book Added!");
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
    public static void main(String[] args){
        addBook obj = new addBook("1","ali");
    }
}
