# redisclient ![version](https://img.shields.io/badge/version-0.0.1-blue.svg)
对官方 redisclient 的一个封装，主要提供两种功能
- 支持四种连接池配置：JedisPool/JedisSentinelPool/ShardedJedisPool/ShardedJedisSentinelPool
- redis 相关操作不需要手动 获取连接 释放连接
---
## 使用方法
---
### pom.xml 添加依赖

```
<dependency>
	<groupId>com.cacheproxy.client</groupId>
	<artifactId>redisclient</artifactId>
	<version>0.0.1</version>
</dependency>
```

### resources 目录添加 redisclient.properties
不管哪种连接池，该配置都支持所有  org.apache.commons.pool2.impl.GenericObjectPoolConfig(http://commons.apache.org/proper/commons-pool/api-2.4.2/index.html) 的所有属性
#### JedisPool 配置举例

```
host=127.0.0.1
port=6379
database=2
timeout=4000
password=aabbccddeeffgg
clientName=dubbo-user

maxTotal=10
maxIdle=5

```

#### JedisSentinelPool 配置举例 

```
master=user-master
sentinels=10.10.10.1:26379,10.10.10.2:26379,10.10.10.3:26379
database=2
timeout=4000
password=aabbccddeeffgg
clientName=dubbo-user

maxTotal=10
maxIdle=5
```

#### ShardedJedisPool 配置举例

```
shards=host:10.10.200.1,port:6379,timeout:2000,weight:1;host:10.10.10.2,port:6379,timeout:2000,weight:2

maxTotal=10
maxIdle=5
```

#### ShardedJedisSentinelPool 配置举例 

```
masters=user-master1,user-master2
sentinels=10.10.10.1:26379,10.10.10.2:26379,10.10.10.3:26379
database=2
timeout=4000
password=aabbccddeeffgg

maxTotal=10
maxIdle=5
```

### 代码引用
---
#### 普通redis 命令
#### 对于独占连接的操作，还是应当有try finally 模块
