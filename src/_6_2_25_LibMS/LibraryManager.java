package _6_2_25;

import java.util.ArrayList;

public class LibraryManager extends LibrarySystem {
	 private final Object lock = new Object();
	
	public LibraryManager() {
		if(books==null) {
			books=new ArrayList<>();
		}
		if(users==null) {
			users=new ArrayList<>();
		}
	}
	@Override
	public void borrowBook(String ISBN, String userID)
			throws BookNotFoundException, UserNotFoundException, MaxBooksAllowedException {
		// TODO Auto-generated method stub
		for(User u:users) {
			if(u.getUserID().equals(userID)) {
				if(u.getBorrowedBooks().size()==5) {
					throw new MaxBooksAllowedException();
				}
				for(Book b:books) {
					if(b.getISBN().equals(ISBN)) {
						u.getBorrowedBooks().add(b);
						System.out.println(u.getName()+" borrowed book ");
						return;
					}
				}throw new BookNotFoundException("Book not available. Come again after few days.");
				
			}
		}
		throw new UserNotFoundException("User ID does not exist!");
	}
		
	

	@Override
	public void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
		// TODO Auto-generated method stub
		for(User u:users) {
			if(u.getUserID().equals(userID)) {
				for(Book b:books) {
					if(b.getISBN().equals(ISBN)) {
						u.getBorrowedBooks().remove(b);
						System.out.println(u.getName()+" returned book ");
						return;
					}
				}
				throw new BookNotFoundException("Enter correct ISBN number  plz");
			}
		}
		throw new UserNotFoundException("User ID does not exist!");
		
	}

	@Override
	public void reserveBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
		// TODO Auto-generated method stub
		
		for(User u:users) {
			if(u.getUserID().equals(userID)) {
				for(Book b:books) {
					if(b.getISBN().equals(ISBN)) {
						u.getBorrowedBooks().add(b);
						System.out.println(u.getName()+" reserved book ");
						return;
					}
				}
				throw new BookNotFoundException("Plz Enter correct ISBN number");
			}
		}
		throw new UserNotFoundException("User ID does not exist!");
		
	}

	@Override
	public Book searchBook(String title) {
		// TODO Auto-generated method stub
		synchronized (lock) {
		boolean flag=false;
		for(Book b:books) {
			if(b.getTitle().equals(title)) {
			    flag=true;
				System.out.println(title+ " book available. ");
				return b;
			}
		}
		if (!flag) {
		
			System.out.println("The list of available book are:");
			LibrarySystem.readFromFile();
			
		}
		 lock.notifyAll();
		return null;
		  // Notify the next waiting thread to proceed
        }
			
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		books.add(book);
		LibrarySystem.writeToFile(books);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		users.add(user);
		
	}
	   // Detailed implementations for all abstract methods and interface methods
	   // Thread-safe implementations for borrowBook, returnBook methods
	   // Exception handling within all methods
	}
