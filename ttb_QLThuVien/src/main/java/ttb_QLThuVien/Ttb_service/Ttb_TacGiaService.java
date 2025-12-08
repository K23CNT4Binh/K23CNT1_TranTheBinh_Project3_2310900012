package ttb_QLThuVien.Ttb_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttb_QLThuVien.Ttb_entity.Ttb_TacGia;
import ttb_QLThuVien.Ttb_repository.Ttb_TacGiaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class Ttb_TacGiaService {

    @Autowired
    private Ttb_TacGiaRepository tacGiaRepository;

    // Lấy tất cả tác giả
    public List<Ttb_TacGia> getAllTacGia() {
        return tacGiaRepository.findAll();
    }

    // Lấy 1 tác giả theo ID
    public Optional<Ttb_TacGia> getTacGiaById(Long id) {
        return tacGiaRepository.findById(id);
    }

    // Lưu hoặc cập nhật tác giả
    public Ttb_TacGia saveTacGia(Ttb_TacGia tacGia) {
        return tacGiaRepository.save(tacGia);
    }


    // Xóa tác giả
    public void deleteTacGia(Long id) {
        tacGiaRepository.deleteById(id);
    }

    // 🔍 Tìm kiếm theo họ tên hoặc bí danh
    public List<Ttb_TacGia> searchTacGia(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return tacGiaRepository.findAll();
        }
        return tacGiaRepository
                .findByHoTenContainingIgnoreCaseOrBiDanhContainingIgnoreCase(keyword, keyword);
    }
}
