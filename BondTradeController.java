import java.awt.event.*;
import java.io.File;

/*
 * @author: Nura Abdul-Rahman: 2418644a
 */
@SuppressWarnings("unused")
public class BondTradeController {
	private BondTradeView bond_View;
	private BondTradeModel bond_Model;
	private FileChooser selectFile = new FileChooser();
	
	// Contructor
	public BondTradeController(BondTradeModel model, BondTradeView view) {
		bond_View = view;
		bond_Model = model;
		bond_View.addXListActionListener(new XListActionListener());
		bond_View.addYListActionListener(new YListActionListener());
		bond_View.addQuitActionListener(new QuitActionListener());
		bond_View.addSelectFileActionListener(new SelectFileActionListener());
		bond_View.getBondTradeGraph().addMouseListener(new PointMouseListener());
	}
	
   //Listener Classes
	class XListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bond_View.getBondTradeGraph().setUserXSelection(bond_View.getxList().getSelectedIndex());
			bond_View.getBondTradeGraph().repaint();
		}
	}

	class YListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bond_View.getBondTradeGraph().setUserYSelection(bond_View.getyList().getSelectedIndex());
			bond_View.getBondTradeGraph().repaint();
		}
	}

	class QuitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	class SelectFileActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File newFile = selectFile.chooseFile();
			if( newFile==null) {
				System.out.println("No file was selected, select a CSV file to proceed");
			}
			else {
			bond_View.getFileName().setText(newFile.getName());
			bond_View.getBondTradeGraph().readFileData(newFile);
			bond_View.getxList().setSelectedIndex(0);
			bond_View.getyList().setSelectedIndex(1);
			bond_View.getBondTradeGraph().setUserXSelection(bond_View.getxList().getSelectedIndex());
			bond_View.getBondTradeGraph().setUserYSelection(bond_View.getyList().getSelectedIndex());
			bond_View.getBondTradeGraph().repaint();
			}
		}
	}

	class PointMouseListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			String pointDesc = bond_View.getBondTradeGraph().findPoint(e.getX(), e.getY());
			bond_View.getSelectedField().setText(pointDesc);
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

}
