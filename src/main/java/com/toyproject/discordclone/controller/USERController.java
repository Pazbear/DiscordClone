package com.toyproject.discordclone.controller;

import com.toyproject.discordclone.dao.USERDao;
import com.toyproject.discordclone.dto.USERDto;
import com.toyproject.discordclone.model.DefaultResponse;
import com.toyproject.discordclone.service.USERService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class USERController {

    @Autowired
    private USERService userService;

    @PostMapping("/create")
    public DefaultResponse CreateUser(@RequestBody USERDto userDto){
        System.out.println(userDto);
        return userService.createUser(userDto.getEmail(), userDto.getPassword(),userDto.getName(),userDto.getAvatar());
    }

    @PostMapping("/get")
    public DefaultResponse GetUser(@RequestBody USERDto userDto){
        System.out.println(userDto);
        return userService.getUser(userDto.getEmail(), userDto.getPassword());
    }
}
