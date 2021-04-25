package io.github.forezp.context;

import cn.hutool.core.util.StrUtil;
import io.github.forezp.constant.MetadataConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

public class SWrContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Environment environment;

    private static ApplicationContext staticApplicationContext;
    private static Environment staticEnvironment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.environment = applicationContext.getEnvironment();

        staticApplicationContext = applicationContext;
        staticEnvironment = applicationContext.getEnvironment();
    }

    public Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public Object getBean(String name, Object... args) throws BeansException {
        return applicationContext.getBean(name, args);
    }

    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }

    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        return applicationContext.getBean(requiredType, args);
    }

    public boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isPrototype(name);
    }

    public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException {
        return applicationContext.isTypeMatch(name, typeToMatch);
    }

    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        return applicationContext.isTypeMatch(name, typeToMatch);
    }

    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public String[] getAliases(String name) {
        return applicationContext.getAliases(name);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public static ApplicationContext getStaticApplicationContext() {
        return staticApplicationContext;
    }

    public static Environment getStaticEnvironment() {
        return staticEnvironment;
    }


    public static String getAppName(Environment environment) {
        return environment.getProperty(MetadataConstants.SPRING_APPLICATION_NAME, String.class, "");
    }

    public static String getClusterId(Environment environment) {
        return environment.getProperty(MetadataConstants.SWR_CLUSTER_ID, String.class, "");
    }

    public static String getNamespaceId(Environment environment) {
        return environment.getProperty(MetadataConstants.SWR_NAMESPACE_ID, String.class, "");
    }

    public static String getAppId(Environment environment) {
        return environment.getProperty(MetadataConstants.SWR_APP_ID, String.class, "");
    }

    public static String getAppVersion(Environment environment) {
        return environment.getProperty(MetadataConstants.SWR_APP_VERSION, String.class, "");
    }

    public static String getServiceName(Environment environment) {
        return environment.getProperty(MetadataConstants.SWR_SERVICE_NAME, String.class, "");
    }

    public static String getInstanceId(Environment environment) {
        return environment.getProperty(MetadataConstants.SWR_INSTANCE_ID, String.class, "");
    }

    public static String getGroupId(Environment environment) {
        return environment.getProperty(MetadataConstants.SWR_GROUP_ID, String.class, "");
    }

    public static String getLocalIp(Environment environment) {


        InetUtils inetUtils = new InetUtils(new InetUtilsProperties());
        String localIp = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
        if (StrUtil.isNotBlank(localIp)) {
            return localIp;
        }
        return environment.getProperty(MetadataConstants.SWR_LOCAL_IP, String.class, "");
    }

    public static String getToken(Environment environment) {
        return environment.getProperty(MetadataConstants.TOKEN, String.class, "");
    }

    public static String getRegion(Environment environment) {
        return environment.getProperty(MetadataConstants.REGION, String.class, "");
    }

    public static String getZone(Environment environment) {
        return environment.getProperty(MetadataConstants.ZONE, String.class, "");
    }

    public static String getVersion(Environment environment) {
        return environment.getProperty(MetadataConstants.VERSION, String.class, "");
    }

    public static String getGroup(Environment environment) {
        return environment.getProperty(MetadataConstants.GROUP, String.class, "");
    }


}
