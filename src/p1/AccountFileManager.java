package p1;

import java.io.*;

// -------------------------------------------------------------------------
/**
 * Saves and loads the account to and from a file.
 * 
 * @author Luke Stabler
 * @version Sep 14, 2026
 */
public class AccountFileManager {

    // ----------------------------------------------------------
    /**
     * Saves the account (balance, streak, interest state) to filePath using
     * Java object serialization.
     * 
     * @param account the account to save
     * @param filePath the file to write to
     * @throws IOException if the file cannot be written
     */
    static void saveAccount(Account account, String filePath)
        throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(filePath))) {
            oos.writeObject(account);
        }
    }


    // ----------------------------------------------------------
    /**
     * Reads a serialized account from filePath and returns it.
     * 
     * @param filePath the file to read from
     * @return the Account stored in the file
     * @throws IOException if the file is missing, empty, or not a valid
     *             serialized object
     * @throws ClassNotFoundException if the stored object's class cannot be
     *             found
     */
    static Account loadAccount(String filePath)
        throws IOException,
        ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
            filePath))) {
            return (Account)ois.readObject();
        }
    }
}
