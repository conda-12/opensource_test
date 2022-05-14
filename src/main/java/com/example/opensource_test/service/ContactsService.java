package com.example.opensource_test.service;

import com.example.opensource_test.dto.ContactsDto;
import com.example.opensource_test.entity.Contacts;
import com.example.opensource_test.repository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Slf4j
public class ContactsService {

    private final ContactsRepository contactsRepository;

    /**
     * 연락처 등록
     */
    public Long add(ContactsDto dto) {
        return contactsRepository.save(dto.toEntity()).getId();
    }

    /**
     * 연락처 전체 조회
     */
    public List<ContactsDto> findAll() {
        return contactsRepository.findAll().stream().map(ContactsDto::new).collect(Collectors.toList());
    }

    /**
     * 연락처 이름 검색 조회
     */
    public List<ContactsDto> findByName(String name) {
        return contactsRepository.findByName(name).stream().map(ContactsDto::new).collect(Collectors.toList());
    }

    /**
     * 연락처 조회
     */
    public ContactsDto findById(Long id) {
        Contacts contacts = contactsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 연락처가 없습니다. id : " + id));
        return new ContactsDto(contacts);
    }

    /**
     * 연락처 수정
     */
    public Long update(ContactsDto dto) {
        Contacts contacts = contactsRepository.findById(dto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 연락처가 없습니다. id : " + dto.getId()));
        contacts.update(dto.getName(), dto.getEmail(), dto.getPhoneNum());
        return contacts.getId();
    }

    /**
     * 연락처 삭제
     */
    public void delete(Long id) {
        Contacts contacts = contactsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 연락처가 없습니다. id : " + id));
        contactsRepository.delete(contacts);
    }

}
