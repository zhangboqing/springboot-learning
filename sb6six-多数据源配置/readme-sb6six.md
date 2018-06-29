##sping boot 多数据源配置

####多数据源配置
    创建一个Spring配置类，定义两个DataSource用来读取application.properties中的不同配置。
    如下例子中，主数据源配置为spring.datasource.primary开头的配置，
    第二数据源配置为spring.datasource.secondary开头的配置。
   
    @Configuration
    public class DataSourceConfig {
        @Bean(name = "primaryDataSource")
        @Qualifier("primaryDataSource")
        @ConfigurationProperties(prefix="spring.datasource.primary")
        public DataSource primaryDataSource() {
            return DataSourceBuilder.create().build();
        }
        @Bean(name = "secondaryDataSource")
        @Qualifier("secondaryDataSource")
        @Primary
        @ConfigurationProperties(prefix="spring.datasource.secondary")
        public DataSource secondaryDataSource() {
            return DataSourceBuilder.create().build();
        }
    }
    
    对应的application.properties配置如下：
    
    spring.datasource.primary.url=jdbc:mysql://localhost:3306/test1
    spring.datasource.primary.username=root
    spring.datasource.primary.password=root
    spring.datasource.primary.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.secondary.url=jdbc:mysql://localhost:3306/test2
    spring.datasource.secondary.username=root
    spring.datasource.secondary.password=root
    spring.datasource.secondary.driver-class-name=com.mysql.jdbc.Driver
    
####1.对JdbcTemplate支持
     对JdbcTemplate的支持比较简单，只需要为其注入对应的datasource即可，
     如下例子，在创建JdbcTemplate的时候分别注入名为primaryDataSource和secondaryDataSource的数据源来区分不同的JdbcTemplate
     
     @Bean(name = "primaryJdbcTemplate")
     public JdbcTemplate primaryJdbcTemplate(
             @Qualifier("primaryDataSource") DataSource dataSource) {
         return new JdbcTemplate(dataSource);
     }
     @Bean(name = "secondaryJdbcTemplate")
     public JdbcTemplate secondaryJdbcTemplate(
             @Qualifier("secondaryDataSource") DataSource dataSource) {
         return new JdbcTemplate(dataSource);
     }
     
####2.Spring-data-jpa支持

    新增对第一数据源的JPA配置，注意两处注释的地方，用于指定数据源对应的Entity实体和Repository定义位置，用@Primary区分主数据源。
    新增对第二数据源的JPA配置，内容与第一数据源类似
    
    主数据源的实体和数据访问对象位于：com.zbq.domain.p，次数据源的实体和数据访问接口位于：com.zbq.domain.s。