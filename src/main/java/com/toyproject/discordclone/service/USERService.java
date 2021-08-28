package com.toyproject.discordclone.service;


import com.toyproject.discordclone.dto.USERDto;
import com.toyproject.discordclone.model.DefaultResponse;

public interface USERService{
    public DefaultResponse signUp(String email, String password, String name, String avatar);
    public DefaultResponse signIn(String email, String password);
    public DefaultResponse setUserEnabled(String email, String certified_key);

    //
    public USERDto getUserByEmail(String email);
}
