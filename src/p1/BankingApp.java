import java.io.IOException;
import java.util.Scanner;

public class BankingApp {
    private Account account;
    private StreakRewards streakRewards;
    private Scanner scanner;

    public BankingApp() {
        this.scanner = new Scanner(System.in);
        this.streakRewards = new StreakRewards(); // Needs to be implemented[cite: 4]
    }

    // Starting point[cite: 9]
    public static void main(String[] args) {
        BankingApp app = new BankingApp();
        
        // Loads or creates an Account[cite: 9]
        try {
            app.account = AccountFileManager.loadAccount("balance.csv"); //[cite: 8]
        } catch (IOException e) {
            System.out.println("No existing account found. Creating a new one.");
            app.account = new Account();
        }
        
        app.run();
    }

    // Menu loop[cite: 9]
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
                        AccountFileManager.saveAccount(account, "balance.csv"); //[cite: 8]
                    } catch (IOException e) {
                        System.out.println("Failed to save account state.");
                    }
                    break;
                default: System.out.println("Invalid option. Try again.");
            }
        }
    }

    public void viewBalance() {
        account.payInterest(); //[cite: 9]
        System.out.printf("Current Balance: $%.2f\n", account.getBalance());
        System.out.println("Current Streak: " + account.getStreak());
        System.out.println("Current Interest Rate: " + (account.getCurrentAnnualRate() * 100) + "%");
    }

    public void deposit() {
        while (true) {
            try {
                double amount = promptForAmount("Enter deposit amount: "); //[cite: 9, 10]
                account.deposit(amount);
                streakRewards.earnStreakCoins(account.getStreak());
                
                System.out.printf("Deposited $%.2f successfully.\n", amount);
                System.out.printf("New Balance: $%.2f | Streak: %d | Streak Coins: %d\n", 
                                  account.getBalance(), account.getStreak(), streakRewards.getStreakCoinBalance());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Please try again."); // Reprompts[cite: 9]
            }
        }
    }

    public void withdraw() {
        while (true) {
            try {
                double amount = promptForAmount("Enter withdrawal amount: "); //[cite: 9, 10]
                account.withdraw(amount);
                System.out.printf("Withdrew $%.2f successfully.\n", amount);
                System.out.printf("New Balance: $%.2f\n", account.getBalance());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Please try again."); // Reprompts[cite: 9]
            }
        }
    }

    public void viewStreakCoins() {
        System.out.println("Streak Coins: " + streakRewards.getStreakCoinBalance()); //[cite: 9]
    }

    public void redeemStreakCoins() {
        while (true) {
            try {
                int coinsToSpend = promptForWholeNumber("Enter amount of Streak Coins to redeem: "); //[cite: 10]
                InterestBoost boost = streakRewards.redeemStreakCoins(coinsToSpend); //[cite: 8]
                account.applyInterestBoost(boost);
                System.out.println("Interest boost applied successfully!");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage() + " Please try again."); // Reprompts[cite: 10]
            }
        }
    }

    private double promptForAmount(String prompt) throws EmptyInputException, NonNumericInputException, InvalidAmountException {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return InputValidator.validateAmount(input); //[cite: 10]
    }

    private int promptForWholeNumber(String prompt) throws EmptyInputException, NonNumericInputException, InvalidAmountException {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return InputValidator.validateWholeNumber(input); //[cite: 10]
    }
}