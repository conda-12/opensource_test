package com.example.opensource_test;

import com.example.opensource_test.repository.ContactsRepository;
import com.example.opensource_test.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactsConfig {

    private final ContactsRepository contactsRepository;

    @Autowired
    public ContactsConfig(ContactsRepository contactsRepository){
        this.contactsRepository = contactsRepository;
    }

    @Bean
    public ContactsService contactsService(){
        return new ContactsService(contactsRepository);
    }
}
