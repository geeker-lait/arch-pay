package org.arch.pay.rest;

import org.arch.framework.crud.CrudRest;
import org.arch.pay.api.PayChannelRequest;
import org.arch.pay.api.PayChannelSearchDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lait
 * @description
 * @date 2021年6月12日 下午5:00:21
 */
@RestController
@RequestMapping("payChannel")
public interface PayChannelRest extends CrudRest<PayChannelRequest, Long, PayChannelSearchDto> {


}
