package com.wangshi.ws.springboot;

import com.wangshi.ws.springboot.model.Teacher;
import com.wangshi.ws.springboot.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
    @Autowired
    TeacherService teacherService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作字符串

    @Autowired
    RedisTemplate<String, Object> redisTemplate;//操作k-v


    @Test
    public void contextLoads() {
        Teacher teacher = teacherService.getTeacherById(1);

        String name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);

        redisTemplate.opsForValue().set("teacher", teacher);
    }

}
