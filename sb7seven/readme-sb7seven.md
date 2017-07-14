##sping boot NoSQL数据库连接 redis/MongoDB
    Spring Boot中除了对常用的关系型数据库提供了优秀的自动化支持之外，
    对于很多NoSQL数据库一样提供了自动化配置的支持，包括：Redis, MongoDB, Elasticsearch, Solr和Cassandra。
    
####1.使用Redis数据库
    步骤
     1.引入依赖（基于Jedis）
     
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-redis</artifactId>
     </dependency>
     
     实现依赖
     		<dependency>
     			<groupId>org.springframework.data</groupId>
     			<artifactId>spring-data-redis</artifactId>
     		</dependency>


     2.参数配置（Redis服务端的相关配置）
     
     # redis连接配置
     # Redis数据库数量（默认为16）
     spring.redis.database=0
     # Redis服务器地址
     spring.redis.host=localhost
     # Redis服务器连接端口
     spring.redis.port=6379
     # Redis服务器连接密码（默认为空）
     spring.redis.password=
     # 连接池最大连接数（使用负值表示没有限制）
     spring.redis.pool.max-active=8
     # 连接池最大阻塞等待时间（使用负值表示没有限制）
     spring.redis.pool.max-wait=-1
     # 连接池中的最大空闲连接
     spring.redis.pool.max-idle=8
     # 连接池中的最小空闲连接
     spring.redis.pool.min-idle=0
     # 连接闲置超时时间（毫秒）0 表示关闭该功能
     spring.redis.timeout=0
     
     其中spring.redis.database的配置通常使用0即可，Redis在配置的时候可以设置数据库数量，默认为16，可以理解为数据库的schema
     
     3.使用StringRedisTemplate
     引入依赖
     
            <dependency>
     			<groupId>org.springframework.data</groupId>
     			<artifactId>spring-data-redis</artifactId>
     			<version>1.8.1.RELEASE</version>
     			<scope>test</scope>
     		</dependency>
     		
     使用
     
     @Autowired
     	private StringRedisTemplate stringRedisTemplate;
     	@Test
     	public void test() throws Exception {
     		// 保存字符串
     		stringRedisTemplate.opsForValue().set("aaa", "111");
     		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
         }
         
       通过自动配置的StringRedisTemplate对象进行Redis的读写操作，该对象从命名中就可注意到支持的是String类型。
       如果有使用过spring-data-redis的开发者一定熟悉RedisTemplate<K, V>接口，StringRedisTemplate就相当于RedisTemplate<String, String>的实现。
       
####1.使用MongoDb数据库
       步骤
       1.引入依赖
       
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-data-mongodb</artifactId>
       </dependency>
       
       实现依赖，是spring-data的子项目
            <dependency>
       			<groupId>org.springframework.data</groupId>
       			<artifactId>spring-data-mongodb</artifactId>
       		</dependency>
       
       2.配置
        若MongoDB的安装配置采用默认端口，那么在自动配置的情况下，我们不需要做任何参数配置，就能马上连接上本地的MongoDB。
        直接使用spring-data-mongodb来尝试对mongodb的存取操作。     
        
        应用服务器与MongoDB通常不在同一台设备，则需要在application.properties中加入mongodb服务端的相关配置
        
        spring.data.mongodb.uri=mongodb://name:pass@localhost:27017/test
        
        

