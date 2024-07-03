import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UserLogin {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, clearButton, backButton;

    private JFrame frame;
    private FileHandler fileHandler;
    private ArrayList<Account> accounts;
    private Account loggedInAccount;

    public UserLogin() {
        initialize();
        fileHandler = new FileHandler("newaccounts.txt");
        accounts = fileHandler.readFromFile();
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    private void initialize() {
        frame = new JFrame("User Login");
        frame.setLayout(null);
        frame.setSize(850, 480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank.png"));
        // Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        // ImageIcon i3 = new ImageIcon(i2);
        // JLabel image = new JLabel(i3);
        // image.setBounds(380, 10, 100, 100);
        // frame.getContentPane().add(image);
        // ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("card.png"));
        // Image c2 = c1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        // ImageIcon c3 = new ImageIcon(c2);
        // JLabel cardImage = new JLabel(c3);
        // cardImage.setBounds(630, 350, 100, 100);
        // frame.getContentPane().add(cardImage);

        JLabel header = new JLabel("USER LOGIN");
        header.setForeground(Color.WHITE);
        header.setFont(new Font("", Font.BOLD, 38));
        header.setBounds(330, 125, 450, 40);
        frame.getContentPane().add(header);

        JLabel userLabel = new JLabel("Username: ");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("", Font.BOLD, 28));
        userLabel.setBounds(150, 190, 375, 30);
        frame.getContentPane().add(userLabel);

        usernameField = new JTextField(15);
        usernameField.setBounds(325, 190, 230, 30);
        usernameField.setFont(new Font("", Font.BOLD, 14));
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("", Font.BOLD, 28));
        passwordLabel.setBounds(150, 250, 375, 30);
        frame.getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(325, 250, 230, 30);
        passwordField.setFont(new Font("", Font.BOLD, 14));
        frame.getContentPane().add(passwordField);

        loginButton = new JButton("SIGN IN");
        loginButton.setBounds(320, 300, 100, 30);
        loginButton.setFont(new Font("", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        frame.getContentPane().add(loginButton);
        clearButton = new JButton("CLEAR");
        clearButton.setBounds(450, 300, 100, 30);
        clearButton.setFont(new Font("", Font.BOLD, 14));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.BLACK);
        frame.getContentPane().add(clearButton);
        backButton = new JButton("BACK");
        backButton.setBounds(385, 350, 100, 30);
        backButton.setFont(new Font("", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        frame.getContentPane().add(backButton);
        ImageIcon b1 = new ImageIcon(ClassLoader.getSystemResource("robert-almonte-Hp8_AaU2Juc-unsplash.jpg"));
        Image b2 = b1.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
        ImageIcon b3 = new ImageIcon(b2);
        JLabel backImage = new JLabel(b3);
        backImage.setBounds(0, 0, 850, 480);
        frame.getContentPane().add(backImage);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                loggedInAccount = retrieveLoggedInAccount(username, password);

                if (loggedInAccount != null) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    frame.dispose(); // Close login window
                    openUserMenu(loggedInAccount); // Pass the loggedInAccount to UserMenu
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password!");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == clearButton) {
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) {
                    new WelcomeGUI("Welcome Screen");
                    frame.dispose();
                }
            }
        });

        frame.setVisible(true);
    }

    private void openUserMenu(Account loggedInAccount) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserMenu userMenu = new UserMenu("User Menu", loggedInAccount);
                userMenu.setSize(850, 480);
                userMenu.setVisible(true);
                userMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    private Account retrieveLoggedInAccount(String username, String password) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return account; // Return the account if found
            }
        }
        return null; // Return null if no account matches the provided credentials
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserLogin();
            }
        });
    }
}
