package org.arch.pay.sdk.params;

import lombok.Data;
import org.arch.pay.sdk.PayParams;
import org.arch.pay.sdk.bean.PayHeader;

/**
 * @author lait.zhang@gmail.com
 * @description: 支付验证码请求
 * @weixin PN15855012581
 * @date 12/30/2020 9:13 PM
 */
@Data
public class PrePayingParams implements PayParams {

    // 支付请求头
    private PayHeader payHeader;

    // 金额
    private String amount;
}
