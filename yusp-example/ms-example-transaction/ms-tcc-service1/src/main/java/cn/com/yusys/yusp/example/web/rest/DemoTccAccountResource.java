/*
 * 代码生成器自动生成的
 * Since 2008 - 2018
 *
 */
package cn.com.yusys.yusp.example.web.rest;


import cn.com.yusys.yusp.example.service.IDemoTccAccountService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@Compensable(interfaceClass = IDemoTccAccountService.class, simplified = true)
public class DemoTccAccountResource {

    @Autowired
    private IDemoTccAccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/api/decrease", method = RequestMethod.POST)
    @Transactional
    public void decreaseAmount(@RequestParam("acctId") String acctId, @RequestParam("amount") double amount) {
        accountService.decreaseAmount(acctId, amount);
    }

}
