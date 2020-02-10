import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
// web server replies using the protocol
// it doesn't even know who it is talking to.
// it gets a request
// it sends a response
// microsoft.com is a human readable name
// we have to convert it to ip address
// use DNS to do this
// it converts microsoft.com to DNS servers.
// we got an ip to sned to. 
// get says we make a request but we are not gonna change anything
// don't make purchase
// post we gonna store something back end.
// get is nullipotent
// post ship a package
// get send a mail catalog

public class MiniWebServer {
	public static final int PORT = 80;
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(MiniWebServer.PORT)){
			while(true) {
				// listening to port 80
				// web browser connecting to port 80.
				try(Socket connection = serverSocket.accept()) {
					RequestMessage req_mes = readRequest(connection);
//					InputStream inputByteStream = connection.getInputStream();
//					String first = readLine(inputByteStream);
//					
//					Scanner scan = new Scanner(first);
//					
//					System.out.println("Reading request");
//					
//					String method = scan.next();
//					String url = scan.next();
//					String protocol = scan.next();
//					
//					
//					System.out.println("HTTP method is " + method); // GET or POST
//					System.out.println("HTTP url is" + url); // only slash "/" 
//					// url has no space inside
//					System.out.println("HTTP protocol is " + protocol); // HTTP/1.1					
//					
//					String header = readLine(inputByteStream );
//					int num = 0;
//					while(!header.isEmpty()) {
//						System.out.println(header);
//						header = readLine(inputByteStream );
//						if(header.startsWith("Content-Length:")) {
//							String [] splt = header.split(":", 2);
//							num = Integer.parseInt(splt[1].trim());
//						}
//					}
//					
//					byte[] content = new byte[num];
//					inputByteStream.read(content);
//					System.out.print("-------------");
//					System.out.write(content);
//					// we don't know who will talk to us
//					// asking me for a specific web page
//					// 
//					//String page = "<html> <head> <title>Hello </title> </head> <body>Hello World!</body></html>";
//					
					writeResponse(connection);
//					File file = new File("index.html");
//					byte[] bytes = Files.readAllBytes(file.toPath());
//
//					PrintWriter writer = new PrintWriter(connection.getOutputStream());
//					writer.print("HTTP/1.1 200 OK\r\n");
//					writer.print("Content-Length: " + bytes.length + "\r\n");
//					writer.print("\r\n"); // end of headers
//					writer.flush(); //CONCEPT: must flush
//					connection.getOutputStream().write(bytes);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readLine(InputStream s) throws IOException {
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		byte last_byte = 0;
		byte current_byte = (byte)s.read();
		while(last_byte != '\r' && current_byte != '\n') {
			bytes.add(current_byte);
			last_byte = current_byte;
			current_byte = (byte)s.read();
		}
		byte [] arr = new byte[bytes.size()-1];
		for(int i = 0; i < bytes.size()-1; i++) {
			arr[i] = bytes.get(i);
		}
		return new String(arr, StandardCharsets.UTF_8);
	}
	public static RequestMessage readRequest(Socket  connection) throws IOException {
		RequestMessage rm = new RequestMessage();
		rm.headers = new HashMap<String, String>();

		InputStream inputByteStream = connection.getInputStream();
		String first = readLine(inputByteStream);
		Scanner scan = new Scanner(first);
		
		System.out.println("Reading request");
		String method = scan.next();
		String url = scan.next();
		String protocol = scan.next();
		
		rm.method = method;
		rm.path = url;
		rm.version = protocol;
		
		System.out.println("HTTP method is " + method); // GET or POST
		System.out.println("HTTP url is" + url); // only slash "/" 
		// url has no space inside
		System.out.println("HTTP protocol is " + protocol); // HTTP/1.1	
		
		String header = readLine(inputByteStream);
		int num = 0;

		while (!header.isEmpty()) {
			System.out.println(header);
			if(header.startsWith("Content-Length:")) {
				String [] splt = header.split(":", 2);
				num = Integer.parseInt(splt[1].trim());
			}
			String [] splt = header.split(":", 2);
			System.out.println("splt[0]: '" + splt[0] + "'");
			rm.headers.put(splt[0], splt[1]);
			header = readLine(inputByteStream );
		}
		// after the header comes body
		// body can be zero bytes
		// int num = 0 initialized to zero
		byte[] body = new byte[num];
		inputByteStream.read(body);
		System.out.print("-------------");
		System.out.write(body);
		return rm;
	}
	
	public static void writeResponse(Socket  connection) throws IOException {
		File file = new File("index.html");
		byte[] bytes = Files.readAllBytes(file.toPath());

		PrintWriter writer = new PrintWriter(connection.getOutputStream());
		writer.print("HTTP/1.1 200 OK\r\n");
		writer.print("Content-Length: " + bytes.length + "\r\n");
		writer.print("\r\n"); // end of headers
		writer.flush(); //CONCEPT: must flush
		connection.getOutputStream().write(bytes);
	}
}
