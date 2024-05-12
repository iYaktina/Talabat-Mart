/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.util.converter.LocalDateStringConverter;
public class JavaApplication2 extends Application {
    public static void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // Setting header text to null
        alert.setContentText(content);
        alert.showAndWait();
    }
        @Override
    public void start(Stage stage) throws Exception {
     
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        File f = new File("Database.bin");
        Admin a=new Admin("Admin","Root");
        a.adduser(a);
        a.loadUsersFromFile("Database.bin");   
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            a.saveUsersToFile("Database.bin");
        }));
        Label user=new Label("Username : ");
        Label pass=new Label("Password : ");
        Button login=new Button("Login");
        Button register=new Button("Register");
        TextField userf=new TextField("User");
        
        PasswordField passf = new PasswordField();
        GridPane root = new GridPane();
        x(root,"talabat.jpg");
        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(10));
        root.add(user, 0, 0);
        root.add(pass, 0,1);
        root.add(userf,1,0);
        root.add(passf,1,1);
        root.add(register, 0, 4);
        root.add(login, 4,4);
        root.setAlignment(Pos.CENTER);
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        
        login.setOnAction(e->{ User loggedInUser = a.login(userf.getText(), passf.getText());
        if(loggedInUser==null){
        showAlert("Invalid","Incorrect Username or Password .",ERROR);
        }
 if (loggedInUser instanceof Admin) {
     showAlert("Welcome","Hello Admin ! ",INFORMATION);
     stage.hide();
     Stage admin=new Stage();
     GridPane admin1= new GridPane();
     x(admin1,"talabat.jpg");
     Button adm=new Button("Add User");
     Button adm1=new Button("Edit User");
     Button adm2=new Button("Remove User");
     Button adm3=new Button("List User");
     Button adm4=new Button("Order per seller");
     Button adm5=new Button("Seller with max orders");
     Button adm6=new Button("Seller with max revenue");
     Button adm7=new Button("Orders per customer");
     Button adm8=new Button("Customer with max orders");
     Button adm9=new Button("Customer with max revenue");
     Button adm10=new Button("Search for a user");
     Button logout=new Button("Logout"); 
     admin1.add(adm, 0, 0);
     admin1.add(adm1, 0, 1);
     admin1.add(adm2, 0, 2);
     admin1.add(adm3, 0, 3);
     admin1.add(adm10, 0, 4);
     admin1.add(adm4, 0, 5);
     admin1.add(adm5, 0, 6);
     admin1.add(adm6, 0, 7);
     admin1.add(adm7, 0, 8);
     admin1.add(adm8, 0, 9);
     admin1.add(adm9, 0, 10);
     admin1.add(logout, 7, 11);
     admin1.setHgap(10); admin1.setVgap(10); admin1.setPadding(new Insets(10));
     Scene admins=new Scene(admin1);
     admin.setScene(admins);
     admin.setTitle("Admin Menu");
     admin.show();
     adm.setOnAction(ee-> {
         admin.hide();
         Stage func=new Stage();
         Label name=new Label("Username : ");
         Label passw=new Label("Password : ");
         TextField ntf= new TextField();
         PasswordField ptf=new PasswordField();
         Button ubtn=new Button("Add User");
         Button boi=new Button("Back");
         boi.setOnAction(boii->{
         admin.show();
         func.close();
         });
         CheckBox cbox=new CheckBox("Seller");
         GridPane func1=new GridPane();
         x(func1,"talabat.jpg");
         func1.setVgap(10);func1.setHgap(5);func1.setPadding(new Insets(10));
         func1.setAlignment(Pos.CENTER);
         func1.add(name, 0, 0);
         func1.add(passw, 0, 1);
         func1.add(ntf, 1, 0);
         func1.add(ptf, 1, 1);
         func1.add(cbox,3,0);
         func1.add(ubtn, 3, 3);
         func1.add(boi, 1, 3);
         Scene func2=new Scene(func1);
         func.setScene(func2);
         ubtn.setOnAction(xx->{
           if(ntf.getText().isEmpty()||ptf.getText().isEmpty()){
           showAlert("Failed","All fields must be filled ...",ERROR);}
           else{
                ArrayList<User> league=Admin.getUsers();
               boolean exists=false;
               for(User legend:league){
               if(legend.getUsername().equals(ntf.getText())){
               exists=true;
               break;
               }
               }
               if(exists){
               showAlert("Failed","User with the choosen username already exists",ERROR);
               }
               else{
                showAlert("Success","User has been added Successfully !!",INFORMATION);
               a.signup(ntf.getText(),ptf.getText(),cbox.isSelected());
               }
               func.hide();
               admin.show();
           }
                     
                     });
         func.setTitle("User Registartion");
         func.show();
     });
 
     adm1.setOnAction(eh->{admin.hide();
         Stage func=new Stage();
     ListView<String> userListView = new ListView<>();
     userListView.setItems(a.getUserNames());
     Label ID = new Label("Enter User ID : ");
     TextField IDField=new TextField();
     Button conf=new Button("Confirm");
      Button boi=new Button("Back");
         boi.setOnAction(boii->{
         admin.show();
         func.close();
         });
     HBox funcc1=new HBox(15,ID,IDField);  
     VBox func1 = new VBox(15,funcc1,conf);
     x(func1,"talabat.jpg");
     func1.setPadding(new Insets(10));
     func1.setAlignment(Pos.CENTER);
     func1.getChildren().addAll(userListView,boi);
     Scene func2=new Scene(func1);
     func.setScene(func2);
     func.setTitle("User Editing");
     conf.setOnAction(aa->{
         if(IDField.getText().isEmpty()){
         showAlert("Failed","You Must Enter A Valid User ID ...",INFORMATION);
         }
         else{
             
          try{   
         int userid= Integer.valueOf(IDField.getText()); 
         final User[] Usertoedit = {null};
     Boolean found=false;
     for(User use : Admin.getUsers()){
         if(use.getUserID()==userid){
             found=true;
         Usertoedit[0]=use;
         break;
         }
        
         
     }
     if(found){
     showAlert("Success","User Found",INFORMATION);
         func.hide();
         userListView.setItems(a.getUserNames());
         Stage editing=new Stage();
         GridPane editi=new GridPane();
         x(editi,"talabat.jpg");
         Label lblu=new Label("Username");
         Label lblp=new Label("Password");
         TextField tfu=new TextField();
         TextField pfu=new TextField();
         Button editbtn=new Button("Confirm");
         
         editi.add(lblu, 0, 0);
         editi.add(lblp, 0, 1);
         editi.add(tfu, 1, 0);
         editi.add(pfu, 1, 1);
         editi.add(editbtn,5,5);
         editbtn.setOnAction(mmm->{
             if(tfu.getText().isEmpty()&&pfu.getText().isEmpty()){
             showAlert("Success","No Changes Took place",INFORMATION);
             editing.hide();
             }
             else{
                 if(tfu.getText().isEmpty()){
                          Usertoedit[0].setpass(pfu.getText());
                           editing.hide();
                 }
                 else if (pfu.getText().isEmpty()){
                        Usertoedit[0].setuser(tfu.getText());
                         editing.hide();
                 }
                 else if (!tfu.getText().isEmpty()&&!pfu.getText().isEmpty()){
                  Usertoedit[0].setuser(tfu.getText());
                  Usertoedit[0].setpass(pfu.getText());
                  editing.hide();
                 }
             }
         
         });
         Scene editin= new Scene(editi);
         editing.setScene(editin);
         editing.show();}
     
      else{
             showAlert("Failed","No User Exists with Such ID ... ",ERROR);
             
         
         }
          }
          catch(Exception mma){
            showAlert("Integer", "You Must enter an Integer" ,ERROR);
          }
   
         }
     
     });
     func.show();

     });
     
     
     
     adm2.setOnAction(eh->{admin.hide();
         Stage func=new Stage();
     ListView<String> userListView = new ListView<>();
     userListView.setPrefWidth(325);
     userListView.setItems(a.getUserNames());
     Label lbll = new Label("Enter ID : ");
     Button bt1=new Button("Remove User");
     Button boi=new Button("Back");
         boi.setOnAction(boii->{
         admin.show();
         func.close();
         });
     TextField tff=new TextField();
     HBox func3=new HBox(15,lbll,tff);
     VBox func1 = new VBox(15,func3,userListView);
     x(func1,"talabat.jpg");
     func1.getChildren().addAll(bt1,boi);
     func1.setPadding(new Insets(10));
     bt1.setOnAction(abc->{ 
         try{
         
          int userid = Integer.valueOf(tff.getText());
          Boolean found = false;
          for(User use : Admin.getUsers()){
         if(use.getUserID()==userid){
             found=true;
         break;
         }
     }
         if(found){
             showAlert("Success","User Removed Successfully !!",INFORMATION);
             a.removeuser(userid);
             userListView.setItems(a.getUserNames());
         }
         else{
         showAlert("Error","No User with such ID Exists ...",ERROR);
         }
         }
         catch(Exception ofleg){
             showAlert("ERROR","Input Must be Integer",ERROR);
         }
        
     });
     Scene func2=new Scene(func1);
     func.setScene(func2);
     func.setTitle("User Removal");
     func.show();
     });
     
     
     
     adm3.setOnAction(eh->{
         admin.hide();
         Stage func=new Stage();
  ListView<String> userListView = new ListView<>();
  userListView.setPrefWidth(325);
     userListView.setItems(a.getUserNames());
     Button backoff=new Button("Go Back");
     VBox func1=new VBox(15,userListView,backoff);
     x(func1,"talabat.jpg");
     func1.setPadding(new Insets(10));
     Scene func2=new Scene(func1);
     func.setScene(func2);
     func.setTitle("User OverView");
     func.show();
             backoff.setOnAction(Audi->{
             func.close();
             admin.show();
             });
             });
     
     
     
     adm4.setOnAction(eh->{admin.hide();
         Stage func=new Stage();
     ListView<String> userListView = new ListView<>();
     userListView.setPrefWidth(325);
     ArrayList<User> uu=new ArrayList<>();
     ObservableList<String> ss = FXCollections.observableArrayList();
     uu=Admin.getUsers();
    
     for(User u : uu){
         if(u instanceof Seller){ 
             int count=0;
             Seller sell= (Seller) u;
                 ss.add("Seller ID : " + u.getUserID());
              ArrayList<Order> sellerOrders = sell.getOrders();
             for (Order order : sellerOrders) {
                   ss.add("  Order ID: " + order.getOrderID() +
                        ", Status: " + order.getStatus());
                }
       }
            
     }
    
     userListView.setItems(ss);
      Button backoff=new Button("Go Back");
     VBox func1=new VBox(15,userListView,backoff);
     x(func1,"talabat.jpg");
     backoff.setOnAction(Audi->{
             func.close();
             admin.show();
             });
      func1.setPadding(new Insets(10));
     Scene func2=new Scene(func1);
     func.setScene(func2);
     func.setTitle("Order Mangement");
     func.show();});
     
     
     
     adm5.setOnAction(eh->{
         Seller ss=a.findSellerWithMaxOrders();
         if(ss!=null){
         showAlert("Success","Seller with Maximum Orders : "+ss.getUserID() +"\n Seller Username : "+ss.getUsername() + "\n Seller Number of Orders : "+ss.getOrders().size(),INFORMATION);
                 }
         else{
         showAlert("Failed","No Sellers Found",ERROR);
         }

     });
     
     
     
     adm6.setOnAction(eh->{
     Seller ss=a.findSellerWithMaxRevenue();
         if(ss!=null){
               ArrayList<Order> sellerOrders = ss.getOrders();

            double totalRevenue = 0.0;

          
            for (Order order : sellerOrders) {
                totalRevenue += order.getRevenue();
            }
         showAlert("Success","Seller with Maximum Revenue : "+ss.getUserID() +"\n Seller Username : "+ss.getUsername() + "\n Seller Maximum Revenue : "+totalRevenue,INFORMATION);
                 }
         else{
         showAlert("Failed","No Sellers Found",ERROR);
         }

     });
     
     
     
     adm7.setOnAction(eh->{
         admin.hide();
         Stage func=new Stage();
     ListView<String> userListView = new ListView<>();
     userListView.setPrefWidth(325);
     ArrayList<User> uu=new ArrayList<>();
     ObservableList<String> ss = FXCollections.observableArrayList();
     uu=Admin.getUsers();
     for(User u : uu){
         if(u instanceof Customer){ 
             Customer sell= (Customer) u;
                 ss.add("Customer ID : " + u.getUserID());
              ArrayList<Order> customerOrders = sell.getOrderHistory();
             for (Order order : customerOrders) {
                   ss.add("  Order ID: " + order.getOrderID() +
                        ", Status: " + order.getStatus()+"\n");
                }
            }
     }
     userListView.setItems(ss);
           Button backoff=new Button("Go Back");
     VBox func1=new VBox(15,userListView,backoff);
     x(func1,"talabat.jpg");
     func1.setPadding(new Insets(10));
     Scene func2=new Scene(func1);    
     backoff.setOnAction(Audi->{
             func.close();
             admin.show();
             });
     func.setTitle("Customer Management");
     func.setScene(func2);
     func.show();});
     
     
     
     adm8.setOnAction(eh->{Customer ss=a.findCustomerWithMaxOrders();
         if(ss!=null){
          
         showAlert("Success","Customer with Maximum Revenue : "+ss.getUserID() +"\n Seller Username : "+ss.getUsername() + "\n Seller Number of Orders : "+ss.getOrderHistory().size(),INFORMATION);
                 }
         else{
         showAlert("Failed","No Customers Found",ERROR);
         }});
     
     
     
     adm9.setOnAction(eh->{Customer ss=a.findCustomerWithMaxRevenue();
         if(ss!=null){
                 ArrayList<Order> customerOrders = ss.getOrderHistory();

            double totalRevenue = 0.0;

          
            for (Order order : customerOrders) {
                totalRevenue += order.getRevenue();
            }
         showAlert("Success","Customer with Maximum Revenue : "+ss.getUserID() +"\n Customer Username : "+ss.getUsername() + "\n Customer Number of Orders : "+totalRevenue,INFORMATION);
                 }
         else{
         showAlert("Failed","No Customers Found",ERROR);
         }});
     
     adm10.setOnAction(eh->{
      admin.hide();
         Stage func=new Stage();
         ObservableList<String> Userchoosen=FXCollections.observableArrayList();
     ListView<String> userListView = new ListView<>();
     userListView.setPrefWidth(325);
     Label lbll = new Label("Enter User Information : ");
     Button bt1=new Button("Find User");
     Button boi=new Button("Back");
         boi.setOnAction(boii->{
         admin.show();
         func.close();
         });
     TextField tff=new TextField();
     HBox func3=new HBox(15,lbll,tff);
     VBox func1 = new VBox(15,func3,userListView);
     x(func1,"talabat.jpg");
     func1.getChildren().addAll(bt1,boi);
     func1.setPadding(new Insets(10));
     bt1.setOnAction(abc->{ 
     
         
          User uau=null;
          Boolean found = false;
          for(User use : Admin.getUsers()){
         if(tff.getText().equals(String.valueOf(use.getUserID()))||use.getUsername().equals(tff.getText())){
             found=true;
             uau=use;
         break;
         }
     }
         if(found){
             Userchoosen.clear();
             showAlert("Success","User Found Successfully !!",INFORMATION);
             Userchoosen.add("User Name : "+uau.getUsername() + ", User ID : "+uau.getUserID());
             userListView.setItems(Userchoosen);
         }
         else{
         showAlert("Error","No User with such Information Exists ...",ERROR);
         }
        
        
     });
     Scene func2=new Scene(func1);
     func.setScene(func2);
     func.setTitle("User Searching");
     func.show();
     });
     
     
     logout.setOnAction(eh->{admin.close();
     userf.clear();
     passf.clear();
     stage.show();});
 }

else if (loggedInUser instanceof Seller) {     showAlert("Welcome","Hello "+ loggedInUser.getUsername()+"! ",INFORMATION);
stage.hide();
 Stage seller = new Stage();
 GridPane sell= new GridPane();
 x(sell,"talabat.jpg");
 Button sales=new Button("Add Product");
 Button sales1 = new Button("Display Orders");
 Button sales2=new Button("Display Order History");
 Button sales3=new Button("Number of Pieces Sold Over a Period");
 Button sales4=new Button("Best Seller Product Over a Period");
 Button sales5=new Button("Most Revenue Product Over a Period");
 Button sales6=new Button("Change Order Status to Shipped");
 Button sales7=new Button("Calculate Total Revenue Over a Period");
 Button sales8=new Button("Edit Products");
 Button sales9=new Button("List Products");
 Button sales10=new Button("Remove a Product");
 Button sales11=new Button("Search for a Product");
 Button logout=new Button("Logout");
 sell.add(sales, 0, 0); 
 sell.add(sales1, 0, 1);
 sell.add(sales2, 0, 2);
 sell.add(sales3, 0, 3);
 sell.add(sales4, 0, 4);
 sell.add(sales5, 0, 5);
 sell.add(sales6, 0, 6);
 sell.add(sales7, 0, 7);
 sell.add(sales8, 0, 8);
 sell.add(sales9, 0, 9);
 sell.add(sales10, 0, 10);
 sell.add(sales11, 0, 11);
 sell.add(logout, 7, 11);
 sell.setVgap(10); sell.setHgap(10); sell.setPadding(new Insets(10));
 Scene sellshow=new Scene(sell);
 seller.setScene(sellshow);
 seller.setTitle("Seller Menu");
 seller.show();
 
 
 
 sales.setOnAction(act->{seller.hide();
     Stage x =new Stage();
         GridPane xx = new GridPane();
         x(xx,"talabat.jpg");
         Label guiding=new Label("Which Category does your product lay underneath : \n"+
                 " Electronics begins with 0 \n"+
                 " Groceries begins with 1 \n"    +
                 " Toys begins with 2 \n"         +
                 " Clothing begins with 3 \n"      );
         
         Label x1 = new Label("Product ID ");
         Label x2 = new Label("Product Name ");
         Label x3 = new Label("Product Price ");
         Label x4 = new Label("Product Quantity ");
         TextField tx1=new TextField();
         TextField tx2=new TextField();
         TextField tx3=new TextField();
         TextField tx4=new TextField();
         Button bx1=new Button("Add Product");
         Button bx2=new Button("Go Back");
         xx.add(guiding, 10, 0);
         xx.add(x1, 0, 0);
         xx.add(x2, 0, 1);
         xx.add(x3, 0, 2);
         xx.add(x4, 0, 3);
         xx.add(tx1, 1, 0);
         xx.add(tx2, 1, 1);
         xx.add(tx3, 1, 2);
         xx.add(tx4, 1, 3);
         xx.add(bx1, 1, 5);
         xx.add(bx2, 10, 5);
         xx.setAlignment(Pos.CENTER);
         xx.setHgap(10); xx.setVgap(10); xx.setPadding(new Insets(10));
         Scene xx1=new Scene(xx);
         x.setScene(xx1);
         x.setTitle("Product Mangement");
         x.show();
         
         
         
         bx1.setOnAction(ax->{
         if(tx1.getText().isEmpty()||tx2.getText().isEmpty()||tx3.getText().isEmpty()||tx4.getText().isEmpty()){
         showAlert("Failed","All Fields must be filled ...",ERROR);
         }
         else{
             try{Integer.valueOf(tx1.getText());
         Product prod = new Product(tx1.getText(),tx2.getText(),Double.valueOf(tx3.getText()),Integer.valueOf(tx4.getText()),(Seller) loggedInUser);
         ((Seller) loggedInUser).addProduct(prod);
         showAlert("Success","Product Has Been Added Successfully !! \n Product added to category " + prod.categorychecker()  ,INFORMATION);
             }
             catch(Exception sion){
             showAlert("ERROR","Invalid Data Input ....",ERROR);}
         }
         });
         
         bx2.setOnAction(ax1->{
         x.close();
         seller.show();
         
         });

         });
 sales1.setOnAction(act->{seller.hide();
 Stage x = new Stage();
 ListView<String> orderListView = new ListView<>();
  orderListView.setPrefWidth(325);
     orderListView.setItems(((Seller) loggedInUser).getPendingOrders());
     Button backoff=new Button("Go Back");
     backoff.setOnAction(yepi->{seller.show();
         
         x.close();});
     VBox x1 = new VBox(15,orderListView,backoff);
     x(x1,"talabat.jpg");
     x1.setPadding(new Insets(10));
     Scene xx=new Scene(x1);
     x.setScene(xx);
     x.setTitle("Pending Orders");
     x.show();
 
 });
 
 
 sales2.setOnAction(act->{seller.hide();
 Stage x = new Stage();
 ListView<String> orderListView = new ListView<>();
  orderListView.setPrefWidth(325);
     orderListView.setItems(((Seller) loggedInUser).getCompleteOrders());
     Button backoff=new Button("Go Back");
     backoff.setOnAction(yepi->{seller.show();
         
         x.close();});
     VBox x1 = new VBox(15,orderListView,backoff);
     x(x1,"talabat.jpg");
          x1.setPadding(new Insets(10));
     Scene xx=new Scene(x1);
     x.setScene(xx);
     x.setTitle("Shipped Orders");
     x.show();
 });
 
 
 sales3.setOnAction(act->{ seller.hide();
     Stage x = new Stage();
     GridPane x1 = new GridPane();
     x(x1,"talabat.jpg");
     Label lb=new Label("Starting From : ");
     Label lb1 = new Label(" Until : ");
     Button bx=new Button("Show Product");
      Button backoff=new Button("Go Back");
     backoff.setOnAction(yepi->{seller.show();
         
         x.close();});
       DatePicker dateStr = new DatePicker();
        dateStr.setPromptText("yyyy-MM-dd");
        dateStr.setConverter(new LocalDateStringConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd"), null));
        DatePicker dateEnd = new DatePicker();
        dateEnd.setPromptText("yyyy-MM-dd");
        dateEnd.setConverter(new LocalDateStringConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd"), null));
        dateStr.setEditable(false);
        dateEnd.setEditable(false);
       x1.add(lb, 0, 0);
       x1.add(dateStr, 1, 0);
       x1.add(lb1, 2, 0);
       x1.add(dateEnd, 3, 0);
       x1.add(bx, 4, 1);
       x1.add(backoff, 2, 1);
       x1.setAlignment(Pos.CENTER);
       x1.setVgap(10); x1.setHgap(10); x1.setPadding(new Insets(10));
       bx.setOnAction(mm->{
       
                    if(dateStr.getValue()==null||dateEnd.getValue()==null){
                    showAlert("Failed","You must fill in all the fields ",ERROR);
                    }
                    else{
                            String startdate=dateStr.getValue().toString();
                         String enddate=dateEnd.getValue().toString();
                           Date startDate =null;
                            Date endDate=null;
                        try {
                            endDate = dateFormat.parse(enddate);
                            startDate= dateFormat.parse(startdate);
                        } catch (ParseException ex) {
                          
                        }
                    if(((Seller) loggedInUser).getNumberOfPiecesSoldOverPeriod(startDate, endDate)!= 0){
    showAlert("Success","Number of products sold over the period selected is : " +((Seller) loggedInUser).getNumberOfPiecesSoldOverPeriod(startDate, endDate),INFORMATION);
    x.close();
    seller.show();
         }
     else{
        showAlert("Success","No product sold  within the specified period.",INFORMATION);
}
                    }
       });
     Scene xx= new Scene(x1,700,100);
     x.setTitle("Quantity & Period");
     x.setScene(xx);
     x.show();
 
 });
 sales4.setOnAction(act->{seller.hide();
  Stage x = new Stage();
     GridPane x1 = new GridPane();
     x(x1,"talabat.jpg");
     Label lb=new Label("Starting From : ");
     Label lb1 = new Label(" Until : ");
     Button bx=new Button("Show Product");
      Button backoff=new Button("Go Back");
     backoff.setOnAction(yepi->{seller.show();
         
         x.close();});
       DatePicker dateStr = new DatePicker();
        dateStr.setPromptText("yyyy-MM-dd");
        dateStr.setConverter(new LocalDateStringConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd"), null));
       DatePicker dateEnd = new DatePicker();
        dateEnd.setPromptText("yyyy-MM-dd");
        dateEnd.setConverter(new LocalDateStringConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd"), null));
         dateStr.setEditable(false);
        dateEnd.setEditable(false);
       x1.add(lb, 0, 0);
       x1.add(dateStr, 1, 0);
       x1.add(lb1, 2, 0);
       x1.add(dateEnd, 3, 0);
       x1.add(bx, 4, 1);
       x1.add(backoff,2,1);
       x1.setAlignment(Pos.CENTER);
       x1.setVgap(10); x1.setHgap(10); x1.setPadding(new Insets(10));
       bx.setOnAction(mm->{
            
                    if(dateStr.getValue()==null||dateEnd.getValue()==null){
                    showAlert("Failed","You must fill in all the fields ",ERROR);
                    }
                    else{
                                   String startdate=dateStr.getValue().toString();
                         String enddate=dateEnd.getValue().toString();
                           Date startDate =null;
                            Date endDate=null;
                        try {
                            endDate = dateFormat.parse(enddate);
                            startDate= dateFormat.parse(startdate);
                        } catch (ParseException ex) {
                            
                        }
    if(((Seller) loggedInUser).getBestSellerProductOverPeriod(startDate, endDate)!= null){
    showAlert("Success","Best Selling Product Over The Selected Period Of Time Is  : " +((Seller) loggedInUser).getBestSellerProductOverPeriod(startDate, endDate).getName(),INFORMATION);
         }
     else{
        showAlert("Success","No best-selling product found within the specified period.",INFORMATION);
}
                        
                    
                    }
       });
     Scene xx= new Scene(x1,700,100);
     x.setTitle("#1 Sales & Period");
     x.setScene(xx);
     x.show();
 });
 sales5.setOnAction(act->{seller.hide();
   Stage x = new Stage();
     GridPane x1 = new GridPane();
     x(x1,"talabat.jpg");
     Label lb=new Label("Starting From : ");
     Label lb1 = new Label(" Until : ");
     Button bx=new Button("Show Product");
      Button backoff=new Button("Go Back");
     backoff.setOnAction(yepi->{seller.show();
         
         x.close();});
       DatePicker dateStr = new DatePicker();
        dateStr.setPromptText("yyyy-MM-dd");
        dateStr.setConverter(new LocalDateStringConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd"), null));
       DatePicker dateEnd = new DatePicker();
        dateEnd.setPromptText("yyyy-MM-dd");
        dateEnd.setConverter(new LocalDateStringConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd"), null));
         dateStr.setEditable(false);
        dateEnd.setEditable(false);
       x1.add(lb, 0, 0);
       x1.add(dateStr, 1, 0);
       x1.add(lb1, 2, 0);
       x1.add(dateEnd, 3, 0);
       x1.add(bx, 4, 1);
       x1.add(backoff,2,1);
       x1.setAlignment(Pos.CENTER);
       x1.setVgap(10); x1.setHgap(10); x1.setPadding(new Insets(10));
       bx.setOnAction(mm->{
            
                    if(dateStr.getValue()==null||dateEnd.getValue()==null){
                    showAlert("Failed","You must fill in all the fields ",ERROR);
                    }
                    else{
                                String startdate=dateStr.getValue().toString();
                         String enddate=dateEnd.getValue().toString();
                           Date startDate =null;
                            Date endDate=null;
                        try {
                            endDate = dateFormat.parse(enddate);
                            startDate= dateFormat.parse(startdate);
                        } catch (ParseException ex) {
                            
                        }
    if(((Seller) loggedInUser).getMostRevenueProductOverPeriod(startDate, endDate)!= null){
    showAlert("Success","Most Revenue Product Over The Selected Period Of Time Is  : " +((Seller) loggedInUser).getMostRevenueProductOverPeriod(startDate, endDate).getName(),INFORMATION);
         }
     else{
        showAlert("Success","No Most-Revenue product found within the specified period.",INFORMATION);
}
                        
                    
                    }
       });
     Scene xx= new Scene(x1,700,100);
     x.setTitle("Most Revenue & Period");
     x.setScene(xx);
     x.show();
 });
 sales6.setOnAction(act->{seller.hide();
 Stage x = new Stage();
 ListView<String> orderListView = new ListView<>();
  orderListView.setPrefWidth(325);
     orderListView.setItems(((Seller) loggedInUser).getPendingOrders());
     Button bx=new Button("Confirm");
     Button bx1=new Button("Go Back");
     Label lx=new Label("Enter Order ID : ");
     TextField tx=new TextField();
     HBox hx=new HBox(15,lx,tx);
     VBox x1 = new VBox(15,hx,orderListView);
     HBox hx1=new HBox(15,bx,bx1);
     x1.getChildren().add(hx1);
     x(x1,"talabat.jpg");
     x1.setPadding(new Insets(10));
     Scene xx=new Scene(x1);
     x.setScene(xx);
     x.setTitle("Orders Management");
     bx.setOnAction(xl1->{
         try{Integer.valueOf(tx.getText());
     ArrayList<Order> orders=((Seller) loggedInUser).getOrders();
     Order tochange=null;
     Boolean found = false;
     for(Order o : orders){
     if (Integer.valueOf(tx.getText())==o.getOrderID()&&"Pending".equals(o.getStatus())){
     found = true;
     tochange=o;
     break;
     }
     }
        if(found){showAlert("Success","Order Status Changed To Shipped ...",INFORMATION);
        tochange.changeStatus("Shipped");
        orderListView.setItems(((Seller) loggedInUser).getPendingOrders());
        }
        else{showAlert("Failed","No Order With Such An ID ...",ERROR);}
        
         }
         catch(Exception jungle){
         showAlert("ERROR","Invalid Data Input ...",ERROR);
         }
     });
     bx1.setOnAction(xl->{
         x.close();
         seller.show();
     
     });
     x.show();
 });
 sales7.setOnAction(act->{seller.hide();
     Stage x = new Stage();
     GridPane x1 = new GridPane();
     x(x1,"talabat.jpg");
     Label lb=new Label("Starting From : ");
     Label lb1 = new Label(" Until : ");
     Button bx=new Button("Show Product");
      Button backoff=new Button("Go Back");
     backoff.setOnAction(yepi->{seller.show();
         
         x.close();});
       DatePicker dateStr = new DatePicker();
        dateStr.setPromptText("yyyy-MM-dd");
        dateStr.setConverter(new LocalDateStringConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd"), null));
       DatePicker dateEnd = new DatePicker();
        dateEnd.setPromptText("yyyy-MM-dd");
        dateEnd.setConverter(new LocalDateStringConverter(DateTimeFormatter.ofPattern("yyyy-MM-dd"), null));
         dateStr.setEditable(false);
        dateEnd.setEditable(false);
       x1.add(lb, 0, 0);
       x1.add(dateStr, 1, 0);
       x1.add(lb1, 2, 0);
       x1.add(dateEnd, 3, 0);
       x1.add(bx, 4, 1);
       x1.add(backoff,2,1);
       x1.setAlignment(Pos.CENTER);
       x1.setVgap(10); x1.setHgap(10); x1.setPadding(new Insets(10));
       bx.setOnAction(mm->{
                    if(dateStr.getValue()==null||dateEnd.getValue()==null){
                    showAlert("Failed","You must fill in all the fields ",ERROR);
                    }
                    else{
                         String startdate=dateStr.getValue().toString();
                         String enddate=dateEnd.getValue().toString();
                           Date startDate =null;
                            Date endDate=null;
                        try {
                            endDate = dateFormat.parse(enddate);
                            startDate= dateFormat.parse(startdate);
                        } catch (ParseException ex) {
                            
                        }
    if(((Seller) loggedInUser).calculateTotalRevenueOverPeriod(startDate, endDate)!= 0.0){
    showAlert("Success","Total Revenue Product Over The Selected Period Of Time Is  : " +((Seller) loggedInUser).calculateTotalRevenueOverPeriod(startDate, endDate),INFORMATION);
         }
     else{
        showAlert("Success","No Products were sold found within the specified period.",INFORMATION);
        }
    }
 
        });
       Scene xx=new Scene(x1,700,100);
       x.setScene(xx);
       x.setTitle("Total Revenue & Period");
       x.show();
 
 });
 sales8.setOnAction(act->{
     seller.hide();
     Stage x = new Stage();
     ListView<String> userListView = new ListView<>();
     userListView.setPrefWidth(325);
     userListView.setItems(((Seller) loggedInUser).getObservableProducts());
     Label lbll = new Label("Enter Product ID : ");
     Button bt1=new Button("Edit Product");
     Button backoff=new Button("Go Back");
     backoff.setOnAction(yepi->{seller.show();
         
         x.close();});
     TextField tff=new TextField();
     HBox func3=new HBox(15,lbll,tff);
     VBox func1 = new VBox(15,func3,userListView);
     x(func1,"talabat.jpg");
     func1.getChildren().addAll(bt1,backoff);
     func1.setPadding(new Insets(10));
     Scene xx=new Scene(func1);
     x.setScene(xx);
     x.setTitle("Product Editing");
     x.show();
     bt1.setOnAction(lx->{try{Integer.valueOf(tff.getText());
     ArrayList<Product> prods=new ArrayList<>();
      final Product[] pp = {null};
     prods=((Seller) loggedInUser).getProducts();
     boolean found = false;
     for(Product aa: prods){
     if(tff.getText().equals(aa.getProductID())){
      found=true;
      pp[0]=aa;
      break;
     }
     } 
     if(found){
         showAlert("Success","Product has been found !",INFORMATION);
         Stage editin=new Stage();
         editin.setTitle("Editing Window");
         VBox edit=new VBox(15);
         x(edit,"talabat.jpg");
         Button btn=new Button("Update Product's Name");
         Button btn1=new Button("Update Product's Price");
         Button btn2=new Button("Update Product's Quantity");
         Button btn3=new Button("Update Product's ID");
         edit.getChildren().addAll(btn,btn1,btn2,btn3);
         edit.setPadding(new Insets(10));
         Scene edited=new Scene(edit,300,175);
         editin.setScene(edited);
         editin.show();
         btn.setOnAction(ll->{
         TextInputDialog tid=new TextInputDialog();
         tid.setHeaderText(null);
         tid.setTitle("Product Updates");
         tid.setContentText("Enter new product name : ");
         
         Optional<String> ans=tid.showAndWait();
         if(ans.isPresent()){
             ans.ifPresent(in->{pp[0].setName(in);
                          userListView.setItems(((Seller) loggedInUser).getObservableProducts());
});
         }
         });
         
         btn1.setOnAction(ll->{
          TextInputDialog tid=new TextInputDialog();
         tid.setHeaderText(null);
         tid.setTitle("Product Updates");
         tid.setContentText("Enter new product price : ");
         
         Optional<String> ans=tid.showAndWait();
         if(ans.isPresent()){
             ans.ifPresent(in->{
                 try{Double.valueOf(in);
                 pp[0].setPrice(Double.valueOf(in));
                                 userListView.setItems(((Seller) loggedInUser).getObservableProducts());

                 }
                 catch(Exception dam){
                 showAlert("ERROR","Invalid Data Input ...",ERROR);}
             
             });
         }
         });
         
         btn2.setOnAction(ll->{
          TextInputDialog tid=new TextInputDialog();
         tid.setHeaderText(null);
         tid.setTitle("Product Updates");
         tid.setContentText("Enter new product Quantity : ");
         
         Optional<String> ans=tid.showAndWait();
         if(ans.isPresent()){
             ans.ifPresent(in->{
                 try{Integer.valueOf(in);
                 pp[0].setQuantity(Integer.valueOf(in));
                                  userListView.setItems(((Seller) loggedInUser).getObservableProducts());

                 }
                 catch(Exception dam){
                 showAlert("ERROR","Invalid Data Input ...",ERROR);}
                 });
         }
         });
         
         btn3.setOnAction(ll->{
          TextInputDialog tid=new TextInputDialog();
         tid.setHeaderText(null);
         tid.setTitle("Product Updates");
         tid.setContentText("Enter new product ID : ");
         
         Optional<String> ans=tid.showAndWait();
         if(ans.isPresent()){
             ans.ifPresent(in->{
                  try{Integer.valueOf(in);
                 pp[0].setProductID(in);
                                   userListView.setItems(((Seller) loggedInUser).getObservableProducts());

                 }
                 catch(Exception dam){
                 showAlert("ERROR","Invalid Data Input ...",ERROR);}
                });
         }
         });
         
        }
     else{
     showAlert("Failed","No product with such ID ",ERROR);
     
     }
     }
     catch(Exception eww){showAlert("ERROR","Invalid Data Input",ERROR);}
     });
     
 
 });
 sales9.setOnAction(act->{
     seller.hide();
     Stage x = new Stage();
  ListView<String> productListView = new ListView<>();
     productListView.setPrefWidth(325);
     productListView.setItems(((Seller) loggedInUser).getObservableProducts());
      Button backoff=new Button("Go Back");
     backoff.setOnAction(yepi->{seller.show();
         
         x.close();});
     VBox vb=new VBox(15,productListView,backoff);
     x(vb,"talabat.jpg");
     vb.setPadding(new Insets(10));
     Scene xx = new Scene(vb);
     x.setScene(xx);
     x.setTitle("Products List");
     x.show();
 
 });
 sales10.setOnAction(act->{seller.hide();
 Stage x = new Stage();
  ListView<String> productListView = new ListView<>();
     productListView.setPrefWidth(325);
     productListView.setItems(((Seller) loggedInUser).getObservableProducts());
     Button bb=new Button("Remove Product ");
      Button backoff=new Button("Go Back");
     backoff.setOnAction(yepi->{seller.show();
         
         x.close();});
     Label lb1=new Label("Enter Product ID : ");
     TextField Tff=new TextField();
     HBox hb=new HBox(15,lb1,Tff);
     VBox vb=new VBox(15,productListView,hb);
     x(vb,"talabat.jpg");
     vb.getChildren().addAll(bb,backoff);
     vb.setAlignment(Pos.CENTER);
     vb.setPadding(new Insets(10));
     bb.setOnAction(amg->{
         ArrayList<Product> Gclass=((Seller) loggedInUser).getProducts();
         Product Brabus=null;
         boolean found=false;
         for(Product p:Gclass){
         if(p.getProductID().equals(Tff.getText())){
         found=true;
         Brabus=p;
         break;}
         }
         if(found){
         showAlert("Success","Product has been removed Successfully !",INFORMATION);
         ((Seller) loggedInUser).deleteproduct(Brabus);
                  productListView.setItems(((Seller) loggedInUser).getObservableProducts());
         }
         else{
         showAlert("Failed","No product with such ID found ",ERROR);
         }
     });
     Scene xx = new Scene(vb);
     x.setScene(xx);
     x.setTitle("Products Removal");
 x.show();
 
 });
 sales11.setOnAction(act->{
  seller.hide();
         Stage func=new Stage();
         ObservableList<String> Prodchoosen=FXCollections.observableArrayList();
     ListView<String> userListView = new ListView<>();
     userListView.setPrefWidth(325);
     Label lbll = new Label("Enter Product Information : ");
     Button bt1=new Button("Find Product");
     Button boi=new Button("Back");
         boi.setOnAction(boii->{
         seller.show();
         func.close();
         });
     TextField tff=new TextField();
     HBox func3=new HBox(15,lbll,tff);
     VBox func1 = new VBox(15,func3,userListView);
     x(func1,"talabat.jpg");
     func1.getChildren().addAll(bt1,boi);
     func1.setPadding(new Insets(10));
     bt1.setOnAction(abc->{ 
     
         
          Product uau=null;
          Boolean found = false;
          for(Product use : ((Seller)loggedInUser).getProducts()){
         if(tff.getText().equals(String.valueOf(use.getProductID()))||use.getName().equals(tff.getText())){
             found=true;
             uau=use;
         break;
         }
     }
         if(found){
             Prodchoosen.clear();
             showAlert("Success","Product Found Successfully !!",INFORMATION);
             Prodchoosen.add("Product Name : "+uau.getName()+ ", Product ID : "+uau.getProductID()+"\nProduct Quantity : "+uau.getQuantity() +"Product Price : "+uau.getPrice());
             userListView.setItems(Prodchoosen);
         }
         else{
         showAlert("Error","No Product with such Information Exists ...",ERROR);
         }
        
        
     });
     Scene func2=new Scene(func1);
     func.setScene(func2);
     func.setTitle("Product Searching");
     func.show();
 
 });
 
 
 logout.setOnAction(act->{seller.close();
  userf.clear();
     passf.clear();
     stage.show();});
 
}

 else if (loggedInUser instanceof Customer) {     
     showAlert("Welcome","Hello "+ loggedInUser.getUsername()+"! ",INFORMATION);
     stage.hide();
    Stage customer=new Stage();
    GridPane cust=new GridPane();
    x(cust,"talabat.jpg");
    cust.setHgap(10); cust.setVgap(10); cust.setPadding(new Insets(10));
    Button btc=new Button( "Add to Cart");
    Button btc1=new Button("Remove from Cart");
    Button btc2=new Button("Add Address");
    Button btc3=new Button("View Payment and Confirm Cart");
    Button btc4=new Button("Cancel Cart");
    Button btc5=new Button("Track Order Status");
    Button btc6=new Button("Rate Orders");
    Button logout=new Button("Logout");
    cust.add(btc, 0, 0);
    cust.add(btc1, 0, 1);
    cust.add(btc2, 0, 2);
    cust.add(btc3, 0, 3);
    cust.add(btc4, 0, 4);
    cust.add(btc5, 0, 5);
    cust.add(btc6, 0, 6);
    cust.add(logout, 7, 7);
    Scene scc=new Scene(cust);
    customer.setScene(scc);
    customer.setTitle("Customer Menu");
    customer.show();
    btc.setOnAction(cc->{ customer.hide();
    Stage shop=new Stage();
    GridPane shopping=new GridPane();
    x(shopping,"talabat.jpg");
    shopping.setVgap(10); shopping.setHgap(10); shopping.setPadding(new Insets(10));
    Button cat=new Button("Groceries");
    Button cat1=new Button("Toys");
    Button cat2=new Button("Clothes");
    Button cat3=new Button("Electronics");
    Button backoff=new Button("Back");
    backoff.setOnAction(ice->{customer.show();
    shop.close();});
    cat.setPrefSize(150, 50);
    cat1.setPrefSize(150, 50);
    cat2.setPrefSize(150, 50);
    cat3.setPrefSize(150, 50);
    backoff.setPrefSize(300,50);
    shopping.add(cat, 0, 0);
    shopping.add(cat1, 0, 1);
    shopping.add(cat2, 1, 0);
    shopping.add(cat3, 1, 1);
    shopping.add(backoff, 1, 2);
    backoff.setAlignment(Pos.CENTER);
    Scene shopped=new Scene(shopping,350,120);
    shop.setScene(shopped);
    shop.setTitle("Shopping Menu");
    shop.show();
    cat.setOnAction(shopy->{  
   shop.hide();
         ObservableList<String> prods = FXCollections.observableArrayList();
            for (User user1 : Admin.users) {
        if (user1 instanceof Seller) {
            ArrayList<Product> prod=((Seller)user1).getProducts();
         for(Product grop:prod){
         if(grop.getProductID().startsWith("1")){
            if(grop.getQuantity()<=0){
              prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +" Product Status : Out of Stock \n----------------------------------------" );
             }
             else{
          prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +" Product Status : In Stock \n----------------------------------------" );
             }
         }
         }
        }
            }
            if(prods.isEmpty()){
            showAlert("Failed","No Products Are Avaliable within the Selected Category",ERROR);
            }
            else{
         ListView<String> productListView = new ListView<>();
     productListView.setPrefWidth(500);
     productListView.setItems(prods);
    Stage groc=new Stage();
    Label en=new Label("Enter Product ID : ");
    TextField ent=new TextField();
    Button ea=new Button("Add To Cart");
         Button backoff1=new Button("Back");
    backoff1.setOnAction(ice->{groc.close();shop.show();
    });
    HBox ee=new HBox(15,en,ent);
    VBox gro=new VBox(15,ee,productListView);
    x(gro,"talabat.jpg");
    gro.setPadding(new Insets(10));
    gro.getChildren().addAll(ea,backoff1);
    Scene go=new Scene(gro);
    groc.setScene(go);
    groc.setTitle("Grocery Products");
    groc.show();
    ea.setOnAction(mf->{
        final Product[] fo = {null};
        
        boolean found=false;
         for (User user1 : Admin.users) {
        if (user1 instanceof Seller) {
            ArrayList<Product> prod=((Seller)user1).getProducts();
         for(Product grop:prod){
         if(grop.getProductID().equals(ent.getText())){
             found=true;
             fo[0]=grop;
         }
         }
        }
         }
         if(found){  
                    List<Integer> numberList = new ArrayList<>();
        for (int i = 1; i <= fo[0].getQuantity(); i++) {
            numberList.add(i);
        }

        
        if(fo[0].getQuantity()>0){ChoiceDialog<Integer> cd = new ChoiceDialog<>(1, numberList);
        cd.setTitle("Quantity");
        cd.setHeaderText(null);
        cd.setContentText("Select Quantity :");

        Optional<Integer> result = cd.showAndWait();
        result.ifPresent(selectedNumber -> {
                 showAlert("Success","Product Added to your Cart ! ",INFORMATION);
             ((Customer) loggedInUser).addProduct(fo[0],selectedNumber);
        });
        }
        else{
            showAlert("Failed","Products is out of stock",ERROR);
        }
            
         
         }
         else{showAlert("Failed","No Product with such an ID ",ERROR);}
        
    });
            }
    });
    cat1.setOnAction(shopy->{shop.hide();
         ObservableList<String> prods = FXCollections.observableArrayList();
            for (User user1 : Admin.users) {
        if (user1 instanceof Seller) {
            ArrayList<Product> prod=((Seller)user1).getProducts();
         for(Product grop:prod){
         if(grop.getProductID().startsWith("2")){
            if(grop.getQuantity()<=0){
              prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +" Product Status : Out of Stock \n----------------------------------------" );
             }
             else{
          prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +" Product Status : In Stock \n----------------------------------------" );
             }
         }
         }
        }
            }
              if(prods.isEmpty()){
            showAlert("Failed","No Products Are Avaliable within the Selected Category",ERROR);
            }
            else{
         ListView<String> productListView = new ListView<>();
     productListView.setPrefWidth(500);
     productListView.setItems(prods);
    Stage groc=new Stage();
    Label en=new Label("Enter Product ID : ");
    TextField ent=new TextField();
    Button ea=new Button("Add To Cart");
           Button backoff1=new Button("Back");
    backoff1.setOnAction(ice->{groc.close();shop.show();
    });
    HBox ee=new HBox(15,en,ent);
    VBox gro=new VBox(15,ee,productListView);
    x(gro,"talabat.jpg");
    gro.setPadding(new Insets(10));
    gro.getChildren().addAll(ea,backoff1);
    Scene go=new Scene(gro);
    groc.setScene(go);
    groc.setTitle("Toys Products");
    groc.show();
    ea.setOnAction(mf->{
           final Product[] fo = {null};
        
        boolean found=false;
         for (User user1 : Admin.users) {
        if (user1 instanceof Seller) {
            ArrayList<Product> prod=((Seller)user1).getProducts();
         for(Product grop:prod){
         if(grop.getProductID().equals(ent.getText())){
             found=true;
             fo[0]=grop;
         }
         }
        }
         }
         if(found){  
                    List<Integer> numberList = new ArrayList<>();
        for (int i = 1; i <= fo[0].getQuantity(); i++) {
            numberList.add(i);
        }

        
        if(fo[0].getQuantity()>0){ChoiceDialog<Integer> cd = new ChoiceDialog<>(1, numberList);
        cd.setTitle("Quantity");
        cd.setHeaderText(null);
        cd.setContentText("Select Quantity :");

        Optional<Integer> result = cd.showAndWait();
        result.ifPresent(selectedNumber -> {
                 showAlert("Success","Product Added to your Cart ! ",INFORMATION);
             ((Customer) loggedInUser).addProduct(fo[0],selectedNumber);
        });
        }
        else{
            showAlert("Failed","Products is out of stock",ERROR);
        }
            
         
         }
         else{showAlert("Failed","No Product with such an ID ",ERROR);}
    });
              }
    });
    cat2.setOnAction(shopy->{shop.hide();
            ObservableList<String> prods = FXCollections.observableArrayList();
            for (User user1 : Admin.users) {
        if (user1 instanceof Seller) {
            ArrayList<Product> prod=((Seller)user1).getProducts();
         for(Product grop:prod){
         if(grop.getProductID().startsWith("3")){
            if(grop.getQuantity()<=0){
              prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +" Product Status : Out of Stock \n----------------------------------------" );
             }
             else{
          prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +" Product Status : In Stock \n----------------------------------------" );
             }
         }
         }
        }
            }
              if(prods.isEmpty()){
            showAlert("Failed","No Products Are Avaliable within the Selected Category",ERROR);
            }
            else{
         ListView<String> productListView = new ListView<>();
     productListView.setPrefWidth(500);
     productListView.setItems(prods);
    Stage groc=new Stage();
    Label en=new Label("Enter Product ID : ");
    TextField ent=new TextField();
    Button ea=new Button("Add To Cart");
           Button backoff1=new Button("Back");
    backoff1.setOnAction(ice->{groc.close();shop.show();
    });
    HBox ee=new HBox(15,en,ent);
    VBox gro=new VBox(15,ee,productListView);
        x(gro,"talabat.jpg");
    gro.getChildren().addAll(ea,backoff1);
    gro.setPadding(new Insets(10));
    Scene go=new Scene(gro);
    groc.setScene(go);
    groc.setTitle("Clothes Products");
    groc.show();
    ea.setOnAction(mf->{
           final Product[] fo = {null};
        
        boolean found=false;
         for (User user1 : Admin.users) {
        if (user1 instanceof Seller) {
            ArrayList<Product> prod=((Seller)user1).getProducts();
         for(Product grop:prod){
         if(grop.getProductID().equals(ent.getText())){
             found=true;
             fo[0]=grop;
         }
         }
        }
         }
         if(found){  
                    List<Integer> numberList = new ArrayList<>();
        for (int i = 1; i <= fo[0].getQuantity(); i++) {
            numberList.add(i);
        }

        
        if(fo[0].getQuantity()>0){ChoiceDialog<Integer> cd = new ChoiceDialog<>(1, numberList);
        cd.setTitle("Quantity");
        cd.setHeaderText(null);
        cd.setContentText("Select Quantity :");

        Optional<Integer> result = cd.showAndWait();
        result.ifPresent(selectedNumber -> {
                 showAlert("Success","Product Added to your Cart ! ",INFORMATION);
             ((Customer) loggedInUser).addProduct(fo[0],selectedNumber);
        });
        }
        else{
            showAlert("Failed","Products is out of stock",ERROR);
        }
            
         
         }
         else{showAlert("Failed","No Product with such an ID ",ERROR);}
    });
              }
    });
    cat3.setOnAction(shopy->{ shop.hide();
            ObservableList<String> prods = FXCollections.observableArrayList();
            for (User user1 : Admin.users) {
        if (user1 instanceof Seller) {
            ArrayList<Product> prod=((Seller)user1).getProducts();
         for(Product grop:prod){
         if(grop.getProductID().startsWith("0")){
             if(grop.getQuantity()<=0){
              prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +" Product Status : Out of Stock \n----------------------------------------" );
             }
             else{
          prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +" Product Status : In Stock \n----------------------------------------" );
             }
         }
         }
        }
            }
              if(prods.isEmpty()){
            showAlert("Failed","No Products Are Avaliable within the Selected Category",ERROR);
            }
            else{
         ListView<String> productListView = new ListView<>();
     productListView.setPrefWidth(500);
     productListView.setItems(prods);
    Stage groc=new Stage();
    Label en=new Label("Enter Product ID : ");
    TextField ent=new TextField();
    Button ea=new Button("Add To Cart");
           Button backoff1=new Button("Back");
    backoff1.setOnAction(ice->{groc.close();shop.show();
    });
    HBox ee=new HBox(15,en,ent);
    VBox gro=new VBox(15,ee,productListView);
        x(gro,"talabat.jpg");
    gro.getChildren().addAll(ea,backoff1);
    gro.setPadding(new Insets(10));
    Scene go=new Scene(gro);
    groc.setScene(go);
    groc.setTitle("Electronic Products");
    groc.show();
    ea.setOnAction(mf->{
           final Product[] fo = {null};
        
        boolean found=false;
         for (User user1 : Admin.users) {
        if (user1 instanceof Seller) {
            ArrayList<Product> prod=((Seller)user1).getProducts();
         for(Product grop:prod){
         if(grop.getProductID().equals(ent.getText())){
             found=true;
             fo[0]=grop;
         }
         }
        }
         }
         if(found){  
                    List<Integer> numberList = new ArrayList<>();
        for (int i = 1; i <= fo[0].getQuantity(); i++) {
            numberList.add(i);
        }

        
        if(fo[0].getQuantity()>0){ChoiceDialog<Integer> cd = new ChoiceDialog<>(1, numberList);
        cd.setTitle("Quantity");
        cd.setHeaderText(null);
        cd.setContentText("Select Quantity :");

        Optional<Integer> result = cd.showAndWait();
        result.ifPresent(selectedNumber -> {
                 showAlert("Success","Product Added to your Cart ! ",INFORMATION);
             ((Customer) loggedInUser).addProduct(fo[0],selectedNumber);
        });
        }
        else{
            showAlert("Failed","Products is out of stock",ERROR);
        }
            
         
         }
         else{showAlert("Failed","No Product with such an ID ",ERROR);}
    });
     
              }
    });
    
    });

    
    btc1.setOnAction(cc->{  customer.hide();  if(((Customer) loggedInUser).getCart().isEmpty()){
        showAlert("Failed","You have no products in your cart ",ERROR);
        customer.show();
    }else{
         ObservableList<String> prods = FXCollections.observableArrayList();
           ArrayList<Product>prod = ((Customer) loggedInUser).getCart().getProducts();
         for(Product grop:prod){
       
              prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+" Quantity : "+grop.getQuantity()
              +" \n----------------------------------------" );
         }
        ListView<String> productListView = new ListView<>();
     productListView.setPrefWidth(500);
     productListView.setItems(prods);
     Stage groc=new Stage();
    Label en=new Label("Enter Product ID : ");
    TextField ent=new TextField();
    Button ea=new Button("Remove From Cart");
     Button backoff1=new Button("Back");
    backoff1.setOnAction(ice->{groc.close();customer.show();
    });
    HBox ee=new HBox(15,en,ent);
    VBox gro=new VBox(15,ee,productListView);
        x(gro,"talabat.jpg");
    gro.setPadding(new Insets(10));
    gro.getChildren().addAll(ea,backoff1);
    Scene go=new Scene(gro);
    groc.setScene(go);
    groc.setTitle("Cart Content");
    groc.show();
     ea.setOnAction(mf->{
     boolean found=false;
     Product pa=null;
     for(Product sea:prod){
         if(sea.getProductID().equals(ent.getText())){
             found=true;
             pa=sea;
             break;
         }
     }
     if(found){showAlert("Success","Product Has been Removed Successfully !",INFORMATION);
     ((Customer) loggedInUser).removeProduct(pa);
     prods.clear();
      ArrayList<Product>prod1 = ((Customer) loggedInUser).getCart().getProducts();
         for(Product grop:prod1){
       
              prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+" Quantity : "+grop.getQuantity()
              +" \n----------------------------------------" );
         }
     }
     else{showAlert("Failed","No Product found with such an ID ",ERROR);}
     
     });
    }});
    btc2.setOnAction(cc->{  customer.hide();
        if(((Customer) loggedInUser).getAddress().isEmpty() ){
    Stage add=new Stage();
    Label addr=new Label("Enter Your Address : ");
    TextField tfr=new TextField();
    Button btt=new Button("Add Address");
           Button backoff1=new Button("Back");
    backoff1.setOnAction(ice->{add.close();customer.show();
    });
    HBox hbb=new HBox(15,addr,tfr);
    hbb.setSpacing(10); hbb.setPadding(new Insets(10));
    VBox vbb=new VBox(5,hbb,btt);
     x(vbb,"talabat.jpg");
    vbb.getChildren().add(backoff1);
    vbb.setPadding(new Insets(10));
    Scene ff=new Scene(vbb);
    add.setScene(ff);
    add.setTitle("Address Entry");
    add.show();
    btt.setOnAction(cia->{ showAlert("Success","Address Has Been Added",INFORMATION);
    ((Customer) loggedInUser).addAddress(tfr.getText());  });
    }
    else{
        boolean userPressedYes = showConfirmationAlert("To Notify", "You already have an existing address. Would you like to update it?");
        if (userPressedYes) {
     Stage add=new Stage();
    Label addr=new Label("Enter Your new Address : ");
    TextField tfr=new TextField();
    Button btt=new Button("Add Address");
    Button boo=new Button("Back");
    boo.setOnAction(bam->{add.close();
    customer.show();});
    HBox hbb=new HBox(15,addr,tfr);
    hbb.setSpacing(10); hbb.setPadding(new Insets(10));
    VBox vbb=new VBox(5,hbb,btt);
    vbb.getChildren().add(boo);
    vbb.setPadding(new Insets(10));
    Scene ff=new Scene(vbb);
    add.setScene(ff);
    add.setTitle("Address Entry");
    add.show();
    btt.setOnAction(cia->{if(tfr.getText().isEmpty()){showAlert("ERROR","Fields must be filled",ERROR);}
    else{showAlert("Success","Address Has Been Updated",INFORMATION);
    ((Customer) loggedInUser).addAddress(tfr.getText());
    customer.show();}  });
        }
        else{
            customer.show();
        }
    }
    });
    btc3.setOnAction(cc->{
        if(((Customer)loggedInUser).getAddress().isEmpty()){showAlert("Failed","An Address must be added First in order to continue",ERROR);}
        else{
        customer.hide();
    Stage confirm=new Stage();
    GridPane conf=new GridPane();
        x(conf,"talabat.jpg");
    conf.setVgap(10);conf.setHgap(10);conf.setPadding(new Insets(10));
    Button con=new Button("View Cart");
    Button con1=new Button("View Payment Method");
    Button con2=new Button("Check Out");
    Button con3=new Button("Back");
    conf.add(con,0,0);
    conf.add(con1,0,1);
    conf.add(con2,0,2);
    conf.add(con3,0,3);
    Scene confi=new Scene(conf,250,150);
    confirm.setScene(confi);
    confirm.setTitle("Check Out");
    confirm.show();
    con.setOnAction(fib->{ confirm.hide();
        ObservableList<String> prods = FXCollections.observableArrayList();
           ArrayList<Product>prod = ((Customer) loggedInUser).getCart().getProducts();
         for(Product grop:prod){
              prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +"\n----------------------------------------" );
             }
        ListView<String> productListView = new ListView<>();
     productListView.setPrefWidth(500);
     productListView.setItems(prods);
        Stage view=new Stage();
                 Button backoff2=new Button("Back");
    backoff2.setOnAction(ice->{view.close();confirm.show();
    });
        VBox viewe=new VBox(15,productListView,backoff2);
            x(viewe,"talabat.jpg");
        viewe.setPadding(new Insets(10));
        Scene vie=new Scene(viewe);
        view.setScene(vie);
        view.setTitle("Cart OverView");
        view.show();
    
    });
    con1.setOnAction(fib->{confirm.hide();
        Stage pay=new Stage();
        GridPane paye=new GridPane();
            x(paye,"talabat.jpg");
        paye.setVgap(10);paye.setHgap(5);paye.setPadding(new Insets(10));
        Label lbll=new Label();
        Label lbll2=new Label();
        Label lbll3=new Label();
        TextField bmw=new TextField();
        TextField bmw1=new TextField();
        TextField bmw2=new TextField();
        Button vw=new Button();
         Button backoff2=new Button("Back");
    backoff2.setOnAction(ice->{pay.close();confirm.show();
    });
        if(((Customer) loggedInUser).getCardNumber()==null){
            lbll.setText("Credit Card : ");
            lbll2.setText("Expiry Date");
            lbll3.setText("CCV OR Security Code");
            vw.setText("Add Credit Card");
            vw.setOnAction(car->{
                if(bmw.getText().isEmpty()||bmw1.getText().isEmpty()||bmw2.getText().isEmpty()){
                    showAlert("ERROR","All Inputs must be Filled",ERROR);
                }
                else{((Customer) loggedInUser).setCardNumber(bmw.getText());
        ((Customer) loggedInUser).setExpiryDate(bmw1.getText());
        ((Customer) loggedInUser).setCcv(Integer.valueOf(bmw2.getText()));}
        });
        }
        else {
            String encr=((Customer) loggedInUser).getCardNumber();
            StringBuilder encry=new StringBuilder();
            for(int i=0;i<encr.length()-3;i++){
            encry.append("*");
            }
            encry.append(encr.charAt(encr.length()-3));
            encry.append(encr.charAt(encr.length()-2));
            encry.append(encr.charAt(encr.length()-1));
            lbll.setText("Existing Credit Card : " +encry);
            lbll2.setText("Expiry Date : " + ((Customer) loggedInUser).getExpiryDate());
            lbll3.setText("CCV OR Security Code : ***");
            vw.setText("Edit Credit Card");
            vw.setOnAction(car->{if(bmw.getText().isEmpty()||bmw1.getText().isEmpty()||bmw2.getText().isEmpty()){
                    showAlert("ERROR","All Inputs must be Filled",ERROR);
                }
                else{((Customer) loggedInUser).setCardNumber(bmw.getText());
        ((Customer) loggedInUser).setExpiryDate(bmw1.getText());
        ((Customer) loggedInUser).setCcv(Integer.valueOf(bmw2.getText()));
            showAlert("Success","CreditCard Has Been Updated Successfully!",INFORMATION);}
        });
        }
        paye.add(lbll, 0, 0);
        paye.add(lbll2, 0, 1);
        paye.add(lbll3, 0, 2);
        paye.add(bmw, 7, 0);
        paye.add(bmw1, 7, 1);
        paye.add(bmw2, 7, 2);
        paye.add(vw, 5, 5);
        paye.add(backoff2,3,5);
        
        
        Scene pa=new Scene(paye);
        pay.setScene(pa);
        pay.setTitle("Credit Card");
        pay.show();
    });
    con2.setOnAction(fib->{ confirm.hide();
        if(((Customer) loggedInUser).getCart().getProducts().isEmpty()){
            showAlert("Failed","You can't Checkout because you have no items in your Cart",ERROR);
            confirm.show();
        }
        else{
     ObservableList<String> prods = FXCollections.observableArrayList();
           ArrayList<Product>prod = ((Customer) loggedInUser).getCart().getProducts();
         for(Product grop:prod){
              prods.add("Product name : "+grop.getName()+ "  Product price : "+grop.getPrice()+" \nProduct id : "+ grop.getProductID()+"  Product quantity : "+grop.getQuantity()
              +"\n----------------------------------------" );
             }
         Stage view=new Stage();
        ListView<String> productListView = new ListView<>();
     productListView.setPrefWidth(500);
     productListView.setItems(prods);  
        Button bba=new Button("Complete Purchase");
        Button bba1=new Button("Back");
        bba.setDisable(true);
        RadioButton rr1=new RadioButton("Cash On Delivery");
       RadioButton rr2=new RadioButton("Credit Card");
       ToggleGroup rr3=new ToggleGroup();
       rr1.setToggleGroup(rr3);
       rr2.setToggleGroup(rr3);
        rr3.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                  
                 RadioButton selectedRadioButton = (RadioButton) newValue;
                if(selectedRadioButton.getText().equals("Cash On Delivery")){
                 bba.setDisable(false);
             
                }
                else{
                if(((Customer)loggedInUser).getCardNumber()!=null){
                bba.setDisable(false);
            
                }
                else{view.hide();
                    bba.setDisable(true);
                 Stage pay=new Stage();
        GridPane paye=new GridPane();
            x(paye,"talabat.jpg");
        paye.setVgap(10);paye.setHgap(5);paye.setPadding(new Insets(10));
        Label lbll=new Label();
        Label lbll2=new Label();
        Label lbll3=new Label();
        TextField bmw=new TextField();
        TextField bmw1=new TextField();
        TextField bmw2=new TextField();
        Button vw=new Button();
         Button backoff2=new Button("Back");
    backoff2.setOnAction(ice->{pay.close();view.show();
    });
        if(((Customer) loggedInUser).getCardNumber()==null){
            lbll.setText("Credit Card : ");
            lbll2.setText("Expiry Date");
            lbll3.setText("CCV OR Security Code");
            vw.setText("Add Credit Card");
            vw.setOnAction(car->{
        if(bmw.getText().isEmpty()||bmw1.getText().isEmpty()||bmw2.getText().isEmpty()){
                    showAlert("ERROR","All Inputs must be Filled",ERROR);
                }
                else{((Customer) loggedInUser).setCardNumber(bmw.getText());
        ((Customer) loggedInUser).setExpiryDate(bmw1.getText());
        ((Customer) loggedInUser).setCcv(Integer.valueOf(bmw2.getText()));
         showAlert("Success","CreditCard Has Been Added Successfully!",INFORMATION);}
        });
        }
        paye.add(lbll, 0, 0);
        paye.add(lbll2, 0, 1);
        paye.add(lbll3, 0, 2);
        paye.add(bmw, 7, 0);
        paye.add(bmw1, 7, 1);
        paye.add(bmw2, 7, 2);
        paye.add(vw, 5, 5);
        paye.add(backoff2,3,5);
        Scene pa=new Scene(paye);
        pay.setScene(pa);
        pay.setTitle("Credit Card");
        pay.show();
                
                }
                }
               
            }
        });
        
        HBox hba=new HBox(10,bba,bba1);
        hba.getChildren().addAll(rr1,rr2);
        VBox viewe=new VBox(15,productListView,hba);
            x(viewe,"talabat.jpg");
        
        
        bba.setOnAction(lada->{RadioButton anss=(RadioButton)rr3.getSelectedToggle();
        if(anss.getText().equals("Credit Card")){
              String encr=((Customer) loggedInUser).getCardNumber();
            StringBuilder encry=new StringBuilder();
            for(int i=0;i<encr.length()-3;i++){
            encry.append("*");
            }
            encry.append(encr.charAt(encr.length()-3));
            encry.append(encr.charAt(encr.length()-2));
            encry.append(encr.charAt(encr.length()-1));
    
       TextInputDialog cconfirm=new TextInputDialog("***");
       cconfirm.setTitle("Enter Your CCV");
       cconfirm.setHeaderText("Credit Card Number : "+encry+"\n Expiry Date : "+((Customer) loggedInUser).getExpiryDate());
       cconfirm.setContentText("Enter Your CCV");
       Optional<String> ccma=cconfirm.showAndWait();
     ccma.ifPresent(CCN -> {
         if(Integer.valueOf(CCN)==((Customer)loggedInUser).getCcv()){
         showAlert("Success","Payment Method Confirmed , Proceeding to check out ....",INFORMATION);
          Order honda= ((Customer) loggedInUser).completePurchase();
        showAlert("Success","Order Has Been Placed Successfully\nOrder ID is : " + honda.getOrderID(),INFORMATION);
        view.close();
        confirm.show();
         }
         else{
         showAlert("Failed","CCV Entered is Incorrect",ERROR);
         
         }
  
     });
       
        }
        else{
             Order honda= ((Customer) loggedInUser).completePurchase();
        showAlert("Success","Order Has Been Placed Successfully\nOrder ID is : " + honda.getOrderID(),INFORMATION);
             view.close();
        confirm.show();
        }
        });
      
        bba1.setOnAction(lada->{
        view.close();
        confirm.show();
        });
        
        Scene vie=new Scene(viewe);
        view.setScene(vie);
        view.setTitle("Check out");
        view.show();
        }
    });
    con3.setOnAction(fib->{ 
        confirm.close();
    customer.show();
    
    });
        }
    });
    btc4.setOnAction(cc->{ showAlert("Success","Cart is now Clear ",INFORMATION);
    ((Customer)loggedInUser).cancelCart();
    });
    btc5.setOnAction(cc->{customer.hide();
    Stage x = new Stage();
 ListView<String> orderListView = new ListView<>();
  orderListView.setPrefWidth(325);
   ObservableList<String> ss = FXCollections.observableArrayList();
              ArrayList<Order> custorders = ((Customer)loggedInUser).getOrderHistory();
             for (Order order : custorders) {
                   ss.add("  Order ID: " + order.getOrderID() +
                        ", Status: " + order.getStatus()+"\nCost : " + order.calculateTotalAmount());
                }
            
     orderListView.setItems(ss);
     Button gg=new Button("Back");
     gg.setOnAction(surr->{
     x.close();
     customer.show();
     });
     VBox x1 = new VBox(15,orderListView,gg);
         x(x1,"talabat.jpg");
     x1.setPadding(new Insets(10));
     Scene xx=new Scene(x1);
     x.setScene(xx);
     x.setTitle("Tracking Order");
     x.show();
    
    });
    btc6.setOnAction(cc->{customer.hide();
    Stage x = new Stage();
    Button gg=new Button("Back");
     gg.setOnAction(surr->{
     x.close();
     customer.show();
     });
 ListView<String> orderListView = new ListView<>();
  orderListView.setPrefWidth(325);
   ObservableList<String> ss = FXCollections.observableArrayList();
              ArrayList<Order> custorders = ((Customer)loggedInUser).getOrderHistory();
             for (Order order : custorders) {
                 if(order.getStatus().equals("Shipped")&&order.getRating()==-1)
                   ss.add("  Order ID: " + order.getOrderID() +
                        ", Status: " + order.getStatus()+"\nCost : " + order.calculateTotalAmount());
                }
     orderListView.setItems(ss);
     Label lft=new Label("Order ID to Rate : ");
     TextField tft=new TextField();
     HBox y1=new HBox(15,lft,tft);
     VBox x1 = new VBox(10,y1,orderListView);
         x(x1,"talabat.jpg");
     Button conc=new Button("Rate Order");
     x1.getChildren().addAll(conc,gg);
     x1.setPadding(new Insets(10));
     conc.setOnAction(lol->{try{Integer.valueOf(tft.getText());
     Order o = null;
     boolean found = false;
      ArrayList<Order> custer = ((Customer)loggedInUser).getOrderHistory();
             for (Order order : custer) {
                  if(order.getOrderID()==Integer.valueOf(tft.getText())&&order.getStatus().equals("Shipped")&&order.getRating()==-1){
                  found=true;
                  o=order;
                  break;
                  }
                }
             if(found){
             ChoiceDialog cid=new ChoiceDialog(1,2,3,4,5);
             Optional<Integer> mem=cid.showAndWait();
             if(mem.isPresent()){
                o.setRating(mem.get());
                ss.clear();
              ArrayList<Order>  custorders1 = ((Customer)loggedInUser).getOrderHistory();
             for (Order order : custorders1) {
                 if(order.getStatus().equals("Shipped")&&order.getRating()==-1)
                   ss.add("  Order ID: " + order.getOrderID() +
                        ", Status: " + order.getStatus()+"\nCost : " + order.calculateTotalAmount());
                }
             }
             
             
             }
             else{showAlert("Failed","No Order with such ID Exists ",ERROR);}
             
     }
     catch(Exception mmo){showAlert("ERROR","Please enter a proper number",ERROR);}
             
     });
     Scene xx=new Scene(x1);
     x.setScene(xx);
     x.setTitle("Order Rating");
     x.show();
    });
    logout.setOnAction(act->{customer.close();
    userf.clear();
     passf.clear();
     stage.show();});
 
 } 

      });
       register.setOnAction(e->{stage.hide();
           Stage reg=new Stage();
           GridPane reglay=new GridPane();
               x(reglay,"talabat.jpg");
           reglay.setHgap(3);reglay.setVgap(10);reglay.setPadding(new Insets(10));
           reglay.setAlignment(Pos.CENTER);
           Label reguser=new Label("Username : ");
           Label regpass=new Label("Password : ");
           TextField regf=new TextField();
           PasswordField regf1 = new PasswordField();
           Button regregister=new Button("Sign up");
           CheckBox check=new CheckBox("Seller ");
           Scene regsce=new Scene(reglay,300,120);
           reglay.add(reguser, 0, 0);
           reglay.add(regpass,0,1);
           reglay.add(regf,1,0);
           reglay.add(regf1,1,1);
           reglay.add(check, 0, 3);
           reglay.add(regregister, 2, 3);
           regregister.setOnAction(eh->{
               if(regf.getText().isEmpty()&&regf1.getText().isEmpty()){
           showAlert("Failed","All Fields must be filled",ERROR);
           }
           else{
                    ArrayList<User> league=Admin.getUsers();
               boolean exists=false;
               for(User legend:league){
               if(legend.getUsername().equals(regf.getText())){
               exists=true;
               break;
               }
               }
               if(exists){
               showAlert("Failed","User with the choosen username already exists",ERROR);
               }
               else{
                showAlert("Success","User has been added Successfully !!",INFORMATION);
               a.signup(regf.getText(),regf1.getText(),check.isSelected());
              
               }
        reg.close();
               stage.show();
               
           }});
           reg.setTitle("Registration Form");
           reg.setScene(regsce);
           reg.show();
       
       });
        stage.show();
        
                }
    public static void main(String[] args)  {
        launch(args);
         

    }

  
    private boolean showConfirmationAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);

    Optional<ButtonType> result = alert.showAndWait();
    return result.isPresent() && result.get() == ButtonType.OK;
}
       public  void x(Pane root, String imagePath) {
          Image image = new Image(imagePath);
        BackgroundSize backgroundSize = BackgroundSize.DEFAULT;
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setBackground(background);
    }
}

  
