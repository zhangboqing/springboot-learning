# Spring Boot 自动配置
```
1.新建stater maven项目
增加Spring Boot自身的自动配置maven依赖
    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-autoconfigure -->
    		<dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-autoconfigure</artifactId>
    			<version>2.0.3.RELEASE</version>
    		</dependency>
2.属性配置
HelloServiceProperties
自定义一个获取属性的类,可以获取在application.properties中设置的自定义属性

3.判断依据类
HelloService
定义一个类，通过判断该类是否存在，来创建该类的Bean,该类可以是第三方库的类如DataSource

4.自动配置类
HelloServiceAutoConfiguration
根据HelloServiceProperties提供的参数，并通过@ConditionalOnClass判断HelloService这个类在类路径中是否存在，且当容器中没有这个Bean的情况下自动配置这个Bean

5.注册配置
若想自动配置生效，需要注册自动配置类。在src/main/resources下新建META-INF/spring.factories
如下：

# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration

若有多个自动配置，则用','隔开，此处'\'是为了换行后仍能读到属性

6.使用starter 作为其他项目的依赖
引入maven 坐标依赖

```