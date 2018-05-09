package cn.com.yusys.yusp.example.common.web.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.com.yusys.yusp.commons.job.core.biz.model.ReturnT;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.commons.web.rest.exception.Message;
import cn.com.yusys.yusp.commons.web.rest.exception.MessageConstans;
import cn.com.yusys.yusp.commons.web.rest.exception.YuspException;
/**
 * @项目名称：example
 * @类名称：ExceptionDemoResource
 * @类描述 ： 异常返回示例
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
@RequestMapping("/api/exceptiondemo")
public class ExceptionDemoResource {

	@PostMapping(value = "/exception")
	public ResultDto<Integer> insert(){
		try {
			int i = 1/0;
		} catch (Exception e) {
			throw new YuspException(new Message(ReturnT.FAIL_CODE, "异常原因XXXXX", MessageConstans.MSG_LEVEL_ERROR));

		}
		return new ResultDto(0);
	}
	
}
