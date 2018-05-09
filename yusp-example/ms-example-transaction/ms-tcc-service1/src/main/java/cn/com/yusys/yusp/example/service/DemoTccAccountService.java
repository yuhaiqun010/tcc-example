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
public class DemoTccAccountService implements  IDemoTccAccountService{

    @Autowired
    private DemoTccAccountMapper demoTccAccountMapper;

    @Transactional
    public void decreaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {

        DemoTccAccount tccAccount = demoTccAccountMapper.selectByPrimaryKey(acctId);
        double total = tccAccount.getAmount().doubleValue();

        System.out.println("Try处理:冻结金额:"+new BigDecimal(tccAccount.getFrozen().doubleValue()+amount).doubleValue());

        //判断是否足额
        if (total < amount) {
            throw new IllegalStateException("Amount not enough!");
        }

        //冻结金额
        DemoTccAccount updateAccount = new DemoTccAccount();
        updateAccount.setAcctId(acctId);
        updateAccount.setAmount(new BigDecimal(total-amount));
        updateAccount.setFrozen(new BigDecimal(tccAccount.getFrozen().doubleValue()+amount));
        int size = demoTccAccountMapper.updateByPrimaryKeySelective(updateAccount);

        if (size != 1) {
            throw new IllegalStateException("ERROR!");
        }
        System.out.printf("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);

        // throw new IllegalStateException("error");
    }

    @CompensableConfirm
    @Transactional
    public void comfirmDecreaseAmount(String acctId, double amount) {
        DemoTccAccount tccAccount = demoTccAccountMapper.selectByPrimaryKey(acctId);

        System.out.println("Confirm处理....");

        //解冻金额
        DemoTccAccount updateAccount = new DemoTccAccount();
        updateAccount.setAcctId(acctId);
        updateAccount.setFrozen(new BigDecimal(tccAccount.getFrozen().intValue()-amount));
        int size = demoTccAccountMapper.updateByPrimaryKeySelective(updateAccount);

        if (size != 1) {
            throw new IllegalStateException("ERROR!");
        }
        System.out.printf("done decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }

    @CompensableCancel
    @Transactional
    public void cancleDecreaseAmount(String acctId, double amount) {
        DemoTccAccount tccAccount = demoTccAccountMapper.selectByPrimaryKey(acctId);
        double total = tccAccount.getAmount().doubleValue();
        System.out.println("Cancel处理:");

        //恢复冻结金额
        //冻结金额
        DemoTccAccount updateAccount = new DemoTccAccount();
        updateAccount.setAcctId(acctId);
        updateAccount.setAmount(new BigDecimal(total+amount));
        updateAccount.setFrozen(new BigDecimal(tccAccount.getFrozen().doubleValue()-amount));
        int size = demoTccAccountMapper.updateByPrimaryKeySelective(updateAccount);

        if (size != 1) {
            throw new IllegalStateException("ERROR!");
        }
        System.out.printf("undo decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }
}
