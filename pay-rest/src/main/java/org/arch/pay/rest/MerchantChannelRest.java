package org.arch.pay.rest;

import org.arch.framework.crud.CrudRest;
import org.arch.pay.api.MerchantChannelRequest;
import org.arch.pay.api.MerchantChannelSearchDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lait
 * @description
 * @date 2021年6月12日 下午5:00:21
 */
@RestController
@RequestMapping("merchantChannel")
public interface MerchantChannelRest extends CrudRest<MerchantChannelRequest, Long, MerchantChannelSearchDto> {


}
