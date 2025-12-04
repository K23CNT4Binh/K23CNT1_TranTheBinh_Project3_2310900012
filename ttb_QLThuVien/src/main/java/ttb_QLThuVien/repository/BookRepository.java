package ttb_QLThuVien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Tìm sách theo tên
    List<Book> findByTenSachContainingIgnoreCase(String tenSach);

    // Tìm sách theo tác giả
    List<Book> findByAuthor_TenTacGiaContainingIgnoreCase(String tenTacGia);

    // Tìm sách theo thể loại
    List<Book> findByCategory_TenDanhMucContainingIgnoreCase(String tenDanhMuc);

    // Lọc sách theo trạng thái
    List<Book> findByTrangThai(String trangThai);

    // Lọc sách còn hàng (so_luong > 0)
    List<Book> findBySoLuongGreaterThan(Integer soLuong);

    // Tìm kiếm tổng hợp theo tên sách, tác giả hoặc thể loại
    @Query("SELECT b FROM Book b " +
            "WHERE LOWER(b.tenSach) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.author.tenTacGia) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.category.tenDanhMuc) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchByKeyword(@Param("keyword") String keyword);
}
