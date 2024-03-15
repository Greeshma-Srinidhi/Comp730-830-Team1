package Storefront;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.MatteBorder;

// Individual Listing Object (test for now)
class Listing extends JPanel {
    private JLabel lblListingID;
    private String ListingID;
    private String Title;
    private String Description;
    private String Price;
    private double PriceNum;
    private String Quantity;
    private int QuantityNum;
    private String Image;

    public Listing(String ListingID, String Title, String Description, String Price, String Quantity, String Image) {
    	setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    	this.ListingID = ListingID;
    	this.Title = Title;
    	this.Description = Description;
    	this.Price = Price;
    	this.PriceNum = Double.parseDouble(Price);
    	this.Quantity = Quantity;
    	this.QuantityNum = Integer.parseInt(Quantity);
    	this.Image = Image;
    	initialize();
    }
    
    private void initialize() {
    	setSize(600, 300);
        lblListingID = new JLabel(ListingID);
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
    }
}
