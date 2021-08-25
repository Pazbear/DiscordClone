package com.toyproject.discordclone.controller;

import com.toyproject.discordclone.dto.SERVERDto;
import com.toyproject.discordclone.model.DefaultResponse;
import com.toyproject.discordclone.service.SERVERService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/server")
public class SERVERController {

    @Autowired
    private SERVERService serverService;

    @PostMapping("/create")
    public DefaultResponse CreateServer(@RequestBody SERVERDto serverDto){
        System.out.println(serverDto);
        return serverService.createServer(serverDto.getName(), serverDto.getHost());
    }
}
