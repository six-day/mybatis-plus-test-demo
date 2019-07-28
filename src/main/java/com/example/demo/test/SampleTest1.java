package com.example.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest1 {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userDao.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    /*@Test
    public void insertTest(){
        User user = User.builder().age(12).name("李明").email("123@qq.com").id(11L).build();
        System.out.println(user);
        int row = userDao.insert(user);
        System.out.println(row);
    }*/

    @Test
    public void select(){
        User user = userDao.selectById(10L);
        System.out.println(user);
    }

    @Test
    public void selectByIds(){
        List<Long> ids = Arrays.asList(10L,11L,1L);
        List<User> users = userDao.selectBatchIds(ids);
        System.out.println(users);
    }

    @Test
    public void selectByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",1L);
        map.put("name","Jone");
        List<User> user = userDao.selectByMap(map);
        System.out.println(user);
    }

    /**
     * 条件构造器
     */
    @Test
    public void selectByQueryWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","明").lt("age",30);
        List<User> users = userDao.selectList(queryWrapper);
        System.out.println(users);
    }

    /**
     * select指定字段
     */
    @Test
    public void selectByQueryWrapperSupper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name")
                .like("name","Billie")
                .lt("age",30);
        List<User> users = userDao.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
}
