package ttb_QLThuVien.Ttb_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttb_QLThuVien.Ttb_entity.Ttb_TheLoai;
import ttb_QLThuVien.Ttb_repository.Ttb_TheLoaiRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Ttb_TheLoaiService {

    @Autowired
    private Ttb_TheLoaiRepository theLoaiRepository;

    // Lấy tất cả thể loại
    public List<Ttb_TheLoai> getAllTheLoai() {
        return theLoaiRepository.findAll();
    }

    // Lấy thể loại theo ID
    public Optional<Ttb_TheLoai> getTheLoaiById(Long id) {
        return theLoaiRepository.findById(id);
    }

    // Thêm / Sửa thể loại, kiểm tra trùng tên
    public Ttb_TheLoai saveTheLoai(Ttb_TheLoai theLoai) throws RuntimeException {
        if (theLoai.getId() == null) { // thêm mới
            if (theLoaiRepository.existsByTenTheLoai(theLoai.getTenTheLoai())) {
                throw new RuntimeException("Thể loại đã tồn tại!");
            }
        } else { // sửa
            Optional<Ttb_TheLoai> existing = theLoaiRepository.findById(theLoai.getId());
            if (existing.isPresent() && !existing.get().getTenTheLoai().equals(theLoai.getTenTheLoai())
                    && theLoaiRepository.existsByTenTheLoai(theLoai.getTenTheLoai())) {
                throw new RuntimeException("Tên thể loại đã tồn tại!");
            }
        }
        return theLoaiRepository.save(theLoai);
    }

    // Xóa thể loại theo ID
    public void deleteTheLoai(Long id) {
        theLoaiRepository.deleteById(id);
    }

    // Lọc theo trạng thái
    public List<Ttb_TheLoai> filterByStatus(String trangThai) {
        return theLoaiRepository.findAll()
                .stream()
                .filter(t -> t.getTrangThai() != null && t.getTrangThai().equalsIgnoreCase(trangThai))
                .collect(Collectors.toList());
    }

    // Tìm kiếm theo keyword (tên hoặc mô tả)
    public List<Ttb_TheLoai> searchTheLoai(String keyword) {
        return theLoaiRepository.findAll()
                .stream()
                .filter(t -> (t.getTenTheLoai() != null && t.getTenTheLoai().toLowerCase().contains(keyword.toLowerCase()))
                        || (t.getMoTa() != null && t.getMoTa().toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    // Kiểm tra tồn tại tên thể loại
    public boolean existsByTenTheLoai(String tenTheLoai) {
        return theLoaiRepository.existsByTenTheLoai(tenTheLoai);
    }
}
