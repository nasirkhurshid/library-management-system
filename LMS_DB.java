package LMS;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class LMS_DB {
	public static void main(String[] args)throws SQLException {
		String url ="jdbc:ucanaccess://C://Users//LMS_DB.accdb";
        Connection con  = DriverManager.getConnection(url);
        String query = "SELECT * from Staff";
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);
        while(res.next()){
            System.out.println(res.getString(1)+"\t\t\t"+res.getString(2)+"\t\t\t"+res.getString(3)+"\t\t\t"+res.getString(4)+"\t\t\t"+res.getString(5)+"\t\t\t"+res.getString(6));
        }
	}
}
