
package javaapplication2;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Customer extends User implements Serializable {
    private Cart cart;
    private ArrayList<Order> orderHistory; 
    private String address;
    private String cardNumber; 
    private int ccv;

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    private String expiryDate; 
    private transient Scanner scanner= new Scanner(System.in);
    public Customer( String username, String password) {
        super( username, password);
        this.cart = new Cart();
        this.orderHistory = new ArrayList<>();
        address="";
    }
public Order findOrder(int orderID) {
    for (Order order : orderHistory) {
        if (order.getOrderID()==(orderID)) {
            return order;
        }
    }
    return null;  
}
    public void rateOrders() {
        Scanner scanner2=new Scanner(System.in);
        boolean checkfororder=false;

        
        for (Order order : getOrderHistory()) {
            if ("Shipped".equals(order.getStatus())) {
                checkfororder=true;
                
            }
        }

        if(!checkfororder){
        return;
        }

        
        int orderIDToRate = 0;

while (true) {
    try {
        orderIDToRate = scanner2.nextInt();

        if (orderIDToRate == 0) {
            
            return;
        }

       
        Order orderToRate = findOrder(orderIDToRate);

        if (orderToRate != null && "Shipped".equals(orderToRate.getStatus())) {
            int rating = 0;

            while (true) {
                try {
                    rating = scanner2.nextInt();

                    if (rating >= 1 && rating <= 5) {
                        orderToRate.setRating(rating);
                        break; 
                    } else {
                    }
                } catch (InputMismatchException e) {
                    scanner2.nextLine(); 
                }
            }
        } else {
        }
        break;
    } catch (InputMismatchException e) {
        scanner2.nextLine(); 
    }
}
    }

    
  public void addAddress(String newAddress) {
      Scanner scanner2=new Scanner(System.in);
        if (address != null && !address.isEmpty()) {

            if ("yes".equalsIgnoreCase("done")) {
                address = newAddress;
            } else {
            }
        } else {
            address = newAddress;
        }
    }

    public String getAddress() {
        return address;
    }



    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
 public void addProduct(Product product, int Quantity) {
    
       Scanner scanner2= new Scanner(System.in);
   
   if (product.getQuantity() > 0) {
     

        cart.addProduct(product, Quantity);
       
        product.getSellingSeller().decProductQuantity(product.getProductID(), Quantity);
    } else {
    }
}
public void removeProduct(Product product){
    cart.removeProduct(product);
     product.getSellingSeller().incProductQuantity(product.getProductID(), product.getQuantity());

}

    public void viewCart() {
        ArrayList<Product> cartProducts = cart.getProducts();

        if (!cartProducts.isEmpty()) {
            System.out.println("Cart Contents:");
            for (Product product : cartProducts) {
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Name: " + product.getName());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
              
                System.out.println("---------------------");
            }
        } else {
            System.out.println("Cart is empty.");
        }
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
  

     public void displayOrderHistory() {
        if (orderHistory != null && !orderHistory.isEmpty()) {
            System.out.println("Order History:");
            for (Order order : orderHistory) {
                System.out.println("Order ID: " + order.getOrderID());
                System.out.println("Status: " + order.getStatus());
            }
        } else {
            System.out.println("No order history found.");
        }
    }

    public void displayProductNames(Order order) {
        for (Product product : order.getProducts()) {
            System.out.println(product.getName());
        }
    }
    public void addOrder(Order order) {
    orderHistory.add(order);
}

   public Order completePurchase() {
   
    Order newOrder=null;
    if (!cart.isEmpty()) {
        int orderID = Order.generateUniqueOrderID();
        newOrder = new Order(orderID, new ArrayList<>(cart.getProducts()), "Pending", new Date());
        newOrder.calculateTotalAmount();
        assignOrderToSellers(newOrder);
        decProductQuantities(cart.getProducts());
        cart.clear();
        orderHistory.add(newOrder);

    } else {
    }
    return newOrder;
}

private void decProductQuantities(ArrayList<Product> products) {
    for (Product product : products) {
        Seller seller = product.getSellingSeller();
        seller.decProductQuantity(product.getProductID(), product.getQuantity());
    }
}
private void incProductQuantities(ArrayList<Product> products) {
    for (Product product : products) {
        Seller seller = product.getSellingSeller();
        seller.incProductQuantity(product.getProductID(), product.getQuantity());
    }
}
   private void assignOrderToSellers(Order order) {
    ArrayList<Product> orderProducts = order.getProducts();
    ArrayList<Seller> processedSellers = new ArrayList<>();

    for (Product product : orderProducts) {
        Seller seller = product.getSellingSeller();
        if (seller != null && !processedSellers.contains(seller)) {
            seller.addOrder(order);
            processedSellers.add(seller);
        }
    }
}
   
     public ArrayList<Product> searchProducts(String query) {
        ArrayList<Product> searchResults = new ArrayList<>();

        for (User user : Admin.users) {
            if (user instanceof Seller) {
                Seller seller = (Seller) user;
                ArrayList<Product> sellerProducts = seller.getProducts();

                for (Product product : sellerProducts) {
                    if (product.getName().toLowerCase().contains(query.toLowerCase())
                            || String.valueOf(product.getPrice()).contains(query)
                            || String.valueOf(product.getSellingSeller().getUserID()).contains(query)) {
                        searchResults.add(product);
                    }
                }
            }
        }

        if (!searchResults.isEmpty()) {
            for (Product result : searchResults) {
            }
        } else {
        }

        return searchResults;
    }
     
     public void viewPayment() {
    Scanner scanner2 = new Scanner(System.in);

    while (true) {
        try {
            System.out.println("Payment Options:");
            System.out.println("1. Credit Card");
            System.out.println("2. Cash on Delivery");

            System.out.print("Choose a payment method: ");
            int paymentChoice = scanner2.nextInt();

            switch (paymentChoice) {
                case 1:
                    if (cardNumber != null && expiryDate != null) {
                        System.out.println("Payment Information:");
                        System.out.println("Card Number: " + cardNumber);
                        System.out.println("Expiry Date: " + expiryDate);
                        System.out.print("Do you want to register a new credit card? (yes/no): ");
                        String regcar = scanner2.next();
                        if ("yes".equalsIgnoreCase(regcar)) {
                            registerCreditCard();
                        } else {
                            System.out.println("Continuing with the payment process...");
                        }
                    } else {
                        System.out.println("No credit card information available.");
                        System.out.print("Do you want to register a new credit card? (yes/no): ");
                        String registerCard = scanner2.next();

                        if ("yes".equalsIgnoreCase(registerCard)) {
                            registerCreditCard();
                        }
                    }
                    return;

                case 2:
                    System.out.println("Payment Method: Cash on Delivery");
                    return;

                

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
    System.out.println("Invalid input. Please enter a valid integer.");
    scanner2.next(); 
}
        
    }
}
    private void registerCreditCard() {
        System.out.print("Enter your credit card number: ");
        cardNumber = scanner.next();
        System.out.print("Enter the expiry date (MM/YYYY): ");
        expiryDate = scanner.next();

        System.out.println("Credit card registered successfully.");
    }
    public void confirmCart() {
         Scanner scanner2= new Scanner(System.in);
    if (!cart.isEmpty()) {
        viewCart(); 


        String proceed = scanner2.next();

        if ("yes".equalsIgnoreCase(proceed)) {
       
            completePurchase();
        } else {
        }
    } else {
    }
    }

    public void setPaymentInformation(String cardNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }
      public void cancelCart() {
        if (!cart.isEmpty()) {
            viewCart(); 

    incProductQuantities(cart.getProducts());
            cart.clear();

        } else {
        }
    }
        public void trackOrderStatus() {
        if (!orderHistory.isEmpty()) {
            for (Order order : orderHistory) {
            }
        } else {
        }
    }
}
