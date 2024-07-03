import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LoanGUI extends JFrame {

    JTextField textName, textAge, textIncome, textCredit, textAmount;
    JRadioButton r1, r2, r3, y, n;
    JButton submitButton, backButton;
    ArrayList<Loan> loans;
    private UserMenu userMenu; // Reference to UserMenu instance
    private Account loggedInAccount;

    public LoanGUI() {
        super("LOAN APPLICATION");
        setLayout(null);
        setComponents();
        setSize(850, 650);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        submitButton.addActionListener(new ButtonActionListener());
        backButton.addActionListener(new ButtonActionListener());
        loans = new ArrayList<>();
    }

    public LoanGUI(UserMenu userMenu, Account loggedInAccount) {
        super("LOAN APPLICATION");
        setLayout(null);
        this.userMenu = userMenu;
        this.loggedInAccount = loggedInAccount;
        setComponents();
        setSize(850, 650);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        submitButton.addActionListener(new ButtonActionListener());
        backButton.addActionListener(new ButtonActionListener());
        loans = new ArrayList<>();
    }

    private void setComponents() {
        getContentPane().setBackground(new Color(255, 255, 255));
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(25, 10, 100, 100);
        add(image);

        JLabel label1 = new JLabel("LOAN APPLICATION FORM");
        label1.setBounds(160, 20, 600, 40);
        label1.setFont(new Font("", Font.BOLD, 38));
        add(label1);
        JLabel nameLabel = new JLabel("NAME:");
        nameLabel.setBounds(100, 140, 100, 30);
        nameLabel.setFont(new Font("", Font.BOLD, 20));
        add(nameLabel);
        JLabel ageLabel = new JLabel("ACCOUNT AGE:");
        ageLabel.setBounds(100, 190, 150, 30);
        ageLabel.setFont(new Font("", Font.BOLD, 18));
        add(ageLabel);
        JLabel genderLabel = new JLabel("GENDER:");
        genderLabel.setBounds(100, 240, 100, 30);
        genderLabel.setFont(new Font("", Font.BOLD, 20));
        add(genderLabel);
        JLabel emoloymentLabel = new JLabel("EMPLOYED:");
        emoloymentLabel.setBounds(100, 290, 150, 30);
        emoloymentLabel.setFont(new Font("", Font.BOLD, 18));
        add(emoloymentLabel);
        JLabel incomeLabel = new JLabel("INCOME:");
        incomeLabel.setBounds(100, 340, 100, 30);
        incomeLabel.setFont(new Font("", Font.BOLD, 20));
        add(incomeLabel);
        JLabel creditLabel = new JLabel("CREDIT:");
        creditLabel.setBounds(100, 390, 100, 30);
        creditLabel.setFont(new Font("", Font.BOLD, 20));
        add(creditLabel);
        JLabel amountLabel = new JLabel("AMOUNT:");
        amountLabel.setBounds(100, 440, 100, 30);
        amountLabel.setFont(new Font("", Font.BOLD, 20));
        add(amountLabel);
        textName = new JTextField();
        textName.setFont(new Font("", Font.BOLD, 14));
        textName.setBounds(300, 140, 300, 30);
        add(textName);
        textAge = new JTextField();
        textAge.setFont(new Font("", Font.BOLD, 14));
        textAge.setBounds(300, 190, 300, 30);
        add(textAge);
        r1 = new JRadioButton("Male");
        r1.setFont(new Font("", Font.BOLD, 14));
        r1.setBounds(300, 240, 60, 30);
        add(r1);
        r2 = new JRadioButton("Female");
        r2.setFont(new Font("", Font.BOLD, 14));
        r2.setBounds(400, 240, 80, 30);
        add(r2);
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(r1);
        genderButtonGroup.add(r2);
        y = new JRadioButton("Yes");
        y.setFont(new Font("", Font.BOLD, 14));
        y.setBounds(300, 290, 60, 30);
        add(y);
        n = new JRadioButton("No");
        n.setFont(new Font("", Font.BOLD, 14));
        n.setBounds(400, 290, 80, 30);
        add(n);
        ButtonGroup employmentButtonGroup = new ButtonGroup();
        employmentButtonGroup.add(y);
        employmentButtonGroup.add(n);

        textIncome = new JTextField();
        textIncome.setFont(new Font("", Font.BOLD, 14));
        textIncome.setBounds(300, 340, 300, 30);
        add(textIncome);
        textCredit = new JTextField();
        textCredit.setFont(new Font("", Font.BOLD, 14));
        textCredit.setBounds(300, 390, 300, 30);
        add(textCredit);
        textAmount = new JTextField();
        textAmount.setFont(new Font("", Font.BOLD, 14));
        textAmount.setBounds(300, 440, 300, 30);
        add(textAmount);
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("", Font.BOLD, 14));
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBounds(620, 550, 130, 30);
        add(submitButton);
        backButton = new JButton("Back");
        backButton.setFont(new Font("", Font.BOLD, 14));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(80, 550, 130, 30);
        add(backButton);
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == submitButton) {
                    handleLoanSubmission();
                } else if (e.getSource() == backButton) {
                    handleBackButton();
                }

            } catch (NumberFormatException ex) {
                // Show error message for invalid input
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numeric values.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void handleLoanSubmission() {
            String customerName = textName.getText();
            int accountAge = Integer.parseInt(textAge.getText());
            String customerGender = r1.isSelected() ? "Male" : "Female";
            boolean employed = y.isSelected();
            double customerIncome = Double.parseDouble(textIncome.getText());
            int customerCredit = Integer.parseInt(textCredit.getText());
            double loanAmount = Double.parseDouble(textAmount.getText());

            // Read accounts from file
            FileHandler fileHandler = new FileHandler("newaccounts.txt");
            ArrayList<Account> accounts = fileHandler.readFromFile();
            LoanHandler loanHandler = new LoanHandler();
            loanHandler.saveLoans(loans);

            // Create a loan instance
            Loan loan = new Loan(accounts, customerName, accountAge, customerGender, employed, customerIncome,
                    customerCredit, loanAmount);

            // Check eligibility for the loan
            if (loan.approveLoan(loggedInAccount)) {
                // Deposit the loan amount into the customer's account
                loan.depositLoan(accounts, loggedInAccount);

                // Update the accounts in the file
                fileHandler.writeToFile(accounts);

                // Show success message
                JOptionPane.showMessageDialog(null,
                        "Loan approved and deposited successfully for " + customerName,
                        "Loan Approval", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Show rejection message
                JOptionPane.showMessageDialog(null, "Loan not approved for " + customerName,
                        "Loan Rejection", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void handleBackButton() {
            if (userMenu == null) {
                System.out.println("user not logged in!");
            } else {
                userMenu.setLoggedInAccount(loggedInAccount);
                userMenu.setVisible(true);
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new LoanGUI();

    }

}
