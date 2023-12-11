package com.mlx.genshinimpactcookbook.service;

import com.mlx.genshinimpactcookbook.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLIntegrityConstraintViolationException;

public interface IUserService {
    public boolean checkValidityViaEmail(User user);

    public boolean checkValidityViaAccount(User user);

    // 调用的时候记得要设置默认的账号、默认头像、默认用户名
    public int insertNewUserViaEmail(User user) throws SQLIntegrityConstraintViolationException;

    public int selectOneLastCreatedAccount();

    public int updateUserName(User user);

    public User selectUserByRegisterEmail(String email);

    public User selectUserByAccount(User user);
}
