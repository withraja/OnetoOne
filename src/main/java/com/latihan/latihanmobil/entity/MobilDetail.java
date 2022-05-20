package com.latihan.latihanmobil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mobil_detail")
@NoArgsConstructor
@Getter
@Setter
public class MobilDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name = "is_new")
    private Boolean isNew;

    @Column(name = "year")
    private Integer year;

    @Column(name = "price")
    private Double price;

}
