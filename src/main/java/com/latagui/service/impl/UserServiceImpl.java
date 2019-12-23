package com.latagui.service.impl;

import com.latagui.bean.User;
import com.latagui.dao.UserMapper;
import com.latagui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getUserList() {
        List<User> list = userMapper.selectByExample();
        return list;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

}
