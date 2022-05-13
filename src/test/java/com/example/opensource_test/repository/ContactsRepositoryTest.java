package com.example.opensource_test.repository;

import com.example.opensource_test.entity.Contacts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ContactsRepositoryTest {

    @Autowired
    private ContactsRepository contactsRepository;

    @AfterEach
    public void afterEach() {
        contactsRepository.deleteAll();
    }

    @Transactional
    @Test
    public void 연락처저장_불러오기() {
        // given
        Contacts contacts1 = Contacts.builder()
                .name("홍길동")
                .email("hong@gmail.com")
                .phoneNum("01099998888")
                .build();

        Contacts contacts2 = Contacts.builder()
                .name("강감찬")
                .email("kang@gmail.com")
                .phoneNum("01011112222").build();

        // when
        contactsRepository.save(contacts1);
        contactsRepository.save(contacts2);
        List<Contacts> list = contactsRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo(contacts2);


    }


}