package com.zengyanyu.system;

import com.zengyanyu.system.component.ApplicationContextHelper;
import com.zengyanyu.system.controller.DictController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class) // JUnit4 必须加
@SpringBootTest
public class BeanTest {

    @Resource
    private ApplicationContext context;

    /**
     * 测试单例Bean
     */
    @Test
    public void testSingletonBean() {
        for (int i = 0; i < 10; i++) {
            Object deptController = context.getBean("departmentController");
            System.out.println("deptController = " + deptController);
            System.out.println("***********************************");
            Object departmentController = ApplicationContextHelper.getBean("departmentController");
            System.out.println("departmentController = " + departmentController);
        }
    }

    /**
     * 测试非单例/多例Bean
     */
    @Test
    public void testPrototypeBean() {
        for (int i = 0; i < 10; i++) {
            Object dictController = context.getBean("dictController");
            System.out.println("dictController = " + dictController);
            System.out.println("========================");
            DictController dictController1 = context.getBean("dictController", DictController.class);
            System.out.println("dictController1 = " + dictController1);
        }
    }
}
