package org.arch.pay.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arch.framework.crud.CrudDao;
import org.arch.framework.crud.CrudServiceImpl;
import org.arch.pay.entity.ChannelPrincipalEntity;
import org.arch.pay.mapper.ChannelPrincipalMapper;
import org.springframework.stereotype.Repository;

/**
 * @author lait
 * @description 项目业务(ChannelPrincipal) 表数据库访问层
 * @date 2021年6月12日 下午5:00:21
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class ChannelPrincipalDao extends CrudServiceImpl<ChannelPrincipalMapper, ChannelPrincipalEntity> implements CrudDao<ChannelPrincipalEntity> {
    private final ChannelPrincipalMapper channelPrincipalMapper;
}