package com.example.opensource_test.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String email;
    @Column(nullable = false)
    private String phoneNum;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @Builder
    public Contact(String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public void update(String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }
}
