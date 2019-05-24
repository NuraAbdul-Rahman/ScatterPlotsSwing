import java.util.ArrayList;

/*
 * @author: Nura Abdul-Rahman: 2418644a
 */
public class BondTradeModel {

	private double yield;
	private int daysOfMaturity;
	private double amount;
   //Constructor
	public BondTradeModel(double yield, int daysOfMaturity, double amount) {
		this.yield = yield;
		this.daysOfMaturity = daysOfMaturity;
		this.amount = amount;
	}
   //Constructor
	public BondTradeModel() {

	}

	public double getYield() {
		return yield;
	}

	public void setYield(double yield) {
		this.yield = yield;
	}

	public int getDaysOfMaturity() {
		return daysOfMaturity;
	}

	public void setDaysOfMaturity(int daysOfMaturity) {
		this.daysOfMaturity = daysOfMaturity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	// Method to compute maximum yield from retreived data
	public double maxYield(ArrayList<BondTradeModel> data) {
		double max = 0.00;
		for (BondTradeModel d : data) {
			max = Math.max(max, d.getYield());
		}
		return max;
	}

	// Method to compute maximum days to maturity from retreived data
	public int maxMaturityDays(ArrayList<BondTradeModel> data) {
		int max = 0;
		for (BondTradeModel d : data) {
			max = Math.max(max, d.getDaysOfMaturity());
		}
		return max;
	}

	// Method to compute maximum amount from retreived data
	public double maxAmount(ArrayList<BondTradeModel> data) {
		double max = 0.00;
		for (BondTradeModel d : data) {
			max = Math.max(max, d.getAmount());
		}
		return max;
	}

	// Method to compute minimum yield from retreived data
	public double minYield(ArrayList<BondTradeModel> data) {
		double min = maxYield(data);
		for (BondTradeModel d : data) {
			min = Math.min(min, d.getYield());
		}
		return min;
	}

	// Method to compute minimum days to maturity from retreived data
	public int minMaturityDays(ArrayList<BondTradeModel> data) {
		int min = maxMaturityDays(data);
		for (BondTradeModel d : data) {
			min = Math.min(min, d.getDaysOfMaturity());
		}
		return min;
	}

	// Method to compute minimum amount from retreived data
	public double minAmount(ArrayList<BondTradeModel> data) {
		double min = maxAmount(data);
		for (BondTradeModel d : data) {
			min = Math.min(min, d.getAmount());
		}
		return min;
	}

}
