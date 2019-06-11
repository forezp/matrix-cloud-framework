package io.github.forezp.web;

import io.github.forezp.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static io.github.forezp.constant.McfConstant.MCF_META_HEADER;

/**
 * Created by forezp on 2019/5/2.
 */

@RestController
public class HelloController {

    Logger logger= LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name",required = false,defaultValue = "forezp")String name) {
        Map<String, String> headers= HttpUtils.getHeaders(HttpUtils.getHttpServletRequest());
            logger.info(headers.get(MCF_META_HEADER));
        return "hi:"+name;
    }
}
