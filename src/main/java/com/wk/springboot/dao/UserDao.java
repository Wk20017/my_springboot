package com.wk.springboot.dao;

import com.wk.springboot.query.UserQuery;
import com.wk.springboot.vo.User;

import java.util.List;

public interface UserDao {
    User queryUserByUserName(String userName);

    User queryById(Integer id);

    int save(User user);

    int update(User user);

    int delete(Integer userId);

    List<User> selectByParams(UserQuery uSerQuery);
}
