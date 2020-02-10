import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StreamUtilities {
	public static String readLine(InputStream s) throws IOException {
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		byte last_byte = 0;
		byte current_byte = (byte)s.read();
		while (last_byte != '\r' && current_byte != '\n') {
			bytes.add(current_byte);
			last_byte = current_byte;
			current_byte = (byte)s.read();
		}
		byte [] arr = new byte[bytes.size() - 1];
		for (int i = 0; i < bytes.size() - 1; i++) {
			arr[i] = bytes.get(i);
		}
		return new String(arr, StandardCharsets.UTF_8);
	}
	
	public static byte [] readUntilEndOfStream(InputStream s) throws IOException {
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		try {
			int current_byte = s.read();
			while (current_byte != -1) {
				bytes.add((byte)current_byte);
				current_byte = s.read();
			}
		} catch (java.net.SocketException ex) {
			ex.printStackTrace();
			
		}
		byte [] arr = new byte[bytes.size()];
		for (int i = 0; i < bytes.size(); i++) {
			arr[i] = bytes.get(i);
		}
		return arr;
	}
	public static RequestMessage readRequest(Socket connection) throws IOException {
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
		
//		System.out.println("HTTP method is " + method); // GET or POST
//		System.out.println("HTTP url is" + url); // only slash "/" 
//		// url has no space inside
//		System.out.println("HTTP protocol is " + protocol); // HTTP/1.1	
		
		String header = readLine(inputByteStream);
		int num = 0;

		while (!header.isEmpty()) {
			System.out.println(header);
			if(header.startsWith("Content-Length:")) {
				
				String [] splt = header.split(":", 2);
				num = Integer.parseInt(splt[1].trim());
				System.out.println("num" + num);
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
	
	public static void writeResponse(Socket connection, ResponseMessage rm) throws IOException {
		// need request message
		PrintWriter writer = new PrintWriter(connection.getOutputStream());
		
		writer.print(rm.version);
		writer.print(" ");
		writer.print(rm.statusCode); // slash "/" is the path
		writer.print(" ");
		writer.print(rm.StatusMessage);
		writer.print("\r\n");

		for (Map.Entry<String, String> entry : rm.headers.entrySet()) {
			writer.print(entry.getKey() + ": " + entry.getValue());
			writer.print("\r\n");
		}
		writer.print("\r\n");
		writer.flush(); //CONCEPT: must flush
		
		// don't use print writer for the body use getOutputStream
		// PrintWriter writes characters
		// body is bytes
		// for bytes use getOutputStream()
		byte[] body = rm.body;
		connection.getOutputStream().write(body);
		
//		File file = new File("index.html");
//		byte[] bytes = Files.readAllBytes(file.toPath());
//
//		PrintWriter writer = new PrintWriter(connection.getOutputStream());
//		writer.print("HTTP/1.1 200 OK\r\n");
//		writer.print("Content-Length: " + bytes.length + "\r\n");
//		writer.print("\r\n"); // end of headers
//		writer.flush(); //CONCEPT: must flush
//		connection.getOutputStream().write(bytes);
	}
	
	public static void writeRequest(Socket connection, RequestMessage rm) throws IOException {
		// need request message
		PrintWriter writer = new PrintWriter(connection.getOutputStream());
		
		writer.print(rm.method);
		writer.print(" ");
		writer.print(rm.path); // slash "/" is the path
		writer.print(" ");
		writer.print(rm.version);
		writer.print("\r\n");

		for (Map.Entry<String, String> entry : rm.headers.entrySet()) {
			writer.print(entry.getKey() + ": " + entry.getValue());
			writer.print("\r\n");
		}
		writer.print("\r\n");
		writer.flush(); //CONCEPT: must flush
		
		// don't use print writer for the body use getOutputStream
		// PrintWriter writes characters
		// body is bytes
		// for bytes use getOutputStream()
		byte[] body = rm.body;
		if (body != null) {
			connection.getOutputStream().write(body);
		}
	}
	
	public static ResponseMessage readResponse(Socket connection) throws IOException {
		ResponseMessage rm = new ResponseMessage();
		rm.headers = new HashMap<String, String>();

		InputStream inputByteStream = connection.getInputStream();
		String first = readLine(inputByteStream);
		Scanner scan = new Scanner(first);
		
		
//		String version; // HTTP/1.1
//		int statusCode; // 200
//		String StatusMessage; // OK
//		Map<String,String> headers;  //http headers
//		byte[] body;
		
		
		System.out.println("Reading response");
		String version = scan.next();
		int statusCode = scan.nextInt();
		String StatusMessage = scan.nextLine();
		
		rm.version = version;
		rm.statusCode = statusCode;
		rm.StatusMessage = StatusMessage;
		
//		System.out.println("HTTP method is " + method); // GET or POST
//		System.out.println("HTTP url is" + url); // only slash "/" 
//		// url has no space inside
//		System.out.println("HTTP protocol is " + protocol); // HTTP/1.1	
		
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
//		byte[] body = new byte[num];
//		inputByteStream.read(body);
//		rm.body = body;
//		System.out.write(body);
//		connection.getOutputStream().close();
		byte[] ans = readUntilEndOfStream(inputByteStream);
		rm.body = ans;
		System.out.println("body -----------");
		System.out.write(ans);
		return rm;
	}
	
}
