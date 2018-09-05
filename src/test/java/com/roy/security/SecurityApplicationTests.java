package com.roy.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.File;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
    DataSource dataSource;

	@Test
	public void test01() throws SQLException {
        System.out.println(dataSource.getConnection());

        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

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
