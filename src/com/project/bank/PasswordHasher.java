package com.project.bank;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PasswordHasher implements IPasswordHasher{
    @Override
    public String encypt(String pass) {
        return "";
    }

    @Override
    public boolean check(String pass) {
        return false;
    }
}
