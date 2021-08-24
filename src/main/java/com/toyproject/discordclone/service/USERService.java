package com.toyproject.discordclone.service;

import com.toyproject.discordclone.dao.USERDao;
import com.toyproject.discordclone.dto.USERDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class USERService{

    private final PasswordEncoder passwordEncoder;

    public USERService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private USERDao userDao;

    public USERDto getUser(HttpServletRequest request, String email, String password){
        String encodedPassword = passwordEncoder.encode(password);
        return userDao.getUser(email, encodedPassword);
    }
}
