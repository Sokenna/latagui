package com.latagui.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.latagui.bean.User;
import com.latagui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("system")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("getUserlist")
    public  List<User> getUserList(){
     List<User> list =  userService.getUserList();
     if (list.size()>0){
         System.out.printf("有用户");
     }else {
         System.out.printf("无用户");
     }
     return list;
    }
}
