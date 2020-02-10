import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MiniChatClient {

	public static void main(String[] args) {
		try(Socket connection = new Socket("127.0.0.1", MiniChatServer.PORT)) {
			System.out.println("Waiting for connection");
			
			// scanner will be reading input from input stream
			Scanner scan = new Scanner(connection.getInputStream());
			Scanner inputScan = new Scanner(System.in);

			
			PrintWriter writer = new PrintWriter(connection.getOutputStream());
			
			while(!connection.isClosed()) {
				System.out.println("type a message to send ");
				String message = inputScan.nextLine();
				
				if (message.equals("")) {
					break;
				}
				System.out.println("Sending" + message);
				writer.println(message);
				writer.flush();
				
				System.out.println("Response: " );
				System.out.println(scan.nextLine());

			}
			
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
	}

}
