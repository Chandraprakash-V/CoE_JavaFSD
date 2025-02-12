package _11_2_25_Fee_Report;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;
public class DBHandling {
	final Set<String> ALLOWED_TABLES = Set.of("admin","student", "accountant");
	String dbUser = System.getenv("DB_USER");
	String dbPassword = System.getenv("DB_PASSWORD");
	String dbUrl = "jdbc:mysql://localhost:3306/mydb";
	String sql = "select * from students;";
	Connection connection;
	Scanner scanner;
	public DBHandling() {
		scanner = new Scanner(System.in);
		try {
			connection=  DriverManager.getConnection(dbUrl,dbUser,dbPassword );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public  boolean authenticateUser(String name,String password,String tableName) throws SQLException {
		if(!ALLOWED_TABLES.contains(tableName)) return false;
		String sql = "SELECT 1 FROM "+tableName+" WHERE name = ? AND password = ?"; 
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password); 

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); 
            }
        }
	}
	
	public void addStudent() throws SQLException {

        String sql = "INSERT INTO student (name, email, course, fee, paid, due, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            System.out.println("Enter student details:");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Course: ");
            String course = scanner.nextLine();

            System.out.print("Fee: ");
            double fee = scanner.nextDouble();
            scanner.nextLine(); 

            System.out.print("Paid: ");
            double paid = scanner.nextDouble();
            scanner.nextLine(); 

            System.out.print("Due: ");
            double due = scanner.nextDouble();
            scanner.nextLine(); 


            System.out.print("Address: ");
            String address = scanner.nextLine();

            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, course);
            preparedStatement.setDouble(4, fee);
            preparedStatement.setDouble(5, paid);
            preparedStatement.setDouble(6, due);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, phone);

            System.out.println( (preparedStatement.executeUpdate()>0)? "Student data inserted successfully!": "Insertion failed");


        }
	}
	public void addAccountant() {
		String sql = "INSERT INTO accountant (name, email, phone, password) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			System.out.println("Enter accountant details:");

			System.out.print("Name: ");
			String name = scanner.nextLine();

			System.out.print("Email: ");
			String email = scanner.nextLine();

			System.out.print("Phone: ");
			String phone = scanner.nextLine();

			System.out.print("Password: ");
			String password = scanner.nextLine(); 

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, password); 
			System.out.println( (preparedStatement.executeUpdate()>0)? "Accountant data inserted successfully!": "Insertion failed");

			

		}catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
		
		}
	}
	
	public void viewAccountants() {
		String sql = "select * from accountant;";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs=  preparedStatement.executeQuery(sql)){
			if (rs.isBeforeFirst()) printResultset(rs);
			else {System.out.println("No data");}
		}catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
		
		}
		
	}
	
	public void viewStudents() throws SQLException {
		String sql = "select * from student;";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs=  preparedStatement.executeQuery(sql)){
			if (rs.isBeforeFirst()) printResultset(rs);
			else {System.out.println("No data");}
		}
		
	}
	
	public void viewStudentDue() throws SQLException {
		String sql = "select * from student where due>0;";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs=  preparedStatement.executeQuery(sql)){
			if (rs.isBeforeFirst()) printResultset(rs);
			else {System.out.println("No data");}
		}
	} 
	
	
	public void editStudent(String email) throws SQLException {
		
	    
	    if(!checkEmail(email,"student")) {System.out.println("Student with this email doesn't exist.");
		return;}
	    
	        String sql = "UPDATE student SET name = ?, email = ?, course = ?, fee = ?, paid = ?, due = ?, address = ?, phone = ? WHERE email = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) { 
	        System.out.println("Enter updated student details:");
	        System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Email: ");
            String newEmail = scanner.nextLine();

            System.out.print("Course: ");
            String course = scanner.nextLine();

            System.out.print("Fee: ");
            double fee = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Paid: ");
            double paid = scanner.nextDouble();
            scanner.nextLine(); 
            System.out.print("Due: ");
            double due = scanner.nextDouble();
            scanner.nextLine();


            System.out.print("Address: ");
            String address = scanner.nextLine();

            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setString(3, course);
            preparedStatement.setDouble(4, fee);
            preparedStatement.setDouble(5, paid);
            preparedStatement.setDouble(6, due);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, phone);
            preparedStatement.setString(9, email);
            System.out.println( (preparedStatement.executeUpdate()>0)? "Student data updated successfully!": "update failed");

	        }
	}
	
	public void editAccountant(String email) throws SQLException {
		
	    
	    if(!checkEmail(email,"accountant")) {System.out.println("Accountant with this email doesn't exist.");
		return;}
	    
	    String sql ="UPDATE accountant SET name = ?, email = ?, phone = ? WHERE email = ?";
	       try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			System.out.println("Enter accountant details:");

			System.out.print("Name: ");
			String name = scanner.nextLine();

			System.out.print("Email: ");
			String newEmail = scanner.nextLine();

			System.out.print("Phone: ");
			String phone = scanner.nextLine();

		

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, newEmail);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, email);
			

			System.out.println( (preparedStatement.executeUpdate()>0)? "Accountant data updated successfully!": "Update failed");
	       }

		
	}
	
	public void deleteAccountant(String email) throws SQLException {
		if(!checkEmail(email,"accountant")) {System.out.println("Accountant with this email doesn't exist.");
		return;}
		
		 String sql = "DELETE FROM accountant WHERE email = ?";
         try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

             preparedStatement.setString(1, email);

         	System.out.println( (preparedStatement.executeUpdate()>0)? "Accountant data deleted successfully!": "Delete failed");
  	      
         }
		
	}
	
	
	public void deleteStudent(String email) throws SQLException {
		if(!checkEmail(email,"student")) {System.out.println("Student with this email doesn't exist.");
		return;}
		
		 String sql = "DELETE FROM student WHERE email = ?";
         try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

             preparedStatement.setString(1, email);

         	System.out.println( (preparedStatement.executeUpdate()>0)? "Student data deleted successfully!": "Delete failed");
  	      
         }
		
	}
	
	
	public boolean checkEmail(String email,String tableName) throws SQLException {
		
		if(!ALLOWED_TABLES.contains(tableName)) return false;
		
		String checkSql = "SELECT 1 FROM "+tableName+" WHERE email = ?";
	    try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
	    	
	    	checkStatement.setString(1, email);
	        try (ResultSet resultSet = checkStatement.executeQuery()) {
	            return resultSet.next();
	        }
	        
	    }
	    
	}
	public void printResultset(ResultSet resultSet) throws SQLException {
		int columnCount =resultSet.getMetaData().getColumnCount();
		
		for (int i = 1; i <= columnCount; i++) {
	        System.out.printf("%-20s", resultSet.getMetaData().getColumnName(i)); // Example: 20-character width
	    }
	    System.out.println();
	    System.out.println("----------------------------------------------------------------------"); // Separator

	    while (resultSet.next()) {
	        for (int i = 1; i <= columnCount; i++) {
	            Object value = resultSet.getObject(i);
	            System.out.printf("%-20s", (value == null) ? "NULL" : value.toString()); // Format and handle NULL
	        }
	        System.out.println();
	    }
	    
	}
}
