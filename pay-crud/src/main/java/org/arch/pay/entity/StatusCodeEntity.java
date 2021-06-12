package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

import java.util.Date;

/**
 * @author lait
 * @description 支付通道状态码
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class StatusCodeEntity extends CrudEntity<StatusCodeEntity> {
    /**
     * 通道状态码
     */
    private String channelStateCode;
    /**
     * 通过状态码描述
     */
    private String channelStateDescr;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 通道ID
     */
    private String channelId;
    /**
     * 统一状态码描述
     */
    private String stateDescr;
    /**
     * 时间戳
     */
    private Date dt;
    /**
     * 通道码
     */
    private String channelCode;
    /**
     * 统一状态码
     */
    private String stateCode;
}
