package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserContoller {

  @Autowired
  private UserService userService;

  @GetMapping("/login")
  public String login() {
    return "user/login";
  }

  @GetMapping("/signup")
  public String signup(UserDTO userDTO) {
    return "user/signup";
  }

  @PostMapping("/signup")
  public String signup(@Valid UserDTO userDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "user/signup";
    }

    try {
      userService.creatUser(userDTO);
    } catch (DataIntegrityViolationException e) {
      e.printStackTrace(System.err);
      bindingResult.reject("signupFailed", "이미 사용중인 아이디입니다.");
      return "user/signup";
    }

    return "redirect:/board/list";
  }
}
