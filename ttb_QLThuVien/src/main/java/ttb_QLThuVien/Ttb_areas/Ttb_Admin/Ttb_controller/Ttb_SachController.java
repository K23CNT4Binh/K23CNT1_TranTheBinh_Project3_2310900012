package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.Ttb_entity.Ttb_Sach;
import ttb_QLThuVien.Ttb_entity.Ttb_TheLoai;
import ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_service.Ttb_SachService;
import ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_service.Ttb_TheLoaiService;

import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class Ttb_SachController {

    @Autowired
    private Ttb_SachService sachService;

    @Autowired
    private Ttb_TheLoaiService theLoaiService;

    @GetMapping
    public String listSach(Model model) {
        model.addAttribute("books", sachService.getAllSach());
        return "admin/books/book-list";
    }

    @GetMapping("/add")
    public String addSachForm(Model model) {
        model.addAttribute("book", new Ttb_Sach());
        List<Ttb_TheLoai> theLoais = theLoaiService.getAllTheLoai();
        model.addAttribute("theLoais", theLoais);
        return "admin/books/book-form";
    }

    @PostMapping("/save")
    public String saveSach(@ModelAttribute Ttb_Sach book) {
        sachService.saveSach(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/edit/{id}")
    public String editSachForm(@PathVariable Long id, Model model) {
        sachService.getSachById(id).ifPresent(book -> model.addAttribute("book", book));
        List<Ttb_TheLoai> theLoais = theLoaiService.getAllTheLoai();
        model.addAttribute("theLoais", theLoais);
        return "admin/books/book-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSach(@PathVariable Long id) {
        sachService.deleteSach(id);
        return "redirect:/admin/books";
    }
}

