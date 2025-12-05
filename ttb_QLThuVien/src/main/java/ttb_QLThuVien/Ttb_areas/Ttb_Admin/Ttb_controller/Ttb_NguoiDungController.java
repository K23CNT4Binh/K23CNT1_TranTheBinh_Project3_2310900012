package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.Ttb_entity.Ttb_NguoiDung;
import ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_service.Ttb_NguoiDungService;

@Controller
@RequestMapping("/admin/users")
public class Ttb_NguoiDungController {

    @Autowired
    private Ttb_NguoiDungService nguoiDungService;

    @GetMapping
    public String listNguoiDung(Model model) {
        model.addAttribute("users", nguoiDungService.getAllNguoiDung());
        return "areas/admin/users/user-list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new Ttb_NguoiDung());
        return "areas/admin/users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute Ttb_NguoiDung user) {
        nguoiDungService.saveNguoiDung(user);
        return "redirect:areas/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        nguoiDungService.getNguoiDungById(id).ifPresent(user -> model.addAttribute("user", user));
        return "areas/admin/users/user-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        nguoiDungService.deleteNguoiDung(id);
        return "redirect:areas/admin/users";
    }
}
