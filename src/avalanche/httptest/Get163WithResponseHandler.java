package avalanche.httptest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 使用responsehandler处理响应
 * 
 * @author luoyanying
 * @date 2016年5月3日
 */
public class Get163WithResponseHandler {

	public static void main(String[] args) {
		CloseableHttpClient client = HttpClients.createDefault();

		// 请求起始行--HttpClient会根据信息自动构建
		HttpGet get = new HttpGet("http://www.163.com/");
		// 请求首部--可选的，User-Agent对于一些服务器必选，不加可能不会返回正确结果
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");

		ResponseHandler<String> rl = new BasicResponseHandler();
		try {
			String responseBody = client.execute(get, rl);
			System.out.println(responseBody);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			;
		}
	}

}
