package LMS;
import javax.swing.*;
import java.sql.*;

import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.*;

public class memUI extends JFrame implements ActionListener{
	int amount=0;
    String user;
    memUI(String n){
    	user = n;
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
        JLabel name = new JLabel(user);
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

        JLabel details = new JLabel("Details");
        details.setForeground(Color.decode(blue));
        details.setFont(new Font("Times New Roman",Font.BOLD,25));

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
        try{
    	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
            Connection con  = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * from Member");
            int i=1;
            while(res.next()) {
                if(res.getString(3).equals(user)){
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
            if(i==1) {
                JOptionPane.showMessageDialog(this,"No record found for given search","No record",JOptionPane.WARNING_MESSAGE);
            }
    	}catch(Exception t){
            System.out.println("Connection Error");
        }
		String clr = "#eeeeee";
		table.setBackground(Color.decode(clr));
		table.setAutoscrolls(true);

        JPanel p4 = new JPanel();
        p4.setLayout(new GridLayout());
        p4.setBounds(10,80,950,50);
        p4.add(details);

        JPanel p5 = new JPanel();
        p5.setLayout(new GridLayout());
        p5.setBounds(10,130,950,350);
        p5.add(table);
		
		int fine = amount;
		String tot = "Total Fine: " + fine;
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
        
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
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
    }
    public static void main(String[] args) {
//        memUI obj = new memUI("");
    }
}
