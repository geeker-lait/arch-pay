package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

import java.util.Date;

/**
 * @author lait
 * @description 支付策略
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class PayStrategyEntity extends CrudEntity<PayStrategyEntity> {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 应用id
     */
    private String appId;
    /**
     * 时间戳
     */
    private Date dt;
    /**
     * 规则ID,这里后期借助规则引擎来实现，预留规则id,前期先简单静态，根据权重或优先级
     */
    private String ruleId;
    /**
     * 应用码
     */
    private String appCode;
    /**
     * 商户号
     */
    private String merchantNo;
}
