package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;


/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/22 下午10:08
 * @description HttpRequestFilter 简单的http请求过滤器
 */
public interface HttpRequestFilter {

    /**
     * http请求过滤器
     *
     * @param fullRequest http请求
     * @param ctx 通道内容
     */
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
    
}
