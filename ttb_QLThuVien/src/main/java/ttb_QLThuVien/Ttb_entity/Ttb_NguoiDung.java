package ttb_QLThuVien.Ttb_entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ttb_nguoi_dung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ttb_NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ttb_id")
    private Long id;

    @Column(name = "ttb_username", nullable = false, unique = true)
    private String username;

    @Column(name = "ttb_password", nullable = false)
    private String password;

    @Column(name = "ttb_ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "ttb_email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "ttb_vai_tro", nullable = false)
    private Ttb_VaiTro vaiTro = Ttb_VaiTro.ROLE_USER;

    @Column(name = "ttb_active")
    private Boolean active = true;

    @Column(name = "ttb_ngay_tao", updatable = false)
    private LocalDateTime ngayTao = LocalDateTime.now();

    @Column(name = "ttb_ngay_cap_nhat")
    private LocalDateTime ngayCapNhat = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.ngayCapNhat = LocalDateTime.now();
    }
}
