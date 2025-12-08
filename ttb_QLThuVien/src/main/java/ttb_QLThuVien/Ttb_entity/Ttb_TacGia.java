package ttb_QLThuVien.Ttb_entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ttb_tac_gia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ttb_TacGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ttb_id")
    private Long id;

    @Column(name = "ttb_ho_ten", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "ttb_bi_danh", length = 100)
    private String biDanh;

    @Column(name = "ttb_nam_sinh")
    private Integer namSinh;

    @Column(name = "ttb_nam_mat")
    private Integer namMat;

    @Column(name = "ttb_quoc_tich", length = 50)
    private String quocTich;

    @Column(name = "ttb_mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "ttb_trang_thai", length = 20)
    private String trangThai;

    @Column(name = "ttb_ngay_tao", updatable = false)
    private LocalDateTime ngayTao;

    @Column(name = "ttb_ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    @PrePersist
    public void onCreate() {
        ngayTao = LocalDateTime.now();
        ngayCapNhat = LocalDateTime.now();
        if (trangThai == null) {
            trangThai = "active"; // mặc định giống SQL
        }
    }

    @PreUpdate
    public void onUpdate() {
        ngayCapNhat = LocalDateTime.now();
    }
}
