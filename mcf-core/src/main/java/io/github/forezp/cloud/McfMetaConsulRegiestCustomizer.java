package io.github.forezp.cloud;

import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistrationCustomizer;

import java.util.ArrayList;
import java.util.List;

import static io.github.forezp.constant.McfConstant.MCF_REGIEST_DATA;


/**
 * Created by forezp on 2019/5/3.
 */
public class McfMetaConsulRegiestCustomizer implements ConsulRegistrationCustomizer {
//DiscoveryClientNameResolver
    //
    @Override
    public void customize(ConsulRegistration consulRegistration) {
        List<String> tags = consulRegistration.getService().getTags();
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(MCF_REGIEST_DATA+"="+MCF_REGIEST_DATA);
        consulRegistration.getService().setTags(tags);
    }
}
