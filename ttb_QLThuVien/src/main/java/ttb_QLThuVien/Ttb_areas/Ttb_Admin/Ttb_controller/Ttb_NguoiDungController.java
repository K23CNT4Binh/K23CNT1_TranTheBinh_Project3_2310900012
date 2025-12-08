package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.Ttb_entity.Ttb_NguoiDung;
import ttb_QLThuVien.Ttb_service.Ttb_NguoiDungService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
public class Ttb_NguoiDungController {

    @Autowired
    private Ttb_NguoiDungService nguoiDungService;

    // ---------------- List all users ----------------
    @GetMapping
    public String listNguoiDung(Model model) {
        model.addAttribute("users", nguoiDungService.getAllNguoiDung());
        return "areas/admin/users/user-list";
    }

    // ---------------- Show Add User Form ----------------
    @GetMapping("/add")
    public String addUserForm(Model model) {
        Ttb_NguoiDung newUser = new Ttb_NguoiDung();
        newUser.setActive(true); // checkbox mặc định checked
        model.addAttribute("user", newUser);
        return "areas/admin/users/user-form";
    }

    // ---------------- Show Edit User Form ----------------
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        Optional<Ttb_NguoiDung> userOpt = nguoiDungService.getNguoiDungById(id);
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            return "areas/admin/users/user-form";
        }
        return "redirect:/admin/users"; // nếu không tìm thấy
    }

    // ---------------- Save User (Add or Edit) ----------------
    @PostMapping("/save")
    public String saveUser(@ModelAttribute Ttb_NguoiDung user, Model model) {

        // ----- Kiểm tra username khi thêm mới -----
        if (user.getId() == null && nguoiDungService.existsByUsername(user.getUsername())) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Username đã tồn tại!");
            return "areas/admin/users/user-form";
        }

        // ----- Kiểm tra username khi edit (ngoại trừ chính user đó) -----
        if (user.getId() != null) {
            Optional<Ttb_NguoiDung> existingUser = nguoiDungService.getByUsername(user.getUsername());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(user.getId())) {
                model.addAttribute("user", user);
                model.addAttribute("error", "Username đã tồn tại!");
                return "areas/admin/users/user-form";
            }
        }

        // ----- Lưu user -----
        nguoiDungService.saveNguoiDung(user);
        return "redirect:/admin/users";
    }

    // ---------------- Delete User ----------------
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        nguoiDungService.deleteNguoiDung(id);
        return "redirect:/admin/users";
    }
}
