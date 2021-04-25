

## 限流的配置


```text

rate.limit.enable: true
rate.limit.global.num: 100
rate.limit.global.second: 1
rate.limit.services:
  - name: mcf-provider
    num: 10
    second: 11
  - name: mcf-consumer
    num: 23
    second: 11
    
```

## 上报数据间隔

```text

limit.data.collect.period: 5000

```