import java.util.Map;

public class RequestMessage {
	String method;
	String path;
	String version;
	Map<String,String> headers;
	byte[] body;
}
