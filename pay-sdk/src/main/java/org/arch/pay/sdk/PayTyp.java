package org.arch.pay.sdk;

/**
 * 基础的支付类型
 */
public interface PayTyp {


    /**
     * 根据支付类型获取交易类型
     *
     * @param transactionType 类型值
     * @return 交易类型
     */
    TransactionTyp getTransactionType(String transactionType);

}
