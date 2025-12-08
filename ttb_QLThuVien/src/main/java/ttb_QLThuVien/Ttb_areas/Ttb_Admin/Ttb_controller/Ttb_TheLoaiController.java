package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.Ttb_entity.Ttb_TheLoai;
import ttb_QLThuVien.Ttb_service.Ttb_TheLoaiService;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class Ttb_TheLoaiController {

    @Autowired
    private Ttb_TheLoaiService theLoaiService;

    // ---------------- DANH SÁCH + TÌM KIẾM + LỌC ----------------
    @GetMapping
    public String listCategories(@RequestParam(value = "keyword", required = false) String keyword,
                                 @RequestParam(value = "status", required = false) String status,
                                 Model model) {

        List<Ttb_TheLoai> categories;

        if (keyword != null && !keyword.trim().isEmpty()) {
            categories = theLoaiService.searchTheLoai(keyword);
        } else if (status != null && !status.trim().isEmpty()) {
            categories = theLoaiService.filterByStatus(status);
        } else {
            categories = theLoaiService.getAllTheLoai();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);

        return "areas/admin/categories/category-list";
    }

    // ---------------- FORM THÊM MỚI ----------------
    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Ttb_TheLoai());
        return "areas/admin/categories/category-form";
    }

    // ---------------- FORM SỬA ----------------
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        theLoaiService.getTheLoaiById(id).ifPresent(category -> model.addAttribute("category", category));
        return "areas/admin/categories/category-form";
    }

    // ---------------- LƯU (THÊM / SỬA) ----------------
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Ttb_TheLoai category, Model model) {
        try {
            theLoaiService.saveTheLoai(category);
            return "redirect:/admin/categories"; // lưu thành công → redirect về danh sách
        } catch (RuntimeException ex) {
            // lưu thất bại (ví dụ trùng tên) → render lại form
            model.addAttribute("category", category);
            model.addAttribute("errorMessage", ex.getMessage());
            return "areas/admin/categories/category-form";
        }
    }

    // ---------------- XÓA ----------------
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        theLoaiService.deleteTheLoai(id);
        return "redirect:/admin/categories";
    }
}
