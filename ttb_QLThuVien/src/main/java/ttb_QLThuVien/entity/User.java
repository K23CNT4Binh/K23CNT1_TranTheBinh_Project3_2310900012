package ttb_QLThuVien.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ttb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ttb_ma_nguoi_dung")
    private Long id;

    @Column(name = "ttb_ten_dang_nhap", nullable = false, unique = true)
    private String username;

    @Column(name = "ttb_mat_khau", nullable = false)
    private String password;

    @Column(name = "ttb_ho_ten")
    private String fullName;

    @Column(name = "ttb_email")
    private String email;

    @Column(name = "ttb_vai_tro")
    private String role;

    @Column(name = "ttb_kich_hoat")
    private Boolean active;

    // --- KHỚP ĐÚNG CỘT TRONG DB ---
    @Column(name = "ttb_ngay_tao", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "ttb_ngay_cap_nhat")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
