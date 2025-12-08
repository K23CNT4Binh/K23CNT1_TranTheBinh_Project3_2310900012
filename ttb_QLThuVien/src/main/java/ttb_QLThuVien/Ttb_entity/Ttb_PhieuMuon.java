package ttb_QLThuVien.Ttb_entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ttb_phieu_muon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ttb_PhieuMuon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ttb_id")
    private Long id;

    // Người mượn
    @ManyToOne
    @JoinColumn(name = "ttb_user_id", nullable = false)
    private Ttb_NguoiDung user;

    @Column(name = "ttb_ma_phieu", nullable = false, unique = true)
    private String maPhieu;

    @Column(name = "ttb_ngay_muon", nullable = false)
    private LocalDateTime ngayMuon;

    @Column(name = "ttb_ngay_hen_tra", nullable = false)
    private LocalDateTime ngayHenTra;

    @Column(name = "ttb_ngay_tra")
    private LocalDateTime ngayTra;

    @Column(name = "ttb_trang_thai", nullable = false)
    private String trangThai;

    @Column(name = "ttb_so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "ttb_phi_phat")
    private Double phiPhat;

    @Column(name = "ttb_ghi_chu")
    private String ghiChu;

    @Column(name = "ttb_created_at", insertable = false, updatable = false)
    private LocalDateTime ngayTao;

    @Column(name = "ttb_updated_at", insertable = false, updatable = false)
    private LocalDateTime ngayCapNhat;
}
