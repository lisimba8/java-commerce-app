import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private Customer customer;
	private JTable allProductsTable;
	private DefaultTableModel dtmProducts, dtmBasket, dtmSearchProducts;
	private ArrayList<Product> products = new ArrayList<Product>();
	private JTable basketTable;
	private JTextField totalCostTextField;
	private JTable searchProductsTable;
	private JTextField txtYourBasket;
	private JTextField emailTextField;
	private JTextField cardNumberTextField;
	private JTextField securityCodeTextField;

	public CustomerFrame(Customer customer) {
		this.customer = customer;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(175, -24, 901, 713);
		contentPane.add(tabbedPane);

		JPanel addToBasketPanel = new JPanel();
		tabbedPane.addTab("Add to basket", null, addToBasketPanel, null);
		addToBasketPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 711, 633);
		addToBasketPanel.add(scrollPane);

//		allProductsTable = new JTable();
//		scrollPane.setViewportView(allProductsTable);
		//////////
		allProductsTable = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // this prevents the table from being edited
			};
		};
		allProductsTable.setFont(new Font("Tahoma", Font.PLAIN, 9));
		scrollPane.setViewportView(allProductsTable);

		dtmProducts = new DefaultTableModel();
		dtmProducts.setColumnIdentifiers(new Object[] { "barcode", "device name", "device type", "brand", "colour",
				"connectivity", "quantity in stock", "retail price", "additional information" });
		allProductsTable.setModel(dtmProducts);
		products = Product.getProducts();
		createTable();

		/////////////
		String[] productIDs = Product.getProductIDs();
		JComboBox comboBox = new JComboBox(productIDs);
		comboBox.setBounds(731, 130, 150, 31);
		addToBasketPanel.add(comboBox);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(741, 226, 140, 188);
		addToBasketPanel.add(scrollPane_1);

		JTextPane addToBasketTextField = new JTextPane();
		scrollPane_1.setViewportView(addToBasketTextField);
		addToBasketTextField.setBackground(SystemColor.menu);
		addToBasketTextField.setEditable(false);

		JButton addToBasketbtn = new JButton("Add to Basket");
		addToBasketbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(comboBox.getSelectedItem().toString());
				products = Product.getProducts();
				for (Product product : products) {
					if (product.getBarcodeID() == selectedID) {
						String addStatus = customer.addToBasket(product);
						addToBasketTextField.setText(addStatus);
					}
				}
