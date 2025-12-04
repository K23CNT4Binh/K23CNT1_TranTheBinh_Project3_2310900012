package ttb_QLThuVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.entity.User;
import ttb_QLThuVien.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users/user-list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        userService.getUserById(id)
                .ifPresent(user -> model.addAttribute("user", user));
        return "admin/users/user-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }
}
