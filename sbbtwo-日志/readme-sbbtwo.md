##sping boot 默认日志
    Spring Boot在所有内部日志中使用Commons Logging，但是默认配置也提供了对常用日志的支持，
    如：Java Util Logging，Log4J, Log4J2和Logback。每种Logger都可以通过配置使用控制台或者文件输出日志内容。

####1.默认日志
    1.默认的日志输出如下：
        	2016-04-13 08:23:50.120  INFO 37397 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate Core {4.3.11.Final}
     
     输出内容元素具体如下：
     
     时间日期 — 精确到毫秒
     日志级别 — ERROR, WARN, INFO, DEBUG or TRACE
     进程ID
     分隔符 — --- 标识实际日志的开始
     线程名 — 方括号括起来（可能会截断控制台输出）
     Logger名 — 通常使用源代码的类名
     日志内容

    2.在Spring Boot中默认配置了ERROR、WARN和INFO级别的日志输出到控制台

    
    