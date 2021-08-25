package com.toyproject.discordclone.service;

import com.toyproject.discordclone.dto.USERDto;
import com.toyproject.discordclone.global.ResponseMessage;
import com.toyproject.discordclone.model.DefaultResponse;
import com.toyproject.discordclone.utils.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public DefaultResponse sendMail(String email, String name, String certifiedKey) throws MessagingException{
        DefaultResponse res = new DefaultResponse();
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setFrom("DiscordClone_sejong");
            helper.setTo(email);
            helper.setSubject("discord_clone authentication");
            helper.setText(StringUtils.getMailContent(email, name, certifiedKey), true);

            javaMailSender.send(mimeMessage);
            res.setSuccess(true);
            res.setMsg(ResponseMessage.SEND_MAIL_SUCCESS);
        }catch(Exception e){
            res.setSuccess(false);
            res.setMsg(ResponseMessage.SEND_MAIL_FAIL);
        }
        return res;
    }

}
