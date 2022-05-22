package com.latihan.latihanmobil.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MobilDetailDTO {
    private Long id;

    private String color;

    private Boolean isNew;

    private Integer year;

    private Double price;
}
