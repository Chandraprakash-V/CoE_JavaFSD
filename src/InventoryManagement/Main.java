package InventoryManagement;

import java.util.Arrays;
import java.util.List;

public class Main {
	   public static void main(String[] args) {
		      InventoryManager inventoryManager = new InventoryManager();
		      // Load products and initial inventory
		      // Simulate order processing
		      // Multithreaded simulation for handling multiple orders
		      // Exception handling and logging
		      inventoryManager.addProduct(new Product("P1", "IPhone", 10, new Location(1, 2, 3)));
		        inventoryManager.addProduct(new Product("P2", "S24", 50, new Location(2, 3, 4)));
		        inventoryManager.addProduct(new Product("P3", "MacBook", 30, new Location(3, 4, 5)));
		        
		        List<String> order1Products = Arrays.asList("P1", "P2");
		        List<String> order2Products = Arrays.asList("P2", "P3");
		        List<String> order3Products = Arrays.asList("P1", "P3");
		 
		        inventoryManager.processOrders(new Order("O1", order1Products, Order.Priority.STANDARD));
		        inventoryManager.processOrders(new Order("O2", order2Products, Order.Priority.EXPEDITED));
		        inventoryManager.processOrders(new Order("O3", order3Products, Order.Priority.STANDARD));
		        // Shutdown inventory manager
		        inventoryManager.shutdown();

		        // Print final inventory state
		        inventoryManager.printInventory();
	   }

}
