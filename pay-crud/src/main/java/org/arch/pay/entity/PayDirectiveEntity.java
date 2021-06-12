package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

import java.util.Date;

/**
 * @author lait
 * @description 支付指令集
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class PayDirectiveEntity extends CrudEntity<PayDirectiveEntity> {
    /**
     * 定向URL
     */
    private String redirectUrl;
    /**
     * 描述
     */
    private String descr;
    /**
     * 通道ID
     */
    private String channelId;
    /**
     * 时间戳
     */
    private Date dt;
    /**
     * 指令名称
     */
    private String directiveName;
    /**
     * 指令参数
     */
    private String paramsJson;
    /**
     * 指令URI
     */
    private String directiveUri;
    /**
     * 支付类型(alipay|wxpay|...)
     */
    private String payTyp;
    /**
     * 异步指令回调URL
     */
    private String callbackUrl;
    /**
     * 字符集
     */
    private String charset;
    /**
     * 异步回调地址
     */
    private String notifyUrl;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 签名类型
     */
    private String signTyp;
    /**
     * 通道码
     */
    private String channelCode;
    /**
     * 指令码
     */
    private String directiveCode;
    /**
     * 同步回调地址
     */
    private String returnUrl;
}
