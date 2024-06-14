import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JTextField keyboardBarcodeIDTxtField;
	private JTextField keyboardBrandTxtField;
	private JTextField keyboardColourTxtField;
	private JTextField keyboardOriginalCostTxtField;
	private JTextField keyboardQuantityInStockTxtField;
	private JTextField keyboardRetailPriceTxtField;
	private JTextField mouseBarcodeIDTxtField;
	private JTextField mouseBrandTxtField;
	private JTextField mouseColourTxtField;
	private JTextField mouseOriginalCostTxtField;
	private JTextField mouseQuantityInStockTxtField;
	private JTextField mouseRetailPriceTxtField;
	private JTextField mouseNumberOfButtonsTxtField;
	private JTable allProductsTable;
	private DefaultTableModel dtmProducts;

	private ArrayList<Product> products = new ArrayList<Product>();

	public AdminFrame(Admin admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(177, -30, 899, 719);
		contentPane.add(tabbedPane);

		JPanel selectNewProduct = new JPanel();
		selectNewProduct.setBorder(null);
		tabbedPane.addTab("Select New Product", null, selectNewProduct, null);
		selectNewProduct.setLayout(null);

		JTextPane txtpnPleaseSelectThe = new JTextPane();
		txtpnPleaseSelectThe.setEditable(false);
		txtpnPleaseSelectThe.setBackground(SystemColor.menu);
		txtpnPleaseSelectThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtpnPleaseSelectThe.setText("Please select the type of product you would like to add");
		txtpnPleaseSelectThe.setBounds(313, 103, 245, 43);
		selectNewProduct.add(txtpnPleaseSelectThe);

		JPanel allProducts = new JPanel();
		allProducts.setBorder(null);
		tabbedPane.addTab("View All Product", null, allProducts, null);
		allProducts.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 884, 680);
		allProducts.add(scrollPane);

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
				"connectivity", "quantity in stock", "original cost", "retail price", "additional information" });
		allProductsTable.setModel(dtmProducts);

		JPanel addKeyboardTab = new JPanel();
		addKeyboardTab.setBorder(null);
		tabbedPane.addTab("addKeyboard", null, addKeyboardTab, null);
		addKeyboardTab.setLayout(null);

		keyboardBarcodeIDTxtField = new JTextField();
		keyboardBarcodeIDTxtField.setBounds(330, 125, 96, 20);
		addKeyboardTab.add(keyboardBarcodeIDTxtField);
		keyboardBarcodeIDTxtField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Barcode ID:");
		lblNewLabel.setBounds(218, 128, 102, 17);
		addKeyboardTab.add(lblNewLabel);

		keyboardBrandTxtField = new JTextField();
		keyboardBrandTxtField.setBounds(564, 125, 96, 20);
		addKeyboardTab.add(keyboardBrandTxtField);
		keyboardBrandTxtField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Brand:");
		lblNewLabel_1.setBounds(452, 125, 115, 20);
		addKeyboardTab.add(lblNewLabel_1);

		keyboardColourTxtField = new JTextField();
		keyboardColourTxtField.setBounds(330, 156, 96, 20);
		addKeyboardTab.add(keyboardColourTxtField);
		keyboardColourTxtField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Colour:");
		lblNewLabel_2.setBounds(218, 153, 102, 23);
		addKeyboardTab.add(lblNewLabel_2);

		JComboBox keyboardConnectivityComboBox = new JComboBox(Connectivity.values());
		keyboardConnectivityComboBox.setBounds(564, 156, 96, 20);
		addKeyboardTab.add(keyboardConnectivityComboBox);

		JLabel lblNewLabel_3 = new JLabel("Connectivity:");
		lblNewLabel_3.setBounds(452, 156, 115, 20);
		addKeyboardTab.add(lblNewLabel_3);

		keyboardOriginalCostTxtField = new JTextField();
		keyboardOriginalCostTxtField.setColumns(10);
		keyboardOriginalCostTxtField.setBounds(330, 187, 96, 20);
		addKeyboardTab.add(keyboardOriginalCostTxtField);

		JLabel lblNewLabel_2_1 = new JLabel("Original Cost:");
		lblNewLabel_2_1.setBounds(218, 190, 102, 17);
		addKeyboardTab.add(lblNewLabel_2_1);

		keyboardQuantityInStockTxtField = new JTextField();
		keyboardQuantityInStockTxtField.setColumns(10);
		keyboardQuantityInStockTxtField.setBounds(564, 187, 96, 20);
		addKeyboardTab.add(keyboardQuantityInStockTxtField);

		JLabel lblNewLabel_2_2 = new JLabel("Quantity In Stock:");
		lblNewLabel_2_2.setBounds(452, 187, 131, 20);
		addKeyboardTab.add(lblNewLabel_2_2);

		keyboardRetailPriceTxtField = new JTextField();
		keyboardRetailPriceTxtField.setColumns(10);
		keyboardRetailPriceTxtField.setBounds(330, 218, 96, 20);
		addKeyboardTab.add(keyboardRetailPriceTxtField);

		JLabel lblNewLabel_2_3 = new JLabel("Retail Price:");
		lblNewLabel_2_3.setBounds(218, 221, 102, 17);
		addKeyboardTab.add(lblNewLabel_2_3);

		JComboBox keyboardTypeComboBox = new JComboBox(KeyboardType.values());
		keyboardTypeComboBox.setBounds(564, 218, 96, 20);
		addKeyboardTab.add(keyboardTypeComboBox);

		JLabel lblNewLabel_3_1 = new JLabel("Keyboard Type:");
		lblNewLabel_3_1.setBounds(452, 218, 115, 20);
		addKeyboardTab.add(lblNewLabel_3_1);

		JComboBox keyboardLayoutComboBox = new JComboBox(KeyboardLayout.values());
		keyboardLayoutComboBox.setBounds(330, 249, 96, 20);
		addKeyboardTab.add(keyboardLayoutComboBox);

		JLabel lblNewLabel_3_2 = new JLabel("Keyboard Layout:");
		lblNewLabel_3_2.setBounds(218, 252, 105, 17);
		addKeyboardTab.add(lblNewLabel_3_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(330, 342, 290, 126);
		addKeyboardTab.add(scrollPane_1);

		JTextPane keyboardErrorMessageTxt = new JTextPane();
		scrollPane_1.setViewportView(keyboardErrorMessageTxt);
		keyboardErrorMessageTxt.setEditable(false);
		keyboardErrorMessageTxt.setBackground(SystemColor.menu);

		JButton btnAddNewKeyboard = new JButton("Add New Keyboard");
		btnAddNewKeyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean errorOccured = false;
				Keyboard keyboard = null;
				String errorMsg = "";
				try {
					////////////////////////////////////////////////////////////////////////////////
					try {
						String val = keyboardBarcodeIDTxtField.getText().trim();
						if (val.trim().matches("\\d{6}")) {
							products = Admin.viewAllProducts();
							for (Product product : products) {
								if (product.getBarcodeID() == Integer.parseInt(val)) {
									errorOccured = true;
									throw new Exception("ID should be unique!");
								}
							}
							///// return the integer value
						} else {
							errorOccured = true;
							throw new Exception("Incorrect Format of ID. Should be 6 digits long.");
						}
					} catch (Exception e1) {
						errorMsg += e1.getMessage() + "\n";
//						System.out.println(e1.getMessage());
					}

					try {
						Double.parseDouble(keyboardOriginalCostTxtField.getText().trim());
					} catch (Exception originalCostError) {
						// TODO: handle exception
						errorMsg += "The Original cost is invalid. Should be a number!\n";
//						System.out.println("The Original cost is invalid. Should be a number!");
					}

					try {
						Integer.parseInt(keyboardQuantityInStockTxtField.getText().trim());
					} catch (Exception quantityError) {
						// TODO: handle exception
						errorMsg += "The quantity should be a number.\n";
//						System.out.println("The quantity should be a number.");
					}

					try {
						Double.parseDouble(keyboardRetailPriceTxtField.getText().trim());
					} catch (Exception retailPriceError) {
						// TODO: handle exception
						errorMsg += "The Retail Price is invalid. Should be a number!\n";
//						System.out.println("The Retail Price is invalid. Should be a number!");
					}
					//////////////////////////////////////////////////////////////////////////////////
					keyboard = new Keyboard(Integer.parseInt(keyboardBarcodeIDTxtField.getText().trim()),
							keyboardBrandTxtField.getText().trim(), keyboardColourTxtField.getText().trim(),
							Connectivity.valueOf(keyboardConnectivityComboBox.getSelectedItem().toString()),
							Double.parseDouble(keyboardOriginalCostTxtField.getText().trim()),
							Integer.parseInt(keyboardQuantityInStockTxtField.getText().trim()),
							Double.parseDouble(keyboardRetailPriceTxtField.getText().trim()),
							KeyboardType.valueOf(keyboardTypeComboBox.getSelectedItem().toString()),
							KeyboardLayout.valueOf(keyboardLayoutComboBox.getSelectedItem().toString()));

				} catch (Exception e2) {
					errorMsg += "Error in your entries! Remember, no fields must be left empty.\n";
//					System.out.println("Error in your entries! Remember, no fields must be left empty.");
					errorOccured = true;
				} finally {
					if (!errorOccured) {
						Admin.addNewProduct(keyboard); // adding the product to the file
						keyboardErrorMessageTxt.setText("Keyboard Added Succesfully");
						clearTxtFields();
					} else {
						keyboardErrorMessageTxt.setText(errorMsg);
					}
				}

//				System.out.println(keyboardBarcodeIDTxtField.getText());
//				System.out.println(keyboardBrandTxtField.getText());
//				System.out.println(keyboardColourTxtField.getText());
//				System.out.println(keyboardConnectivityComboBox.getSelectedItem().toString());
//				System.out.println(keyboardOriginalCostTxtField.getText());
//				System.out.println(keyboardQuantityInStockTxtField.getText());
//				System.out.println(keyboardRetailPriceTxtField.getText());
//				System.out.println(keyboardTypeComboBox.getSelectedItem().toString());
//				System.out.println(keyboardLayoutComboBox.getSelectedItem().toString());

			}
		});
		btnAddNewKeyboard.setBounds(381, 280, 178, 51);
		addKeyboardTab.add(btnAddNewKeyboard);

		JButton keyboardBackBtn = new JButton("Back");
		keyboardBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(selectNewProduct);
			}
		});
		keyboardBackBtn.setBounds(198, 94, 89, 23);
		addKeyboardTab.add(keyboardBackBtn);

		JTextPane txtpnPleaseEnterThe = new JTextPane();
		txtpnPleaseEnterThe.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnPleaseEnterThe.setBackground(SystemColor.menu);
		txtpnPleaseEnterThe.setEditable(false);
		txtpnPleaseEnterThe.setText("Enter details of the new Keyboard");
		txtpnPleaseEnterThe.setBounds(346, 96, 201, 20);
		addKeyboardTab.add(txtpnPleaseEnterThe);

		JPanel addMouseTab = new JPanel();
		addMouseTab.setBorder(null);
		tabbedPane.addTab("addMouse", null, addMouseTab, null);
		addMouseTab.setLayout(null);

		JButton mouseBackBtn = new JButton("Back");
		mouseBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(selectNewProduct);
			}
		});
		mouseBackBtn.setBounds(190, 105, 89, 23);
		addMouseTab.add(mouseBackBtn);

		JTextPane txtpnEnterDetailsOf = new JTextPane();
		txtpnEnterDetailsOf.setText("Enter details of the new Mouse");
		txtpnEnterDetailsOf.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnEnterDetailsOf.setEditable(false);
		txtpnEnterDetailsOf.setBackground(SystemColor.menu);
		txtpnEnterDetailsOf.setBounds(358, 108, 184, 20);
		addMouseTab.add(txtpnEnterDetailsOf);

		JLabel lblNewLabel_4 = new JLabel("Barcode ID:");
		lblNewLabel_4.setBounds(210, 140, 102, 17);
		addMouseTab.add(lblNewLabel_4);

		mouseBarcodeIDTxtField = new JTextField();
		mouseBarcodeIDTxtField.setColumns(10);
		mouseBarcodeIDTxtField.setBounds(322, 137, 96, 20);
		addMouseTab.add(mouseBarcodeIDTxtField);

		mouseBrandTxtField = new JTextField();
		mouseBrandTxtField.setColumns(10);
		mouseBrandTxtField.setBounds(556, 137, 96, 20);
		addMouseTab.add(mouseBrandTxtField);

		JLabel lblNewLabel_1_1 = new JLabel("Brand:");
		lblNewLabel_1_1.setBounds(428, 139, 115, 20);
		addMouseTab.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_4 = new JLabel("Colour:");
		lblNewLabel_2_4.setBounds(210, 165, 102, 23);
		addMouseTab.add(lblNewLabel_2_4);

		mouseColourTxtField = new JTextField();
		mouseColourTxtField.setColumns(10);
		mouseColourTxtField.setBounds(322, 168, 96, 20);
		addMouseTab.add(mouseColourTxtField);

		JLabel lblNewLabel_3_3 = new JLabel("Connectivity:");
		lblNewLabel_3_3.setBounds(428, 168, 115, 20);
		addMouseTab.add(lblNewLabel_3_3);

		JComboBox mouseConnectivityComboBox = new JComboBox(Connectivity.values());
		mouseConnectivityComboBox.setBounds(556, 168, 96, 20);
		addMouseTab.add(mouseConnectivityComboBox);

		JLabel lblNewLabel_2_1_1 = new JLabel("Original Cost:");
		lblNewLabel_2_1_1.setBounds(210, 202, 102, 17);
		addMouseTab.add(lblNewLabel_2_1_1);

		mouseOriginalCostTxtField = new JTextField();
		mouseOriginalCostTxtField.setColumns(10);
		mouseOriginalCostTxtField.setBounds(322, 199, 96, 20);
		addMouseTab.add(mouseOriginalCostTxtField);

		JLabel lblNewLabel_2_2_1 = new JLabel("Quantity In Stock:");
		lblNewLabel_2_2_1.setBounds(428, 199, 131, 20);
		addMouseTab.add(lblNewLabel_2_2_1);

		mouseQuantityInStockTxtField = new JTextField();
		mouseQuantityInStockTxtField.setColumns(10);
		mouseQuantityInStockTxtField.setBounds(556, 199, 96, 20);
		addMouseTab.add(mouseQuantityInStockTxtField);

		JLabel lblNewLabel_2_3_1 = new JLabel("Retail Price:");
		lblNewLabel_2_3_1.setBounds(210, 233, 102, 17);
		addMouseTab.add(lblNewLabel_2_3_1);

		mouseRetailPriceTxtField = new JTextField();
		mouseRetailPriceTxtField.setColumns(10);
		mouseRetailPriceTxtField.setBounds(322, 230, 96, 20);
		addMouseTab.add(mouseRetailPriceTxtField);

		JLabel lblNewLabel_3_1_1 = new JLabel("Number of Buttons:");
		lblNewLabel_3_1_1.setBounds(427, 230, 115, 20);
		addMouseTab.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_2_1 = new JLabel("Mouse Type:");
		lblNewLabel_3_2_1.setBounds(210, 264, 105, 17);
		addMouseTab.add(lblNewLabel_3_2_1);

		JComboBox mouseTypeComboBox = new JComboBox(MouseType.values());
		mouseTypeComboBox.setBounds(322, 261, 96, 20);
		addMouseTab.add(mouseTypeComboBox);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(313, 354, 290, 113);
		addMouseTab.add(scrollPane_2);

		JTextPane mouseErrorMessageTxt = new JTextPane();
		scrollPane_2.setViewportView(mouseErrorMessageTxt);
		mouseErrorMessageTxt.setEditable(false);
		mouseErrorMessageTxt.setBackground(SystemColor.menu);

		JButton addNewMouseBtn = new JButton("Add New Mouse");
		addNewMouseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/////////////////////////////////////
				boolean errorOccured = false;
				Mouse mouse = null;
				String errorMsg = "";
				try {
					////////////////////////////////////////////////////////////////////////////////
					try {
						String val = mouseBarcodeIDTxtField.getText().trim();
						if (val.trim().matches("\\d{6}")) { // checks if 6 digit value
							products = Admin.viewAllProducts();
							for (Product product : products) {
								if (product.getBarcodeID() == Integer.parseInt(val)) {
									errorOccured = true;
									throw new Exception("ID should be unique!");
								}
							}
							///// return the integer value
						} else {
							errorOccured = true;
							throw new Exception("Incorrect Format of ID. Should be 6 digits long.");
						}
					} catch (Exception e1) {
						errorMsg += e1.getMessage() + "\n";
//						System.out.println(e1.getMessage());
					}

					try {
						Double.parseDouble(mouseOriginalCostTxtField.getText().trim());
					} catch (Exception originalCostError) {
						// TODO: handle exception
						errorMsg += "The Original cost is invalid. Should be a number!\n";
//						System.out.println("The Original cost is invalid. Should be a number!");
					}

					try {
						Integer.parseInt(mouseQuantityInStockTxtField.getText().trim());
					} catch (Exception quantityError) {
						// TODO: handle exception
						errorMsg += "The quantity should be a number.\n";
//						System.out.println("The quantity should be a number.");
					}

					try {
						Double.parseDouble(mouseRetailPriceTxtField.getText().trim());
					} catch (Exception retailPriceError) {
						// TODO: handle exception
						errorMsg += "The Retail Price is invalid. Should be a number!\n";
//						System.out.println("The Retail Price is invalid. Should be a number!");
					}
					//////////////////////////////////////////////////////////////////////////////////
					mouse = new Mouse(Integer.parseInt(mouseBarcodeIDTxtField.getText().trim()),
							mouseBrandTxtField.getText().trim(), mouseColourTxtField.getText().trim(),
							Connectivity.valueOf(mouseConnectivityComboBox.getSelectedItem().toString()),
							Double.parseDouble(mouseOriginalCostTxtField.getText().trim()),
							Integer.parseInt(mouseQuantityInStockTxtField.getText().trim()),
							Double.parseDouble(mouseRetailPriceTxtField.getText().trim()),
							Integer.parseInt(mouseNumberOfButtonsTxtField.getText().trim()),
							MouseType.valueOf(mouseTypeComboBox.getSelectedItem().toString()));

				} catch (Exception e2) {
					errorMsg += "Error in your entries! Remember, no fields must be left empty.\n";
//					System.out.println("Error in your entries! Remember, no fields must be left empty.");
					errorOccured = true;
				} finally {
					if (!errorOccured) {
						Admin.addNewProduct(mouse); // adding the product to the file
						mouseErrorMessageTxt.setText("Mouse Added Succesfully");
						clearTxtFields();
					} else {
						mouseErrorMessageTxt.setText(errorMsg);
					}
				}

				///////////////////////////
			}
		});
		addNewMouseBtn.setBounds(373, 292, 178, 51);
		addMouseTab.add(addNewMouseBtn);

		mouseNumberOfButtonsTxtField = new JTextField();
		mouseNumberOfButtonsTxtField.setColumns(10);
		mouseNumberOfButtonsTxtField.setBounds(556, 230, 96, 20);
		addMouseTab.add(mouseNumberOfButtonsTxtField);

		JButton selectNewProductBtn = new JButton("Add New Product");
		selectNewProductBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		selectNewProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(selectNewProduct);
			}
		});
		selectNewProductBtn.setBounds(5, 110, 162, 55);
		contentPane.add(selectNewProductBtn);

		JButton viewAllProductsBtn = new JButton("View All Products");
		viewAllProductsBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		viewAllProductsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				products = Admin.viewAllProducts();
				tabbedPane.setSelectedComponent(allProducts);
				createTable();
			}
		});
		viewAllProductsBtn.setBounds(5, 176, 162, 55);
		contentPane.add(viewAllProductsBtn);

		JButton addKeyboardbtn = new JButton("Keyboard");
		addKeyboardbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(addKeyboardTab);
			}
		});
		addKeyboardbtn.setBounds(236, 169, 142, 43);
		selectNewProduct.add(addKeyboardbtn);

		JButton addMouseBtn = new JButton("Mouse");
		addMouseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(addMouseTab);
			}
		});
		addMouseBtn.setBounds(468, 169, 142, 43);
		selectNewProduct.add(addMouseBtn);

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
						keyboard.getQuantityInStock(), keyboard.getOriginalCost(), keyboard.getRetailPrice(),
						keyboard.getLayout() + " Layout" };
				dtmProducts.addRow(row);
			} else {
				Mouse mouse = (Mouse) product; // cast each product to it's mouse type to access mouse methods
				Object[] row = new Object[] { mouse.getBarcodeID(), "Mouse", mouse.getType(), mouse.getBrand(),
						mouse.getColour(), mouse.getConnectivity().toString(), mouse.getQuantityInStock(),
						mouse.getOriginalCost(), mouse.getRetailPrice(), mouse.getNumOfButtons() + " Button(s)" };
				dtmProducts.addRow(row);
			}
		}
	}

	private void clearTxtFields() {
		keyboardBarcodeIDTxtField.setText("");
		keyboardBrandTxtField.setText("");
		keyboardColourTxtField.setText("");
		keyboardOriginalCostTxtField.setText("");
		keyboardQuantityInStockTxtField.setText("");
		keyboardRetailPriceTxtField.setText("");
		mouseBarcodeIDTxtField.setText("");
		mouseBrandTxtField.setText("");
		mouseColourTxtField.setText("");
		mouseNumberOfButtonsTxtField.setText("");
		mouseOriginalCostTxtField.setText("");
		mouseQuantityInStockTxtField.setText("");
		mouseRetailPriceTxtField.setText("");
	}
}
