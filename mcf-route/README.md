eureka.instance.metadataMap.group=example-service-group
eureka.instance.metadataMap.version=1.0
eureka.instance.metadataMap.region=dev

# Consul config for discovery
spring.cloud.consul.discovery.tags=group=example-service-group,version=1.0,region=dev



# Nacos config for discovery
spring.cloud.nacos.discovery.metadata.group=example-service-group
spring.cloud.nacos.discovery.metadata.version=1.0
spring.cloud.nacos.discovery.metadata.region=dev

```text

mcf.route.enable: true
mcf.route.services:
  - name: mcf-provider
    version: 1.0,1.1
    region: dev
    group: example-group
  - name: mcf-provider2
    version: 23
    region: dev
    group: example-group
    
```