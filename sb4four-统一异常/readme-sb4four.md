##sping boot 模版引擎的使用、swagger2和统一异常处理
   
   1. 默认配置
    
    Spring Boot默认提供静态资源目录位置需置于classpath下，目录名需符合如下规则：
    
    /static
    /public
    /resources
    /META-INF/resources
    
    举例：我们可以在src/main/resources/目录下创建static，在该位置放置一个图片文件。启动程序后，尝试访问http://localhost:8080/D.jpg。如能显示图片，配置成功。
    
   2. Spring Boot提供了默认配置的模板引擎主要有以下几种：
    
    Thymeleaf
    FreeMarker
    Velocity
    Groovy
    Mustach
    
    Spring Boot建议使用这些模板引擎，避免使用JSP，若一定要使用JSP将无法实现Spring Boot的多种特性
    当你使用上述模板引擎中的任何一个，它们默认的模板配置路径为：src/main/resources/templates。当然也可以修改这个路径，具体如何修改，可在后续各模板引擎的配置属性中查询并修改。
    
####1.使用Thymeleaf模板引擎渲染web视图(其它模板引擎使用方式一样)
    
    Thymeleaf是一个XML/XHTML/HTML5模板引擎，可用于Web与非Web环境中的应用开发。
    Thymeleaf提供了一个用于整合Spring MVC的可选模块，在应用开发中，你可以使用Thymeleaf来完全代替JSP或其他模板引擎，如Velocity、FreeMarker等。
    
    步骤：
    1.引入下面依赖
    <dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    
    2.src/main/resources/templates下编写模板文件即可,hymeleaf的页面语法，还请访问Thymeleaf的官方文档查询使用
    <table>
      <thead>
        <tr>
          <th th:text="#{msgs.headers.name}">Name</td>
          <th th:text="#{msgs.headers.price}">Price</td>
        </tr>
      </thead>
      <tbody>
        <tr th:each="prod : ${allProducts}">
          <td th:text="${prod.name}">Oranges</td>
          <td th:text="${#numbers.formatDecimal(prod.price,1,2)}">0.99</td>
        </tr>
      </tbody>
    </table>
    
    Thymeleaf的默认参数配置
    如有需要修改默认配置的时候，只需复制下面要修改的属性到application.properties中，并修改成需要的值，如修改模板文件的扩展名，修改默认的模板路径等。
    
    # Enable template caching.
    spring.thymeleaf.cache=true 
    # Check that the templates location exists.
    spring.thymeleaf.check-template-location=true 
    # Content-Type value.
    spring.thymeleaf.content-type=text/html 
    # Enable MVC Thymeleaf view resolution.
    spring.thymeleaf.enabled=true 
    # Template encoding.
    spring.thymeleaf.encoding=UTF-8 
    # Comma-separated list of view names that should be excluded from resolution.
    spring.thymeleaf.excluded-view-names= 
    # Template mode to be applied to templates. See also StandardTemplateModeHandlers.
    spring.thymeleaf.mode=HTML5 
    # Prefix that gets prepended to view names when building a URL.
    spring.thymeleaf.prefix=classpath:/templates/ 
    # Suffix that gets appended to view names when building a URL.
    spring.thymeleaf.suffix=.html  spring.thymeleaf.template-resolver-order= # Order of the template resolver in the chain. spring.thymeleaf.view-names= # Comma-separated list of view names that can be resolved.
    
####2.使用Swagger2构建restful api
    
    步骤
    
    1.引入坐标
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.2.2</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.2.2</version>
    </dependency>
    
    2.创建Swagger2配置类
    在Application.java同级创建Swagger2的配置类Swagger2
    
    @Configuration
    @EnableSwagger2
    public class Swagger2 {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.zbq.web"))
                    .paths(PathSelectors.any())
                    .build();
        }
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Spring Boot中使用Swagger2构建RESTful APIs")
                    .description("")
                    .termsOfServiceUrl("")
                    .contact("")
                    .version("1.0")
                    .build();
        }
    }
    
    如上代码所示，通过@Configuration注解，让Spring来加载该类配置。再通过@EnableSwagger2注解来启用Swagger2。
    
    再通过createRestApi函数创建Docket的Bean之后，apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
    select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，
    Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
    
    3.添加文档内容
    在Controller中，我们通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
     
    完成上述代码添加上，启动Spring Boot程序，访问：http://localhost:8080/swagger-ui.html
    
####3.统一异常处理
    Spring Boot提供了一个默认的映射：/error，当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容。
    
    虽然，Spring Boot中实现了默认的error映射，但是在实际应用中，上面你的错误页面对用户来说并不够友好，我们通常需要去实现我们自己的异常提示。
    
    步骤
    1.创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
    @ExceptionHandler用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到error.html中
    
    @ControllerAdvice
    class GlobalExceptionHandler {
        public static final String DEFAULT_ERROR_VIEW = "error";
        @ExceptionHandler(value = Exception.class)
        public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", req.getRequestURL());
            mav.setViewName(DEFAULT_ERROR_VIEW);
            return mav;
        }
    }
    
    2.
     2.1实现error.html页面展示：在templates目录下创建error.html，将请求的URL和Exception对象的message输出。
    
    <!DOCTYPE html>
    <html>
    <head lang="en">
        <meta charset="UTF-8" />
        <title>统一异常处理</title>
    </head>
    <body>
        <h1>Error Handler</h1>
        <div th:text="${url}"></div>
        <div th:text="${exception.message}"></div>
    </body>
    </html>
    
    2.2返回JSON格式异常信息
        只需在@ExceptionHandler之后加入@ResponseBody，就能让处理函数return的内容转换为JSON格式。
    
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(value = MyException.class)
        @ResponseBody
        public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
            ErrorInfo<String> r = new ErrorInfo<>();
            r.setMessage(e.getMessage());
            r.setCode(ErrorInfo.ERROR);
            r.setData("Some Data");
            r.setUrl(req.getRequestURL().toString());
            return r;
        }
    }
    
    通过实现上述内容之后，我们只需要在Controller中抛出Exception，当然我们可能会有多种不同的Exception。
    然后在@ControllerAdvice类中，根据抛出的具体Exception类型匹配@ExceptionHandler中配置的异常类型来匹配错误映射和处理。
    
    