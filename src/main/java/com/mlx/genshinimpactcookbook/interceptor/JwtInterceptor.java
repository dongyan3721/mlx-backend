package com.mlx.genshinimpactcookbook.interceptor;

import com.mlx.genshinimpactcookbook.annotation.AccessWithoutRecognize;
import com.mlx.genshinimpactcookbook.domain.common.HttpStatus;
import com.mlx.genshinimpactcookbook.exception.ServiceException;
import com.mlx.genshinimpactcookbook.mapper.UsersMapper;
import com.mlx.genshinimpactcookbook.utils.JwtTokenUtil;
import com.mlx.genshinimpactcookbook.utils.RSAUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Santa Antilles
 * @date 2023/12/2-13:53
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UsersMapper usersMapper;


    @Value("${backend.private-key}")
    private String privateKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(handler instanceof HandlerMethod){
            AccessWithoutRecognize annotation = ((HandlerMethod) handler).getMethodAnnotation(AccessWithoutRecognize.class);
            if(annotation!=null){
                return true;
            }
        }

        HashMap<String, Object> map = JwtTokenUtil.parseToken(token);

        // token无效，抛未认证异常
        if((boolean) map.get("valid")){
            // token超时，抛超时异常
            if((new Date()).before(((Date)map.get("expired")))){
                // 没有email或者account的信息，抛未认证异常
                if((boolean) map.get("continue")){
                    // 通过Email生成的token
                    if(map.get("email")!=null){
                        String password = (String) map.get("password");
                        String email = (String) map.get("email");
                        // 账号密码正确，放行
                        if(RSAUtil.decrypt(password, privateKey).equals(RSAUtil.decrypt(usersMapper.selectEncryptedPasswordByEmail(email), privateKey))){
                            return true;
                        }else{
                            throw new ServiceException("invalid token!", HttpStatus.UNAUTHORIZED);
                        }
                    }
                    // 通过account生成的token
                    else{
                        String password = (String) map.get("password");
                        int account = Integer.parseInt((String) map.get("account"));
                        // 账号密码正确，放行
                        if(RSAUtil.decrypt(password, privateKey).equals(RSAUtil.decrypt(usersMapper.selectEncryptedPasswordByAccount(account), privateKey))){
                            return true;
                        }else{
                            throw new ServiceException("invalid token!", HttpStatus.UNAUTHORIZED);
                        }
                    }
                }else{
                    throw new ServiceException("invalid token!", HttpStatus.UNAUTHORIZED);
                }
            }else{
                throw new ServiceException("time expired!", HttpStatus.FORBIDDEN);
            }
        }else{
            throw new ServiceException("invalid token!", HttpStatus.UNAUTHORIZED);
        }
    }
}
