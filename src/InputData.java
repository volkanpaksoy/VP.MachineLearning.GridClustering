import java.util.ArrayList;
import java.util.Iterator;

public class InputData implements Iterable<ClusterObject> {

	private ArrayList<ClusterObject> m_lstObjectList;
	
	@Override
	public Iterator<ClusterObject> iterator() {
		return m_lstObjectList.iterator();
	}
	
	public InputData()
	{
		m_lstObjectList = new ArrayList<ClusterObject>();
	}
	
	
	public void add(ClusterObject newObject) {
		m_lstObjectList.add(newObject);
	}
	
	public ClusterObject getPoint(int index) {
		
		if (index > m_lstObjectList.size())
			return null;
		
		return m_lstObjectList.get(index);
	}
	
	public int getSize() {
		return m_lstObjectList.size();
	}
}
