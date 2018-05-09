package cn.com.yusys.yusp.example.feign;

import cn.com.yusys.yusp.uaa.client.oauth.AuthorizedFeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@AuthorizedFeignClient(name = "ms-tcc-service2", fallback = TccAccountServce2Hystrix.class)
@AuthorizedFeignClient(name = "ms-tcc-service2")
public interface ITccAccountServce2 {

    @RequestMapping(method = RequestMethod.POST, value = "/api/increase")
    public void increaseAmount(@RequestParam("acctId") String accountId, @RequestParam("amount") double amount);
}
