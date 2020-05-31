package ru.egprojects.sw1_springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(8);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${spring.mail.username}")
    private String mail;

    public String getMail() {
        return mail;
    }

    @Value("${spring.mail.password}")
    private String pass;

    public String getPass() {
        return pass;
    }

    @Value("${spring.mail.host}")
    private String host;

    public String getHost() {
        return host;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(getMail());
        javaMailSender.setPassword(getPass());
        javaMailSender.setHost(getHost());
        javaMailSender.setPort(587);

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        javaMailSender.setJavaMailProperties(props);

        return javaMailSender;
    }

}
