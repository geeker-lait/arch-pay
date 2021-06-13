package org.arch.pay.sdk.order;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.arch.framework.beans.utils.StringUtils;
import org.arch.pay.sdk.CurTyp;
import org.arch.pay.sdk.PayOrder;
import org.arch.pay.sdk.TransactionTyp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付订单信息
 */
@Data
public class PaymentOrder implements PayOrder {
    /**
     * 商品名称
     */
    private String subject;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 附加信息
     */
    private String addition;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 支付平台订单号,交易号
     */
    private String tradeNo;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 银行卡类型
     */
    private String bankType;
    /**
     * 设备信息
     */
    private String deviceInfo;
    /**
     * 支付创建ip
     */
    private String spbillCreateIp;
    /**
     * 付款条码串,人脸凭证，有关支付代码相关的，
     */
    private String authCode;
    /**
     * 微信专用，，，，
     * WAP支付链接
     */
    private String wapUrl;
    /**
     * 微信专用，，，，
     * WAP支付网页名称
     */

    private String wapName;
    /**
     * 用户唯一标识
     * 微信含 sub_openid 字段
     * 支付宝 buyer_id
     */
    private String openid;
    /**
     * 交易类型
     */
    private TransactionTyp transactionTyp;
    /**
     * 支付币种
     */
    private CurTyp curTyp;
    /**
     * 订单过期时间
     */
    private Date expirationTime;

    /**
     * 订单附加信息，可用于预设未提供的参数，这里会覆盖以上所有的订单信息，
     */
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private volatile Map<String, Object> attr;


    public PaymentOrder() {
    }


    public PaymentOrder(String subject, String body, BigDecimal price, String outTradeNo) {
        this(subject, body, price, outTradeNo, null);
    }

    public PaymentOrder(String subject, String body, BigDecimal price, String outTradeNo, TransactionTyp transactionTyp) {
        this.subject = StringUtils.trim(subject);
        this.body = StringUtils.trim(body);
        this.price = price;
        this.outTradeNo = StringUtils.trim(outTradeNo);
        this.transactionTyp = transactionTyp;
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
