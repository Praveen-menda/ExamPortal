package com.exam.service.impl;

public class UserFoundException extends Exception {
    public UserFoundException()
    {
        super("User Alredy There in DB");
    }
    public UserFoundException(String msg)
    {
        super(msg);
    }
}
