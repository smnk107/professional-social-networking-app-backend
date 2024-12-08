package com.smnk107.linkedIn.posts_service.auth;

import org.springframework.stereotype.Component;


public class UserContextHolder {

    private static final ThreadLocal<Long> currentUserid = new ThreadLocal<>();

    public static Long getCurrentUserid()
    {
        return currentUserid.get();
    }

    public static void setCurrentUserid(Long userid)
    {
        currentUserid.set(userid);
    }

    public static void clear()
    {
        currentUserid.remove();
    }




}
