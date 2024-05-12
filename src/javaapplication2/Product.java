
package javaapplication2;
import java.io.Serializable;

public class Product implements Serializable {
      private String productID;
    private String name;
    private  double price;
    private  int quantity;
private Seller sellingSeller;
    public Product(String productID, String name, double price, int quantity,Seller sellingSeller) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellingSeller = sellingSeller;
    }
    
    public String getCategoryFromProductId() {
    char categoryChar = getProductID().charAt(0);

    switch (categoryChar) {
        case '0':
            return "Electronics";
        case '1':
            return "Groceries";
        case '2':
            return "Toys";
  case '3':
            return "Clothes";
        default:
            return "Unknown Category";
    }
    }
    
     public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
      public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
      public Seller getSellingSeller() {
        return sellingSeller;
    }
      
      public String categorychecker(){
          String categoryname=" ";
           String categoryID = productID.substring(0, 1);
               int categoryId = Integer.parseInt(categoryID);
               if(categoryId==0){
                   categoryname="Electronics";}
               else if(categoryId==1){
                   categoryname="Groceries";}
                       else   if(categoryId==2){categoryname="Toys";}
                       else  if(categoryId==3){categoryname="Clothing";}
               return categoryname;
                   
      }

}
