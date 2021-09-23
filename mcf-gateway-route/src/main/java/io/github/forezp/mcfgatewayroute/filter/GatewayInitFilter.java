package io.github.forezp.mcfgatewayroute.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;


public interface GatewayInitFilter extends GlobalFilter, Ordered {
}
