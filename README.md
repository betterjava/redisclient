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
不管哪种连接池，该配置都支持  org.apache.commons.pool2.impl.GenericObjectPoolConfig(http://commons.apache.org/proper/commons-pool/api-2.4.2/index.html) 的所有属性
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
##### jedis
```
@Test
public void testJedisProxy() {
	// 可以将 此 jedisProxy 作为静态变量
	// 默认从 redisclient.properties 加载配置，也可以自定义配置文件名称 JedisProxyFactory.createJedisProxy("my-redis.properties");
	JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
	
	jedisProxy.set("cookie", "hello");

	Assert.assertEquals("hello", jedisProxy.get("cookie"));
}
```
##### shardedjedis
```
@Test
public void testShardJedisProxy(){
	// 可以将 此 proxy 作为静态变量
	// 默认从 redisclient.properties 加载配置，也可以自定义配置文件名称 JedisProxyFactory.createShardedJedisProxy("my-redis.properties");
	ShardedJedisProxy proxy = JedisProxyFactory.createShardedJedisProxy();

	proxy.set("cookie", "girl");

	Assert.assertEquals("girl", proxy.get("cookie"));
}
```
#### 对于独占连接的操作，还是应当有try finally 模块
说明：以事务操作为例子，虽然开始事务时获取连接，关闭事务时释放连接，但是如果发生异常使得关闭事务得不到执行，则连接就不会得到释放，所以需要手动加上try   finally 模块
##### 管道操作
```
@Test
public void testJedisProxyPipeline() {
	// 可以将 此 jedisProxy 作为静态变量
	// 默认从 redisclient.properties 加载配置，也可以自定义配置文件名称 JedisProxyFactory.createJedisProxy("my-redis.properties");
	JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
	Pipeline pi = jedisProxy.pipelined();
	try {
		pi.set("cookie", "helloboy");
		pi.get("cookie");
		System.out.println(pi.syncAndReturnAll());
	} catch (Exception e) {
		// TODO: handle exception
	}finally{
		ReleaseUtil.realease(pi);
	}
}
```
##### 事务操作

```
@Test
public void testTr(){
	// 可以将 此 jedisProxy 作为静态变量
	// 默认从 redisclient.properties 加载配置，也可以自定义配置文件名称 JedisProxyFactory.createJedisProxy("my-redis.properties");
	JedisProxy jedisProxy = JedisProxyFactory.createJedisProxy();
	Transaction tt = null;
	try {
		tt = jedisProxy.multi();
		tt.set("cookie", "10000");
		tt.exec();
	} finally{
		ReleaseUtil.realease(tt);
	}
}
```
