package com.cafeteria.cafeteria.Aspects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.cafeteria.cafeteria.CustomExceptions.UnauthorizedException;
import com.cafeteria.cafeteria.Utils.JwtTokenUtil;
import com.cafeteria.cafeteria.ViewModels.UserAuthModel;
import com.cafeteria.cafeteria.MyConstants;

@Aspect
@Component
public class TokenValidationAspect {

    private final JwtTokenUtil jwtTokenUtil;
    public TokenValidationAspect(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Before("@annotation(ValidateTokenAdmin)")
    public void validateTokenBeforeMethodExecution(JoinPoint jointPoint) {
        // Get token from the request header or wherever it's present
        // String token = request.getHeader("Authorization");
        Object[] args = jointPoint.getArgs();
        String token = "";
        for (Object arg : args) {
            if (arg instanceof String) {
                token = (String) arg;
            }
        }

        var tokenSubtract  = token.substring(7);

        // Validate token
        if (!jwtTokenUtil.validateTokenAdmin(tokenSubtract)) {
            throw new UnauthorizedException(MyConstants.ERROR_MESSAGE_TOKEN_INVALID);
        }
    }

    @Before("@annotation(ValidateTokenUser)")
    public void validateTokenBeforeMethodExecutionUser(JoinPoint jointPoint) {
       
        Object[] args = jointPoint.getArgs();
        String token = "";
        UserAuthModel user = null;
        for (Object arg : args) {
            if (arg instanceof String) {
                token = (String) arg;
            }
        }
        for (Object arg : args) {
            if (arg instanceof UserAuthModel) {
                user = (UserAuthModel) arg;
            }
        }

        if(user == null){
            throw new UnauthorizedException(MyConstants.ERROR_MESSAGE_TOKEN_INVALID);
        }

        token  = token.substring(7);
        var userJwt= jwtTokenUtil.getUsernameUser(token);

        if (userJwt!=null && !userJwt.equals(user.username)) {
            throw new UnauthorizedException(MyConstants.ERROR_MESSAGE_TOKEN_INVALID);
        }

        // Validate token
        if (!jwtTokenUtil.validateTokenUser(token)) {
            throw new UnauthorizedException(MyConstants.ERROR_MESSAGE_TOKEN_INVALID);
        }
        
    }
}
