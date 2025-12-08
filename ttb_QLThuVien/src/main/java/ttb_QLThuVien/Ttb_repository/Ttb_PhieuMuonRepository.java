package ttb_QLThuVien.Ttb_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.Ttb_entity.Ttb_PhieuMuon;

import java.util.List;

@Repository
public interface Ttb_PhieuMuonRepository extends JpaRepository<Ttb_PhieuMuon, Long> {

    // Tìm theo mã phiếu
    Ttb_PhieuMuon findBymaPhieu(String maPhieu);

    // Lấy danh sách phiếu theo user
    List<Ttb_PhieuMuon> findByUser_Id(Long userId);

    // Lấy phiếu theo trạng thái (cho_duyet / dang_muon / da_tra / qua_han / tu_choi)
    List<Ttb_PhieuMuon> findBytrangThai(String trangThai);
}
