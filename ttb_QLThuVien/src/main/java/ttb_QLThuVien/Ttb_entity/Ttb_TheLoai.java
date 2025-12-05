package ttb_QLThuVien.Ttb_entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ttb_the_loai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ttb_TheLoai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ttb_id")
    private Long id;

    @Column(name = "ttb_ten_the_loai", nullable = false)
    private String tenTheLoai;

    @Column(name = "ttb_mo_ta")
    private String moTa;

    @Column(name = "ttb_ngay_tao", updatable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    @Column(name = "ttb_ngay_cap_nhat")
    private LocalDateTime ngayCapNhat = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.ngayCapNhat = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "theLoai", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ttb_Sach> sachList;
}
