package com.mlx.genshinimpactcookbook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String regId;
    private String nickName;
    private String registerPhone;
    private String registerEmail;
    private String encryptedPassword;
    private Integer account;
    private Date createTime;
}
