package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
  @Size(min = 4, max = 20)
  String username;

  @NotEmpty(message = "비밀번호를 입력해주세요.")
  String password;

  @NotEmpty(message = "이메일을 입력해주세요.")
  @Email(message = "이메일 형식에 맞지 않습니다.")
  String email;
}
