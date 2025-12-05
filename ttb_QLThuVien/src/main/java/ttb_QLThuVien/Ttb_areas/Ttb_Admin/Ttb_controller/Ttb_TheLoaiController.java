package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.Ttb_entity.Ttb_TheLoai;
import ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_service.Ttb_TheLoaiService;

@Controller
@RequestMapping("/admin/categories")
public class Ttb_TheLoaiController {

    @Autowired
    private Ttb_TheLoaiService theLoaiService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", theLoaiService.getAllTheLoai());
        return "admin/categories/category-list";
    }

    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Ttb_TheLoai());
        return "admin/categories/category-form";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Ttb_TheLoai category) {
        theLoaiService.saveTheLoai(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        theLoaiService.getTheLoaiById(id).ifPresent(category -> model.addAttribute("category", category));
        return "admin/categories/category-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        theLoaiService.deleteTheLoai(id);
        return "redirect:/admin/categories";
    }
}
