/*
 * @author: Nura Abdul-Rahman:2418644a
 */

public class Points {
	
	private double x;
	private double y;
	int value ;
	//1st constructor for x & y cordinates
	public Points(double x, double y) {
		this.x=x;
		this.y=y;
	}
	//2nd constructor for value, x cordinate & y cordinate
	public Points ( int value,double x, double y) {
		this.value = value;
		this.x=x;
		this.y=y;
	}
	//setters & getters
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	

}
