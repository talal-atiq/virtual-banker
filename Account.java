import java.io.Serializable;

abstract class Account implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private String username;
    private String accountType;
    private int age;
    private String nationality;
    private String occupation;
    private String password;
    private double cash;

    public Account(String username, String accountType, int age, String nationality,
            String occupation, String password, double cash) {
        this.username = username;
        this.accountType = accountType;
        this.age = age;
        this.nationality = nationality;
        this.occupation = occupation;
        this.password = password;
        this.cash = cash;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

    public abstract void transfer(Account targetAccount, double amount);

    public abstract double getInterest(double amount);

    public void depositCash(double amount) {
        this.cash += amount;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\n Type: " + getAccountType() + "\nAge: " + age + "\nNationality: "
                + nationality
                + "\n Cash: " + cash;
    }
}

class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.05; // 5%

    public SavingsAccount(String username, int age, String nationality, String occupation, String password,
            double cash) {
        super(username, "Savings", age, nationality, occupation, password, cash);
        setAccountType("Savings");
    }

    @Override
    public double getInterest(double amount) {
        return amount * INTEREST_RATE;
    }

    @Override

    public void withdraw(double amount) {
        if (amount > 0 && amount <= getCash()) {
            double interest = amount * INTEREST_RATE;
            if (getCash() >= (amount + interest)) {
                setCash(getCash() - (amount + interest));
            } else {
                // Handle insufficient funds
                System.out.println("Insufficient funds to withdraw including interest");
            }
        } else {
            // Handle invalid withdrawal amount
            System.out.println("Invalid withdrawal amount or insufficient funds");
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            double interest = amount * INTEREST_RATE;
            setCash(getCash() + amount + interest);
        } else {
            // Handle invalid deposit amount
            System.out.println("Invalid deposit amount");
        }
    }

    @Override
    public void transfer(Account targetAccount, double amount) {
        if (amount > 0 && amount <= getCash()) {
            double interest = amount * INTEREST_RATE;
            withdraw(amount + interest);
            targetAccount.depositCash(amount);
        }
    }

}

class InternationalAccount extends Account {
    private static final double INTEREST_RATE = 0.1; // 10%

    public InternationalAccount(String username, int age, String nationality, String occupation, String password,
            double cash) {
        super(username, "International", age, nationality, occupation, password, cash);
        setAccountType("International");
    }

    @Override
    public double getInterest(double amount) {
        return amount * INTEREST_RATE;
    }

    @Override

    public void withdraw(double amount) {
        if (amount > 0 && amount <= getCash()) {
            double interest = amount * INTEREST_RATE;
            if (getCash() >= (amount + interest)) {
                setCash(getCash() - (amount + interest));
            } else {
                // Handle insufficient funds
                System.out.println("Insufficient funds to withdraw including interest");
            }
        } else {
            // Handle invalid withdrawal amount
            System.out.println("Invalid withdrawal amount or insufficient funds");
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            double interest = amount * INTEREST_RATE;
            setCash(getCash() + amount + interest);
        } else {
            // Handle invalid deposit amount
            System.out.println("Invalid deposit amount");
        }
    }

    @Override
    public void transfer(Account targetAccount, double amount) {
        if (amount > 0 && amount <= getCash()) {
            double interest = amount * INTEREST_RATE;
            withdraw(amount + interest);
            targetAccount.depositCash(amount);
        }
    }

}

class BusinessAccount extends Account {
    private static final double INTEREST_RATE = 0.08; // 8%

    public BusinessAccount(String username, int age, String nationality, String occupation, String password,
            double cash) {
        super(username, "Business", age, nationality, occupation, password, cash);
        setAccountType("Business");
    }

    @Override
    public double getInterest(double amount) {
        return amount * INTEREST_RATE;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            double interest = amount * INTEREST_RATE;
            setCash(getCash() + amount + interest);
        } else {
            // Handle invalid deposit amount
            System.out.println("Invalid deposit amount");
        }
    }

    @Override

    public void withdraw(double amount) {
        if (amount > 0 && amount <= getCash()) {
            double interest = amount * INTEREST_RATE;
            if (getCash() >= (amount + interest)) {
                setCash(getCash() - (amount + interest));
            } else {
                // Handle insufficient funds
                System.out.println("Insufficient funds to withdraw including interest");
            }
        } else {
            // Handle invalid withdrawal amount
            System.out.println("Invalid withdrawal amount or insufficient funds");
        }
    }

    @Override
    public void transfer(Account targetAccount, double amount) {
        if (amount > 0 && amount <= getCash()) {
            double interest = amount * INTEREST_RATE;
            withdraw(amount + interest);
            targetAccount.depositCash(amount);
        }
    }

}