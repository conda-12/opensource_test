package com.example.opensource_test.dto;

import com.example.opensource_test.entity.Contact;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class ContactDto {

    private Long id;

    @Pattern(regexp = "^[가-힣a-zA-Z]+$", message = "이름 형식을 확인해주세요")
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;

    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "전화번호 형식을 확인해주세요.")
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    private String phoneNum;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    public ContactDto() {
        this.email = "";
    }

    public ContactDto(Contact entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phoneNum = entity.getPhoneNum();
        this.createDate = entity.getCreateDate();
        this.modifyDate = entity.getModifyDate();
    }

    public Contact toEntity() {
        return Contact.builder()
                .name(name)
                .email(email)
                .phoneNum(phoneNum)
                .build();
    }
}
