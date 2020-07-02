package io.github.forezp.discovery;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author fangzhipeng
 * create 2018-05-31
 **/
public class ClientDiscoveryImpl  {

    Logger LOG = LoggerFactory.getLogger( ClientDiscoveryImpl.class );

    private DiscoveryClient discoveryClient;
    ScheduledExecutorService scheduledExecutorService;



    public ClientDiscoveryImpl(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public void init() {
        refresh();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //刚启动每10s查询一次，之后每30s查询一次。
        scheduledExecutorService.schedule( new DiscoveryClientTask(), 10, TimeUnit.SECONDS );
        scheduledExecutorService.schedule( new DiscoveryClientTask(), 20, TimeUnit.SECONDS );
        scheduledExecutorService.scheduleAtFixedRate( new DiscoveryClientTask(), 30,
                10, TimeUnit.SECONDS );

    }

    public void refresh() {

        List<String> services = discoveryClient.getServices();
        if (!CollectionUtils.isEmpty( services )) {
            for (String serviceId : services) {
                List<ServiceInstance> serviceInstances = discoveryClient.getInstances( serviceId );
                for (ServiceInstance serviceInstance:serviceInstances){
                    Map<String, String> map= serviceInstance.getMetadata();
                    Set<Map.Entry<String,String>> set=map.entrySet();
                    for(Map.Entry<String,String> entry:set){
                       // LOG.info("key:{},value:{}",entry.getKey(),entry.getValue());
                    }
                }
            }
        }
    }


    public void destroy() {
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            scheduledExecutorService.shutdownNow();
        }
    }

    class DiscoveryClientTask implements Runnable {

        @Override
        public void run() {
            refresh();
        }
    }

}
