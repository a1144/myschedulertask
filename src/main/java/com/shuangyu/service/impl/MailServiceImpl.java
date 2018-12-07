package com.shuangyu.service.impl;

import com.shuangyu.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

  private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

  @Autowired
  private JavaMailSender javaMailSender;
  @Value("$(mail.fromMail.addr)")
  private String from;

  @Override
  public void sendSimpleMail(String to, String subject, String content) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(from);
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(content);
    try {
      javaMailSender.send(simpleMailMessage);
      logger.info("发送成功");
    } catch (Exception e) {
      logger.info("发送失败");
      e.printStackTrace();
    }

  }
}
