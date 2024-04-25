package Storefront;

public class SimpleObserver {
	
    private Cart cart;
    private boolean loggedIn = false;

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void notifyRemovedFromCart() {
        if (cart != null) {
            cart.removedFromCart();
        }
    }
    
    public boolean getLoggedInStatus() {
    	return loggedIn;
    }
    
    public void notiyLoggedIn(boolean loggedIn) {
    	this.loggedIn = loggedIn;
    }
 
    
}