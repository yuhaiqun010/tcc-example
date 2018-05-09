package cn.com.yusys.yusp.example.feign;

import org.springframework.stereotype.Component;

//@Component
public class TccAccountServce2Hystrix implements  ITccAccountServce2{
    @Override
    public void increaseAmount(String accountId, double amount) {
        System.out.println("TccAccountServce2Hystrix...");
    }
}
