package com.smnk107.linkedIn.user_service.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordUtil {

    public String hashPassword(String password)
    {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public boolean matchPassword(String HashedPw, String givenPw)
    {
        return BCrypt.checkpw(givenPw,HashedPw);
    }
}
