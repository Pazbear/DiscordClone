package com.toyproject.discordclone.dao;

import com.toyproject.discordclone.dto.USERDto;
import org.springframework.stereotype.Repository;

@Repository
public interface USERDao {
    USERDto getUser(String email, String password);
}
