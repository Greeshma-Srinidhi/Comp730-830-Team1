package Storefront;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
    private boolean Carted = false;
    private JLabel lblTitle;
    private JTextPane txtDescription;
    private JLabel lblPrice;
    private JLabel lblQuantity;
    private JLabel lblAmountInCart;
    private JLabel lblImage;
    private JLabel lblSeller;
    private Image read_image;
    private JButton btnAddToCart;
    private JButton btnRemoveFromCart;
    private SimpleObserver observer;

    public Listing(SimpleObserver observer, String ListingID, String Title, String Description, String Price, String Quantity, String Image, String Seller) {
    	setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    	this.observer = observer;
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
     
    public boolean getCarted() {
    	return Carted;
    }
    
    public void setCarted(boolean Carted) {
    	this.Carted = Carted;
    }
    
    private void initialize() {
    	setSize(600, 300);
    	
        lblListingID = new JLabel("Listing #" + ListingID);
        lblListingID.setHorizontalAlignment(SwingConstants.RIGHT);
        lblListingID.setBounds(516, 6, 74, 14);
        setLayout(null);
        add(lblListingID);
        
        lblTitle = new JLabel(Title);
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblTitle.setBounds(10, 6, 151, 51);
        add(lblTitle);
        
        txtDescription = new JTextPane();
        txtDescription.setEditable(false);
        txtDescription.setText(Description);
        txtDescription.setBounds(10, 121, 300, 140);
        add(txtDescription);
        
        lblPrice = new JLabel("$" + Price);
        lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblPrice.setBounds(10, 50, 108, 40);
        add(lblPrice);
        
        lblQuantity = new JLabel("Quantity: " + Quantity);
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblQuantity.setBounds(10, 90, 135, 20);
        add(lblQuantity);
        
        lblAmountInCart = new JLabel("In Cart");
        lblAmountInCart.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAmountInCart.setBounds(10, 90, 135, 20);
        add(lblAmountInCart);
        
    	URL url;
		try {
			url = new URL(Image);
	    	read_image = ImageIO.read(url);
	    	Image scaledImage = read_image.getScaledInstance(244, 244, read_image.SCALE_SMOOTH);
	        lblImage = new JLabel(new ImageIcon(scaledImage));
	        lblImage.setBounds(346, 31, 244, 244);
	        add(lblImage);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IIOException l) {
			l.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
              
        lblSeller = new JLabel("Seller: " + Seller);
        lblSeller.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSeller.setBounds(10, 266, 151, 20);
        add(lblSeller);
        
        btnAddToCart = new JButton("Add To Cart");
        btnAddToCart.setBounds(175, 267, 135, 23);
        btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Carted = true;
			}
		});
        add(btnAddToCart);
        
        btnRemoveFromCart = new JButton("Remove From Cart");
        btnRemoveFromCart.setBounds(175, 267, 161, 23);
        add(btnRemoveFromCart);
        btnRemoveFromCart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Carted = false;
        		observer.notifyRemovedFromCart();
        		setVisible(false);
        	}
        });
        
        btnRemoveFromCart.setVisible(false);   
        lblAmountInCart.setVisible(false);
    }
    
    public void CartModeOn() {
    	btnAddToCart.setVisible(false);
    	lblQuantity.setVisible(false);
    	btnRemoveFromCart.setVisible(true);
    	lblAmountInCart.setVisible(true);
    }
    
    public void CartModeOff() {
    	btnAddToCart.setVisible(true);
    	lblQuantity.setVisible(true);
    	btnRemoveFromCart.setVisible(false);
    	lblAmountInCart.setVisible(false);
    }
}
