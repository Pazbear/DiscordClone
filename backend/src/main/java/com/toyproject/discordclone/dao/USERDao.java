package com.toyproject.discordclone.dao;

import com.toyproject.discordclone.dto.USERDto;
import org.springframework.stereotype.Repository;

@Repository
public interface USERDao {
    void createUser(String email, String password, String name, String avatar, String certified_key);
    USERDto getUser(String email);
    void setUserEnabled(String email, String certified_key);
}
