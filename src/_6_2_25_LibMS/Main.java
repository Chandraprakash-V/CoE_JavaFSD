package _6_2_25;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

	



		LibraryManager obj =new LibraryManager();
		obj.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"));
		obj.addBook(new Book("1984", "George Orwell", "9780451524935"));

		obj.addUser(new User("Alice", "123",new ArrayList<>()));
		obj.addUser(new User("Bob", "122",new ArrayList<>()));

		Thread aliceThread = new Thread(() -> {
			try {
				// Alice borrows a book
				obj.borrowBook("9780743273565", "123");
				// Alice returns a book
				obj.returnBook("9780743273565", "123");
				
				//Alice seach book
				obj.searchBook("The Great Gatsby");
				// Alice reserves another book
				obj.reserveBook("9780451524935", "123");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});

		// Thread for Bob's actions
		Thread bobThread = new Thread(() -> {
			try {
			
				// Bob borrows a book
				obj.borrowBook("9780451524935", "122");
				// Bob tries to borrow the same book again (should throw an exception)
				obj.borrowBook("9780451524935", "122");
				
				//Bob search book
				obj.searchBook("The wings of fire");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});

		// Start the threads
		aliceThread.start();
		bobThread.start();

		// Wait for both threads to finish their execution
		try {
			aliceThread.join();
			bobThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("bye");
	}


	// Initialize library system, add users and books
	// Demonstrate borrowing, returning, reserving books
	// Demonstrate multithreading by simulating multiple users
	// Save and retrieve library data using Java I/O
}

