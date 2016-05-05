package avalanche.httptest;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 使用httpclient访问sina，解析响应并回收资源全过程
 * @author luoyanying
 * @date 2016年5月3日
 */
public class GetSina {

	public static void main(String[] args) {
		CloseableHttpClient client = HttpClients.createDefault();

		// 请求起始行--HttpClient会根据信息自动构建
		HttpGet get = new HttpGet("http://www.sina.com.cn/");
		// 请求首部--可选的，User-Agent对于一些服务器必选，不加可能不会返回正确结果
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");

		try {
			// 执行请求
			CloseableHttpResponse response = client.execute(get);
			;
			// 获得起始行
			System.out.println(response.getStatusLine().toString() + "\n");
			// 获得首部---当然也可以使用其他方法获取
			Header[] hs = response.getAllHeaders();
			for (Header h : hs) {
				System.out.println(h.getName() + ":\t" + h.getValue() + "\n");
			}
			// 获取实体
			HttpEntity ety = response.getEntity();
			System.out.println(EntityUtils.toString(ety, "utf-8"));// 新浪网的编码格式utf-8
			EntityUtils.consume(ety);// 释放实体
			response.close();// 关闭响应
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
