package Storefront;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Database {
	
	private static final String DATABASE_URL = "jdbc:sqlite:C:/Users/seanb/eclipse-workspace/Comp730-830-Team1/Database/store.db"; // Path to your SQLite database

	public Database() {
	
	}

	public int fetchTableSize() {
		int rowCount = 0;
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Listings")) {
	        if (rs.next()) {
	            rowCount = rs.getInt(1); // Get the count from the first column
	        }		
        }
		catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Failed to fetch listings data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
		return rowCount;
	}
	
	public Listing fetchListingsData(int ID) {
		Listing listing = null;
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Listings WHERE ListingID = " + ID)) {

        // Dynamically create and add Listing panels based on database entries        	
            listing = new Listing(
                    rs.getString("ListingID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("Price"),
                    rs.getString("Quantity"),
                    rs.getString("Image")
            );
        } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to fetch listings data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
		
	return listing;
	}
}