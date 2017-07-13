##sping boot入门 基本项目构建、spring boot中的注解、Spring Boot 工程结构最佳实践
    Spring Boot让我们的Spring应用变的更轻量化。
    比如：你可以仅仅依靠一个Java类来运行一个Spring引用。
    你也可以打包你的应用为jar并通过使用java -jar来运行你的Spring Web应用。
  
####Spring Boot的主要优点：
  
    1.为所有Spring开发者更快的入门
    2.开箱即用，提供各种默认配置来简化项目配置
    3.内嵌式容器简化Web项目
    4.没有冗余代码生成和XML配置的要求
    
####maven构建项目
    第一种方式
    1.访问：http://start.spring.io/
    2.选择构建工具Maven Project、Spring Boot版本1.3.2以及一些工程基本信息
    
    第二种方式（通过IntelliJ IDEA）
    1.菜单栏中选择File=>New=>Project
    2.选择Spring Initializr

####spring boot中的注解
    1.@SpringBootApplication
    申明让spring boot自动给程序进行必要的配置，这个配置等同于：
    @Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置
    
    2.@RestController
    @ResponseBody和@Controller的合集
    Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
    
    3.@EnableAutoConfiguration
    Spring Boot自动配置（auto-configuration）：尝试根据你添加的jar依赖自动配置你的Spring应用。
    例如，如果你的classpath下存在HSQLDB，并且你没有手动配置任何数据库连接beans，那么我们将自动配置一个内存型（in-memory）数据库”。
    你可以将@EnableAutoConfiguration或者@SpringBootApplication注解添加到一个@Configuration类上来选择自动配置。
    如果发现应用了你不想要的特定自动配置类，你可以使用@EnableAutoConfiguration注解的排除属性来禁用它们。
    
    4.@ComponentScan
    表示将该类自动发现（扫描）并注册为Bean，可以自动收集所有的Spring组件，包括@Configuration类。
    我们经常使用@ComponentScan注解搜索beans，并结合@Autowired注解导入。
    如果没有配置的话，Spring Boot会扫描启动类所在包下以及子包下的使用了@Service,@Repository等注解的类。
    
    5.@Configuration
    相当于传统的xml配置文件，如果有些第三方库需要用到xml文件，
    建议仍然通过@Configuration类作为项目的配置主类——可以使用@ImportResource注解加载xml配置文件。
    
    6.@Import
    用来导入其他配置类。
    
    7.@ImportResource
    用来加载xml配置文件。
    
    8.@Bean
    用@Bean标注方法等价于XML中配置的bean。
    
    9.@Value
    注入Spring boot application.properties配置的属性的值。
    
    示例代码：
    @Value(value = "#{message}") 
    private String message; 
    
    10.@Qualifier
    @Qualifier限定描述符除了能根据名字进行注入，但能进行更细粒度的控制如何选择候选者，具体使用方式如下：
    
    @Autowired
    @Qualifier(value = "demoInfoService") 
    private DemoInfoService demoInfoService;
    
    11.@Inject
    等价于默认的@Autowired，只是没有required属性；
    
    
 ####Spring Boot 工程结构最佳实践
    典型示例
    
    root package结构：com.example.myproject
    应用主类Application.java置于root package下，通常我们会在应用主类中做一些框架配置扫描等配置，我们放在root package下可以帮助程序减少手工配置来加载到我们希望被Spring加载的内容
    实体（Entity）与数据访问层（Repository）置于com.example.myproject.domain包下
    逻辑层（Service）置于com.example.myproject.service包下
    Web层（web）置于com.example.myproject.web包下    
    
    com
      +- example
        +- myproject
          +- Application.java
          |
          +- domain
          |  +- Customer.java
          |  +- CustomerRepository.java
          |
          +- service
          |  +- CustomerService.java
          |
          +- web
          |  +- CustomerController.java
          |

    
    