import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LoanHandler {
    private static final long serialVersionUID = 123456789L;
    private static final String LOANS_FILE = "loans.txt";

    public void saveLoans(ArrayList<Loan> loans) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LOANS_FILE));
            oos.writeObject(loans);
            oos.close();
            System.out.println("Loan applications saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Loan> readLoans() {
        ArrayList<Loan> loanList = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LOANS_FILE));
            loanList = (ArrayList<Loan>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading data from loans file.");
        }
        return loanList;
    }

}
