package LMS;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class showbook extends JFrame implements ActionListener{
    showbook(String name){
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
        
        model.addColumn("Name");
        model.addColumn("Author");	
        model.addColumn("Publication");
        model.addColumn("Rack");
        model.addColumn("Available");
    	model.insertRow(0, new Object[] {"Name","Author","Publication","Rack","Available"});
        try{
    	    String url = "jdbc:ucanaccess://C://Users//LMS_DB.accdb";
            Connection con  = DriverManager.getConnection(url);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * from Book");
            int i=1,m=0;
            while(res.next()) {
            	m++;
            	String result = res.getString(2).toLowerCase();
            	if(result.contains(name.toLowerCase())) {
            		String name_ = res.getString(2);
            		String author = res.getString(3);
            		String pub = res.getString(4);
            		String rack = res.getString(7);
            		String ava = res.getString(8);
            		model.insertRow(i, new Object[] {name_,author,pub,rack,ava});
            		i++;
            	}
            }
            if(i==1) {
//                JOptionPane.showMessageDialog(this,"No record found for given search","No record",JOptionPane.WARNING_MESSAGE);
//                dispose();
//                new home();
            }
            if(m==0) {
//                JOptionPane.showMessageDialog(this,"No record found for given search","No record",JOptionPane.WARNING_MESSAGE);
            	dispose();
              new home();
            }
    	}catch(Exception t){
            System.out.println("Connection Error");
        }
        
        table.setAutoscrolls(true);
        String clr = "#eeeeee";
		table.setBackground(Color.decode(clr));
        JPanel p5 = new JPanel();
        p5.setLayout(new GridLayout());
        p5.setBounds(10,140,950,400);
        p5.add(table);
        
        setTitle("DRSM LMS");
        setSize(1000,600);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(back);
        add(p1);
        add(p4);
        add(p5);
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
			new home();
        }
    }
    public static void main(String[] args) {
//        showbook obj = new showbook("ala");
    }
}
