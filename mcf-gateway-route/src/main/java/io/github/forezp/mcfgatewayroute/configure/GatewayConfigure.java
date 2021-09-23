package io.github.forezp.mcfgatewayroute.configure;

import io.github.forezp.mcfgatewayroute.filter.DefaultGatewayClearFilter;
import io.github.forezp.mcfgatewayroute.filter.DefaultGatewayInitFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfigure {


    @Bean
    public DefaultGatewayClearFilter defaultGatewayClearFilter() {
        return new DefaultGatewayClearFilter();
    }

    @Bean
    public DefaultGatewayInitFilter defaultGatewayInitFilter() {
        return new DefaultGatewayInitFilter();
    }
}
