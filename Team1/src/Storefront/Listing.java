package Storefront;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
// Individual Listing Object (test for now)
class Listing extends JPanel {
    private JLabel lblListingID;
    private String ListingID;
    private String Title;
    private String Description;
    private String Price;
    private String Quantity;
    private String Image;
    private String Seller;

    public Listing(String ListingID, String Title, String Description, String Price, String Quantity, String Image, String Seller) {
    	setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    	this.ListingID = ListingID;
    	this.Title = Title;
    	this.Description = Description;
    	this.Price = Price;
    	this.Quantity = Quantity;
    	this.Image = Image;
    	this.Seller = Seller;
    	initialize();
    }
    
    public String getListingID() {
    	return ListingID;
    }
    
    public void setListingID(String ListingID) {
    	this.ListingID = ListingID;
    }
    
    public String getTitle() {
    	return Title;
    }
    
    public void setTitle(String Title) {
    	this.Title = Title;
    }
    
    public String getDescription() {
    	return Description;
    }
    
    public void setDescription(String Description) {
    	this.Description = Description;
    }
    
    public String getPrice() {
    	return Price;
    }
    
    public void SetPrice(String Price) {
    	this.Price = Price;
    }
    
    public String getQuantity() {
    	return Quantity;
    }
    
    public void setQuantity(String Quantity) {
    	this.Quantity = Quantity;
    }
    
    public String getImage() {
    	return Image;
    }
    
    public void setImage(String Image) {
    	this.Image = Image;
    }
    
    public String getSeller() {
    	return Seller;
    }
    
    public void setSeller(String Seller) {
    	this.Seller = Seller;
    }
    
    private void initialize() {
    	setSize(600, 300);
        lblListingID = new JLabel("Listing #" + ListingID);
        lblListingID.setHorizontalAlignment(SwingConstants.RIGHT);
        lblListingID.setBounds(535, 6, 55, 14);
        setLayout(null);
        add(lblListingID);
        
        JLabel lblTitle = new JLabel(Title);
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblTitle.setBounds(10, 6, 151, 51);
        add(lblTitle);
        
        JTextPane txtDescription = new JTextPane();
        txtDescription.setEditable(false);
        txtDescription.setText(Description);
        txtDescription.setBounds(10, 121, 300, 140);
        add(txtDescription);
        
        JLabel lblPrice = new JLabel("$" + Price);
        lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblPrice.setBounds(10, 50, 108, 40);
        add(lblPrice);
        
        JLabel lblQuantity = new JLabel("Quantity: " + Quantity);
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblQuantity.setBounds(10, 90, 135, 20);
        add(lblQuantity);
        
        JLabel lblImage = new JLabel(Image);
        lblImage.setIcon(null);
        lblImage.setBounds(346, 31, 244, 244);
        add(lblImage);
        
        JLabel lblSeller = new JLabel("Seller: " + Seller);
        lblSeller.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSeller.setBounds(10, 266, 151, 20);
        add(lblSeller);
        
        JButton btnAddToCart = new JButton("Add To Cart");
        btnAddToCart.setBounds(175, 267, 135, 23);
        btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add functionality later...
			}
		});
        add(btnAddToCart);
        
    }
}
