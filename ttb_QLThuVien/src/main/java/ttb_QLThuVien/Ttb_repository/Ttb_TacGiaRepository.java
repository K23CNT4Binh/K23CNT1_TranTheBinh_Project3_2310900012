package ttb_QLThuVien.Ttb_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.Ttb_entity.Ttb_TacGia;

import java.util.List;
import java.util.Optional;

@Repository
public interface Ttb_TacGiaRepository extends JpaRepository<Ttb_TacGia, Long> {

    // Tìm theo họ tên hoặc bí danh
    List<Ttb_TacGia> findByHoTenContainingIgnoreCase(String hoTen);

    List<Ttb_TacGia> findByBiDanhContainingIgnoreCase(String biDanh);

    // Tìm theo cả hai
    List<Ttb_TacGia> findByHoTenContainingIgnoreCaseOrBiDanhContainingIgnoreCase(String key1, String key2);
}
