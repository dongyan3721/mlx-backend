package com.mlx.genshinimpactcookbook.domain.common;


import java.util.StringJoiner;

public class HttpToken extends HttpMessage{
    public HttpToken(){};

    public HttpToken(int code, String msg, String token) {
        super(code, msg);
        this.token = token;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HttpToken.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("msg='" + msg + "'")
                .add("token='" + token + "'")
                .toString();
    }
}
