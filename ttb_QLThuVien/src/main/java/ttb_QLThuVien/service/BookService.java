package ttb_QLThuVien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttb_QLThuVien.entity.Book;
import ttb_QLThuVien.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Lấy tất cả sách
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Lấy sách theo ID
    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    // Thêm / Cập nhật sách
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Xóa sách
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    // Tìm kiếm sách theo tên
    public List<Book> searchByTenSach(String tenSach) {
        return bookRepository.findByTenSachContainingIgnoreCase(tenSach);
    }

    // Tìm kiếm sách theo tác giả
    public List<Book> searchByAuthor(String tenTacGia) {
        return bookRepository.findByAuthor_TenTacGiaContainingIgnoreCase(tenTacGia);
    }

    // Tìm kiếm sách theo thể loại
    public List<Book> searchByCategory(String categoryName) {
        return bookRepository.findByCategory_TenDanhMucContainingIgnoreCase(categoryName);
    }

    // Lọc sách theo trạng thái
    public List<Book> filterByTrangThai(String trangThai) {
        return bookRepository.findByTrangThai(trangThai);
    }

    // Lọc sách còn hàng
    public List<Book> getBooksInStock() {
        return bookRepository.findBySoLuongGreaterThan(0);
    }

    // Tìm kiếm tổng hợp theo tên sách, tác giả hoặc thể loại
    public List<Book> searchByKeyword(String keyword) {
        return bookRepository.searchByKeyword(keyword);
    }

}
