package com.example.opensource_test.repository;

import com.example.opensource_test.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    @Query("SELECT c FROM Contacts c ORDER BY c.name")
    List<Contacts> findAll();

    List<Contacts> findByName(String name);
}
