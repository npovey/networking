import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MiniChatServer {
	public static final int PORT = 8000;
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(MiniChatServer.PORT)) {
			System.out.println("SERVER Waiting for connection");
			Socket connection = serverSocket.accept();
			
			// scanner will be reading input from input stream
			Scanner scan = new Scanner(connection.getInputStream());
			
			PrintWriter writer = new PrintWriter(connection.getOutputStream());
			
			while(!connection.isClosed()) {
				System.out.println("SERVER Waiting for input");
				String text = scan.nextLine();
				System.out.println("SERVER Read" + text);
				writer.println("SERVER Blah "+ text);
				writer.flush();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		
	}

}
