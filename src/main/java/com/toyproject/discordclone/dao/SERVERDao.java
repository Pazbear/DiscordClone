package com.toyproject.discordclone.dao;

import com.toyproject.discordclone.dto.SERVERDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SERVERDao {
    void createServer(String name, int host);
    List<SERVERDto> getServerList(int host);
}
