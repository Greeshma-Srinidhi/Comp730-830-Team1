package Storefront;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Cart extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cart_panel;
	private JScrollPane scrollPane;
	private Database database;
	private int database_size;
	private int carted_item_count = 0;
	private Listing[] listings_array;

    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(CreateListing::new);
    }

	public Cart(Database database, int database_size, Listing[] listings_array) {
		this.database = database;
		this.database_size = database_size;
		this.listings_array = listings_array;
		initialize_cart();
	}
	
	private void initialize_cart() {
				
		setTitle("Cart");
        setSize(625, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        cart_panel = new JPanel();
        cart_panel.setLayout(null);
        
        JPanel menu_panel = new JPanel();
    	menu_panel.setSize(600, 300);
    	menu_panel.setLayout(null);
        menu_panel.setBounds(0, 0, 590, 100);
        cart_panel.add(menu_panel);
        
        JLabel lblCart = new JLabel("Cart");
        lblCart.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblCart.setBounds(10, 11, 170, 75);
        menu_panel.add(lblCart);       
        
        JButton btnCloseCart = new JButton("Close Cart");
        btnCloseCart.setBounds(470, 54, 110, 32);
        menu_panel.add(btnCloseCart);
        btnCloseCart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		removeAll();
        		dispose();
        	}
        });
        
        JButton btnCheckOut = new JButton("Check Out");
        btnCheckOut.setBounds(470, 11, 110, 32);
        menu_panel.add(btnCheckOut);
        btnCheckOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// add functionality later...
        	}
        });
        
        // loop to dynamically initialize listings as panels to the main screen
        for (int i = 0; i < database_size; i++) {
            
            // if statement to determine whether the item is in the cart or not.
            if (listings_array[i].getCartedAmount() != 0) {
                cart_panel.add(listings_array[i]); // add each listing panel to application
                listings_array[i].setLocation(0, 300 * carted_item_count + 100); // set location to avoid overlap (300 is height of each listing)
                cart_panel.revalidate(); // refresh the layout
                listings_array[i].CartModeOn();
                carted_item_count++;
            }
        }
        
        // set expected total height of panels. this allows the scroll bar to function
        // 300 is the pre-defined height of each listing panel
        int panelHeight = (carted_item_count * 300) + 100;
        cart_panel.setPreferredSize(new Dimension(0, panelHeight));
        
        // create scrollpane and set options
        scrollPane = new JScrollPane(cart_panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);
        setVisible(true);
        
        // set view to the top of the page. (by default, the view was being set to the bottom of all listings).
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                scrollPane.getViewport().setViewPosition( new Point(0, 0) );
            }
        });
	}
	
	
}
