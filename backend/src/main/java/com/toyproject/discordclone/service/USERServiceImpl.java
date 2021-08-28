package com.toyproject.discordclone.service;

import com.toyproject.discordclone.dao.USERDao;
import com.toyproject.discordclone.dto.USERDto;
import com.toyproject.discordclone.global.ResponseMessage;
import com.toyproject.discordclone.jwt.JwtUtil;
import com.toyproject.discordclone.model.DefaultResponse;
import com.toyproject.discordclone.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class USERServiceImpl implements USERService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private USERDao userDao;

    public DefaultResponse signUp(String email, String password, String name, String avatar){
        String encodedPassword = passwordEncoder.encode(password);
        DefaultResponse res = new DefaultResponse();
        try {
            String certifiedKey = StringUtil.generateCertifiedKey();
            userDao.createUser(email, encodedPassword, name, avatar, certifiedKey);
            res.setSuccess(true);
        }catch (Exception e){
            res.setSuccess(false);
            res.setMsg(ResponseMessage.DB_ERROR);
        }
        return res;
    }
    public DefaultResponse signIn(String email, String password){
        DefaultResponse res = new DefaultResponse();
        try {
            USERDto user = userDao.getUser(email);
            if(user == null){
                res.setSuccess(false);
                res.setMsg(ResponseMessage.NOT_FOUND_USER);
                return res;
            }else{
                if(passwordEncoder.matches(password, user.getPassword())){
                    res.setSuccess(true);
                    res.setResult(user);
                    return res;
                }else{
                    res.setSuccess(false);
                    res.setMsg(ResponseMessage.LOGIN_FAIL);
                    return res;
                }
            }
        }catch (Exception e){
            res.setSuccess(false);
            res.setMsg(ResponseMessage.DB_ERROR);
        }
        return res;
    }

    public DefaultResponse setUserEnabled(String email, String certified_key){
        DefaultResponse res = new DefaultResponse();
        try{
            userDao.setUserEnabled(email,certified_key);
            res.setSuccess(true);
        }catch(Exception e){
            res.setSuccess(false);
            res.setMsg(ResponseMessage.DB_ERROR);
        }
        return res;
    }

    //

    public USERDto getUserByEmail(String email){
        try{
            USERDto user = userDao.getUser(email);
            return user;
        }catch(Exception e){
            return null;
        }
    }
}
