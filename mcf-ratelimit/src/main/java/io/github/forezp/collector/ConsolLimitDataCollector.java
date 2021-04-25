package io.github.forezp.collector;


import cn.hutool.core.date.DateUtil;
import io.github.forezp.entity.LimitCollectData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;
import java.util.Set;


/**
 * Created by forezp on 2019/5/1.
 */

public class ConsolLimitDataCollector extends AbstrctLimitDataCollector {

    Logger logger = LoggerFactory.getLogger(ConsolLimitDataCollector.class);

    @Override
    public void reportData() {
        Set<Map.Entry<String, LimitCollectData>> set = collectDataMap.entrySet();
        for (Map.Entry<String, LimitCollectData> entry : set) {
            LimitCollectData limitCollectData = entry.getValue();
            logger.info(DateUtil.formatDate(new Date()) + limitCollectData.toString());
            limitCollectData.reset();
        }
    }
}
