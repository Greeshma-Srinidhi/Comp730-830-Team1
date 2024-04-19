package Storefront;

import java.awt.EventQueue;
import java.awt.Font;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckOut extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Database database;
	private int database_size;
	private SimpleObserver observer;
	private double total;
	private Listing[] listings_array;
	private String[] carted_array = new String[255];
	private boolean complete_status = false;
	
	
	public CheckOut(Database database, int database_size, SimpleObserver observer, Listing[] listings_array, double total) {
		this.database = database;
		this.database_size = database_size;
		this.observer = observer;
		this.listings_array = listings_array;
		this.total = total;
		
		for (int i = 0; i < database_size; i++) {
			if (listings_array[i].getCarted() != false) {
        	String Title = listings_array[i].getTitle();
        	String Price = listings_array[i].getPrice();
        	carted_array[i] = Title + ": $" + Price;
			}
        }
		
		initialize_screen();
	}
	
	private void initialize_screen() {
		setTitle("Check Out");
        setSize(450, 300);    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPageTitle = new JLabel("Check Out");
		lblPageTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblPageTitle.setBounds(10, 11, 120, 30);
		lblPageTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblPageTitle);
		
		JTextField txtCardHolderName = new JTextField();
		txtCardHolderName.setBounds(242, 202, 182, 20);
		contentPane.add(txtCardHolderName);
		txtCardHolderName.setColumns(10);
		
		JTextField txtCardNumber = new JTextField();
		txtCardNumber.setColumns(10);
		txtCardNumber.setBounds(242, 114, 182, 20);
		contentPane.add(txtCardNumber);
		
		JLabel lblExpiration = new JLabel("Expiration");
		lblExpiration.setBounds(242, 145, 86, 14);
		contentPane.add(lblExpiration);
		
		JLabel lblItems = new JLabel("Items In Cart");
		lblItems.setBounds(10, 52, 86, 14);
		contentPane.add(lblItems);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setBounds(242, 100, 86, 14);
		contentPane.add(lblCardNumber);
		
		JTextField txtExpiration = new JTextField();
		txtExpiration.setColumns(10);
		txtExpiration.setBounds(242, 159, 86, 20);
		contentPane.add(txtExpiration);
		
		JLabel lblCardName = new JLabel("Cardholder's Name");
		lblCardName.setBounds(242, 187, 182, 14);
		contentPane.add(lblCardName);
		
		JTextField txtSecurityCode = new JTextField();
		txtSecurityCode.setColumns(10);
		txtSecurityCode.setBounds(338, 159, 86, 20);
		contentPane.add(txtSecurityCode);
		
		JLabel lblSecurityCode = new JLabel("Security Code");
		lblSecurityCode.setBounds(338, 145, 86, 14);
		contentPane.add(lblSecurityCode);
		
		JLabel lblTotal = new JLabel("Total: $" + total);
		lblTotal.setBounds(146, 52, 86, 14);
		contentPane.add(lblTotal);
		
		JList lst_ItemsInCart = new JList(carted_array);
		lst_ItemsInCart.setBounds(10, 68, 222, 183);
		contentPane.add(lst_ItemsInCart);
		
		JLabel lblPayment = new JLabel("Enter Payment Details");
		lblPayment.setHorizontalAlignment(SwingConstants.LEFT);
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPayment.setBounds(242, 59, 182, 30);
		contentPane.add(lblPayment);
		
		JButton btnCompletePayment = new JButton("Complete Payment");
		btnCompletePayment.setBounds(279, 13, 145, 30);
		contentPane.add(btnCompletePayment);
		btnCompletePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCardNumber.getText().toString().isEmpty() || txtExpiration.getText().toString().isEmpty() || txtSecurityCode.getText().toString().isEmpty() 
						|| txtCardHolderName.getText().toString().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please fill all fields");
				} else {
				for (int i = 0; i < database_size; i++) { 
					if (listings_array[i].getCarted() != false) {
						listings_array[i].setQuantity(Integer.toString((Integer.parseInt(listings_array[i].getQuantity()) - 1)));
					}
				}
				complete_status = true;
				JOptionPane.showMessageDialog(null, "Purchase Complete for $" + total);
				setVisible(false);
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(338, 228, 86, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		removeAll();
        		dispose();
			}
		});
	}
	
	public boolean getCompleteStatus() {
		return complete_status;
	}
}
