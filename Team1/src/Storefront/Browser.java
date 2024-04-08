package Storefront;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Browser extends JFrame {

    private JPanel browser_panel;
    private JScrollPane scrollPane;
    
    public Browser() {
        initialize();
        
    }

    private void initialize() {
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
			public void actionPerformed(ActionEvent CreateListing) {
				// add functionality later...
			}
		});
        
        JButton btnEditListings = new JButton("Edit Listings");
        btnEditListings.setBounds(462, 54, 118, 32);
        menu_panel.add(btnEditListings);
        btnEditListings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent EditListings) {
				// add functionality later...
			}
		});
        
        // create database object and find size
        final Database database= new Database();
        int database_size = database.fetchTableSize();
        
        // loop to dynamically initialize listings as panels to the main screen
    	Listing[] listings_array;
        listings_array = new Listing[database_size];
        for (int i = 0; i < database_size; i++) {
            listings_array[i] = database.fetchListingsData(i + 1); // initialize new listing object from database and store in array for use in this class. (i + 1) represents ListingID. 
            browser_panel.add(listings_array[i]); // add each listing panel to application
            listings_array[i].setLocation(0, 300 * i + 100); // set location to avoid overlap (300 is height of each listing)
            browser_panel.revalidate(); // refresh the layout
        }
        
        // set expected total height of panels. this allows the scroll bar to function
        // 300 is the pre-defined height of each listing panel
        int panelHeight = (listings_array.length * 300) + 10;
        browser_panel.setPreferredSize(new Dimension(0, panelHeight));
        
        
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Browser::new);
    }

    
}
