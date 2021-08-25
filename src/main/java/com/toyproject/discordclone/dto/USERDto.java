package com.toyproject.discordclone.dto;

import lombok.Data;

@Data
public class USERDto {
    private int _id;

    private String email;

    private String password;

    private String name;

    private String avatar;
}
