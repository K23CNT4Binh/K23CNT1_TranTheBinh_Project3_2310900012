package ttb_QLThuVien.Ttb_areas.Ttb_Admin.Ttb_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttb_QLThuVien.Ttb_entity.Ttb_NguoiDung;
import ttb_QLThuVien.Ttb_repository.Ttb_NguoiDungRepository;

import java.util.List;
import java.util.Optional;

@Service
public class Ttb_NguoiDungService {

    @Autowired
    private Ttb_NguoiDungRepository nguoiDungRepository;

    public List<Ttb_NguoiDung> getAllNguoiDung() {
        return nguoiDungRepository.findAll();
    }

    public Optional<Ttb_NguoiDung> getNguoiDungById(Long id) {
        return nguoiDungRepository.findById(id);
    }

    public Ttb_NguoiDung saveNguoiDung(Ttb_NguoiDung nguoiDung) {
        return nguoiDungRepository.save(nguoiDung);
    }

    public void deleteNguoiDung(Long id) {
        nguoiDungRepository.deleteById(id);
    }

    public Optional<Ttb_NguoiDung> getByUsername(String username) {
        return nguoiDungRepository.findByUsername(username);
    }
}
