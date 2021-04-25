package io.github.forezp.adapter;

import com.netflix.loadbalancer.Server;
import io.github.forezp.constant.RouteConstant;
import io.github.forezp.entity.RouteEntity;
import io.github.forezp.discovery.PluginAdapter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;


/**
 * Created by forezp on 2019/6/16.
 */
public class DefaultRouteAdapter implements RouteAdapter {

    @Autowired
    PluginAdapter pluginAdapter;


    @Override
    public boolean apply(Server server, RouteEntity routeEntity) {
        //没有路由规则，直接成功。
        if (routeEntity == null) {
            return true;
        }

        boolean enabled = applyVersion(server, routeEntity);
        if (!enabled) {
            return false;
        }

        enabled = applyRegion(server, routeEntity);
        if (!enabled) {
            return false;
        }
        enabled = applyZone(server, routeEntity);
        if (!enabled) {
            return false;
        }
        enabled = applyGroupId(server, routeEntity);
        if (!enabled) {
            return false;
        }

        enabled = applyInstanceId(server, routeEntity);
        if (!enabled) {
            return false;
        }

        enabled = applyLocalIp(server, routeEntity);
        if (!enabled) {
            return false;
        }
        return enabled;
    }


    /**
     * 根据instanceId去路由服务
     *
     * @param server
     * @param routeRule
     * @return
     */
    private boolean applyInstanceId(Server server, RouteEntity routeRule) {
        String routeInstanceId = routeRule.getSwr_instance_id();
        if (StringUtils.isEmpty(routeInstanceId)) {
            return true;
        }
        String serverInstanceId = pluginAdapter.getInstanceId(server);

        if (StringUtils.isEmpty(serverInstanceId)) {
            return false;
        }
        List<String> routeInstanceIds = Arrays.asList(routeInstanceId.split(RouteConstant.SEPERATE_COMMA));
        if (routeInstanceIds.contains(serverInstanceId)) {
            return true;
        }
        return false;
    }

    /**
     * 根据localIp去路由服务
     *
     * @param server
     * @param routeRule
     * @return
     */
    private boolean applyLocalIp(Server server, RouteEntity routeRule) {
        String routeLocalIp = routeRule.getSwr_local_ip();
        if (StringUtils.isEmpty(routeLocalIp)) {
            return true;
        }
        String serverLocalIp = pluginAdapter.getLocalIp(server);

        if (StringUtils.isEmpty(serverLocalIp)) {
            return false;
        }
        List<String> routeGroups = Arrays.asList(routeLocalIp.split(RouteConstant.SEPERATE_COMMA));
        if (routeGroups.contains(serverLocalIp)) {
            return true;
        }
        return false;
    }


    /**
     * 根据部署组去路由服务
     *
     * @param server
     * @param routeRule
     * @return
     */
    private boolean applyGroupId(Server server, RouteEntity routeRule) {
        String routeGroup = routeRule.getSwr_group_id();
        if (StringUtils.isEmpty(routeGroup)) {
            return true;
        }
        String serverGroup = pluginAdapter.getGroup(server);
        if (StringUtils.isEmpty(serverGroup)) {
            serverGroup = pluginAdapter.getGroupId(server);
        }
        if (StringUtils.isEmpty(serverGroup)) {
            return false;
        }
        List<String> routeGroups = Arrays.asList(routeGroup.split(RouteConstant.SEPERATE_COMMA));
        if (routeGroups.contains(serverGroup)) {
            return true;
        }
        return false;
    }


    /**
     * 根据region去路由服务
     *
     * @param server
     * @param routeRule
     * @return
     */
    private boolean applyRegion(Server server, RouteEntity routeRule) {
        String routeRegion = routeRule.getRegion();
        if (StringUtils.isEmpty(routeRegion)) {
            return true;
        }
        String serverRegion = pluginAdapter.getRegion(server);
        if (StringUtils.isEmpty(serverRegion)) {
            return false;
        }
        List<String> routeRegions = Arrays.asList(routeRegion.split(RouteConstant.SEPERATE_COMMA));
        if (routeRegions.contains(serverRegion)) {
            return true;
        }
        return false;
    }

    /**
     * 根据zone去路由服务
     *
     * @param server
     * @param routeRule
     * @return
     */
    private boolean applyZone(Server server, RouteEntity routeRule) {
        String routeZone = routeRule.getZone();
        if (StringUtils.isEmpty(routeZone)) {
            return true;
        }
        String serverZone = pluginAdapter.getZone(server);
        if (StringUtils.isEmpty(serverZone)) {
            return false;
        }
        List<String> routeZones = Arrays.asList(routeZone.split(RouteConstant.SEPERATE_COMMA));
        if (routeZones.contains(serverZone)) {
            return true;
        }
        return false;
    }


    /**
     * 根据版本去路由服务
     *
     * @param server
     * @param routeEntity
     * @return
     */
    private boolean applyVersion(Server server, RouteEntity routeEntity) {

        String routeVersion = routeEntity.getSwr_app_version();
        if (StringUtils.isEmpty(routeVersion)) {
            return true;
        }
        String serverVersion = pluginAdapter.getVersion(server);
        if (StringUtils.isEmpty(serverVersion)) {
            serverVersion = pluginAdapter.getAppVersion(server);
        }
        if (StringUtils.isEmpty(serverVersion)) {
            return false;
        }
        List<String> routeVersions = Arrays.asList(routeVersion.split(RouteConstant.SEPERATE_COMMA));
        if (routeVersions.contains(serverVersion)) {
            return true;
        }
        return false;
    }


}
