package com.spontaneous.server;

import com.spontaneous.server.util.GsonFactory;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@SpringBootApplication
public class SpontaneousApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpontaneousApplication.class, args);
    }

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory managerFactory) {
        return managerFactory.getSessionFactory();
    }

    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter messageConverter = new GsonHttpMessageConverter();

        ((GsonHttpMessageConverter) messageConverter).setGson(
                GsonFactory.getGson()
        );

        return new HttpMessageConverters(messageConverter);
    }
}
