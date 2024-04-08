package Storefront;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Browser extends JFrame {

    private JPanel browser_panel;
    private JScrollPane scrollPane;
    private static final String DATABASE_URL = "jdbc:sqlite:C:/Users/unhmguest/sqlite/store.db"; // Path to your SQLite database

    public Browser() {
        initialize();
        fetchListingsData(); // Fetch listings data from the database
    }

    private void initialize() {
        setTitle("Listings Browser");
        setSize(625, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        browser_panel = new JPanel();
        browser_panel.setLayout(null);

        // Add browser_panel to scrollPane
        scrollPane = new JScrollPane(browser_panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);
        setVisible(true);
    }

    private void fetchListingsData() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Listings")) {

            // Dynamically create and add Listing panels based on database entries
            int index = 0;
            while (rs.next()) {
                Listing listing = new Listing(
                        rs.getString("ListingID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Price"),
                        rs.getString("Quantity"),
                        rs.getString("Image")
                );
                browser_panel.add(listing);
                listing.setLocation(0, 300 * index + 100); // Adjust position
                index++;
            }

            // Set expected total height of panels for scrollbar functionality
            int panelHeight = (index * 300) + 10;
            browser_panel.setPreferredSize(new Dimension(0, panelHeight));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch listings data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Browser::new);
    }
}

// Individual Listing Object
class Listing extends JPanel {
    private JLabel lblListingID;
    private String ListingID;
    private String Title;
    private String Description;
    private String Price;
    private String Quantity;
    private String Image;

    public Listing(String ListingID, String Title, String Description, String Price, String Quantity, String Image) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.ListingID = ListingID;
        this.Title = Title;
        this.Description = Description;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Image = Image;
        initialize();
    }

    private void initialize() {
        setSize(600, 300);
        lblListingID = new JLabel("Listing ID: " + ListingID);
        lblListingID.setBounds(10, 6, 170, 14);
        setLayout(null);
        add(lblListingID);

        JLabel lblTitle = new JLabel("Title: " + Title);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitle.setBounds(10, 30, 170, 20);
        add(lblTitle);

        JLabel lblDescription = new JLabel("Description: " + Description);
        lblDescription.setBounds(10, 60, 400, 20);
        add(lblDescription);

        JLabel lblPrice = new JLabel("Price: $" + Price);
        lblPrice.setBounds(10, 90, 100, 20);
        add(lblPrice);

        JLabel lblQuantity = new JLabel("Quantity: " + Quantity);
        lblQuantity.setBounds(10, 120, 100, 20);
        add(lblQuantity);

        JLabel lblImage = new JLabel("Image: " + Image);
        lblImage.setBounds(10, 150, 400, 20);
        add(lblImage);
    }
}
