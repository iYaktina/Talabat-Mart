
package javaapplication2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Seller extends User implements Serializable {
    
    private ArrayList<Product> products;
    private ArrayList<Order> orderHistory;
        String status;

    public Seller( String username, String password) {
        super( username, password);
        this.products = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        
    }
    
    public void decProductQuantity(String productID, int soldQuantity) {
        boolean productFound = false;

    for (Product product : products) {
        if (product.getProductID().equals(productID)) {
       
            int currentQuantity = product.getQuantity();
            if (currentQuantity >= soldQuantity) {
                product.setQuantity(currentQuantity - soldQuantity);
            } else {
     
            }
            productFound = true; 
            break; 
        }
    }

    if (!productFound) {
    }
    }
      public void incProductQuantity(String productID, int retQuantity) {
        boolean productFound = false;

    for (Product product : products) {
        if (product.getProductID().equals(productID)) {
       
            int currentQuantity = product.getQuantity();
        
                product.setQuantity(product.getQuantity() + retQuantity);
          
     
         
            productFound = true; 
            break; 
        }
    }

    if (!productFound) {
    }
    }
public void displayproducts(){

    for(int i=0;i<products.size();i++){
        if(products.get(i).getQuantity()<1){
            status="Out of stock";
        }
        else{
        status="In Stock";
        }
    }
}
  public Product findProductByCategoryAndID(String category, String productID) {
        for (Product product : products) {
            if (product.getProductID().startsWith(category) && product.getProductID().endsWith(productID)) {
                return product;
            }
        }
        return null; 
    }
public boolean displayproductsbycategory(String productid) {
    boolean productsDisplayed = false; 

    for (Product product : products) {
        if (product.getQuantity() < 1) {
            status = "Out of stock";
        } else {
            status = "In Stock";
        }

        if (product.getProductID().startsWith(productid)) {
            
            String category = product.getCategoryFromProductId();


            productsDisplayed = true;
        }
    }

return productsDisplayed;
}
public Product getproductbyid(String productid){
     for(Product a:products){
         if(productid.equals(a.getProductID())){
             return a;
         }
     }
     return null;
}
public void deleteproduct(Product product){
    if(product==null){
    return;
    }
    else{
    products.remove(product);
    }
}
public void editproduct(Product product){ 
    Scanner s=new Scanner(System.in);
    if(product==null){
    return;
    }
    else{
        int option=0;
         while (true) {
            try {
    System.out.println("Enter the option to update:");
    System.out.println("1. Product ID");
    System.out.println("2. Name");
    System.out.println("3. Price");
    System.out.println("4. Quantity");
    System.out.println("5. To go back");
     option = s.nextInt();
     break;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for the option.");
                s.nextLine(); 
            }
        }
    
    switch (option) {
        case 1:
            System.out.println("Enter the new Product ID :");
            String ProductID = s.next();
            product.setProductID(ProductID);
            product.categorychecker();
            System.out.println("Product ID updated successfully."+ " Current Category is : "+ product.getCategoryFromProductId());
            break;

        case 2:
            System.out.println("Enter the new Product name:");
            String name = s.next();
            product.setName(name);
            System.out.println("Product name updated successfully.");
            break;

        case 3:
            double price=0;
             while (true) {
            try {
            System.out.println("Enter the new Price:");
            price=s.nextDouble();
            break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid double value.");
                s.nextLine(); 
            }
        }
            product.setPrice(price);
            System.out.println("Price updated successfully.");
            break;
        case 4:
            int quantity=0;
            while (true) {
            try {
            System.out.println("Enter the new Quantity:");
            quantity=s.nextInt();
            break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer value.");
                s.nextLine(); 
            }
        }
            product.setQuantity(quantity);
            System.out.println("Quantity updated successfully.");
            System.out.println("Going back ...");
            break;
        default:
            System.out.println("Invalid option. No changes made.");
            break;
    }
  }
}
public void listproduct(){
    System.out.println("Avaliable Products ...");
    for(Product a:products){
        System.out.println("Product name : "+a.getName() );
        System.out.println("Product price : "+a.getPrice());
        System.out.println("Product id : "+ a.getProductID());
        System.out.println("Product quantity : "+a.getQuantity() );
        System.out.println("Product Category : "+ a.categorychecker()) ;
        System.out.println("----------------------------------------");
       
    }
    if(products.isEmpty()){
        System.out.println("No Products are up for sale ...");
        System.out.println("Please add your products ...");
    }
}
    public ArrayList<Product> getProducts() {
        return products;
    }
     public ObservableList<String> getObservableProducts() {
         ObservableList<String> prods = FXCollections.observableArrayList();

         for(Product a:products){
              prods.add("Product name : "+a.getName()+ " Product price : "+a.getPrice()+"\nProduct id : "+ a.getProductID()+" Product quantity : "+a.getQuantity()
              +"\nProduct Category : "+ a.categorychecker()+"\n----------------------------------------" );
    }
    if(products.isEmpty()){
       return null;
    }
            
        return FXCollections.observableArrayList(prods);
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    
    public ArrayList<Order> getOrders() {
        return orderHistory;
    }
    
     public void addOrder(Order order) {
    orderHistory.add(order);
}
public boolean hasPendingOrders(){
    for (Order order : orderHistory) {
            if("Pending".equals(order.getStatus())){
            return true;}
    }
    return false;
}
    public void displayOrders() {
        boolean check=false;
        System.out.println("Orders related to Seller " + getUsername() + ":");
        for (Order order : orderHistory) {
            if("Pending".equals(order.getStatus())){
                check=true;
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Status: " + order.getStatus());
    
            System.out.println("---------------------");
        }
        }
        if(!check)
            System.out.println("No Pending Orders");
    }
     public void displayOrderHistory() {
        if (!orderHistory.isEmpty()) {
             boolean hasShippedOrders = false;
            for (Order order : orderHistory) {
                if("Shipped".equals(order.getStatus())){
                     hasShippedOrders = true;
                System.out.println("Order ID: " + order.getOrderID());
                System.out.println("Status: " + order.getStatus());
                System.out.println("Customer Rating "+order.getRating()+" Out of 5 Stars");
                System.out.println("---------------------");
            }
            }
         if (!hasShippedOrders) {
        }
    } else {
    }
    }
     //GUI Updated Func 
public ObservableList<String> getPendingOrders() {
        ObservableList<String> pendingOrdersList = FXCollections.observableArrayList();
        boolean hasPendingOrders = false;

        for (Order order : orderHistory) {
            if ("Pending".equals(order.getStatus())) {
                hasPendingOrders = true;
                pendingOrdersList.add("Order ID: " + order.getOrderID() +
                        "\nStatus: " + order.getStatus() +
                        "\n---------------------");
            }
        }

        if (!hasPendingOrders) {
            pendingOrdersList.add("No Pending Orders");
        }

        return pendingOrdersList;
    }

    public ObservableList<String> getCompleteOrders() {
        ObservableList<String> orderHistoryList = FXCollections.observableArrayList();
        boolean hasShippedOrders = false;

        if (!orderHistory.isEmpty()) {
            orderHistoryList.add("Order History for Seller ID: " + super.getUserID());

            for (Order order : orderHistory) {
                if ("Shipped".equals(order.getStatus())) {
                    hasShippedOrders = true;
                    orderHistoryList.add("Order ID: " + order.getOrderID() +
                            "\nStatus: " + order.getStatus() +
                            "\nCustomer Rating: " + order.getRating() + " Out of 5 Stars" +
                            "\n---------------------");
                }
            }

            if (!hasShippedOrders) {
                orderHistoryList.add("No completed purchases for Seller ID: " + super.getUserID());
            }
        } else {
            orderHistoryList.add("No orders found for Seller ID: " + super.getUserID());
        }

        return orderHistoryList;
    }
    public int getNumberOfPiecesSoldOverPeriod(Date startDate, Date endDate) {
        int piecesSold = 0;

        for (Order order : orderHistory) {
            Date orderDate = order.getOrderDate();

     
            if (orderDate.after(startDate) && orderDate.before(endDate)) {
             
                for (Product product : order.getProducts()) {
                    if(product.getSellingSeller()==this)
                    piecesSold += product.getQuantity();
                }
            }
        }

        return piecesSold;
    }
     public Product getBestSellerProductOverPeriod(Date startDate, Date endDate) {
        Product bestSellerProduct = null;
        int maxQuantitySold = 0;

        for (Order order : orderHistory) {
            Date orderDate = order.getOrderDate();

    
            if (orderDate.after(startDate) && orderDate.before(endDate)) {
          
                for (Product product : order.getProducts()) {
                    if(product.getSellingSeller()==this){
                    int quantitySold = product.getQuantity();
                    if (quantitySold > maxQuantitySold) {
                        maxQuantitySold = quantitySold;
                        bestSellerProduct = product;
                    }
                }
                }
            }
        }

        if (bestSellerProduct != null) {
        } else {
        }

        return bestSellerProduct;
    }
     
     public Product getMostRevenueProductOverPeriod(Date startDate, Date endDate) {
        Product mostRevenueProduct = null;
        double maxRevenue = 0.0;

        for (Order order : orderHistory) {
            Date orderDate = order.getOrderDate();


            if (orderDate.after(startDate) && orderDate.before(endDate)) {
        
                for (Product product : order.getProducts()) {
                    if(product.getSellingSeller()==this){
                    double productRevenue = product.getPrice() * product.getQuantity();
                    if (productRevenue > maxRevenue) {
                        maxRevenue = productRevenue;
                        mostRevenueProduct = product;
                    }
                    }
                }
            }
        }

        if (mostRevenueProduct != null) {
        } else {
        }

        return mostRevenueProduct;
    }
     
       public void changeOrderStatusToShipped(int orderID) {
        for (Order order : orderHistory) {
            if (order.getOrderID() == orderID && order.getStatus().equals("Pending")) {
                order.changeStatus("Shipped");
                return;
            }
        }
    }
       
        public double calculateTotalRevenueOverPeriod(Date startDate, Date endDate) {
        double totalRevenue = 0.0;
        int orderCount = 0;
        for (Order order : orderHistory) {
            Date orderDate = order.getOrderDate();
            if (orderDate.after(startDate) && orderDate.before(endDate)) {
                totalRevenue += order.getRevenue();
                orderCount++;
            }
        }
        if (orderCount > 0) {
            double averageRevenue = totalRevenue / orderCount;
            return averageRevenue;
        } else {
            return 0.0; 
        }
    }
}

