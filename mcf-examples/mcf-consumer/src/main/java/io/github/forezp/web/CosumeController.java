package io.github.forezp.web;

import io.github.forezp.client.ProviderClient;
import io.github.forezp.test.LimitRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by forezp on 2019/5/2.
 */
@RestController
public class CosumeController {

    @Autowired
    ProviderClient providerClient;

//    @Autowired
//    TestProperties testProperties;

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name", required = false) String name) {
        return providerClient.sayHiFromClientEureka(name);
    }

    @GetMapping("/test")
    public String test() {
//        System.out.println(testProperties.getEnable());
//        if(testProperties.getGlobal()!=null) {
//            System.out.println(testProperties.getGlobal().toString());
//        }
//        if(testProperties.getServices()!=null&&testProperties.getServices().size()>0){
//            for(LimitRule limitRule:testProperties.getServices()){
//                System.out.println(limitRule.toString());
//            }
//        }
        return "test";

    }
}
