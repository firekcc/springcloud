# spring cloud config client
config client

### config server 集群访问方式
```
spring.cloud.config.discovery.enabled:true
spring.cloud.config.discovery.service-id:config-server
```

### config server 单节点访问方式
```
spring.cloud.config.profile=dev
spring.cloud.config.label=main
spring.cloud.config.uri=http://localhost:8760/
```

### config 动态刷新

### config 读取其他类型配置文件