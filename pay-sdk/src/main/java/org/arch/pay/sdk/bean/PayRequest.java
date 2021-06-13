package org.arch.pay.sdk.bean;

import lombok.Getter;
import lombok.ToString;
import org.arch.framework.beans.utils.HttpRequestUtils;
import org.arch.pay.sdk.PayParams;
import org.arch.pay.sdk.enums.DirectiveCode;
import org.arch.pay.sdk.enums.PayType;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付请求
 */
@Getter
@ToString
public class PayRequest {
    // 指令码
    private DirectiveCode directiveCode;
    // 支付类型(微信/支付宝/第三方)
    private PayType payType;


    // 设备号
    private String deviceId;
    // ip地址
    private String ip;

    // 实体用户对应的-账号ID
    private String accountId;
    // 实体用户对应的-用户ID
    private String userId;
    // 当前账号对应的支付订单号
    private String orderNo;

    // 支付流水号
    private String paySn;
    // 支付金额
    private String amount;

    // 具体支付参数
    private PayParams payParams;

    private PayRequest() {

    }


    public static PayRequest of(DirectiveCode directiveCode) {
        PayRequest payRequest = new PayRequest();
        payRequest.directiveCode = directiveCode;
        return payRequest;
    }


    public PayRequest init(HttpServletRequest httpServletRequest) {
        this.ip = HttpRequestUtils.getIpAddress(httpServletRequest);
        this.deviceId = HttpRequestUtils.getDeviceId(httpServletRequest);
        this.accountId = "";
        this.paySn = "";
        this.amount = "";
        this.userId = "";
        this.orderNo = "";

        String payType = httpServletRequest.getParameter("payType");
        if (PayType.THIRD_PAY.toString().equalsIgnoreCase(payType)) {
            this.payType = PayType.THIRD_PAY;
        } else if (PayType.ALI_PAY.toString().equalsIgnoreCase(payType)) {
            this.payType = PayType.ALI_PAY;
        } else if (PayType.WX_PAY.toString().equalsIgnoreCase(payType)) {
            this.payType = PayType.WX_PAY;
        }
        return this;
    }

    /**
     * 创建map请求参数
     *
     * @param payParams
     * @return
     */
    public PayRequest setPayParams(PayParams payParams) {
        this.payParams = payParams;
        return this;
    }
}
