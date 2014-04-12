import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;

public class GridMatrix  implements Iterable<Grid> {

	private ArrayList<Grid> m_lstGrids = null;
	
	@Override
	public Iterator<Grid> iterator() {
		return m_lstGrids.iterator();
	}
	
	private int m_nSize;
	
	public GridMatrix(int size) {
		m_lstGrids = new ArrayList<Grid>();
		m_nSize = size;
	}
	
	public void addGrid(Grid g) {
		m_lstGrids.add(g);
	}
	
	
	public Grid getGrid(int x, int y) {
		return m_lstGrids.get(x * m_nSize + y);
	}
	
	// GridCount is the number of desired grids on one axis
	// For example, if gridCount = 5 and gridSize = 4 then a 5 x 5 matrix
	// will be created and each grid will be a square with size = 4 
	public static GridMatrix CreateGrid(int gridCount, int gridSize) {
		GridMatrix newMatrix = new GridMatrix(gridSize);
		
		for (int i = 0; i < gridCount; i++) {
			for (int j = 0; j < gridCount; j++) {
				// For size = 4, Topleft of the first grid is (0, 0). The next one on the 
				// same row will be (0, 4). The next one on the same column will (4, 0)..
				Grid g = new Grid(new GridPoint(i * gridSize, j * gridSize), gridSize);
				newMatrix.addGrid(g);
				
				// Add this as neighbor to its neighbors. Also add others as neighbor of this grid
				// above neighbor
				if (i >= 1) {
					g.addNeighborGrid(newMatrix.getGrid(i - 1, j));
					newMatrix.getGrid(i - 1, j).addNeighborGrid(g);
				}
				// left neighbor
				if (j >= 1) {
					g.addNeighborGrid(newMatrix.getGrid(i, j - 1));
					newMatrix.getGrid(i, j - 1).addNeighborGrid(g);
				}
				// above - left neighbor
				if (i >= 1 && j >= 1) {
					g.addNeighborGrid(newMatrix.getGrid(i - 1, j - 1));
					newMatrix.getGrid(i - 1, j - 1).addNeighborGrid(g);
				}
				// above - right
				if (i >= 1 && j < gridCount - 1) {
					g.addNeighborGrid(newMatrix.getGrid(i - 1, j + 1));
					newMatrix.getGrid(i - 1, j + 1).addNeighborGrid(g);
				}
				// Since the other neighbor grids are not created yet, we will only add
				// the ones on the left and above of the current grid.
			}
		}
		
		return newMatrix;
	}
	
	public String drawMatrix() {
		
		StringBuilder sb = new StringBuilder();
		int gridsOnAxis = (int)Math.sqrt(m_lstGrids.size());
		
		for (int i = 0; i < gridsOnAxis; i++) {
			sb.append("| ");
			
			for (int j = 0; j < gridsOnAxis; j++) {
				sb.append(this.getGrid(i, j).getPointCount());
				sb.append(" | ");
			}
			sb.append("\n");
		}
		
		Logger.logMessage(sb.toString());
		
		return sb.toString();
	}
	
	// Loops through all items and finds grids that belong to the specified cluster    
	public ArrayList<Grid> getClusterGrids(int nClusterId) {
		ArrayList<Grid> lstClusterGrids = new ArrayList<Grid>();
		
		for (Grid gr : m_lstGrids) {
			if (gr.getClusterId() == nClusterId) {
				lstClusterGrids.add(gr);
			}
		}
		
		return lstClusterGrids;
	}
	
	
}
