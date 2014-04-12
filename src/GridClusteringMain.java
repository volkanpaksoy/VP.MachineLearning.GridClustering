import java.io.BufferedReader;
import java.io.InputStreamReader;


public class GridClusteringMain {

	public static void main(String args[]) {
		
		ConsoleLogListener consoleListener = new ConsoleLogListener(); 
		Logger.attachListener(consoleListener);
		
		Logger.logMessage("---------------------------------");
		Logger.logMessage(" CLIQUE Algorithm Implementation ");
		Logger.logMessage("---------------------------------");
		
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		String strParam = null;
		CLIQUE clique = new CLIQUE();
		try {
			
			System.out.print("Enter the path of database: ");
			strParam = in.readLine();
			clique.setConnectionString(strParam);
			
			System.out.print("Enter value for threshold: ");
			strParam = in.readLine();
			clique.setThreshold(Integer.parseInt(strParam));
			
		} catch (Exception ex) {
			Logger.logMessage("Error while reading parameters : " + ex.getMessage());
			return;
		} 
		
		clique.run();
		
	}

}
