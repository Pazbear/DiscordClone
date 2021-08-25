package com.toyproject.discordclone.service;

import com.toyproject.discordclone.dao.USERDao;
import com.toyproject.discordclone.dto.USERDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class USERServiceImpl implements USERService{

    private final PasswordEncoder passwordEncoder;
    private USERDao userDao;

    public USERDto getUser(String email, String password){
        String encodedPassword = passwordEncoder.encode(password);
        return userDao.getUser(email, encodedPassword);
    }
}
