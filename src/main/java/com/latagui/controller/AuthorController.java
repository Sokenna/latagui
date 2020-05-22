package com.latagui.controller;

import com.latagui.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    @RequestMapping("/login")
    public String login(User user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken  = new UsernamePasswordToken(user.getUser_name(),user.getPassword());

        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){

            e.printStackTrace();
            return "用户名或密码错误！";
        }catch (LockedAccountException e){
            e.printStackTrace();
            return "账号已锁定！";
        } catch (DisabledAccountException e){
            e.printStackTrace();
            return "禁用的账号！";
        }catch (ExcessiveAttemptsException e){
            e.printStackTrace();
            return "错误次数过多！";
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return "错误的凭证！";
        }
        return "登录成功！";
    }
    @RequestMapping("/logout")
    public String  logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "退出成功！";
    }


}
