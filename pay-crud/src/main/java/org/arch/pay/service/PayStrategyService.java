package org.arch.pay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arch.framework.crud.CrudService;
import org.arch.pay.dao.PayStrategyDao;
import org.arch.pay.entity.PayStrategyEntity;
import org.springframework.stereotype.Service;

/**
 * @author lait
 * @description 项目业务(PayStrategy) 表服务层
 * @date 2021年6月12日 下午5:00:21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PayStrategyService extends CrudService<PayStrategyEntity, Long> {
    private final PayStrategyDao payStrategyDao = (PayStrategyDao) crudDao;
}
