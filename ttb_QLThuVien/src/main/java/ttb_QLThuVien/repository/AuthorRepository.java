package ttb_QLThuVien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // Tìm theo tên tác giả
    List<Author> findByTenTacGiaContainingIgnoreCase(String tenTacGia);

    // Tìm theo quốc tịch
    List<Author> findByQuocTichContainingIgnoreCase(String quocTich);

    // Tìm kiếm tổng hợp: tên tác giả hoặc quốc tịch
    @Query("SELECT a FROM Author a " +
            "WHERE LOWER(a.tenTacGia) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(a.quocTich) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Author> searchByKeyword(@Param("keyword") String keyword);
}
