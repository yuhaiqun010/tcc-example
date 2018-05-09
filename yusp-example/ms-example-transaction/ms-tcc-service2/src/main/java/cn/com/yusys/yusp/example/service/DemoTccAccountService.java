/*
 * 代码生成器自动生成的
 * Since 2008 - 2018
 *
 */
package cn.com.yusys.yusp.example.service;

import cn.com.yusys.yusp.example.domain.DemoTccAccount;
import cn.com.yusys.yusp.example.repository.mapper.DemoTccAccountMapper;
import org.bytesoft.compensable.Compensable;
import org.bytesoft.compensable.CompensableCancel;
import org.bytesoft.compensable.CompensableConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Service
@Compensable(interfaceClass = IDemoTccAccountService.class, simplified = true)
public class DemoTccAccountService implements IDemoTccAccountService{

    @Autowired
    private DemoTccAccountMapper demoTccAccountMapper;

    @Transactional
    public void increaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {

        DemoTccAccount tccAccount = demoTccAccountMapper.selectByPrimaryKey(acctId);
        System.out.println("Try处理:冻结金额:"+new BigDecimal(tccAccount.getFrozen().doubleValue()+amount).doubleValue());
        //冻结金额+amount
        DemoTccAccount updateAccount = new DemoTccAccount();
        updateAccount.setAcctId(acctId);
        updateAccount.setFrozen(new BigDecimal(tccAccount.getFrozen().doubleValue()+amount));
        int size = demoTccAccountMapper.updateByPrimaryKeySelective(updateAccount);

        if (size != 1) {
            throw new IllegalStateException("ERROR!");
        }
        System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);
        System.out.println("Try处理结束...");
        // throw new IllegalStateException("error");
    }

    @CompensableConfirm
    @Transactional
    public void comfirmIncreaseAmount(String acctId, double amount) {
        DemoTccAccount tccAccount = demoTccAccountMapper.selectByPrimaryKey(acctId);
        DemoTccAccount updateAccount = new DemoTccAccount();

        System.out.println("Confirm处理：转账操作，转入："+new BigDecimal(tccAccount.getAmount().doubleValue()+amount).doubleValue() +
                "，解决冻结金额:"+new BigDecimal(updateAccount.getFrozen().doubleValue()-amount).doubleValue());

        //金额+,冻结金额-
        updateAccount.setAcctId(acctId);
        updateAccount.setAmount(new BigDecimal(tccAccount.getAmount().doubleValue()+amount));
        updateAccount.setFrozen(new BigDecimal(tccAccount.getFrozen().doubleValue()-amount));

        int size = demoTccAccountMapper.updateByPrimaryKeySelective(updateAccount);

        if (size != 1) {
            throw new IllegalStateException("ERROR!");
        }
        System.out.printf("done decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }

    @CompensableCancel
    @Transactional
    public void cancleIncreaseAmount(String acctId, double amount) {
        DemoTccAccount tccAccount = demoTccAccountMapper.selectByPrimaryKey(acctId);
        System.out.println("Cancel处理:");
        //异常时，将冻结金额恢复
        DemoTccAccount updateAccount = new DemoTccAccount();
        updateAccount.setAcctId(acctId);
        updateAccount.setFrozen(new BigDecimal(tccAccount.getFrozen().intValue()-amount));
        int size = demoTccAccountMapper.updateByPrimaryKeySelective(updateAccount);

        if (size != 1) {
            throw new IllegalStateException("ERROR!");
        }
        System.out.printf("undo decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }
}
