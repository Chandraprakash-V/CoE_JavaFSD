package _11_2_25_Fee_Report;

import java.sql.SQLException;
import java.util.Scanner;

public class FeeReportSoftware {
	Scanner scanner;
	DBHandling DBobj;
	public FeeReportSoftware() {
		// TODO Auto-generated constructor stub
		scanner=new Scanner(System.in);
		DBobj= new DBHandling();
		login();
	}
	public void login() {
		while(true) {	
			System.out.println("Select user/Exit");
			System.out.println("1. Admin");
			System.out.println("2. Accountant");
			System.out.println("3.Exit");
			int choice= scanner.nextInt();
			if (choice ==3)return;
			System.out.println("Enter username/name:");
			String name=scanner.next();
			System.out.println("Enter password:");
			String password=scanner.next();


			switch(choice) {
			case 1:
				try {
					if(!DBobj.authenticateUser(name, password,"admin")) {
						System.out.println("Invalid credentials");
						continue;
					}
					adminUI();
					break;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			case 2:
				try {
					if(!DBobj.authenticateUser(name, password,"accountant")) {

						System.out.println("Invalid credentials");
						continue;
					}
					accountantUI();
					break;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
		}
	}

	public void adminUI() {
		int choice;
		String email;
		do {
			System.out.println("\nAccountant Management Menu:");
			System.out.println("1. Add new accountant");
			System.out.println("2. View existing accountants");
			System.out.println("3. Edit accountant details");
			System.out.println("4. Delete accountant");
			System.out.println("5. Logout");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); 

			switch (choice) {
			case 1:
				DBobj.addAccountant();
				break;
			case 2:
				DBobj.viewAccountants();
				break;
			case 3:
				System.out.println("Enter email:");
				email=scanner.next();
				try {
					DBobj.editAccountant(email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Enter email:");
				email=scanner.next();
				try {
					DBobj.deleteAccountant(email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				System.out.println("Logging out...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 5);
	}



	public void accountantUI() {
		int choice;
		String email;
		do {
			System.out.println("\nStudent Management Menu:");
			System.out.println("1. Add new student");
			System.out.println("2. View student records");
			System.out.println("3. Edit student details");
			System.out.println("4. Delete student records");
			System.out.println("5. Check Due Fee");
			System.out.println("6. Logout");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				try {
					DBobj.addStudent();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					DBobj.viewStudents();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Enter email:");
				email=scanner.next();
				try {
					DBobj.editStudent(email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Enter email:");
				email=scanner.next();
				try {
					DBobj.deleteStudent(email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:
				try {
					DBobj.viewStudentDue();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break; 
			case 6:
				System.out.println("Logging out...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			} 
		}while (choice != 6);
	}
}
