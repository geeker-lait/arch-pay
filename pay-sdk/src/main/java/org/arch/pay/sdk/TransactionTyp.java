package org.arch.pay.sdk;

/**
 * 交易类型
 *
 */
public interface TransactionTyp {
    /**
     * 获取交易类型
     * @return 交易类型
     */
     String getType();

    /**
     * 获取接口
     * @return 接口
     */
     String getMethod();
}

