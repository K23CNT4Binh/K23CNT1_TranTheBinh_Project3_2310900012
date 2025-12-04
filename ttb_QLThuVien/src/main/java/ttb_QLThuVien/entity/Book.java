package ttb_QLThuVien.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ttb_books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_sach", nullable = false, length = 255)
    private String tenSach;

    @Column(name = "ma_isbn", unique = true, length = 50)
    private String maIsbn;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "nam_xuat_ban")
    private Integer namXuatBan;

    @Column(name = "nha_xuat_ban", length = 255)
    private String nhaXuatBan;

    @Column(name = "so_trang")
    private Integer soTrang;

    @Column(name = "ngon_ngu", length = 50)
    private String ngonNgu;

    @Column(name = "anh_bia", length = 255)
    private String anhBia;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    @Column(name = "ngay_tao", updatable = false)
    private java.sql.Timestamp ngayTao;

    @Column(name = "ngay_cap_nhat")
    private java.sql.Timestamp ngayCapNhat;

    // ============================
    // QUAN HỆ VỚI TÁC GIẢ
    // ============================
    @ManyToOne
    @JoinColumn(name = "tac_gia_id", nullable = false)
    private Author author; // mapping tới entity Author
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
