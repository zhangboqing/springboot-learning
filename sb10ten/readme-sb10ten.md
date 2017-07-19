##sping boot @Scheduled定时任务、@Async异步调用

####1.使用@Scheduled创建定时任务
    实现每过5秒输出一下当前时间
    步骤
    1.在Spring Boot的主类中加入@EnableScheduling注解，启用定时任务的配置
    
    @SpringBootApplication
    @EnableScheduling
    public class Application {
    	public static void main(String[] args) {
    		SpringApplication.run(Application.class, args);
    	}
    }
    
    2.创建定时任务实现类
    
    @Component
    public class ScheduledTasks {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        @Scheduled(fixedRate = 5000)
        public void reportCurrentTime() {
            System.out.println("现在时间：" + dateFormat.format(new Date()));
        }
    }
    
    3.运行
    
   
    @Scheduled详解
    
    1.@Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
    2.@Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
    3.@Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
    4.@Scheduled(cron="*/5 * * * * *") ：通过cron表达式定义规则
   

####2.使用@Async实现异步调用

    步骤
    1.使用@Async注解将原来的同步函数变为异步函数
    
    @Component
    public class Task {
        @Async
        public void doTaskOne() throws Exception {
            // 同上内容，省略
        }
        @Async
        public void doTaskTwo() throws Exception {
            // 同上内容，省略
        }
        @Async
        public void doTaskThree() throws Exception {
            // 同上内容，省略
        }
    }
    
    2.为了让@Async注解能够生效，还需要在Spring Boot的主程序中配置@EnableAsync
    
    @SpringBootApplication
    @EnableAsync
    public class Application {
    	public static void main(String[] args) {
    		SpringApplication.run(Application.class, args);
    	}
    }
    
    3.注意:@Async所修饰的函数不要定义为static类型，这样异步调用不会生效
    
    4.注意:异步的注解不能用在同一个类中。
    
    