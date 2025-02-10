package _6_2_25;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class LibrarySystem implements ILibrary {
	protected List<Book> books=new ArrayList<>();;
	protected List<User> users;
	private static final String FILE_NAME = "library_data.ser";


	public abstract void addBook(Book book);
	public abstract void addUser(User user);

	public static <T> void writeToFile(List<T> data) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(data);
			
			//System.out.println("Data has been written to the file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}





	 public static void readFromFile() {
	       
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
	            List<?> data = (List<?>) ois.readObject();  // Read the list as an object
	            for (Object obj : data) {
	                if (obj instanceof User) {
	                    User user = (User) obj;
	                    System.out.println("User: " + user);
	                } else if (obj instanceof Book) {
	                    Book book = (Book) obj;
	                    System.out.println("Book: " + book);
	                } else {
	                    System.out.println("Unknown object: " + obj);
	                }
	            }
	        } catch (EOFException e) {
	            System.out.println("End of file reached.");
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

}
// Implementations for ILibrary methods


