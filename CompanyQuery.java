import java.sql.*;

public class CompanyQuery {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:postgresql://localhost:5432/Company";
		String user = "postgres";
		String pw = "xxxxxxxxx";
		try {
			//1. get connection
			Connection myconn = DriverManager.getConnection(jdbcUrl, user, pw);
			//2. create a statement
			Statement mystmt = myconn.createStatement();
			//1. (SQL) Query the database to find all orders from Customer with ID=14
			//execute a sql query
			ResultSet myrs = mystmt.executeQuery("SELECT * FROM \"Orders\" WHERE \"Customer ID\" = 1 ");
			
			//process result set
			while(myrs.next()) {
				System.out.println(myrs.getInt("Order ID") + "\t" 
									+ myrs.getInt("Employee ID") + "\t"
									+ myrs.getInt("Customer ID") + "\t"
									+ myrs.getDate("Order Date") + "\t"
									+ myrs.getDate("Shipped Date") + "\t"
									+ myrs.getInt("Shipper ID") + "\t"
									+ myrs.getString("Ship Name") + "\t"
									+ myrs.getString("Ship Address") + "\t"
									+ myrs.getString("Ship City") + "\t"
									+ myrs.getString("Ship State") + "\t"
									+ myrs.getInt("Ship Zip") + "\t"
									+ myrs.getInt("Status ID") );
			}

			//2. (Prepared Statement): Query the database to find all orders from Customer with ID=14
			//sql to select from orders string
			String order = "SELECT * FROM \"Orders\" WHERE  \"Customer ID\" = ?";
			//create prepared statement
			PreparedStatement pstmt = myconn.prepareStatement(order);
			//set parameter
			pstmt.setInt(1, 1);
			//execute prepared statement
			ResultSet r = pstmt.executeQuery();
			//process result set
			while(r.next()) {
				System.out.println(r.getInt("Order ID") + "\t" 
						+ r.getInt("Employee ID") + "\t"
						+ r.getInt("Customer ID") + "\t"
						+ r.getDate("Order Date") + "\t"
						+ r.getDate("Shipped Date") + "\t"
						+ r.getInt("Shipper ID") + "\t"
						+ r.getString("Ship Name") + "\t"
						+ r.getString("Ship Address") + "\t"
						+ r.getString("Ship City") + "\t"
						+ r.getString("Ship State") + "\t"
						+ r.getInt("Ship Zip") + "\t"
						+ r.getInt("Status ID") );
			}
			
			//#3 Add 2 customers using prepared Statement
			
			//SQL query to insert into customers
			String addCustomer = "INSERT INTO \"Customers\" (\"ID\", \"Company\", \"Last Name\", \"First Name\", \"email\", \"business phone\") VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt2 = myconn.prepareStatement(addCustomer);
			
			//customer1
			pstmt2.setInt(1,  15);
			pstmt2.setString(2, "QBC SPECIALTIES");
			pstmt2.setString(3,  "OREN");
			pstmt2.setString(4, "SAM");
			pstmt2.setString(5,  "S.OREN@QBCCO.COM");
			pstmt2.setString(6, "(561)989-0989");
			pstmt2.executeUpdate();
			
			//customer2
			pstmt2.setInt(1, 16);
			pstmt2.setString(2, "JBL SPECIALTIES");
			pstmt2.setString(3,  "RENO");
			pstmt2.setString(4, "MAS");
			pstmt2.setString(5,  "M.RENO@QJBL.COM");
			pstmt2.setString(6, "(445)895-0215");
			pstmt2.executeUpdate();
			
			
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}

	}

}
