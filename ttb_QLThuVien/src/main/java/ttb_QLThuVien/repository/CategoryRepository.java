package ttb_QLThuVien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttb_QLThuVien.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // Tìm kiếm danh mục theo tên (ignore case)
    List<Category> findByTenDanhMucContainingIgnoreCase(String tenDanhMuc);
}
