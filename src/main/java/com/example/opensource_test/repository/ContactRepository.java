package com.example.opensource_test.repository;

import com.example.opensource_test.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findAll(Pageable pageable);

    Page<Contact> findByNameOrderByName(String name, Pageable pageable);
}
