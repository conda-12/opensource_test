package com.example.opensource_test.controller;

import com.example.opensource_test.dto.ContactDto;
import com.example.opensource_test.entity.Contact;
import com.example.opensource_test.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ContactControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void tearDown() {
        contactRepository.deleteAll();
    }

    @Test
    void 등록() throws Exception {
        // given
        String name = "홍길동";
        String email = "hong@gmail.com";
        String phoneNum = "01011112222";
        ContactDto dto = new ContactDto();
        dto.setName(name);
        dto.setEmail(email);
        dto.setPhoneNum(phoneNum);

        // when
        mvc.perform(post("/api/v1/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        //then
        Page<Contact> all = contactRepository.findAll(PageRequest.of(0, 10));
        assertThat(all.stream().findAny().get().getName()).isEqualTo(name);


    }

    @Transactional
    @Test
    void 수정() throws Exception {
        //given
        Contact contact = contactRepository.save(Contact.builder()
                .name("홍길동")
                .email("hong@gmail.com")
                .phoneNum("01011112222")
                .build()
        );

        Long contactId = contact.getId();
        String expectedPhoneNum = "01077778888";

        // when
        ContactDto dto = new ContactDto();
        dto.setName("홍길동");
        dto.setEmail("hong@gmail.com");
        dto.setPhoneNum(expectedPhoneNum);

        mvc.perform(put("/api/v1/contact/" + contactId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        //then
        Contact result = contactRepository.findById(contactId).get();
        assertThat(result).isEqualTo(contact);


    }

    @Test
    void 이름_검색() throws Exception {
        //given
        Contact contact = contactRepository.save(Contact.builder()
                .name("홍길동")
                .email("hong@gmail.com")
                .phoneNum("01011112222")
                .build()
        );
        // when
        MvcResult mvcResult = mvc.perform(get("/api/v1/contact/search")
                        .param("offset", "0")
                        .param("limit", "10")
                        .param("name", "홍길동"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        String json = mvcResult.getResponse().getContentAsString();
        assertThat(json.contains("hong@gmail.com")).isEqualTo(true);
    }

    @Test
    void 예외_처리() throws Exception {
        // when
        MvcResult mvcResult = mvc.perform(delete("/api/v1/contact/" + 1))
                .andExpect(status().isBadRequest())
                .andReturn();

        // then
        String message = mvcResult.getResponse().getContentAsString();
        assertThat(message.contains("해당 연락처가 없습니다.")).isEqualTo(true);

    }

}