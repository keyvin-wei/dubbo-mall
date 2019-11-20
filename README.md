# dubbo-mall
dubbo商城项目

RPC 远程过程调用
客户机<--->服务器模式
抽象后都是服务和服务之间的调用

## 模块
父模块：子模块api、子模块model、子模块server，作为服务提供方   
mall-client是独立的springboot，作为服务消费方

dubbo常用两大协议   
RPC协议（dubbo协议）--同构，犹如本地调用   
Http协议（rest api）--通用项目，通用解析   
配置文件：
+ spring-dubbo.xml
    - 作用一：注解dubbo服务所在包的发布
    - 作用二：配置支持的协议，两种调用方式
    - 作用三：消费服务配置
+ dubbo.properties
    - 作用：配置两大协议对应的端口信息等
    
功能技术点：
 + 服务拆分：商品库存服务，商城服务，订单服务，支付服务...
 + 服务解耦：明确服务职责，服务调度，网络通信
 + 服务管理：统一注册中心配置（发布、订阅调度）
 
 调用其他系统dubbo服务：  
  引入依赖：pom引入依赖包  
  引入服务：spring-dubbo.xml配置服务接口
  
 
  