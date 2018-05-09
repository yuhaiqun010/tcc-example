package cn.com.yusys.yusp.example.repository.mapper;


import cn.com.yusys.yusp.example.domain.DemoTccAccount;

/**
 * @项目名称: ms-tcc-service1
 * @类名称: DemoTccAccountMapper
 * @类描述: #Dao类
 * @功能描述: 
 * @创建人: yuhq
 * @创建时间: 2018-05-08 10:15:27
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2017宇信科技-版权所有
 */
public interface DemoTccAccountMapper {

    /**
     * @方法名称: selectByPrimaryKey
     * @方法描述: 根据主键查询
     * @参数与返回说明:
     * @算法描述: 无
     */
    DemoTccAccount selectByPrimaryKey(String sysId);

    /**
     * @方法名称: updateByPrimaryKeySelective
     * @方法描述: 根据主键更新 - 只更新非空字段
     * @参数与返回说明:
     * @算法描述: 无
     */
    int updateByPrimaryKeySelective(DemoTccAccount record);

}