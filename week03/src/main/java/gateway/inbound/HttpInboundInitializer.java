package gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import java.util.List;

/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/22 下午10:02
 * @description HttpInboundInitializer 通道初始化
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

  private final List<String> proxyServer;

  public HttpInboundInitializer(List<String> proxyServer) {
    this.proxyServer = proxyServer;
  }

  @Override
  public void initChannel(SocketChannel ch) {
    ChannelPipeline p = ch.pipeline();
    p.addLast(new HttpServerCodec());
    p.addLast(new HttpObjectAggregator(1024 * 1024));
    p.addLast(new HttpInboundHandler(this.proxyServer));
  }
}
