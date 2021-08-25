package com.toyproject.discordclone.service;


import com.toyproject.discordclone.dto.USERDto;

public interface USERService{
    public USERDto getUser(String email, String password);
}
