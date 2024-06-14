import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblUsers;
	private DefaultTableModel dtmUsers;
	private JComboBox comboBox;

	private ArrayList<User> users = new ArrayList<User>();
	private BufferedReader br = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 856, 638);
		contentPane.add(scrollPane);

		getUsers(); // used to populate the users ArrayList

		tblUsers = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // this prevents the table from being edited
			};
		};

		scrollPane.setViewportView(tblUsers);

		dtmUsers = new DefaultTableModel();
		dtmUsers.setColumnIdentifiers(new Object[] { "ID", "Username", "Name", "Role" });
		tblUsers.setModel(dtmUsers);

		createTable();

		String[] userIDs = getUserIDs();

		comboBox = new JComboBox(userIDs);
		comboBox.setBounds(897, 113, 138, 34);
		contentPane.add(comboBox);

		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(comboBox.getSelectedItem().toString());
				for (User user : users) {
					if (user.getUserID() == selectedID) {
						JFrame newFrame;
						if (user instanceof Admin) {
							newFrame = new AdminFrame((Admin) user);
						} else {
							newFrame = new CustomerFrame((Customer) user);
						}
						newFrame.setVisible(true);
						break;
					}
				}
			}
		});
		loginBtn.setBounds(897, 158, 138, 34);
		contentPane.add(loginBtn);

		JTextArea userSelectTextArea = new JTextArea();
		userSelectTextArea.setBackground(SystemColor.menu);
		userSelectTextArea.setEditable(false);
		userSelectTextArea.setLineWrap(true);
		userSelectTextArea.setText("Please select your \r\nuser ID from the \r\ndrop down below.");
		userSelectTextArea.setBounds(897, 51, 138, 76);
		contentPane.add(userSelectTextArea);

		JTextArea txtrCurrentUsers = new JTextArea();
		txtrCurrentUsers.setEditable(false);
		txtrCurrentUsers.setFont(new Font("Monospaced", Font.BOLD, 13));
		txtrCurrentUsers.setBackground(SystemColor.menu);
		txtrCurrentUsers.setText("Current Users");
		txtrCurrentUsers.setBounds(378, 18, 109, 22);
		contentPane.add(txtrCurrentUsers);
	}

	private void getUsers() {
		String fileTxt = null;
		String[] userDetailsArr = null;
		try {
			this.br = new BufferedReader(new FileReader("UserAccounts.txt"));
			while ((fileTxt = this.br.readLine()) != null) {
				// System.out.println(fileTxt);
				userDetailsArr = fileTxt.split(",");
				// validate address
				Address userAddress = new Address(Integer.parseInt(userDetailsArr[3].trim()), userDetailsArr[4].trim(),
						userDetailsArr[5]);
				if (userDetailsArr[6].trim().toLowerCase().equals("admin")) {
					users.add(new Admin(Integer.parseInt(userDetailsArr[0].trim()), userDetailsArr[1].trim(),
							userDetailsArr[2].trim(), userAddress));
				} else {
					users.add(new Customer(Integer.parseInt(userDetailsArr[0].trim()), userDetailsArr[1].trim(),
							userDetailsArr[2].trim(), userAddress));
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (this.br != null) {
					this.br.close();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private String[] getUserIDs() {
		ArrayList<String> userIDs = new ArrayList<String>();
		for (User user : users) {
			userIDs.add(String.valueOf(user.getUserID()));
		}
		String[] arr = userIDs.toArray(new String[0]);
		return arr;
	}

	private void createTable() {
		for (User user : users) {
			if (user instanceof Admin) {
				Object[] row = new Object[] { user.getUserID(), user.getUsername(), user.getName(), "Admin" };
				dtmUsers.addRow(row);
			} else {
				Object[] row = new Object[] { user.getUserID(), user.getUsername(), user.getName(), "Customer" };
				dtmUsers.addRow(row);
			}
		}
	}
}
