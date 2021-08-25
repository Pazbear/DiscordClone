package com.toyproject.discordclone.service;


import com.toyproject.discordclone.dto.USERDto;
import com.toyproject.discordclone.model.DefaultResponse;

public interface USERService{
    public DefaultResponse createUser(String email, String password, String name, String avatar);
    public DefaultResponse getUser(String email, String password);
}
