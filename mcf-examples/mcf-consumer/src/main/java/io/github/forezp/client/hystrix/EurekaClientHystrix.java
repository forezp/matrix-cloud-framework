package io.github.forezp.client.hystrix;


import io.github.forezp.client.ProviderClient;
import org.springframework.stereotype.Component;

/**
 * Created by 36189 on 2019/1/14.
 */
@Component
public class EurekaClientHystrix implements ProviderClient{

    @Override
    public String sayHiFromClientEureka(String name) {
        return "进入Hystrix了";
    }
}
