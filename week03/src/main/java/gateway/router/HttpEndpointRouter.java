package gateway.router;

import java.util.List;


/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/22 下午21:33
 * @description HttpEndpointRouter 路由策略
 */
public interface HttpEndpointRouter {

    /**
     * 地址路由
     * @param endpoints 需要路由地址
     * @return 路由地址
     */
    String route(List<String> endpoints);
    
}
