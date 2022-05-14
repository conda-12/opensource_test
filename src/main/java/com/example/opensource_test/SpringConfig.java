package com.example.opensource_test;

import com.example.opensource_test.controller.ContactController;
import com.example.opensource_test.repository.ContactRepository;
import com.example.opensource_test.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final ContactRepository contactRepository;

    @Autowired
    public SpringConfig(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Bean
    public ContactService contactService() {
        return new ContactService(contactRepository);
    }

    @Bean
    public ContactController contactController() {
        return new ContactController(contactService());
    }
}
