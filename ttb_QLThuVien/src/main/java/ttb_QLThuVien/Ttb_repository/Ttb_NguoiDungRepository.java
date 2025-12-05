package ttb_QLThuVien.Ttb_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.Ttb_entity.Ttb_NguoiDung;

import java.util.Optional;

@Repository
public interface Ttb_NguoiDungRepository extends JpaRepository<Ttb_NguoiDung, Long> {

    Optional<Ttb_NguoiDung> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
