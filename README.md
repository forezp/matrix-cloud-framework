# matrix微服务治理框架

## 支持的能力

matrix微服务治理平台旨在为客户提供微服务治理的基本能力，目前支持的功能包括一下：

- 服务路由，可以实现灰度发布
- 服务限流，可以实现熔断
- 服务鉴权，指定某些服务可以调用

## 支持的版本：

- Spring Cloud: Hoxton
- Spring Boot版本： Hoxton

## 适配的框架

- 注册中心：nacos、consul
- 配置中心：nacos

后续如果有需要计划适配各大配置中心、注册中心。

## 基本架构

- 管控端： 包括前端页面和管控端服务端，主要功能就是能过通过页面去做一些服务治理的功能，比如获取服务注册列表。给线上服务发送路由规则、鉴权规则、限流规则。（待实现）
- 配置中心，目前只适配了nacos，可以适配各种配置中心
- 注册中心，目前只适配了nacos，可以适配各种注册中心

![](https://static.javajike.com/img/2021/4/25/mcf01.png)

###  服务路由基本原理

![mcf02](https://static.javajike.com/img/2021/4/25/mcf02.png)

- 服务注册的时候上报各自服务的基本数据，比如当前服务的region、zone、版本、IP等信息。
- 在管控端下发配置给配置中心，微服务A监控配置发生了变化，及时更新了路由配置规则。比如下发了只能调用B服务的1.1版本。
- 服务A在调用服务B的时候会根据路由规则去选择服务B，在服务注册列表中B服务的元数据含有的版本的信息，这是服务B在服务注册的时候注册进去的。服务注册的时候可以通过application.yml去配置以下元数据，也可以通过jvm启动参数的形式注入，比如-Dswr_cluster_id:=test-clu

```
swr_cluster_id: test-clu
swr_namespace_id: test-ns
swr_app_id: test-consumewr
swr_app_version: 1.1
swr_service_name: consumer
swr_instance_id: test-si2
swr_group_id: test-sg2
version: 1.1
group: test-g
```

在nacos配置中心下发配置（管控界面操作还没有实现），如下：

![mcf03](https://static.javajike.com/img/2021/4/25/mcf03.png)

配置下发后立马生效，当前服务只能调用版本为v1.1或者1.2的版本。

## 异步Header的传递

通过discovery的agent去传递，需要用到discovery-agent-starter，代码地址：

```
-javaagent:/Users/nickelfang/work/github/github202007/DiscoveryAgent/discovery-agent/discovery-agent-starter-1.0.2.jar -Dthread.scan.packages="io.github.forezp.context;com.netflix.hystrix" -Dthread.swrplugin.enabled=true
```

