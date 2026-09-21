package p1;

import java.io.*;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

// -------------------------------------------------------------------------
/**
 *  Test account file manager
 * 
 *  @author Luke Stabler
 *  @version Sep 21, 2026
 */
public class AccountFileManagerTest
{
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
 
    // ----------------------------------------------------------
    /**
     * Tests saveAccount method of the AccountFileManager
     * @throws Exception
     */
    @Test
    public void testSaveAccount() throws Exception
    {
        File file = new File(tempFolder.getRoot(), "account.dat");
        Account account = new Account(150.75, 3);
 
        assertFalse(file.exists());
        AccountFileManager.saveAccount(account, file.getPath());
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
 
        Account newer = new Account(20.0, 1);
        AccountFileManager.saveAccount(newer, file.getPath());
        Account loaded = AccountFileManager.loadAccount(file.getPath());
        assertEquals(20.0, loaded.getBalance(), 0.001);
        assertEquals(1, loaded.getStreak());
 
        String badPath = new File(tempFolder.getRoot(), "noSuchFolder/account.dat").getPath();
        try
        {
            AccountFileManager.saveAccount(account, badPath);
            fail("Expected IOException for a path in a missing folder");
        }
        catch (IOException e)
        {
            assertTrue(e instanceof FileNotFoundException);
            assertFalse(new File(badPath).exists());
        }
    }
 
    // ----------------------------------------------------------
    /**
     * Tests loadAccount method of the AccountFileManager
     * @throws Exception
     */
    @Test
    public void testLoadAccount() throws Exception
    {
        File file = new File(tempFolder.getRoot(), "account.dat");
        Account original = new Account(150.75, 3);
        AccountFileManager.saveAccount(original, file.getPath());
 
        Account loaded = AccountFileManager.loadAccount(file.getPath());
        assertNotNull(loaded);
        assertNotSame(original, loaded);
        assertEquals(150.75, loaded.getBalance(), 0.001);
        assertEquals(3, loaded.getStreak());
 
        String missingPath = new File(tempFolder.getRoot(), "missing.dat").getPath();
        try
        {
            AccountFileManager.loadAccount(missingPath);
            fail("Expected FileNotFoundException for a missing file");
        }
        catch (FileNotFoundException e)
        {
            assertTrue(e.getMessage().contains("missing.dat"));
            assertFalse(new File(missingPath).exists());
        }
 
        File emptyFile = tempFolder.newFile("empty.dat");
        try
        {
            AccountFileManager.loadAccount(emptyFile.getPath());
            fail("Expected EOFException for an empty file");
        }
        catch (EOFException e)
        {
            assertEquals(0, emptyFile.length());
        }
 
        File textFile = tempFolder.newFile("notAnAccount.dat");
        FileWriter writer = new FileWriter(textFile);
        writer.write("this is not a serialized object");
        writer.close();
        try
        {
            AccountFileManager.loadAccount(textFile.getPath());
            fail("Expected StreamCorruptedException for a plain text file");
        }
        catch (StreamCorruptedException e)
        {
            assertTrue(e.getMessage().contains("invalid stream header"));
        }
 
        File wrongTypeFile = tempFolder.newFile("wrongType.dat");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(wrongTypeFile));
        oos.writeObject("I am a String, not an Account");
        oos.close();
        try
        {
            AccountFileManager.loadAccount(wrongTypeFile.getPath());
            fail("Expected ClassCastException for a file holding a String");
        }
        catch (ClassCastException e)
        {
            assertTrue(e.getMessage().contains("java.lang.String"));
        }
    }
}
