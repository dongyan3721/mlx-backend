package com.mlx.genshinimpactcookbook.service.impls;

import com.mlx.genshinimpactcookbook.domain.User;
import com.mlx.genshinimpactcookbook.mapper.UsersMapper;
import com.mlx.genshinimpactcookbook.service.IUserService;
import com.mlx.genshinimpactcookbook.utils.RSAUtil;
import com.mlx.genshinimpactcookbook.utils.RandomLengthStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserService implements IUserService {

    @Value("${backend.private-key}")
    private String privateKey;

    @Autowired
    UsersMapper usersMapper;

    // 检验用户通过邮箱登陆的合法性
    @Override
    public boolean checkValidityViaEmail(User user) {
        try {
            return RSAUtil.decrypt(user.getEncryptedPassword(), privateKey).equals(RSAUtil.decrypt(usersMapper.selectEncryptedPasswordByEmail(user.getRegisterEmail()), privateKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 检验用户通过账户登录的合法性
    @Override
    public boolean checkValidityViaAccount(User user) {
        try {
            return RSAUtil.decrypt(user.getEncryptedPassword(), privateKey).equals(RSAUtil.decrypt(usersMapper.selectEncryptedPasswordByAccount(user.getAccount()), privateKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 用户通过邮箱注册
     * @param user 前台传递的user类型对象
     * @return 成功返回1
     * 使用@Transactional保证account唯一
     */
    @Override
    @Transactional
    public int insertNewUserViaEmail(User user) throws SQLIntegrityConstraintViolationException {
        if(usersMapper.selectExistEmail(user)!=null){
            throw new SQLIntegrityConstraintViolationException();
        }else{
            user.setAccount(usersMapper.selectOneLastCreatedAccount()+1);
            user.setNickName("用户"+ RandomLengthStringGenerator.generateRandomString(6));
            return usersMapper.insertNewUserViaEmail(user);
        }
    }

    // 与用户注册配套使用，写在这里提醒我下有这个接口
    @Override
    public int selectOneLastCreatedAccount() {
        return usersMapper.selectOneLastCreatedAccount();
    }

    // 更新用户信息
    @Override
    public int updateUserName(User user) {
        return usersMapper.updateUserName(user);
    }
}
