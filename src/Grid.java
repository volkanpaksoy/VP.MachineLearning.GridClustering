import java.util.ArrayList;

public class Grid {

	private GridPoint m_ptTopLeft;
	public GridPoint getTopLeftPoint() {
		return m_ptTopLeft;
	}
	
	private int m_nSize;
	
	private int m_nClusterId = -1;
	public void setClusterId(int nClusterId) {
		this.m_nClusterId = nClusterId;
	}
	public int getClusterId() {
		return m_nClusterId;
	}
	
	private ArrayList<ClusterObject> m_lstObjects = null;
	
	private ArrayList<Grid> m_lstNeighborGrids = null;
	
	public Grid(GridPoint topLeft, int size) {
		m_lstObjects = new ArrayList<ClusterObject>();
		m_lstNeighborGrids = new ArrayList<Grid>();
		
		m_ptTopLeft = topLeft;
		m_nSize = size;
	}

	public void addObject(ClusterObject obj) {
		m_lstObjects.add(obj);
	}
	
	public void addNeighborGrid(Grid grid) {
		m_lstNeighborGrids.add(grid);
	}
	
	public ArrayList<Grid> getNeighbors() {
		return m_lstNeighborGrids;
	}
	
	public ArrayList<ClusterObject> getObjects() {
		return m_lstObjects;
	}
	
	// For a point to fall into range of a grid, its distance on both axis
	// must be smaller than size.
	public boolean isPointInRange(GridPoint pt) {
		boolean result = false;
		
		if (Math.abs(m_ptTopLeft.getX() - pt.getX()) <= m_nSize &&
			Math.abs(m_ptTopLeft.getY() - pt.getY()) <= m_nSize) {
			result = true;
		}
		
		return result;
	}
	
	public int getPointCount() {
		return m_lstObjects.size();
	}
	
}
