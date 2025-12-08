package ttb_QLThuVien.Ttb_entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ttb_phieu_muon_chi_tiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ttb_PhieuMuonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ttb_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ttb_phieu_muon_id", nullable = false)
    private Ttb_PhieuMuon phieuMuon;

    @ManyToOne
    @JoinColumn(name = "ttb_sach_id", nullable = false)
    private Ttb_Sach sach;

    @Column(name = "ttb_so_luong")
    private Integer soLuong;

    @Column(name = "ttb_ngay_tra_ct")
    private LocalDateTime ngayTraCt;

    @Column(name = "ttb_trang_thai")
    private String trangThai;
}
