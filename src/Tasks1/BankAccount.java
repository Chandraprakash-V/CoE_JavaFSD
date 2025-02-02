package Tasks1;
class BankAccount {
	private double balance;

	public BankAccount(double initialBalance) {
		this.balance = initialBalance;
	}

	public synchronized void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println(Thread.currentThread().getName() + " deposited " + amount + ". New balance: " + balance);
		} else {
			System.out.println("Invalid deposit amount.");
		}
	}

	public synchronized void withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ". New balance: " + balance);
		} else {
			System.out.println(Thread.currentThread().getName() + " --- invalid withdrawal.");
		}
	}

	public double getBalance() {
		return balance;
	}

	public static void main(String[] args) {
		BankAccount account = new BankAccount(1000);

		Runnable depositTask = () -> {
			for (int i = 0; i < 5; i++) {
				account.deposit(100);
				try { Thread.sleep(100); 
				} catch (InterruptedException e)
				{ e.printStackTrace(); }
			}
		};

		Runnable withdrawTask = () -> {
			for (int i = 0; i < 5; i++) {
				account.withdraw(150);
				try { Thread.sleep(100); 
				} catch (InterruptedException e)
				{ e.printStackTrace(); }
			}
		};

		Thread user1 = new Thread(depositTask, "User1");
		Thread user2 = new Thread(withdrawTask, "User2");

		user1.start();
		user2.start();

		try {
			user1.join();
			user2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Final Balance: " + account.getBalance());
	}
}
