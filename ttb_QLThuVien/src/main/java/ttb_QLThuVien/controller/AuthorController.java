package ttb_QLThuVien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ttb_QLThuVien.entity.Author;
import ttb_QLThuVien.service.AuthorService;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "admin/authors/author-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        return "admin/authors/author-form";
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("author") Author author) {
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Author author = authorService.getAuthorById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
        model.addAttribute("author", author);
        return "admin/authors/author-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Integer id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

    // Tìm kiếm gộp: tên tác giả hoặc quốc tịch
    @GetMapping("/search")
    public String searchAuthors(@RequestParam("keyword") String keyword, Model model) {
        List<Author> authors = authorService.searchByKeyword(keyword);
        model.addAttribute("authors", authors);
        return "admin/authors/author-list";
    }
}
