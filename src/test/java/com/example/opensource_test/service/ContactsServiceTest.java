package com.example.opensource_test.service;

import com.example.opensource_test.dto.ContactDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class contactServiceTest {

    @Autowired
    private ContactService contactService;

    @Test
    void 등록_불러오기() {
        //given
        String name = "홍길동";
        String email = "hong@gmail.com";
        String phoneNum = "01011112222";
        ContactDto saveDto = new ContactDto();
        saveDto.setName(name);
        saveDto.setEmail(email);
        saveDto.setPhoneNum(phoneNum);

        //when
        Long id = contactService.add(saveDto);
        Page<ContactDto> result = contactService.findAll(0, 10);
        ContactDto dto = result.stream().findAny().get();

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getPhoneNum()).isEqualTo(phoneNum);
    }

    @Test
    void 수정() {
        //given
        String name = "홍길동";
        String email = "hong@gmail.com";
        String phoneNum = "01011112222";
        ContactDto saveDto = new ContactDto();
        saveDto.setName(name);
        saveDto.setEmail(email);
        saveDto.setPhoneNum(phoneNum);

        Long id1 = contactService.add(saveDto);
        ContactDto findDto = contactService.findById(id1);

        //when
        findDto.setName("강감찬");
        Long id2 = contactService.update(findDto);
        ContactDto updatedDto = contactService.findById(id2);

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
        ContactDto saveDto = new ContactDto();
        saveDto.setName(name);
        saveDto.setEmail(email);
        saveDto.setPhoneNum(phoneNum);

        //when
        Long id = contactService.add(saveDto);

        // then
        assertThrows(IllegalArgumentException.class, () -> contactService.findById(id + 1));

    }
}