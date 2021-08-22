package gateway.router;

import java.util.List;
import java.util.Random;


/**
 * @author xiaohuajun
 * @version 1.0
 * @date 2021/8/22 下午10:03
 * @description RandomHttpEndpointRouter 随机路由策略
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter {


    /**
     * 地址随机路由
     * @param urls 需要路由地址
     * @return 路由地址
     */
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }
}
