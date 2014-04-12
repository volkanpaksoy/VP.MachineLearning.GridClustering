import java.io.*;

public class TextFileDatabase extends Database {

	private InputData m_clusterObjectList;   
	
	public InputData loadData(String filePath) throws ApplicationException {
		
		Logger.logMessage("Loading data...");
		
		m_clusterObjectList = new InputData();
		
		File inputFile = null;
		FileReader fileReader = null;
		BufferedReader reader = null;
		try {
			inputFile = new File(filePath);
			fileReader = new FileReader(inputFile);
			reader = new BufferedReader(fileReader);
			String strLine = reader.readLine();
			if (strLine == null) {
				throw new ApplicationException("No data found");
			}
			
			int nLineNumber = 0;
			do 
			{
				ClusterObject newObject = ClusterObject.fromRow(strLine);
				if (newObject != null)
				{
					newObject.setObjectId(nLineNumber);
					m_clusterObjectList.add(newObject);
				}
				
				strLine = reader.readLine();
				nLineNumber++;
				
			} while (strLine != null);
			
		} catch (IOException ex) {
			throw new ApplicationException("Error while reading database: " + ex.getMessage());
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (fileReader != null)
					fileReader.close();
			}
			catch (IOException ex) {
				throw new ApplicationException("Error while closing file: " + ex.getMessage());
			}
		}
		
		Logger.logMessage(String.format("Data loaded"));
		
		return m_clusterObjectList;
	}
	
	
	
	
}
