package http;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/15 下午9:49
 * @description HttpClientRequest : 发起http请求
 */
public class HttpClientRequest {


  public static void main(String[] args) {
    String url = "http://localhost:8801";
    System.out.println(httpGetRequest(url));
  }


  public static String httpGetRequest(String url) {
    // 创建 HttpClient 客户端
    CloseableHttpClient httpClient = HttpClients.createDefault();
    // 创建 HttpGet 请求
    HttpGet httpGet = new HttpGet(url);
    try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
      System.out.println(httpResponse.getStatusLine());
      HttpEntity entity1 = httpResponse.getEntity();
      return EntityUtils.toString(entity1, "UTF-8");
    } catch (IOException e) {
      throw new RuntimeException("调用异常：", e);
    }
  }


}
