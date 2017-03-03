

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tech.mybatis.pojo.User;
import org.tech.mybatis.service.UserService;
  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})  
public class TestMyBatis {  
    private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);
//  private ApplicationContext ac = null;  
    @Resource  
    private UserService userService = null;  
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
        User user = userService.getUserById(1);  
        logger.error(user.toString());
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
    } 
    
    @Test
    public void testInsert() {
    	User user = new User("Test", "123456", 30);
    	int flg = userService.insertUser(user);
    	System.out.println(flg);
    }
}