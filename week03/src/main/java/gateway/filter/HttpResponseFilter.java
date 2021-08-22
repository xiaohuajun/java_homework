package gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;


/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/22 下午10:05
 * @description HttpResponseFilter 简单的http请求返回过滤器
 */
public interface HttpResponseFilter {

  /**
   * 过滤器
   *
   * @param response 请求返回内容
   */
  void filter(FullHttpResponse response);

}
