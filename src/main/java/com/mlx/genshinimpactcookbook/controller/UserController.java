package com.mlx.genshinimpactcookbook.controller;

import com.mlx.genshinimpactcookbook.domain.User;
import com.mlx.genshinimpactcookbook.domain.common.HttpMessage;
import com.mlx.genshinimpactcookbook.domain.common.HttpStatus;
import com.mlx.genshinimpactcookbook.domain.common.HttpToken;
import com.mlx.genshinimpactcookbook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/email")
    public HttpMessage userLoginViaEmail(User user){
        if(userService.checkValidityViaEmail(user)){
            return new HttpToken(HttpStatus.SUCCESS, "success", "000");
        }else{
            return new HttpMessage(HttpStatus.FORBIDDEN, "wrong account or password!");
        }
    }

    @PostMapping("/account")
    public HttpMessage userLoginViaAccount(User user){
        if(userService.checkValidityViaAccount(user)){
            return new HttpToken(HttpStatus.SUCCESS, "success", "000");
        }else{
            return new HttpMessage(HttpStatus.FORBIDDEN, "wrong account or password!");
        }
    }

    @PostMapping("/register")
    public HttpMessage userRegisterViaEmail(User user){
        userService.insertNewUserViaEmail(user);
        return new HttpMessage(HttpStatus.SUCCESS, "success");
    }


    /**
     * 修改用户名需要jwt验证，学完了再修改
     */
    @PutMapping("/modify")
    public HttpMessage userModifyUsername(User user){
        userService.updateUserName(user);
        return new HttpMessage(HttpStatus.SUCCESS, "success");
    }
}
