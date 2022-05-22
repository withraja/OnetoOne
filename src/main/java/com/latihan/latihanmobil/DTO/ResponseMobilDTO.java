package com.latihan.latihanmobil.DTO;

import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseMobilDTO {
    private List<MobilDTO> mobilDTO;
    private Boolean loadSuccess;
    private Integer totalPage;
    private Long totalData;
}
