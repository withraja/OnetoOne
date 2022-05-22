package com.latihan.latihanmobil.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.latihan.latihanmobil.DTO.MobilDTO;
import com.latihan.latihanmobil.DTO.MobilDetailDTO;
import com.latihan.latihanmobil.entity.Mobil;
import com.latihan.latihanmobil.entity.MobilDetail;
import com.latihan.latihanmobil.repository.MobilRepository;
import com.latihan.latihanmobil.service.MobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobilServiceImpl implements MobilService {

    @Autowired
    private MobilRepository mobilRepository;

    @Override
    public MobilDTO create(MobilDTO mobilDTO) {

        // INI UNTUK MEMBUAT MOBIL DI DATABASE
        Mobil mobil = convertMobilDTOtoMobil(mobilDTO);

        Mobil mobilCreated = mobilRepository.save(mobil);

        // INI UNTUK MOBIL YANG SUDAH TERBUAT DI DATABASE
        MobilDTO mobilDTOCreated = convertMobiltoMobilDTO(mobilCreated);

        return mobilDTOCreated;
    }

    private MobilDTO convertMobiltoMobilDTO(Mobil mobilCreated) {
        MobilDTO mobilDTOCreated = new MobilDTO();
        mobilDTOCreated.setId(mobilCreated.getId());
        mobilDTOCreated.setIsDeleted(mobilCreated.getIsDeleted());
        mobilDTOCreated.setBrand(mobilCreated.getBrand());

        MobilDetailDTO mobilDetailDTOCreated = new MobilDetailDTO();
        mobilDetailDTOCreated.setId(mobilCreated.getMobilDetail().getId());
        mobilDetailDTOCreated.setColor(mobilCreated.getMobilDetail().getColor());
        mobilDetailDTOCreated.setIsNew(mobilCreated.getMobilDetail().getIsNew());
        mobilDetailDTOCreated.setYear(mobilCreated.getMobilDetail().getYear());
        mobilDetailDTOCreated.setPrice(mobilCreated.getMobilDetail().getPrice());

        mobilDTOCreated.setMobilDetailDTO(mobilDetailDTOCreated);
        return mobilDTOCreated;
    }

    private Mobil convertMobilDTOtoMobil(MobilDTO mobilDTO) {
        Mobil mobil = new Mobil();
        mobil.setBrand(mobilDTO.getBrand());
        mobil.setIsDeleted(false);

        MobilDetail mobilDetail = new MobilDetail();
        mobilDetail.setColor(mobilDTO.getMobilDetailDTO().getColor());
        mobilDetail.setIsNew(mobilDTO.getMobilDetailDTO().getIsNew());
        mobilDetail.setYear(mobilDTO.getMobilDetailDTO().getYear());
        mobilDetail.setPrice(mobilDTO.getMobilDetailDTO().getPrice());
        mobil.setMobilDetail(mobilDetail);
        return mobil;
    }

    @Override
    public List<MobilDTO> getAll() {
        List<Mobil> mobilList = mobilRepository.findAll();
        List<MobilDTO> mobilDTOList = new ArrayList<>();

        for (Mobil mobil : mobilList) {
            MobilDTO mobilDTO = convertMobiltoMobilDTO(mobil);
            mobilDTOList.add(mobilDTO);
        }
        return mobilDTOList;
    }

}
