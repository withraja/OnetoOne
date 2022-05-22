package com.latihan.latihanmobil.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.latihan.latihanmobil.DTO.MobilDTO;
import com.latihan.latihanmobil.DTO.MobilDetailDTO;
import com.latihan.latihanmobil.DTO.ResponseMobilDTO;
import com.latihan.latihanmobil.entity.Mobil;
import com.latihan.latihanmobil.entity.MobilDetail;
import com.latihan.latihanmobil.repository.MobilRepository;
import com.latihan.latihanmobil.service.MobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        if (mobilCreated.getId() == null) {
            // CREATE DATA BARU
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

        // UPDATE DATA LAMA
        else {
            Optional<Mobil> existingMobilOptional = mobilRepository.findById(mobilCreated.getId());
            Mobil existingMobil = existingMobilOptional.get();

            MobilDTO mobilDTOUpdated = new MobilDTO();
            mobilDTOUpdated.setId(existingMobil.getId());
            mobilDTOUpdated.setIsDeleted(existingMobil.getIsDeleted());
            mobilDTOUpdated.setBrand(existingMobil.getBrand());

            MobilDetailDTO mobilDetailDTOUpdated = new MobilDetailDTO();
            mobilDetailDTOUpdated.setId(existingMobil.getMobilDetail().getId());
            mobilDetailDTOUpdated.setColor(existingMobil.getMobilDetail().getColor());
            mobilDetailDTOUpdated.setIsNew(existingMobil.getMobilDetail().getIsNew());
            mobilDetailDTOUpdated.setYear(existingMobil.getMobilDetail().getYear());
            mobilDetailDTOUpdated.setPrice(existingMobil.getMobilDetail().getPrice());

            mobilDTOUpdated.setMobilDetailDTO(mobilDetailDTOUpdated);
            return mobilDTOUpdated;
        }
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
    public ResponseMobilDTO getAll(int pageNo, int pageSize) {
        ResponseMobilDTO responseMobilDTO = new ResponseMobilDTO();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        Page<Mobil> mobilList = mobilRepository.findAll(pageable);
        List<MobilDTO> mobilDTOList = new ArrayList<>();

        for (Mobil mobil : mobilList) {
            MobilDTO mobilDTO = convertMobiltoMobilDTO(mobil);
            mobilDTOList.add(mobilDTO);
        }

        mobilDTOList = mobilDTOList.stream().filter(mobilDTO -> {
            return mobilDTO.getIsDeleted() != null && mobilDTO.getIsDeleted() == false;
        })
                .collect(Collectors.toList());

        responseMobilDTO.setMobilDTO(mobilDTOList);
        responseMobilDTO.setLoadSuccess(true);
        responseMobilDTO.setTotalPage(mobilList.getTotalPages());
        responseMobilDTO.setTotalData(mobilDTOList.size());

        return responseMobilDTO;
    }

    @Override
    public MobilDTO getById(Long id) {
        Optional<Mobil> mobilOptional = mobilRepository.findById(id);
        Mobil mobil = new Mobil();

        if (mobilOptional.isPresent()) {
            mobil = mobilOptional.get();
            String brand = mobil.getBrand();
            System.out.println(brand);
            Double price = mobil.getMobilDetail().getPrice();
            System.out.println("Rp. " + price);
            return convertMobiltoMobilDTO(mobil);
        }

        return null;
    }

    @Override
    public MobilDTO deleteById(Integer id) {
        Optional<Mobil> mobilOptional = mobilRepository.findById(id.longValue());
        Mobil mobil = null;
        if (mobilOptional.isPresent()) {
            mobil = mobilOptional.get();
            mobil.setIsDeleted(true);
            Mobil mobilUpdated = mobilRepository.save(mobil);

            MobilDTO mobilDTOUpdated = convertMobiltoMobilDTO(mobilUpdated);
            return mobilDTOUpdated;
        }

        return null;
    }

}
