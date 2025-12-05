package ttb_QLThuVien.Ttb_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.Ttb_entity.Ttb_Sach;

import java.util.List;

@Repository
public interface Ttb_SachRepository extends JpaRepository<Ttb_Sach, Long> {

    List<Ttb_Sach> findByTieuDeContainingIgnoreCase(String tieuDe);

    List<Ttb_Sach> findByTacGiaContainingIgnoreCase(String tacGia);

    List<Ttb_Sach> findByTheLoaiId(Long theLoaiId);
}
