package ttb_QLThuVien.Ttb_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.Ttb_entity.Ttb_TheLoai;

@Repository
public interface Ttb_TheLoaiRepository extends JpaRepository<Ttb_TheLoai, Long> {

    boolean existsByTenTheLoai(String tenTheLoai);
}
