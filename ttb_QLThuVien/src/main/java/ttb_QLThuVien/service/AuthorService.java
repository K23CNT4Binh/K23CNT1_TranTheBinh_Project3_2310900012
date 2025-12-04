package ttb_QLThuVien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttb_QLThuVien.entity.Author;
import ttb_QLThuVien.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Lấy tất cả tác giả trong database
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Lấy tác giả theo ID, trả về Optional để kiểm tra tồn tại
    public Optional<Author> getAuthorById(Integer id) {
        return authorRepository.findById(id);
    }

    // Thêm mới hoặc cập nhật tác giả
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Xóa tác giả theo ID
    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    // Tìm kiếm tác giả theo tên (không phân biệt chữ hoa/thường)
    public List<Author> searchByTenTacGia(String tenTacGia) {
        return authorRepository.findByTenTacGiaContainingIgnoreCase(tenTacGia);
    }

    // Tìm kiếm tác giả theo quốc tịch (không phân biệt chữ hoa/thường)
    public List<Author> searchByQuocTich(String quocTich) {
        return authorRepository.findByQuocTichContainingIgnoreCase(quocTich);
    }

    // Tìm kiếm gộp: tên tác giả hoặc quốc tịch
    // Người dùng nhập keyword, nếu trùng tên hoặc quốc tịch đều được trả về
    public List<Author> searchByKeyword(String keyword) {
        return authorRepository.searchByKeyword(keyword);
    }
}
