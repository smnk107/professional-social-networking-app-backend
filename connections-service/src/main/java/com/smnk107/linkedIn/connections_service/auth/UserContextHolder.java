package com.smnk107.linkedIn.connections_service.auth;


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
