package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

import java.util.Date;

/**
 * @author lait
 * @description 应用通道支持银行表
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class ChannelBankEntity extends CrudEntity<ChannelBankEntity> {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 通道ID
     */
    private String channelId;
    /**
     * 卡bin
     */
    private String cardBin;
    /**
     * 时间戳
     */
    private Date dt;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 通道码
     */
    private String channelCode;
    /**
     * 银行码
     */
    private String bankCode;
}
