package com.wk.springboot.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wk.springboot.dao.UserDao;
import com.wk.springboot.query.UserQuery;
import com.wk.springboot.utils.AssertUtil;
import com.wk.springboot.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired(required = false)
    private UserDao userDao;

    public User queryUserByUserName(String userName){
        return userDao.queryUserByUserName(userName);
    }

    public User queryUserByUserId(Integer id){
        return userDao.queryById(id);
    }

    public void saveUser(User user){
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPwd()), "用户密码不能为空！");
        AssertUtil.isTrue(null != userDao.queryUserByUserName(user.getUserName()), "该用户已存在！");
        AssertUtil.isTrue(userDao.save(user)<1, "用户记录添加失败!");
    }

    public void updateUser(User user){
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPwd()), "用户密码不能为空！");
        AssertUtil.isTrue(null==userDao.queryById(user.getId()), "该用户不存在！");
//        用户名唯一
        User user1 = userDao.queryById(user.getId());
        AssertUtil.isTrue(null!=user1.getUserName() && !user1.getId().equals(user.getId()), "用户名不唯一！");
        AssertUtil.isTrue(userDao.update(user)<1, "更新失败！");
    }

    public void delete(Integer userId){
        AssertUtil.isTrue(null==userId || null == userDao.queryById(userId), "该用户不存在!");
        AssertUtil.isTrue(userDao.delete(userId)<1, "用户删除失败！");
    }

    public PageInfo<User> queryUsersByParams(UserQuery userQuery){
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        List<User> users = userDao.selectByParams(userQuery);
        return new PageInfo<>(users);
    }
}
