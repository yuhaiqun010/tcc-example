package cn.com.yusys.yusp.example.feign;

import cn.com.yusys.yusp.uaa.client.oauth.AuthorizedFeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@AuthorizedFeignClient(name = "ms-tcc-service1", fallback = TccAccountServce1Hystrix.class)
@AuthorizedFeignClient(name = "ms-tcc-service1")
public interface ITccAccountServce1 {

    @RequestMapping(method = RequestMethod.POST, value = "/api/decrease")
    public void decreaseAmount(@RequestParam("acctId") String accountId, @RequestParam("amount") double amount);
}
