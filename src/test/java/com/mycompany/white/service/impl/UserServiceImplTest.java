package com.mycompany.white.service.impl;

import com.mycompany.white.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void 회원가입(){
        userService.saveUser("sloth@naver.com", "1234");


    }

}