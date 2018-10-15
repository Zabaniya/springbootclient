package org.ypq.springboothelloworld.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "eureka-provider")
public interface ProductService extends ProductAPI {

}
