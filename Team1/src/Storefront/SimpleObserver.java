package Storefront;

public class SimpleObserver {
	
    private Cart cart;

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void notifyRemovedFromCart(String price) {
        if (cart != null) {
            cart.removedFromCart(price);
        }
    }
}