package ttb_QLThuVien.Ttb_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.Ttb_entity.Ttb_TheLoai;

import java.util.List;

@Repository
public interface Ttb_TheLoaiRepository extends JpaRepository<Ttb_TheLoai, Long> {

    boolean existsByTenTheLoai(String tenTheLoai);

    // Lọc theo trạng thái
    List<Ttb_TheLoai> findByTrangThai(String trangThai);

    // Tìm kiếm theo keyword (tên thể loại hoặc mô tả)
    List<Ttb_TheLoai> findByTenTheLoaiContainingIgnoreCaseOrMoTaContainingIgnoreCase(String tenTheLoai, String moTa);
}
