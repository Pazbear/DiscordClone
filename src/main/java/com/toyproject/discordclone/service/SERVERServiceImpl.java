package com.toyproject.discordclone.service;

import com.toyproject.discordclone.dao.SERVERDao;
import com.toyproject.discordclone.global.ResponseMessage;
import com.toyproject.discordclone.model.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SERVERServiceImpl implements SERVERService {

    @Autowired
    private SERVERDao serverDao;

    public DefaultResponse createServer(String name, int host){
        DefaultResponse res = new DefaultResponse();
        try{
            serverDao.createServer(name, host);
            res.setSuccess(true);
            res.setMsg(ResponseMessage.CREATED_SERVER);
        }catch(Exception e){
            res.setSuccess(false);
            res.setMsg(ResponseMessage.CREATED_SERVER_FAIL);
        }
        return res;
    }
}
