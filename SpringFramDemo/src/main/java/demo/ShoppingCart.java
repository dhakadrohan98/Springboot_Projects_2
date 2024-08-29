package demo;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
    //Logging
    //Autentication & Authorization
    //Sanitize the data
    public void checkout(String status) {
        System.out.println("Checkout method is called from shopping cart class - " + status);
    }

    public int quantity() {
        return 2;
    }
}
