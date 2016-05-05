package avalanche.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

public abstract class Client {
	private HttpClient httpclient;
	
	public Client(){
		
	}
	
	abstract HttpResponse sendMsg();
}
