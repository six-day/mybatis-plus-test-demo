package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {
}
