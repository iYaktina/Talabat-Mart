
package javaapplication2;
import java.io.Serializable;
import java.util.ArrayList;
public class Cart implements Serializable {
    private int cartID;
    private ArrayList<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }
 public void addProductById(String productId, ArrayList<Product> availableProducts) {
        for (Product availableProduct : availableProducts) {
            if (availableProduct.getProductID().equals(productId)) {
                addProduct(availableProduct);
                return;
            }
        }

     
    }
 public void removeProductById(String productId) {

        for (Product cartProduct : products) {
            if (cartProduct.getProductID().equals(productId)) {
      
                removeProduct(cartProduct);
                return; 
            }
        }

    
    }
 
    public void addProduct(Product product) {
        products.add(product);
    }
    
     public void addProduct(Product product, int quantity) {

        if (quantity > 0) {
    
       
            Product cartProduct = new Product(product.getProductID(), product.getName(),
                    product.getPrice(), quantity, product.getSellingSeller());

 
            products.add(cartProduct);
        } else {
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
  public void clear() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
