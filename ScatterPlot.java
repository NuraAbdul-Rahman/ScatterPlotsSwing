import java.util.ArrayList;
/*
 * @author: Nura Abdul-Rahman: 2418644a
 */
public class ScatterPlot {

	//Method to determin scatter plot points
	public ArrayList<Points> scatterPlotPoints(ArrayList<BondTradeModel> graphData, ArrayList<Points> xAxis,
			ArrayList<Points> yAxis, int xInput, int yInput){
		ArrayList<Points> scatterPoints = new ArrayList<Points>();
		ArrayList<Double> xDataList = dataList(graphData,xInput);
		ArrayList<Double> yDataList = dataList(graphData,yInput);
		ArrayList<Double>xPoints = xAxisPoints(xDataList,xAxis);
		ArrayList<Double>yPoints = yAxisPoints(yDataList,yAxis);
		for(int i=0; i<xPoints.size();i++) {
			scatterPoints.add(i, new Points(xPoints.get(i),yPoints.get(i)));
		}
		return scatterPoints;
	}
   //Method to retreived a given ( from user selection) list data from retreived graph data
	public ArrayList<Double> dataList(ArrayList<BondTradeModel> graphData, int type) {
		ArrayList<Double> x = new ArrayList<Double>();

		for (int i = 0; i < graphData.size(); i++) {
			if (type == 0) {
				x.add(i, graphData.get(i).getYield());
			} else if (type == 1) {
				x.add(i, (double) (graphData.get(i).getDaysOfMaturity()));
			} else {
				x.add(i, graphData.get(i).getAmount());
			}
		}
		return x;
	}
	//Method to compute scatter plot x positions given xAxis values & position intervals
	public ArrayList<Double> xAxisPoints(ArrayList<Double> graphList, ArrayList<Points> xAxis) {
		ArrayList<Double> xList = new ArrayList<Double>();
		double xPos=0 ,xCordinateInterval = 0, xMovement = 0, xValueInterval=0;

		for (int i = 0; i < graphList.size(); i++) {
			for (int j = 0; j < xAxis.size(); j++) {
				if (j == xAxis.size() - 1) {
					if (graphList.get(i)== xAxis.get(j).getValue()) {
						xPos = xAxis.get(j).getX();
					} else if (graphList.get(i) > xAxis.get(j).getValue()) {
						double diff = graphList.get(i) - xAxis.get(j).getValue();
						double z = diff * xMovement;
						xPos = z + xAxis.get(j).getX();
					}
				}
				else {
					xValueInterval = xAxis.get(j+1).getValue() - xAxis.get(j).getValue();
					xCordinateInterval = xAxis.get(j+1).getX() - xAxis.get(j).getX();
					xMovement = xCordinateInterval/xValueInterval;
					if (graphList.get(i) == xAxis.get(j).getValue()) {
						xPos = xAxis.get(j).getX();
					} else if ((xAxis.get(j).getValue() < graphList.get(i))
							&& (graphList.get(i) < xAxis.get(j+1).getValue())) {
						double diff = graphList.get(i) - xAxis.get(j).getValue();
						double z = diff * xMovement;
						xPos = z + xAxis.get(j).getX();
					} else if (j == 0 && (graphList.get(i)< xAxis.get(j).getValue())) {
						double diff = xAxis.get(j).getValue() - graphList.get(i);
						double z = diff * xMovement;
						xPos = xAxis.get(j).getX() - z;
					}
				}				
				}
			xList.add(i,xPos);
			}
		return xList;
	}
	
	//Method to compute scatter plot y positions given yAxis values & position intervals
	public ArrayList<Double>yAxisPoints(ArrayList<Double>graphList, ArrayList<Points>yAxis){
		ArrayList<Double>yList = new ArrayList<Double>();
		double yPos=0,yCordinateInterval=0,yMovement=0,yValueInterval=0;
		
		for(int i=0; i< graphList.size(); i++) {
			for(int j=0; j<yAxis.size(); j++) {
				if(j==yAxis.size()-1) {
					if (graphList.get(i) == yAxis.get(j).getValue()) {
						yPos = yAxis.get(j).getY();
					} else if (graphList.get(i)> yAxis.get(j).getValue()) {
						double diff = graphList.get(i)- yAxis.get(j).getValue();
						double z = diff * yMovement;
						yPos = yAxis.get(j).getY() - z;
					}
				}
				else {
					yValueInterval = yAxis.get(j+1).getValue() - yAxis.get(j).getValue();
					yCordinateInterval = yAxis.get(j).getY() - yAxis.get(j+1).getY();
					yMovement = yCordinateInterval/yValueInterval;	
					if (graphList.get(i) == yAxis.get(j).getValue()) {
						yPos = yAxis.get(j).getY();
					} else if ((yAxis.get(j).getValue() < graphList.get(i))
							&& (graphList.get(i)< yAxis.get(j+1).getValue())) {
						double diff = graphList.get(i) - yAxis.get(j).getValue();
						double z = diff * yMovement;
						yPos = yAxis.get(j).getY() - z;
					} else if (j == 0 && (graphList.get(i)< yAxis.get(j).getValue())) {
						double diff = yAxis.get(j).getValue() - graphList.get(i);
						double z = diff * yMovement;
						yPos = z + yAxis.get(j).getY();
					}
				}
			}
			yList.add(i,yPos);
		}
		
		return yList;
	}

}
