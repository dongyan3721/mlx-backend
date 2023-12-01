package com.mlx.genshinimpactcookbook.mapper;

import com.mlx.genshinimpactcookbook.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UsersMapper {
    @Select("select encrypted_password from users where register_email = #{email}")
    public String selectEncryptedPasswordByEmail(String email);

    @Select("select encrypted_password from users where account = #{account}")
    public String selectEncryptedPasswordByAccount(int account);

    // 调用的时候记得要设置默认的账号、默认头像、默认用户名
    @Insert("insert into users (reg_id, nick_name, register_phone, register_email, encrypted_password, account) VALUES (" +
            "uuid(), #{nickName}, null, #{registerEmail}, #{encryptedPassword}, #{account})")
    public int insertNewUserViaEmail(User user);

    @Select("select account from users order by create_time desc limit 1")
    public int selectOneLastCreatedAccount();

    @Update("update users set nick_name = #{nickName} where register_email = #{registerEmail}")
    public int updateUserName(User user);
}
