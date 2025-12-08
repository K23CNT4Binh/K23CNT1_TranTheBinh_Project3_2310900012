package ttb_QLThuVien.Ttb_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttb_QLThuVien.Ttb_entity.Ttb_PhieuMuon;
import ttb_QLThuVien.Ttb_repository.Ttb_PhieuMuonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Ttb_PhieuMuonService {

    private final Ttb_PhieuMuonRepository phieuMuonRepository;

    // Lấy tất cả phiếu mượn
    public List<Ttb_PhieuMuon> getAll() {
        return phieuMuonRepository.findAll();
    }

    // Tìm phiếu mượn theo ID
    public Optional<Ttb_PhieuMuon> getById(Long id) {
        return phieuMuonRepository.findById(id);
    }

    // Tìm phiếu mượn theo mã phiếu
    public Ttb_PhieuMuon getByMaPhieu(String maPhieu) {
        return phieuMuonRepository.findBymaPhieu(maPhieu);
    }

    // Lấy danh sách phiếu mượn của 1 người dùng
    public List<Ttb_PhieuMuon> getByUser(Long userId) {
        return phieuMuonRepository.findByUser_Id(userId);
    }

    // Lấy danh sách phiếu mượn theo trạng thái
    // Trạng thái có thể là: "cho_duyet", "dang_muon", "da_tra", "qua_han", "tu_choi"
    public List<Ttb_PhieuMuon> getByTrangThai(String trangThai) {
        return phieuMuonRepository.findBytrangThai(trangThai);
    }

    // Lưu hoặc cập nhật phiếu mượn
    public Ttb_PhieuMuon save(Ttb_PhieuMuon phieuMuon) {
        return phieuMuonRepository.save(phieuMuon);
    }

    // Xóa phiếu mượn theo ID
    public void delete(Long id) {
        phieuMuonRepository.deleteById(id);
    }

    // Kiểm tra phiếu mượn có tồn tại theo ID
    public boolean existsById(Long id) {
        return phieuMuonRepository.existsById(id);
    }
}
