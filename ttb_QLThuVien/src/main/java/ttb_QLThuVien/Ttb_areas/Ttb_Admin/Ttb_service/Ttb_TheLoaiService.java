package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttb_QLThuVien.Ttb_entity.Ttb_TheLoai;
import ttb_QLThuVien.Ttb_repository.Ttb_TheLoaiRepository;

import java.util.List;
import java.util.Optional;

@Service
public class Ttb_TheLoaiService {

    @Autowired
    private Ttb_TheLoaiRepository theLoaiRepository;

    public List<Ttb_TheLoai> getAllTheLoai() {
        return theLoaiRepository.findAll();
    }

    public Optional<Ttb_TheLoai> getTheLoaiById(Long id) {
        return theLoaiRepository.findById(id);
    }

    public Ttb_TheLoai saveTheLoai(Ttb_TheLoai theLoai) {
        return theLoaiRepository.save(theLoai);
    }

    public void deleteTheLoai(Long id) {
        theLoaiRepository.deleteById(id);
    }
}
