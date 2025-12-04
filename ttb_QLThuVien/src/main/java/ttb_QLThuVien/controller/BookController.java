package ttb_QLThuVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.entity.Book;
import ttb_QLThuVien.service.BookService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Hiển thị danh sách tất cả sách
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "admin/books/book-list";
    }

    // Hiển thị form thêm sách mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/books/book-form";
    }

    // Thêm hoặc cập nhật sách
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    // Hiển thị form chỉnh sửa sách
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "admin/books/book-form";
    }

    // Xóa sách
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    // Tìm kiếm tổng hợp theo tên sách, tác giả hoặc thể loại
    @GetMapping("/search")
    public String searchBooks(@RequestParam("keyword") String keyword, Model model) {
        List<Book> books = bookService.searchByKeyword(keyword); // Gọi method tổng hợp
        model.addAttribute("books", books);
        return "admin/books/book-list";
    }

    // Lọc sách theo trạng thái
    @GetMapping("/filter/status")
    public String filterByTrangThai(@RequestParam("trangThai") String trangThai, Model model) {
        List<Book> books = bookService.filterByTrangThai(trangThai);
        model.addAttribute("books", books);
        return "admin/books/book-list";
    }

    // Lọc sách còn hàng
    @GetMapping("/in-stock")
    public String getBooksInStock(Model model) {
        List<Book> books = bookService.getBooksInStock();
        model.addAttribute("books", books);
        return "admin/books/book-list";
    }
}
