package com.example.fypqrcode.http.requests;

public class LoginRequest {
    public String email;
    public String password;

    public LoginRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}
