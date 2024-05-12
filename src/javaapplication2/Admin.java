
package javaapplication2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.InputMismatchException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Admin extends User implements Serializable  {
   private transient Scanner s = new Scanner(System.in);
    public static ArrayList<User> users;

    public Admin( String username, String password) {
        super( username, password);
        this.users = new ArrayList<>();
    }
    public Admin(){
        super("Admin","Root");
        this.users = new ArrayList<>();
    }

    public static void setUsers(ArrayList<User> users) {
        Admin.users = users;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public void adduser(User user) {
        users.add(user);
    }

    public void edituser(User user) {
    if(user==null){
    System.out.println("User not found");
    return;
    }
    else{
        int option =0;
    while (true) {
            try {
                System.out.println("Enter the option to update:");
                System.out.println("1. Username");
                System.out.println("2. Password");
                System.out.println("3. Both");
                System.out.println("4. To go back");

                option = s.nextInt();
                break; 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for the option.");
                s.nextLine(); 
            }
        }
    
    switch (option) {
        case 1:
            System.out.println("Enter the new username:");
            String newUsername = s.next();
            user.setuser(newUsername);
            System.out.println("Username updated successfully.");
            break;

        case 2:
            System.out.println("Enter the new password:");
            String newPassword = s.next();
            user.setpass(newPassword);
            System.out.println("Password updated successfully.");
            break;

        case 3:
            System.out.println("Enter the new username:");
            String updatedUsername = s.next();
            System.out.println("Enter the new password:");
            String updatedPassword = s.next();
            user.setuser(updatedUsername);
            user.setpass(updatedPassword);
            System.out.println("Username and password updated successfully.");
            break;
        case 4:
            System.out.println("No Changes made .....");
            System.out.println("Going back ...");
            break;
        default:
            System.out.println("Invalid option. No changes made.");
            
    }
  }
}

    public void removeuser(int userid) {
        users.removeIf(userToRemove -> userToRemove.getUserID() == userid);
    }

    public void listuser() {
        System.out.println("List of Users:");
        for (User currentUser : users) {
            System.out.println("UserID: " + currentUser.getUserID() + ", Username: " + currentUser.getUsername());
        }
    }
    
    //GUI Updated function
     public ObservableList<String> getUserNames() {
        ObservableList<String> userNames = FXCollections.observableArrayList();
        for (User currentUser : users) {
            if(currentUser instanceof Admin)
            userNames.add("UserID: " + currentUser.getUserID() + ", Username: " + currentUser.getUsername() + ", Role : Admin" );
            else if(currentUser instanceof Customer)
            userNames.add("UserID: " + currentUser.getUserID() + ", Username: " + currentUser.getUsername() + ", Role : Customer" );    
            else if(currentUser instanceof Seller)
            userNames.add("UserID: " + currentUser.getUserID() + ", Username: " + currentUser.getUsername() + ", Role : Seller" );    
        }
        return userNames;
    }

    public User searchuser(int userid) {
        for (User userToSearch : users) {
            if (userToSearch.getUserID() == userid) {
                return userToSearch;
            }
        }
        return null;
    }

    public void displayOrderHistory(User user) {
        ArrayList<Order> userOrders = user.getOrderHistory();

        if (userOrders != null && !userOrders.isEmpty()) {
            for (Order order : userOrders) {
                
            }
        } else {
        }
    }

   public void displayOrdersPerSeller() {
       boolean checking = false;

    for (User user : users) {
        if (user instanceof Seller) {
            Seller seller = (Seller) user;
            ArrayList<Order> sellerOrders = seller.getOrders();

            if (sellerOrders != null && !sellerOrders.isEmpty()) {

                for (Order order : sellerOrders) {
                    checking=true;
                  
                }
            } else {
            }
        }
    }
    if(!checking){}
}
   
   public Seller findSellerWithMaxOrders() {
    Seller sellerWithMaxOrders = null;
    int maxOrders = 0;

    for (User user : users) {
        if (user instanceof Seller) {
            Seller seller = (Seller) user;
            ArrayList<Order> sellerOrders = seller.getOrders();

            int numOrders = sellerOrders != null ? sellerOrders.size() : 0;

            if (numOrders > maxOrders) {
                maxOrders = numOrders;
                sellerWithMaxOrders = seller;
            }
        }
    }

    if (sellerWithMaxOrders != null) {
        return sellerWithMaxOrders;
    } else {
        return null;
    }
}
   
public Seller findSellerWithMaxRevenue() {
    Seller sellerWithMaxRevenue = null;
    double maxRevenue = 0.0;

    for (User user : users) {
        if (user instanceof Seller) {
            Seller seller = (Seller) user;
            ArrayList<Order> sellerOrders = seller.getOrders();

            double totalRevenue = 0.0;

          
            for (Order order : sellerOrders) {
                totalRevenue += order.getRevenue();
            }

            if (totalRevenue > maxRevenue) {
                maxRevenue = totalRevenue;
                sellerWithMaxRevenue = seller;
            }
        }
    }

    if (sellerWithMaxRevenue != null) {
        return sellerWithMaxRevenue;
    } else {
        return null;
    }
}
    public void displayOrdersPerCustomer() {
       boolean checking = false;
        for (User user : users) {
            if (user instanceof Customer customer) {
                ArrayList<Order> customerOrders = customer.getOrderHistory();


                if (customerOrders != null && !customerOrders.isEmpty()) {

                    for (Order order : customerOrders) {
                        checking=true;
                    }
                } else {
                }
            }
        }
            if(!checking){}
    }
    public Customer findCustomerWithMaxOrders() {
    Customer customerWithMaxOrders = null;
    int maxOrders = 0;

    for (User user : users) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            ArrayList<Order> customerOrders = customer.getOrderHistory();

            int numOrders = customerOrders != null ? customerOrders.size() : 0;

            if (numOrders > maxOrders) {
                maxOrders = numOrders;
                customerWithMaxOrders = customer;
            }
        }
    }

    if (customerWithMaxOrders != null) {
        return customerWithMaxOrders;
    } else {
        return null;
    }
}
    
    public Customer findCustomerWithMaxRevenue() {
    Customer customerWithMaxRevenue = null;
    double maxRevenue = 0.0;

    for (User user : users) {
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            ArrayList<Order> customerOrders = customer.getOrderHistory();

            double totalRevenue = 0.0;

            
            for (Order order : customerOrders) {
                totalRevenue += order.getRevenue();
            }

            if (totalRevenue > maxRevenue) {
                maxRevenue = totalRevenue;
                customerWithMaxRevenue = customer;
            }
        }
    }

    if (customerWithMaxRevenue != null) {
        return customerWithMaxRevenue;
    } else {
        return null;
    }
}

    public double calculateTotalRevenueOverPeriod(Date startDate, Date endDate) {
        double totalRevenue = 0.0;
        int orderCount = 0;

        for (User user : users) {
            if (user instanceof Seller) {
                Seller seller = (Seller) user;
                ArrayList<Order> sellerOrders = seller.getOrders();

                for (Order order : sellerOrders) {
                    Date orderDate = order.getOrderDate();

                   
                    if (orderDate.after(startDate) && orderDate.before(endDate)) {
                        totalRevenue += order.getRevenue();
                        orderCount++;
                    }
                }
            }
        }

        if (orderCount > 0) {
            double averageRevenue = totalRevenue / orderCount;
            return averageRevenue;
        } else {
            return 0.0; 
        }
    }
    
    public void saveUsersToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
          
            oos.writeObject(users);
            oos.writeInt(User.getUserIDCounter());
            oos.writeInt(Order.getNextOrderID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUsersFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
           
            users = (ArrayList<User>) ois.readObject();
            User.setUserIDCounter(ois.readInt());
            Order.setNextOrderID(ois.readInt());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
      public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void signup(String username, String password, boolean isSeller) {
       if (isUsernameExists(username)) {
        return;
    }
    User newUser;
    if (isSeller) {
        newUser = new Seller(username, password);
    } else {
        newUser = new Customer(username, password);
    }

    users.add(newUser);
    }
    
    private boolean isUsernameExists(String username) {
    for (User user : users) {
        if (user.getUsername().equals(username)) {
            return true; 
        }
    }
    return false; 
}
}


