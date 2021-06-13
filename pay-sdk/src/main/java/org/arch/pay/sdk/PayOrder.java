package org.arch.pay.sdk;

/**
 * 支付订单信息
 */
public interface PayOrder extends Attrs {


    /**
     * 添加订单信息
     *
     * @param key   key
     * @param value 值
     */
    void addAttr(String key, Object value);

}
