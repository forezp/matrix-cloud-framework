package io.github.forezp.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by forezp on 2019/6/8.
 */
@Configuration
public class DataCollectProperties {


    @Value("${limit.data.collect.period:60000}")
    Integer collectPeriod;

    public Integer getCollectPeriod() {
        return collectPeriod;
    }


}
