package io.github.forezp.client;

import io.github.forezp.client.hystrix.EurekaClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "provider",fallback = EurekaClientHystrix.class)
public interface ProviderClient {


    @GetMapping(value = "/hi")
    String sayHiFromClientEureka(@RequestParam(value = "name") String name);
}