//				customer.viewBasket(); //used for debugging
			}
		});
		addToBasketbtn.setBounds(731, 169, 155, 44);
		addToBasketPanel.add(addToBasketbtn);

		JTextPane txtpnAllItemsCurrently = new JTextPane();
		txtpnAllItemsCurrently.setEditable(false);
		txtpnAllItemsCurrently.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnAllItemsCurrently.setBackground(SystemColor.menu);
		txtpnAllItemsCurrently.setText("All Items Currently Available");
		txtpnAllItemsCurrently.setBounds(337, 11, 170, 19);
		addToBasketPanel.add(txtpnAllItemsCurrently);

		JTextPane txtpnSelectProductId = new JTextPane();
		txtpnSelectProductId.setEditable(false);
		txtpnSelectProductId.setBackground(SystemColor.menu);
		txtpnSelectProductId.setText("Select product ID you would like to buy and add it to your basket");
		txtpnSelectProductId.setBounds(731, 41, 150, 93);
		addToBasketPanel.add(txtpnSelectProductId);

		JPanel viewBasketPanel = new JPanel();
		tabbedPane.addTab("View Basket", null, viewBasketPanel, null);
		viewBasketPanel.setLayout(null);

		totalCostTextField = new JTextField();
		totalCostTextField.setEditable(false);
		totalCostTextField.setBackground(SystemColor.menu);
		totalCostTextField.setBounds(788, 62, 98, 25);
		viewBasketPanel.add(totalCostTextField);
		totalCostTextField.setColumns(10);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 32, 764, 557);
		viewBasketPanel.add(scrollPane_2);

		basketTable = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // this prevents the table from being edited
			};
		};
		basketTable.setFont(new Font("Tahoma", Font.PLAIN, 9));
		scrollPane_2.setViewportView(basketTable);

		dtmBasket = new DefaultTableModel();
		dtmBasket.setColumnIdentifiers(new Object[] { "barcode", "device name", "device type", "brand", "colour",
				"connectivity", "number in basket", "retail price", "additional information" });
		basketTable.setModel(dtmBasket);
		createBasketTable();

		JButton clearBasketBtn = new JButton("Cancel Basket");
		clearBasketBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer.clearBasket();
				createBasketTable();
			}
		});
		clearBasketBtn.setBounds(774, 361, 122, 45);
		viewBasketPanel.add(clearBasketBtn);

		JPanel paypalPanel = new JPanel();
		tabbedPane.addTab("Paypal", null, paypalPanel, null);
		paypalPanel.setLayout(null);

		JPanel visaPanel = new JPanel();
		tabbedPane.addTab("Visa", null, visaPanel, null);
		visaPanel.setLayout(null);

		JButton visaPayBtn = new JButton("VISA");
		visaPayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customer.getBasketHashMap() != null) {
					tabbedPane.setSelectedComponent(visaPanel);
				}
			}
		});
		visaPayBtn.setBounds(788, 210, 98, 45);
		viewBasketPanel.add(visaPayBtn);

		JButton paypalPayBtn = new JButton("Paypal");
		paypalPayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customer.getBasketHashMap() != null) {
					tabbedPane.setSelectedComponent(paypalPanel);
				}
			}
		});
		paypalPayBtn.setBounds(788, 266, 98, 45);
		viewBasketPanel.add(paypalPayBtn);

		JTextPane txtpnTheTotalCost = new JTextPane();
		txtpnTheTotalCost.setEditable(false);
		txtpnTheTotalCost.setBackground(SystemColor.menu);
		txtpnTheTotalCost.setText("The total cost is:");
		txtpnTheTotalCost.setBounds(788, 32, 98, 35);
		viewBasketPanel.add(txtpnTheTotalCost);

		JTextPane txtpnSelectYourPayment = new JTextPane();
		txtpnSelectYourPayment.setText("Select your Payment method");
		txtpnSelectYourPayment.setBackground(SystemColor.menu);
		txtpnSelectYourPayment.setEditable(false);
		txtpnSelectYourPayment.setBounds(788, 146, 98, 53);
		viewBasketPanel.add(txtpnSelectYourPayment);

		txtYourBasket = new JTextField();
		txtYourBasket.setEditable(false);
		txtYourBasket.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtYourBasket.setText("Your Basket");
		txtYourBasket.setBackground(SystemColor.menu);
		txtYourBasket.setBounds(396, 11, 81, 20);
		viewBasketPanel.add(txtYourBasket);
		txtYourBasket.setColumns(10);

		JTextPane txtpnOr = new JTextPane();
		txtpnOr.setText("Or:");
		txtpnOr.setEditable(false);
		txtpnOr.setBackground(SystemColor.menu);
		txtpnOr.setBounds(815, 322, 46, 28);
		viewBasketPanel.add(txtpnOr);

		JPanel searchPanel = new JPanel();
		tabbedPane.addTab("Search Items", null, searchPanel, null);
		searchPanel.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 87, 720, 536);
		searchPanel.add(scrollPane_3);

		searchProductsTable = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // this prevents the table from being edited
			};
		};
		searchProductsTable.setFont(new Font("Tahoma", Font.PLAIN, 9));
		scrollPane_3.setViewportView(searchProductsTable);

		dtmSearchProducts = new DefaultTableModel();
		dtmSearchProducts.setColumnIdentifiers(new Object[] { "barcode", "device name", "device type", "brand",
				"colour", "connectivity", "quantity in stock", "retail price", "additional information" });
		searchProductsTable.setModel(dtmSearchProducts);

		String[] productBrands = Product.getProductBrands();
		JComboBox brandComboBox = new JComboBox(productBrands);
		brandComboBox.setSelectedIndex(-1);
		brandComboBox.setBounds(171, 32, 87, 22);
		searchPanel.add(brandComboBox);

		JLabel lblNewLabel = new JLabel("Brand:");
		lblNewLabel.setBounds(132, 36, 49, 14);
		searchPanel.add(lblNewLabel);

		String[] mouseButtons = Mouse.getMouseButtons();
		JComboBox NumOfButtonsComboBox = new JComboBox(mouseButtons);
		NumOfButtonsComboBox.setSelectedIndex(-1);
		NumOfButtonsComboBox.setBounds(407, 32, 87, 22);
		searchPanel.add(NumOfButtonsComboBox);

		JLabel lblNewLabel_1 = new JLabel("Number of Buttons:");
		lblNewLabel_1.setBounds(278, 36, 119, 14);
		searchPanel.add(lblNewLabel_1);

		JTextPane txtpnSelectProductId_1 = new JTextPane();
		txtpnSelectProductId_1.setText("Select product ID you would like to buy and add it to your basket");
		txtpnSelectProductId_1.setEditable(false);
		txtpnSelectProductId_1.setBackground(SystemColor.menu);
		txtpnSelectProductId_1.setBounds(740, 32, 146, 93);
		searchPanel.add(txtpnSelectProductId_1);

		DefaultComboBoxModel DCBMProduct = new DefaultComboBoxModel();

		JComboBox searchProductIDComboBox = new JComboBox(new Object[] {});
		searchProductIDComboBox.setBounds(740, 121, 146, 31);
		searchPanel.add(searchProductIDComboBox);
		searchProductIDComboBox.setModel(DCBMProduct);

		JTextPane addToBasketTextField_1 = new JTextPane();
		addToBasketTextField_1.setEditable(false);
		addToBasketTextField_1.setBackground(SystemColor.menu);
		addToBasketTextField_1.setBounds(740, 219, 146, 170);
		searchPanel.add(addToBasketTextField_1);

		JButton addToBasketbtn_1 = new JButton("Add to Basket");
		addToBasketbtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(searchProductIDComboBox.getSelectedItem().toString());
				products = Product.getProducts();
				for (Product product : products) {
					if (product.getBarcodeID() == selectedID) {
						String addStatus = customer.addToBasket(product);
						addToBasketTextField_1.setText(addStatus);
					}
				}
			}
		});
		addToBasketbtn_1.setBounds(740, 160, 146, 44);
		searchPanel.add(addToBasketbtn_1);

		JButton searchItemsBtn = new JButton("Search");
		searchItemsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] productIDs;
				if (brandComboBox.getSelectedItem() != null && NumOfButtonsComboBox.getSelectedItem() != null) {
					productIDs = createSearchTable(brandComboBox.getSelectedItem().toString(),
							NumOfButtonsComboBox.getSelectedItem().toString());

					DCBMProduct.removeAllElements();
					for (int i = 0; i < productIDs.length; i++) {
						DCBMProduct.addElement(productIDs[i]);
					}
					searchProductIDComboBox.setModel(DCBMProduct);

				} else if (brandComboBox.getSelectedItem() != null) {
					// create table with brand
					productIDs = createSearchTable(brandComboBox.getSelectedItem().toString());
					DCBMProduct.removeAllElements();
					for (int i = 0; i < productIDs.length; i++) {
						DCBMProduct.addElement(productIDs[i]);
					}
					searchProductIDComboBox.setModel(DCBMProduct);

				} else if (NumOfButtonsComboBox.getSelectedItem() != null) {
					// create table with number of buttons
//					createMouseSearchTable
					productIDs = createMosueSearchTable(NumOfButtonsComboBox.getSelectedItem().toString());
					DCBMProduct.removeAllElements();
					for (int i = 0; i < productIDs.length; i++) {
						DCBMProduct.addElement(productIDs[i]);
					}
					searchProductIDComboBox.setModel(DCBMProduct);
				} else {
					// create full table
					productIDs = createSearchTable();
					DCBMProduct.removeAllElements();
					for (int i = 0; i < productIDs.length; i++) {
						DCBMProduct.addElement(productIDs[i]);
					}
					searchProductIDComboBox.setModel(DCBMProduct);
				}
			}
		});
		searchItemsBtn.setBounds(407, 61, 80, 22);
		searchPanel.add(searchItemsBtn);

		JTextPane txtpnSearchByThe = new JTextPane();
		txtpnSearchByThe.setEditable(false);
		txtpnSearchByThe.setText("Search by the brand and/or the number of buttons (for mouse only)");
		txtpnSearchByThe.setBackground(SystemColor.menu);
		txtpnSearchByThe.setBounds(132, 0, 385, 22);
		searchPanel.add(txtpnSearchByThe);

		JLabel lblNewLabel_2 = new JLabel("You can leave any of the fields blank as well");
		lblNewLabel_2.setBounds(132, 63, 265, 18);
		searchPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Pay with PayPal");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(390, 150, 204, 41);
		paypalPanel.add(lblNewLabel_3);

		JButton paypal1BackBtn = new JButton("Back");
		paypal1BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(viewBasketPanel);
			}
		});
		paypal1BackBtn.setBounds(221, 163, 89, 23);
		paypalPanel.add(paypal1BackBtn);

		JTextPane txtpnPleaseEnterYour = new JTextPane();
		txtpnPleaseEnterYour.setEditable(false);
		txtpnPleaseEnterYour.setBackground(SystemColor.menu);
		txtpnPleaseEnterYour.setText("Please enter your email address and press pay:");
		txtpnPleaseEnterYour.setBounds(370, 219, 235, 49);
		paypalPanel.add(txtpnPleaseEnterYour);

		JPanel successfulPaymentPanel = new JPanel();
		tabbedPane.addTab("Success", null, successfulPaymentPanel, null);
		successfulPaymentPanel.setLayout(null);

		JTextPane paypalPayStatusTextField = new JTextPane();
		paypalPayStatusTextField.setEditable(false);
		paypalPayStatusTextField.setBackground(SystemColor.menu);
		paypalPayStatusTextField.setBounds(360, 387, 234, 49);
		visaPanel.add(paypalPayStatusTextField);

		emailTextField = new JTextField();
		emailTextField.setBounds(370, 268, 204, 20);
		paypalPanel.add(emailTextField);
		emailTextField.setColumns(10);

		JTextPane paypal1PayStatusTextField = new JTextPane();
		paypal1PayStatusTextField.setEditable(false);
		paypal1PayStatusTextField.setBackground(SystemColor.menu);
		paypal1PayStatusTextField.setBounds(360, 356, 234, 49);
		paypalPanel.add(paypal1PayStatusTextField);

		JTextPane successMessageTextField = new JTextPane();
		successMessageTextField.setEditable(false);
		successMessageTextField.setBackground(SystemColor.menu);
		successMessageTextField.setBounds(284, 129, 320, 125);
		successfulPaymentPanel.add(successMessageTextField);

		JButton payBtn = new JButton("Pay");
		payBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = emailTextField.getText();

				String payStatus = customer.pay(email);
				if (payStatus.equals("")) {
					tabbedPane.setSelectedComponent(successfulPaymentPanel);
					emailTextField.setText("");
					successMessageTextField.setText(String.format("%.2f", customer.getBasketTotal())
							+ " paid using Paypal, and the delivery address is " + customer.getAddress().toString());
				}
				paypal1PayStatusTextField.setText(payStatus);
			}
		});
		payBtn.setBounds(424, 309, 89, 23);
		paypalPanel.add(payBtn);

		JLabel lblNewLabel_3_1 = new JLabel("Pay with VISA");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3_1.setBounds(390, 133, 204, 41);
		visaPanel.add(lblNewLabel_3_1);

		JButton visaBackBtn = new JButton("Back");
		visaBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(viewBasketPanel);
			}
		});
		visaBackBtn.setBounds(235, 146, 89, 23);
		visaPanel.add(visaBackBtn);

		JTextPane txtpnPleaseEnterYour_1 = new JTextPane();
		txtpnPleaseEnterYour_1.setEditable(false);
		txtpnPleaseEnterYour_1
				.setText("Please enter your 6 digit card number and your 3 digit security code, and then press pay:");
		txtpnPleaseEnterYour_1.setBackground(SystemColor.menu);
		txtpnPleaseEnterYour_1.setBounds(360, 231, 234, 49);
		visaPanel.add(txtpnPleaseEnterYour_1);

		cardNumberTextField = new JTextField();
		cardNumberTextField.setColumns(10);
		cardNumberTextField.setBounds(471, 280, 123, 20);
		visaPanel.add(cardNumberTextField);

		JButton successBackBtn = new JButton("Back");
		successBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(addToBasketPanel);
				products = Product.getProducts();
				createTable();
			}
		});
		successBackBtn.setBounds(405, 331, 89, 45);
		successfulPaymentPanel.add(successBackBtn);

		JButton payBtn_1 = new JButton("Pay");
		payBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cardNumber = cardNumberTextField.getText();
				String securityCode = securityCodeTextField.getText();

				String payStatus = customer.pay(cardNumber, securityCode);
				if (payStatus.equals("")) {
					tabbedPane.setSelectedComponent(successfulPaymentPanel);
					cardNumberTextField.setText("");
					securityCodeTextField.setText("");
					successMessageTextField.setText(String.format("%.2f", customer.getBasketTotal())
							+ " paid using Credit Card, and the delivery address is "
							+ customer.getAddress().toString());
				}
				paypalPayStatusTextField.setText(payStatus);
			}
		});
		payBtn_1.setBounds(414, 352, 89, 23);
		visaPanel.add(payBtn_1);

		JLabel lblNewLabel_4 = new JLabel("Card Number:");
		lblNewLabel_4.setBounds(360, 283, 101, 14);
		visaPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Security code:");
		lblNewLabel_4_1.setBounds(360, 317, 101, 14);
		visaPanel.add(lblNewLabel_4_1);

		securityCodeTextField = new JTextField();
		securityCodeTextField.setColumns(10);
		securityCodeTextField.setBounds(471, 314, 123, 20);
		visaPanel.add(securityCodeTextField);

		JButton addToBasketTabBtn = new JButton("View Catalogue");
		addToBasketTabBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(addToBasketPanel);
				addToBasketTextField.setText("");
				createTable();
			}
		});
		addToBasketTabBtn.setBounds(10, 109, 155, 52);
		contentPane.add(addToBasketTabBtn);

		JButton viewBasketBtn = new JButton("View Basket");
		viewBasketBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(viewBasketPanel);
				createBasketTable();
			}
		});
		viewBasketBtn.setBounds(10, 172, 155, 52);
		contentPane.add(viewBasketBtn);

		JButton searchPanelBtn = new JButton("Search Items");
		searchPanelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(searchPanel);
				products = Product.getProducts();
				NumOfButtonsComboBox.setSelectedIndex(-1);
				brandComboBox.setSelectedIndex(-1);
				addToBasketTextField_1.setText("");

			}
		});
		searchPanelBtn.setBounds(10, 235, 155, 52);
		contentPane.add(searchPanelBtn);
	}

	private void createTable() {
		dtmProducts.setRowCount(0);
		ArrayList<Product> tempProductsList = new ArrayList<Product>();
		tempProductsList = products;
		Collections.sort(tempProductsList, new RetailPriceCompare());// sort the list according to retail price
		for (Product product : tempProductsList) {
			if (product instanceof Keyboard) {
				Keyboard keyboard = (Keyboard) product; // cast each product to it's keyboard type to access Keyboard
														// methods
				Object[] row = new Object[] { keyboard.getBarcodeID(), "Keyboard", keyboard.getType(),
						keyboard.getBrand(), keyboard.getColour(), keyboard.getConnectivity().toString(),
						keyboard.getQuantityInStock(), keyboard.getRetailPrice(), keyboard.getLayout() + " Layout" };
				dtmProducts.addRow(row);
			} else {
				Mouse mouse = (Mouse) product; // cast each product to it's mouse type to access mouse methods
				Object[] row = new Object[] { mouse.getBarcodeID(), "Mouse", mouse.getType(), mouse.getBrand(),
						mouse.getColour(), mouse.getConnectivity().toString(), mouse.getQuantityInStock(),
						mouse.getRetailPrice(), mouse.getNumOfButtons() + " Button(s)" };
				dtmProducts.addRow(row);
			}
		}
	}

	private void createBasketTable() {
		dtmBasket.setRowCount(0);
		//// get array list of products from the cutomer's basket
//		ArrayList<Product> tempProductsList = new ArrayList<Product>();
		ArrayList<Product> customerBasket = customer.getBasketArrayList();
		double totalCost = 0.00;

		/////////////////////
		for (Product product : customerBasket) {
			HashMap<Integer, Integer> productMap = customer.getBasketHashMap();
			Integer key = product.getBarcodeID();
			Integer quantityInBasket = productMap.get(key);

			if (product instanceof Keyboard) {
				Keyboard keyboard = (Keyboard) product; // cast each product to it's keyboard type to access Keyboard
														// methods
				Object[] row = new Object[] { keyboard.getBarcodeID(), "Keyboard", keyboard.getType(),
						keyboard.getBrand(), keyboard.getColour(), keyboard.getConnectivity().toString(),
						quantityInBasket, keyboard.getRetailPrice(), keyboard.getLayout() + " Layout" };
				dtmBasket.addRow(row);
				totalCost += (keyboard.getRetailPrice() * quantityInBasket);
			} else {
				Mouse mouse = (Mouse) product; // cast each product to it's mouse type to access mouse methods
				Object[] row = new Object[] { mouse.getBarcodeID(), "Mouse", mouse.getType(), mouse.getBrand(),
						mouse.getColour(), mouse.getConnectivity().toString(), quantityInBasket, mouse.getRetailPrice(),
						mouse.getNumOfButtons() + " Button(s)" };
				dtmBasket.addRow(row);
				totalCost += (mouse.getRetailPrice() * quantityInBasket);
			}
		}
		/////////////////////////
		totalCostTextField.setText(String.format("%.2f", totalCost));
		//// use the create table from there
	}

	private String[] createSearchTable(String brand, String numOfButtons) { // returns array of the id's in the table
		dtmSearchProducts.setRowCount(0);
		ArrayList<Product> tempProductsList = new ArrayList<Product>();

		ArrayList<String> productIDs = new ArrayList<String>();

		tempProductsList = products;
		Collections.sort(tempProductsList, new RetailPriceCompare());// sort the list according to retail price
		for (Product product : tempProductsList) {
			if (product instanceof Mouse) {
				Mouse mouse = (Mouse) product; // cast each product to it's mouse type to access mouse methods
				if (product.getBrand().equals(brand) && numOfButtons.equals(String.valueOf(mouse.getNumOfButtons()))) {
					Object[] row = new Object[] { mouse.getBarcodeID(), "Mouse", mouse.getType(), mouse.getBrand(),
							mouse.getColour(), mouse.getConnectivity().toString(), mouse.getQuantityInStock(),
							mouse.getRetailPrice(), mouse.getNumOfButtons() + " Button(s)" };
					dtmSearchProducts.addRow(row);
					productIDs.add(String.valueOf(mouse.getBarcodeID()));
				}
			}
		}
		String[] arr = productIDs.toArray(new String[0]);
		return arr;

	}

	private String[] createSearchTable(String brand) {
		dtmSearchProducts.setRowCount(0);
		ArrayList<Product> tempProductsList = new ArrayList<Product>();

		ArrayList<String> productIDs = new ArrayList<String>();

		tempProductsList = products;
		Collections.sort(tempProductsList, new RetailPriceCompare());// sort the list according to retail price
		for (Product product : tempProductsList) {
			if (product.getBrand().equals(brand)) {
				if (product instanceof Keyboard) {
					Keyboard keyboard = (Keyboard) product; // cast each product to it's keyboard type to access
															// Keyboard
															// methods
					Object[] row = new Object[] { keyboard.getBarcodeID(), "Keyboard", keyboard.getType(),
							keyboard.getBrand(), keyboard.getColour(), keyboard.getConnectivity().toString(),
							keyboard.getQuantityInStock(), keyboard.getRetailPrice(),
							keyboard.getLayout() + " Layout" };
					dtmSearchProducts.addRow(row);
				} else {
					Mouse mouse = (Mouse) product; // cast each product to it's mouse type to access mouse methods
					Object[] row = new Object[] { mouse.getBarcodeID(), "Mouse", mouse.getType(), mouse.getBrand(),
							mouse.getColour(), mouse.getConnectivity().toString(), mouse.getQuantityInStock(),
							mouse.getRetailPrice(), mouse.getNumOfButtons() + " Button(s)" };
					dtmSearchProducts.addRow(row);
				}
				productIDs.add(String.valueOf(product.getBarcodeID()));
			}
		}
		String[] arr = productIDs.toArray(new String[0]);
		return arr;
	}

	private String[] createMosueSearchTable(String numOfButtons) {
		dtmSearchProducts.setRowCount(0);
		ArrayList<Product> tempProductsList = new ArrayList<Product>();

		ArrayList<String> productIDs = new ArrayList<String>();

		tempProductsList = products;
		Collections.sort(tempProductsList, new RetailPriceCompare());// sort the list according to retail price
		for (Product product : tempProductsList) {
			if (product instanceof Mouse) {
				Mouse mouse = (Mouse) product; // cast each product to it's mouse type to access mouse methods
				if (numOfButtons.equals(String.valueOf(mouse.getNumOfButtons()))) {
					Object[] row = new Object[] { mouse.getBarcodeID(), "Mouse", mouse.getType(), mouse.getBrand(),
							mouse.getColour(), mouse.getConnectivity().toString(), mouse.getQuantityInStock(),
							mouse.getRetailPrice(), mouse.getNumOfButtons() + " Button(s)" };
					dtmSearchProducts.addRow(row);
					productIDs.add(String.valueOf(mouse.getBarcodeID()));
				}
			}
		}
		String[] arr = productIDs.toArray(new String[0]);
		return arr;
	}

	private String[] createSearchTable() {
		dtmSearchProducts.setRowCount(0);
		ArrayList<Product> tempProductsList = new ArrayList<Product>();
		ArrayList<String> productIDs = new ArrayList<String>();
		tempProductsList = products;
		Collections.sort(tempProductsList, new RetailPriceCompare());// sort the list according to retail price
		for (Product product : tempProductsList) {
			if (product instanceof Keyboard) {
				Keyboard keyboard = (Keyboard) product; // cast each product to it's keyboard type to access Keyboard
														// methods
				Object[] row = new Object[] { keyboard.getBarcodeID(), "Keyboard", keyboard.getType(),
						keyboard.getBrand(), keyboard.getColour(), keyboard.getConnectivity().toString(),
						keyboard.getQuantityInStock(), keyboard.getRetailPrice(), keyboard.getLayout() + " Layout" };
				dtmSearchProducts.addRow(row);
			} else {
				Mouse mouse = (Mouse) product; // cast each product to it's mouse type to access mouse methods
				Object[] row = new Object[] { mouse.getBarcodeID(), "Mouse", mouse.getType(), mouse.getBrand(),
						mouse.getColour(), mouse.getConnectivity().toString(), mouse.getQuantityInStock(),
						mouse.getRetailPrice(), mouse.getNumOfButtons() + " Button(s)" };
				dtmSearchProducts.addRow(row);
			}
			productIDs.add(String.valueOf(product.getBarcodeID()));
		}
		String[] arr = productIDs.toArray(new String[0]);
		return arr;
	}
}
