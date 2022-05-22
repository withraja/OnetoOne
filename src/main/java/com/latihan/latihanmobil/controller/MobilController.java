package com.latihan.latihanmobil.controller;

import com.latihan.latihanmobil.DTO.MobilDTO;
import com.latihan.latihanmobil.DTO.ResponseMobilDTO;
import com.latihan.latihanmobil.service.MobilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobil")
public class MobilController {

    @Autowired
    private MobilService mobilService;

    @PostMapping("/create")
    public ResponseEntity<MobilDTO> create(@RequestBody MobilDTO mobilDTO) {
        MobilDTO mobilDTOCreated = this.mobilService.create(mobilDTO);
        return new ResponseEntity<>(mobilDTOCreated, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseMobilDTO> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {
        ResponseMobilDTO responseMobilDTO = this.mobilService.getAll(pageNo, pageSize);
        return new ResponseEntity<>(responseMobilDTO, HttpStatus.OK);
    }
}
