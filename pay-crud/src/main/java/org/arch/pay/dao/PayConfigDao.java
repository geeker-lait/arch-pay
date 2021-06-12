package org.arch.pay.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arch.framework.crud.CrudDao;
import org.arch.framework.crud.CrudServiceImpl;
import org.arch.pay.entity.PayConfigEntity;
import org.arch.pay.mapper.PayConfigMapper;
import org.springframework.stereotype.Repository;

/**
 * @author lait
 * @description 项目业务(PayConfig) 表数据库访问层
 * @date 2021年6月12日 下午5:00:21
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class PayConfigDao extends CrudServiceImpl<PayConfigMapper, PayConfigEntity> implements CrudDao<PayConfigEntity> {
    private final PayConfigMapper payConfigMapper;
}