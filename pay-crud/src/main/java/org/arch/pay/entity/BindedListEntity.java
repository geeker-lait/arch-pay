package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

import java.util.Date;

/**
 * @author lait
 * @description 绑卡记录
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class BindedListEntity extends CrudEntity<BindedListEntity> {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 通道ID
     */
    private String channelId;
    /**
     * 应用id
     */
    private String appId;
    /**
     * 身份证号
     */
    private String idcard;
    /**
     * 预留手机号
     */
    private String phone;
    /**
     * 账号ID
     */
    private String accountId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 协议id
     */
    private String protocolId;
    /**
     * 银行卡号
     */
    private String bankcard;
    /**
     * 银行简码
     */
    private String bankCode;
    /**
     * 时间戳
     */
    private Date dt;
}
