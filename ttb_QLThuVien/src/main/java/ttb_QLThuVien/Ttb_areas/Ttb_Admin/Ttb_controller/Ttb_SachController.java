package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ttb_QLThuVien.Ttb_entity.Ttb_Sach;
import ttb_QLThuVien.Ttb_entity.Ttb_TheLoai;
import ttb_QLThuVien.Ttb_entity.Ttb_TacGia;
import ttb_QLThuVien.Ttb_service.Ttb_SachService;
import ttb_QLThuVien.Ttb_service.Ttb_TheLoaiService;
import ttb_QLThuVien.Ttb_service.Ttb_TacGiaService;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin/books")
public class Ttb_SachController {

    @Autowired
    private Ttb_SachService sachService;

    @Autowired
    private Ttb_TheLoaiService theLoaiService;

    @Autowired
    private Ttb_TacGiaService tacGiaService;

    // ------------------- DANH SÁCH SÁCH -------------------
    @GetMapping
    public String listSach(Model model) {
        model.addAttribute("books", sachService.getAllSach());
        return "areas/admin/books/book-list";
    }

    // ------------------- FORM THÊM MỚI -------------------
    @GetMapping("/add")
    public String addSachForm(Model model) {
        Ttb_Sach book = new Ttb_Sach();
        book.setTacGias(new ArrayList<>()); // tránh null khi multi-select
        model.addAttribute("book", book);
        model.addAttribute("theLoais", theLoaiService.getAllTheLoai());
        model.addAttribute("tacGias", tacGiaService.getAllTacGia());
        return "areas/admin/books/book-form";
    }

    // ------------------- FORM SỬA -------------------
    @GetMapping("/edit/{id}")
    public String editSachForm(@PathVariable Long id, Model model) {
        Optional<Ttb_Sach> optBook = sachService.getSachById(id);
        if (optBook.isPresent()) {
            Ttb_Sach book = optBook.get();
            if (book.getTacGias() == null) {
                book.setTacGias(new ArrayList<>());
            }
            model.addAttribute("book", book);
            model.addAttribute("theLoais", theLoaiService.getAllTheLoai());
            model.addAttribute("tacGias", tacGiaService.getAllTacGia());
            return "areas/admin/books/book-form";
        }
        return "redirect:/admin/books";
    }

    // ------------------- LƯU (THÊM / SỬA) -------------------
    @PostMapping("/save")
    public String saveSach(@ModelAttribute Ttb_Sach book) throws IOException {

        // ----- Xử lý ảnh bìa -----
        MultipartFile file = book.getAnhBiaFile();
        if (file != null && !file.isEmpty()) {
            Path uploadPath = Paths.get("uploads/");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            book.setAnhBia("/uploads/" + fileName);
        }

        // ----- Lưu sách trực tiếp, Spring đã bind tacGias & theLoai -----
        sachService.saveSach(book);

        return "redirect:/admin/books";
    }

    // ------------------- XÓA -------------------
    @GetMapping("/delete/{id}")
    public String deleteSach(@PathVariable Long id) {
        sachService.deleteSach(id);
        return "redirect:/admin/books";
    }

    // ------------------- INIT BINDER CHO THỂ LOẠI & TÁC GIẢ -------------------
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        // Chuyển id -> Ttb_TheLoai
        binder.registerCustomEditor(Ttb_TheLoai.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null && !text.isEmpty()) {
                    Long id = Long.valueOf(text);
                    Ttb_TheLoai theLoai = theLoaiService.getTheLoaiById(id).orElse(null);
                    setValue(theLoai);
                } else {
                    setValue(null);
                }
            }
        });

        // Chuyển id -> Ttb_TacGia (dùng cho multi-select)
        binder.registerCustomEditor(Ttb_TacGia.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null && !text.isEmpty()) {
                    Long id = Long.valueOf(text);
                    Ttb_TacGia tg = tacGiaService.getTacGiaById(id).orElse(null);
                    setValue(tg);
                } else {
                    setValue(null);
                }
            }
        });
    }
}
