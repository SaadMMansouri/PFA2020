package com.emsi.iir5.pfa.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilisateurController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String getEncreptedPassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }
}
