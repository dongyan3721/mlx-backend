package com.mlx.genshinimpactcookbook.mapper;

import com.mlx.genshinimpactcookbook.domain.User;
import com.mlx.genshinimpactcookbook.utils.RSAUtil;
import com.mlx.genshinimpactcookbook.utils.RandomLengthStringGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersMapperTest {

//    @Value("${backend.public-key}")
//    private String publicKey;
//
//    @Value("${backend.private-key}")
//    private String privateKey;
//
//    @Autowired
//    UsersMapper usersMapper;
//
//    @Test
//    void selectEncryptedPasswordByEmail() {
//    }
//
//    @Test
//    void selectEncryptedPasswordByPhone() {
//    }
//
//    @Test
//    void insertNewUserViaPhone() {
//    }
//
//    @Test
//    void insertNewUserViaEmail() throws Exception {
//        User user = new User();
//        user.setRegisterEmail("2135050509@st.usst.edu.cn");
//        user.setNickName("用户"+ RandomLengthStringGenerator.generateRandomString(6));
//        user.setAccount(usersMapper.selectOneLastCreatedAccount()+1);
//        user.setEncryptedPassword(RSAUtil.encrypt("liruiyys".getBytes(), publicKey));
//        usersMapper.insertNewUserViaEmail(user);
//    }
//
//    @Test
//    void selectOneLastCreatedAccount() {
//        int i = usersMapper.selectOneLastCreatedAccount();
//        System.out.println(i);
//    }
}