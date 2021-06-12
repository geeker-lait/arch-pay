package org.arch.pay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arch.framework.crud.CrudService;
import org.arch.pay.dao.DirectiveResponseListDao;
import org.arch.pay.entity.DirectiveResponseListEntity;
import org.springframework.stereotype.Service;

/**
 * @author lait
 * @description 项目业务(DirectiveResponseList) 表服务层
 * @date 2021年6月12日 下午5:00:21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DirectiveResponseListService extends CrudService<DirectiveResponseListEntity, Long> {
    private final DirectiveResponseListDao directiveResponseListDao = (DirectiveResponseListDao) crudDao;
}
