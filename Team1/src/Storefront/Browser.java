package Storefront;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Browser extends JFrame {

    private JPanel browser_panel;
    private JScrollPane scrollPane;
    private int database_size;
    private Listing initialized_listing;
    private final Database database = new Database();
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Browser::new);
    }
    
    public Browser() {
    	initialize_database();
        initialize_screen();
        initialize_listings();
    }
    

    private void initialize_database() {
    	// create database object and find size
        database_size = database.fetchTableSize();
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
        btnCreateListing.setBounds(462, 11, 118, 32);
        menu_panel.add(btnCreateListing);
        btnCreateListing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// this block creates a dialog box for creating a new listing using CreateListing.java
				// it will open and not allow the browser to continue running code until completed
				// the resulting output is a new listing object
		        CreateListing dialogFrame = new CreateListing();
		        JDialog dialog = new JDialog(dialogFrame, "Dialog", true);
		        dialog.setSize(450, 300);
		        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		        dialog.getContentPane().add(dialogFrame.getContentPane());
		        dialogFrame.setListingID(Integer.toString(database_size + 1));
		        dialog.setVisible(true);
		        
		        // this runs after the dialog box has been completed
		        // it saves the created listing to a variable and pushes it to the database
		        Listing createdlisting = (dialogFrame.getCreatedListing());
		        database.insertListingData(createdlisting);
		        
		        // update for the new size of the database
		        database_size = database.fetchTableSize();
		        		        
		        // this adds the new listing to the browser screen
	            browser_panel.add(createdlisting); 
	            createdlisting.setLocation(0, 300 * (database_size) + 100); 
	            browser_panel.revalidate();
	            int panelHeight = (database_size * 300) + 100;
	            browser_panel.setPreferredSize(new Dimension(0, panelHeight));
	           
			}
		});
        
        JButton btnEditListings = new JButton("Edit Listings");
        btnEditListings.setBounds(462, 54, 118, 32);
        menu_panel.add(btnEditListings);
        btnEditListings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add functionality later...
			}
		});
    }
    
    private void initialize_listings() {   
        
        // loop to dynamically initialize listings as panels to the main screen
        for (int i = 0; i < database_size; i++) {
            initialized_listing = database.fetchListingsData(i + 1); // initialize new listing object from database. (i + 1) represents ListingID. 
            browser_panel.add(initialized_listing); // add each listing panel to application
            initialized_listing.setLocation(0, 300 * i + 100); // set location to avoid overlap (300 is height of each listing)
            browser_panel.revalidate(); // refresh the layout
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
    

    
}
