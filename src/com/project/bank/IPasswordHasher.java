package com.project.bank;

public interface IPasswordHasher {
    public String encypt(String pass);
    public boolean check(String pass);

}
