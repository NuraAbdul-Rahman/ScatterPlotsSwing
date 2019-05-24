import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComponent;

/*
 * @author: Nura Abdul-Rahman: 2418644a
 */
@SuppressWarnings("serial")
public class BondTradeGraphComponent extends JComponent {
	private ArrayList<BondTradeModel> graphData = new ArrayList<BondTradeModel>();
	private ArrayList<Points> xAxis = new ArrayList<Points>();
	private ArrayList<Points> yAxis = new ArrayList<Points>();
	private ArrayList<Points> scatterPoints = new ArrayList<Points>();
	private String[] dataHeaders = new String[3];
	private ScatterPlot s = new ScatterPlot();
	private BondTradeModel bondT;
	private int axisDivision = 10;
	private int padding = 5;
	private int labelPadding = 15;
	private int pointWidth = 9;
	private int userXSelection;
	private int userYSelection;

	public BondTradeGraphComponent(BondTradeModel b) {
		bondT = b;
	}

	public void paintComponent(Graphics g) {
		if (!getGraphData().isEmpty()) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setBackground(Color.WHITE);
			int width = this.getWidth();
			int height = this.getHeight();
			g2.drawRect(50, 0, width - 90, height - 38);
			// Creating ticks & assigning values for x & y axis.
			for (int i = 0; i <= axisDivision; i++) {
				int x0 = padding + labelPadding;
				int y0 = height - ((i * (height - padding * 7 - labelPadding)) / axisDivision + padding + labelPadding);
				int y1 = width - (i * (width - padding - 8 * labelPadding) / axisDivision + labelPadding + padding);
				g2.setColor(Color.BLACK);
				// Ticks & values for y axis
				int yValue = (int) ((axisInterval(getUserYSelection()) / axisDivision) * i);
				g2.drawString(yValue + "", 5, y0 - 12);
				int yaxisx = 45;
				int yaxisy = y0 - 18;
				// Saving y axis points into an array
				yAxis.add(i, new Points(yValue, yaxisx, yaxisy));
				g2.drawLine(x0 + 29, y0 - 18, 45, y0 - 18);
				// Ticks & values for x axis
				int xValue = (int) (((axisInterval(getUserXSelection())) / axisDivision) * i);
				g2.drawString(xValue + "", width - y1 + 27, height - 20);
				int xaxisx = width - y1 + 27;
				int xaxisy = height - 38;
				// Saving x axis points into an array
				xAxis.add(i, new Points(xValue, xaxisx, xaxisy));
				g2.drawLine(y1 - 55, height - 33, y1 - 55, height - 38);
			}
			ArrayList<Points> p = s.scatterPlotPoints(getGraphData(), xAxis, yAxis, getUserXSelection(),
					getUserYSelection());
			setScatterPoints(p);
			// Drawing scatter plots from scatter data
			for (Points r : getScatterPoints()) {
				Ellipse2D.Double e = new Ellipse2D.Double(r.getX(), r.getY(), pointWidth, pointWidth);
				g2.setColor(Color.BLUE);
				g2.fill(e);
			}
			xAxis.clear();
			yAxis.clear();
		}
	}

	// Method to read csv file contents and save into an arraylist
	public void readFileData(File newFile) {
		String fileName = newFile.getName();
		try {
			if (fileName.endsWith("csv")) {
				FileReader fileReader = new FileReader(newFile.getPath());
				BufferedReader buffer = new BufferedReader(fileReader);
				String line;
				int count = 0;
				while ((line = buffer.readLine()) != null) {
					String[] data = line.split(",");
					if (count == 0) {
						for (int i = 0; i < dataHeaders.length; i++) {
							dataHeaders[i] = data[i];
						}
					} else {
						storeGraphData(data);
					}
					count++;
				}
				buffer.close();
			} else {
				System.out.println("Wrong file selected, selected file should be CSV");
				System.out.println("Select another file");
			}
		} catch (FileNotFoundException e) {
			System.out.println("This file is not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// A method to store csv data into an arraylist
	public void storeGraphData(String[] data) {
		double yield = Double.parseDouble(data[0]);
		int daysOfMaturity = Integer.parseInt(data[1]);
		double amount = Double.parseDouble(data[2]);
		BondTradeModel tradeItem = new BondTradeModel(yield, daysOfMaturity, amount);
		graphData.add(tradeItem);
	}

	public int getUserXSelection() {
		return userXSelection;
	}

	public void setUserXSelection(int userXSelection) {
		this.userXSelection = userXSelection;
	}

	public int getUserYSelection() {
		return userYSelection;
	}

	public String[] getDataHeaders() {
		return dataHeaders;
	}

	public void setUserYSelection(int userYSelection) {
		this.userYSelection = userYSelection;
	}

	public ArrayList<BondTradeModel> getGraphData() {
		return graphData;
	}

	public ArrayList<Points> getxAxis() {
		return xAxis;
	}

	public ArrayList<Points> getyAxis() {
		return yAxis;
	}

	public ArrayList<Points> getScatterPoints() {
		return scatterPoints;
	}

	public void setScatterPoints(ArrayList<Points> scatterPoints) {
		this.scatterPoints = scatterPoints;
	}

	// A method to determine the difference between maximum and minimum values of
	// selected axis
	public double axisInterval(int input) {
		double interval = 0;
		if (input == 0) {
			interval = bondT.maxYield(getGraphData()) - bondT.minYield(getGraphData());
		} else if (input == 1) {
			interval = bondT.maxMaturityDays(getGraphData()) - bondT.minMaturityDays(getGraphData());
		} else if (input == 2) {
			interval = bondT.maxAmount(getGraphData()) - bondT.minAmount(getGraphData());
		}
		return interval;
	}

	// Method to determine cordinates of the mouse click in scatter point
	public String findPoint(int x, int y) {
		String pointDescription = "";
		for (int i = 0; i < scatterPoints.size(); i++) {
			int xPoint = (int) scatterPoints.get(i).getX();
			int higherX = xPoint + pointWidth;
			int yPoint = (int) scatterPoints.get(i).getY();
			int higherY = yPoint + pointWidth;
			if ((xPoint <= x) && (x <= higherX)) {
				if ((yPoint <= y) && (y <= higherY)) {
					pointDescription = "Yield: " + getGraphData().get(i).getYield() + ", Days to Maturity: "
							+ getGraphData().get(i).getDaysOfMaturity() + ",  Amount: "
							+ getGraphData().get(i).getAmount();
					return pointDescription;
				}
			}
		}

		return pointDescription;
	}

}
