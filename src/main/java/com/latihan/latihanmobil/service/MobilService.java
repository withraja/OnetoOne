package com.latihan.latihanmobil.service;

import java.util.List;

import com.latihan.latihanmobil.DTO.MobilDTO;

public interface MobilService {

    public MobilDTO create(MobilDTO mobilDTO);

    public List<MobilDTO> getAll(int pageNo, int pageSize);
}
