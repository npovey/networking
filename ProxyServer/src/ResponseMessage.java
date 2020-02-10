import java.util.Map;

public class ResponseMessage {
	String version; // HTTP/1.1
	int statusCode; // 200
	String StatusMessage; // OK
	Map<String,String> headers;  //http headers
	byte[] body;
}
