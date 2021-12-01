package com.mycompany.white.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String password;

}
