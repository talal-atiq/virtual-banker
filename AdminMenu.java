import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminMenu extends JFrame {
    JLabel titleLabel, usernameLabel, typeLabel, ageLabel, occupationLabel, nationalityLabel, passwordLabel, cashLabel;
    JTextField usernameField, typeField, ageField, occupationField, passwordField, cashField;
    JButton createButton, deleteButton, searchButton, showAllAccountsButton, logoutButton, showCustomerComplaintsButton, viewTransactionHistoryButton;
    JCheckBox typeCheckBox1, typeCheckBox2, typeCheckBox3;
    FileHandler fileHandler;

    private final List<String> countries = Arrays.asList(
    "Select Country...", "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", 
    "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", 
    "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", 
    "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", 
    "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", 
    "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Cote d'Ivoire", 
    "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo", "Denmark", 
    "Djibouti", "Dominica", "Dominican Republic", "East Timor (Timor-Leste)", "Ecuador", "Egypt", 
    "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", 
    "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", 
    "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", 
    "India", "Indonesia", "Iran", "Iraq", "Ireland", "Italy", "Jamaica", "Japan", 
    "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South", "Kosovo", 
    "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", 
    "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", 
    "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", 
    "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)", 
    "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", 
    "North Macedonia (formerly Macedonia)", "Norway", "Oman", "Pakistan", "Palau", "Palestine", 
    "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", 
    "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", 
    "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", 
    "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", 
    "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", 
    "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", 
    "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", 
    "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", 
    "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", 
    "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
);



    public AdminMenu(String title) {
        super(title);
        setLayout(null);
        fileHandler = new FileHandler("newaccounts.txt");

        setSize(850, 480);
        createComponents(fileHandler);
        setLocationRelativeTo(null);
        setVisible(true);
        initializeBackground();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("moneysign.png"));
        Image image = icon.getImage();

        setIconImage(image);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeBackground() {
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("pexels-tima-miroshnichenko-5912587.jpg"));
        Image img = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(img);

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight()); // Set size to cover the entire frame

        getContentPane().add(backgroundLabel);
        getContentPane().setLayout(null); // Set layout to null to position components freely
        setResizable(false); // Prevent resizing for maintaining background image size
    }

    public void createComponents(FileHandler fileHandler) {

        titleLabel = new JLabel("Admin Menu", SwingConstants.CENTER);
        usernameLabel = new JLabel("Username:");
        typeLabel = new JLabel("Type:");
        ageLabel = new JLabel("Age:");
        occupationLabel = new JLabel("Occupation:");
        nationalityLabel = new JLabel("Nationality:");
        passwordLabel = new JLabel("Password:");
        cashLabel = new JLabel("Initial Balance: ");

        Font labelFont = new Font("Arial", Font.BOLD, 14); // Change the font and size as needed

        
        titleLabel.setForeground(Color.WHITE); // Set foreground color to white
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(100, 15, 650, 50);
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(Color.WHITE);
        typeLabel.setFont(labelFont);
        typeLabel.setForeground(Color.WHITE);
        ageLabel.setFont(labelFont);
        ageLabel.setForeground(Color.WHITE);
        occupationLabel.setFont(labelFont);
        occupationLabel.setForeground(Color.WHITE);
        nationalityLabel.setFont(labelFont);
        nationalityLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(Color.WHITE);
        cashLabel.setFont(labelFont);
        cashLabel.setForeground(Color.WHITE);
        usernameField = new JTextField();
        ageField = new JTextField();
        occupationField = new JTextField();
        passwordField = new JTextField();
        cashField = new JTextField();

        typeCheckBox1 = new JCheckBox("Savings");
        typeCheckBox2 = new JCheckBox("Business");
        typeCheckBox3 = new JCheckBox("International");

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(typeCheckBox1);
        typeGroup.add(typeCheckBox2);
        typeGroup.add(typeCheckBox3);

        createButton = new JButton("Add Account");
        deleteButton = new JButton("Delete Account");
        searchButton = new JButton("Search Account");
        showAllAccountsButton = new JButton("Show All Accounts");
        logoutButton = new JButton("Logout");
        showCustomerComplaintsButton = new JButton("Show Customer Complaints");
        viewTransactionHistoryButton = new JButton("View Transaction History");



        //setting colors for the buttons:
        Color navyBlue =  new Color(15,20,36); // RGB value for navy blue

        createButton.setBackground(navyBlue);
        deleteButton.setBackground(navyBlue);
        searchButton.setBackground(navyBlue);
        showAllAccountsButton.setBackground(navyBlue);
        logoutButton.setBackground(navyBlue);
        showCustomerComplaintsButton.setBackground(navyBlue);
        viewTransactionHistoryButton.setBackground(navyBlue); // RGB value for navy blue
        createButton.setBackground(navyBlue);
        deleteButton.setBackground(navyBlue);
        searchButton.setBackground(navyBlue);
        showAllAccountsButton.setBackground(navyBlue);
        logoutButton.setBackground(navyBlue);
        showCustomerComplaintsButton.setBackground(navyBlue);
        viewTransactionHistoryButton.setBackground(navyBlue);


        Font buttonFont = new Font("Arial", Font.BOLD, 14); // Font: Arial, Bold, Size: 12
        Color whiteColor = Color.WHITE;

        createButton.setForeground(whiteColor);
        createButton.setFont(buttonFont);

        deleteButton.setForeground(whiteColor);
        deleteButton.setFont(buttonFont);

        searchButton.setForeground(whiteColor);
        searchButton.setFont(buttonFont);

        showAllAccountsButton.setForeground(whiteColor);
        showAllAccountsButton.setFont(buttonFont);

        logoutButton.setForeground(whiteColor);
        logoutButton.setFont(buttonFont);

        showCustomerComplaintsButton.setForeground(whiteColor);
        showCustomerComplaintsButton.setFont(buttonFont);

        viewTransactionHistoryButton.setForeground(whiteColor);
        viewTransactionHistoryButton.setFont(buttonFont);


        usernameLabel.setBounds(30, 60, 80, 30);
        usernameField.setBounds(140, 60, 200, 30);

        typeLabel.setBounds(30, 100, 80, 20);
        typeCheckBox1.setBounds(120, 100, 100, 20);
        typeCheckBox2.setBounds(230, 100, 100, 20);
        typeCheckBox3.setBounds(340, 100, 100, 20);
        ageLabel.setBounds(30, 140, 80, 30);
        ageField.setBounds(140, 140, 200, 30);

        occupationLabel.setBounds(30, 180, 90, 20);
        occupationField.setBounds(140, 180, 200, 30);

        nationalityLabel.setBounds(30, 220, 80, 20);

        passwordLabel.setBounds(30, 260, 80, 20);
        passwordField.setBounds(140, 260, 200, 30);

        cashLabel.setBounds(30, 300, 120, 20);
        cashField.setBounds(140, 300, 200, 30);


        createButton.setBounds(150, 350, 150, 40);
        deleteButton.setBounds(550, 295, 150, 40);
        searchButton.setBounds(550, 75, 150, 40);
        showAllAccountsButton.setBounds(550, 130, 250, 40);
        logoutButton.setBounds(550, 350, 150, 40);
        showCustomerComplaintsButton.setBounds(550, 185, 250, 40);
        viewTransactionHistoryButton.setBounds(550, 240, 250, 40); // Adjusted Y position to 370

        JComboBox<String> nationalityComboBox = new JComboBox<>(countries.toArray(new String[0]));
        nationalityComboBox.setBounds(140, 220, 200, 30); // Adjusted width to fit with the label


        add(titleLabel);
        add(usernameLabel);
        add(usernameField);
        add(typeLabel);
        add(typeCheckBox1);
        add(typeCheckBox2);
        add(typeCheckBox3);
        add(ageLabel);
        add(ageField);
        add(occupationLabel);
        add(occupationField);
        add(nationalityLabel);
        //add(nationalityField);
        add(passwordLabel);
        add(passwordField);
        add(cashLabel);
        add(cashField);
        add(createButton);
        add(deleteButton);
        add(searchButton);
        add(showAllAccountsButton);
        add(logoutButton);
        add(showCustomerComplaintsButton);
        add(nationalityComboBox);
        add(viewTransactionHistoryButton);

        

        createButton.addActionListener(new CreateButtonHandler(fileHandler, usernameField, typeCheckBox1, typeCheckBox2, typeCheckBox3, ageField, occupationField, nationalityComboBox, passwordField, cashField));
        deleteButton.addActionListener(new DeleteButtonHandler(fileHandler));
        searchButton.addActionListener(new SearchButtonHandler(fileHandler));
        showAllAccountsButton.addActionListener(new ShowAllAccountsButtonHandler(fileHandler));


            viewTransactionHistoryButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame historyFrame = new JFrame("Transaction History");
                    JTextArea historyTextArea = new JTextArea();
                    JScrollPane scrollPane = new JScrollPane(historyTextArea);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
                    try {
                        File file = new File("transactionhistory.txt");
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
        
                        while ((line = br.readLine()) != null) {
                            historyTextArea.append(line + "\n");
                        }
                        br.close();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Transaction history file not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error reading transaction history file.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
        
                    historyFrame.add(scrollPane);
                    historyFrame.setLocationRelativeTo(null);
                    historyFrame.setSize(600, 400);
                    historyFrame.setVisible(true);
                }
            });

        

        showCustomerComplaintsButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        // Read complaints from complaints.txt file
        try {
            File complaintsFile = new File("complaints.txt");
            Scanner scanner = new Scanner(complaintsFile);

            // Create a StringBuilder to store complaints data
            StringBuilder complaintsData = new StringBuilder();

            while (scanner.hasNextLine()) {
                complaintsData.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            // Display complaints data in a new window with a scroll pane
            JFrame complaintsFrame = new JFrame("Customer Complaints");
            JTextArea complaintsTextArea = new JTextArea();
            complaintsTextArea.setText(complaintsData.toString());
            complaintsTextArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(complaintsTextArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            complaintsFrame.add(scrollPane);
            complaintsFrame.setLocationRelativeTo(null);
            complaintsFrame.setSize(400, 300);
            complaintsFrame.setVisible(true);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Complaints file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
            }
        });

        
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GUIAdminLogin();
                dispose();
            }
        });

    }

    class CreateButtonHandler implements ActionListener {
        private FileHandler fileHandler;
        private JTextField usernameField, ageField, occupationField, passwordField, cashField;
        private JCheckBox typeCheckBox1, typeCheckBox2, typeCheckBox3;
        private JComboBox<String> nationalityComboBox;
    
        public CreateButtonHandler(FileHandler fileHandler, JTextField usernameField, JCheckBox typeCheckBox1,
                JCheckBox typeCheckBox2, JCheckBox typeCheckBox3, JTextField ageField, JTextField occupationField,
                JComboBox<String> nationalityComboBox, JTextField passwordField, JTextField cashField) {
            this.fileHandler = fileHandler;
            this.usernameField = usernameField;
            this.typeCheckBox1 = typeCheckBox1;
            this.typeCheckBox2 = typeCheckBox2;
            this.typeCheckBox3 = typeCheckBox3;
            this.ageField = ageField;
            this.occupationField = occupationField;
            this.nationalityComboBox = nationalityComboBox;
            this.passwordField = passwordField;
            this.cashField = cashField;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                String username = usernameField.getText();
                String accountType = "";

                if (typeCheckBox1.isSelected()) {
                    accountType = "Savings";
                } else if (typeCheckBox2.isSelected()) {
                    accountType = "Business";
                } else if (typeCheckBox3.isSelected()) {
                    accountType = "International";
                }

                String ageText = ageField.getText().trim();
                String cashText = cashField.getText().trim();

                if (!ageText.isEmpty() && !cashText.isEmpty()) {
                    int age = Integer.parseInt(ageText);
                    double cash = Double.parseDouble(cashText);

                    if (age < 0 || cash < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Age and cash cannot be negative.");
                    } else {
                        String occupation = occupationField.getText();
                        String nationality = (String) nationalityComboBox.getSelectedItem(); // Retrieve selected nationality
                        String password = passwordField.getText();

                        // Create an account with all the fields and add to the display
                        Account account = null;

                        switch (accountType) {
                            case "Savings":
                                account = new SavingsAccount(username, age, nationality, occupation, password, cash);
                                break;
                            case "Business":
                                account = new BusinessAccount(username, age, nationality, occupation, password, cash);
                                break;
                            case "International":
                                account = new InternationalAccount(username, age, nationality, occupation, password,
                                        cash);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Invalid account type: " + accountType);
                                break;
                        }

                        if (account != null) {
                            // Writing the account to the file
                            ArrayList<Account> accounts = fileHandler.readFromFile();
                            accounts.add(account);
                            fileHandler.writeToFile(accounts);

                            // Display account details in a new window
                            JFrame accountDetailsFrame = new JFrame("Account Details");
                            JTextArea accountDetailsArea = new JTextArea();
                            accountDetailsArea.append(account.toString());

                            accountDetailsFrame.add(accountDetailsArea);
                            accountDetailsFrame.setLocationRelativeTo(null);
                            accountDetailsFrame.setSize(300, 200);
                            accountDetailsFrame.setVisible(true);

                            // Clear input fields after creating the account
                            usernameField.setText("");
                            typeCheckBox1.setSelected(false);
                            typeCheckBox2.setSelected(false);
                            typeCheckBox3.setSelected(false);
                            ageField.setText("");
                            occupationField.setText("");
                            nationalityComboBox.setSelectedIndex(0); // Reset selected index to default
                            passwordField.setText("");
                            cashField.setText(""); // Clear cash field as well
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid data for all fields.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric values for age and cash.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred. Please check your input and try again.");
            }
        }
    }

    class DeleteButtonHandler implements ActionListener {
        private FileHandler fileHandler;

        public DeleteButtonHandler(FileHandler fileHandler) {
            this.fileHandler = fileHandler;
        }

        public void actionPerformed(ActionEvent e) {
            JFrame deleteAccountFrame = new JFrame("Delete Account");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 2));

            JLabel nameLabel = new JLabel("Account Name:");
            JLabel passLabel = new JLabel("Password:");
            JTextField nameField = new JTextField();
            JPasswordField passField = new JPasswordField();
            JButton deleteConfirmButton = new JButton("Delete");

            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(passLabel);
            panel.add(passField);
            panel.add(deleteConfirmButton);

            deleteAccountFrame.add(panel);
            deleteAccountFrame.setLocationRelativeTo(null);
            deleteAccountFrame.setSize(300, 150);
            deleteAccountFrame.setVisible(true);

            deleteConfirmButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String pass = new String(passField.getPassword());

                    // Read accounts from file
                    ArrayList<Account> accounts = fileHandler.readFromFile();

                    boolean found = false;
                    // Iterate over the accounts to find the one to delete
                    for (int i = 0; i < accounts.size(); i++) {
                        Account acc = accounts.get(i);
                        if (acc.getUsername().equals(name) && acc.getPassword().equals(pass)) {
                            accounts.remove(i); // Remove the account at index i
                            found = true;
                            break;
                        }
                    }

                    // Write updated accounts to the file
                    fileHandler.writeToFile(accounts);

                    // Show a message based on the deletion result
                    if (found) {
                        JOptionPane.showMessageDialog(null, "Account deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Account not found or password incorrect.");
                    }

                    deleteAccountFrame.dispose(); // Close the dialog
                }
            });
        }
    }

    class SearchButtonHandler implements ActionListener {
        private FileHandler fileHandler;

        public SearchButtonHandler(FileHandler fileHandler) {
            this.fileHandler = fileHandler;
        }

        public void actionPerformed(ActionEvent e) {
            JFrame searchAccountFrame = new JFrame("Search Account");
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 2));

            JLabel nameLabel = new JLabel("Enter Username:");
            JTextField nameField = new JTextField();
            JButton searchConfirmButton = new JButton("Search");

            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(searchConfirmButton);

            searchAccountFrame.add(panel);
            searchAccountFrame.setLocationRelativeTo(null);
            searchAccountFrame.setSize(300, 150);
            searchAccountFrame.setVisible(true);

            searchConfirmButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String searchName = nameField.getText();

                    // Read accounts from file
                    ArrayList<Account> accounts = fileHandler.readFromFile();

                    boolean found = false;
                    Account foundAccount = null;

                    // Search for the account with the entered username
                    for (Account account : accounts) {
                        if (account.getUsername().equals(searchName)) {
                            found = true;
                            foundAccount = account;
                            break;
                        }
                    }

                    if (found) {
                        // Display the account details in a new window
                        JFrame accountDetailsFrame = new JFrame("Account Details");
                        JTextArea accountDetailsArea = new JTextArea();
                        accountDetailsArea.append(foundAccount.toString());

                        accountDetailsFrame.add(accountDetailsArea);
                        accountDetailsFrame.setSize(300, 200);
                        accountDetailsFrame.setVisible(true);
                    } else {
                        // Display "Not Found" message
                        JOptionPane.showMessageDialog(null, "Account not found.");
                    }

                    searchAccountFrame.dispose(); // Close the dialog
                }
            });
        }
    }

    class ShowAllAccountsButtonHandler implements ActionListener {
        private FileHandler fileHandler;

        public ShowAllAccountsButtonHandler(FileHandler fileHandler) {
            this.fileHandler = fileHandler;
        }

        public void actionPerformed(ActionEvent e) {
            // Read accounts from file
            ArrayList<Account> accounts = fileHandler.readFromFile();

            // Create a new frame to display all accounts
            JFrame allAccountsFrame = new JFrame("All Accounts");
            JTextArea allAccountsArea = new JTextArea();

            // Append details of each account to the text area
            for (Account account : accounts) {
                allAccountsArea.append(account.toString() + "\n\n");
            }

           JScrollPane scrollPane = new JScrollPane(allAccountsArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // Add the scroll pane to the new window
            allAccountsFrame.add(scrollPane);
            allAccountsFrame.setSize(400, 300);
            allAccountsFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new AdminMenu("Admin Menu");
    }
}
