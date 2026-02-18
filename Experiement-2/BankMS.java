// BankManagementSystem
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

interface BankOperations {
    void deposit(double amount);

    void withdraw(double amount) throws InsufficientBalanceException;

    double checkBalance();
}

class BankAccount implements BankOperations {

    private String customerName;
    private double balance;

    public BankAccount(String customerName, double balance) {
        this.customerName = customerName;
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance)
            throw new InsufficientBalanceException("Insufficient Balance!");
        balance -= amount;
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    public void showCustomerName() {
        System.out.println("Customer: " + customerName.toUpperCase());
    }

    public void divideBalance(int value) {
        System.out.println("Result: " + (balance / value));
    }
}

public class BankMS {
    public static void main(String[] args) {

        try {
            BankAccount acc = new BankAccount(null, 5000);

            acc.deposit(1000);
            acc.withdraw(2000);

            // NullPointerException
            acc.showCustomerName();

            // ArithmeticException
            acc.divideBalance(0);

        } catch (InsufficientBalanceException e) {
            System.out.println("Checked Exception: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Runtime Exception: Null Pointer");
        } catch (ArithmeticException e) {
            System.out.println("Runtime Exception: Arithmetic Error");
        }
    }
}
