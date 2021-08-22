package gateway.inbound;

import gateway.filter.HeaderHttpRequestFilter;
import gateway.filter.HttpRequestFilter;
import gateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/22 下午10:20
 * @description HttpInboundHandler
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

  private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
  private final List<String> proxyServer;
  private HttpOutboundHandler handler;
  private HttpRequestFilter filter = new HeaderHttpRequestFilter();

  public HttpInboundHandler(List<String> proxyServer) {
    this.proxyServer = proxyServer;
    this.handler = new HttpOutboundHandler(this.proxyServer);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    try {
      System.out.println("channelRead流量接口请求开始，时间为" + new Date());
      FullHttpRequest fullRequest = (FullHttpRequest) msg;
      String uri = fullRequest.uri();
      System.out.println("接收到的请求url为" + uri);
      handler.handle(fullRequest, ctx, filter);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ReferenceCountUtil.release(msg);
    }
  }


}
