##sping boot 数据库访问 JdbcTemplate、Spring-data-jpa
    在Spring Boot基础下配置数据源和通过JdbcTemplate编写数据访问

   
####1.使用JdbcTemplate
    
    步骤
    1.引入jdbc数据源配置,为了连接数据库需要引入jdbc支持
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    
    2.
    2.1嵌入式数据库支持
    嵌入式数据库通常用于开发和测试环境，不推荐用于生产环境。
    Spring Boot提供自动配置的嵌入式数据库有H2、HSQL、Derby，你不需要提供任何连接配置就能使用。
    pom坐标：
     <dependency>
         <groupId>org.hsqldb</groupId>
         <artifactId>hsqldb</artifactId>
         <scope>runtime</scope>
     </dependency>
    
    2.2连接生产数据源msql数据库
    
    pom坐标
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.21</version>
    </dependency>
    
    在src/main/resources/application.properties中配置数据源信息
    
    spring.datasource.url=jdbc:mysql://localhost:3306/test
    spring.datasource.username=dbuser
    spring.datasource.password=dbpass
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    
    2.3连接JNDI数据源
    当你将应用部署于应用服务器上的时候想让数据源由应用服务器管理，那么可以使用如下配置方式引入JNDI数据源。
    
    spring.datasource.jndi-name=java:jboss/datasources/customers
    
    3.使用JdbcTemplate操作数据库
    Spring的JdbcTemplate是自动配置的，你可以直接使用@Autowired来注入到你自己的bean中来使用

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
####使用Spring-data-jpa简化数据访问层

    步骤
    1.引入pom坐标
    
    <dependency
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    2.在application.properties中配置：数据库连接信息（如使用嵌入式数据库则不需要）、自动创建表结构的设置，例如使用mysql的情况如下：
    
    spring.datasource.url=jdbc:mysql://localhost:3306/test
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    
    spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop
    
    其中spring.jpa.properties.hibernate.hbm2ddl.auto是hibernate的配置属性，其主要作用是：自动创建、更新、验证数据库表结构。该参数的几种配置如下：
    
    create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
    create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
    update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。
    validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。
    
    JPA的传统配置在persistence.xml文件中，但是这里我们不需要,最好在构建项目时候按照之前提过的最佳实践的工程结构来组织，这样以确保各种配置都能被框架扫描到
    
    3.我们只需要通过编写一个继承自JpaRepository的接口就能完成数据访问
    3.1创建实体类
        创建一个User实体，包含id（主键）、name（姓名）、age（年龄）属性，通过ORM框架其会被映射到数据库表中，由于配置了hibernate.hbm2ddl.auto，在应用启动的时候框架会自动去数据库中创建对应的表。
        
        @Entity
        public class User {
            @Id
            @GeneratedValue
            private Long id;
            @Column(nullable = false)
            private String name;
            @Column(nullable = false)
            private Integer age;
            // 省略构造函数
            // 省略getter和setter
        }
      
     3.2创建数据访问接口
     
        public interface UserRepository extends JpaRepository<User, Long> {
            User findByName(String name);
            User findByNameAndAge(String name, Integer age);
            @Query("from User u where u.name=:name")
            User findUser(@Param("name") String name);
        }
        
       在Spring-data-jpa中，只需要编写类似上面这样的接口就可实现数据访问。不再像我们以往编写了接口时候还需要自己编写接口实现类，直接减少了我们的文件清单。
        
       下面对上面的UserRepository做一些解释，该接口继承自JpaRepository，
       通过查看JpaRepository接口的API文档，可以看到该接口本身已经实现了创建（save）、更新（save）、删除（delete）、查询（findAll、findOne）等基本操作的函数，
       因此对于这些基础操作的数据访问就不需要开发者再自己定义。
    
    在上例中，我们可以看到下面两个函数：
    
    User findByName(String name)
    User findByNameAndAge(String name, Integer age)
    它们分别实现了按name查询User实体和按name和age查询User实体，可以看到我们这里没有任何类SQL语句就完成了两个条件查询方法。这就是Spring-data-jpa的一大特性：通过解析方法名创建查询。
    
    除了通过解析方法名来创建查询外，它也提供通过使用@Query 注解来创建查询，您只需要编写JPQL语句，并通过类似“:name”来映射@Param指定的参数，就像例子中的第三个findUser函数一样。
    
    对于Spring-data-jpa的使用只是介绍了常见的使用方式。诸如@Modifying操作、分页排序、原生SQL支持以及与Spring MVC的结合使用等等