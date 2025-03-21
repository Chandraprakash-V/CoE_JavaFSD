package _6_2_25;

import java.util.List;

public class User {
	   private String name;
	   private String userID;
	   private List<Book> borrowedBooks;
	public User() {}   
	public User(String name, String userID, List<Book> borrowedBooks) {
		super();
		this.name = name;
		this.userID = userID;
		this.borrowedBooks = borrowedBooks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(List<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", userID=" + userID + ", borrowedBooks=" + borrowedBooks + "]";
	}

	   // Constructors, getters, and setters
	
	   
	}

