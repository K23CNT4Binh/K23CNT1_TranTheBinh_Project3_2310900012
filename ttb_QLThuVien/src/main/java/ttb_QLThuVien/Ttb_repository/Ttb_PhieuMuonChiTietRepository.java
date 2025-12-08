package ttb_QLThuVien.Ttb_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.Ttb_entity.Ttb_PhieuMuonChiTiet;

import java.util.List;

@Repository
public interface Ttb_PhieuMuonChiTietRepository extends JpaRepository<Ttb_PhieuMuonChiTiet, Long> {

    // Lấy chi tiết theo phiếu mượn
    List<Ttb_PhieuMuonChiTiet> findByphieuMuon_Id(Long phieuMuonId);

    // Lấy chi tiết theo sách
    List<Ttb_PhieuMuonChiTiet> findBysach_Id(Long sachId);

    // Lấy chi tiết đang mượn
    List<Ttb_PhieuMuonChiTiet> findBytrangThai(String trangThai);
}
