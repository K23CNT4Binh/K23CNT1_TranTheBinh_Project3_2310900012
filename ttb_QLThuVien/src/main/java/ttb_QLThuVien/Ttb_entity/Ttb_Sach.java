package ttb_QLThuVien.Ttb_entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ttb_sach")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ttb_Sach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ttb_id")
    private Long id;

    @Column(name = "ttb_tieu_de", nullable = false)
    private String tieuDe;

    @Column(name = "ttb_ma_isbn", unique = true)
    private String maIsbn;

    @Column(name = "ttb_mo_ta")
    private String moTa;

    @Column(name = "ttb_nam_xuat_ban")
    private Integer namXuatBan;

    @Column(name = "ttb_nha_xuat_ban")
    private String nhaXuatBan;

    @Column(name = "ttb_so_trang")
    private Integer soTrang;

    @Column(name = "ttb_ngon_ngu")
    private String ngonNgu;

    @Column(name = "ttb_so_luong")
    private Integer soLuong = 0;

    @Column(name = "ttb_anh_bia")
    private String anhBia;

    @Transient
    private MultipartFile anhBiaFile;

    @Column(name = "ttb_trang_thai")
    private String trangThai = "available";

    @Column(name = "ttb_ngay_tao", updatable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    @Column(name = "ttb_ngay_cap_nhat")
    private LocalDateTime ngayCapNhat = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.ngayCapNhat = LocalDateTime.now();
    }

    // ⭐ ManyToMany với tác giả
    @ManyToMany
    @JoinTable(
            name = "ttb_sach_tac_gia",
            joinColumns = @JoinColumn(name = "ttb_sach_id"),
            inverseJoinColumns = @JoinColumn(name = "ttb_tac_gia_id")
    )
    private List<Ttb_TacGia> tacGias;

    // ⭐ ManyToOne với thể loại
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ttb_the_loai_id")
    private Ttb_TheLoai theLoai;
}
