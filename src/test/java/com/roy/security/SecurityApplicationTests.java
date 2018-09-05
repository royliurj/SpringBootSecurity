package com.roy.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

	@Autowired
	JavaMailSender javaMailSender;

		@Test
	public void contextLoads() {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //设置邮件
        mailMessage.setSubject("今晚开会");
        mailMessage.setText("19:00开会");
        mailMessage.setTo("liuranjun@sinopatho.com");
        mailMessage.setFrom("ranjun_liu@126.com");

        javaMailSender.send(mailMessage);
	}

	@Test
	public void test() throws MessagingException {

	    //创建一个复杂邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //设置邮件
        helper.setSubject("今晚开会");
        helper.setText("<b>19:00开会</b>");
        helper.setTo("liuranjun@sinopatho.com");
        helper.setFrom("ranjun_liu@126.com");

        //上传附件
        helper.addAttachment("1.jpg", new File("C:\\Users\\admin\\Pictures\\1.png"));

        javaMailSender.send(mimeMessage);
    }
}
