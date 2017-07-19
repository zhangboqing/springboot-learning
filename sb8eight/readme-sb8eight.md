##sping boot 整合MyBatis、MyBatis注解配置详解


####1.Spring Boot 整合MyBatis
    步骤
    1.引入依赖
    
    1.1这里用到spring-boot-starter基础和spring-boot-starter-test用来做单元测试验证数据访问
    1.2引入连接mysql的必要依赖mysql-connector-java
    1.3引入整合MyBatis的核心依赖mybatis-spring-boot-starter
    1.4这里不引入spring-boot-starter-jdbc依赖，是由于mybatis-spring-boot-starter中已经包含了此依赖
   
       	<dependency>
       		<groupId>org.mybatis.spring.boot</groupId>
       		<artifactId>mybatis-spring-boot-starter</artifactId>
       	</dependency>
       	<dependency>
       		<groupId>mysql</groupId>
       		<artifactId>mysql-connector-java</artifactId>
       	</dependency> 
       	
     2.配置
     在application.properties中配置mysql的连接配置
     
     spring.datasource.url=jdbc:mysql://localhost:3306/test
     spring.datasource.username=root
     spring.datasource.password=123456
     spring.datasource.driver-class-name=com.mysql.jdbc.Driver
     
     3.使用MyBatis进行数据库操作
        
####2.MyBatis注解配置详解
    1.@Param
    @Param中定义的name对应了SQL中的#{name}，age对应了SQL中的#{age}
    
    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
    
    2.使用Map    
    通过Map对象来作为传递参数的容器
    对于Insert语句中需要的参数，我们只需要在map中填入同名的内容即可
    
    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);
    
    Map<String, Object> map = new HashMap<>();
    map.put("name", "CCC");
    map.put("age", 40);
    userMapper.insertByMap(map);
    
    3.使用对象
    除了Map对象，我们也可直接使用普通的Java对象来作为查询条件的传参
    
    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insertByUser(User user);
    
    4.@Results和@Result
    返回结果的绑定
    
    @Results({
        @Result(property = "name", column = "name"),
        @Result(property = "age", column = "age")
    })
    @Select("SELECT name, age FROM user")
    List<User> findAll();

