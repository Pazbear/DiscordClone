package com.toyproject.discordclone.service;

import com.toyproject.discordclone.model.DefaultResponse;

public interface SERVERService {
    public DefaultResponse createServer(String name, int host);
    public DefaultResponse getServerList(int host);
}
