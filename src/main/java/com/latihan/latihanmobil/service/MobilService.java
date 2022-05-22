package com.latihan.latihanmobil.service;

import com.latihan.latihanmobil.DTO.MobilDTO;
import com.latihan.latihanmobil.DTO.ResponseMobilDTO;

public interface MobilService {

    public MobilDTO create(MobilDTO mobilDTO);

    public ResponseMobilDTO getAll(int pageNo, int pageSize);

    public MobilDTO deleteById(Integer id);
}
