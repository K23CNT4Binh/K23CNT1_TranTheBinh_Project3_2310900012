package ttb_QLThuVien.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "ttb_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_danh_muc", nullable = false, length = 100)
    private String tenDanhMuc;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "ngay_tao", updatable = false)
    private java.sql.Timestamp ngayTao;

    @Column(name = "ngay_cap_nhat")
    private java.sql.Timestamp ngayCapNhat;

    // ============================
    // QUAN HỆ 1-N VỚI BOOK
    // ============================
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
