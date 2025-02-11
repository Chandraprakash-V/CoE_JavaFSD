package InventoryManagement;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryManager {
	   private Map<String, Product> products;
	   private PriorityQueue<Order> orderQueue;
	   private ExecutorService executorService;

	   public InventoryManager() {
	       products = new ConcurrentHashMap<>();
	       orderQueue = new PriorityQueue<>(Comparator.comparing(o -> o.getPriority() == Order.Priority.EXPEDITED ? 0 : 1));
	       executorService = Executors.newFixedThreadPool(5);
	   }
	   
	   public synchronized void addProduct(Product product) {
	       products.put(product.getProductID(), product);
	       // Logic to place product in correct location
	   }
	   
	   public void processOrders(Order order) {
	       // Multithreaded order processing
		   orderQueue.add(order);
	        System.out.println("Added order to queue: "+ order);
	        executorService.submit(() -> fulfillOrder(order));
	   }
	   
	   private void fulfillOrder(Order order){
	        for(String prdtId : order.getProductIDs()){
	            Product product = products.get(prdtId);

	            if(product != null && product.getQuantity() > 0){
	                synchronized (product){
	                    product.setQuantity(product.getQuantity() - 1);
	                    System.out.println("Processed product: " + prdtId + " for order: " + order.getOrderID());
	                }
	            }else{
	                System.out.println("Out of stock for product" + prdtId);
	            }
	        }
	    }
	   public void shutdown(){
	        executorService.shutdown();
	        try {
	            if(!executorService.awaitTermination(60, TimeUnit.SECONDS)){
	                executorService.shutdownNow();
	            }
	        } catch (InterruptedException e) {
	            executorService.shutdownNow();
	        }
	    }

	    public void printInventory(){
	        System.out.println("Current Inventory:");
	        products.forEach((id, prdt) -> System.out.println(prdt));
	    }


}
