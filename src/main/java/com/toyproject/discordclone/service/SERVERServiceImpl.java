package com.toyproject.discordclone.service;

import com.toyproject.discordclone.dao.SERVERDao;
import com.toyproject.discordclone.dto.SERVERDto;
import com.toyproject.discordclone.global.ResponseMessage;
import com.toyproject.discordclone.model.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            System.out.println(e.toString());
            res.setSuccess(false);
            res.setMsg(ResponseMessage.CREATED_SERVER_FAIL);
        }
        return res;
    }

    public DefaultResponse getServerList(int host){
        DefaultResponse res = new DefaultResponse();
        try{
            List<SERVERDto> servers = serverDao.getServerList(host);
            res.setSuccess(true);
            res.setResult(servers);
        }catch(Exception e){
            System.out.println(e.toString());
            res.setSuccess(false);
            res.setMsg(ResponseMessage.GET_SERVER_LIST_FAIL);
        }
        return res;
    }
}
