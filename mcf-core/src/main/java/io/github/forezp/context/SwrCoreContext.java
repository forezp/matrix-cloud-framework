package io.github.forezp.context;

import io.github.forezp.configure.Metadata;
import io.github.forezp.entity.Tag;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.github.forezp.constant.SwrContextConstants.*;

public class SwrCoreContext {


    private Map<String, Object> context = new HashedMap();

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public ServerWebExchange getServerWebExchange() {
        Object exchange = this.context.get(GATEWAY_CONTEXT_EXANGE);
        return (ServerWebExchange) exchange;
    }

    public void setServerWebExchange(ServerWebExchange exchange) {
        this.context.put(SWR_TAGS, exchange);
    }


    public List<Tag> getTags() {
        Object tags = this.context.get(SWR_TAGS);
        return (List) (null == tags ? new ArrayList() : (List) tags);
    }

    public void setTags(List<Tag> tags) {
        this.context.put(SWR_TAGS, tags);
    }


    public Metadata getMetadata() {
        return (Metadata) this.context.get(SWR_META_DATA);
    }

    public void setMetadata(Metadata metadata) {
        this.context.put(SWR_META_DATA, metadata);
    }

    public List<Tag> getUpperTags() {
        Object tags = this.context.get(SWR_UPPER_TAGS);
        return (List) (null == tags ? new ArrayList() : (List) tags);
    }

    public void setUpperTags(List<Tag> tags) {
        this.context.put(SWR_UPPER_TAGS, tags);
    }


    public Metadata getUpperMetadata() {
        return (Metadata) this.context.get(SWR_UPPER_META_DATA);
    }

    public void setUpperMetadata(Metadata metadata) {
        this.context.put(SWR_UPPER_META_DATA, metadata);
    }

}
