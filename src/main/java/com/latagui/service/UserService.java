package com.latagui.service;

import com.latagui.bean.User;
import com.latagui.common.ResponseModel;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getUserByUserName(String userName);
    ResponseModel saveUser(User user);
}
