package com.company;

import com.company.dubbo.DubboConsumerApplication;
import com.company.dubbo.api.dto.User;
import com.company.dubbo.service.UserManagerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author bin.li
 * @date 2020/11/10
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DubboConsumerApplication.class)
public class DubboTest {

    @Autowired
    private UserManagerService userManagerService;

    @Test
    public void test1(){
        User user = userManagerService.getUser();
        System.out.println(user);
    }

    @Test
    public void test2(){
        User user = userManagerService.getRestUser();
        System.out.println(user);
    }
}
