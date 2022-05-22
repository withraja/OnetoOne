package com.latihan.latihanmobil.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mobil")
@NoArgsConstructor
@Getter
@Setter
public class Mobil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL) // OneToOne
    @JoinColumn(name = "mobil_detail_id", referencedColumnName = "id", unique = true)
    private MobilDetail mobilDetail;

}