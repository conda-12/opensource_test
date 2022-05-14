package com.example.opensource_test.controller;


import com.example.opensource_test.dto.ContactDto;
import com.example.opensource_test.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/vi/contact")
@ResponseBody
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<?> addContact(@RequestBody ContactDto dto) {
        try {
            dto.setId(null);
            Long id = contactService.add(dto);
            return ResponseEntity.ok().body(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody ContactDto dto) {
        try {
            dto.setId(id);
            contactService.update(dto);
            return ResponseEntity.ok().body(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        try {
            contactService.delete(id);
            return ResponseEntity.ok().body(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> allContact(@Param("offset") Integer offset, @Param("limit") Integer limit) {
        try {
            Page<ContactDto> result = contactService.findAll(offset, limit);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            ContactDto dto = contactService.findById(id);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByName(@Param("name") String name, @Param("offset") Integer offset, @Param("limit") Integer limit) {
        try {
            Page<ContactDto> result = contactService.findByName(name, offset, limit);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
