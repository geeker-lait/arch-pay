package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

import java.util.Date;

/**
 * @author lait
 * @description 通道主体
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class ChannelPrincipalEntity extends CrudEntity<ChannelPrincipalEntity> {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 通道ID
     */
    private String channelId;
    /**
     * 时间戳
     */
    private Date dt;
    /**
     * 主体的三方商户号即支付合作商家号，支付平台的账号等（卖家收款账号, 针对支付宝）
     */
    private String principalNo;
    /**
     * 证书对应的密码
     */
    private String storePassword;
    /**
     * 应用私钥(生成签名)
     */
    private String privateKey;
    /**
     * 状态
     */
    private String state;
    /**
     * 支付平台公钥(签名校验使用)，sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5(友店支付除外)的情况
     */
    private String publicKey;
    /**
     * 加密签名密钥
     */
    private String secretKey;
    /**
     * 账号类型（主体在三方开设的账户存在主账户和子账户，0：主，1：子）
     */
    private String accountTyp;
    /**
     * 通道名称
     */
    private String channelName;
    /**
     * 请求证书地址，请使用绝对路径
     */
    private String keyStorePath;
}
