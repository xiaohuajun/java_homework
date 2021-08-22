package gateway;

import gateway.inbound.HttpInboundHandler;
import gateway.inbound.HttpInboundServer;
import java.util.Arrays;

/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/22 下午9:40
 * @description NettyApplicationServer netty实现网关服务
 */
public class NettyApplicationServer {


  public final static String GATEWAY_NAME = "NIOGateway";

  public static void main(String[] args)  {
    //监听端口
    String proxyPort = System.getProperty("proxyPort", "8888");
    //代理httpServer
    String proxyServers = System.getProperty("proxyServers",
        "http://localhost:8801,http://localhost:8802");
    int port = Integer.parseInt(proxyPort);
    System.out.println(GATEWAY_NAME + " starting...");
    //创建netty server
    HttpInboundServer httpInboundServer = new HttpInboundServer(port,
        Arrays.asList(proxyServers.split(",")));
    System.out.println(GATEWAY_NAME + " started");
    try {
      httpInboundServer.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
