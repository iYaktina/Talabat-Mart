
package javaapplication2;
import java.io.*;
import java.util.ArrayList;

public class User implements Serializable  {
      private static int userIDCounter = 0; 
    private  int userid;
    private String username;
    private String password;
 private ArrayList<Order> orderHistory;
    public User(String username, String password) {
        this.userid = userIDCounter++;
        this.username = username;
        this.password = password;
    }

    public static int getUserIDCounter() {
        return userIDCounter;
    }

    public static void setUserIDCounter(int userIDCounter) {
        User.userIDCounter = userIDCounter;
    }

     public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
public void setuser(String username){
    this.username = username;
    }
public void setpass(String password){
        this.password = password;}
public void setid( int userid ){
         this.userid = userid;
}

    public int getUserID() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
      
}
