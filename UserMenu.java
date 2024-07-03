import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class UserMenu extends JFrame {
    JButton withdrawButton, depositButton, transferButton, logoutButton, complaintButton, loanButton, showBalanceButton;
    private Account loggedInAccount;
    private ArrayList<Account> accounts;

    public UserMenu(String title, Account loggedInAccount) {
        super(title);
        setSize(850, 480);
        this.loggedInAccount = loggedInAccount;
        loadAccountsFromFile("newaccounts.txt");
        createComponents();
        initializeBackground();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }

    private void initializeBackground() {
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("3923.jpg"));
        Image img = backgroundImage.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
        backgroundImage = new ImageIcon(img);

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 850, 480);

        getContentPane().add(backgroundLabel);
        getContentPane().setLayout(null);
        setResizable(false);
    }

    private void loadAccountsFromFile(String filename) {
        FileHandler fileHandler = new FileHandler(filename);
        try {
            accounts = fileHandler.readFromFile();

            for (Account account : accounts) {
                if (account instanceof SavingsAccount) {
                    account.setAccountType("Savings");
                } else if (account instanceof BusinessAccount) {
                    account.setAccountType("Business");
                } else if (account instanceof InternationalAccount) {
                    account.setAccountType("International");
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading accounts from file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showTranscript(double withdrawnAmount, double remainingAmount, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(new Date());

        String transcript = String.format(
                "Date/Time: %s\nWithdrawn Amount: %.2f\nRemaining Amount After Interest: %.2f\nAccount Type: %s",
                dateTime, withdrawnAmount, remainingAmount, type);

        JOptionPane.showMessageDialog(null, transcript, "Withdrawal Transcript", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logTransaction(String transactionDetails) {
        try {
            FileWriter fileWriter = new FileWriter("transactionhistory.txt", true);
            fileWriter.write(
                    "-----------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            fileWriter.write(transactionDetails + "\n");
            fileWriter.write(
                    "------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            fileWriter.close();
            System.out.println("Transaction History Saved.");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving transaction.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createComponents() {
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        transferButton = new JButton("Transfer");
        logoutButton = new JButton("Logout");
        complaintButton = new JButton("Register Complaint");
        loanButton = new JButton("Request Loan");
        showBalanceButton = new JButton("Show Balance");

        setLayout(null);

        withdrawButton.setBounds(142, 160, 150, 40);
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setBackground(Color.BLACK);

        depositButton.setBounds(340, 278, 150, 40);
        depositButton.setForeground(Color.WHITE);
        depositButton.setBackground(Color.BLACK);

        transferButton.setBounds(142, 219, 150, 40);
        transferButton.setForeground(Color.WHITE);
        transferButton.setBackground(Color.BLACK);

        logoutButton.setBounds(630, 372, 150, 40);
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setBackground(Color.LIGHT_GRAY);

        complaintButton.setBounds(340, 219, 150, 40);
        complaintButton.setForeground(Color.WHITE);
        complaintButton.setBackground(Color.BLACK);

        loanButton.setBounds(142, 278, 150, 40);
        loanButton.setForeground(Color.WHITE);
        loanButton.setBackground(Color.BLACK);

        showBalanceButton.setBounds(340, 160, 150, 40);
        showBalanceButton.setForeground(Color.WHITE);
        showBalanceButton.setBackground(Color.BLACK);

        add(withdrawButton);
        add(depositButton);
        add(transferButton);
        add(logoutButton);
        add(complaintButton);
        add(loanButton);
        add(showBalanceButton);

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String amountString = JOptionPane.showInputDialog("Enter withdrawal amount:");
                    if (amountString == null) {
                        return;
                    }
                    double amount = Double.parseDouble(amountString);

                    if (amount < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid amount. Withdrawal amount cannot be negative.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Find the logged-in account in the accounts list
                    if (accounts != null) {
                        for (Account account : accounts) {
                            if (account.getUsername().equals(loggedInAccount.getUsername())) {
                                // Check if the withdrawal amount is greater than the account balance
                                if (amount >= account.getCash()) {
                                    JOptionPane.showMessageDialog(null, "Insufficient funds for withdrawal.",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                // Additional checks based on the type of account
                                if (account instanceof SavingsAccount && amount > 10000) {
                                    JOptionPane.showMessageDialog(null,
                                            "Withdrawal limit exceeded for Savings Account.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    return;
                                } else if (account instanceof BusinessAccount && amount > 20000) {
                                    JOptionPane.showMessageDialog(null,
                                            "Withdrawal limit exceeded for Business Account.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    return;
                                } else if (amount > 25000) {
                                    // You can add more conditions for other types of accounts if needed
                                    JOptionPane.showMessageDialog(null,
                                            "Withdrawal limit exceeded for this account type.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                account.withdraw(amount);
                                // Update the account in the file
                                FileHandler fileHandler = new FileHandler("newaccounts.txt");
                                fileHandler.updateAccountToFile(accounts);

                                showTranscript(amount, account.getCash(), account.getAccountType());
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String dateTime = dateFormat.format(new Date());
                                String transactionDetails = String.format(
                                        "[%s] %s withdrew %.2f. Initial Balance: %.2f, Remaining Balance: %.2f",
                                        dateTime, loggedInAccount.getUsername(), amount, loggedInAccount.getCash(),
                                        account.getCash());
                                logTransaction(transactionDetails);

                                // Show custom dialog with withdrawal transcript
                                // showTranscript(amount, account.getCash(), account.getAccountType());

                                break;
                            }
                        }
                    } else {
                        System.out.println("Accounts list is null. Check the file loading process.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String amountString = JOptionPane.showInputDialog("Enter deposit amount:");

                    if (amountString == null) {
                        // User canceled, do nothing
                        return;
                    }

                    double amount = Double.parseDouble(amountString);

                    if (amount < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid amount. Deposit amount cannot be negative.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Find the logged-in account in the accounts list
                    if (accounts != null) {
                        for (Account account : accounts) {
                            if (account.getUsername().equals(loggedInAccount.getUsername())) {
                                if (account instanceof SavingsAccount && amount > 10000) {
                                    JOptionPane.showMessageDialog(null, "Deposit limit exceeded for Savings Account.",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    return;
                                } else if (account instanceof BusinessAccount && amount > 20000) {
                                    JOptionPane.showMessageDialog(null, "Deposit limit exceeded for Business Account.",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    return;
                                } else if (amount > 25000) {
                                    // You can add more conditions for other types of accounts if needed
                                    JOptionPane.showMessageDialog(null, "Deposit limit exceeded for this account type.",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                account.deposit(amount);

                                String message = "Deposit successful!\n\nAccount Balance: $" + account.getCash();
                                JOptionPane.showMessageDialog(null, message, "Deposit Successful",
                                        JOptionPane.INFORMATION_MESSAGE);

                                // Update the account in the file
                                // showTranscript(amount, account.getCash(), account.getAccountType());
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String dateTime = dateFormat.format(new Date());
                                String transactionDetails = String.format(
                                        "[%s] %s withdrew %.2f. Initial Balance: %.2f, Remaining Balance: %.2f",
                                        dateTime, loggedInAccount.getUsername(), amount, loggedInAccount.getCash(),
                                        account.getCash());
                                logTransaction(transactionDetails);

                                FileHandler fileHandler = new FileHandler("newaccounts.txt");
                                fileHandler.updateAccountToFile(accounts);

                                break;
                            }
                        }
                    } else {
                        System.out.println("Accounts list is null. Check the file loading process.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String targetAccountName = JOptionPane.showInputDialog("Enter target account name:");

                    if (targetAccountName == null) {
                        return;
                    }

                    Account targetAccount = null;
                    boolean isTargetAccountFound = false;

                    for (Account account : accounts) {
                        if (account.getUsername().equalsIgnoreCase(targetAccountName)) {
                            targetAccount = account;
                            isTargetAccountFound = true;
                            break; // Break the loop once the target account is found
                        }
                    }

                    if (!isTargetAccountFound) {

                        JOptionPane.showMessageDialog(null, "Invalid target account name.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String amountString = JOptionPane.showInputDialog("Enter amount to transfer:");

                    if (amountString == null) {
                        return;
                    }

                    double amount = Double.parseDouble(amountString);

                    if (amount < 0 || amount >= loggedInAccount.getCash()) {
                        JOptionPane.showMessageDialog(null, "Insufficient funds for transfer.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    for (Account account : accounts) {
                        if (account.getUsername().equals(loggedInAccount.getUsername())) {
                            if (account instanceof SavingsAccount && amount > 10000) {
                                JOptionPane.showMessageDialog(null, "Transfer limit exceeded for Savings Account.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            } else if (account instanceof BusinessAccount && amount > 20000) {
                                JOptionPane.showMessageDialog(null, "Transfer limit exceeded for Business Account.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            } else if (amount > 25000) {
                                JOptionPane.showMessageDialog(null, "Transfer limit exceeded for this account type.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            account.transfer(targetAccount, amount);

                            String message = "Transfer successful!\n\nUpdated Balance: $" + account.getCash();
                            JOptionPane.showMessageDialog(null, message, "Transfer Successful",
                                    JOptionPane.INFORMATION_MESSAGE);

                            FileHandler fileHandler = new FileHandler("newaccounts.txt");
                            fileHandler.updateAccountToFile(accounts);

                            // showTranscript(amount, account.getCash(), account.getAccountType());
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String dateTime = dateFormat.format(new Date());
                            String transactionDetails = String.format(
                                    "[%s] %s transfered %.2f to %s., Remaining Balance: %.2f", dateTime,
                                    loggedInAccount.getUsername(), amount, targetAccount.getUsername(),
                                    loggedInAccount.getCash(), account.getCash());
                            logTransaction(transactionDetails);
                            break;
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserLogin();
                dispose();
            }
        });

        loanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoanGUI(UserMenu.this, loggedInAccount);
                dispose();
            }
        });

        showBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loggedInAccount != null) {
                    String accountType = loggedInAccount.getAccountType();
                    String balanceDetails;

                    if (accountType != null) {

                        FileHandler fileHandler = new FileHandler("newaccounts.txt");
                        try {
                            ArrayList<Account> accounts = fileHandler.readFromFile();

                            for (Account account : accounts) {
                                if (account.getUsername().equals(loggedInAccount.getUsername()) &&
                                        account.getPassword().equals(loggedInAccount.getPassword())) {

                                    switch (accountType) {
                                        case "Savings":
                                        case "Business":
                                        case "International":

                                            balanceDetails = String.format(
                                                    "Account Name: %s\nAccount Type: %s\nNationality: %s\nCurrent Balance: $%.2f",
                                                    account.getUsername(), accountType, account.getNationality(),
                                                    account.getCash());

                                            JOptionPane.showMessageDialog(null, balanceDetails,
                                                    accountType + " Account Balance Details",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            return;

                                        default:
                                            JOptionPane.showMessageDialog(null, "Invalid account type.", "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                                            return;
                                    }
                                }
                            }
                            // Show error if account is not found
                            JOptionPane.showMessageDialog(null, "Logged-in account not found.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error reading accounts from file.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Account type not set.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Logged-in account not found.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        complaintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String complaintText = JOptionPane.showInputDialog("Enter your complaint:");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = dateFormat.format(new Date());

                String complaintRecord = String.format("Account Name: %s\nTime: %s\nComplaint: %s\n\n",
                        loggedInAccount.getUsername(), dateTime, complaintText);

                try {
                    FileWriter fileWriter = new FileWriter("complaints.txt", true);
                    fileWriter.write(complaintRecord);
                    fileWriter.close();

                    JOptionPane.showMessageDialog(null, "Complaint registered successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving complaint.", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserLogin userLogin = new UserLogin();
                Account loggedInAccount = userLogin.getLoggedInAccount();
                if (loggedInAccount != null) {

                    UserMenu userMenu = new UserMenu("User Menu", loggedInAccount);

                    String filename = "newaccounts.txt";
                    userMenu.loadAccountsFromFile(filename);
                    userMenu.setSize(850, 480);
                    userMenu.setVisible(true);
                    userMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    System.out.println("Login failed.");
                }
            }
        });
    }
}