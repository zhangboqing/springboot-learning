##sping boot配置文件使用(可自定义属性、多环境配置、随机数)
    相比spring 项目，spring boot替代它的是在pom.xml中引入模块化的Starter POMs，
    其中各个模块都有自己的默认配置，所以如果不是特殊应用场景，
    就只需要在application.properties中完成一些属性配置就能开启各模块的应用
    
####1.自定义属性与加载
    1.在application.properties中定义属性
    2.然后通过@Value("${属性名}")注解来加载对应的配置属性
    3.参数间的引用
    在application.properties中的各个参数之间也可以通过${}直接引用来使用
    
    问题：
        1.@Value不能对statis属性复制
        2.中文有乱码
        解决：必须对中文进行Unicode编码一下，再写入properties文件中
     
####2.使用随机数
    1.Spring Boot的属性配置文件中可以通过${random}来产生int值、long值或者string字符串，来支持属性的随机值。
    
    #随机数
    #随机字符串
    random.string=${random.value} eg:"3f7d9ddc34cd04524fad579b04e8f315"
    #随机int
    random.int1=${random.int}
    #随机long
    random.long1=${random.long}
    #10以内的随机数
    random.int2=${random.int(10)}
    #10-20的随机数
    random.int3=${random.int[10,20]}

####3.通过命令行设置属性值
    1.java -jar xxx.jar -server.port=8888 
        通过使用–-server.port属性来设置xxx.jar应用的端口为8888
    2.java -jar xxx.jar --server.port=8888
        在命令行运行时，连续的两个减号--就是对application.properties中的属性值进行赋值的标识。
        所以，java -jar xxx.jar --server.port=8888命令，等价于我们在application.properties中添加属性server.port=8888
    3.屏蔽命令行访问属性的设置，只需要这句设置就能屏蔽：
        SpringApplication.setAddCommandLineProperties(false)

####多环境配置
    1.在Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式，
    其中{profile}对应你的环境标识,比如：
        application-dev.properties：开发环境
        application-release.properties：测试环境
        application-preheat.properties：预热环境
        application-online.properties：生产环境
    2.至于哪个具体的配置文件会被加载，需要在application.properties文件中通过spring.profiles.active属性来设置，
    其值对应{profile}值。
    如：spring.profiles.active=dev就会加载application-dev.properties配置文件内容
    3.通过命令行方式去激活不同环境的配置
    执行java -jar xxx.jar --spring.profiles.active=release
    