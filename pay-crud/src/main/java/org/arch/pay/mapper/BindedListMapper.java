package org.arch.pay.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.arch.framework.crud.CrudMapper;
import org.arch.pay.entity.BindedListEntity;

/**
 * 绑卡记录(BindedList) 表数据库 Mapper 层
 *
 * @author lait
 * @date 2021年6月12日 下午5:00:21
 * @since 1.0.0
 */
@Mapper
public interface BindedListMapper extends CrudMapper<BindedListEntity> {

}