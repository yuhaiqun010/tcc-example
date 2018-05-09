/*
 * 代码生成器自动生成的
 * Since 2008 - 2018
 *
 */
package cn.com.yusys.yusp.example.service;

import cn.com.yusys.yusp.example.feign.ITccAccountServce1;
import cn.com.yusys.yusp.example.feign.ITccAccountServce2;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Compensable(interfaceClass = IDemoTccAccountService.class, simplified = true)
public class DemoTccAccountService implements  IDemoTccAccountService{

    @Autowired
    private ITccAccountServce1 servce1;
    @Autowired
    private ITccAccountServce2 servce2;

    /**
     * 转账操作
     * @param targetAcctId 目标账号
     * @param sourceAcctId1 转出账号
     * @param amount1 转账金额
     */
    @Transactional(propagation= Propagation.REQUIRED)
    public void transfer(String targetAcctId, String sourceAcctId1, double amount1) {

        //转账，从A账户（service1）转到B账户（service2）
        //先从账户A扣减，然后B账户进账
        //为演示事务，先进行B账户进账，然后A账户扣减，如果A账户余额不足，则B账户回退
        this.servce2.increaseAmount(targetAcctId, amount1);// 账号B冻结金额
        this.servce1.decreaseAmount(sourceAcctId1, amount1);// 账号A冻结金额

    }

    public void reset(){

    }

}
