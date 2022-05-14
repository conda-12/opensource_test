package com.example.opensource_test.service;

import com.example.opensource_test.dto.ContactDto;
import com.example.opensource_test.entity.Contact;
import com.example.opensource_test.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    /**
     * 연락처 등록
     */
    public Long add(ContactDto dto) {
        return contactRepository.save(dto.toEntity()).getId();
    }

    /**
     * 연락처 전체 조회
     */
    public Page<ContactDto> findAll(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by("name"));
        return contactRepository.findAll(pageable).map(ContactDto::new);
    }

    /**
     * 연락처 이름 검색 조회
     */
    public Page<ContactDto> findByName(String name, Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return contactRepository.findByNameOrderByName(name, pageable).map(ContactDto::new);
    }

    /**
     * 연락처 조회
     */
    public ContactDto findById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 연락처가 없습니다. id : " + id));
        return new ContactDto(contact);
    }

    /**
     * 연락처 수정
     */
    public Long update(ContactDto dto) {
        Contact contact = contactRepository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 연락처가 없습니다. id : " + dto.getId()));
        contact.update(dto.getName(), dto.getEmail(), dto.getPhoneNum());
        return contact.getId();
    }

    /**
     * 연락처 삭제
     */
    public void delete(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 연락처가 없습니다. id : " + id));
        contactRepository.delete(contact);
    }

}