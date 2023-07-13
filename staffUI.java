package LMS;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class staffUI extends JFrame implements ActionListener{
	String staffId,n;
    staffUI(String stfid, String n){
    	staffId = stfid;
    	this.n=n;
        String blue = "#3566b5";

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout());
        p1.setBounds(5,10,700,50);
        JLabel label = new JLabel("DRSM LIBRARY MANAGEMENT SYSTEM");
        label.setForeground(Color.decode(blue));
        label.setFont(new Font("Times New Roman",Font.BOLD,32));
        p1.add(label);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout());
        p2.setBounds(650,12,220,50);
        JLabel name = new JLabel(n);
        name.setForeground(Color.BLACK);
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        name.setFont(new Font("Calibri",Font.BOLD,20));
        p2.add(name);

        JButton logout = new JButton("Logout");
        logout.setBackground(Color.decode(blue));
        logout.setForeground(Color.white);
        logout.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        logout.addActionListener(this);

        JPanel p3 = new JPanel();
        p3.setBounds(880,18,80,30);
        p3.setLayout(new GridLayout());
        p3.add(logout);

        JLabel cat1 = new JLabel("BOOKS");
        cat1.setForeground(Color.decode(blue));
        cat1.setFont(new Font("Times New Roman",Font.BOLD,25));
        cat1.setHorizontalAlignment(SwingConstants.CENTER);
        cat1.setForeground(Color.decode(blue));

        JButton add = new JButton("Add Book");
        add.setBackground(Color.decode(blue));
        add.setForeground(Color.white);
        add.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add.addActionListener(this);

        JButton update = new JButton("Update Book");
        update.setBackground(Color.decode(blue));
        update.setForeground(Color.white);
        update.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        update.addActionListener(this);

        JButton issue = new JButton("Issue Book");
        issue.setBackground(Color.decode(blue));
        issue.setForeground(Color.white);
        issue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        issue.addActionListener(this);

        JButton ret = new JButton("Return Book");
        ret.setBackground(Color.decode(blue));
        ret.setForeground(Color.white);
        ret.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        ret.addActionListener(this);
        
        JButton searchBook = new JButton("Search Book");
        searchBook.setBackground(Color.decode(blue));
        searchBook.setForeground(Color.white);
        searchBook.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        searchBook.addActionListener(this);

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout(6,1,0,10));
        p4.setBounds(80,140,300,240);
        p4.add(cat1);
        p4.add(add);
        p4.add(update);
        p4.add(issue);
        p4.add(ret);
        p4.add(searchBook);


        JLabel cat2 = new JLabel("MEMBER");
        cat2.setForeground(Color.decode(blue));
        cat2.setFont(new Font("Times New Roman",Font.BOLD,25));
        cat2.setHorizontalAlignment(SwingConstants.CENTER);
        cat2.setForeground(Color.decode(blue));

        JButton add2 = new JButton("Add Member");
        add2.setBackground(Color.decode(blue));
        add2.setForeground(Color.white);
        add2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add2.addActionListener(this);

        JButton update2 = new JButton("Update Member");
        update2.setBackground(Color.decode(blue));
        update2.setForeground(Color.white);
        update2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        update2.addActionListener(this);

        JButton search = new JButton("Search Member");
        search.setBackground(Color.decode(blue));
        search.setForeground(Color.white);
        search.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        search.addActionListener(this);

        JPanel p5 = new JPanel();
        p5.setLayout(new GridLayout(5,1,0,10));
        p5.setBounds(580,140,300,200);
        p5.add(cat2);
        p5.add(add2);
        p5.add(update2);
        p5.add(search);



        setTitle("DRSM LMS");
        setSize(1000,600);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        if(btn =="Add Book"){
            dispose();
			new addBook(staffId,n);
        }
        if(btn =="Update Book"){
            dispose();
			new updateBook(staffId,n);
        }
        if(btn =="Issue Book"){
            dispose();
			new issueBook(staffId,n);
        }
        if(btn =="Return Book"){
            dispose();
			new returnBook(staffId,n);
        }
        if(btn =="Search Book"){
            dispose();
			new searchBook(staffId,n);
        }
        if(btn =="Add Member"){
            dispose();
			new addMember(staffId,n);
        }
        if(btn =="Update Member"){
            dispose();
			new updateMember(staffId,n);
        }
        if(btn =="Search Member"){
            dispose();
			new searchMember(staffId,n);
        }
        if(btn =="Logout"){
            dispose();
			new home();
        }
    }
    public static void main(String[] args) {
//        staffUI obj = new staffUI("","");
    }
}
