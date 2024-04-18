package Storefront;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Browser extends JFrame {

    private JPanel browser_panel;
    private JScrollPane scrollPane;
    private SimpleObserver observer = new SimpleObserver();
    private final Database database = new Database(observer);
    private int database_size = database.fetchTableSize();
    private Listing[] listings_array = new Listing[255];
    private int array_size;
    private boolean loggedIn = false; // Track login status
    private String loggedInUsername = ""; // Track the username of the logged-in user
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Browser::new);
    }
    
    public Browser() {
    	
        initialize_screen();
        initialize_listings();
    }
    
    private void initialize_screen() {
    	
    	setTitle("Listings Browser");
        setSize(625, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        browser_panel = new JPanel();
        browser_panel.setLayout(null);
        
        JPanel menu_panel = new JPanel();
    	menu_panel.setSize(600, 300);
    	menu_panel.setLayout(null);
        menu_panel.setBounds(0, 0, 590, 100);
        browser_panel.add(menu_panel);
        
        JLabel lblStoreFront = new JLabel("Storefront");
        lblStoreFront.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblStoreFront.setBounds(10, 11, 170, 75);
        menu_panel.add(lblStoreFront);
        
        JButton btnCreateListing = new JButton("Create listing");
        btnCreateListing.setBounds(350, 11, 110, 32);
        menu_panel.add(btnCreateListing);
        btnCreateListing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create_listings();
			}
		});
        
        JButton btnEditListings = new JButton("Edit Listings");
        btnEditListings.setBounds(350, 54, 110, 32);
        menu_panel.add(btnEditListings);
        btnEditListings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add functionality later...
			}
		});
        
        JButton btnViewCart = new JButton("View Cart");
        btnViewCart.setBounds(470, 54, 110, 32);
        menu_panel.add(btnViewCart);
        btnViewCart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		view_cart();
        	}
        });
         
        JButton btnLogIn = new JButton("Login");
        btnLogIn.setBounds(470, 11, 110, 32);
        menu_panel.add(btnLogIn);
        btnLogIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		login();
        	}
        });
    }
    
    private void initialize_listings() {   
        
        // loop to dynamically initialize listings as panels to the main screen
        for (int i = 0; i < database_size; i++) {
        	listings_array[i] = database.fetchListingsData(i + 1); // initialize new listing object from database. (i + 1) represents ListingID. 
            browser_panel.add(listings_array[i]); // add each listing panel to application
            listings_array[i].setLocation(0, 300 * i + 100); // set location to avoid overlap (300 is height of each listing)
            browser_panel.revalidate(); // refresh the layout
            array_size++;
        }
        
        // set expected total height of panels. this allows the scroll bar to function
        // 300 is the pre-defined height of each listing panel
        int panelHeight = (database_size * 300) + 100;
        browser_panel.setPreferredSize(new Dimension(0, panelHeight));
        
        // create scrollpane and set options
        scrollPane = new JScrollPane(browser_panel);
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
    
    private void create_listings() {
    	
		// this block creates a dialog box for creating a new listing using CreateListing.java
		// it will open and not allow the browser to continue running code until completed
		// the resulting output is a new listing object
        CreateListing dialogFrame = new CreateListing(observer);
        JDialog dialog = new JDialog(dialogFrame, "Create Listing", true);
        dialog.setSize(450, 300);
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.getContentPane().add(dialogFrame.getContentPane());
        dialogFrame.setListingID(Integer.toString(database_size + 1));
        dialog.setVisible(true);
        
        if (dialogFrame.getCreatedListing() != null) {
            // this runs after the dialog box has been completed
            // it saves the created listing to a variable and pushes it to the database
            Listing createdlisting = (dialogFrame.getCreatedListing());
            database.insertListingData(createdlisting);
            listings_array[array_size] = createdlisting;
            array_size++;
            
            // update for the new size of the database
            database_size = database.fetchTableSize();
            		        
            // this adds the new listing to the browser screen
            browser_panel.add(createdlisting); 
            createdlisting.setLocation(0, 300 * (database_size - 1) + 100); 
            browser_panel.revalidate();
            int panelHeight = (database_size * 300) + 100;
            browser_panel.setPreferredSize(new Dimension(0, panelHeight));
        }
    }
    
    private void login() {
        if (loggedIn) {
            JOptionPane.showMessageDialog(null, "You are already logged in as " + loggedInUsername + ".", "Already Logged In", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String username = JOptionPane.showInputDialog("Enter your username:");
            String password = JOptionPane.showInputDialog("Enter your password:");

            // Compare entered credentials with predefined values
            if (username != null && password != null && username.equals("admin") && password.equals("admin123")) {
                loggedIn = true;
                loggedInUsername = username;
                JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void view_cart() {
    	
    	//
    	Cart dialogFrame = new Cart(database, database_size, observer, listings_array);
    	observer.setCart(dialogFrame);
        JDialog dialog = new JDialog(dialogFrame, "Cart", true);
        dialog.setSize(625, 1000);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(dialogFrame.getContentPane());
        dialog.setVisible(true);
        
        for (int i = 0; i < database_size; i++) {       
                listings_array[i].CartModeOff();
        }
    }
}
