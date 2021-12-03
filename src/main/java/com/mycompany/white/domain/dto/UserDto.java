package com.mycompany.white.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    private Long id;
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]{6,}$", message = "비밀번호는 영어와 숫자를 포함하여 6자리 이상으로 입력해주세요.")
    private String password;

}
