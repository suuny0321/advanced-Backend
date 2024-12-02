package com.lion.demo.controller;


import com.lion.demo.entity.User;
import com.lion.demo.repository.UserRepository;
import com.lion.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.List;

import static org.mindrot.jbcrypt.BCrypt.hashpw;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository; //userRepository에 있는 걸 사용한다.

    @Autowired
    private UserService userService; // "객체 생성"후 의존성 주입

    @GetMapping("/register")
    public String registerForm() {
        return "user/register";
    }

    @PostMapping("/register")
    public String registerProc(String uid, String pwd, String pwd2, String uname, String email) {
        if (userService.findByUid(uid) == null && pwd.equals(pwd2) && pwd.length() >= 4) {

            String hashedPwd = hashpw(pwd, BCrypt.gensalt()); //DB에는 hashedPassword로 저장이 된다. 암호화된 패스워드는 평문으로 가는게 불가능
            User user = User.builder()
                    .uid(uid).pwd(hashedPwd)
                    .uname(uname).email(email)
                    .regDate(LocalDate.now())
                    .role("ROLE_USER")
                    .build();
            userService.registerUser(user);
        }
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "user/list";

    }

    @GetMapping("/delete/{uid}")
    public String delete(@PathVariable String uid) {
        userService.deleteUser(uid);
        return "redirect:/user/list";
    }

    @GetMapping("/update/{uid}")
    public String update(@PathVariable String uid, Model model) {
        User user = userService.findByUid(uid);
        model.addAttribute("user", user);
        return "user/update";
    }

    @GetMapping("/update")
    public String updateProc(String uid, String pwd, String pwd2, String uname, String email, String role) {
        User user = userService.findByUid(uid);
        if (pwd.equals(pwd2) && pwd.length() >= 4) {
            String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            user.setPwd(hashedPwd);
        }
        user.setUname(uname);
        user.setEmail(email);
        user.setRole(role);
        userService.updateUser(user);
        return "redirect:/user/list";
        }


    }








