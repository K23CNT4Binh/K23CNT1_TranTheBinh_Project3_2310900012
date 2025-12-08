package ttb_QLThuVien.Ttb_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttb_QLThuVien.Ttb_entity.Ttb_Sach;
import ttb_QLThuVien.Ttb_repository.Ttb_SachRepository;

import java.util.List;
import java.util.Optional;

@Service
public class Ttb_SachService {

    @Autowired
    private Ttb_SachRepository sachRepository;

    public List<Ttb_Sach> getAllSach() {
        return sachRepository.findAll();
    }

    public Optional<Ttb_Sach> getSachById(Long id) {
        return sachRepository.findById(id);
    }

    public Ttb_Sach saveSach(Ttb_Sach sach) {
        return sachRepository.save(sach);
    }

    public void deleteSach(Long id) {
        sachRepository.deleteById(id);
    }

    public List<Ttb_Sach> findByTieuDe(String tieuDe) {
        return sachRepository.findByTieuDeContainingIgnoreCase(tieuDe);
    }

    // ⭐ Sửa tên phương thức đúng ManyToMany
    public List<Ttb_Sach> findByTacGia(String tenTacGia) {
        return sachRepository.findByTacGias_HoTenContainingIgnoreCase(tenTacGia);
    }

    public List<Ttb_Sach> findByTheLoaiId(Long theLoaiId) {
        return sachRepository.findByTheLoaiId(theLoaiId);
    }
}
