
public class GridPoint {

	private double m_X;
	public void setX(double x) {
		this.m_X = x;
	}
	public double getX() {
		return m_X;
	}
	
	private double m_Y;
	public void setY(double y) {
		this.m_Y = y;
	}
	public double getY() {
		return m_Y;
	}
	
	public GridPoint(double x, double y) {
		m_X = x;
		m_Y = y;
	}
	
}
