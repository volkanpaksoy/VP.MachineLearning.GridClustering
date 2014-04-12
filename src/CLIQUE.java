
public class CLIQUE {

	private String m_strConnectionString;
	public void setConnectionString(String m_strConnectionString) {
		this.m_strConnectionString = m_strConnectionString;
	}
	public String getConnectionString() {
		return m_strConnectionString;
	}
	
	private int m_nThreshold;
	public void setThreshold(int nThreshold) {
		this.m_nThreshold = nThreshold;
	}
	public int getThreshold() {
		return m_nThreshold;
	}	
	
	private InputData input;
//	public int[][] m_grid = null;
	
	private GridMatrix m_matrix;
	
	
	public void run() {
		
		Logger.logMessage("CLIQUE started.");
		
		Database db = new TextFileDatabase();
		input = db.loadData(getConnectionString());
		
		int gridSize = 5;
		
		m_matrix = new GridMatrix(gridSize);
		m_matrix = GridMatrix.CreateGrid(5, 5);
		
		assignObjectsToGrids();
		
		m_matrix.drawMatrix();
		
		int nClusterId = 1;
		
		// Loop through all grids and check if they have at least threshold number of points
		for (Grid gr : m_matrix) {
			if (gr.getPointCount() >= getThreshold()) {
				
				// If grid's cluster ID is not it's default then don't start a new cluster.
				// Because it's already a part of another cluster now.
				if (gr.getClusterId() == -1)  
				{
					gr.setClusterId(nClusterId);
					nClusterId++;
				}
				
				// Check the neighbors. If they are "dense" too, add to this cluster.
				for (Grid grNeighbor : gr.getNeighbors()) {
					if (grNeighbor.getPointCount() >= getThreshold()) {
						grNeighbor.setClusterId(gr.getClusterId());
					}
				}
				
			}
		}
		
		/*
		for (Grid gr : m_matrix) {
			Logger.logMessage("Toplef point: " + gr.getTopLeftPoint().getX() + ", " + gr.getTopLeftPoint().getY() + "  Neighbor count: " + gr.getNeighbors().size());
		}
		*/
		
		// Print output  
		for (int i = 1 ; i <= nClusterId; i++) {
			Logger.logMessage("Cluster ID: " + i);
			Logger.logMessage("Objects belonging to cluster: ");
			
			for (Grid grid : m_matrix.getClusterGrids(i)) {
				
				for (ClusterObject obj : grid.getObjects()) {
					Logger.logMessage(obj.getAttribute(0) + ", " + obj.getAttribute(1));
				}
				
			}
			
		}
		
	}
	
	// Assign each point to a grid in the matrix..
	private void assignObjectsToGrids() {
	
		for (ClusterObject obj : input) {
			GridPoint newPoint = new GridPoint(obj.getAttribute(0), obj.getAttribute(1));
			
			for (Grid gr : m_matrix) {
				if (gr.isPointInRange(newPoint)) {
					gr.addObject(obj);
					break;
				}
			}
		}
	}
	
	
	
}
