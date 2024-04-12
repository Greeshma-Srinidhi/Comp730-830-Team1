package Storefront;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class CreateListing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTextField txtImage;
	private JTextField txtSeller;
	private String ListingID;
	private Listing listing = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CreateListing::new);
    }

	public CreateListing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPageTitle = new JLabel("Create A Listing");
		lblPageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageTitle.setBounds(10, 11, 120, 30);
		lblPageTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblPageTitle);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(44, 66, 86, 20);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(140, 66, 86, 20);
		contentPane.add(txtPrice);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(44, 52, 86, 14);
		contentPane.add(lblTitle);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(44, 152, 86, 14);
		contentPane.add(lblDescription);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(140, 52, 86, 14);
		contentPane.add(lblPrice);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(44, 111, 86, 20);
		contentPane.add(txtQuantity);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(44, 97, 86, 14);
		contentPane.add(lblQuantity);
		
		txtImage = new JTextField();
		txtImage.setColumns(10);
		txtImage.setBounds(236, 66, 86, 20);
		contentPane.add(txtImage);
		
		JLabel lblImage = new JLabel("Image URL");
		lblImage.setBounds(236, 52, 86, 14);
		contentPane.add(lblImage);
		
		txtSeller = new JTextField();
		txtSeller.setColumns(10);
		txtSeller.setBounds(140, 111, 86, 20);
		contentPane.add(txtSeller);
		
		JLabel lblSeller = new JLabel("Seller");
		lblSeller.setBounds(140, 97, 86, 14);
		contentPane.add(lblSeller);
		
		JTextArea txtDescription = new JTextArea();
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(44, 169, 278, 81);
		contentPane.add(txtDescription);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtTitle.getText().toString().isEmpty() || txtDescription.getText().toString().isEmpty() || txtPrice.getText().toString().isEmpty() 
				|| txtQuantity.getText().toString().isEmpty() || txtImage.getText().toString().isEmpty() || txtSeller.getText().toString().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all fields");
				} else {
					listing = new Listing(ListingID, txtTitle.getText(), txtDescription.getText(), txtPrice.getText(), txtQuantity.getText(), txtImage.getText(), txtSeller.getText());
					JOptionPane.showMessageDialog(null, "Listing #" + ListingID + " has been created.");
					setVisible(false);
				}
			}
		});
		btnSubmit.setBounds(335, 169, 89, 30);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listing = null;
				setVisible(false);
			}
		});
		btnCancel.setBounds(335, 220, 89, 30);
		contentPane.add(btnCancel);
	}
	
	public void setListingID(String ListingID) {
		this.ListingID = ListingID;
	}
	
	public Listing getCreatedListing() {
		return listing;
	}
}
