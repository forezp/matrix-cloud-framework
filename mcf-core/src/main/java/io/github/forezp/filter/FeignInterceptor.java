package io.github.forezp.filter;


import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.forezp.context.SwrContextHolder;
import io.github.forezp.context.SwrCoreContext;
import io.github.forezp.configure.Metadata;
import io.github.forezp.entity.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static io.github.forezp.constant.SwrContextConstants.SWR_META_DATA;
import static io.github.forezp.constant.SwrContextConstants.SWR_TAGS;

/**
 * Created by forezp on 2019/5/2.
 */
@Component
@ConditionalOnClass(FeignAutoConfiguration.class)
public class FeignInterceptor implements RequestInterceptor {

    Logger log = LoggerFactory.getLogger(FeignInterceptor.class);
    @Autowired
    Metadata metadata;

    public void apply(RequestTemplate requestTemplate) {
        log.debug("--- enter FeignInterceptor --- ");
        Map<String, Collection<String>> headers = requestTemplate.headers();
        if (headers != null) {
            Collection<String> collection = headers.get(SWR_TAGS);
            if (collection != null && collection.size() > 0) {
                Iterator it = collection.iterator();
                List<Tag> tags = buildTags((String) it.next());
                //tags.add(new Tag("channel", "feign"));
                requestTemplate.header(SWR_TAGS, JSON.toJSONString(tags));
            } else {

                SwrCoreContext swrContext = SwrContextHolder.get();
                List<Tag> tags = swrContext.getUpperTags();
                if (tags != null && tags.size() > 0) {
                    log.debug("tags :{}", tags.toString());
                    requestTemplate.header(SWR_TAGS, JSON.toJSONString(tags));
                }else {

                }

            }
            String metadataStr = JSON.toJSONString(metadata);
            requestTemplate.header(SWR_META_DATA, metadataStr);
        }
    }

    private List<Tag> buildTags(String tagJsonStr) {
        return JSON.parseArray(tagJsonStr, Tag.class);
    }

}
