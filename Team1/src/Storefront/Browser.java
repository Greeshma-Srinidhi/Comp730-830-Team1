package Storefront;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Browser extends JFrame {

    private JPanel browser_panel;
    private JScrollPane scrollPane;
    private static final String DATABASE_URL = "jdbc:sqlite:C:/Users/seanb/eclipse-workspace/Comp730-830-Team1/Database/store.db"; // Path to your SQLite database

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
