
import java.util.ArrayList;

public class ClusterObject {

	private ArrayList<Double> m_lstAttributeList;
	
	private int m_nObjectId = -1;
	public void setObjectId(int m_nObjectId) {
		this.m_nObjectId = m_nObjectId;
	}
	public int getObjectId() {
		return m_nObjectId;
	}
	
	
	private int m_nClusterId = -1;
	public void setClusterId(int m_nClusterId) {
		this.m_nClusterId = m_nClusterId;
	}
	public int getClusterId() {
		return m_nClusterId;
	}
	
	
	
	public ClusterObject() {
		m_lstAttributeList = new ArrayList<Double>();
	}
	
	public ClusterObject(int nObjectId) {
		
		m_nObjectId = nObjectId;	
	}
	
	public ClusterObject(int nObjectId, int nClusterId) {
		
		m_nObjectId = nObjectId;
		m_nClusterId = nClusterId;
	}
	
	public void addAttribute(double dValue) {
		m_lstAttributeList.add(dValue);
	}

	public double getAttribute(int index) {
		return m_lstAttributeList.get(index);
	}

	public int getSize() {
		return m_lstAttributeList.size();
	}
	
	public static ClusterObject fromRow(String strLine) {
		return ClusterObject.fromRow(-1, strLine);
	}
	
	public static ClusterObject fromRow(int nObjectId, String strLine) {
		
		if (strLine == null || strLine.isEmpty() == true)
			return null;
		
		String[] strItems = strLine.split(",");
		ClusterObject newObj = new ClusterObject();
		
		for (int i = 0; i < strItems.length; i++) {
			newObj.addAttribute(Double.parseDouble(strItems[i].trim()));
		}
		
		return newObj;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		String strResult = "";
		
		sb.append("{");
		for (double d : m_lstAttributeList) {
			sb.append(d);
			sb.append(",");
		}
		
		strResult = sb.toString().substring(0, sb.length() - 1);
		strResult += "}"; 
		
		return strResult;
	}



}
