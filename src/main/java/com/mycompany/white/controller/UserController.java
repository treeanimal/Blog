package com.mycompany.white.controller;

import com.mycompany.white.domain.dto.UserDto;
import com.mycompany.white.domain.entity.Role;
import com.mycompany.white.domain.entity.User;
import com.mycompany.white.service.RoleService;
import com.mycompany.white.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerForm", new UserDto());
        return "user/register";
    }

    @PostMapping("/register")
    public String userRegister(UserDto userDto) {


        String password = passwordEncoder.encode(userDto.getPassword());
        userService.saveUser(userDto.getEmail(), password);
        return "redirect:/";
    }

//
//    @GetMapping("/login")
//    public String login(){
//        return "user/login";
//    }

}
