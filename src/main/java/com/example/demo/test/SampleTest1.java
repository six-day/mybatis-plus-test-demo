package com.example.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.utils.DozerUtil;
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

    /**
     * 分页查询
     */
    @Test
    public void testPage(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = new Page<User>(1,2);
        IPage<User> iPage = userDao.selectPage(page,queryWrapper);
        iPage.getRecords().forEach(System.out::println);
    }

    /**
     * 分页查询，返回的record是map
     */
    @Test
    public void testPageMap(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = new Page<User>(1,2);
        IPage<Map<String,Object>> iPage = userDao.selectMapsPage(page,queryWrapper);
        iPage.getRecords().forEach(System.out::println);
    }

    /**
     * 不执行count
     */
    @Test
    public void testPageMap2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = new Page<User>(1,2,false);
        IPage<Map<String,Object>> iPage = userDao.selectMapsPage(page,queryWrapper);
        iPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void testMyPage(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = new Page<User>(1,4);
        IPage<User> iPage = userDao.selectUserPage(page,queryWrapper);
        iPage.getRecords().forEach(System.out::println);
    }

    /**
     * 实体作为条件构造器参数
     */
    @Test
    public void selectByQueryWrapperEntity(){
        User user = User.builder().age(20).name("c").build();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
//        queryWrapper.like("name","明").lt("age",30);
        List<User> users = userDao.selectList(queryWrapper);
        System.out.println(users);
    }

    /**
     * 根据map查询（缺点，是全等，即时配置了like也不行
     */
    @Test
    public void selectByAllEq(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","c");
        map.put("age",20);
        queryWrapper.allEq(map);
        List<User> users = userDao.selectList(queryWrapper);
        System.out.println(users);
    }

    /**
     * 只返回select的第一个字段
     */
    @Test
    public void selectByWrapperObj(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","明").lt("age",30);
        List<Object> users = userDao.selectObjs(queryWrapper);
        System.out.println(users);
    }


    /**
     * lambda条件构造器（根据实体字段名匹配）
     */
    @Test
    public void selectByLambda(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName,"c").eq(User::getAge,20);
        List<User> users = userDao.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }

    /**
     * name like '%c%' and (age<40 or email is not null)
     */
    @Test
    public void selectByLambda2(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .like(User::getName,"a")
                .and(lqw->lqw.lt(User::getAge,40)
                        .or()
                        .isNotNull(User::getEmail));
        List<User> users = userDao.selectList(lambdaQueryWrapper);
        System.out.println(users);
    }

    /**
     * 更新
     */
    @Test
    public void updateByWrapper(){
        //where
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("name","Jack");
        //set
        User user = User.builder().email("qqq@qq.com").name("李明").build();
        userDao.update(user,updateWrapper);
    }

    /**
     * Lambda更新
     */
    @Test
    public void updateByLambda(){
        //where
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(User::getName,"liming");
        //set
        User user = User.builder().email("qqq@qq.com").name("李明").build();
        userDao.update(user,updateWrapper);
    }
    @Test
    public void insertTest(){
        UserDto dto = UserDto.builder().age(10).id(22L).name("test").email("test@qq.com").sex("1").build();
        userDao.insert(DozerUtil.map(dto,User.class));
    }
}
