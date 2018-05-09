package cn.com.yusys.yusp.example.feign;

import org.springframework.stereotype.Component;

//@Component
public class TccAccountServce1Hystrix implements ITccAccountServce1  {

    @Override
    public void decreaseAmount(String accountId, double amount) {

        System.out.println("TccAccountServce1Hystrix...");

    }
}
