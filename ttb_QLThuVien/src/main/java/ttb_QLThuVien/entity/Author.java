package ttb_QLThuVien.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "ttb_author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_tac_gia", nullable = false, length = 255)
    private String tenTacGia;

    @Column(name = "but_danh", length = 255)
    private String butDanh;

    @Column(name = "tieu_su", columnDefinition = "TEXT")
    private String tieuSu;

    @Column(name = "quoc_tich", length = 100)
    private String quocTich;

    @Column(name = "ngay_sinh")
    private java.sql.Date ngaySinh;

    @Column(name = "ngay_mat")
    private java.sql.Date ngayMat;

    @Column(name = "ngay_tao", updatable = false)
    private java.sql.Timestamp ngayTao;

    @Column(name = "ngay_cap_nhat")
    private java.sql.Timestamp ngayCapNhat;

    // ============================
    // QUAN HỆ 1-N VỚI BOOK
    // ============================
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
