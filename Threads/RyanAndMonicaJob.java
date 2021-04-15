class BankAccount {
    private int balance = 100;

    public int getBalance() {
        return balance;
    }

    public void withDraw(int amount) {
        balance = balance - amount;
    }
}

public class RyanAndMonicaJob implements Runnable {
    private BankAccount account = new BankAccount();

    public static void main(String[] args) {
        RyanAndMonicaJob theJob = new RyanAndMonicaJob();

        Thread ryan = new Thread(theJob);
        Thread monica = new Thread(theJob);

        ryan.setName("Ryan");
        monica.setName("Monica");

        ryan.start();
        monica.start();
    }

    public void run() {
        for (int i=0; i<10; i++) {
            makeWithDrawl(10);

            if(account.getBalance() < 0) {
                System.out.println("Overdrawn!");
            }
        }
    }

    private synchronized void makeWithDrawl(int amount) {
        if(account.getBalance() >= amount) {
            System.out.println(Thread.currentThread().getName() + " is about to withdraw.");

            try {
                System.out.println(Thread.currentThread().getName() + " is going to sleep!");
                Thread.sleep(500);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " woke up.");

            account.withDraw(amount);

            System.out.println(Thread.currentThread().getName() + " completes the withdrawl.");

            System.out.println("Current Balance: " + account.getBalance());
        } else {
            System.out.println("Sorry, not enough for " + Thread.currentThread().getName());
        }
    }
}