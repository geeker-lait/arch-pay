package org.arch.pay.sdk.order;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.arch.pay.sdk.CurTyp;
import org.arch.pay.sdk.PayOrder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 退款订单信息
 */
@Data
public class RefundOrder implements PayOrder {
    /**
     * 退款单号，每次进行退款的单号，此处唯一
     */
    private String refundNo;
    /**
     * 支付平台订单号,交易号
     */
    private String tradeNo;
    /**
     * 商户单号
     */
    private String outTradeNo;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 退款交易日期
     */
    private Date orderDate;

    /**
     * 货币
     */
    private CurTyp curTyp;
    /**
     * 退款说明
     */
    private String description;
    /**
     * 退款用户
     */
    private String userId;

    /**
     * 订单附加信息，可用于预设未提供的参数，这里会覆盖以上所有的订单信息，
     */
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Map<String, Object> attr;

    public RefundOrder() {
    }

    public RefundOrder(String refundNo, String tradeNo, BigDecimal refundAmount) {
        this.refundNo = refundNo;
        this.tradeNo = tradeNo;
        this.refundAmount = refundAmount;
    }

    public RefundOrder(String tradeNo, String outTradeNo, BigDecimal refundAmount, BigDecimal totalAmount) {
        this.tradeNo = tradeNo;
        this.outTradeNo = outTradeNo;
        this.refundAmount = refundAmount;
        this.totalAmount = totalAmount;
    }

    public RefundOrder(String refundNo, String tradeNo, String outTradeNo, BigDecimal refundAmount, BigDecimal totalAmount) {
        this.refundNo = refundNo;
        this.tradeNo = tradeNo;
        this.outTradeNo = outTradeNo;
        this.refundAmount = refundAmount;
        this.totalAmount = totalAmount;
    }


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
