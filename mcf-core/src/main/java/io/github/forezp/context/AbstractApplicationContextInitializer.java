package io.github.forezp.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;


public abstract class AbstractApplicationContextInitializer implements org.springframework.context.ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractApplicationContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (!(applicationContext instanceof AnnotationConfigApplicationContext)) {

            initializeDefaultProperties(applicationContext);
        }

        applicationContext.getBeanFactory().addBeanPostProcessor(new InstantiationAwareBeanPostProcessorAdapter() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

                return afterInitialization(applicationContext, bean, beanName);
            }
        });
    }

    private void initializeDefaultProperties(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        //设置自定义的变量，适配多种模式

//        try {
//            DiscoveryProperties properties = new DiscoveryProperties(path, DiscoveryConstant.ENCODING_GBK, DiscoveryConstant.ENCODING_UTF_8);
//            Map<String, String> propertiesMap = properties.getMap();
//            for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//                // 如果已经设置，则尊重已经设置的值
//                if (environment.getProperty(key) == null && System.getProperty(key) == null && System.getenv(key.toUpperCase()) == null) {
//                    System.setProperty(key, value);
//                }
//            }
//
//            LOG.info("{} is loaded...", path);
//        } catch (IOException e) {
//
//        }
    }



    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

    protected abstract Object afterInitialization(ConfigurableApplicationContext applicationContext, Object bean, String beanName) throws BeansException;
}