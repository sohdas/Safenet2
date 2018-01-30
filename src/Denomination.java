public class Denomination {

	private final int preStock = 10;
	private int value;
	private int amount;

	public Denomination() {
		value = 0;
		amount = preStock;
	}

	public Denomination(int v) {
		value = v;
		amount = preStock;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
