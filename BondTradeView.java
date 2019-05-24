import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * @author: Nura Abdul-Rahman: 2418644a
 */
@SuppressWarnings({ "serial","rawtypes", "unchecked" })
public class BondTradeView extends JFrame {
	private JTextField fileName = new JTextField(20);
	private JTextField selectedField = new JTextField(50);
	private BondTradeGraphComponent bondTradeGraph;
	private JButton openButton = new JButton("Open");
	private JButton quitButton = new JButton("Quit");
	private String[] bondLabels = { "YIELD", "DAYS_TO_MATURITY", "AMOUNT_CHF(000)" };
	private JComboBox xList = new JComboBox(bondLabels);
	private JComboBox yList = new JComboBox(bondLabels);
	
	public BondTradeView(BondTradeModel b){
    bondTradeGraph= new BondTradeGraphComponent(b);
    this.setSize(800, 600);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	componentSetUp();
	this.setVisible(true);
	}
	//Component SetUp: top panel,lower panel & Drawing area for scatter graph
		private void componentSetUp() {
			JPanel topPanel = new JPanel(new BorderLayout());
			JPanel lowerPanel = new JPanel(new BorderLayout());
			topPanel.setPreferredSize(new Dimension(500, 35));
			topPanel.setBackground(Color.WHITE);
			topPanel.add(openButton, BorderLayout.WEST);
			topPanel.add(fileName, BorderLayout.CENTER);
			topPanel.add(quitButton, BorderLayout.EAST);
			this.getContentPane().add(topPanel, BorderLayout.NORTH);
			lowerPanel.setPreferredSize(new Dimension(500, 50));
			lowerPanel.setBackground(Color.WHITE);
			yList.setPreferredSize(new Dimension(100, 40));
			xList.setPreferredSize(new Dimension(100,40 ));
			lowerPanel.add(xList, BorderLayout.WEST);
			lowerPanel.add(yList, BorderLayout.CENTER);
			lowerPanel.add(selectedField, BorderLayout.EAST);
			this.getContentPane().add(lowerPanel, BorderLayout.SOUTH);
			this.getContentPane().setBackground(Color.WHITE);
			this.getContentPane().add(bondTradeGraph, BorderLayout.CENTER);
		}
		
		//ActionListener Methods
		void addSelectFileActionListener(ActionListener open ) {
			openButton.addActionListener(open);
		}
		
		void addQuitActionListener(ActionListener quit) {
			quitButton.addActionListener(quit);
		}
		
		void addXListActionListener(ActionListener listX) {
			xList.addActionListener(listX);
		}
		void addYListActionListener(ActionListener listY) {
			yList.addActionListener(listY);
		}
		public BondTradeGraphComponent getBondTradeGraph() {
			return bondTradeGraph;
		}
		
		public void setBondTradeGraph(BondTradeGraphComponent bondTradeGraph) {
			this.bondTradeGraph = bondTradeGraph;
		}
		public JComboBox getxList() {
			return xList;
		}
		public JComboBox getyList() {
			return yList;
		}
		public JTextField getFileName() {
			return fileName;
		}
		public JTextField getSelectedField() {
			return selectedField;
		}
		
		
		
		
	
}
