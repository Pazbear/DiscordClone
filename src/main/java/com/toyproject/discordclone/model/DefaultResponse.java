package com.toyproject.discordclone.model;

import lombok.Data;

@Data
public class DefaultResponse {

    private boolean success;

    private Object result;

    private String msg;

}
