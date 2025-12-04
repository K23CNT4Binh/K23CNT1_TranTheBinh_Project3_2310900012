package ttb_QLThuVien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttb_QLThuVien.entity.Category;
import ttb_QLThuVien.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy tất cả danh mục
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lấy danh mục theo ID
    public Category getCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    // Lưu hoặc cập nhật danh mục
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Xóa danh mục theo ID
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    // Tìm kiếm danh mục theo tên (ignore case)
    public List<Category> searchCategories(String keyword) {
        return categoryRepository.findByTenDanhMucContainingIgnoreCase(keyword);
    }
}
