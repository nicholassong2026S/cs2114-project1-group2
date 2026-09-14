package p1;
import java.io.*;

// -------------------------------------------------------------------------
/**
 *  Saves and loads the account to and from a file.
 * 
 *  @author Luke Stabler
 *  @version Sep 14, 2026
 */
public class AccountFileManager {
    
    // ----------------------------------------------------------
    /**
     * Writes balance and streak to filePath as one CSV line.
     * @param account
     * @param filePath
     * @throws IOException
     */
    static void saveAccount(Account account, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(account);
        }
    }

    // ----------------------------------------------------------
    /**
     * Reads the CSV line from the filePath and returns a new Account built from it.
     * @param filePath
     * @return Account
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static Account loadAccount(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Account) ois.readObject();
        }
    }
}
