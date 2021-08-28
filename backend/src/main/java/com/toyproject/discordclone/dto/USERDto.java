package com.toyproject.discordclone.dto;

import lombok.Data;

@Data
public class USERDto {
    private int _id;

    private String email;

    private String password;

    private String name;

    private String avatar;

    private Boolean is_enabled;

    private String certified_key;
}
