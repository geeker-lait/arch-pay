package org.arch.pay.sdk;


import org.arch.pay.sdk.bean.PayRequest;
import org.arch.pay.sdk.enums.DirectiveCode;

public interface DirectiveRequest {
    DirectiveRequest setRequestId(String requestId);
    String getRequestId();


    DirectiveRequest setDirectiveCode(DirectiveCode directiveCode);
    DirectiveCode getDirectiveCode();



    DirectiveRequest setTransactionType(TransactionTyp transactionTyp);
    TransactionTyp getTransactionType();



    /**
     * 转换参数
     *
     * @param payRequest
     * @param payConfigurable
     * @return
     */
    DirectiveRequest convert(PayRequest payRequest, PayConfigurable payConfigurable);


    /**
     * 签名
     *
     * @param payConfigurable#prikey
     * @return
     */
    DirectiveRequest signature(PayConfigurable payConfigurable);

    /**
     * 加密
     *
     * @param #secretKey
     * @return
     */
    default DirectiveRequest encrypt(String secretKey) {
        return this;
    }

    /**
     * 解密
     *
     * @param secretKey
     * @return
     */
    default DirectiveRequest decrypt(String secretKey) {
        return this;
    }
}
