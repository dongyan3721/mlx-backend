package com.mlx.genshinimpactcookbook.domain.common;


import com.mlx.genshinimpactcookbook.domain.User;

import java.util.StringJoiner;

public class HttpToken extends HttpMessage{
    public HttpToken(){};

    public HttpToken(int code, String msg, User user, String token) {
        super(code, msg);
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    @Override
    public String toString() {
        return "HttpToken{" +
                "user=" + user +
                ", token='" + token + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
