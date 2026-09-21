import java.io.IOException;
import java.util.Scanner;

/**
 * Has the console menu loop and brings all of the other classes together[cite: 1].
 * 
 * @author Group 2: Andersson, Luke, Nicholas, Ashutosh[cite: 1]
 * @version 2026.09.21
 */
public class BankingApp {
    private Account account;
    private StreakRewards streakRewards;
    private Scanner scanner;

    /**
     * Constructs the BankingApp, initializing the scanner and StreakRewards.
     */
    public BankingApp() {
        this.scanner = new Scanner(System.in);
        this.streakRewards = new StreakRewards(); 
    }

    /**
     * Starting point. Loads or creates an Account and then calls run()[cite: 9].
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        BankingApp app = new BankingApp();
        
        try {
            app.account = AccountFileManager.loadAccount("balance.csv"); 
        } catch (IOException e) {
            System.out.println("No existing account found. Creating a new one.");
            app.account = new Account();
        }
        
        app.run();
    }

    /**
     * Displays menu loop until user exits[cite: 9].
     */
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- SaveStreak Menu ---");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Streak Coins");
            System.out.println("5. Redeem Streak Coins");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1": viewBalance(); break;
                case "2": deposit(); break;
                case "3": withdraw(); break;
                case "4": viewStreakCoins(); break;
                case "5": redeemStreakCoins(); break;
                case "6": 
                    running = false; 
                    try {
                        AccountFileManager.saveAccount(account, "balance.csv"); 
                    } catch (IOException e) {
                        System.out.println("Failed to save account state.");
                    }
                    break;
                default: System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Pays interest and then prints current balance, streak, and interest rate[cite: 9].
     */
    public void viewBalance() {
        account.payInterest(); 
        System.out.printf("Current Balance: $%.2f\n", account.getBalance());
        System.out.println("Current Streak: " + account.getStreak());
        System.out.println("Current Interest Rate: " + (account.getCurrentAnnualRate() * 100) + "%");
    }

    /**
     * Prompts for an amount, validates it, and then deposits it into the account. 
     * Adds StreakCoins based on streak. Prints the new balance, streak, and 
     * Streak Coins earned. If the value does not pass validation, returns an 
     * issue and then reprompts for the value[cite: 9].
     */
    public void deposit() {
        while (true) {
            try {
                double amount = promptForAmount("Enter deposit amount: "); 
                account.deposit(amount);
                streakRewards.earnStreakCoins(account.getStreak());
                
                System.out.printf("Deposited $%.2f successfully.\n", amount);
                System.out.printf("New Balance: $%.2f | Streak: %d | Streak Coins: %d\n", 
                                  account.getBalance(), account.getStreak(), streakRewards.getStreakCoinBalance());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Please try again."); 
            }
        }
    }

    /**
     * Prompts for an amount, validates it, and then withdraws it from the account. 
     * Prints the new balance. If the value does not pass validation, returns an 
     * issue and then reprompts for the value[cite: 9].
     */
    public void withdraw() {
        while (true) {
            try {
                double amount = promptForAmount("Enter withdrawal amount: "); 
                account.withdraw(amount);
                System.out.printf("Withdrew $%.2f successfully.\n", amount);
                System.out.printf("New Balance: $%.2f\n", account.getBalance());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Please try again."); 
            }
        }
    }

    /**
     * Prints the current StreakCoin balance[cite: 9].
     */
    public void viewStreakCoins() {
        System.out.println("Streak Coins: " + streakRewards.getStreakCoinBalance()); 
    }

    /**
     * Prompts for a whole-number Streak Coin amount, validates it, and then 
     * redeems the coins and applies the respective interestBoost[cite: 9, 10].
     */
    public void redeemStreakCoins() {
        while (true) {
            try {
                int coinsToSpend = promptForWholeNumber("Enter amount of Streak Coins to redeem: "); 
                InterestBoost boost = streakRewards.redeemStreakCoins(coinsToSpend); 
                account.applyInterestBoost(boost);
                System.out.println("Interest boost applied successfully!");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Please try again."); 
            }
        }
    }

    /**
     * Prints prompt, reads one line from the console, and returns InputValidator.validateAmount() of it[cite: 10].
     * 
     * @param prompt The prompt to display to the user.
     * @return The validated double amount.
     * @throws EmptyInputException If the input is empty[cite: 10].
     * @throws NonNumericInputException If the input contains letters or symbols[cite: 10].
     * @throws InvalidAmountException If the input is negative or out of range[cite: 10].
     */
    private double promptForAmount(String prompt) throws EmptyInputException, NonNumericInputException, InvalidAmountException {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return InputValidator.validateAmount(input); 
    }

    /**
     * Prints prompt, reads one line from the console, and returns InputValidator.validateWholeNumber() of it[cite: 10].
     * 
     * @param prompt The prompt to display to the user.
     * @return The validated integer amount.
     * @throws EmptyInputException If the input is empty[cite: 10].
     * @throws NonNumericInputException If the input contains letters, symbols, or decimals[cite: 10].
     * @throws InvalidAmountException If the input is negative or out of range[cite: 10].
     */
    private int promptForWholeNumber(String prompt) throws EmptyInputException, NonNumericInputException, InvalidAmountException {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return InputValidator.validateWholeNumber(input); 
    }
}