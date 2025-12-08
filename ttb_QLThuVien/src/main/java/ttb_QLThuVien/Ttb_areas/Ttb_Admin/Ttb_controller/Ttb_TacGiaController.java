package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.Ttb_entity.Ttb_TacGia;
import ttb_QLThuVien.Ttb_service.Ttb_TacGiaService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/authors")
public class Ttb_TacGiaController {

    @Autowired
    private Ttb_TacGiaService tacGiaService;

    // ---------------- DANH SÁCH + TÌM KIẾM ----------------
    @GetMapping
    public String listTacGia(@RequestParam(value = "keyword", required = false) String keyword,
                             Model model) {

        List<Ttb_TacGia> list;

        if (keyword != null && !keyword.trim().isEmpty()) {
            list = tacGiaService.searchTacGia(keyword);
        } else {
            list = tacGiaService.getAllTacGia();
        }

        model.addAttribute("authors", list);
        model.addAttribute("keyword", keyword);

        return "areas/admin/authors/author-list";
    }

    // ---------------- FORM THÊM MỚI ----------------
    @GetMapping("/add")
    public String addTacGiaForm(Model model) {
        Ttb_TacGia author = new Ttb_TacGia();
        // Gán ngày hiện tại để hiển thị ngay cả khi chưa lưu
        author.setNgayTao(LocalDateTime.now());
        author.setNgayCapNhat(LocalDateTime.now());
        model.addAttribute("author", author);
        return "areas/admin/authors/author-form";
    }

    // ---------------- FORM SỬA ----------------
    @GetMapping("/edit/{id}")
    public String editTacGiaForm(@PathVariable Long id, Model model) {

        Optional<Ttb_TacGia> optional = tacGiaService.getTacGiaById(id);

        if (optional.isPresent()) {
            model.addAttribute("author", optional.get());
            return "areas/admin/authors/author-form";
        }

        return "redirect:/admin/authors";
    }

    // ---------------- LƯU (THÊM / SỬA) ----------------
    @PostMapping("/save")
    public String saveTacGia(@ModelAttribute("author") Ttb_TacGia tacGia) {
        // Nếu muốn, backend vẫn đảm bảo @PrePersist/@PreUpdate sẽ tự set ngày
        tacGiaService.saveTacGia(tacGia);
        return "redirect:/admin/authors";
    }

    // ---------------- XÓA ----------------
    @GetMapping("/delete/{id}")
    public String deleteTacGia(@PathVariable Long id) {
        tacGiaService.deleteTacGia(id);
        return "redirect:/admin/authors";
    }

}
