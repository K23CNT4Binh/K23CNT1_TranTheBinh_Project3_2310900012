package ttb_QLThuVien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Tìm user theo username
    Optional<User> findByUsername(String username);

    // Kiểm tra username có tồn tại chưa
    boolean existsByUsername(String username);

    // Kiểm tra email có tồn tại chưa
    boolean existsByEmail(String email);
}
