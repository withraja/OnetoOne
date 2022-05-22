package com.latihan.latihanmobil.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class MobilDTO {

    private Long id;

    private String brand;

    private Boolean isDeleted;

    private MobilDetailDTO MobilDetailDTO;
}
