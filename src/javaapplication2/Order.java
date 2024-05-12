
package javaapplication2;
import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
public class Order implements Serializable {
      private int orderID;
    private ArrayList<Product> products;
    private String status;
    private double totalRevenue;
   private Date orderDate;
      private static int nextOrderID = 1;
       private int rating;
    public Order(int orderID, ArrayList<Product> products, String status, Date orderDate) {
        this.orderID = orderID;
        this.products = products;
        this.status = status;
         this.orderDate = orderDate;
         this.rating = -1;
    }
        public static int getNextOrderID() {
        return nextOrderID;
    }

    public static void setNextOrderID(int nextOrderID) {
        Order.nextOrderID = nextOrderID;
    }
     public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.rating = rating;
        } else {
        }
    }
       public int getOrderID() {
        return orderID;
    }
              public Date getOrderDate() {
                  return orderDate;
              }

public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }
    
      public void viewDetails() {
        for (Product product : products) {
    }
   }
      
  public double calculateTotalAmount() {
        double totalAmount = 0.0;
        for (Product product : products) {
            totalAmount += product.getPrice() * product.getQuantity();
        }
        this.totalRevenue = totalAmount;
        return totalAmount;
    }
    public void changeStatus(String newStatus) {
        this.status = newStatus;
    }

    public double getRevenue() {
        return totalRevenue;
    }
        public boolean isEmpty() {
        return products.isEmpty();
    }
     

public static int generateUniqueOrderID() {
    return nextOrderID++;
}
          
}
