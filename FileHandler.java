import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
    private static final long serialVersionUID = 123456789L;
    private String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    // Method to write ArrayList<Account> to file
    public void writeToFile(ArrayList<Account> accounts) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(accounts);
            System.out.println("Accounts written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read ArrayList<Account> from file
    public ArrayList<Account> readFromFile() {
        ArrayList<Account> accounts = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            accounts = (ArrayList<Account>) inputStream.readObject();
            System.out.println("Accounts read from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // updating the values:
    public void updateAccountToFile(ArrayList<Account> updatedAccounts) {
        writeToFile(updatedAccounts); // Use the existing writeToFile method to update the file
    }
}
