import java.util.Scanner;

public class ATM {
	final static int preStock = 10;

	public static void main(String[] args) {

		String ans = "";

		Denomination[] stock = new Denomination[7];

		stock[0] = new Denomination(1);
		stock[1] = new Denomination(5);
		stock[2] = new Denomination(10);
		stock[3] = new Denomination(20);
		stock[4] = new Denomination(50);
		stock[5] = new Denomination(100);
		stock[5] = new Denomination(1000);

		String[] demSplit;

		System.out.println(
				">Enter R to restock\n>Enter W to withdraw\n>Enter I and type in a denomination to view amounts"
						+ "\n>Enter Q to quit");
		Scanner input = new Scanner(System.in);
		while (!ans.contains("Q")) {
			ans = input.nextLine().toUpperCase();

			if (ans.contains("R")) {
				restock(stock);
				System.out.println("Restocked.");
			} else if (ans.contains("W")) {
				if (ans.contains("$"))
					withdraw(stock, Integer.parseInt(ans.substring(ans.indexOf("$") + 1).trim()));
				else
					withdraw(stock, Integer.parseInt(ans.substring(1).trim()));
			} else if (ans.contains("I")) {
				demSplit = ans.substring(2).split(" ");
				displayAmts(demSplit, stock);
			}
		}

		input.close();
	}

	private static void displayAmts(String[] demSplit, Denomination[] stock) {
		boolean flag = false;
		for (String s : demSplit) {
			int x = Integer.parseInt(s);

			for (int y = 0; y < 7; y++) {
				if (x == stock[y].getValue()) {
					flag = true;

					System.out.println("$" + x + " - " + stock[y].getAmount());
				}
			}
		}
		if (flag == false)
			System.out.println("Invalid denomination.");
	}

	private static void withdraw(Denomination[] orig, int wAmt) {
		try {

			Denomination[] stock = orig.clone();

			int x = 6;
			int finAmt = wAmt;

			while (wAmt != 0) {
				if (wAmt >= stock[x].getValue()) {

					if (stock[x].getAmount() > 0) {
						stock[x].setAmount(stock[x].getAmount() - 1);
						wAmt -= stock[x].getValue();
					} else
						x--;
				} else if (wAmt < stock[x].getValue()) {
					x--;
				}
			}

			System.out.println("Success: Dispensed $" + finAmt);
			System.out.println("Machine balance: ");

			String[] all = { "1000", "100", "50", "20", "10", "5", "1" };
			displayAmts(all, stock);

			orig = stock.clone();

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Insufficient funds");
		}
	}

	private static void restock(Denomination[] stock) {
		for (int x = 0; x < 7; x++) {
			if (stock[x].getAmount() != preStock)
				stock[x].setAmount(preStock);
		}
	}

}
