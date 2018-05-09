package cn.com.yusys.yusp.example.common.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.api.client.sequence.SequenceTemplateService;
import cn.com.yusys.yusp.commons.mapper.QueryModel;
import cn.com.yusys.yusp.commons.web.rest.dto.ResultDto;
import cn.com.yusys.yusp.example.common.domain.SequenceConfig;
import cn.com.yusys.yusp.example.common.repository.mapper.SequenceConfigMapper;
/**
 * @项目名称：example
 * @类名称：SequenceDemoResource
 * @类描述 ： 微服务开发示例：序列号微服务客户端调用示例
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
@RequestMapping("/api/sequence")
public class SequenceDemoResource {
	
	@Autowired
	private SequenceTemplateService sequenceTemplateService;
	
	@Autowired
	private SequenceConfigMapper sequenceConfigMapper;

	/**
	 * 获取所有序列号配置
	 * @return
	 */
	@GetMapping(value = "/getAllSeqId")
    public  ResultDto<List<SequenceConfig>> getAllSeqId() {
		
		QueryModel queryModel = new QueryModel();
		queryModel.setSize(0);
    	ResultDto<List<SequenceConfig>> resultDto = new ResultDto<List<SequenceConfig>>(sequenceConfigMapper.selectByModel(queryModel));
    	resultDto.setCode(0);  	
    	resultDto.setMessage("获取所有序列号配置成功");       
        return resultDto;
    }

	
	
	/**
	 * 生成序列号
	 * @param fileIds
	 * @return
	 */
	@GetMapping(value = "/generate")
    public  ResultDto<String> generate(String seqId) {
    	ResultDto<String> resultDto = new ResultDto<String>(sequenceTemplateService.getSequenceTemplate(seqId, new HashMap<String,String>()));
    	resultDto.setCode(0);  	
    	resultDto.setMessage("序列号生成成功");       
        return resultDto;
    }

}
