package com.wk.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.wk.springboot.exceptions.ParamException;
import com.wk.springboot.model.ResultInfo;
import com.wk.springboot.query.UserQuery;
import com.wk.springboot.service.UserService;
import com.wk.springboot.vo.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("user/uname/{userName}")
    public User queryUserByUserName(@PathVariable String userName){
        return userService.queryUserByUserName(userName);
    }

    @GetMapping("user/{userId}")
    public User queryUserByUserId(@PathVariable Integer userId){
        return userService.queryUserByUserId(userId);
    }

    @PutMapping("user")
    public ResultInfo saveUser(@RequestBody User user){//requestbody为json格式
        ResultInfo resultInfo = new ResultInfo();
        try {
            userService.saveUser(user);
        }  catch (ParamException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
            e.printStackTrace();
        }   catch (Exception e){
            resultInfo.setCode(300);
            resultInfo.setMsg("用户添加失败！");
            e.printStackTrace();
        }
        return resultInfo;
    }

    @PostMapping("user")
    public ResultInfo updateUser(@RequestBody User user){
        ResultInfo resultInfo = new ResultInfo();
        try {
            userService.updateUser(user);
        }  catch (ParamException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
            e.printStackTrace();
        }   catch (Exception e){
            resultInfo.setCode(300);
            resultInfo.setMsg("用户更新失败！");
            e.printStackTrace();
        }
        return resultInfo;
    }

    @DeleteMapping("user/{userId}")
    public ResultInfo deleteUser(@PathVariable Integer userId){
        ResultInfo resultInfo = new ResultInfo();
        try {
            userService.delete(userId);
        }  catch (ParamException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
            e.printStackTrace();
        }   catch (Exception e){
            resultInfo.setCode(300);
            resultInfo.setMsg("用户删除失败！");
            e.printStackTrace();
        }
        return resultInfo;
    }

    @GetMapping("user/list")
    public PageInfo<User> queryUsersByParams(UserQuery userQuery){
        return userService.queryUsersByParams(userQuery);
    }
}
