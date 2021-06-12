package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

import java.util.Date;

/**
 * @author lait
 * @description 支付配置
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class PayConfigEntity extends CrudEntity<PayConfigEntity> {
    /**
     * 描述
     */
    private String descr;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 属性键名 properties key
     */
    private String fkey;
    /**
     * 时间戳
     */
    private Date dt;
    /**
     * table name and id
     */
    private String tnaid;
    /**
     * 属性键值 properties val或配置key对应的json串
     */
    private String fval;
    /**
     * 命名空间
     */
    private String namespace;
    /**
     * 属性字段的类型
     */
    private String ftyp;
    /**
     * 父ID
     */
    private String pid;
    /**
     * 应用ID
     */
    private String appId;
}
