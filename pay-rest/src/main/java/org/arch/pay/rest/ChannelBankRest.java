package org.arch.pay.rest;

import org.arch.framework.crud.CrudRest;
import org.arch.pay.api.ChannelBankRequest;
import org.arch.pay.api.ChannelBankSearchDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @description 
*
* @author lait
* @date 2021年6月12日 下午5:00:21
*/
@RestController
@RequestMapping("channelBank")
public interface ChannelBankRest extends CrudRest<ChannelBankRequest, Long, ChannelBankSearchDto> {


}
