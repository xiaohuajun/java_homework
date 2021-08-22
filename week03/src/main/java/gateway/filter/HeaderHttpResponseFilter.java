package gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;


/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/22 下午10:15
 * @description HeaderHttpResponseFilter 简单的http请求返回过滤器
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("xiaohuajun", "xiao");
    }
}
