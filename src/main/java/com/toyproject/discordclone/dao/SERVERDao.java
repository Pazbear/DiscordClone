package com.toyproject.discordclone.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface SERVERDao {
    void createServer(String name, int host);
}
