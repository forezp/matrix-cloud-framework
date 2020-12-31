package io.github.forezp.web;

//import io.github.forezp.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//import static io.github.forezp.constant.McfConstant.MCF_META_HEADER;

/**
 * Created by forezp on 2019/5/2.
 */

@RestController
@RefreshScope
public class HelloController {

    Logger logger= LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name",required = false,defaultValue = "forezp")String name) {
//        Map<String, String> headers= HttpUtils.getHeaders(HttpUtils.getHttpServletRequest());
//            logger.info(headers.get(MCF_META_HEADER));
        return "hi:"+name;
    }

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }
}
