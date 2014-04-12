
public class ConsoleLogListener extends LogListener{

	@Override
	public void update(String message, boolean newLine) {
		System.out.print(message);
		
		if (newLine)
			System.out.println();
	}

	
}
