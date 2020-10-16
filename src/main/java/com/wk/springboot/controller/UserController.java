package com.wk.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.wk.springboot.exceptions.ParamException;
import com.wk.springboot.model.ResultInfo;
import com.wk.springboot.query.UserQuery;
import com.wk.springboot.service.UserService;
import com.wk.springboot.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户模块管理")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("user/uname/{userName}")
    @ApiOperation(value="用户模块-根据用户名查询用户记录")
    @ApiImplicitParam(name = "userName",value = "查询参数",required = true,paramType = "path")
    public User queryUserByUserName(@PathVariable String userName){
        System.out.println("用户名->"+userName);
        return userService.queryUserByUserName(userName);
    }

    @GetMapping("user/{userId}")
    @ApiOperation(value="用户模块-根据用户id查询用户记录")
    public User queryUserByUserId(@PathVariable Integer userId){
        return userService.queryUserByUserId(userId);
    }

    @PutMapping("user")
    @ApiOperation(value="用户模块-保存新用户")
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
    @ApiOperation(value="用户模块-更新用户信息")
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
    @ApiOperation(value="用户模块-删除用户")
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
    @ApiOperation(value="用户模块-用户列表查询")
    public PageInfo<User> queryUsersByParams(UserQuery userQuery){
        return userService.queryUsersByParams(userQuery);
    }
}
