package com.latagui.service;

import com.latagui.bean.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getUserByUserName(String userName);
}
