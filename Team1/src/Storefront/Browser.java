package Storefront;

import javax.swing.*;
import java.awt.*;

public class Browser extends JFrame {
	
	private JPanel browser_panel;
	private Listing[] listings_array;
	private JScrollPane scrollPane;
	
    public Browser() {
    	initialize();
    	run();
    	
    }
    
    private void initialize() {
        setTitle("Listings Browser");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        browser_panel = new JPanel();
        browser_panel.setLayout(null);
        
        // loop to dynamically initialize listings as panels to the main screen
        // change the number 40 to the number of database entries we start with
    	Listing[] listings_array;
        listings_array = new Listing[40];
        for (int i = 0; i < 40; i++) {
            listings_array[i] = new Listing("Listing " + i, "Example", "This is an example description", "12.99", "10", "img");
            browser_panel.add(listings_array[i]); // add each listing panel to application
            listings_array[i].setLocation(0, 300 * i); // set location to avoid overlap (300 is height of each listing)
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
        SwingUtilities.invokeLater(() -> new Browser());
    }
    
    public void run() { 
        
    }
    
}