import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUIAdminLogin extends JFrame {
    JLabel welcomeLabel, cardNoLabel, pinLabel;
    JTextField cardNoTextField;
    JPasswordField pinPasswordField;
    JButton signInButton, clearButton, backButton;

    public GUIAdminLogin() {
        super("Admin Login");
        setLayout(null);
        setComponents();
        setSize(850, 480);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signInButton.addActionListener(new ButtonActionListener());
        clearButton.addActionListener(new ButtonActionListener());
        backButton.addActionListener(new ButtonActionListener());
        setResizable(false);
    }

    public void setComponents() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 10, 100, 100);
        add(image);


        welcomeLabel = new JLabel("ADMIN LOGIN");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("", Font.BOLD, 38));
        welcomeLabel.setBounds(330, 125, 450, 40);
        add(welcomeLabel);

        cardNoLabel = new JLabel("Username:");
        cardNoLabel.setFont(new Font("", Font.BOLD, 28));
        cardNoLabel.setForeground(Color.WHITE);
        cardNoLabel.setBounds(150, 190, 375, 30);
        add(cardNoLabel);

        cardNoTextField = new JTextField(15);
        cardNoTextField.setBounds(325, 190, 230, 30);
        cardNoTextField.setFont(new Font("", Font.BOLD, 14));
        add(cardNoTextField);

        pinLabel = new JLabel("Password:");
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setFont(new Font("", Font.BOLD, 28));
        pinLabel.setBounds(150, 240, 375, 60);
        add(pinLabel);

        pinPasswordField = new JPasswordField(15);
        pinPasswordField.setBounds(325, 250, 230, 30);
        pinPasswordField.setFont(new Font("", Font.BOLD, 14));
        add(pinPasswordField);

        signInButton = new JButton("SIGN IN");
        clearButton = new JButton("CLEAR");
        backButton = new JButton("BACK");

        customizeButton(signInButton);
        customizeButton(clearButton);
        customizeButton(backButton);

        signInButton.setBounds(320, 300, 100, 30);
        clearButton.setBounds(450, 300, 100, 30);
        backButton.setBounds(385, 350, 100, 30);

        add(signInButton);
        add(clearButton);
        add(backButton);

        ImageIcon b1 = new ImageIcon(ClassLoader.getSystemResource("eduardo-soares-utWyPB8_FU8-unsplash.jpg"));
        Image b2 = b1.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
        ImageIcon b3 = new ImageIcon(b2);
        JLabel backImage = new JLabel(b3);
        backImage.setBounds(0, 0, 850, 480);
        add(backImage);
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == signInButton) {
                    String cardNumber = cardNoTextField.getText();
                    char[] pinChars = pinPasswordField.getPassword();
                    String pin = new String(pinChars);

                    if (AdminLogin.authenticate(cardNumber, pin)) {
                        JOptionPane.showMessageDialog(GUIAdminLogin.this, "Login Successful!");
                        new AdminMenu("Admin Menu");
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(GUIAdminLogin.this, "Invalid Credentials. Try again.");
                        // Handle unsuccessful login
                    }
                } else if (e.getSource() == clearButton) {
                    cardNoTextField.setText("");
                    pinPasswordField.setText("");
                } else if (e.getSource() == backButton) {
                    new WelcomeGUI("Welcome Screen");
                    dispose();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new GUIAdminLogin();
    }
}
