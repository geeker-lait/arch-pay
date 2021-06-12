package org.arch.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.arch.framework.crud.CrudEntity;

import java.util.Date;

/**
 * @author lait
 * @description 支付请求记录
 * @date 2021年6月12日 下午5:00:21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName
public class DirectiveRequestListEntity extends CrudEntity<DirectiveRequestListEntity> {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 时间戳
     */
    private Date dt;
}
