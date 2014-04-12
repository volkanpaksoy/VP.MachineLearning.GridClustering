import java.util.ArrayList;

public class Logger {


	private static ArrayList<LogListener> m_lstListeners = new ArrayList<LogListener>();
	
	public static void logMessage(String message) {

	//	notifyListeners(message);
		
		logMessage(message, true);
	}
	
	public static void logMessage(String message, boolean newLine) {

		notifyListeners(message, newLine);
	}
	
	public static void attachListener(LogListener listener) {
		m_lstListeners.add(listener);
	}
	
	public static void detachListener(LogListener listener) {
		m_lstListeners.remove(listener);
	}
	
	private static void notifyListeners(String message, boolean newLine) {
		
		for (LogListener listener : m_lstListeners) {
			listener.update(message, newLine);
		}
	}


}
