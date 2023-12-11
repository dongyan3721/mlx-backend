package com.mlx.genshinimpactcookbook.controller;

import com.mlx.genshinimpactcookbook.annotation.AccessWithoutRecognize;
import com.mlx.genshinimpactcookbook.domain.User;
import com.mlx.genshinimpactcookbook.domain.common.HttpMessage;
import com.mlx.genshinimpactcookbook.domain.common.HttpStatus;
import com.mlx.genshinimpactcookbook.domain.common.HttpToken;
import com.mlx.genshinimpactcookbook.exception.ServiceException;
import com.mlx.genshinimpactcookbook.service.IUserService;
import com.mlx.genshinimpactcookbook.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 用户通过邮箱登录
     * @param user 自动封装的登录信息
     * @return 登录成功或失败消息
     * 登录不需要jwt验证
     */
    @PostMapping("/email")
    @AccessWithoutRecognize
    public HttpMessage userLoginViaEmail(@RequestBody User user){
        System.out.println(user);
        if(userService.checkValidityViaEmail(user)){
            System.out.println(userService.selectUserByRegisterEmail(user.getRegisterEmail()));
            return new HttpToken(HttpStatus.SUCCESS, "success", userService.selectUserByRegisterEmail(user.getRegisterEmail()), JwtTokenUtil.generateEmailToken(user));
        }else{
            return new HttpMessage(HttpStatus.FORBIDDEN, "wrong account or password!");
        }
    }

    /**
     * 用户通过账户登录
     * @param user 自动封装的登录信息
     * @return 登录成功或失败消息
     * 登录不需要jwt验证
     */
    @PostMapping("/account")
    @AccessWithoutRecognize
    public HttpMessage userLoginViaAccount(@RequestBody User user){
        if(userService.checkValidityViaAccount(user)){
            return new HttpToken(HttpStatus.SUCCESS, "success", userService.selectUserByAccount(user), JwtTokenUtil.generateAccountToken(user));
        }else{
            return new HttpMessage(HttpStatus.FORBIDDEN, "wrong account or password!");
        }
    }

    /**
     * 用户注册
     * @param user 自动封装的用户信息
     * @return 注册成功或失败消息
     * 注册不需要jwt验证
     */
    @PostMapping("/register")
    @AccessWithoutRecognize
    public HttpMessage userRegisterViaEmail(@RequestBody User user){
        System.out.println(user);
        try {
            userService.insertNewUserViaEmail(user);
            return new HttpMessage(HttpStatus.SUCCESS, "success");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new ServiceException("重复注册！", HttpStatus.SUCCESS);
        }
    }


    /**
     * 修改用户名需要jwt验证，学完了再修改
     */
    @PutMapping("/modify")
    public HttpMessage userModifyUsername(@RequestBody User user){
        userService.updateUserName(user);
        return new HttpMessage(HttpStatus.SUCCESS, "success");
    }
}
