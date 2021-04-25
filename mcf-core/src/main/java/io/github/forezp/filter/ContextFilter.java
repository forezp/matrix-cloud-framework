package io.github.forezp.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.github.forezp.context.SwrContextHolder;
import io.github.forezp.context.SwrCoreContext;
import io.github.forezp.entity.Metadata;
import io.github.forezp.entity.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.github.forezp.constant.SwrConstants.SWR_META_DATA;
import static io.github.forezp.constant.SwrConstants.SWR_TAGS;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

public class ContextFilter extends OncePerRequestFilter {
    private Logger LOG = LoggerFactory.getLogger(ContextFilter.class);
    public static final int ORDER = HIGHEST_PRECEDENCE;

    @Autowired
    Metadata metadata;

    public ContextFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        SwrCoreContext swrCoreContext = SwrContextHolder.get();
        String tagJsonStr = request.getHeader(SWR_TAGS);
        String metadataStr = request.getHeader(SWR_META_DATA);

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
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException | IOException exception) {
            throw exception;
        } finally {
            SwrContextHolder.remove();
        }

    }

    private List<Tag> buildTags(String tagJsonStr) {
        return JSON.parseArray(tagJsonStr, Tag.class);
    }

    private Metadata buildMetadata(String metadataStr) {
        return JSON.parseObject(metadataStr, Metadata.class);

    }
}
