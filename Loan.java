import java.io.Serializable;
import java.util.ArrayList;

public class Loan implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private ArrayList<Account> accounts;
    private String name;
    private int accountAge;
    private String gender;
    private boolean employed;
    private double income;
    private int credit;
    private double amount;

    public Loan(ArrayList<Account> accounts, String name, int accountAge, String gender, boolean employed,
            double income,
            int credit, double amount) {
        this.accounts = accounts;
        setName(name);
        setAge(accountAge);
        setGender(gender);
        setEmployed(employed);
        setIncome(income);
        setCredit(credit);
        setAmount(amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Name cannot be empty or null");
        }
    }

    public int getAge() {
        return accountAge;
    }

    public void setAge(int accountAge) {
        if (accountAge > 0) {
            this.accountAge = accountAge;
        } else {
            System.out.println("Account age can't be negative");
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if ("Male".equalsIgnoreCase(gender) || "Female".equalsIgnoreCase(gender)) {
            this.gender = gender;
        } else {
            System.out.println("Invalid gender. Please enter 'Male' or 'Female'");
        }
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        if (income >= 0) {
            this.income = income;
        } else {
            System.out.println("Income cannot be negative");
        }
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        if (credit >= 300 && credit <= 850) {
            this.credit = credit;
        } else {
            System.out.println("Credit score must be between 300 and 850");
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {

        if (amount >= 0) {
            this.amount = amount;
        } else {
            System.out.println("Loan amount cannot be negative");
        }
    }

    public boolean approveLoan(Account loggedInAccount) {
        // Check if the name is not null or empty
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // Check if the account with the specified name exists
        boolean accountExists = false;
        for (Account account : accounts) {
            if (name.equals(account.getUsername())) {
                accountExists = true;
                break;
            }
        }

        // If the account exists, is the logged-in account, and meets the loan criteria,
        // approve the loan
        if (accountExists && loggedInAccount.getUsername().equals(name)
                && accountAge > 2 && employed && income >= 20000 && credit >= 700) {
            return true;
        } else {
            return false;
        }
    }

    public void depositLoan(ArrayList<Account> accounts, Account loggedInAccount) {
        if (name != null && !name.trim().isEmpty()) {
            for (Account account : accounts) {
                if (name.equals(account.getUsername()) && approveLoan(loggedInAccount)) {
                    account.depositCash(amount);
                    System.out.println("Loan deposited into the account with username: " + account.getUsername());
                    // Update the account in the file
                    FileHandler fileHandler = new FileHandler("newaccounts.txt");
                    fileHandler.updateAccountToFile(accounts);
                    return; // Exit the loop once a match is found
                }
            }
            // If no matching account is found
            System.out.println("No account found with the username: " + name);
        } else {
            System.out.println("Name cannot be empty or null");
        }
    }

}
