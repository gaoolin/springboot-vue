package com.gl.springbootend.utils;

import cn.hutool.core.util.RandomUtil;
import com.gl.springbootend.SpringbootEndApplication;
import com.gl.springbootend.entity.User;
import com.gl.springbootend.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Random;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootEndApplication.class)
public class UUIDGenerator {

    @Autowired
    UserMapper userMapper;

    @Test
    public void dataGeneration(){

        //UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            int randomInt = RandomUtil.randomInt(20, 60);
            userMapper.insert(new User(null, uid, "123456", uid , randomInt, randomInt%2==0?"男":"女","昆山市千灯镇"));
            System.out.println("批量生成完成");
        }
    }
}
