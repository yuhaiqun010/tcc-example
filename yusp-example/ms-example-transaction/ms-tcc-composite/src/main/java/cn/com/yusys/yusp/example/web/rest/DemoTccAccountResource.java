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

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Compensable(interfaceClass = IDemoTccAccountService.class, simplified = true)
public class DemoTccAccountResource {

    @Autowired
    private IDemoTccAccountService tccAccountService;

    /**
     * TCC测试
     * @param targetAcctId 目标账号
     * @param sourceAcctId1 转出账号
     * @param amount1 转账金额
     * @return code
     */
    @ResponseBody
    @Transactional
    @RequestMapping(value = "/api/tcc")
    public Map transfer(@RequestParam String targetAcctId, @RequestParam String sourceAcctId1,
                        @RequestParam double amount1) {
        tccAccountService.transfer(targetAcctId, sourceAcctId1, amount1);
        Map result = new LinkedHashMap();
        result.put("code", "0");
        return result;
    }

    /**
     * 重设测试数据
     * @return
     */
    public Map reset(){
        Map result = new LinkedHashMap();
        result.put("code", "0");
        return result;
    }
}
