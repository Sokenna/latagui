package com.latagui.bean;

import java.io.Serializable;
import java.util.Date;
/***
 *
 *@User实体类
 *@throws
 *@author 77912204@qq.com
 *@date 2019/12/19 0019 1:00
 *
 *
 */
public class User implements Serializable {

    private Integer user_id;    //用户id

    private String user_name;   //用户名

    private String real_name;   //真实姓名

    private String password;    //密码

    private String gender;      //性别

    private Date birthday;      //生日

    private Integer age;        //年龄

    private String valid_flag;  //有效标志

    private String lock_flag;   //是否锁定

    private int error_num;   //错误次数

    private String token;       //token

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getError_num() {
        return error_num;
    }

    public void setError_num(int error_num) {
        this.error_num = error_num;
    }

    public String getLock_flag() {
        return lock_flag;
    }

    public void setLock_flag(String lock_flag) {
        this.lock_flag = lock_flag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getValid_flag() {
        return valid_flag;
    }

    public void setValid_flag(String valid_flag) {
        this.valid_flag = valid_flag == null ? null : valid_flag.trim();
    }
}