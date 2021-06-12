package org.arch.pay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arch.framework.crud.CrudService;
import org.arch.pay.dao.PayDirectiveDao;
import org.arch.pay.entity.PayDirectiveEntity;
import org.springframework.stereotype.Service;

/**
 * @author lait
 * @description 项目业务(PayDirective) 表服务层
 * @date 2021年6月12日 下午5:00:21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PayDirectiveService extends CrudService<PayDirectiveEntity, Long> {
    private final PayDirectiveDao payDirectiveDao = (PayDirectiveDao) crudDao;
}
