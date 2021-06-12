package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

/**
 * @author lait
 * @description 支付通道
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class PayChannelEntity extends CrudEntity<PayChannelEntity> {
    /**
     * 描述
     */
    private String descr;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 通道编号(全局唯一)
     */
    private String channelNo;
    /**
     * 是否测试
     */
    private String test;
    /**
     * 时间戳
     */
    private Date dt;
    /**
     * 通道码
     */
    private String channelCode;
    /**
     * 通道名称
     */
    private String channelName;
}
