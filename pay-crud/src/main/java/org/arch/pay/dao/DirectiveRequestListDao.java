package org.arch.pay.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arch.framework.crud.CrudDao;
import org.arch.framework.crud.CrudServiceImpl;
import org.arch.pay.entity.DirectiveRequestListEntity;
import org.arch.pay.mapper.DirectiveRequestListMapper;
import org.springframework.stereotype.Repository;

/**
 * @author lait
 * @description 项目业务(DirectiveRequestList) 表数据库访问层
 * @date 2021年6月12日 下午5:00:21
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class DirectiveRequestListDao extends CrudServiceImpl<DirectiveRequestListMapper, DirectiveRequestListEntity> implements CrudDao<DirectiveRequestListEntity> {
    private final DirectiveRequestListMapper directiveRequestListMapper;
}