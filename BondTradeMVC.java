/*
 * @author: Nura Abdul-Rahman: 2418644a
 */
@SuppressWarnings("unused")
public class BondTradeMVC {

	public static void main(String[] args) {

		BondTradeModel model = new BondTradeModel();
		BondTradeView view = new BondTradeView(model);
		BondTradeController controller = new BondTradeController(model, view);
	}

}
