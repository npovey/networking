import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class ProxyServer {
	public static final int PORT = 80;
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(ProxyServer.PORT)){
			while (true) {
				System.out.println("Waiting for browser");
				try (Socket connection = serverSocket.accept()) {
					System.out.println("reading request from browser");
					RequestMessage reqMes = StreamUtilities.readRequest(connection);
					// change the pass 
					String [] str = reqMes.path.split("/",4);
					System.out.println("str3" + str[3]);
					reqMes.path = "/" + str[3];
					reqMes.version = "HTTP/1.0";
					ResponseMessage rm;
					reqMes.headers.put("Connection", "close");
					reqMes.headers.put("Proxy-Connection", "close");

					try (Socket connection2 = new Socket(reqMes.headers.get("Host"), ProxyServer.PORT)){
						// write request
						System.out.println("writing request from browser");

						 StreamUtilities.writeRequest(connection2,reqMes);
						 System.out.println("reading response from webserver");
						 rm =  StreamUtilities.readResponse(connection2);
						 // change write response code so it can write response
						 // write that response message to a browser.
					} 
					System.out.println("writing response to browser");
					StreamUtilities.writeResponse(connection, rm);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}



