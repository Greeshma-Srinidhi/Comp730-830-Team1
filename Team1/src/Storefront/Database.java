package Storefront;

import javax.swing.*;
import java.sql.*;
import java.net.URL;

public class Database {
	
	private final URL url = getClass().getResource("store.db");
	private final String file = url.getPath();
	private final String DATABASE_URL = "jdbc:sqlite:" + file; // Path to SQLite database
	private SimpleObserver observer;
	
	public Database(SimpleObserver observer) {
		this.observer = observer;
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
            		observer,
                    rs.getString("ListingID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("Price"),
                    rs.getString("Quantity"),
                    rs.getString("Image"),
                    rs.getString("Seller")
            );
        } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to fetch listings data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
		
	return listing;
	}
	
	public void insertListingData(Listing newListing) {
		try (Connection conn = DriverManager.getConnection(DATABASE_URL);
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Listings(ListingID, Title, Description, Price, Quantity, Image, Seller) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
			pstmt.setString(1, newListing.getListingID());
	        pstmt.setString(2, newListing.getTitle());
	        pstmt.setString(3, newListing.getDescription());
	        pstmt.setString(4, newListing.getPrice());
	        pstmt.setString(5, newListing.getQuantity());
	        pstmt.setString(6, newListing.getImage());
	        pstmt.setString(7, newListing.getSeller());
	        pstmt.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to fetch listings data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
		
	}
	
    public boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM UserAuth WHERE username = ? AND password = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            isAuthenticated = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to authenticate user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isAuthenticated;
    }
    

    public boolean checkUserCredentials(String username, String password) {
        String query = "SELECT * FROM UserAuth WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if the query returns any results
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error checking user credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public void insertUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            String sql = "INSERT INTO UserAuth (username, password) VALUES ('" + username + "', '" + password + "')";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean checkUsernameExists(String username) {
        String query = "SELECT * FROM UserAuth WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if the query returns any results
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error checking if username exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean updateUser(String oldUsername, String newUsername, String newPassword) {
        String query = "UPDATE UserAuth SET username = ?, password = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newUsername);
            pstmt.setString(2, newPassword);
            pstmt.setString(3, oldUsername);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; // Return true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update user information.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }





}
