package com.latagui.dao;

import com.latagui.bean.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {


    //int deleteByPrimaryKey(Integer user_id);

    //int insert(User record);

    //int insertSelective(User record);

    List<User> selectByExample();

    User getUserByUserName(@Param("userName")String userName);
}