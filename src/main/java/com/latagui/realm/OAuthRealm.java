package com.latagui.realm;

import com.latagui.bean.User;
import com.latagui.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OAuthRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }
    @Autowired
    private UserService userService;
   /* @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }*/

    /**
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)principals.getPrimaryPrincipal();
        //Integer user_id = user.getUser_id();

        return null;
    }
    /**
     *
     *@TODO
     *@param authenticationToken
     *@return {{@link AuthenticationInfo}}
     *@throws
     *@author 77912204@qq.com
     *@date 2019/12/18 0018 7:25
     *
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String accessToken = (String) authenticationToken.getPrincipal();
        //根据accessToken,查询用户信息
        //编写shiro的验证逻辑，判断用户名和密码

        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = (String) token.getPrincipal();//得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        User user = userService.getUserByUserName(token.getUsername());
        if (user==null ){
            //用户名不存在
            return null;//shiro 低层会抛出UnknowAccountException
        }else if (user.getValid_flag().equals("0")){
            throw new DisabledAccountException("账号已禁用！");//禁用的账号
        }else if(user.getError_num()>5){
            throw  new ExcessiveAttemptsException("错误次数过多！");//错误次数过多
        }else if(user.getLock_flag().equals("1")){
            throw  new LockedAccountException("账号已锁定！");//锁定的账号
        }else if(password.equals(user.getPassword())){
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //2.判断密码

        return new SimpleAuthenticationInfo(userName,user.getPassword(),user.getReal_name()!=null?user.getReal_name():user.getUser_name());
    }
}
