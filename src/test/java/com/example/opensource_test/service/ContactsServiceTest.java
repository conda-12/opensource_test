package com.example.opensource_test.service;

import com.example.opensource_test.dto.ContactsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ContactsServiceTest {

    @Autowired
    private ContactsService contactsService;

    @Test
    void 등록_불러오기() {
        //given
        String name = "홍길동";
        String email = "hong@gmail.com";
        String phoneNum = "01011112222";
        ContactsDto dto = new ContactsDto();
        dto.setName(name);
        dto.setEmail(email);
        dto.setPhoneNum(phoneNum);

        //when
        Long id = contactsService.add(dto);
        ContactsDto result = contactsService.findById(id);

        //then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getPhoneNum()).isEqualTo(phoneNum);
    }

    @Test
    void 수정() {
        //given
        String name = "홍길동";
        String email = "hong@gmail.com";
        String phoneNum = "01011112222";
        ContactsDto saveDto = new ContactsDto();
        saveDto.setName(name);
        saveDto.setEmail(email);
        saveDto.setPhoneNum(phoneNum);

        Long id1 = contactsService.add(saveDto);
        ContactsDto findDto = contactsService.findById(id1);

        //when
        findDto.setName("강감찬");
        Long id2 = contactsService.update(findDto);
        ContactsDto updatedDto = contactsService.findById(id2);

        //then
        assertThat(updatedDto.getName()).isEqualTo("강감찬");
        assertThat(updatedDto.getPhoneNum()).isEqualTo(phoneNum);
    }

    @Test
    void 예외처리() {
        //given
        String name = "홍길동";
        String email = "hong@gmail.com";
        String phoneNum = "01011112222";
        ContactsDto saveDto = new ContactsDto();
        saveDto.setName(name);
        saveDto.setEmail(email);
        saveDto.setPhoneNum(phoneNum);

        //when
        Long id = contactsService.add(saveDto);

        // then
        assertThrows(IllegalArgumentException.class, () -> contactsService.findById(id + 1));

    }
}