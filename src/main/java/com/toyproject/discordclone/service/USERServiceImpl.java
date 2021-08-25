package com.toyproject.discordclone.service;

import com.toyproject.discordclone.dao.USERDao;
import com.toyproject.discordclone.dto.USERDto;
import com.toyproject.discordclone.model.DefaultResponse;
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

    public DefaultResponse createUser(String email, String password, String name, String avatar){
        String encodedPassword = passwordEncoder.encode(password);
        DefaultResponse res = new DefaultResponse();
        try {
            userDao.createUser(email, encodedPassword, name, avatar);
            res.setSuccess(true);
        }catch (Exception e){
            res.setSuccess(false);
            res.setMsg("유저 생성 실패");
        }
        return res;
    }
    public DefaultResponse getUser(String email, String password){
        DefaultResponse res = new DefaultResponse();
        try {
            USERDto user = userDao.getUser(email);
            if(user == null){
                res.setSuccess(false);
                res.setMsg("해당 유저 없음");
                return res;
            }else{
                if(passwordEncoder.matches(password, user.getPassword())){
                    res.setSuccess(true);
                    res.setResult(user);
                    return res;
                }else{
                    res.setSuccess(false);
                    res.setMsg("비밀번호 오류");
                    return res;
                }
            }
        }catch (Exception e){
            res.setSuccess(false);
            res.setMsg("유저 가져오기 실패");
        }
        return res;
    }
}
