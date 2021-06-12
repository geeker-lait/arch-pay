package org.arch.pay.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arch.framework.crud.CrudDao;
import org.arch.framework.crud.CrudServiceImpl;
import org.arch.pay.entity.ChannelBankEntity;
import org.arch.pay.mapper.ChannelBankMapper;
import org.springframework.stereotype.Repository;

/**
 * @author lait
 * @description 项目业务(ChannelBank) 表数据库访问层
 * @date 2021年6月12日 下午5:00:21
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class ChannelBankDao extends CrudServiceImpl<ChannelBankMapper, ChannelBankEntity> implements CrudDao<ChannelBankEntity> {
    private final ChannelBankMapper channelBankMapper;
}