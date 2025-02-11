package InventoryManagement;

import java.util.List;

public class Order implements Comparable<Order>{
	private String orderID;
	   private List<String> productIDs;
	   private Priority priority;

	   // Enum for priority
	   public enum Priority {
	       STANDARD, EXPEDITED
	   }
	   
	   public Order(String orderId, List<String> productId, Priority priority) {
	        this.orderID = orderId;
	        this.productIDs = productId;
	        this.priority = priority;
	    }

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public List<String> getProductIDs() {
		return productIDs;
	}

	public void setProductIDs(List<String> productIDs) {
		this.productIDs = productIDs;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	 public int compareTo( Order o) {
	        return this.priority.compareTo(o.priority);
	    }
	 
	    public String toString() {
	        return "Order{" +
	                "orderId='" + orderID + '\'' +
	                ", productId=" + productIDs +
	                ", priority=" + priority +
	                '}';
	    }
}
