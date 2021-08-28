package com.toyproject.discordclone.controller;

import com.toyproject.discordclone.dao.USERDao;
import com.toyproject.discordclone.dto.USERDto;
import com.toyproject.discordclone.jwt.JwtUtil;
import com.toyproject.discordclone.model.DefaultResponse;
import com.toyproject.discordclone.service.MailService;
import com.toyproject.discordclone.service.USERService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class USERController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private USERService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/signup")
    public DefaultResponse SignUp(@RequestBody USERDto userDto){
        System.out.println(userDto);
        return userService.signUp(userDto.getEmail(), userDto.getPassword(),userDto.getName(),userDto.getAvatar());
    }

    @PostMapping("/signin")
    public DefaultResponse SignIn(@RequestBody USERDto userDto){
        System.out.println(userDto);
        DefaultResponse res = userService.signIn(userDto.getEmail(), userDto.getPassword());
        res.setToken(jwtUtil.generateToken(userDto));
        return res;
    }

    @PostMapping("/mail/auth")
    public DefaultResponse sendAuthMail(@RequestBody USERDto userDto) throws MessagingException{
        System.out.println(userDto);
        return mailService.sendMail(userDto.getEmail(), userDto.getName(), userDto.getCertified_key());
    }

    @GetMapping("/mail/certified")
    @Transactional
    public DefaultResponse certifiedAuthMail(USERDto userDto) throws MessagingException{
        System.out.println(userDto);
        return userService.setUserEnabled(userDto.getEmail(), userDto.getCertified_key());
    }

}
