##sping boot实现简单对象操作的RESTFUL API

####1.实现简单对象操作的RESTFUL API
    与spring项目编写一样，只是多了几种注解
    
####2.单元测试
    通过MockMvc进行测试
    
####3.@ModelAttribute注解的使用
    注意：访问控制器方法时，会优先调用被@ModelAttribute修饰的方法,但是加了@RequestMapping的方法不会被调用
    1.@ModelAttribute注释void返回值的方法
    2.@ModelAttribute注释返回具体类的方法
        会将返回的结果放入model域中
    3.对象合并(传递)
    //controller方法接收参数的对象和该返回对象是同一个对象，具有传递性
    @Controller  
    public class Hello2ModelController {  
          
        @ModelAttribute  
        public User populateModel() {    
           User user=new User();  
           user.setAccount("ray");  
           return user;  
        }    
          
        @RequestMapping(value = "/helloWorld2")    
        public String helloWorld(User user) {  
            user.setName("老王");  
           return "helloWorld.jsp";    
        }    
    }  
   