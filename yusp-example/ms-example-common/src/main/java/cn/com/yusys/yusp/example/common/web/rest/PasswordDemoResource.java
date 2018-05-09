package cn.com.yusys.yusp.example.common.web.rest;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.yusp.commons.util.BCRSAUtil;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
/**
 * @项目名称：example
 * @类名称：PasswordDemoResource
 * @类描述 ： 密码加密、解密、校验等示例
 * @功能描述:
 * @创建人：wangyf10@yusys.com.cn
 * @创建时间：2018-04-17 10:31
 * @修改备注：
 * @修改日期		修改人员		修改原因
 * --------    --------		----------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2018宇信科技-版权所有
 */
@RestController
@RequestMapping("/api/password")
public class PasswordDemoResource {
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	/**
	 * 生成密文 ,形如：$2a$10$NknbMmxNSQEh8VRIZVsQfOTp.MmRVskdc/A9G7Hsj5rVzpZDJkooe 
	 * @param password 明文密码，形如：admin
	 * @return 形如：$2a$10$NknbMmxNSQEh8VRIZVsQfOTp.MmRVskdc/A9G7Hsj5rVzpZDJkooe
	 */
	@PostMapping(value = "/BCrypt")
	public ResultDto<String> BCrypt(@RequestBody Map map){			
		String passwordBCrypt = passwordEncoder.encode((String)map.get("userPassword"));
		return new ResultDto(passwordBCrypt);
	}
	
	/**
	 * 校验密文与密文是否匹配
	 * @param passwordWithBCrypt  密文 ,形如：$2a$10$NknbMmxNSQEh8VRIZVsQfOTp.MmRVskdc/A9G7Hsj5rVzpZDJkooe 
	 * @param password 明文密码，形如：admin
	 * @return 是否匹配
	 */
	@PostMapping(value = "/BCryptCheck")
	public ResultDto<Boolean> BCryptCheck(@RequestBody Map map){		
		boolean isOk = passwordEncoder.matches((String)map.get("userPassword"),(String)map.get("userPasswordWithBCrypt"));
		return new ResultDto(isOk);
	}
	
	
	/**
	 * RSA加密
	 * @param password 明文
	 * @return 密文
	 * @throws Exception
	 */
	@PostMapping(value = "/jiami")
	public ResultDto<String> encryptByRSA(@RequestBody Map map) throws Exception {
		String passwordWithRSA = BCRSAUtil.encryptByRSA((String) map.get("password"));
		return new ResultDto(passwordWithRSA);
	}

	/**
	 * RSA解密
	 * @param passwordWithRSA  密文
	 * @return 明文
	 * @throws Exception
	 */
	@PostMapping(value = "/jiemi")
	public ResultDto<String> decrypt(@RequestBody Map map) throws Exception {
		String password = BCRSAUtil.decrypt((String) map.get("passwordWithRSA"));
		return new ResultDto(password);
	}
	
}
