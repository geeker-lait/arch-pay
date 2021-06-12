package org.arch.pay.rest;

import org.arch.framework.crud.CrudRest;
import org.arch.pay.api.PayDirectiveRequest;
import org.arch.pay.api.PayDirectiveSearchDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lait
 * @description
 * @date 2021年6月12日 下午5:00:21
 */
@RestController
@RequestMapping("payDirective")
public interface PayDirectiveRest extends CrudRest<PayDirectiveRequest, Long, PayDirectiveSearchDto> {


}
