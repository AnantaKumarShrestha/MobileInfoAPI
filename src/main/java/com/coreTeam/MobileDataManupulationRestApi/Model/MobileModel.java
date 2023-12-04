package com.coreTeam.MobileDataManupulationRestApi.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
public class MobileModel {


    @Id
    @Generated
    private int id;
    private String companyName;
    private String modelName;
    private String price;
    private String imei;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateCreated;

    @UpdateTimestamp
    private LocalDate lastUpdate;



}
