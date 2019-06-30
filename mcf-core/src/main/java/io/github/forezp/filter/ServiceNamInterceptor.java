package io.github.forezp.filter;

import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.forezp.entity.McfMetaData;
import io.github.forezp.entity.SvcInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static io.github.forezp.constant.McfConstant.MCF_META_HEADER;

/**
 * Created by forezp on 2019/5/2.
 */
@Component
public class ServiceNamInterceptor implements RequestInterceptor {


    @Value("${spring.application.name:}")
    String serviceName;

    public void apply(RequestTemplate requestTemplate) {
        Map<String, Collection<String>> headers=requestTemplate.headers();
//        requestTemplate.path();
//        requestTemplate.request().
        if(headers!=null&&!StringUtils.isEmpty(serviceName)){
            Collection<String> collection=headers.get(MCF_META_HEADER) ;
            if(collection!=null&&collection.size()>0){
                Iterator it=  collection.iterator();
                McfMetaData metaData= JSON.parseObject((String) it.next(),McfMetaData.class);
                SvcInstance svcInstance=new SvcInstance();
                svcInstance.setSvcName(serviceName);
                metaData.setSvcSource(svcInstance);
                requestTemplate.header(MCF_META_HEADER,JSON.toJSONString(metaData));
            }else {
                McfMetaData metaData=new McfMetaData();
                SvcInstance svcInstance=new SvcInstance();
                svcInstance.setSvcName(serviceName);
                metaData.setSvcSource(svcInstance);
                requestTemplate.header(MCF_META_HEADER,JSON.toJSONString(metaData));
            }
        }
    }
}
