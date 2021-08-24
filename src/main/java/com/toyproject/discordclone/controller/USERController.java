package com.toyproject.discordclone.controller;

import com.toyproject.discordclone.dao.USERDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class USERController {

    @Autowired
    private USERDao userDao;

    @GetMapping("/get")
    public ResponseEntity GetUser(String email, String password){
        return new ResponseEntity(userDao.getUser(email, password), HttpStatus.OK);
    }
}
