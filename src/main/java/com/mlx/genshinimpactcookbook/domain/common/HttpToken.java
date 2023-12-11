package com.mlx.genshinimpactcookbook.domain.common;


import com.mlx.genshinimpactcookbook.domain.User;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;


@Getter
public class HttpToken extends HttpMessage{
    public HttpToken(){};

    public HttpToken(int code, String msg, User user, String token) {
        super(code, msg);
        this.user = user;
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", user)
                .append("token", token)
                .append("code", code)
                .append("msg", msg)
                .toString();
    }
}
