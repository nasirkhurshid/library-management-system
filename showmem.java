package LMS;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.*;
//import java.beans.Statement;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class showmem extends JFrame implements ActionListener{
	String staffId,n;
    showmem(String name, String idd,String n){
        String blue = "#3566b5";
        staffId = idd;
        this.n=n;
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
        p1.setBounds(35,10,900,50);
        JLabel label = new JLabel("DRSM LIBRARY MANAGEMENT SYSTEM");
        label.setForeground(Color.decode(blue));
        label.setFont(new Font("Times New Roman",Font.BOLD,32));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(label);

        JLabel details = new JLabel("Result(s)");
        details.setForeground(Color.decode(blue));
        details.setFont(new Font("Times New Roman",Font.BOLD,25));

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.setBounds(10,80,950,50);
        p4.add(details);
       


        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model){
            public boolean isCellEditable(int row, int column){
                return false;
            }
            public Component prepareRenderer(TableCellRenderer renderer, 
            int row, int column) 
            {
               Component c = super.prepareRenderer(renderer, row, column);
               Color color1 = new Color(53, 102, 181);
               Color color2 = Color.WHITE;
               Color color3 = new Color(220,220,220);
               if(!c.getBackground().equals(getSelectionBackground())) {
                  Color coleur;
                  coleur = (row == 0 ? color1 : color2);
                  if(row % 2 == 0 && row != 0) {
                	  coleur = color3;
                  }
                  c.setBackground(coleur);
                  if(coleur==color1) {
                      c.setForeground(color2);  
                  }
                  else
                	  c.setForeground(Color.black);
                  coleur = null;
               }
               return c;
            }
        };
        
        model.addColumn("Book ID");
        model.addColumn("Book Name");	
        model.addColumn("Issue Date");
        model.addColumn("Return Date");
        model.addColumn("Staff");
        model.addColumn("Fine");
    	model.insertRow(0, new Object[] {"Book ID","Book Name","Issue Date","Return Date","Staff","Fine"});
    	
    	String name_ = "";
    	String id_ = "";
    	int amount=0;
        try{
    	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
            Connection con  = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * from Member");
            int i=1,m=0;
            while(res.next()) {
                if(res.getString(3).toLowerCase().equals(name.toLowerCase())){
                	m++;
                	id_ = res.getString(1);
                	name_ = res.getString(2);
                    Statement stmt2 = con.createStatement();
                    ResultSet res2 = stmt2.executeQuery("SELECT * from Issuance WHERE MemberId="+Integer.parseInt(res.getString(1)));
                    while(res2.next()){
                        if(res2.getString(5).equals(res.getString(1))){
                            Statement stmt3 = con.createStatement();
                            ResultSet res3 = stmt3.executeQuery("SELECT * from Fine WHERE BookId="+Integer.parseInt(res2.getString(4))+" AND IssuanceId="+Integer.parseInt(res2.getString(1)));
                            while(res3.next()){
                                if((res3.getString(3).equals(res2.getString(4)))&&(res3.getString(6).equals(res2.getString(1)))){
                                    Statement stmt4 = con.createStatement();
                                    ResultSet res4 = stmt4.executeQuery("SELECT * from Book WHERE BookId="+Integer.parseInt(res2.getString(4)));
                                    while(res4.next()){
                                        if(res4.getString(1).equals(res2.getString(4))){
                                            Statement stmt5 = con.createStatement();
                                            ResultSet res5 = stmt5.executeQuery("SELECT * from Staff WHERE StaffId="+Integer.parseInt(res2.getString(6)));
                                            while(res5.next()){
                                                if(res5.getString(1).equals(res2.getString(6))){
                                                    String bid = "B"+res4.getString(1);
                                                    String bname = res4.getString(2);
                                                    String bissue = res2.getString(2);
                                                    String breturn = res2.getString(3);
                                                    String sname = res5.getString(2);
                                                    String bfine = res3.getString(2);
                                                    amount = amount + Integer.parseInt(res3.getString(2));
                                                    model.insertRow(i, new Object[] {bid,bname,bissue,breturn,sname,bfine});
                                                    i++;
                                                }
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        } 
                    }
                }
            }
            if(m==0) {
                JOptionPane.showMessageDialog(this,"No record found for given search","No record",JOptionPane.WARNING_MESSAGE);
            }
    	}catch(Exception t){
            System.out.println("Connection Error");
        }
		String clr = "#eeeeee";
		table.setBackground(Color.decode(clr));
		table.setAutoscrolls(true);

		JLabel nLabel = new JLabel("Name: " + name_);
        nLabel.setForeground(Color.decode(blue));
        nLabel.setFont(new Font("Times New Roman",Font.BOLD,20));

		JLabel d = new JLabel("ID: M"+id_);
        d.setForeground(Color.decode(blue));
        d.setFont(new Font("Times New Roman",Font.BOLD,20));
		
		JPanel p51 = new JPanel();
        p51.setLayout(new GridLayout(1,2));
        p51.setBounds(10,140,950,30);
        p51.add(nLabel);
        p51.add(d);
        
		JPanel p5 = new JPanel();
        p5.setLayout(new GridLayout());
        p5.setBounds(10,170,950,350);
        p5.add(table);
        
		String tot = "Total Fine: " + amount;
		JLabel total = new JLabel(tot);
        total.setForeground(Color.decode(blue));
        total.setFont(new Font("Times New Roman",Font.BOLD,15));
		
		
		JPanel p6 = new JPanel();
		p6.setLayout(new GridLayout());
        p6.setBounds(800,500,150,50);
        p6.add(total);
        
        
        setTitle("DRSM LMS");
        setSize(1000,600);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(back);
        add(p1);
        add(p4);
        add(p5);
        add(p51);
        add(p6);
    }

    @Override
	public void actionPerformed(ActionEvent e){
        JButton b = (JButton)e.getSource();
        String btn = b.getText();
        if(btn =="Logout"){
            dispose();
			new home();
        }
        if(btn =="Back"){
            dispose();
			new searchMember(staffId,n);
        }
    }
    public static void main(String[] args) {
//        showmem obj = new showmem("bilal001","1");
    }
}
