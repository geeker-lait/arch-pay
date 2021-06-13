package org.arch.pay.sdk.order;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.arch.pay.sdk.Bank;
import org.arch.pay.sdk.CountryCode;
import org.arch.pay.sdk.CurTyp;
import org.arch.pay.sdk.PayOrder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 转账订单
 */
@Data
public class TransferOrder implements PayOrder {

    /**
     * 转账批次订单单号
     */
    private String batchNo;

    /**
     * 转账订单单号
     */
    private String outNo;

    /**
     * 收款方账户, 用户openid,卡号等等
     */
    private String payeeAccount;

    /**
     * 转账金额
     */
    private BigDecimal amount;

    /**
     * 付款人名称
     */
    private String payerName;

    /**
     * 收款人名称
     */
    private String payeeName;
    /**
     * 收款人地址
     */
    private String payeeAddress;

    /**
     * 备注
     */
    private String remark;

    /**
     * 收款开户行
     */
    private Bank bank;

    /**
     * 收款开户行地址
     */
    private String payeeBankAddress;

    /**
     * 币种
     */
    private CurTyp curTyp;
    /**
     * 国家代码
     */
    private CountryCode countryCode;
    /**
     * 转账类型，收款方账户类型，比如支付宝账户或者银行卡
     */
    //private TransferType transferType;

    /**
     * 操作者ip，根据支付平台所需进行设置
     */
    private String ip;
    /**
     * 订单附加信息，可用于预设未提供的参数，这里会覆盖以上所有的订单信息，
     */
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Map<String, Object> attr;


    @Override
    public Map<String, Object> getAttrs() {
        if (null == attr) {
            attr = new HashMap<>();
        }
        return attr;
    }

    @Override
    public Object getAttr(String key) {
        return getAttrs().get(key);
    }

    /**
     * 添加订单信息
     *
     * @param key   key
     * @param value 值
     */
    @Override
    public void addAttr(String key, Object value) {
        getAttrs().put(key, value);
    }


}
