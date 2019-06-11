//package io.github.forezp.test;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * rate.limit.enable: true
// * rate.limit.global.num: 100
// * rate.limit.global.second: 1
// * rate.limit.services:
// *       -name: mcf-provider
// *       -num: 100
// *       -second
// * Created by forezp on 2019/5/4.
// */
//@Component
//@ConfigurationProperties(prefix = "rate.limit")
//public class TestProperties {
//
//    String enable;
//    GlobalLimitRule global;
//    List<LimitRule> services;
//
//    public String getEnable() {
//        return enable;
//    }
//
//    public void setEnable(String enable) {
//        this.enable = enable;
//    }
//
//    public GlobalLimitRule getGlobal() {
//        return global;
//    }
//
//    public void setGlobal(GlobalLimitRule global) {
//        this.global = global;
//    }
//
//    public List<LimitRule> getServices() {
//        return services;
//    }
//
//    public void setServices(List<LimitRule> services) {
//        this.services = services;
//    }
//}
