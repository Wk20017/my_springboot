package com.wk.springboot.dao;

import com.wk.springboot.vo.User;

import java.util.List;

public interface UserDao {
    public User queryUserByUserName(String userName);

    public User queryById(Integer id);

    public int save(User user);

    public int update(User user);

    public int delete(Integer userId);

//    public List<User> selectByParams(UserQuery uSerQuery);
}
