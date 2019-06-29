package io.github.forezp.adapter;

import com.netflix.loadbalancer.Server;
import io.github.forezp.constant.RouteConstant;
import io.github.forezp.entity.RouteRule;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by forezp on 2019/6/16.
 */
public class DefaultRouteAdapter implements RouteAdapter {

    @Autowired
    PluginAdapter pluginAdapter;


    @Override
    public boolean apply(Server server, RouteRule routeRule) {

        if (routeRule == null) {
            return true;
        }
        boolean enabled = applyVersion(server, routeRule);
        if (!enabled) {
            return false;
        }
        enabled = applyRegion(server, routeRule);
        if (!enabled) {
            return false;
        }

//        enabled = applyAddress(server);
//        if (!enabled) {
//            return false;
//        }
        return enabled;
//
//        return applyStrategy(server);
    }

    private boolean applyRegion(Server server, RouteRule routeRule) {
        String routeRegion = routeRule.getRegion();
        if (StringUtils.isEmpty(routeRegion)) {
            return true;
        }
        String serverRegion = getRegion(server);
        if (StringUtils.isEmpty(serverRegion)) {
            return false;
        }

        List<String> routeRegions = Arrays.asList(routeRegion.split(RouteConstant.SEPERATE_COMMA));
        if (routeRegions.contains(serverRegion)) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean applyVersion(Server server, RouteRule routeRule) {

        String routeVersion = routeRule.getVersion();
        if (StringUtils.isEmpty(routeVersion)) {
            return true;
        }
        String serverVersion = getVersionValue(server);
        if (StringUtils.isEmpty(serverVersion)) {
            return false;
        }
        List<String> routeVersions = Arrays.asList(routeVersion.split(RouteConstant.SEPERATE_COMMA));
        if (routeVersions.contains(serverVersion)) {
            return true;
        }
        return false;
    }

    private String getVersionValue(Server server) {
        Map<String, String> meteData = pluginAdapter.getMetaData(server);
        return meteData.get(RouteConstant.VERSION);
    }

    private String getRegion(Server server) {
        Map<String, String> meteData = pluginAdapter.getMetaData(server);
        return meteData.get(RouteConstant.REGION);
    }

    private String getHost(Server server) {
        Map<String, String> meteData = pluginAdapter.getMetaData(server);
        return meteData.get(RouteConstant.GROUP);
    }
//    @SuppressWarnings("unchecked")
//    private boolean applyRegion(Server server) {
//        String regionValue = getRegionValue(server);
//        if (StringUtils.isEmpty(regionValue)) {
//            RuleEntity ruleEntity = pluginAdapter.getRule();
//            if (ruleEntity != null) {
//                StrategyEntity strategyEntity = ruleEntity.getStrategyEntity();
//                if (strategyEntity != null) {
//                    regionValue = strategyEntity.getRegionValue();
//                }
//            }
//        }
//
//        if (StringUtils.isEmpty(regionValue)) {
//            return true;
//        }
//
//        Map<String, String> metadata = pluginAdapter.getServerMetadata(server);
//        String region = metadata.get(DiscoveryConstant.REGION);
//        if (StringUtils.isEmpty(region)) {
//            return false;
//        }
//
//        String regions = null;
//        try {
//            Map<String, String> regionMap = JsonUtil.fromJson(regionValue, Map.class);
//            String serviceId = pluginAdapter.getServerServiceId(server);
//            regions = regionMap.get(serviceId);
//        } catch (Exception e) {
//            regions = regionValue;
//        }
//
//        if (StringUtils.isEmpty(regions)) {
//            return true;
//        }
//
//        // 如果精确匹配不满足，尝试用通配符匹配
//        List<String> regionList = StringUtil.splitToList(regions, DiscoveryConstant.SEPARATE);
//        if (regionList.contains(region)) {
//            return true;
//        }
//
//        // 通配符匹配。前者是通配表达式，后者是具体值
//        for (String regionPattern : regionList) {
//            if (discoveryMatcherStrategy.match(regionPattern, region)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    @SuppressWarnings("unchecked")
//    private boolean applyAddress(Server server) {
//        String addressValue = getAddressValue(server);
//        if (StringUtils.isEmpty(addressValue)) {
//            RuleEntity ruleEntity = pluginAdapter.getRule();
//            if (ruleEntity != null) {
//                StrategyEntity strategyEntity = ruleEntity.getStrategyEntity();
//                if (strategyEntity != null) {
//                    addressValue = strategyEntity.getAddressValue();
//                }
//            }
//        }
//
//        if (StringUtils.isEmpty(addressValue)) {
//            return true;
//        }
//
//        Map<String, String> addressMap = JsonUtil.fromJson(addressValue, Map.class);
//        String serviceId = pluginAdapter.getServerServiceId(server);
//        String addresses = addressMap.get(serviceId);
//        if (StringUtils.isEmpty(addresses)) {
//            return true;
//        }
//
//        // 如果精确匹配不满足，尝试用通配符匹配
//        List<String> addressList = StringUtil.splitToList(addresses, DiscoveryConstant.SEPARATE);
//        if (addressList.contains(server.getHostPort()) || addressList.contains(server.getHost())) {
//            return true;
//        }
//
//        // 通配符匹配。前者是通配表达式，后者是具体值
//        for (String addressPattern : addressList) {
//            if (discoveryMatcherStrategy.match(addressPattern, server.getHostPort()) || discoveryMatcherStrategy.match(addressPattern, server.getHost())) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private boolean applyStrategy(Server server) {
//        if (discoveryEnabledStrategy == null) {
//            return true;
//        }
//
//        return discoveryEnabledStrategy.apply(server);
//
//    }
}
