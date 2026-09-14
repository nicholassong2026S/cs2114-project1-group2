//
public class AccountFileManager {
    
    /*
        Writes balance and streak to filePath as one CSV line.
    */
    static void saveAccount(Account account, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(account);
        }
    }

    /*
        Reads the CSV line from the filePath and returns a new Account built from it.
    */

    static Account loadAccount(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Account) ois.readObject();
        }
    }
}
