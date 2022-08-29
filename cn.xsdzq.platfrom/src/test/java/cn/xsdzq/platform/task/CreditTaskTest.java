package cn.xsdzq.platform.task;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(locations = {"classpath:config.xml"})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CreditTaskTest {
    @Autowired
    private CreditTask creditTask;

    //@Test
    public void testQueryOrderTask() throws Exception {
        creditTask.queryOrderTask();
    }
}