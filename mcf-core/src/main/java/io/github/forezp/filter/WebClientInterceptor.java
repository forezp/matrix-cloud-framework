package io.github.forezp.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.github.forezp.context.SwrContextHolder;
import io.github.forezp.context.SwrCoreContext;
import io.github.forezp.configure.Metadata;
import io.github.forezp.entity.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static io.github.forezp.constant.SwrContextConstants.SWR_META_DATA;
import static io.github.forezp.constant.SwrContextConstants.SWR_TAGS;

public class WebClientInterceptor implements WebFilter, Ordered {

    private Logger LOG = LoggerFactory.getLogger(WebClientInterceptor.class);

    public static final int ORDER = HIGHEST_PRECEDENCE;

    @Autowired
    Metadata metadata;

    private List<Tag> buildTags(String tagJsonStr) {
        return JSON.parseArray(tagJsonStr, Tag.class);
    }

    private Metadata buildMetadata(String metadataStr) {
        return JSON.parseObject(metadataStr, Metadata.class);

    }

    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        SwrCoreContext swrCoreContext = SwrContextHolder.get();
        ServerHttpRequest request = serverWebExchange.getRequest();

        HttpHeaders headers = request.getHeaders();
        if (!CollectionUtils.isEmpty(headers)) {
            String tagJsonStr = headers.getFirst(SWR_TAGS);
            String metadataStr = headers.getFirst(SWR_META_DATA);
            if (StrUtil.isNotBlank(tagJsonStr)) {
                swrCoreContext.setUpperTags(buildTags(tagJsonStr));
            } else {
                List<Tag> tags = new ArrayList<>();
                Tag tag = new Tag("nd-version", "1.2");
                tags.add(tag);
                swrCoreContext.setUpperTags(tags);
            }
            if (StrUtil.isNotBlank(metadataStr)) {
                swrCoreContext.setUpperMetadata(buildMetadata(metadataStr));
            }
            if (metadata != null) {
                swrCoreContext.setMetadata(metadata);
            }
        }
        return webFilterChain.filter(serverWebExchange);

    }
}
