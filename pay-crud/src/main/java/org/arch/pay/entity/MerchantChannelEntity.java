package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

import java.util.Date;

/**
 * @author lait
 * @description 商户通道
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class MerchantChannelEntity extends CrudEntity<MerchantChannelEntity> {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 应用id
     */
    private String appId;
    /**
     * 平台商户号
     */
    private String merchantNo;
    /**
     * 主体ID
     */
    private String principalId;
    /**
     * 时间戳
     */
    private Date dt;
    /**
     * 状态
     */
    private String state;
}
