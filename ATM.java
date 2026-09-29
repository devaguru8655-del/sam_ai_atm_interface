import java.util.Scanner;

// Task 3 - ATM Interface
// Simple ATM with withdraw, deposit and balance check.

// This class holds the account data and the operations
class BankAccount {
    private double balance;

    BankAccount(double openingBalance) {
        this.balance = openingBalance;
    }

    double getBalance() {
        return balance;
    }

    void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        balance += amount;
        System.out.printf("Rs. %.2f deposited successfully.%n", amount);
        System.out.printf("New balance: Rs. %.2f%n", balance);
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance! You only have Rs. " + String.format("%.2f", balance));
        } else {
            balance -= amount;
            System.out.printf("Rs. %.2f withdrawn. Please collect your cash.%n", amount);
            System.out.printf("Remaining balance: Rs. %.2f%n", balance);
        }
    }

    void checkBalance() {
        System.out.printf("Your current balance is Rs. %.2f%n", balance);
    }
}

public class ATM {

    // reads an amount from the user, returns -1 if it isn't a number
    static double readAmount(Scanner sc, String message) {
        System.out.print(message);
        if (!sc.hasNext()) return -1;
        String text = sc.next();
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount(5000);   // starting balance
        final String PIN = "1234";

        System.out.println("===== Welcome to SAM ATM =====");

        // 3 tries for the PIN
        boolean loggedIn = false;
        for (int tries = 1; tries <= 3; tries++) {
            System.out.print("Enter your 4-digit PIN: ");
            if (!sc.hasNext()) return;
            if (sc.next().equals(PIN)) {
                loggedIn = true;
                break;
            }
            System.out.println("Wrong PIN. Attempts left: " + (3 - tries));
        }

        if (!loggedIn) {
            System.out.println("Too many wrong attempts. Card blocked.");
            return;
        }

        int choice = 0;
        while (choice != 4) {
            System.out.println("\n1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (!sc.hasNext()) break;
            String input = sc.next();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 4.");
                choice = 0;
                continue;
            }

            switch (choice) {
                case 1:
                    account.withdraw(readAmount(sc, "Enter amount to withdraw: "));
                    break;
                case 2:
                    account.deposit(readAmount(sc, "Enter amount to deposit: "));
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using SAM ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        sc.close();
    }
}
