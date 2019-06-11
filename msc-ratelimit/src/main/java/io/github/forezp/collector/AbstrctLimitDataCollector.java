package io.github.forezp.collector;


import io.github.forezp.config.DataCollectProperties;
import io.github.forezp.entity.LimitCollectData;
import io.github.forezp.entity.LimitResult;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by forezp on 2019/5/1.
 *
 */
public abstract class AbstrctLimitDataCollector implements LimitDataCollector,InitializingBean,DisposableBean{

    @Autowired
    DataCollectProperties dataCollectProperties;

    protected ConcurrentHashMap<String,LimitCollectData> collectDataMap=new ConcurrentHashMap<>();
    ScheduledExecutorService scheduledExecutorService;

    @Override
    public void collect(LimitResult result) {
        LimitCollectData limitCollectData=collectDataMap.get(result.getName());
        if(limitCollectData==null){
            limitCollectData=new LimitCollectData();
            collectDataMap.put(result.getName(),limitCollectData);

        }
        switch (result.getResultType()){
            case FAIL:
                limitCollectData.refuseIncrement();
                break;
            case SUCCESS:
                limitCollectData.accessIncrement();
                break;
        }

    }


    @Override
    public void destroy()throws Exception {
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            scheduledExecutorService.shutdownNow();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate( new ReportTask(), 5000,
                dataCollectProperties.getCollectPeriod() , TimeUnit.MILLISECONDS);
    }


    class ReportTask implements Runnable{

        @Override
        public void run() {
            reportData();
        }
    }

}
