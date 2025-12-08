package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.Ttb_entity.Ttb_PhieuMuon;
import ttb_QLThuVien.Ttb_entity.Ttb_NguoiDung;
import ttb_QLThuVien.Ttb_service.Ttb_PhieuMuonService;
import ttb_QLThuVien.Ttb_service.Ttb_NguoiDungService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/borrow")  // URL quản lý phiếu mượn
@RequiredArgsConstructor
public class Ttb_PhieuMuonController {

    private final Ttb_PhieuMuonService phieuMuonService;
    private final Ttb_NguoiDungService nguoiDungService;

    // Hiển thị danh sách phiếu mượn
    @GetMapping
    public String listBorrowSlips(Model model) {
        List<Ttb_PhieuMuon> borrowList = phieuMuonService.getAll();  // Lấy tất cả phiếu mượn
        model.addAttribute("borrowList", borrowList);
        return "areas/admin/borrow-slip/borrow-list"; // template Thymeleaf
    }

    // Hiển thị form thêm mới hoặc sửa phiếu mượn
    @GetMapping({"/form", "/form/{id}"})
    public String showForm(@PathVariable(required = false) Long id, Model model) {
        Ttb_PhieuMuon borrow;

        if (id != null) {  // Sửa phiếu mượn
            borrow = phieuMuonService.getById(id).orElse(new Ttb_PhieuMuon());
        } else {  // Thêm mới phiếu mượn
            borrow = new Ttb_PhieuMuon();
        }

        // Lấy danh sách người dùng để chọn khi tạo phiếu mượn
        List<Ttb_NguoiDung> users = nguoiDungService.getAllNguoiDung();
        model.addAttribute("borrow", borrow);
        model.addAttribute("users", users);

        return "areas/admin/borrow-slip/borrow-form"; // template Thymeleaf
    }

    // Lưu phiếu mượn (thêm mới hoặc cập nhật)
    @PostMapping("/save")
    public String saveBorrow(@ModelAttribute("borrow") Ttb_PhieuMuon borrow) {
        phieuMuonService.save(borrow); // Gọi service lưu phiếu mượn
        return "redirect:/admin/borrow";
    }

    // Xem chi tiết phiếu mượn
    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        Optional<Ttb_PhieuMuon> borrowOpt = phieuMuonService.getById(id);
        if (borrowOpt.isPresent()) {
            model.addAttribute("borrow", borrowOpt.get());
        } else {
            model.addAttribute("borrow", new Ttb_PhieuMuon());
        }
        return "areas/admin/borrow-slip/borrow-form"; // Hoặc tạo riêng borrow-detail.html
    }

    // Xóa phiếu mượn theo ID
    @GetMapping("/delete/{id}")
    public String deleteBorrow(@PathVariable Long id) {
        if (phieuMuonService.existsById(id)) {  // Kiểm tra tồn tại trước khi xóa
            phieuMuonService.delete(id);
        }
        return "redirect:/admin/borrow";
    }
}
