package com.example.opensource_test.repository;

import com.example.opensource_test.entity.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @AfterEach
    public void tearDown() {
        contactRepository.deleteAll();
    }

    @Transactional
    @Test
    public void 연락처저장_이름순으로불러오기() {
        // given
        Contact contact1 = Contact.builder()
                .name("홍길동")
                .email("hong@gmail.com")
                .phoneNum("01099998888")
                .build();

        Contact contact2 = Contact.builder()
                .name("강감찬")
                .email("kang@gmail.com")
                .phoneNum("01011112222")
                .build();

        // when
        contactRepository.save(contact1);
        contactRepository.save(contact2);

        //then
        Page<Contact> page = contactRepository.findAll(PageRequest.of(0, 10, Sort.by("name")));
        List<Contact> list = page.getContent();
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo(contact2);

    }

    @Transactional
    @Test
    public void 이름검색() {
        // given
        Contact contact = Contact.builder()
                .name("홍길동")
                .email("hong@gmail.com")
                .phoneNum("01099998888")
                .build();

        // when
        contactRepository.save(contact);
        Page<Contact> result = contactRepository.findByNameOrderByName("홍길동", PageRequest.of(0, 10));
        Contact contact1 = result.stream().findAny().get();
        System.out.println(contact1);
        //then
        assertThat(contact1).isEqualTo(contact);
    }


}